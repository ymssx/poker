package com.xjtu.cp;

import java.util.ArrayList;

public class Card {
    public Card() {
    }
    public ArrayList<String> getCard(){
        ArrayList<String> cardlist=new ArrayList<String>();
        ArrayList<Character> arr=new ArrayList<Character>();
        arr.add('D');
        arr.add('S');
        arr.add('H');
        arr.add('C');
        ArrayList<Character> arr1=new ArrayList<Character>();
        for (int i = 2; i <10 ; i++) {
            arr1.add((char) (i+48));
        }
        arr1.add('T');
        arr1.add('J');
        arr1.add('Q');
        arr1.add('K');
        arr1.add('A');
        for (int i = 0; i <arr.size() ; i++) {
            for (int j = 0; j <arr1.size(); j++) {
                String tmp=arr.get(i)+""+arr1.get(j);
                cardlist.add(tmp);
            }
        }
        return cardlist;
    }
}
