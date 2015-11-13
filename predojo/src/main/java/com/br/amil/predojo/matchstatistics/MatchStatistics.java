package com.br.amil.predojo.matchstatistics;

import java.util.Date;

import com.br.amil.predojo.match.Match;

public class MatchStatistics {

	public Match createMatch(String id, Date dateStart) {
		Match match = new Match();
		
		match.getData().setId(id);
		match.getData().setStartTime(dateStart);
		
		return match;
	}

}
