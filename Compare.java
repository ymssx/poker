package com.xjtu.cp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class Compare {
	public static void main(String[] args) {
		Card card=new Card();
		ArrayList<String> card2 = card.getCard();
		Comp_Card cc=new Comp_Card(card2);
		ArrayList<String> card_list1 = cc.getCard_list1();
		ArrayList<String> card_list2 = cc.getCard_list2();
		Player Witle=new Player("Witle", card_list1);
		Player Black=new Player("Black", card_list2);
		find_Level(Witle);
		System.out.println(Witle.getPcard());
		System.out.println(Witle.getLevel()+":"+Witle.getHighcard());
		find_Level(Black);
		System.out.println(Black.getPcard());
		System.out.println(Black.getLevel()+":"+Black.getHighcard());
		comp(Witle, Black);
	}
	
	public static void find_Level(Player pler){
		ArrayList<String> pcard = pler.getPcard();
		ArrayList<Character> pcard_color = new ArrayList<Character>();
		ArrayList<Character> pcard_number = new ArrayList<Character>();
		for (String string : pcard) {
			pcard_color.add(string.charAt(0));
			pcard_number.add(string.charAt(1));
		}
			ArrayList<Integer> pcard_number_int=Char_change_int(pcard_number);
		int first=0;
		for(int i=1;i<pcard_number.size();i++){
			for (int j = 0; j < i; j++) {
				if(pcard_number.get(i).equals(pcard_number.get(j)))
					first++;
			}
		}
		if(first==1){
			pler.setLevel(1);
			int findPair = findPair(pcard_number_int);
			for (int i = 0; i < pcard_number_int.size(); i++) {
				if(pcard_number_int.get(i)==findPair){
					pcard_number_int.remove(i);
					i--;
				}
			}
			Collections.sort(pcard_number_int);
			ArrayList<Integer> HighCard=new ArrayList<Integer>();
			HighCard.add(findPair);
			for (int i = pcard_number_int.size()-1; i>=0; i--) {
				HighCard.add(pcard_number_int.get(i));
			}
			pler.setHighcard(HighCard);
		}else if(first==2){
			pler.setLevel(2);
			ArrayList<Integer> findPairs = findPairs(pcard_number_int);
			for (Integer integer : findPairs) {
				for (int i = 0; i < pcard_number_int.size(); i++) {
					if(pcard_number_int.get(i)==integer){
						pcard_number_int.remove(i);
						i--;
					}
				}
			}
			Collections.sort(findPairs);
			ArrayList<Integer> HighCard=new ArrayList<Integer>();
			for (int i = findPairs.size()-1; i>=0; i--) {
				HighCard.add(findPairs.get(i));
			}
			HighCard.add(pcard_number_int.get(0));
			pler.setHighcard(HighCard);
		}else if(first==3){
			pler.setLevel(3);
			int findPair = findPair(pcard_number_int);
			for (int i = 0; i < pcard_number_int.size(); i++) {
				if(pcard_number_int.get(i)==findPair){
					pcard_number_int.remove(i);
					i--;
				}
			}
			Collections.sort(pcard_number_int);
			ArrayList<Integer> HighCard=new ArrayList<Integer>();
			HighCard.add(findPair);
			for (int i = pcard_number_int.size()-1; i>=0; i--) {
				HighCard.add(pcard_number_int.get(i));
			}
			pler.setHighcard(HighCard);
		}else if(first==4){
			pler.setLevel(6);
			ArrayList<Integer> find_Full_House = find_Full_House(pcard_number_int);
			ArrayList<Integer> HighCard=new ArrayList<Integer>();
			for (int i = find_Full_House.size()-1; i>=0; i--) {
				HighCard.add(find_Full_House.get(i));
			}
			pler.setHighcard(HighCard);
		}else if(first==6){
			pler.setLevel(7);
			int findPair = findPair(pcard_number_int);
			for (int i = 0; i < pcard_number_int.size(); i++) {
				if(pcard_number_int.get(i)==findPair){
					pcard_number_int.remove(i);
					i--;
				}
			}
			ArrayList<Integer> HighCard=new ArrayList<Integer>();
			HighCard.add(findPair);
			HighCard.add(pcard_number_int.get(0));
			pler.setHighcard(HighCard);
		}else if(first==0){
			HashMap<Integer, Integer> map = isStraight_Flush(pcard_color, pcard_number);
			Set<Integer> set = map.keySet();
			Integer key = set.iterator().next();
			if(key==3){
				pler.setLevel(8);
				ArrayList<Integer> HighCard=new ArrayList<Integer>();
				HighCard.add(map.get(key));
				pler.setHighcard(HighCard);
			}else if(key==2){
				pler.setLevel(4);
				ArrayList<Integer> HighCard=new ArrayList<Integer>();
				HighCard.add(map.get(key));
				pler.setHighcard(HighCard);
			}else if(key==1){
				pler.setLevel(5);
				ArrayList<Integer> HighCard=new ArrayList<Integer>();
				HighCard.add(map.get(key));
				pler.setHighcard(HighCard);
			}else if(key==0){
				pler.setLevel(0);
				ArrayList<Integer> HighCard=new ArrayList<Integer>();
				for (int i = pcard_number_int.size()-1; i>=0; i--) {
					HighCard.add(pcard_number_int.get(i));
				}
				pler.setHighcard(HighCard);
			}
		}
		
	}
	
	public static HashMap<Integer,Integer> isStraight_Flush(ArrayList<Character> pcard_color,ArrayList<Character> pcard_number){
		HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
		boolean isS=true;
		boolean isF=true;
		ArrayList<Integer> pcard_number_int=Char_change_int(pcard_number);
		for (Character character : pcard_color) {
			if(pcard_color.get(0).equals(character)==false)
				isS=false;
		}
		Collections.sort(pcard_number_int);
		for (int i = 1; i < pcard_number_int.size(); i++) {
			if(pcard_number_int.get(i)-pcard_number_int.get(i-1)!=1)
				isF=false;
		}
		if(isF==true&&isS==true)
			map.put(3, pcard_number_int.get(pcard_number_int.size()-1));
		else if(isS==true&&isF==false)
			map.put(2, pcard_number_int.get(pcard_number_int.size()-1));
		else if(isF==true&&isS==false)
			map.put(1, pcard_number_int.get(pcard_number_int.size()-1));
		else
			map.put(0, pcard_number_int.get(pcard_number_int.size()-1));
		return map;
	}
	
	public static ArrayList<Integer> Char_change_int(ArrayList<Character> changeChar){
		ArrayList<Integer> pcard_number_int=new ArrayList<Integer>();
		for (int i = 0; i <changeChar.size(); i++) {
			if(changeChar.get(i)=='A')
				pcard_number_int.add(14);
			else if((changeChar.get(i)=='K'))
				pcard_number_int.add(13);
			else if((changeChar.get(i)=='Q'))
				pcard_number_int.add(12);
			else if((changeChar.get(i)=='J'))
				pcard_number_int.add(11);
			else if((changeChar.get(i)=='T'))
				pcard_number_int.add(10);
			else{
				int tmp=(int)changeChar.get(i);
				pcard_number_int.add(tmp-48);
			}
		}
		return pcard_number_int;
	}
	
	public static int findPair(ArrayList<Integer> x){
		for (int i = 1; i < x.size(); i++) {
			for (int j = 0; j < i; j++) {
				if(x.get(i).equals(x.get(j)))
					return x.get(i);
			}
		}
		return -1;
	}
	
	public static ArrayList<Integer> findPairs(ArrayList<Integer> x){
		ArrayList<Integer> tar=new ArrayList<Integer>();
		for (int i = 1; i < x.size(); i++) {
			for (int j = 0; j < i; j++) {
				if(x.get(i).equals(x.get(j)))
					tar.add(x.get(i));
			}
		}
		return tar;
	}
	
	public static ArrayList<Integer> find_Full_House(ArrayList<Integer> x){
		ArrayList<Integer> tar=new ArrayList<Integer>();
		Integer tmp = x.get(0);
		tar.add(tmp);
		for (int i = 0; i < x.size(); i++) {
			if(x.get(i)==tmp){
				x.remove(i);
				i--;
			}
		}
		tar.add(x.get(0));
		Collections.sort(tar);
		return tar;
	}
	
	public static void comp(Player a,Player b){
		String[] win_method={"High Card","Pair","Two Pairs","Three of a Kind","Straight","Flush","Full House","Four of a Kind","Straight flush"};
		String[] card={"","","2","3","4","5","6","7","8","9","10","J","Q","K","Ace"};
		if(a.getLevel()>b.getLevel()){
			System.out.println(a.getName()+" wins - "+win_method[a.getLevel()]+":"+card[a.getHighcard().get(0)]);
		}else if(a.getLevel()<b.getLevel()){
			System.out.println(b.getName()+" wins - "+win_method[b.getLevel()]+":"+card[b.getHighcard().get(0)]);
		}else{
			boolean isEqual=true;
			ArrayList<Integer> a_hc = a.getHighcard();
			ArrayList<Integer> b_hc = b.getHighcard();
			for (int i = 0; i < a_hc.size(); i++) {
				if(a_hc.get(i)>b_hc.get(i)){
					isEqual=false;
					System.out.println(a.getName()+" wins - "+win_method[a.getLevel()]+":"+card[a_hc.get(i)]);
					break;
				}else if(a_hc.get(i)<b_hc.get(i)){
					isEqual=false;
					System.out.println(b.getName()+" wins - "+win_method[b.getLevel()]+":"+card[b_hc.get(i)]);
					break;
				}
			}
			if(isEqual==true)
				System.out.println("Tie");
		}
	}
}
