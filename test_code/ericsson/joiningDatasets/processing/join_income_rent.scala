/*
@author: Ericsson Colborn, evc252

This program merges two datasets:
- the median income per city
- the median rental cost (most july-sep 2022) per city

The datasets are loaded into separate Spark dataframes, joined
into a single dataframe and then exported to a csv file.
*/

import org.apache.spark.sql.functions._

val data_dir = "final_project/data/";
val cities_rent = spark.read.option("header", "true").csv(data_dir + "cities_rent.csv");
val income = spark.read.option("header", "true").csv(data_dir + "income.csv");

// user-defined function for transforming columns
// rental data has lots of decimal places... 
// - only concerned with whole numbers for rental cost
def truncate_rent(str: String): String = {
    str.slice(0,4);
}

val truncate_rent_udf = udf(truncate_rent _);

val join_df = cities_rent.join(income, cities_rent("value") === income("City"), "inner")
.drop("City", "RegionID", "RegionName")
.withColumnRenamed("value", "city")
.withColumnRenamed("StateName", "state")
.withColumnRenamed("Income", "income")
.select(
    col("city"),
    col("state"),
    col("income"),
    truncate_rent_udf(col("2022-07-31")).as("median_rent_2022-07-31"),
    truncate_rent_udf(col("2022-08-31")).as("median_rent_2022-08-31"),
    truncate_rent_udf(col("2022-09-30")).as("median_rent_2022-09-30")
);

// TODO: calculate average of 3 rents

join_df.show();
join_df.write.option("header", "true").csv("final_project/merge_rent_income_output");