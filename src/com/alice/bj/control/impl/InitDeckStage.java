package com.alice.bj.control.impl;

import java.util.Collections;
import java.util.Stack;

import com.alice.bj.control.Stage;
import com.alice.bj.model.Card;

public class InitDeckStage extends Stage{
	@Override
	public boolean execute() {
		if (dealer.amount > playersPossibleBet() && players.size() > 0) {
			
			refillDeck(deck);
			Collections.shuffle(deck);
			for (Card card : deck) {
				card.tap = true;
			}
			return true;
		} else {
			return false;
		}
	}
	
	private int playersPossibleBet() {
		return 0;// This validation is more complex, but for practical purpose
	}
	
	private void refillDeck(Stack deck) {
		deck.clear();
		//add the cards to the deck
	}
}
