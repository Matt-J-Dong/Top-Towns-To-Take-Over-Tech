# delete directory at initial output location
hdfs dfs -rm -r shared_data/spark_output/clean_weather
# delete directory from shared data directory
hdfs dfs -rm shared_data/clean/clean_weather.csv
# delete directory from local
rm ~/Top-Towns-To-Take-Over-Tech/data/clean/clean_weather.csv

# run spark program
spark-shell --deploy-mode client -i process_weather.scala
# move output to shared data directory
hdfs dfs -mv shared_data/spark_output/clean_weather/*.csv shared_data/clean/clean_weather.csv

# copy data to local
hdfs dfs -copyToLocal shared_data/clean/clean_weather.csv ~/Top-Towns-To-Take-Over-Tech/data/clean_weather