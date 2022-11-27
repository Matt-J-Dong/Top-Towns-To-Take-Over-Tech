rm CountRecs.jar
rm CountRecs.class
rm CountRecsMapper.class
rm CountRecsReducer.class
hdfs dfs -rm -r -f output
java -version
yarn classpath
javac -classpath `yarn classpath` -d . CountRecsMapper.java
javac -classpath `yarn classpath` -d . CountRecsReducer.java
javac -classpath `yarn classpath`:. -d . CountRecs.java
jar -cvf CountRecs.jar *.class
hdfs dfs -put ../../data/clean/clean_geo.csv
hadoop jar CountRecs.jar CountRecs clean_geo.csv output
hdfs dfs -cat output/part-r-00000