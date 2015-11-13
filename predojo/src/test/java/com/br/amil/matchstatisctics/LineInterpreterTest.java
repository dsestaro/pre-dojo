package com.br.amil.matchstatisctics;

import java.text.ParseException;

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
import com.br.amil.predojo.matchstatistics.LineInterpreter;
import com.br.amil.predojo.matchstatistics.dto.LineInformation;
import com.br.amil.predojo.matchstatistics.enums.MatchStatusEnum;
import com.br.amil.predojo.start.PredojoApplication;
import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PredojoApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class LineInterpreterTest {

	@Value("${local.server.port}")
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
	}
	
	@Test
	public void parseLineNewMatch() throws ParseException {
		String line = "23/04/2013 15:34:22 - New match 11348965 has started";
		
		LineInformation answer = new LineInformation();
		answer.setTime(FileConfig.FORMATTER.parse("23/04/2013 15:34:22"));
		answer.setMatch("11348965");
		answer.setStatus(MatchStatusEnum.STARTED);
		
		LineInformation response = LineInterpreter.parseLine(line);		
		
		Assert.assertEquals(answer.getMatch(), response.getMatch());
		Assert.assertEquals(answer.getPlayerOne(), response.getPlayerOne());
		Assert.assertEquals(answer.getPlayerTwo(), response.getPlayerTwo());
		Assert.assertEquals(answer.getWeapon(), response.getWeapon());
		Assert.assertEquals(answer.getStatus(), response.getStatus());
		Assert.assertEquals(answer.getTime(), response.getTime());
	}
	
	@Test
	public void parseLineMatchEnded() throws ParseException {
		String line = "23/04/2013 15:39:22 - Match 11348965 has ended";
		
		LineInformation answer = new LineInformation();
		answer.setTime(FileConfig.FORMATTER.parse("23/04/2013 15:39:22"));
		answer.setMatch("11348965");
		answer.setStatus(MatchStatusEnum.ENDED);
		
		LineInformation response = LineInterpreter.parseLine(line);		
		
		Assert.assertEquals(answer.getMatch(), response.getMatch());
		Assert.assertEquals(answer.getPlayerOne(), response.getPlayerOne());
		Assert.assertEquals(answer.getPlayerTwo(), response.getPlayerTwo());
		Assert.assertEquals(answer.getWeapon(), response.getWeapon());
		Assert.assertEquals(answer.getStatus(), response.getStatus());
		Assert.assertEquals(answer.getTime(), response.getTime());
	}
	
	@Test
	public void parseLinePlayerKillingPlayer() throws ParseException {
		String line = "23/04/2013 15:36:04 - Roman killed Nick using M16";
		
		LineInformation answer = new LineInformation();
		answer.setTime(FileConfig.FORMATTER.parse("23/04/2013 15:36:04"));
		answer.setStatus(MatchStatusEnum.KILLED);
		answer.setPlayerOne("Roman");
		answer.setPlayerTwo("Nick");
		answer.setWeapon("M16");
		
		LineInformation response = LineInterpreter.parseLine(line);		
		
		Assert.assertEquals(answer.getMatch(), response.getMatch());
		Assert.assertEquals(answer.getPlayerOne(), response.getPlayerOne());
		Assert.assertEquals(answer.getPlayerTwo(), response.getPlayerTwo());
		Assert.assertEquals(answer.getWeapon(), response.getWeapon());
		Assert.assertEquals(answer.getStatus(), response.getStatus());
		Assert.assertEquals(answer.getTime(), response.getTime());
	}
	
	@Test
	public void parseLineWorldKillingPlayer() throws ParseException {
		String line = "23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN";
		
		LineInformation answer = new LineInformation();
		answer.setTime(FileConfig.FORMATTER.parse("23/04/2013 15:36:33"));
		answer.setStatus(MatchStatusEnum.KILLED);
		answer.setPlayerTwo("Nick");
		answer.setWeapon("DROWN");
		
		LineInformation response = LineInterpreter.parseLine(line);		
		
		Assert.assertEquals(answer.getMatch(), response.getMatch());
		Assert.assertEquals(answer.getPlayerOne(), response.getPlayerOne());
		Assert.assertEquals(answer.getPlayerTwo(), response.getPlayerTwo());
		Assert.assertEquals(answer.getWeapon(), response.getWeapon());
		Assert.assertEquals(answer.getStatus(), response.getStatus());
		Assert.assertEquals(answer.getTime(), response.getTime());
	}
}
