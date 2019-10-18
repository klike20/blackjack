package com.alice.bj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.alice.bj.control.Stage;
import com.alice.bj.control.impl.*;
import com.alice.bj.model.Card;
import com.alice.bj.model.Player;

public class BlackJack {
	
	public void play() {
		Stack<Card> deck = new Stack<>();
		
		List<Player> players = Arrays.asList(new Player(100), new Player(100));
		
		Player dealer = new Player(1000);
		
		
		/*
		 * Some considerations:
		 * 
		 * The idea is to create a flow of stages depending of the 
		 * situations of the game 
		 * (from one stage the game can move to a next stage)
		 * 
		 * The abstraction is based on the different situations (stages)
		 * of the game
		 * 
		 * To make it more explicit (more verbose in terms of the business logic)
		 * a higher amount of stages were consider, but to make it more 
		 * practical in terms of development the amount could be reduced
		 * to 4 stages
		 * 
		 * Another abstraction to reduce the complexity and increase
		 * the object oriented model (increasing decoupling and cohesion)
		 * is to use a DTO like ConfigData object which has as properties
		 * the different entities of the model: players, dealer, deck (cards)
		 * and use this DTO to init the different stages instead of pass as
		 * parameters each of this entities
		 * 
		 * With this approach the api will not be changed if a new
		 * game entity is included but only the implementations
		 * 
		 */
			Map<String,Stage> stages = new HashMap<>();
			
			InitDeckStage initDeckStage = new InitDeckStage();
			AskForBetStage askForBetStage = new AskForBetStage();
			FirstCardStage firstCardStage = new FirstCardStage();
			FirstCardDealerStage firstCardDealerStage = new FirstCardDealerStage();
			SecondCardStage secondCardStage = new SecondCardStage();
			SecondCardDealerStage secondCardDealerStage = new SecondCardDealerStage();
			DealerHandReviewStage dealerHandReviewStage = new DealerHandReviewStage();
			RequestPlayStage requestPlayStage = new RequestPlayStage();
			DealerPlayStage dealerPlayStage = new DealerPlayStage();
			
			initDeckStage.init(players, dealer, deck, askForBetStage, stages);
			askForBetStage.init(players, dealer, deck, firstCardStage, stages);
			firstCardStage.init(players, dealer, deck, firstCardDealerStage, stages);
			firstCardDealerStage.init(players, dealer, deck, secondCardStage, stages);
			secondCardStage.init(players, dealer, deck, secondCardDealerStage, stages);
			secondCardDealerStage.init(players, dealer, deck, dealerHandReviewStage, stages);
			dealerHandReviewStage.init(players, dealer, deck, requestPlayStage, stages);
			requestPlayStage.init(players, dealer, deck, dealerPlayStage, stages);

			/*
			 * the next stage is null in this case because the idea
			 * was to have a graph with transitions (automat)
			 * but this will cause a possible stack issue as the connection
			 * is through method invocations
			 * 
			 * for that reason the flow will go until the last stage
			 * and then the execution starts again from the first stage
			 * if the required conditions are match
			 */
			dealerPlayStage.init(players, dealer, deck, null, stages);
			
			stages.put("initDeckStage", initDeckStage);
			stages.put("askForBetStage", askForBetStage);
			stages.put("firstCardStage", firstCardStage);
			stages.put("firstCardDealerStage", firstCardDealerStage);
			stages.put("secondCardStage", secondCardStage);
			stages.put("secondCardDealerStage", secondCardDealerStage);
			stages.put("dealerHandReviewStage", dealerHandReviewStage);
			stages.put("requestPlayStage", requestPlayStage);
			stages.put("dealerPlayStage", dealerPlayStage);
			
			//Init game
			while (initDeckStage.run()) {
			}
	}

	public static void main(String[] args) {
		new BlackJack().play();
	}
}
