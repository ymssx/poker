package com.xjtu.cp;

import java.util.ArrayList;
import java.util.Collections;

public class Comp_Card {
	private ArrayList<String> card_list1;
	private ArrayList<String> card_list2;
	public Comp_Card(ArrayList<String> card_list) {
		super();
		card_list1=new ArrayList<String>();
		card_list2=new ArrayList<String>();
		Collections.shuffle(card_list);
		for (int i = 0; i < 10; i++) {
			if(i%2==0)
				card_list1.add(card_list.get(i));
			else
				card_list2.add(card_list.get(i));
		}
	}
	public ArrayList<String> getCard_list1() {
		return card_list1;
	}
	public ArrayList<String> getCard_list2() {
		return card_list2;
	}	
}
