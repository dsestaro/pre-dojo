package com.br.amil.utils;

import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
import com.br.amil.predojo.utils.FileUtils;
import com.google.common.io.CharStreams;
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
	public void spliValidatorTest() {
		String line = "23/04/2013 15:34:22 - New match 11348965 has started";
		
		String[] parsedLine = line.split(FileConstants.TIME_SEPARATOR);
		
		Assert.assertTrue(FileUtils.validateSplit(parsedLine));
	}
	
	@Test
	public void splitLines() throws IOException {
		InputStream inputStream = this.getClass().getResourceAsStream("/game.txt");
		
		String file = CharStreams.toString(new InputStreamReader(inputStream));
		
		String[] linesExpected = file.split("\r\n"); 
		
		String[] acutalLines = FileUtils.splitLines(file);
		
		Assert.assertThat(acutalLines, is(linesExpected));
	}
}
