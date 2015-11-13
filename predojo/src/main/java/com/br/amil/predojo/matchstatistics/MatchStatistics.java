package com.br.amil.predojo.matchstatistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import com.br.amil.predojo.match.Match;
import com.br.amil.predojo.matchstatistics.dto.LineInformation;
import com.br.amil.predojo.matchstatistics.dto.MatchStatisticsDTO;

public class MatchStatistics {
	
	public MatchStatisticsDTO data = new MatchStatisticsDTO();

	public MatchStatistics() {
		this.data.setMatches(new ArrayList<Match>());
	}

	public Match createMatch(String id, Date dateStart) {
		Match match = new Match();
		
		match.getData().setId(id);
		match.getData().setStartTime(dateStart);
		
		return match;
	}

	public void processMatchs(LinkedList<LineInformation> processedLines) {
		Match match = new Match();
		
		for(LineInformation lineInformation : processedLines) {
			switch(lineInformation.getStatus()) {
			case STARTED:
				match = createMatch(lineInformation.getMatch(), lineInformation.getTime());
				
				break;
			case ENDED:
				match.getData().setEndTime(lineInformation.getTime());
				this.data.getMatches().add(match);

				break;
			case KILLED:
				processDeath(lineInformation, match);
				
				break;
			default:
				break;
			}
		}
	}

	public Match processDeath(LineInformation lineInformation, Match match) {
		match.addPlayerDeath(lineInformation.getPlayerTwo());
		
		if(lineInformation.getPlayerOne() != null) {
			match.addPlayerKill(lineInformation.getPlayerOne());
		}
		
		match.addWeaponKill(lineInformation.getWeapon());
		
		return match;
	}
}
