# In Practice 

```
docker pull sequenceiq/hadoop-docker:2.7.0

docker run --name hadoop_master -it -d -p 8088:8088 -p 8042:8042 -p 4040:4040 -p 50070:50070 sequenceiq/hadoop-docker:2.7.0 /etc/bootstrap.sh -bash
```

 	Daemon	Default Port	Configuration Parameter
HDFS	Namenode	50070	dfs.http.address
Datanodes	50075	dfs.datanode.http.address
Secondarynamenode	50090	dfs.secondary.http.address
Backup/Checkpoint node?	50105	dfs.backup.http.address
MR	Jobracker	50030	mapred.job.tracker.http.address
Tasktrackers	50060	mapred.task.tracker.http.address
