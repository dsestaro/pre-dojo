package com.br.amil.matchstatisctics;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.br.amil.predojo.configs.FileConfig;
import com.br.amil.predojo.matchstatistics.FileInterpreter;
import com.br.amil.predojo.matchstatistics.dto.LineInformation;
import com.br.amil.predojo.start.PredojoApplication;
import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PredojoApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class MatchStatisticsTest {

	@Value("${local.server.port}")
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
	}
	
	@Test
	public void parseLine() throws ParseException {
		String line = "23/04/2013 15:34:22 - New match 11348965 has started";
		
		LineInformation answer = new LineInformation();
		answer.setTime(FileConfig.FORMATTER.parse("23/04/2013 15:34:22"));
		
		LineInformation response = FileInterpreter.parseLine(line);		
		
		Assert.assertEquals(answer.getMatch(), response.getMatch());
		Assert.assertEquals(answer.getPlayerOne(), response.getPlayerOne());
		Assert.assertEquals(answer.getPlayerTwo(), response.getPlayerTwo());
		Assert.assertEquals(answer.getWeapon(), response.getWeapon());
		Assert.assertEquals(answer.getStatus(), response.getStatus());
		Assert.assertEquals(answer.getTime(), response.getTime());
	}
}
