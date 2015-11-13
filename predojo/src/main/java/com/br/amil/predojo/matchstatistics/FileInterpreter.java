package com.br.amil.predojo.matchstatistics;

import java.util.LinkedList;

import com.br.amil.predojo.matchstatistics.dto.LineInformation;

public class FileInterpreter {

	public static LinkedList<LineInformation> parseLines(String[] lines) {
		LinkedList<LineInformation> processedLines = new LinkedList<LineInformation>();
		
		for(String line : lines) {
			processedLines.add(LineInterpreter.parseLine(line));
		}
		
		return processedLines;
	}

}
