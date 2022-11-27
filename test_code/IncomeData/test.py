import pandas as pd

df = pd.read_csv("raw_geo.csv")
print(df["city"])
df1 = pd.read_csv("clean_income.csv")
print(df1["city"])