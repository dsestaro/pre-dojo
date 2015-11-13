package com.br.amil.predojo.match;

import com.br.amil.predojo.match.dto.MatchDTO;
import com.br.amil.predojo.match.dto.PlayerInfoDTO;

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
		if(!this.data.getPlayersInfo().containsKey(name)) {
			this.data.getPlayersInfo().put(name, new PlayerInfoDTO(name));
		}
		
		Integer nbrDeaths = this.data.getPlayersInfo().get(name).getPlayersDeath();
		
		this.data.getPlayersInfo().get(name).setPlayersDeath(nbrDeaths + 1);
	}
	
	public void addPlayerKill(String name) {
		if(!this.data.getPlayersInfo().containsKey(name)) {
			this.data.getPlayersInfo().put(name, new PlayerInfoDTO(name));
		}
		
		Integer nbrKills = this.data.getPlayersInfo().get(name).getPlayersKills();
		
		this.data.getPlayersInfo().get(name).setPlayersKills(nbrKills + 1);
	}
	
	public void addWeaponKill(String name, String player) {
		if(!this.data.getPlayersInfo().containsKey(player)) {
			this.data.getPlayersInfo().put(player, new PlayerInfoDTO(player));
		}
		
		this.data.getPlayersInfo().get(player).addWeaponsKills(name);
	}
}
