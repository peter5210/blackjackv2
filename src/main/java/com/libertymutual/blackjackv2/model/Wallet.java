package com.libertymutual.blackjackv2.model;

public class Wallet {

	public int startingBalance = 100;
	public int bet;
	
	public Wallet() {
	}
	
	public int howMuchDoIHaveLeft(int betAmount) {
		bet = betAmount;
		int remainingBalance;
		remainingBalance = startingBalance - bet;
		return remainingBalance; 
	}

	public int getStartingBalance() {
		return startingBalance;
	}

	public int getbet() {
		return bet;
	}
	
}
