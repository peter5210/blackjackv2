package com.libertymutual.blackjackv2.model;

public class Wallet {

	public int balance;
	public int bet;
	
	public Wallet() {
		this.balance = 100;
	}
	
	public int getStartingBalance(int betAmount) {
		bet = betAmount;
		balance = balance - bet;
		return balance; 
	}

	public int getbalance() {
		return balance;
	}

	public int getbet() {
		return bet;
	}
	
	public int payoutForBlackjackWin() {
		return balance = balance + (bet * 2);
	}
	
	public int payoutForPush() {
		return balance = balance + bet;
	}	
	
	public int payoutForWin() {
		return balance = (int) (balance + (bet * 1.5));
	}
	
	public int payoutForLoss() {
		return balance;
	}
	
}
