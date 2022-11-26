# this script creates a new .csv by averaging the number of good days,
# averaging the monthly rent, creating new columns for each,
# and dropping the individual columns for each "# of good days" and rent statistic

# -- also takes cares of deleting/replacing files in HDFS and local file system

hdfs dfs -rm -r final_project/average_output

rm data/average_income_weather.csv

spark-shell --deploy-mode client -i processing/average.scala

hdfs dfs -copyToLocal final_project/average_output/*.csv data/average_income_weather.csv