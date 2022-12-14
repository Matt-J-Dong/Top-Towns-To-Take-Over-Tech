# *******************************************
#   -- Uploads each raw dataset to HDFS --
# *******************************************
#
# first, delete old files
hdfs dfs -rm /user/evc252/shared_data/raw/raw_rent.csv
hdfs dfs -rm /user/evc252/shared_data/raw/raw_weather.csv
hdfs dfs -rm /user/evc252/shared_data/raw/raw_income.csv
hdfs dfs -rm /user/evc252/shared_data/raw/raw_geo.csv 
#
# upload new files
hdfs dfs -put data/raw/raw_rent.csv /user/evc252/shared_data/raw
hdfs dfs -put data/raw/raw_weather.csv /user/evc252/shared_data/raw
hdfs dfs -put data/raw/raw_income.csv /user/evc252/shared_data/raw
hdfs dfs -put data/raw/raw_geo.csv /user/evc252/shared_data/raw