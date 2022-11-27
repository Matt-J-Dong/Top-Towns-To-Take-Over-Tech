# -- Run this script to build and run analytic --

# data ingest
echo "data ingest"
./data_ingest/set_permissions.sh
./data_ingest/upload_raw_data_to_hdfs.sh

# etl
echo "etl"
./etl_code/etl.sh

# analysis
echo "analysis"
./ana_code/ana.sh
