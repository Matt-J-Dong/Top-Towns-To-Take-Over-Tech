# setting default permissions recursively
hdfs dfs -setfacl -R -m default:user:zc1548:rwx shared_data
hdfs dfs -setfacl -R -m default:user:mjd9571:rwx shared_data

# giving executable access to parent directory so subdirectories can be accessed
hdfs dfs -setfacl -m user:zc1548:--x .
hdfs dfs -setfacl -m user:mjd9571:--x .