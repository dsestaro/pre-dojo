package com.br.amil.predojo.matchstatistics;

import java.text.ParseException;

import com.br.amil.predojo.configs.FileConfig;
import com.br.amil.predojo.configs.FileConstants;
import com.br.amil.predojo.matchstatistics.dto.LineInformation;
import com.br.amil.predojo.matchstatistics.enums.MatchStatusEnum;
import com.br.amil.predojo.utils.FileUtils;

public class LineInterpreter {
	
	public static LineInformation parseLine(String line) {
		LineInformation lineInformation = new LineInformation();
		
		if(!line.isEmpty()) {
			try {
				line = splitTime(line, lineInformation);
				
				splitStatus(line, lineInformation);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return lineInformation;
	}

	private static void splitStatus(String line, LineInformation lineInformation) {
		String[] splitedLine;
		
		if(line.contains(FileConstants.NEW_MATCH)) {
			lineInformation.setStatus(MatchStatusEnum.STARTED);
			
			splitedLine = line.split(FileConstants.NEW_MATCH);
			
			if(FileUtils.validateSplit(splitedLine)) {
				lineInformation.setMatch(splitedLine[1].split(FileConstants.SPACE)[0]);
			}
		} else if(line.contains(FileConstants.MATCH_ENDED)) {
			lineInformation.setStatus(MatchStatusEnum.ENDED);

			splitedLine = line.split(FileConstants.MATCH_ENDED);
			
			lineInformation.setMatch(splitedLine[0].split(FileConstants.SPACE)[1]);
		} else if(line.contains(FileConstants.KILLED)) {
			lineInformation.setStatus(MatchStatusEnum.KILLED);
			
			splitPlayers(line, lineInformation);
		}
	}

	private static void splitPlayers(String line, LineInformation lineInformation) {
		String[] splitedLine = line.split(FileConstants.KILLED);
		
		if(FileUtils.validateSplit(splitedLine)) {
			if(!splitedLine[0].equals(FileConstants.WORLD)) {
				lineInformation.setPlayerOne(splitedLine[0]);
			}
			
			splitedLine = splitedLine[1].split(FileConstants.SPACE);
			
			if(FileUtils.validateSplit(splitedLine)) {
				lineInformation.setPlayerTwo(splitedLine[0]);
				
				lineInformation.setWeapon(splitedLine[splitedLine.length - 1]);
			}
		}
	}

	private static String splitTime(String line, LineInformation lineInformation) throws ParseException {
		String[] splitedLine = line.split(FileConstants.TIME_SEPARATOR);
		
		if(FileUtils.validateSplit(splitedLine)) {
			lineInformation.setTime(FileConfig.FORMATTER.parse(splitedLine[0]));
			
			return splitedLine[1];
		}
		
		return null;
	}
}
