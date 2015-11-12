package com.br.amil.predojo.matchstatistics.enums;

public enum MatchStatusEnum {
	STARTED(1),
	ENDED(2),
	KILLED(3);
	
	private final int valor;
	
	MatchStatusEnum(int valor) {
		this.valor = valor;
	}
}
