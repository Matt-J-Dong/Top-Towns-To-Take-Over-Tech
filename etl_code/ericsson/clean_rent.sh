# delete directory at initial output location
hdfs dfs -rm -r shared_data/spark_output/clean_rent
# delete directory from shared data directory
hdfs dfs -rm shared_data/clean/clean_rent.csv
# delete directory from local
rm ~/Top-Towns-To-Take-Over-Tech/data/clean/clean_rent.csv

# run spark program
spark-shell --deploy-mode client -i etl_code/ericsson/clean_rent.scala
# move output to shared data directory
hdfs dfs -mv shared_data/spark_output/clean_rent/*.csv shared_data/clean/clean_rent.csv

# copy data to local
hdfs dfs -copyToLocal shared_data/clean/clean_rent.csv ~/Top-Towns-To-Take-Over-Tech/data/clean