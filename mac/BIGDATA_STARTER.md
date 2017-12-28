# Login Starter on Mac

- Start Automator.app
- Select "Application"
- Click "Show library" in the toolbar (if hidden)
- Add "Run shell script" (from the Actions/Utilities)
- Copy&paste your script into the window
- Test it
- Save somewhere, for example you can make an "Applications" folder in your HOME (you will get an your_name.app)
- Go to System Preferences -> Accounts -> Login items
- Add this app
- test & done ;)

```
echo "start hadoop"
export HADOOP_HOME=/usr/local/Cellar/hadoop/2.8.2
bash $HADOOP_HOME/sbin/start-all.sh

echo "start spark"
export APACHE_SPARK_HOME=/usr/local/Cellar/apache-spark/2.2.0/libexec
export SPARK_MASTER_WEBUI_PORT=8989
bash $APACHE_SPARK_HOME/sbin/start-all.sh 
```
