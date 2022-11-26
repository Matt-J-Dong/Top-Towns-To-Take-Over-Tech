import org.apache.spark.sql.functions._

val data_dir = "final_project/data/";
val rent_income_weather = spark.read.option("header", "true").csv(data_dir + "rent_income_weather.csv");
val geo = spark.read.option("header", "true").csv(data_dir + "citiesLatLong.csv");

val join_df = rent_income_weather.join(geo, Seq("city", "state"), "inner");

// TODO: calculate average of 3 rents + average of 3 good days 

join_df.show();
join_df.coalesce(1).write.option("header", "true").csv("final_project/merge_geo_output");