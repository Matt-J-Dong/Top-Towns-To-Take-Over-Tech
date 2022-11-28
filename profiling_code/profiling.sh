spark-shell --deploy-mode client -i profiling_code/matthew/profiling_income.scala
spark-shell --deploy-mode client -i profiling_code/ericsson/profiling_rent.scala
spark-shell --deploy-mode client -i profiling_code/zhiquan/profiling_weather.scala

# NOTE: have to run matthew's MR program from profiling_code/matthew directory
cd profiling_code/matthew
chmod +x countrecs_geo.sh
chmod +x countrecs_income.sh

./countrecs_geo.sh
./countrecs_income.sh
#
# move back to parent directory
cd ../..

