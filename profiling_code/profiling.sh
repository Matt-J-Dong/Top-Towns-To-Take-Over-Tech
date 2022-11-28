spark-shell --deploy-mode client -i matthew/profiling_income.scala
spark-shell --deploy-mode client -i ericsson/profiling_rent.scala
spark-shell --deploy-mode client -i zhiquan/profiling_weather.scala

# NOTE: have to run matthew's MR program from etl_code/matthew directory
cd profiling_code/matthew
./countrecs_geo.sh
#
# move back to parent directory
cd ../..

