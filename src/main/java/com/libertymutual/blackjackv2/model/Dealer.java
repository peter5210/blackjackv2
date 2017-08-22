package com.libertymutual.blackjackv2.model;

import java.util.ArrayList;

public class Dealer {
	
	ArrayList<Card> cards;
	public boolean playerBlackjackWin;
	public boolean playerWin;
	public boolean playerPush;
	
	public Dealer() {
		cards = new ArrayList<Card>();
	}

	public void takeCard(Card card) {
		cards.add(card);
	}
	
	public ArrayList<Card> getHand() {
		return cards;
	}
	
	public Card getFirstCard() {
		return cards.get(0);
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
	
	int firstNumber = totalCardValue()[0];
	int secondNumber = totalCardValue()[1];
	boolean dealerBust;
	
	public boolean dealerBust() {
		if (firstNumber > 21 && secondNumber > 21) {
		dealerBust = true; 
		}
		return dealerBust = false;
	}

	public boolean playerWin(int dealerFirstTotal, int dealerSecondTotal, int playerFirstTotal, int playerSecondTotal, PlayerOne playerOne) {
		dealerFirstTotal = totalCardValue()[0];
		dealerSecondTotal = totalCardValue()[1];
		playerFirstTotal = playerOne.totalCardValue()[0];
		playerSecondTotal = playerOne.totalCardValue()[1];

		if ((playerFirstTotal == 21 || playerSecondTotal == 21) && (dealerFirstTotal < 21 && dealerSecondTotal < 21)) {
			playerBlackjackWin = true;
		}
		if ((dealerFirstTotal == 21 || dealerSecondTotal == 21) && (playerFirstTotal == 21 || playerSecondTotal == 21)) {
			playerPush = true;
		}
		if (dealerFirstTotal > playerFirstTotal || dealerFirstTotal > playerSecondTotal) {		
			if (dealerSecondTotal > playerFirstTotal || dealerSecondTotal > playerSecondTotal) {
				playerWin = false;
			}
		}		
		return playerWin = true;
	} 
	
	public int payout(Wallet wallet) {
		if (playerBlackjackWin) {
			wallet.payoutForBlackjackWin();
			} if (playerPush); {
				wallet.payoutForPush();
				} if (!playerWin); {
					 wallet.payoutForWin();
					} return wallet.payoutForLoss();
	
	}

	public boolean isPlayerBlackjackWin() {
		return playerBlackjackWin;
	}

	public boolean isPlayerWin() {
		return playerWin;
	}

	public boolean isPlayerPush() {
		return playerPush;
	}		
	
}
