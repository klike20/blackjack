package com.alice.bj.control.impl;

import com.alice.bj.model.Card;
import com.alice.bj.control.Stage;

public class SecondCardDealerStage extends Stage{
	@Override
	public boolean execute() {
		Card card = deck.pop();
		card.unTap();
		dealer.addCard(0,card);
		return true;
	}
}
