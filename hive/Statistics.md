# Hive Table Statistics

## [Hive Table Statistics](https://www.cloudera.com/documentation/enterprise/5-9-x/topics/cm_mc_hive_table_stats.html)

### [Generating Table and Column Statistics (COMPUTE STATS Statement)](https://www.cloudera.com/documentation/enterprise/5-9-x/topics/impala_perf_stats.html#perf_stats_computing)
  To gather table statistics after loading data into a table or partition, you typically use the COMPUTE STATS statement. This statement is available in Impala 1.2.2 and higher. It gathers both table statistics and column statistics for all columns in a single operation. For large partitioned tables, where you frequently need to update statistics and it is impractical to scan the entire table each time, use the syntax COMPUTE INCREMENTAL STATS, which is available in CDH 5.3 / Impala 2.1 and higher.

### [how-to-ingest-and-query-fast-data-with-impala](https://blog.cloudera.com/blog/2015/11/how-to-ingest-and-query-fast-data-with-impala-without-kudu/)

### [How Impala Works with Hive](https://www.cloudera.com/documentation/enterprise/5-12-x/topics/impala_hadoop.html#intro_hive)
The Impala query optimizer can also make use of table statistics and column statistics. Originally, you gathered this information with the ANALYZE TABLE statement in Hive; in Impala 1.2.2 and higher, use the Impala COMPUTE STATS statement instead. COMPUTE STATS requires less setup, is more reliable and faster, and does not require switching back and forth between impala-shell and the Hive shell.

## [Statistics in Hive](https://cwiki.apache.org/confluence/display/Hive/StatsDev)
