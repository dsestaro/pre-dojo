package com.br.amil.matchstatisctics;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.br.amil.predojo.matchstatistics.FileInterpreter;
import com.br.amil.predojo.matchstatistics.LineInterpreter;
import com.br.amil.predojo.matchstatistics.dto.LineInformation;
import com.br.amil.predojo.start.PredojoApplication;
import com.google.common.io.CharStreams;
import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PredojoApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class FileInterpreterTest {

	@Value("${local.server.port}")
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
	}
	
	@Test
	public void processLines() throws IOException {
		InputStream inputStream = this.getClass().getResourceAsStream("/game.txt");
		
		String file = CharStreams.toString(new InputStreamReader(inputStream));
		
		String[] lines = file.split("\r\n");
		
		LinkedList<LineInformation> expectedProcessedLines = new LinkedList<LineInformation>();
		
		for(String line : lines) {
			expectedProcessedLines.add(LineInterpreter.parseLine(line));
		}
		
		LinkedList<LineInformation> processedLines = FileInterpreter.parseLines(lines);
		
		for(int i = 0; i < processedLines.size(); i++) {
			Assert.assertEquals(expectedProcessedLines.get(i).getMatch(), processedLines.get(i).getMatch());
			Assert.assertEquals(expectedProcessedLines.get(i).getPlayerOne(), processedLines.get(i).getPlayerOne());
			Assert.assertEquals(expectedProcessedLines.get(i).getPlayerTwo(), processedLines.get(i).getPlayerTwo());
			Assert.assertEquals(expectedProcessedLines.get(i).getWeapon(), processedLines.get(i).getWeapon());
			Assert.assertEquals(expectedProcessedLines.get(i).getStatus(), processedLines.get(i).getStatus());
			Assert.assertEquals(expectedProcessedLines.get(i).getTime(), processedLines.get(i).getTime());
		}
	}
}
