hdfs dfs -rm -r project/output/result_data
spark-shell --deploy-mode client -i process_weather.scala