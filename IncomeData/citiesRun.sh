java -version
yarn classpath
javac -classpath `yarn classpath` -d . CitiesMapper.java
javac -classpath `yarn classpath` -d . CitiesReducer.java
javac -classpath `yarn classpath`:. -d . Cities.java
jar -cvf Cities.jar *.class
hdfs dfs -put city-income.csv
hadoop jar Cities.jar Cities uscities.csv output
hdfs dfs -cat output/part-r-00000