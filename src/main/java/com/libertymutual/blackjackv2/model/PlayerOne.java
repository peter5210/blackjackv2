package com.libertymutual.blackjackv2.model;

import java.util.ArrayList;

public class PlayerOne {

	ArrayList<Card> cards;
	
	public PlayerOne() {
		cards = new ArrayList<Card>();
	}
	
	public void takeCard(Card card) {
		cards.add(card);
	}
	
	public void clearHand() {
		cards.clear();
	}
	
	public ArrayList<Card> getHand() {
		return cards;
	}
	
	public int[] totalCardValue() {
		int[] sums = new int[] { 0, 0 };
		for (Card c : cards) {
			int[] values = c.getCardValues();
			sums[0] += values[0];
			sums[1] += values[1];
		}
		
		return sums;
	}
		
}
