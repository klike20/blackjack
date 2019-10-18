package com.alice.bj.control.impl;

import com.alice.bj.model.Player;
import com.alice.bj.control.Stage;

public class SecondCardStage extends Stage{
	@Override
	public boolean execute() {
		for (Player p : players) {
            p.addCard(0,deck.pop());
		}
		return true;
	}
}
