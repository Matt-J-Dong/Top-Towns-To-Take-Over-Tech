# ********************************************************
#   -- Analysis: Assigning a score 0-100 to each city --
# ********************************************************
# delete directory at initial output location (might not have permission)
hdfs dfs -rm -r /user/evc252/shared_data/spark_output/scored
# delete directory from final location
hdfs dfs -rm /user/evc252/shared_data/scored/final.csv
# delete directory from local
rm ~/Top-Towns-To-Take-Over-Tech/data/scored/final.csv
# run spark program
spark-shell --deploy-mode client -i ana_code/ericsson_zhiquan/average_score.scala
# move output to shared data directory
hdfs dfs -mv /user/evc252/shared_data/spark_output/scored/*.csv /user/evc252/shared_data/scored/final.csv
# delete directory at initial output location
hdfs dfs -rm -r /user/evc252/shared_data/spark_output/scored
# copy data to local
hdfs dfs -copyToLocal /user/evc252/shared_data/scored/final.csv ~/Top-Towns-To-Take-Over-Tech/data/scored