Here is a description of each of the datasets involved in this step of the analytic:

* cities_with_state.txt
    - list of ~20 cities that we elected as possiblities for the final analytic
    - cities need to be present in all 3 datasets (income, rent, weather), thus we elected more than we necessary will end up including

* rental_cost_per_month.csv
    - raw data provided by Zillow (not scraped) that lists, for over 100 cities, the median rental cost each month from

* rental_cost.csv
    - cleaned version of rental_cost_per_month.csv
    - only includes data from the most recent 3 months

* cities_rent.csv
    - inner join of cities_with_state.txt and rental_cost.csv on city name
