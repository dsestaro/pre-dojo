package com.br.amil.predojo.match.dto;

import java.util.HashMap;
import java.util.Map;

public class PlayerInfoDTO {
	private int playersDeath;
	private int playersKills;
	private Map<String, WeaponInfoDTO> weaponsKills;
	
	public PlayerInfoDTO() {
		this.playersDeath = 0;
		this.playersKills = 0;
		this.weaponsKills = new HashMap<String, WeaponInfoDTO>();
	}
	public int getPlayersDeath() {
		return playersDeath;
	}
	public void setPlayersDeath(int playersDeath) {
		this.playersDeath = playersDeath;
	}
	public int getPlayersKills() {
		return playersKills;
	}
	public void setPlayersKills(int playersKills) {
		this.playersKills = playersKills;
	}
	public void addWeaponsKills(String name) {
		if(!this.weaponsKills.containsKey(name)) {
			this.weaponsKills.put(name, new WeaponInfoDTO(name));
		}
		
		int killsNbr = this.weaponsKills.get(name).getKills();
		
		this.weaponsKills.get(name).setKills(killsNbr + 1);
	}
	public Map<String, WeaponInfoDTO> getWeaponsKills() {
		return weaponsKills;
	}
}
