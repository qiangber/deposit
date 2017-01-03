package com.deposit.service;

import java.text.NumberFormat;

public class Test {
	public static void main(String[] args) {
		String s = "300吨/手";
		System.out.println(s.replaceAll("\\D", ""));
		System.out.println(String.format("%.2f", 0.7000000000000001));
	}
}
