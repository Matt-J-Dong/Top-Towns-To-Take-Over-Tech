# **********************************************
#   -- ETL: Cleaning + Joining each dataset --
# **********************************************
#
# cleaning scripts
chmod +x ./etl_code/ericsson/clean_rent.sh
chmod +x ./etl_code/zhiquan/process_weather.sh
./etl_code/ericsson/clean_rent.sh
./etl_code/zhiquan/process_weather.sh
#
# NOTE: have to run matthew's MR program from etl_code/matthew directory
cd etl_code/matthew
chmod +x ./clean_geo.sh
./clean_geo.sh
#
# move back to parent directory
cd ../..
#
# joining script
chmod +x ./etl_code/ericsson/join.sh
./etl_code/ericsson/join.sh