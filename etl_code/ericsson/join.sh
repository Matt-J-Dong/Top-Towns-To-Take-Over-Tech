# delete directory at initial output location (might not have permission)
hdfs dfs -rm -r /user/evc252/shared_data/spark_output/joined
# delete directory from shared data directory
hdfs dfs -rm /user/evc252/shared_data/joined/rent_income_weather_geo.csv
# delete directory from local
rm ~/Top-Towns-To-Take-Over-Tech/data/joined/rent_income_weather_geo.csv
#
# run spark program
spark-shell --deploy-mode client -i etl_code/ericsson/join.scala
#
# move output to shared data directory
hdfs dfs -mv /user/evc252/shared_data/spark_output/joined/*.csv /user/evc252/shared_data/joined/rent_income_weather_geo.csv
# delete directory at initial output location
hdfs dfs -rm -r /user/evc252/shared_data/spark_output/joined
#
# copy data to local
hdfs dfs -copyToLocal /user/evc252/shared_data/joined/rent_income_weather_geo.csv ~/Top-Towns-To-Take-Over-Tech/data/joined