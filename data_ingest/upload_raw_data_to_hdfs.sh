# *******************************************
#   -- Uploads each raw dataset to HDFS --
# *******************************************

# first, delete old files
hdfs dfs -rm data/raw/raw_rent.csv /user/evc252/shared_data/raw
hdfs dfs -rm data/raw/raw_weather.csv /user/evc252/shared_data/raw
hdfs dfs -rm data/raw/raw_income.csv /user/evc252/shared_data/raw
hdfs dfs -rm data/raw/raw_geo.csv /user/evc252/shared_data/raw 

# upload new files
hdfs dfs -put data/raw/raw_rent.csv /user/evc252/shared_data/raw
hdfs dfs -put data/raw/raw_weather.csv /user/evc252/shared_data/raw
hdfs dfs -put data/raw/raw_income.csv /user/evc252/shared_data/raw
hdfs dfs -put data/raw/raw_income.csv /user/evc252/shared_data/raw
