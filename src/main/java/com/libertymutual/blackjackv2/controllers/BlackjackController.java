package com.libertymutual.blackjackv2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class BlackjackController {

	@GetMapping("/blackjack2")
	public String startAGame() {
		return "blackjack2/startpage";
	}		
	
	@PostMapping("startGame")
	public String readyPlayerOne() {
		return "blackjack2/playerturn";
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
