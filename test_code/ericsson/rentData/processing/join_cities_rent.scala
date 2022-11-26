/*
@author: Ericsson Colborn, evc252

Joins the list of cities (cities_with_state.txt) 
with the cleaned rental data (rental_cost.csv).

In effect, only selects the data for the cities we are
considering. If a city in cities.txt is not present in
rental_cost.csv, that city is dropped.

Fist step in filtering out cities not present
in all three datasets.
*/

val data_dir = "final_project/data/";
val cities = spark.read.text(data_dir + "cities_with_state.txt");
val rents = spark.read.option("header", "true").csv(data_dir + "rental_cost.csv");

val join_df = cities.join(rents, cities("value") === rents("RegionName"), "inner");

join_df.show(5);
join_df.write.option("header", "true").csv("final_project/join_output");

// cities.show(5);
// rents.show(5);