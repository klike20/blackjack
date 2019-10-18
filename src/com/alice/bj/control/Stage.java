package com.alice.bj.control;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.alice.bj.model.Card;
import com.alice.bj.model.Player;
import com.alice.bj.control.Stage;

abstract public class Stage {
	protected Stage nextStage;
	
	protected List<Player> players;
	
	protected Player dealer;
	
	protected Stack<Card> deck;
	
	protected Map<String,Stage> stages;
	
	public void init(
			List<Player> players, Player dealer, 
			Stack<Card> deck, Stage nextStage, Map<String,Stage> stages) {
		this.players = players;
		this.dealer = dealer;
		this.deck = deck;
		this.nextStage = nextStage;
		this.stages = stages;
	}
	
    public abstract boolean execute();
    
    public boolean run() {
    		boolean running = execute();
    		if (running) {
    			goToNextStage();
    		}
    		return running;
    }
    
    public void goToNextStage() {
	    	if (nextStage!=null) {
	    	  nextStage.run();
	    	}
    }
}
