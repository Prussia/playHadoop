# playHadoop

## Get Started
### [Setting up a Single Node Cluster](http://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-common/SingleCluster.html)

### [Hadoop Image for Docker](https://hub.docker.com/r/sequenceiq/hadoop-docker/)
```
docker run -d --name=hadoopserver --net=host -p 8030:8030 -p 8040:8040 -p 8042:8042 -p 8088:8088 -p 19888:19888 -p 49707:49707 -p 50010:50010 -p 50020:50020 -p 50070:50070 -p 50075:50075 -p 50090:50090 -p 9000:9000 sequenceiq/hadoop-docker:latest /etc/bootstrap.sh -d
```

### [Docker Container Executor](https://hadoop.apache.org/docs/r2.7.2/hadoop-yarn/hadoop-yarn-site/DockerContainerExecutor.html)
