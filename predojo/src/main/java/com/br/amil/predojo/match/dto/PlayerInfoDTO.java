package com.br.amil.predojo.match.dto;

import java.util.HashMap;
import java.util.Map;

public class PlayerInfoDTO {
	private int playersDeath;
	private int playersKills;
	private Map<String, WeaponInfoDTO> weaponsKills;
	private String playerName;
	private int prefWeaponKills;
	private String prefWeapon;
	
	public PlayerInfoDTO(String name) {
		this.playerName = name;
		this.playersDeath = 0;
		this.playersKills = 0;
		this.prefWeaponKills = 0;
		this.prefWeapon = "";
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
		
		if(killsNbr + 1 > this.prefWeaponKills) {
			this.prefWeaponKills = killsNbr + 1;
			this.prefWeapon = name;
		}
		
		this.weaponsKills.get(name).setKills(killsNbr + 1);
	}
	public Map<String, WeaponInfoDTO> getWeaponsKills() {
		return weaponsKills;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getPrefWeaponKills() {
		return prefWeaponKills;
	}
	public void setPrefWeaponKills(int prefWeaponKills) {
		this.prefWeaponKills = prefWeaponKills;
	}
	public String getPrefWeapon() {
		return prefWeapon;
	}
	public void setPrefWeapon(String prefWeapon) {
		this.prefWeapon = prefWeapon;
	}
	public void setWeaponsKills(Map<String, WeaponInfoDTO> weaponsKills) {
		this.weaponsKills = weaponsKills;
	}
}
