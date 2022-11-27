/*
@author: Ericsson Colborn, evc252

- The dataset that I'm contributing to this project contains the median rental cost
for the cities we're considering.
- The raw data is from Zillow and contains data from now to all the way back in 2019
- For this project, we really only need current data, since costs from years prior
    aren't as pertinent to someone looking to rent now. We consider that data to be stale.
- Thus, this scala program cleans the Zillow data by dropping those unnecessary columns.

** same program submitted for homework 7 **
*/

import org.apache.spark.sql.functions._

val df = spark.read.format("csv")
.option("inferSchema", true)
.option("header", "true")
.load("shared_data/raw/raw_rent.csv");

// drop all but last few months of rental data
// also drop any rows with null values
var new_df = df.select("RegionName", "StateName",
"2022-07-31", "2022-08-31", "2022-09-30")
.na.drop();

// user-defined function
// rental data has lots of decimal places... only concerned with whole numbers for rental cost
def truncate_rent(str: String): String = {
    str.slice(0,4);
}

val truncate_rent_udf = udf(truncate_rent _);

val clean_df = new_df
.withColumnRenamed("RegionName", "city")
.withColumnRenamed("StateName", "state")
.select(
    col("city"),
    col("state"),
    truncate_rent_udf(col("2022-07-31")).as("rent_2022-07-31"),
    truncate_rent_udf(col("2022-08-31")).as("rent_2022-08-31"),
    truncate_rent_udf(col("2022-09-30")).as("rent_2022-09-30")
);

// write to hdfs
clean_df.write.option("header", "true").csv("shared_data/spark_output/clean_rent");

// exit spark shell
System.exit(0);
