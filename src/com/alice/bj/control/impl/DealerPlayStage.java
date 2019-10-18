package com.alice.bj.control.impl;

import com.alice.bj.model.Player;
import com.alice.bj.control.Stage;

public class DealerPlayStage extends Stage{
	@Override
	public boolean execute() {
		dealer.getDeck(0).cards.get(0).unTap();
		
		while (dealer.getDeck(0).sum()<17) {
			dealer.getDeck(0).addCard(deck.pop());
		}
		
		for (Player p : players) {
			for (int i = 0; i < p.decks.keySet().size(); i++) {
				if (p.getDeck(i)!=null) {
					if (dealer.getDeck(0).sum() > 21) {
						p.amount = p.amount + 2*p.getDeck(i).bet;
						//take out the money from the dealer
						dealer.amount = dealer.amount - p.getDeck(i).bet;
					} else if (dealer.getDeck(0).sum() == 21) {
						if (p.getDeck(i).sum()==21) {
							//return the bet
							p.amount = p.amount + p.getDeck(i).bet;
						} else {
							// get the money from the deck bet
							dealer.amount = dealer.amount + p.getDeck(i).bet;
						}
					} else if (dealer.getDeck(0).sum() >=17 && dealer.getDeck(0).sum() < 21) {
						if (p.getDeck(i).sum() > dealer.getDeck(0).sum()) {
							p.amount = p.amount + 2*p.getDeck(i).bet;
							//take out the money from the dealer
							dealer.amount = dealer.amount - p.getDeck(i).bet;
						} else if (p.getDeck(i).sum() == dealer.getDeck(0).sum()) {
							p.amount = p.amount + p.getDeck(i).bet;
						} else if (p.getDeck(i).sum() < dealer.getDeck(0).sum()) {
							// get the money from the deck bet
							dealer.amount = dealer.amount + p.getDeck(i).bet;
						}
					}
					p.reInitDeck(i);
				}
			}
		}
		return true;
	}
}
