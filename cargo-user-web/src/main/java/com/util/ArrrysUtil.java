package com.util;

import java.util.Random;

public class ArrrysUtil {
	
	//随机生成数组
	public static int[] randomArrys(int size) {
		int[] or = new int[size];
		Random rd = new Random();
		for (int i = 0; i < or.length; i++) {
			or[i] = rd.nextInt();
		}
		return or;
	}
	
}
