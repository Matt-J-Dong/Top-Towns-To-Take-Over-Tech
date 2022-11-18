javac -classpath `yarn classpath` -d . CleanMapper.java

javac -classpath `yarn classpath` -d . CleanReducer.java

javac -classpath `yarn classpath`:. -d . Clean.java

jar -cvf clean.jar *.class

hadoop jar clean.jar Clean project/data/30cities.csv project/data/filteredCities