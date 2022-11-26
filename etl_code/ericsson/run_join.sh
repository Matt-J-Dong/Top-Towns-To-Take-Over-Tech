# delete directory at initial output location
hdfs dfs -rm -r final_project/join_output
# delete directory from shared data directory
hdfs dfs -rm shared_data/joined/rent_income_weather_geo.csv
# delete directory from local
rm ~/Top-Towns-To-Take-Over-Tech/data/joined/rent_income_weather_geo.csv

# run spark program
spark-shell --deploy-mode client -i join.scala
# move output to shared data directory
hdfs dfs -mv final_project/join_output/*.csv shared_data/joined/rent_income_weather_geo.csv

# copy data to local
hdfs dfs -copyToLocal shared_data/joined/rent_income_weather_geo.csv ~/Top-Towns-To-Take-Over-Tech/data/joined