# Top-Towns-To-Take-Over-Tech
Which American cities are the best for tech jobs?
![Our diagram showcasing our pipelines and processes](diagram.png)
**Jira board link**: <https://big-data-project.atlassian.net/jira/software/projects/BDP/boards/1>

---

## Subdirectories: *(Itemized description of each subdirectory)*
1. ana_code:
    - code used to average rent and calculate the score of each specific city.
2. data:
    - clean: Contains all of the cleaned datasets just after being cleaned. No other processing has been done yet.
    - joined: Contains our fully joined data, which is all of our datasets merged together into one file.
    - raw: Contains fully raw data, which we have pulled from multiple sources.
    - scored: *We don't have anything here yet oops*
3. data_ingest: Contains the shell scripts we used to put data into HDFS and provide permissions for the group to put data into HDFS in Ericsson's directory.
4. etl_code: Our code for cleaning all of our data (income/weather/rent) data, and the shell scripts to go along with them. Also included are the commands used to create our hive tables.
    - ericsson: Contains the code used to clean the rent data and merge the different datasets together into our final joined dataset.
    - matthew: Contains the code used to clean the geographic data, count the number of records in both the income and geographic datasets, and the commands used to input our datasets into Hive.
    - zhiquan: Contains the code used to clean and process the weather data into a fully cleaned state.
5. profiling_code: Our code for averaging the data of multiple years together, *as well as the code used to provide scores to cities?*.
    - ericsson: Contains the code used to average out the values for rent costs and number of good days across multiple years within the data.
    - matthew: *I'll probably throw in a couple hive queries or something*
    - zhiquan: *This would be the code that contains the code used to give final scores to each city based on our specific criteria of ratio of income to rent, and the number of good days that city has?*.
6. screenshots: Contains all the screenshots needed to showcase our analytic in the process of running.
7. test_code: Contains old code that is not used in the final version of the project, sorted by person. Each subfolder contains old code,shell scripts, and data.
---
### Running the code:
- The necessary building for all of the code is included within the shell scripts used to run the code.
- We have made running the code very simple. All you need to do is run run.sh located in the main directory. The exception is the creation of the hive warehouse, which needs to be manually copied into the terminal from the hive_input.txt file in etl_code.
- All of the input data can be found within the data subdirectory.
