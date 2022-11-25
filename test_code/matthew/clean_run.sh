rm Clean.jar
rm Clean.class
rm CleanMapper.class
rm CleanReducer.class
hdfs dfs -rm -r -f output
java -version
yarn classpath
javac -classpath `yarn classpath` -d . CleanMapper.java
javac -classpath `yarn classpath` -d . CleanReducer.java
javac -classpath `yarn classpath`:. -d . Clean.java
jar -cvf Clean.jar *.class
hdfs dfs -put city-income.csv
hadoop jar Clean.jar Clean city-income.csv output
hdfs dfs -cat output/part-r-00000