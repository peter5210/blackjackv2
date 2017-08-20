package com.libertymutual.blackjackv2.model;

import java.util.ArrayList;

public class Dealer {
	
	ArrayList<Card> cards;
	
	public Dealer() {
		cards = new ArrayList<Card>();
	}

	public void takeCard(Card card) {
		cards.add(card);
	}
	
	public ArrayList<Card> getHand() {
		return cards;
	}
	
	public void clearHand() {
		cards.clear();
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
