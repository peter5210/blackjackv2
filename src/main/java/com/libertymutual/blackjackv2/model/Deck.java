package com.libertymutual.blackjackv2.model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
		
	private ArrayList<Card> deck = new ArrayList<Card>();	
	public String suits[];
	public String faceCards[];
	
	public Deck() {
		
		String[] suits = {"Clubs", "Hearts", "Spades", "Hearts"};
		String[] faceCards = {"J", "Q", "K"};
				
		for (String s : suits) {
			AceCard aceCard = new AceCard(s);
			deck.add(aceCard);
			for (String dv : faceCards) {
				FaceCard faceCard = new FaceCard(s, dv);
				deck.add(faceCard);
			}
			for (int i = 2; i < 11; i += 1) {
				NumberCard numberCard = new NumberCard(s, i);
				deck.add(numberCard);
			}
		}
	
		Collections.shuffle(deck);
	}

	public Card removeCard() {
		return deck.remove(0);
	}
	
}
