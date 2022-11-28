//CLEAN THE DATA

val df = spark.read.format("csv")
.option("inferSchema", true)
.option("header", "true")
.load("/user/evc252/shared_data/raw/raw_weather.csv");

println("\n\n******* Original dataframe (first 2 records) ***********")
df.show(2) 
println("************************************************\n");

val df1 = df.select("name","datetime","feelslike","humidity","precip","precipcover","windspeed","visibility","uvindex");

println("******* Dataframe after dropping columns ***********")
df1.show(2)
println("***************************************************\n");



// FILTER THE DATA

val df2 = df1.filter(
    df("feelslike").cast("int") > 60 && df("feelslike").cast("int") < 80 &&
    df("humidity").cast("int") > 40 && df("humidity").cast("int") < 80 &&
    df("precip").cast("int") * df("precipcover").cast("int") < 6 &&
    df("windspeed").cast("int") < 22 &&
    df("visibility").cast("int") >= 9 &&
    df("uvindex").cast("int") < 10
    );

println("\n\n******* Filtered Non-qualified Rows (first 5) ***********")
df2.show(5) 
println("************************************************\n");


// Change the content of "datatime" column: 
val df3 = df2.withColumn("datetime",substring(col("datetime"), 0, 4));


// Count by each city each year
// Reference: https://sparkbyexamples.com/spark/using-groupby-on-dataframe/
val df4 = df3.groupBy("name", "datetime").count();

println("\n\n******* Adjusted to City_Year_Count format for each row (first 5) ***********")
df4.show(5) 
println("************************************************\n");


// Create extra columns of years to store the number of good days for each city of the year
val df5 = df4.withColumn("2020", when($"datetime" === "2020", $"count".cast("int")).otherwise(0));
val df6 = df5.withColumn("2021", when($"datetime" === "2021", $"count".cast("int")).otherwise(0));
val df7 = df6.withColumn("2022", when($"datetime" === "2022", $"count".cast("int")).otherwise(0));

println("\n\n******* Extra columns added (first 5) ***********")
df7.show(5) 
println("************************************************\n");


// Reduce by year columns; only keep the name and sums of three year columns
val df8 = df7.groupBy("name").sum("2020", "2021", "2022");


// Update the column names
val df9 = df8.withColumnRenamed("sum(2020)","num_of_good_days_2020");
val df10 = df9.withColumnRenamed("sum(2021)","num_of_good_days_2021");
val df11 = df10.withColumnRenamed("sum(2022)","num_of_good_days_2022");

println("******* After the final reduce by the sum of year columns columns ***********");
df11.show(10);
println("***************************************************\n");

// write to hdfs
df11.coalesce(1).write.option("header", "true").csv("/user/evc252/shared_data/spark_output/clean_weather");

System.exit(0);
