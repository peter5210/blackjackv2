package com.libertymutual.blackjackv2.model;

public class FaceCard implements Card {

	String suit; 
	int[] faceCardValue = {10, 10};
	String displayValue; 
	
	public FaceCard (String suit, String displayValue) {
		this.suit = suit;
		this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}

	public int[] getCardValues() {
		return faceCardValue;
	}

	public String getSuit() {
		return suit;
	}
	
}
