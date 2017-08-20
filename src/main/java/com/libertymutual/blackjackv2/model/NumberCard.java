package com.libertymutual.blackjackv2.model;

public class NumberCard implements Card {

	String suit; 
	int[] numberCardValues;
	String displayValue;
	
	public NumberCard(String suit, int numberCardValues) {
		this.suit = suit;
		this.numberCardValues = new int[] {numberCardValues, numberCardValues};
		displayValue = String.valueOf(numberCardValues);
	}

	public String getSuit() {
		return suit;
	}

	public int[] getCardValues() {
		return numberCardValues;
	}

	public String getDisplayValue() {
		return displayValue;
	}

}
