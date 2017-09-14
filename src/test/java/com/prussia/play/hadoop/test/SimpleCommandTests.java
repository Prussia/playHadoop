package com.prussia.play.hadoop.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.apache.hadoop.yarn.api.records.YarnApplicationState;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.yarn.test.context.MiniYarnCluster;
import org.springframework.yarn.test.context.YarnDelegatingSmartContextLoader;
import org.springframework.yarn.test.junit.AbstractYarnClusterTests;
import org.springframework.yarn.test.junit.ApplicationInfo;
import org.springframework.yarn.test.support.ContainerLogUtils;

@ContextConfiguration(loader=YarnDelegatingSmartContextLoader.class)
@MiniYarnCluster
public class SimpleCommandTests extends AbstractYarnClusterTests {

	@Test
	@Timed(millis=70000)
	public void testAppSubmission() throws Exception {
		ApplicationInfo info = submitApplicationAndWait();
		assertThat(info, notNullValue());
		assertThat(info.getYarnApplicationState(), notNullValue());
		assertThat(info.getApplicationId(), notNullValue());
		assertThat(info.getYarnApplicationState(), is(YarnApplicationState.FINISHED));

		List<Resource> resources = ContainerLogUtils.queryContainerLogs(getYarnCluster(), info.getApplicationId());

		// appmaster and 4 containers should
		// make it 10 log files
		assertThat(resources, notNullValue());
		assertThat(resources.size(), is(10));

		for (Resource res : resources) {
			File file = res.getFile();
			if (file.getName().endsWith("stdout")) {
				// there has to be some content in stdout file
				assertThat(file.length(), greaterThan(0l));
			} else if (file.getName().endsWith("stderr")) {
				String content = "";
				if (file.length() > 0) {
					Scanner scanner = new Scanner(file);
					content = scanner.useDelimiter("\\A").next();
					scanner.close();
				}
				// can't have anything in stderr files
				assertThat("stderr file is not empty: " + content, file.length(), is(0l));
			}
		}
	}

}
