package com.br.amil.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.br.amil.predojo.start.PredojoApplication;
import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PredojoApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class FileUploadControllerTest {

	@Value("${local.server.port}")
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
	}
	
	@Test
	public void uploadFileTest() throws IOException {
		File testFile = File.createTempFile("game", ".txt");

		given().
		        param("file", Files.readAllBytes(testFile.toPath()).toString()).
		expect().
		        body(is("OK")).
		when().
		        post("/fileUpload");
	}
	
	@Test
	public void isAliveTest() {
		given().
		expect().
		         body(is("OK")).
		when().
		         get("/isAlive");
	}
}

