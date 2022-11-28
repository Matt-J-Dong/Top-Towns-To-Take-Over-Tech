# ****************************************************
#   -- Run this script to build and run analytic --
# ****************************************************
#
#
# give execution privileges to everybody for shell scripts
chmod +x run.sh
chmod +x ./**/*.sh
#
#
# data ingest
echo "data ingest"
# *** ONLY NEEDS TO BE RUN ONCE ***
# ./data_ingest/set_permissions.sh
# ./data_ingest/upload_raw_data_to_hdfs.sh
#
#
# etl
echo ""
echo "******************** ETL ********************"
echo ""
chmod +x ./etl_code/etl.sh
./etl_code/etl.sh
#
#
# analysis
echo ""
echo "******************** ANALYSIS ********************"
echo ""
chmod +x ./ana_code/ana.sh
./ana_code/ana.sh