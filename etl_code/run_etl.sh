# combined shell script for running all etl

./ericsson/clean_rent.sh
./zhiquan/process_weather.sh
# -------
# -------
./ericsson/join.sh
