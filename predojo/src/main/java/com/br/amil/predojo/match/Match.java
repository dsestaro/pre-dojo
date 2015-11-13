package com.br.amil.predojo.match;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.br.amil.predojo.match.dto.MatchDTO;

public class Match {
	private MatchDTO data;

	public Match() {
		this.data = new MatchDTO();
	}
	
	public MatchDTO getData() {
		return data;
	}

	public void setData(MatchDTO data) {
		this.data = data;
	}

	public void addPlayerDeath(String name) {
		if(this.data.getPlayersDeath().containsKey(name)) {
			Integer nbrDeaths = this.data.getPlayersDeath().get(name);
			
			this.data.getPlayersDeath().put(name, nbrDeaths + 1);
		} else {
			this.data.getPlayersDeath().put(name, 1);
		}
		
		addPlayerToMath(name);
	}
	
	public void addPlayerKill(String name) {
		if(this.data.getPlayersKills().containsKey(name)) {
			Integer nbrDeaths = this.data.getPlayersKills().get(name);
			
			this.data.getPlayersKills().put(name, nbrDeaths + 1);
		} else {
			this.data.getPlayersKills().put(name, 1);
		}
		
		addPlayerToMath(name);
	}

	private void addPlayerToMath(String name) {
		if(!this.data.getPlayersDeath().containsKey(name)) {
			this.data.getPlayersDeath().put(name, 0);
		}
		
		if(!this.data.getPlayersKills().containsKey(name)) {
			this.data.getPlayersKills().put(name, 0);
		}
	}
}