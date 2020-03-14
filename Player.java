package com.xjtu.cp;

import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<String> pcard;
	private int level;
	private ArrayList<Integer> Highcard;
	private boolean isWin;
		
	public Player() {
		super();
	}

	public Player(String name, ArrayList<String> pcard) {
		super();
		this.name = name;
		this.pcard = pcard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getPcard() {
		return pcard;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ArrayList<Integer> getHighcard() {
		return Highcard;
	}

	public void setHighcard(ArrayList<Integer> highcard) {
		Highcard = highcard;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}
}
