package com.br.amil.predojo.match;

import java.util.Date;
import java.util.Map;

public class Match {
	private String id;
	private Date startTime;
	private Date endTime;
	private Map<String, Integer> playersDeath;
	private Map<String, Integer> playersKills;
	private Map<String, Integer> weaponsKills;
	
	
	
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
}
