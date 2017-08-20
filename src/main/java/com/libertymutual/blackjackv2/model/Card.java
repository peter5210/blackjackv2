package com.libertymutual.blackjackv2.model;

public interface Card {
	
	String getDisplayValue(); 

	int[] getCardValues();

	String getSuit();

}
