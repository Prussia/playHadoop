# Reference

### Mac安装Hadoop2.8.0
https://segmentfault.com/a/1190000009130234

### 免密码启动Hadoop
https://www.cnblogs.com/dagger14/p/6599194.html
   
   Press enter for each line 提示输入直接按回车就好
   
   id_rsa = {file_name}
```
$ ssh-keygen -t rsa
$ cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
$ chmod og-wx ~/.ssh/authorized_keys 
```

  测试 ssh localhost如果仍然提示要输入密码，那么可以vim ~/.ssh/config文件，添加以下代码。
  
  ```
   Host localhost
   AddKeysToAgent yes
   UseKeychain yes
   IdentityFile ~/.ssh/id_rsa
  ```

### hadoop无法访问50070端口

http://blog.csdn.net/Neone__u/article/details/53741786
hdfs-site.xml 添加如下
```
<property>
  <name>dfs.http.address</name>
  <value>0.0.0.0:50070</value>
</property>
```
```
sbin/stop-hdfs.sh
sbin/stop-yarn.sh

bin/hdfs namenode -format

sbin/start-hdfs.sh
sbin/start-yarn.sh

```



