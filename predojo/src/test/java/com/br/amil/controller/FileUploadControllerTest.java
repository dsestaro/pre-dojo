package com.br.amil.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.br.amil.PredojoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PredojoApplication.class)
@WebAppConfiguration
public class FileUploadControllerTest {

	@Test
	public void uploadFile() {
		File testFile = Mockito.mock(File.class);

		given().
		         multiPart(testFile).
		expect().
		         body("fileUploadResult", is("OK")).
		when().
		         post("/fileUpload");
	}
}

