# delete directory at initial output location
hdfs dfs -rm -r final_project/clean_rent_output
# delete directory from shared data directory
hdfs dfs -rm shared_data/clean/clean_rent.csv
# delete directory from local
rm ~/Top-Towns-To-Take-Over-Tech/data/clean/clean_rent.csv

# run spark program
spark-shell --deploy-mode client -i clean_rent.scala
# move output to shared data directory
hdfs dfs -mv final_project/clean_rent_output/*.csv shared_data/clean/clean_rent.csv

# copy data to local
hdfs dfs -copyToLocal shared_data/clean/clean_rent.csv ~/Top-Towns-To-Take-Over-Tech/data/clean