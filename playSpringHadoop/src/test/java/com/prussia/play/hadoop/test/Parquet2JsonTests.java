package com.prussia.play.hadoop.test;

import static java.util.stream.Collectors.toList;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.Footer;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Parquet2JsonTests {

	public static void main(String[] args) throws Exception {

		System.setProperty("hadoop.home.dir", "/");
		System.setProperty("HADOOP_USER_NAME", "hdfs");

		URL url = Parquet2JsonTests.class.getClassLoader().getResource("test.parquet");
		Configuration conf = new Configuration();
		// FileSystem fs = FileSystem.get(conf);
		LocalFileSystem localFileSystem = FileSystem.getLocal(conf);

		Path filePath = new Path(url.getPath());

		FileStatus[] fileStatus = localFileSystem.listStatus(filePath);
		List<FileStatus> fileStatusList = Arrays.stream(fileStatus).collect(toList());

		// ParquetMetadata metadata = ParquetFileReader.readFooter(new
		// Configuration(), new Path(url.getPath()));

		List<Footer> footers = ParquetFileReader.readAllFootersInParallel(conf, fileStatusList, false);
		// footers.stream().forEach(e -> log.debug(
		// "ParquetMetadata = {}",
		// ParquetMetadata.toPrettyJSON(e.getParquetMetadata()))
		// );
		long count = footers.stream()
				.mapToLong(e -> e.getParquetMetadata().getBlocks().stream().mapToLong(t -> t.getRowCount()).sum())
				.sum();
		log.warn("total count = {}", count);
	}

}
