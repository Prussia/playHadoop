package com.prussia.play.hadoop.test;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.Footer;
import org.apache.parquet.hadoop.ParquetFileReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Parquet2JsonTests {

	public static void main(String[] args) throws Exception {

		System.setProperty("hadoop.home.dir", "/");
		System.setProperty("HADOOP_USER_NAME", "hdfs");

		URL fileURL = Parquet2JsonTests.class.getClassLoader().getResource("prussia_order.parquet");
		Configuration conf = new Configuration();
		// FileSystem fs = FileSystem.get(conf);
		try (LocalFileSystem localFileSystem = FileSystem.getLocal(conf);) {

			Path filePath = new Path(fileURL.getPath());
			FileStatus[] fileStatus = localFileSystem.listStatus(filePath);
			
			List<FileStatus> fileStatusList = getFileStatusList(fileStatus);

			long count = getRowCount4Parquet(conf, fileStatusList);
			log.warn("total count = {}", count);
		}
	}

	private static List<FileStatus> getFileStatusList(FileStatus[] fileStatus) {
		return Arrays.stream(fileStatus)
				.filter(f -> "parquet".equals(FilenameUtils.getExtension(f.getPath().getName()))).collect(toList());
	}

	private static long getRowCount4Parquet(Configuration conf, List<FileStatus> fileStatusList) throws IOException {
		List<Footer> footers = ParquetFileReader.readAllFootersInParallel(conf, fileStatusList, false);
//			footers.stream().forEach(
//					e -> log.debug("ParquetMetadata = {}", ParquetMetadata.toPrettyJSON(e.getParquetMetadata())));
		long count = footers.stream()
				.mapToLong(e -> e.getParquetMetadata().getBlocks().stream().mapToLong(t -> t.getRowCount()).sum())
				.sum();
		return count;
	}

}
