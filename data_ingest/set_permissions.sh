# **********************************************************
# -- Sets default permissions recursively for all users --
#    that need access to shared_data directory in hdfs
# **********************************************************
# also gives executable access to parent directory
# so shared_data, a subdirectory, can be accessed
#
# group members
hdfs dfs -setfacl -m -R default:user:zc1548:rwx /user/evc252/shared_data
hdfs dfs -setfacl -m -R default:user:mjd9571:rwx /user/evc252/shared_data
hdfs dfs -setfacl -m -R default:user:evc252:rwx /user/evc252/shared_data
hdfs dfs -setfacl -m user:zc1548:--x /user/evc252
hdfs dfs -setfacl -m user:mjd9571:--x /user/evc252
hdfs dfs -setfacl -m user:evc252:--x /user/evc252
#
# graders
hdfs dfs -setfacl -m -R default:user:sj3549:rwx /user/evc252/shared_data
hdfs dfs -setfacl -m user:sj3549:--x /user/evc252
#
# professor
hdfs dfs -setfacl -m -R default:user:adm209:rwx /user/evc252/shared_data
hdfs dfs -setfacl -m user:adm209:--x /user/evc252