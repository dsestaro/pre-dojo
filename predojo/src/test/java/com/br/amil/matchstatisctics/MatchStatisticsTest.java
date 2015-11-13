package com.br.amil.matchstatisctics;

import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
import com.br.amil.predojo.matchstatistics.FileInterpreter;
import com.br.amil.predojo.matchstatistics.LineInterpreter;
import com.br.amil.predojo.matchstatistics.MatchStatistics;
import com.br.amil.predojo.matchstatistics.dto.LineInformation;
import com.br.amil.predojo.start.PredojoApplication;
import com.google.common.io.CharStreams;
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
		
		Match actual = matchstatistics.createMatch(id, dateStart);
		
		Assert.assertThat(answer.getData().getId(), is(actual.getData().getId()));
		Assert.assertThat(answer.getData().getStartTime(), is(actual.getData().getStartTime()));
	}
	
	@Test
	public void processMatchsTest() throws IOException {
		MatchStatistics matchStatistics = new MatchStatistics();
		InputStream inputStream = this.getClass().getResourceAsStream("/game.txt");
		
		String file = CharStreams.toString(new InputStreamReader(inputStream));
		
		String[] lines = file.split("\n\r");
		
		LinkedList<LineInformation> processedLines = FileInterpreter.parseLines(lines);
		
		matchStatistics.processMatchs(processedLines);
	}
	
	@Test
	public void processDeath() {
		String line = "23/04/2013 15:36:04 - Roman killed Nick using M16";
		
		LineInformation lineInformation = LineInterpreter.parseLine(line);
		
		MatchStatistics matchStatistics = new MatchStatistics();
		
		Match expectedMatch = new Match();
		
		expectedMatch.addPlayerDeath("Nick");
		expectedMatch.addPlayerKill("Roman");
		expectedMatch.addWeaponKill("M16");
		
		Match actualMatch = matchStatistics.processDeath(lineInformation, new Match());
		
		Assert.assertThat(expectedMatch.getData().getPlayersDeath(), is(actualMatch.getData().getPlayersDeath()));
		Assert.assertThat(expectedMatch.getData().getPlayersKills(), is(actualMatch.getData().getPlayersKills()));
		Assert.assertThat(expectedMatch.getData().getWeaponsKills(), is(actualMatch.getData().getWeaponsKills()));
	}
}
