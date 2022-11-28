//PROFILING THE DATA
import scala.util.matching.Regex
import scala.util.Try
import org.apache.spark.sql.functions._


println("Profiling the raw data")

val df = spark.read.format("csv")
.option("inferSchema", true)
.option("header", "true")
.load("/user/evc252/shared_data/raw/raw_weather.csv");

// Filter on column:
// Reference:https://stackoverflow.com/questions/54607919/how-does-spark-interprets-type-of-a-column-in-reduce
println("count before filter by name", df.count())
val df0 = df.filter(col("name").rlike("[a-zA-Z ]*, [A-Z]*"))
println("count after filter by name", df0.count())


//System.exit(0);
