package com.br.amil.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.br.amil.predojo.service.FileUploadService;
import com.br.amil.predojo.start.PredojoApplication;
import com.google.common.io.CharStreams;
import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PredojoApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class FileUploadServiceTest {
	
	@Value("${local.server.port}")
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
	}
	
	@Test
	public void processFileTest() throws IOException {
		InputStream inputStream = this.getClass().getResourceAsStream("/game.txt");
		
		String file = CharStreams.toString(new InputStreamReader(inputStream));
		
		FileUploadService fileUploadService = new FileUploadService();
		
		fileUploadService.processFile(file);
	}
}
