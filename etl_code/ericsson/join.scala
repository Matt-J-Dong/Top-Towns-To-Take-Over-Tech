/*
@author: Ericsson Colborn, evc252

This program merges the following datasets:
- median income per city
- median monthly (july-sep 2022) per city
- # of good days per year (2020-2022) per city

The datasets are loaded into separate Spark dataframes, joined
into a single dataframe and then exported to a csv file.
*/

import org.apache.spark.sql.functions._

val data_dir = "shared_data/clean/";

val rent = spark.read.option("header", "true").csv(data_dir + "clean_rent.csv");
val income = spark.read.option("header", "true").csv(data_dir + "clean_income.csv");
// --- WAITING ON WEATHER TO BE ADDED --- val weather = spark.read.option("header", "true").csv(data_dir + "clean_weather.csv");
val geo = spark.read.option("header", "true").csv(data_dir + "clean_geo.csv")
.drop(col("state"));

val join_df = rent
// join income
.join(income, Seq("city"), "inner")
// join weather
// --- WAITING ON WEATHER TO BE ADDED --- .join(weather, Seq("city", "state"), "inner");
// join geo
.join(geo, Seq("city"), "inner");

// write to hdfs
join_df.coalesce(1).write.option("header", "true").csv("final_project/join_output");

// exit Spark shell
System.exit(0);