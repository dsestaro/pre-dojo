package com.br.amil.predojo.match.dto;

public class WeaponInfoDTO {
	private String weaponName;
	private int kills;

	public WeaponInfoDTO(String name) {
		this.weaponName = name;
		this.kills = 0;
	}
	
	public String getWeaponName() {
		return weaponName;
	}
	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
}
