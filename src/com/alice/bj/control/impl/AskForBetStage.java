package com.alice.bj.control.impl;

import com.alice.bj.control.Stage;
import com.alice.bj.model.Player;

public class AskForBetStage extends Stage{
	@Override
	public boolean execute() {
		for (Player p : players) {
			
			/*
			 * this logic could be more complex using 
			 * 
			 * an approach of reduce the bet
			 */
			int bet = 10;//getFromConsole();
			
			if (p.amount > bet) {
				p.amount = p.amount - bet;
				
				p.roundBet = bet;
				
			} else {
				players.remove(p);
			}
		}
		if (players.size() >0) {
			return true;
		} else {
			return false;
		}
	}
}
