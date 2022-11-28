# delete old jar and class files
rm CountRecs.jar
rm CountRecs.class
rm CountRecsMapper.class
rm CountRecsReducer.class
#
# delete old data from hdfs
hdfs dfs -rm -r -f /user/evc252/shared_data/raw/recs_geo.csv
hdfs dfs -rm -r /user/evc252/shared_data/spark_output/recs_geo
#
# delete old data from local
rm ~/Top-Towns-To-Take-Over-Tech/data/raw/recs_geo.csv
#
# java -version
# yarn classpath
#
# build
javac -classpath `yarn classpath` -d . CountRecsMapper.java
javac -classpath `yarn classpath` -d . CountRecsReducer.java
javac -classpath `yarn classpath`:. -d . CountRecs.java
jar -cvf CountRecs.jar *.class
#
# run
hadoop jar CountRecs.jar CountRecs /user/evc252/shared_data/raw/raw_geo.csv /user/evc252/shared_data/spark_output/recs_geo
# hdfs dfs -cat output/part-r-00000
#
# move to final destination in shared dir - add headers
hdfs dfs -mv /user/evc252/shared_data/spark_output/recs_geo/part-r-00000 /user/evc252/shared_data/raw/recs_geo.csv
#
# make sure temp output directory is deleted by same user
hdfs dfs -rm -r /user/evc252/shared_data/spark_output/recs_geo
#
# show the results for geo
echo "Geo records: "
hdfs dfs -cat /user/evc252/shared_data/raw/recs_geo.csv