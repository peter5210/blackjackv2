package com.libertymutual.blackjackv2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.libertymutual.blackjackv2.model.Wallet;

@Controller
@RequestMapping
public class BlackjackController {	

	Wallet wallet;
	
	public BlackjackController() {
		wallet = new Wallet();
	}
	
	@GetMapping("/blackjack2")
	public String startAGame() {
		return "blackjack2/startpage";
	}		
	
	@PostMapping("/startGame")
	public ModelAndView readyPlayerOne(int betAmount) {

		int bet = betAmount;
				
		ModelAndView modelAndView = new ModelAndView("blackjack2/playerturn");
		modelAndView.addObject("remainingBalance", wallet.howMuchDoIHaveLeft(bet));
		modelAndView.addObject("startingBalance", wallet.getStartingBalance());
		return modelAndView;	
	}
	
	@PostMapping("hit")
	public String playerHit() {
		return "blackjack2/playerturn";
	}
	
	@PostMapping("stand")
	public String playerStand() {
		return "blackjack2/dealerturn";
	}
	
	@PostMapping("playAgain")
	public String playAgain() {
		return "redirect:/blackjack2";
	}
	
	

	
}
