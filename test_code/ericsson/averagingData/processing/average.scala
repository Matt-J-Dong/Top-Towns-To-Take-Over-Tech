import org.apache.spark.sql.functions._

val data_dir = "final_project/data/";
val data = spark.read.option("header", "true").csv(data_dir + "rent_income_weather_geo.csv");


// ---------- AVERAGE GOOD DAYS ----------- \\

// add new column
val df = data.withColumn(
    "average_good_days", ((col("num_of_good_days_2020") + col("num_of_good_days_2021") + col("num_of_good_days_2022")) / 3).cast("int")
)
// drop three original columns
.drop(
    "num_of_good_days_2020",
    "num_of_good_days_2021",
    "num_of_good_days_2022"
);


// ---------- AVERAGE RENT ----------- \\
val df_ = df.withColumn(
    "average_rent", ((col("median_rent_2022-07-31") + col("median_rent_2022-08-31") + col("median_rent_2022-09-30")) / 3).cast("int")
)
.drop(
    "median_rent_2022-07-31",
    "median_rent_2022-08-31",
    "median_rent_2022-09-30"
)

df_.show();
// write to .csv in one part (one file)
df_.coalesce(1).write.option("header", "true").csv("final_project/average_output");

// exit spark shell
System.exit(0);