package com.br.amil.predojo.matchstatistics.dto;

import java.util.List;

import com.br.amil.predojo.match.Match;

public class MatchStatisticsDTO {
	private List<Match> matches;

	public MatchStatisticsDTO() {
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
}