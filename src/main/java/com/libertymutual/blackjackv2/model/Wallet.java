package com.libertymutual.blackjackv2.model;

public class Wallet {

	public int startingBalance = 100;
	
	public Wallet() {
	}
	
	public int howMuchDoIHaveLeft(int bet) {
		int remainingBalance;
		remainingBalance = startingBalance - bet;
		return remainingBalance; 
	}

	public int getStartingBalance() {
		return startingBalance;
	}
	
}
