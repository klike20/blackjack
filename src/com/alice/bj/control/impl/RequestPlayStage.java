package com.alice.bj.control.impl;

import com.alice.bj.model.Player;
import com.alice.bj.control.Stage;

/*
 * Another approach for this Stage is to treat each
 * Phase not as a method but as a class which implement the
 * phase interface
 * 
 * So, in case a new Phase is included is a matter to add the new
 * interface implementation and add it to the pool of phases
 * 
 */
public class RequestPlayStage extends Stage{
	
	private boolean hit(Player p, int deckIndex) {
		
		p.addCard(deckIndex, deck.pop());
		
		
		if (p.getDeck(deckIndex).sum() > 21) {
			dealer.amount = dealer.amount + p.getDeck(deckIndex).bet;
			
			p.reInitDeck(deckIndex);
			
			return false;//continue: askBet //vuelve a empezar (pasa al siguiente player actually)
		}
		return true;//continue: requestPlay
	}
	
	private void stand(Player p, int deckIndex) {
		p.getDeck(deckIndex).stand = true;
	}
	
	private void doubleDown(Player p, int deckIndex) {
		int bet = p.getDeck(deckIndex).bet;
		
		if (p.amount > bet) {
			p.amount = p.amount - bet;
			
			p.addBet(deckIndex, bet);
			
			if (hit(p,deckIndex)) {
				stand(p,deckIndex);
			}
	
		} else {
			new IllegalStateException("this play is not possible, insuffient amount");
		}
	}
	
	private void split(Player p, int deckIndex) {
		if (deckIndex == 0 && p.decks.keySet().size() == 1) {
			if (p.getDeck(deckIndex).cards.get(0).value == p.getDeck(deckIndex).cards.get(1).value) {
				
				if (p.amount > p.getDeck(deckIndex).bet) {
					p.amount = p.amount - p.getDeck(deckIndex).bet;
				
					p.createDeck(1, p.getDeck(deckIndex).bet);
					p.addCard(1, p.getDeck(deckIndex).cards.remove(1));
					
					//add cards to the new decks
					p.addCard(deckIndex, deck.pop());
					p.addCard(1, deck.pop());
					
					//continue requestPlay:
					
				} else {
					new IllegalStateException("this play is not possible, insuffient amount");
				}
			}
		}
	}
	
	private void surrender(Player p, int deckIndex) {
		p.amount = p.amount + p.getDeck(deckIndex).bet/2;
		
		dealer.amount = dealer.amount + p.getDeck(deckIndex).bet/2;
		
		p.reInitDeck(deckIndex);
	}

	@Override
	public boolean execute() {
		for (Player p : players) {
			for (int i = 0; i < p.decks.keySet().size(); i++) {
				int deckIndex = i;
				
				if (p.getDeck(deckIndex)!=null && !p.getDeck(deckIndex).stand) {
					int play = 1;//from console (1 hit, 2 stand, 3 double, 4 split, 5 surrender)
					
					switch (play) {
						case 1://hit
							hit(p,deckIndex);
						case 2://stand
							stand(p,deckIndex);
						case 3://double
							doubleDown(p,deckIndex);
						case 4://split
							split(p,deckIndex);
						case 5: //surrender
							surrender(p,deckIndex);
					}
				}
			}
		}
		return true;
	}
}
