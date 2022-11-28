# **********************************************
#   -- ETL: Cleaning + Joining each dataset --
# **********************************************
#
# cleaning scripts
./etl_code/ericsson/clean_rent.sh
./etl_code/zhiquan/process_weather.sh
#
# NOTE: have to run matthew's MR program from etl_code/matthew directory
cd etl_code/matthew
./clean_geo.sh
#
# move back to parent directory
cd ../..
#
# joining script
./etl_code/ericsson/join.sh