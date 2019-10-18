package com.alice.bj.control.impl;

import com.alice.bj.model.Card;
import com.alice.bj.model.Player;
import com.alice.bj.control.Stage;

public class FirstCardStage extends Stage{
	@Override
	public boolean execute() {
		for (Player p : players) {
            p.createDeck(0,p.roundBet);
            
            Card card = deck.pop();
            card.tap = false;
            
            p.addCard(0,card);
		}
		
		return true;
	}
}
