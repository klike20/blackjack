package com.alice.bj.model;

public class Card {
	public String name;//should use getter
	public int value;//should use getter
	public boolean tap;//should use getter
	
	public void unTap() {
		tap = false;
	}
	
	public String getName() {
		if (tap) {
			return "";
		}
		return name;
	}
	
	public int getValue() {
		if (tap) {
			new IllegalStateException("the value can not been seen");
		}
		return value;
	}
}
