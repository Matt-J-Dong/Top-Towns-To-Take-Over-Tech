# delete old jar and class files
rm CountRecs.jar
rm CountRecs.class
rm CountRecsMapper.class
rm CountRecsReducer.class
#
# delete old data from hdfs
hdfs dfs -rm -r -f /user/evc252/shared_data/raw/recs_income.csv
hdfs dfs -rm -r /user/evc252/shared_data/spark_output/recs_income
#
# delete old data from local
rm ~/Top-Towns-To-Take-Over-Tech/data/raw/recs_income.csv
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
hadoop jar CountRecs.jar CountRecs /user/evc252/shared_data/raw/raw_income.csv /user/evc252/shared_data/spark_output/recs_income
# hdfs dfs -cat output/part-r-00000
#
# move to final destination in shared dir - add headers
hdfs dfs -mv /user/evc252/shared_data/spark_output/recs_income/part-r-00000 /user/evc252/shared_data/raw/recs_income.csv
#
# make sure temp output directory is deleted by same user
hdfs dfs -rm -r /user/evc252/shared_data/spark_output/recs_income
#
# copy to local
hdfs dfs -copyToLocal /user/evc252/shared_data/raw/recs_income.csv ~/Top-Towns-To-Take-Over-Tech/data/raw
hdfs dfs -cat /user/evc252/shared_data/raw/recs_income.csv