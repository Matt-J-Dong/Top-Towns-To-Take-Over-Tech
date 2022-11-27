# delete directory at initial output location
hdfs dfs -rm -r shared_data/spark_output/score
# delete directory from final location
hdfs dfs -rm shared_data/scored/final.csv
# delete directory from local
~/Top-Towns-To-Take-Over-Tech/data/scored/final.csv

# run spark program
spark-shell --deploy-mode client -i process_weather.scala
# move output to shared data directory
hdfs dfs -mv shared_data/spark_output/score/*.csv shared_data/scored/final.csv

# copy data to local
hdfs dfs -copyToLocal shared_data/joined/final.csv ~/Top-Towns-To-Take-Over-Tech/data/scored
