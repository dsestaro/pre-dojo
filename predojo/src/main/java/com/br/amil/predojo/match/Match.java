package com.br.amil.predojo.match;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Match {
	private String id;
	private Date startTime;
	private Date endTime;
	private Map<String, Integer> playersDeath;
	private Map<String, Integer> playersKills;
	private Map<String, Integer> weaponsKills;
	
	public Match() {
		this.playersDeath = new HashMap<String, Integer>();
		this.playersKills = new HashMap<String, Integer>();
		this.weaponsKills = new HashMap<String, Integer>();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Map<String, Integer> getPlayersDeath() {
		return playersDeath;
	}
	public void setPlayersDeath(Map<String, Integer> playersDeath) {
		this.playersDeath = playersDeath;
	}
	public Map<String, Integer> getPlayersKills() {
		return playersKills;
	}
	public void setPlayersKills(Map<String, Integer> playersKills) {
		this.playersKills = playersKills;
	}
	public Map<String, Integer> getWeaponsKills() {
		return weaponsKills;
	}
	public void setWeaponsKills(Map<String, Integer> weaponsKills) {
		this.weaponsKills = weaponsKills;
	}
	
	public void addPlayerDeath(String name) {
		if(this.playersDeath.containsKey(name)) {
			Integer nbrDeaths = this.playersDeath.get(name);
			
			this.playersDeath.put(name, nbrDeaths + 1);
		} else {
			this.playersDeath.put(name, 1);
		}
		
		addPlayerToMath(name);
	}
	
	public void addPlayerKill(String name) {
		if(this.playersKills.containsKey(name)) {
			Integer nbrDeaths = this.playersKills.get(name);
			
			this.playersKills.put(name, nbrDeaths + 1);
		} else {
			this.playersKills.put(name, 1);
		}
		
		addPlayerToMath(name);
	}

	private void addPlayerToMath(String name) {
		if(!this.playersDeath.containsKey(name)) {
			this.playersDeath.put(name, 0);
		}
		
		if(!this.playersKills.containsKey(name)) {
			this.playersKills.put(name, 0);
		}
	}
}
