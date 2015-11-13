package com.br.amil.predojo.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.br.amil.predojo.match.Match;
import com.br.amil.predojo.matchstatistics.FileInterpreter;
import com.br.amil.predojo.matchstatistics.MatchStatistics;
import com.br.amil.predojo.matchstatistics.dto.LineInformation;
import com.br.amil.predojo.utils.FileUtils;

@Service
public class FileUploadService {
	
	public List<Match> processFile(String file) {
		String[] lines = FileUtils.splitLines(file);
		
		LinkedList<LineInformation> linesInformation = FileInterpreter.parseLines(lines);
		
		MatchStatistics matchStatistics = new MatchStatistics();
		
		List<Match> matches = matchStatistics.processMatchs(linesInformation); 
		
		return matches;
	}
}
