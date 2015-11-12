package com.br.amil.utils;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.br.amil.predojo.configs.FileConstants;
import com.br.amil.predojo.start.PredojoApplication;
import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PredojoApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class FileUtilsTest {
	
	@Value("${local.server.port}")
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
	}
	
	@Test
	public void parseValidatorTest() {
		String line = "23/04/2013 15:34:22 - New match 11348965 has started";
		
		String[] parsedLine = line.split(FileConstants.TIME_SEPARATOR);
		
		Assert.assertTrue(FileUtils.validateParse(parsedLine));
	}
}
