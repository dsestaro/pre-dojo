package com.br.amil.predojo.matchstatistics;

import java.text.ParseException;

import com.br.amil.predojo.configs.FileConfig;
import com.br.amil.predojo.configs.FileConstants;
import com.br.amil.predojo.matchstatistics.dto.LineInformation;
import com.br.amil.predojo.matchstatistics.enums.MatchStatusEnum;
import com.br.amil.predojo.utils.FileUtils;

public class FileInterpreter {
	
	public static LineInformation parseLine(String line) {
		LineInformation lineInformation = new LineInformation();
		
		if(!line.isEmpty()) {
			try {
				line = splitTime(line, lineInformation);
				
				line = splitStatus(line, lineInformation);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return lineInformation;
	}

	private static String splitStatus(String line, LineInformation lineInformation) {
		String[] splitedLine;
		
		if(line.contains(FileConstants.NEW_MATCH)) {
			splitStatus(line, lineInformation, FileConstants.NEW_MATCH, MatchStatusEnum.STARTED);
		} else if(line.contains(FileConstants.MATCH_ENDED)) {
			splitStatus(line, lineInformation, FileConstants.MATCH_ENDED, MatchStatusEnum.ENDED);
		} else if(line.contains(FileConstants.KILLED)) {
			splitStatus(line, lineInformation, FileConstants.KILLED, MatchStatusEnum.KILLED);
			
			splitPlayers(line, lineInformation);
		}
		
		return null;
	}

	private static void splitPlayers(String line, LineInformation lineInformation) {
		String[] splitedLine = line.split(FileConstants.KILLED);
		
		if(FileUtils.validateSplit(splitedLine)) {

		}
	}

	private static void splitStatus(String line, LineInformation lineInformation, String constant, MatchStatusEnum type) {
		String[] splitedLine = line.split(constant);
		
		if(FileUtils.validateSplit(splitedLine)) {
			lineInformation.setStatus(type);
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
