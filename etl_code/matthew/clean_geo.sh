# delete old jar and class files
rm CleanGeo.jar
rm CleanGeo.class
rm CleanGeoMapper.class
rm CleanGeoReducer.class

# delete old data from hdfs
hdfs dfs -rm -r -f ../evc252/shared_data/clean/clean_geo.csv
hdfs dfs -rm -r ../evc252/shared_data/spark_output/clean_geo

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
hadoop jar CleanGeo.jar CleanGeo ../evc252/shared_data/raw/raw_geo.csv ../evc252/shared_data/spark_output/clean_geo

# hdfs dfs -cat output/part-r-00000
# move to final destination in shared dir

# TODO: DOUBLE CHECK THIS
hdfs dfs -mv ../evc252/shared_data/spark_output/clean_geo/part-r-00000 ../evc252/shared_data/clean/clean_geo.csv

# copy to local
hdfs dfs -copyToLocal ../evc252/shared_data/clean/clean_geo.csv ~/Top-Towns-To-Take-Over-Tech/data/clean