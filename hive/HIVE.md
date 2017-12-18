# Hive Installation Notes
- http://www.cnblogs.com/ToDoToTry/p/5349753.html
- https://dzone.com/articles/how-configure-mysql-metastore
- http://www.jianshu.com/p/776802db315e

## 创建mysql metastore

mysql> CREATE DATABASE metastore;
mysql> USE metastore;
mysql> CREATE USER 'hiveuser'@'localhost' IDENTIFIED BY 'password';
mysql> GRANT SELECT,INSERT,UPDATE,DELETE,ALTER,CREATE ON metastore.* TO 'hiveuser'@'localhost';

## Notes on hive-site.xml

```
  cp hive-default.xml.template hive-site.xml
```
#添加或者编辑如下内容
```
  <property>
    <name>system:java.io.tmpdir</name>
    <value>/tmp/hive/java</value>
  </property>
  <property>
    <name>system:user.name</name>
    <value>${user.name}</value>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionURL</name>
    <value>jdbc:mysql://localhost/metastore</value>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionDriverName</name>
    <value>com.mysql.jdbc.Driver</value>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionUserName</name>
    <value>hiveuser</value>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionPassword</name>
    <value>password</value>
  </property>
  <property>
    <name>datanucleus.fixedDatastore</name>
    <value>false</value>
  </property>

```
