package com.alice.bj.control.impl;

import com.alice.bj.control.Stage;

public class FirstCardDealerStage extends Stage{
	@Override
	public boolean execute(){
		dealer.createDeck(0, 0);
		dealer.addCard(0, deck.pop());
		return true;
	}
}
