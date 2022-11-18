# Top-Towns-To-Take-Over-Tech
Which American cities are the best for tech jobs?


### Current Status:

We have all of our datasets, and they are processed and merged together. We are now working on setting up our formulas for calculating a score from 0-100 for each city, and displaying those scores on a geographic heatmap on Tableau. We also plan to have Hive tables for our data, we are just running into data type issues and working through those.

---
### Updates since last submission:

    
* Added a geographic dataset containing latitude and longitude of the cities
   * used MapReduce to clean and process the data to be able to be easily merged with the other datasets (kept the city names consistent)
   
* Began Tableau visualization
   * bar graph overview
   * screenshot included in submission
   
* Joined weather and income/rent datasets
   * only remaining step is to add geographic data in order to create heatmap on Tableau
    

    
 ---   
**Jira board link**: https://big-data-project.atlassian.net/jira/software/projects/BDP/boards/1

---
Description of folders:

* IncomeData
    - MapReduce program for processing income data + income datasets (raw and clean)
    - Also currently contains the latitude and longtitude data (raw and clean), as well as the processing MapReduce code.
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
    - Spark program for joining income, rental, and weather data
    - input + output datasets
    - submitted by Ericsson
