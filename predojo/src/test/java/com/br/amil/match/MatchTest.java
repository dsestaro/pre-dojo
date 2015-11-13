package com.br.amil.match;

import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.br.amil.predojo.match.Match;
import com.br.amil.predojo.start.PredojoApplication;
import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PredojoApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class MatchTest {

	@Value("${local.server.port}")
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
	}
	
	@Test
	public void addPlayerDeathTest() {
		String name = "Nick";
		
		Map<String, Integer> expectedPlayerDeaths = new HashMap<String, Integer>();
		
		expectedPlayerDeaths.put(name, 1);
		
		Match match = new Match();
		
		match.addPlayerDeath(name);
		
		Assert.assertThat(match.getPlayersDeath(), is(expectedPlayerDeaths));
	}
	
	@Test
	public void addPlayerDeathToAAlreadyKilledPlayerTest() {
		String name = "Nick";
		
		Map<String, Integer> expectedPlayerDeaths = new HashMap<String, Integer>();
		
		expectedPlayerDeaths.put(name, 2);
		
		Match match = new Match();
		
		match.addPlayerDeath(name);
		match.addPlayerDeath(name);
		
		Assert.assertThat(match.getPlayersDeath(), is(expectedPlayerDeaths));
	}
	
	@Test
	public void addPlayerKillTest() {
		String name = "Nick";
		
		Map<String, Integer> expectedPlayerKills = new HashMap<String, Integer>();
		
		expectedPlayerKills.put(name, 1);
		
		Match match = new Match();
		
		match.addPlayerKill(name);
		
		Assert.assertThat(match.getPlayersKills(), is(expectedPlayerKills));
	}
	
	@Test
	public void addPlayerKillToAAlreadyScoringPlayerTest() {
		String name = "Nick";
		
		Map<String, Integer> expectedPlayerKills = new HashMap<String, Integer>();
		
		expectedPlayerKills.put(name, 2);
		
		Match match = new Match();
		
		match.addPlayerKill(name);
		match.addPlayerKill(name);

		Assert.assertThat(match.getPlayersKills(), is(expectedPlayerKills));
	}
}
