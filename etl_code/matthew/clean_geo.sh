rm CleanGeo.jar
rm CleanGeo.class
rm CleanGeoMapper.class
rm CleanGeoReducer.class
hdfs dfs -rm -r -f output
java -version
yarn classpath
javac -classpath `yarn classpath` -d . CleanGeoMapper.java
javac -classpath `yarn classpath` -d . CleanGeoReducer.java
javac -classpath `yarn classpath`:. -d . CleanGeo.java
jar -cvf CleanGeo.jar *.class
hdfs dfs -put raw_geo.csv
hadoop jar CleanGeo.jar CleanGeo raw_geo.csv output
hdfs dfs -cat output/part-r-00000
hdfs dfs -mv output/part-r-00000 output/clean_geo.csv