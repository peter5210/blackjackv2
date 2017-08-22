package com.libertymutual.blackjackv2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	//	boolean playerWin;
	//	public boolean playerWin() {
	//		int dealerFirstTotal = dealer.totalCardValue()[0];
	//		int dealerSecondTotal = dealer.totalCardValue()[1];
	//		int playerFirstTotal = playerOne.totalCardValue()[0];
	//		int playerSecondTotal = playerOne.totalCardValue()[1];
	//		
	//		if (dealerFirstTotal > playerFirstTotal || dealerFirstTotal > playerSecondTotal) {		
	//			if (dealerSecondTotal > playerFirstTotal || dealerSecondTotal > playerSecondTotal) {
	//				return playerWin = false;
	//			} 
	//		}
	//		return playerWin = true;
	//	}


//	public boolean playerBlackjackWin;
//	public boolean playerWin;
//	public boolean playerPush;
//
//	public boolean whoWon() {
//		int dealerFirstTotal = dealer.totalCardValue()[0];
//		int dealerSecondTotal = dealer.totalCardValue()[1];
//		int playerFirstTotal = playerOne.totalCardValue()[0];
//		int playerSecondTotal = playerOne.totalCardValue()[1];
//
//		if ((playerFirstTotal == 21 || playerSecondTotal == 21) && (dealerFirstTotal < 21 && dealerSecondTotal < 21)) {
//			playerBlackjackWin = true;
//		}
//		if ((dealerFirstTotal == 21 || dealerSecondTotal == 21) && (playerFirstTotal == 21 || playerSecondTotal == 21)) {
//			playerPush = true;
//		}
//		if (dealerFirstTotal > playerFirstTotal || dealerFirstTotal > playerSecondTotal) {		
//			if (dealerSecondTotal > playerFirstTotal || dealerSecondTotal > playerSecondTotal) {
//				playerWin = false;
//			}
//		}		
//		return playerWin = true;
//	} 

	public int payout() {
		if (dealer.playerBlackjackWin) {
			wallet.payoutForBlackjackWin();
			} if (dealer.playerPush); {
				wallet.payoutForPush();
				} if (!dealer.playerWin); {
					 wallet.payoutForWin();
					} return wallet.payoutForLoss();
	
	}		


//landing page for the game, no bet has been made.
@GetMapping("/")
public String homePage(Model model) {
	model.addAttribute("Balance", wallet.getbalance());
	return "blackjack2/blackjackdefault";

}

//bet was placed
@PostMapping("betStart")
public ModelAndView startAGame(int betAmount) {
	ModelAndView modelAndView = new ModelAndView("blackjack2/startpage");
	modelAndView.addObject("remainingBalance", wallet.getStartingBalance(betAmount));
	modelAndView.addObject("bet", wallet.getbet());
	return modelAndView;	

}		

//The user has submitted a bet, and cards are dealt
@PostMapping("startGame")
public ModelAndView readyPlayerOne() {

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
		
		//This is mandatory for the game over form
		modelAndView.addObject("remainingBalance", wallet.getbalance());
		modelAndView.addObject("dealersHand", dealer.getHand());
		modelAndView.addObject("playersHand", playerOne.getHand());
		modelAndView.addObject("Busted", "Busted");
		modelAndView.addObject("balance", wallet.payoutForLoss());

		return modelAndView;	
		}
			{
			ModelAndView modelAndView = new ModelAndView("blackjack2/playerturn");
			modelAndView.addObject("remainingBalance", wallet.getbalance());
			modelAndView.addObject("dealersHoleCard", dealer.getFirstCard());
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
	
	//If the player busts
	if (firstNumber > 21 && secondNumber > 21) {
		ModelAndView modelAndView = new ModelAndView("blackjack2/gameover");
		
		//details about who won the game
		modelAndView.addObject("blackJackWin", dealer.playerBlackjackWin);
		modelAndView.addObject("playerStandardWin", dealer.playerWin);
		modelAndView.addObject("playerPush", dealer.playerPush);
		
		
		//This is mandatory for the game over form
		modelAndView.addObject("remainingBalance", wallet.getbalance());
		modelAndView.addObject("dealersHand", dealer.getHand());
		modelAndView.addObject("playersHand", playerOne.getHand());
		modelAndView.addObject("Busted", "Busted");
		modelAndView.addObject("balance", wallet.payoutForLoss());

		return modelAndView;
	}
	
	ModelAndView modelAndView = new ModelAndView("blackjack2/playerturn");
	modelAndView.addObject("remainingBalance", wallet.getbalance());
	modelAndView.addObject("dealersHoleCard", dealer.getFirstCard());
	modelAndView.addObject("playersHand", playerOne.getHand());
	return modelAndView;

}


@PostMapping("stand")
public ModelAndView playerStand() {

	//this is for the dealer to keep hitting until they are on or above 17
	int firstNumber = dealer.totalCardValue()[0];
	int secondNumber = dealer.totalCardValue()[1];
	
	while (firstNumber < 17 && secondNumber < 17) {
		Card card = deck.removeCard();
		dealer.takeCard(card);
	}

	if (!dealer.dealerBust()); {
		ModelAndView modelAndView = new ModelAndView("blackjack2/gameover");
		//This is mandatory for the game over form
		modelAndView.addObject("dealersHand", dealer.getHand());
		modelAndView.addObject("playersHand", playerOne.getHand());
		modelAndView.addObject("Busted", "Dealer Busted You WIN");
		modelAndView.addObject("balance", wallet.payoutForWin());
		
		//payout
		modelAndView.addObject("balance", wallet.payoutForWin());
			
		//extra details needed for the form
		modelAndView.addObject("blackJackWin", "");
		modelAndView.addObject("playerStandardWin", dealer.playerWin);
		modelAndView.addObject("playerPush", "");
		return modelAndView;
	}
		if (dealer.playerWin);
		ModelAndView modelAndView = new ModelAndView("blackjack2/gameover");
		//This is mandatory for the game over form
		modelAndView.addObject("dealersHand", dealer.getHand());
		modelAndView.addObject("playersHand", playerOne.getHand());
		modelAndView.addObject("Busted", "");
		modelAndView.addObject("balance", wallet.payoutForWin());
		
		//payout
		modelAndView.addObject("balance", wallet.payoutForWin());
					
		//extra details needed for the form
		modelAndView.addObject("blackJackWin", dealer.playerBlackjackWin);
		modelAndView.addObject("playerStandardWin", dealer.playerWin);
		modelAndView.addObject("playerPush", dealer.playerPush);
		return modelAndView;
}

	
	
	

@PostMapping("playAgain")
public String playAgain() {
	//Need to clear the players hand after game over
	dealer.clearHand(); 
	playerOne.clearHand();
	return "redirect:/";
}

}
