package com.br.amil.predojo.match.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MatchDTO {
	private String id;
	private Date startTime;
	private Date endTime;
	private Map<String, PlayerInfoDTO> playersInfo;

	public MatchDTO() {
		this.playersInfo = new HashMap<String, PlayerInfoDTO>();
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

	public Map<String, PlayerInfoDTO> getPlayersInfo() {
		return playersInfo;
	}

	public void setPlayersInfo(Map<String, PlayerInfoDTO> playersInfo) {
		this.playersInfo = playersInfo;
	}
}