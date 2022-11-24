import org.apache.spark.sql.functions._
// import org.apache.spark.sql.functions.{min, max}
// import org.apache.spark.sql.functions.rank

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.Window

val data_dir = "final_project/data/";
val data = spark.read.option("header", "true").csv(data_dir + "rent_income_weather_geo.csv");


// ---------- AVERAGE GOOD DAYS ----------- \\

// add new column
val df1 = data.withColumn(
    "average_good_days", ((col("num_of_good_days_2020") + col("num_of_good_days_2021") + col("num_of_good_days_2022")) / 3).cast("int")
)
// drop three original columns
.drop(
    "num_of_good_days_2020",
    "num_of_good_days_2021",
    "num_of_good_days_2022"
);


// ---------- AVERAGE RENT ----------- \\

val df2 = df1.withColumn(
    "average_rent", ((col("median_rent_2022-07-31") + col("median_rent_2022-08-31") + col("median_rent_2022-09-30")) / 3).cast("int")
)
.drop(
    "median_rent_2022-07-31",
    "median_rent_2022-08-31",
    "median_rent_2022-09-30"
)

println("\n\n******* Average Rent And Weather ***********")
df2.show();
println("************************************************\n");


// ---------- SCORE FORMULA ----------- \\

// The formula to calculate the score is score(income/average_rent) * 60% + score(average_good_days) * 40%
// score() can be understood as a method that calculate the position of the current data among all the data sets
// where the largest data yeilds 100 and the lowest yields 0

// Check datatypes for columns:
// println(df2.schema("average_good_days").dataType); // we cannot assign a integer type to a double type variable

// Get the min and max for weather
val Row(minWeather: Integer, maxWeather: Integer) = df2.agg(min("average_good_days"), max("average_good_days")).head();
println("minWeather is " + minWeather);
println("maxWeather is " + maxWeather);
val weatherRange = maxWeather - minWeather;

// Get the min and max for income/rent
val Row(minRatio: Double, maxRatio: Double) = df2.agg(min($"income" / $"average_rent"), max($"income" / $"average_rent")).head();
println("minRatio is " + minRatio);
println("maxRatio is " + maxRatio);
val ratioRange = maxRatio - minRatio;

// Create a new column of 'score' using the formula
val df3 = df2.withColumn("score", ($"income"/$"average_rent"-minRatio)/ratioRange*60+($"average_good_days"-minWeather)/weatherRange*40);

println("******* Dataframe after added column 'score' ***********");
df3.show()
println("***************************************************\n");

// Rank the cities based on scores
// Reference: https://hadoopsters.com/spark-starter-guide-4-9-how-to-rank-or-row-number-data-a9e980b8b8cf
val w = Window.orderBy($"score".desc);
val df4 = df3.withColumn("row_number", row_number().over(w)).withColumnRenamed("row_number", "rank")

println("******* Dataframe After Ranking ***********")
df4.show()
println("***************************************************\n");

// rearrange the columns' positions
val df5 = df4.select("rank","city","state","income","average_good_days", "average_rent", "lat", "lng", "score");

println("******* After Rearrange The Columns' positions ***********")
df5.show()
println("***************************************************\n");


// write to .csv in one part (one file)
// df5.coalesce(1).write.option("header", "true").csv("final_project/average_output");

// exit spark shell
System.exit(0);
