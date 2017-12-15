package com.prussia.play.hadoop.test;

import java.net.URL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Parquet2JsonTests {

	public static void main(String[] args) throws Exception {

		System.setProperty("hadoop.home.dir", "/");
		System.setProperty("HADOOP_USER_NAME", "hdfs");
		URL url = Parquet2JsonTests.class.getClassLoader().getResource("new.snappy.parquet");
		ParquetMetadata metadata = ParquetFileReader.readFooter(new Configuration(), new Path(url.getPath()));
		
		log.debug(metadata.toPrettyJSON(metadata));
		 
		 
	}

}
