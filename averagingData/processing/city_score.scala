/*
@author: Zhiquan Cao, zc1548

This script calculate the score for each city and rank them by the scores

*/
import org.apache.spark.sql.functions.{min, max}
import org.apache.spark.sql.Row
import org.apache.spark.sql.functions.rank
import org.apache.spark.sql.expressions.Window


val df = spark.read.format("csv")
.option("inferSchema", true)
.option("header", "true")
.load("project/data/averaged.csv");

println("\n\n******* Original dataframe (first 2 records) ***********")
df.show(2) 
println("************************************************\n");

// The formula to calculate the score is 
// score(income/average_rent) * 60% + score(average_good_days) * 40%
// score() can be understood as a method that calculate the position of the current data among all the data sets
// where the largest data yeilds 100 and the lowest yields 0

// check datatypes for columns
// println(df.schema("average_good_days").dataType); // we cannot assign a integer type to a double type variable

// get the min and max for weather
val Row(minWeather: Integer, maxWeather: Integer) = df.agg(min("average_good_days"), max("average_good_days")).head();
println("minWeather is " + minWeather);
println("maxWeather is " + maxWeather);
val weatherRange = maxWeather - minWeather;

// get the min and max for income/rent
val Row(minRatio: Double, maxRatio: Double) = df.agg(min($"income" / $"average_rent"), max($"income" / $"average_rent")).head();
println("minRatio is " + minRatio);
println("maxRatio is " + maxRatio);
val ratioRange = maxRatio - minRatio;

// create a new column of 'score' using the formula
val df1 = df.withColumn("score", ($"income"/$"average_rent"-minRatio)/ratioRange*60+($"average_good_days"-minWeather)/weatherRange*40);

println("******* Dataframe after added column 'score' ***********");
df1.show()
println("***************************************************\n");

// Rank the cities based on scores
// Reference: https://hadoopsters.com/spark-starter-guide-4-9-how-to-rank-or-row-number-data-a9e980b8b8cf
val w = Window.orderBy($"score".desc);
val df2 = df1.withColumn("row_number", row_number().over(w)).withColumnRenamed("row_number", "rank")

println("******* Dataframe after ranking ***********")
df2.show()
println("***************************************************\n");

// rearrange the columns' positions
val df3 = df2.select("rank","city","state","income","average_good_days", "average_rent", "lat", "lng", "score");

println("******* after rearrange the columns' positions ***********")
df3.show()
println("***************************************************\n");

// write to hdfs
// df1.write.option("header", "true").csv("project/output/score_data");