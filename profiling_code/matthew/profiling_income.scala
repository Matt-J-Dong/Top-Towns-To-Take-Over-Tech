//PROFILING THE DATA
import scala.util.matching.Regex
import scala.util.Try
import org.apache.spark.sql.functions._


println()
println("PROFILING THE RAW INCOME DATA")
println("*******************************************************************************")

val df = spark.read.format("csv")
.option("inferSchema", true)
.option("header", "true")
.load("/user/evc252/shared_data/raw/raw_income.csv");


// Filter on column:
// Reference:https://stackoverflow.com/questions/54607919/how-does-spark-interprets-type-of-a-column-in-reduce
println("count before filter by city name", df.count())
val df0 = df.filter(col("city").rlike("[a-zA-Z ]*, [A-Z]*"))
println("count after filter by city name", df0.count())


System.exit(0);
