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
# profiling
echo ""
echo "******************** PROFILING ********************"
echo ""
./profiling_code/profiling.sh
#
# etl
echo ""
echo "******************** ETL ********************"
echo ""
./etl_code/etl.sh
#
#
# analysis
echo ""
echo "******************** ANALYSIS ********************"
echo ""
./ana_code/ana.sh