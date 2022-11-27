# delete old jar and class files
rm CleanGeo.jar
rm CleanGeo.class
rm CleanGeoMapper.class
rm CleanGeoReducer.class

# delete old data from hdfs
hdfs dfs -rm -r -f /user/evc252/shared_data/clean/clean_geo.csv
hdfs dfs -rm -r /user/evc252/shared_data/spark_output/clean_geo

# delete old data from local
rm ~/Top-Towns-To-Take-Over-Tech/data/clean/clean_geo.csv

# java -version
# yarn classpath

# build
javac -classpath `yarn classpath` -d . CleanGeoMapper.java
javac -classpath `yarn classpath` -d . CleanGeoReducer.java
javac -classpath `yarn classpath`:. -d . CleanGeo.java
jar -cvf CleanGeo.jar *.class

# run
hadoop jar CleanGeo.jar CleanGeo /user/evc252/shared_data/raw/raw_geo.csv /user/evc252/shared_data/spark_output/clean_geo
# hdfs dfs -cat output/part-r-00000


# move to final destination in shared dir - add headers
touch /user/evc252/shared_data/clean/clean_geo.csv
echo "city,state,lat,long" >> clean_geo.csv

hdfs dfs -cat /user/evc252/shared_data/spark_output/clean_geo/part-r-00000 >> /user/evc252/shared_data/clean/clean_geo.csv
# make sure temp output directory is deleted by same user
hdfs dfs -rm -r /user/evc252/shared_data/spark_output/clean_geo

# copy to local
hdfs dfs -copyToLocal /user/evc252/shared_data/clean/clean_geo.csv ~/Top-Towns-To-Take-Over-Tech/data/clean