package com.alice.bj.model;

import java.util.ArrayList;
import java.util.List;

import com.alice.bj.model.Card;

public class Deck {
	public List<Card> cards = new ArrayList<>();
	public int bet = 0;
	public boolean stand = false;
	public int sum() {
		int sum = 0;
		for (Card card : cards) {
			sum = sum + card.getValue();
		}
		return sum;
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
}
