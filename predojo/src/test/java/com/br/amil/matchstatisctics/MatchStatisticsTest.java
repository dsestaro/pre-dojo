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
import com.br.amil.predojo.match.Match;
import com.br.amil.predojo.matchstatistics.MatchStatistics;
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
	public void createMatchTest() throws ParseException {
		String id = "11348965";
		Date dateStart = FileConfig.FORMATTER.parse("23/04/2013 15:34:22");
		
		MatchStatistics matchstatistics = new MatchStatistics();

		Match answer = new Match();
		
		answer.getData().setId(id);
		answer.getData().setStartTime(dateStart);
		
		Assert.assertEquals(answer, matchstatistics.createMatch(id, dateStart));
	}
}
