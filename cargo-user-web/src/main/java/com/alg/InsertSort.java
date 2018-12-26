package com.alg;

import java.util.Arrays;

import org.junit.Test;

//插入排序
public class InsertSort {
	
	public static int[] sortToBg(int[] or) {
		int il = or.length;
		int temp = 0;//交换媒介
		for (int i = 0 ; i < il ; i++) {
			for (int o = i; o >= 1 ; o--) {
				if (or[o] < or[o-1]) {
					System.err.printf("change:o[i:%d,o:%d]\n",i,o);
					temp = or[o];
					or[o] = or[o-1];
					or[o-1] = temp;
				} 
			}
		}
		return or;
	}
	
	@Test
	public void sortTtest() {
		int[] or = {-1,9,5,10,7,3,4,11,2,1,0,8};
		System.err.printf("or[%s]\n",Arrays.toString(or));
		int[] rel = InsertSort.sortToBg(or);
		System.err.printf("rel[%s]\n",Arrays.toString(rel));
		
	}

}
