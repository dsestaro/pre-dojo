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
import com.br.amil.predojo.match.dto.PlayerInfoDTO;
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
		
		PlayerInfoDTO expectedPlayerDeaths = new PlayerInfoDTO(name);
		
		expectedPlayerDeaths.setPlayersDeath(1);
		
		Match match = new Match();
		
		match.addPlayerDeath(name);
		
		Assert.assertThat(match.getData().getPlayersInfo().get("Nick").getPlayersDeath(), is(expectedPlayerDeaths.getPlayersDeath()));
	}
	
	@Test
	public void addPlayerDeathToAAlreadyKilledPlayerTest() {
		String name = "Nick";
		
		PlayerInfoDTO expectedPlayerDeaths = new PlayerInfoDTO(name);
		
		expectedPlayerDeaths.setPlayersDeath(2);
		
		Match match = new Match();
		
		match.addPlayerDeath(name);
		match.addPlayerDeath(name);
		
		Assert.assertThat(match.getData().getPlayersInfo().get(name).getPlayersDeath(), is(expectedPlayerDeaths.getPlayersDeath()));
	}
	
	@Test
	public void addPlayerKillTest() {
		String name = "Nick";
		
		PlayerInfoDTO expectedPlayerKills = new PlayerInfoDTO(name);
		
		expectedPlayerKills.setPlayersKills(1);
		
		Match match = new Match();
		
		match.addPlayerKill(name);
		
		Assert.assertThat(match.getData().getPlayersInfo().get(name).getPlayersKills(), is(expectedPlayerKills.getPlayersKills()));
	}
	
	@Test
	public void addPlayerKillToAAlreadyScoringPlayerTest() {
		String name = "Nick";
		
		PlayerInfoDTO expectedPlayerKills = new PlayerInfoDTO(name);
		
		expectedPlayerKills.setPlayersKills(2);
		
		Match match = new Match();
		
		match.addPlayerKill(name);
		match.addPlayerKill(name);

		Assert.assertThat(match.getData().getPlayersInfo().get(name).getPlayersKills(), is(expectedPlayerKills.getPlayersKills()));
	}
	
	@Test
	public void addWeaponKillTest() {
		String name = "AK-47";
		String player = "Adam";
		
		PlayerInfoDTO expectedWeaponKills = new PlayerInfoDTO(player);
		
		expectedWeaponKills.addWeaponsKills(name);
		
		Match match = new Match();
		
		match.addWeaponKill(name, player);
		
		Assert.assertThat(match.getData().getPlayersInfo().get(player).getWeaponsKills().get(name).getKills(), is(expectedWeaponKills.getWeaponsKills().get(name).getKills()));
	}
	
	@Test
	public void addWeaponKillToAAlreadyScoringWeaponTest() {
		String name = "AK-47";
		String player = "Adam";
		
		PlayerInfoDTO expectedWeaponKills = new PlayerInfoDTO(player);
		
		expectedWeaponKills.addWeaponsKills(name);
		expectedWeaponKills.addWeaponsKills(name);
		
		Match match = new Match();
		
		match.addWeaponKill(name, player);
		match.addWeaponKill(name, player);

		Assert.assertThat(match.getData().getPlayersInfo().get(player).getWeaponsKills().get(name).getKills(), is(expectedWeaponKills.getWeaponsKills().get(name).getKills()));
	}
	
	@Test
	public void validateLongestStreakTest() {
		String name = "Nick";
		
		Match match = new Match();
		
		match.addPlayerKill(name);
		match.addPlayerKill(name);
		
		Assert.assertThat(match.getData().getLongestStreakPlayer(), is(name));
	}
}
