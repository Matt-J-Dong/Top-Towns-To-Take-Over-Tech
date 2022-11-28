//PROFILING THE DATA
import scala.util.matching.Regex
import scala.util.Try
import org.apache.spark.sql.functions._

val df = spark.read.format("csv")
.option("inferSchema", true)
.option("header", "true")
.load("/user/evc252/shared_data/raw/raw_weather.csv");


// val result = Try(Option(df.filter($"name" rlike "[a-zA-Z ]*, [A-Z]*").first)).toOption.flatten
// if (result.isEmpty) { println("Empty")} else {print(result)}
println("test: ")

// Reduce on column:
// Reference:https://stackoverflow.com/questions/54607919/how-does-spark-interprets-type-of-a-column-in-reduce
val result = df.select(col("name").as[String]).reduce((pre: String, curr: String)=>{
    if(curr.matches("[a-zA-Z ]*, [A-Z]*") && pre == "all matched"){
        "all matched"
    }else{
        "not matched"
    } 
})

print(result)

// println("\n\n******* Name is correct (first 2 records) ***********")
// df.show(2) 
// println("************************************************\n");

System.exit(0);
