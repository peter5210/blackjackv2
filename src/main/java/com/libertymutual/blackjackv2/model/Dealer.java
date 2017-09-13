package com.libertymutual.blackjackv2.model;

import java.util.ArrayList;

public class Dealer {

	ArrayList<Card> cards;
	public boolean playerBlackjackWin;
	public boolean playerWin;
	public boolean playerPush;

	int firstNumber;
	int secondNumber;

	public Dealer() {
		cards = new ArrayList<Card>();
	}

	public void takeCard(Card card) {
		cards.add(card);
		firstNumber = totalCardValue()[0];
		secondNumber = totalCardValue()[1];
	}

	public ArrayList<Card> getHand() {
		return cards;
	}

	public Card getFirstCard() {
		return cards.get(0);
	}

	public void clearHand() {
		cards.clear();
		dealerBust = false;
		playerBlackjackWin = false;
		playerPush = false;
		playerWin = false;
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

	boolean dealerBust;

	public boolean dealerBust() {
		if (firstNumber > 21 && secondNumber > 21) {
			dealerBust = true;
		}
		return dealerBust;
	}

	public boolean playerWin(PlayerOne playerOne) {
		int dealerFirstTotal = totalCardValue()[0];
		int dealerSecondTotal = totalCardValue()[1];
		int playerFirstTotal = playerOne.totalCardValue()[0];
		int playerSecondTotal = playerOne.totalCardValue()[1];

		if ((playerFirstTotal == 21 || playerSecondTotal == 21) && (dealerFirstTotal < 21 && dealerSecondTotal < 21)) {
			return playerBlackjackWin = true;
		}
		if ((dealerFirstTotal == 21 || dealerSecondTotal == 21)
				&& (playerFirstTotal == 21 || playerSecondTotal == 21)) {
			return playerPush = true;
		}
		if (!dealerBust()) {
			if (dealerFirstTotal > playerFirstTotal || dealerFirstTotal > playerSecondTotal) {
				if (dealerSecondTotal > playerFirstTotal || dealerSecondTotal > playerSecondTotal) {
					return playerWin = false;
				}
			} 
		}
		if (playerFirstTotal > 21 && playerSecondTotal > 21) {
			return playerWin = false;
		}
			
		return playerWin = true;
	}

	public int payout(Wallet wallet) {
		if (playerBlackjackWin) {
			return wallet.payoutForBlackjackWin();
		}
		if (playerPush) {
			return wallet.payoutForPush();
		}
		if (playerWin) {
			return wallet.payoutForWin();
		}
		return wallet.payoutForLoss();

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
