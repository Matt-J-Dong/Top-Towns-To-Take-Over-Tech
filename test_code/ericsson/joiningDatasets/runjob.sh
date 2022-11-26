hdfs dfs -rm -r final_project/merge_geo_output
rm -rf data/merge_geo_output

spark-shell --deploy-mode client -i processing/join_geo.scala

# hdfs dfs -copyToLocal final_project/merge_geo_output data