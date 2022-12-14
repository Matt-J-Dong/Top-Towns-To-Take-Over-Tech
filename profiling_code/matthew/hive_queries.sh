use mjd9571;
show tables;
select * from allData;
#West coast cities
select * from allData where lng < -100;
#Central cities
select * from allData where lng between -100 and -80 and lat between 30 and 40;
#Sort by income descending
select * from allData order by income desc;
#Sort by the ratio of income divided by rent descending, to see which city has the best income to rent ratio
select * from allData order by income/rent desc;