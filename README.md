# Top-Towns-To-Take-Over-Tech
Which American cities are the best for tech jobs?

Description of folders:

* IncomeData
    - MapReduce program for processing income data + income datasets (raw and clean)
    - submitted by Matthew

* weatherData
    - data related to weather
    - submitted by Zhiquan

* weatherProcessing
    - MapReduce program for processing weather data
    - submitted by Zhiquan

* weatherOutput
    - MapReduce output for processing weather data
    - submitted by Zhiquan

* rentData
    - raw and processed rental data from Zillow
    - Spark programs for processing that data
    - submitted by Ericsson

* joiningDataSets
    - Spark program for joining income and rental data
    - input + output datasets
    - submitted by Ericsson

---
* Current Status:
We have all of our datasets, and they are processed and merged together. We are now working on setting up our formulas for calculating the score of each city, and showing those scores on a geographic heatmap on Tableau. We also plan to have Hive tables for our data as well.
Matthew added a new dataset for latitude and longitude of the cities in our dataset, and used MapReduce to clean and process the data to be able to be easily merged with the rest of the data.
