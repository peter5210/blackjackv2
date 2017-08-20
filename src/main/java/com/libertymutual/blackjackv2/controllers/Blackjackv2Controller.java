package com.libertymutual.blackjackv2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.libertymutual.blackjackv2.model.Card;
import com.libertymutual.blackjackv2.model.Dealer;
import com.libertymutual.blackjackv2.model.Deck;
import com.libertymutual.blackjackv2.model.PlayerOne;
import com.libertymutual.blackjackv2.model.Wallet;

@Controller
@RequestMapping
public class Blackjackv2Controller {	

	Wallet wallet;
	Deck deck;
	PlayerOne playerOne;
	Dealer dealer;


	public Blackjackv2Controller() {
		wallet = new Wallet();
		deck = new Deck();
		playerOne = new PlayerOne();
		dealer = new Dealer();
		
	}
	
	boolean playerWin;
	public boolean playerWin() {
		int dealerFirstTotal = dealer.totalCardValue()[0];
		int dealerSecondTotal = dealer.totalCardValue()[1];
		int playerFirstTotal = playerOne.totalCardValue()[0];
		int playerSecondTotal = playerOne.totalCardValue()[1];
		
		if (dealerFirstTotal > playerFirstTotal || dealerFirstTotal > playerSecondTotal) {		
			if (dealerSecondTotal > playerFirstTotal || dealerSecondTotal > playerSecondTotal) {
				return playerWin = false;
			} 
		}
		return playerWin = true;
	}
	
	
	//landing page for the game...waiting on a bet to start
	@GetMapping("/blackjack2")
	public String startAGame() {
		return "blackjack2/startpage";
	}		

	//The user has submitted a bet, and cards are dealt
	@PostMapping("startGame")
	public ModelAndView readyPlayerOne(int betAmount) {

		//Take the first card for each player
		Card card = deck.removeCard();
		playerOne.takeCard(card);

		card = deck.removeCard();
		dealer.takeCard(card);

		//Take the second card for each player
		card = deck.removeCard();
		playerOne.takeCard(card);

		card = deck.removeCard();
		dealer.takeCard(card); 
		
				
		int firstNumber;
		int secondNumber;
		firstNumber = playerOne.totalCardValue()[0];
		secondNumber = playerOne.totalCardValue()[1];


		if (firstNumber > 21 && secondNumber > 21) {  
			ModelAndView modelAndView = new ModelAndView("blackjack2/gameover");
			return modelAndView;	
		}
		{
			ModelAndView modelAndView = new ModelAndView("blackjack2/playerturn");
			modelAndView.addObject("remainingBalance", wallet.howMuchDoIHaveLeft(betAmount));
			modelAndView.addObject("startingBalance", wallet.getStartingBalance());
			modelAndView.addObject("dealersHand", dealer.getHand());
			modelAndView.addObject("playersHand", playerOne.getHand());
			modelAndView.addObject("bet", wallet.getbet());
			return modelAndView;	
		}
	}

	@PostMapping("hit")
	public ModelAndView playerHit() {

		Card card = deck.removeCard();
		playerOne.takeCard(card);

		//This is all to determine if playerOne lost

		int firstNumber = playerOne.totalCardValue()[0];
		int secondNumber = playerOne.totalCardValue()[1];

		if (firstNumber > 21 && secondNumber > 21) {
			ModelAndView modelAndView = new ModelAndView("blackjack2/gameover");
			modelAndView.addObject("playerWin", playerWin);
			modelAndView.addObject("playerLost", !playerWin);
			modelAndView.addObject("remainingBalance", wallet.howMuchDoIHaveLeft(wallet.getbet()));
			modelAndView.addObject("dealersHand", dealer.getHand());
			modelAndView.addObject("playersHand", playerOne.getHand());
			return modelAndView;
		}

		ModelAndView modelAndView = new ModelAndView("blackjack2/playerturn");
		modelAndView.addObject("remainingBalance", wallet.howMuchDoIHaveLeft(wallet.getbet()));
		modelAndView.addObject("startingBalance", wallet.getStartingBalance());
		modelAndView.addObject("dealersHand", dealer.getHand());
		modelAndView.addObject("playersHand", playerOne.getHand());
		return modelAndView;
		
		
	}


	@PostMapping("stand")
	public ModelAndView playerStand() {

		//this is for the dealer to keep hitting until they are on or above 17
		int firstNumber = dealer.totalCardValue()[0];
		int secondNumber = dealer.totalCardValue()[1];
		
		
		while ( firstNumber < 17 && secondNumber < 17) {
			Card card = deck.removeCard();
			dealer.takeCard(card);
			firstNumber = dealer.totalCardValue()[0];
			secondNumber = dealer.totalCardValue()[1];
		}
		
		//This determines if the player won
		if (playerWin = true) {
			ModelAndView modelAndView = new ModelAndView("blackjack2/gameover");
			modelAndView.addObject("playerWin", playerWin);
			modelAndView.addObject("playerLost", !playerWin);
			modelAndView.addObject("remainingBalance", wallet.howMuchDoIHaveLeft(wallet.getbet()));
			modelAndView.addObject("startingBalance", wallet.getStartingBalance());
			modelAndView.addObject("dealersHand", dealer.getHand());
			modelAndView.addObject("playersHand", playerOne.getHand());
			return modelAndView;
		
			//This is all to determine if the dealer busted		
		}  if (firstNumber > 21 && secondNumber > 21) {
			  ModelAndView modelAndView = new ModelAndView("blackjack2/gameover");
			  modelAndView.addObject("remainingBalance", wallet.howMuchDoIHaveLeft(wallet.getbet()));
			  modelAndView.addObject("startingBalance", wallet.getStartingBalance());
			  modelAndView.addObject("dealersHand", dealer.getHand());
			  modelAndView.addObject("playersHand", playerOne.getHand());
			  return modelAndView;
			
		} 

		ModelAndView modelAndView = new ModelAndView("blackjack2/playerturn");
		modelAndView.addObject("remainingBalance", wallet.howMuchDoIHaveLeft(wallet.getbet()));
		modelAndView.addObject("startingBalance", wallet.getStartingBalance());
		modelAndView.addObject("dealersHand", dealer.getHand());
		modelAndView.addObject("playersHand", playerOne.getHand());
		return modelAndView;
	}

	@PostMapping("playAgain")
	public String playAgain() {
		//Need to clear the players hand after game over
		dealer.clearHand(); 
		playerOne.clearHand();
		return "redirect:/blackjack2";
	}

}
