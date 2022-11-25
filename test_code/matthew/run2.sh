java -version
yarn classpath
javac -classpath `yarn classpath` -d . CountRecsMapper.java
javac -classpath `yarn classpath` -d . CountRecsReducer.java
javac -classpath `yarn classpath`:. -d . CountRecs.java
jar -cvf CountRecs.jar *.class
hdfs dfs -put cleanedData.csv
hadoop jar CountRecs.jar CountRecs cleanedData.csv output
hdfs dfs -cat output/part-r-00000
