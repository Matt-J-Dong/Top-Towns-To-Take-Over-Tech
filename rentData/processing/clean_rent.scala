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

val df = spark.read.format("csv")
.option("inferSchema", true)
.option("header", "true")
.load("hw7/rental_cost_per_month.csv");

println("\n\n******* Original dataframe (first 2 records) ***********")
df.show(2) 
println("************************************************\n");

// we only need the last few months of rental data
// prior to that is stale -> drop those cols

val new_df = df.select("RegionID", "RegionName", "StateName",
"2022-07-31", "2022-08-31", "2022-09-30");

println("******* Dataframe after dropping columns ***********")
new_df.show(5)
println("***************************************************\n");

// write to hdfs
new_df.write.csv("hw7/clean_data");
