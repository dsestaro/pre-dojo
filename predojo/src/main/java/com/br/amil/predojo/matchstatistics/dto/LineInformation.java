package com.br.amil.predojo.matchstatistics.dto;

import java.sql.Date;

import com.br.amil.predojo.matchstatistics.enums.MatchStatusEnum;

public class LineInformation {
	private Date time;
	private String match;
	private MatchStatusEnum status;
	private String playerOne;
	private String playerTwo;
	private String weapon;
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getMatch() {
		return match;
	}
	public void setMatch(String match) {
		this.match = match;
	}
	public MatchStatusEnum getStatus() {
		return status;
	}
	public void setStatus(MatchStatusEnum status) {
		this.status = status;
	}
	public String getPlayerOne() {
		return playerOne;
	}
	public void setPlayerOne(String playerOne) {
		this.playerOne = playerOne;
	}
	public String getPlayerTwo() {
		return playerTwo;
	}
	public void setPlayerTwo(String playerTwo) {
		this.playerTwo = playerTwo;
	}
	public String getWeapon() {
		return weapon;
	}
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
}
