package com.alice.bj.control.impl;

import com.alice.bj.model.Player;
import com.alice.bj.control.Stage;

public class DealerHandReviewStage extends Stage{
	@Override
	public boolean execute() {
		if (dealer.getDeck(0).sum() == 21) {
			for (Player p : players) {
				//Se mira si el jugador tiene tambien tiene 21
				if (p.getDeck(0).sum() == 21) {
					
					p.amount = p.amount + p.getDeck(0).bet;// recupera lo apostado
					
				} else {
					//darle la plata al dealer
					dealer.amount = dealer.amount + p.getDeck(0).bet;
				}
			}
			//stages.get("InitDeckStage").execute();//continue: askBet //vuelve a empezar (pasa al siguiente player actually)
			return false;
		}
		return true;
	}
}
