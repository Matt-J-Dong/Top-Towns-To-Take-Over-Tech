# delete directory at initial output location (possible permission denial)
hdfs dfs -rm -r /user/evc252/shared_data/spark_output/clean_weather

# delete directory from shared data directory
hdfs dfs -rm /user/evc252/shared_data/clean/clean_weather.csv
# delete directory from local
rm ~/Top-Towns-To-Take-Over-Tech/data/clean/clean_weather.csv

# run spark program
spark-shell --deploy-mode client -i etl_code/zhiquan/process_weather.scala
# move output to shared data directory
hdfs dfs -mv /user/evc252/shared_data/spark_output/clean_weather/*.csv /user/evc252/shared_data/clean/clean_weather.csv

# delete directory at initial output location
hdfs dfs -rm -r /user/evc252/shared_data/spark_output/clean_weather

# copy data to local
hdfs dfs -copyToLocal /user/evc252/shared_data/clean/clean_weather.csv ~/Top-Towns-To-Take-Over-Tech/data/clean