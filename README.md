# DataframeSparkStreaming
This application is an easy implementation of Apache Spark Streaming for dataframes 

This app is launching a spark streaming that will read csv or parquet files inside a local repository, apply a processing function and display the result in the console.

## How to run 
- Clone the repo of the project 
- Launch sbt console and run assembly 
- With the schema json template, create the schema.json file associated with the schema of your dataframe
- Set the different path in config.yml file
- spark-submit the package.jar with config.yml file as parameter 
