package com.br.amil.predojo.utils;

public class FileUtils {
	public static boolean validateSplit(String[] splited) {
		if(splited != null && splited.length > 0) {
			return true;
		}
		
		return false;
	}
}
