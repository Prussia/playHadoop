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
