/*
@author: Ericsson Colborn, evc252

This program merges two datasets, one of which is a previous merge:
- the median income + median rental cost (most july-sep 2022) per city
- # of good days per year per city

The datasets are loaded into separate Spark dataframes, joined
into a single dataframe and then exported to a csv file.
*/

import org.apache.spark.sql.functions._

val data_dir = "final_project/data/";
// val data_dir = "file://Top-Towns-To-Take-Over-Tech/joiningDatasets/data/";
val rent_income = spark.read.option("header", "true").csv(data_dir + "rent_income.csv");
val weather = spark.read.option("header", "true").csv(data_dir + "weather.csv");

// val good_days = 
val join_df = rent_income.join(weather, Seq("city", "state"), "inner");
// select(
//     col("city"),
//     col("state")
// );

// TODO: calculate average of 3 rents

join_df.show();
join_df.write.option("header", "true").csv("final_project/merge_all_output");