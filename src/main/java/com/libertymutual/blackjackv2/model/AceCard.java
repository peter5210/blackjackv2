package com.libertymutual.blackjackv2.model;

public class AceCard implements Card {

	private int[] aceValues;
	private String suit;
	private String displayValue = "A";
	
	public AceCard (String suit) {
		this.suit = suit;
		this.aceValues = new int[] {1, 11};
	}
	
	public String getDisplayValue() {
		return displayValue;
	}

	public int[] getCardValues() {
		return aceValues;
	}

	public String getSuit() {
		return suit;
	}

}
