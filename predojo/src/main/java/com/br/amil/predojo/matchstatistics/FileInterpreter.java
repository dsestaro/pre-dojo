package com.br.amil.predojo.matchstatistics;

import java.text.ParseException;

import com.br.amil.predojo.configs.FileConfig;
import com.br.amil.predojo.configs.FileConstants;
import com.br.amil.predojo.matchstatistics.dto.LineInformation;
import com.br.amil.predojo.utils.FileUtils;

public class FileInterpreter {
	
	public static LineInformation parseLine(String line) {
		LineInformation lineInformation = new LineInformation();
		
		if(!line.isEmpty()) {
			try {
				splitTime(line, lineInformation);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return lineInformation;
	}

	private static void splitTime(String line, LineInformation lineInformation) throws ParseException {
		String[] splitedLine = line.split(FileConstants.TIME_SEPARATOR);
		
		if(FileUtils.validateSplit(splitedLine)) {
			lineInformation.setTime(FileConfig.FORMATTER.parse(splitedLine[0]));
		}
	}
}
