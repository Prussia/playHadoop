# Connect to Hive

- [Beeline](https://cwiki.apache.org/confluence/display/Hive/HiveServer2+Clients)
- hive
- Command
  - [Create DB](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DDL#LanguageManualDDL-CreateDataBase)
  ```
  CREATE DATABASE IF NOT EXISTS phu_hive_db
  COMMENT 'prussia hive database'
  location 'hdfs://localhost:9000/user/phu/phu_hive_db.db/';
  
  Show databases;
  use phu_hive_db;
  ```
  - [Create table](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DDL#LanguageManualDDL-CreateTable)
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
  - [Create/Drop/Alter Index](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DDL#LanguageManualDDL-Create/Drop/AlterIndex)
  ```
  CREATE INDEX index_name
  ON TABLE tb_test (id)
  AS index_type
  [WITH DEFERRED REBUILD]
  [IDXPROPERTIES (property_name=property_value, ...)]
  [IN TABLE index_table_name]
  [
     [ ROW FORMAT ...] STORED AS ...
     | STORED BY ...
  ]
  [LOCATION hdfs_path]
  [TBLPROPERTIES (...)]
  [COMMENT "index comment"];
  ```
