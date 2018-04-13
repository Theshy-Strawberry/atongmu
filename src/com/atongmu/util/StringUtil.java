package com.atongmu.util;

public class StringUtil {
	public static String nvl(String input){
		String output = "";
		if(input == null || "null".equals(input.toLowerCase())){
			output = "";
		}else{
			output = input;
		}
		return output;
	}
}
