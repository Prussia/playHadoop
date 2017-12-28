# Connect to Hive

- [Beeline](https://cwiki.apache.org/confluence/display/Hive/HiveServer2+Clients)
- hive
- Command
  - Create DB
  ```
  CREATE DATABASE IF NOT EXISTS phu_hive_db
  COMMENT 'prussia hive database'
  location 'hdfs://localhost:9000/user/phu/phu_hive_db.db/';
  
  Show databases;
  use phu_hive_db;
  ```
  - Create table
  ```
  CREATE EXTERNAL TABLE tb_test (
  id INT,
  ip STRING COMMENT 'visitor IP',
  avg_view_depth DECIMAL(5,1),
  bounce_rate DECIMAL(6,5)
  ) COMMENT 'test external table'
  PARTITIONED BY (day STRING)
  ROW FORMAT DELIMITED
  FIELDS TERMINATED BY ','
  STORED AS textfile
  LOCATION 'hdfs://localhost:9000/tmp/test/';
  
  ```

