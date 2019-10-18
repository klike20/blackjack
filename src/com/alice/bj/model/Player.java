package com.alice.bj.model;

import java.util.HashMap;
import java.util.Map;

import com.alice.bj.model.Card;
import com.alice.bj.model.Deck;

public class Player {

	public int amount;//should use getter
	
	public int roundBet;//should use getter
	
	public Player(int amount) {
		this.amount = amount;
	}
	
	public Map<Integer,Deck> decks = new HashMap<>();

	public void createDeck(int index, int bet) {
		Deck deck = new Deck();
		deck.bet = bet;
		decks.put(index, deck);
	}
	
	public void addBet(int index, int bet) {
		decks.get(index).bet = decks.get(index).bet + bet;
	}
	
	public void reInitDeck(int index) {
		decks.remove(index);
	}
	
	public void addCard(int deckIndex, Card card) {
		if (decks.get(deckIndex)!=null) {
		decks.get(deckIndex).addCard(card);
		} else {
			new IllegalStateException("the deck does not exists");
		}
	}
	
	public Deck getDeck(int index) {
		return decks.get(index);
	}
}
