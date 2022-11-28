# etl_code:
1. ericsson:
2. matthew:
    - The path for the put command in the shell files may not be the same if the code is moved from its original location.
    - The hive data is pulled from Matthew's HDFS directory. (user/mjd9571/hiveData) We were able to have hive pull from Ericsson's directory due to permission issues, and our efforts to solve this issue were not able to. The hive table creation script is not run with the rest of the scripts in the main run script, this will need to be run separately after logging into beeline/hive.
3. zhiquan: