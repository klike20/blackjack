package com.alice.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alice.bj.BlackJack.Player;

public class Bfs {
    /*
             ___A___
          __B__     C__
        _D    _E_     _F_
       G     H   I   J   K
    
    Expected output: A-B-C-D-E-F-G-H-I-J-K
    */
    public static void main(String[] args) {
        Node G = new Node("G");
        Node H = new Node("H");
        Node I = new Node("I");
        Node J = new Node("J");
        Node K = new Node("K");
Node D = new Node("D", G);
        Node E = new Node("E", H, I);
        Node F = new Node("F", J, K);
        Node B = new Node("B", D, E);
        Node C = new Node("C", F);
        Node A = new Node("A", B, C);        
        // here you go
        
        bfs(A);        
    }
    
    static Map<Integer,List<String>> order = new HashMap<>(); 
    
    private static void bfs(Node n) {
    		loop(n,1);
    		for (int i = 1 ; i <= order.keySet().size(); i++) {
    			System.out.print(order.get(i));
    		}
    }
    
    private static void loop(Node n, int level) {
    		addNode(order, level, n.name);
    		for (Node child : n.children) {
    			loop (child, level + 1);
    		}
    }
    
    private static void addNode(Map<Integer,List<String>> order, int level, String name){
    		if (order.get(level) != null) {
    			order.get(level).add(name);
    		} else {
    			List<String> list = new ArrayList();
    			list.add(name);
    			order.put(level, list);
    		}
    }
    
    
    private static class Node {
        String name;
        List<Node> children;
        
        public Node(String name, Node... children) {
            this.name = name;
            this.children = Arrays.asList(children);
        }
    }    
}






//for (Player p : players) {
//	for (int i = 0; i < p.decks.keySet().size(); i++) {
//		int deckIndex = i;
//		
//		//se le pregunta al jugador que hacer
//		int play = 1;//from console que quiere hacer (1 hit, 2 stand, 3 double, 4 split, 5 surrender)
//		
//		switch (play) {
//		case 1://hit
//			hit(p,deckIndex);
////			hit:
////			//Se le da una carta mas al jugador
////			p.addCard(deckIndex, deck.pop());
////			
////			//si se pasa de 21 termina el juego
////			if (p.getDeck(deckIndex).sum() > 21) {
////				//darle la plata al dealer
////				dealer.amount = dealer.amount + p.getDeck(deckIndex).bet;
////				
////				
////				//retirar jugador
////				
////				//reiniciar el deck del jugador
////				p.reInitDeck(deckIndex);
////				
////				//continue: askBet //vuelve a empezar (pasa al siguiente player actually)
////				
////			}
////			
////			//continue: requestPlay
//		case 2://stand
//			stand();
//			
//		case 3://double
//			doubleDown(p,deckIndex);
////			bet = p.getDeck(deckIndex).bet;
////			
////			if (p.amount > bet) {
////				p.amount = p.amount - bet;
////				
////				p.addBet(deckIndex, bet);
////				
////				hit();
////				
////				stand();
////				//continue hit;
////			}
//			
//		case 4://split
//			split(p,deckIndex);
////			if (deckIndex == 0 && p.decks.keySet().size() == 1) {// por ahora solo un split
////				//verificar que se pueda
////				if (p.getDeck(deckIndex).cards.get(0).value == p.getDeck(deckIndex).cards.get(1).value) {
////					
////					if (p.amount > p.getDeck(deckIndex).bet) {
////						p.amount = p.amount - p.getDeck(deckIndex).bet;
////					
////						p.createDeck(1, p.getDeck(deckIndex).bet);
////						p.addCard(1, p.getDeck(deckIndex).cards.remove(1));
////						
////						//add cards to the new decks
////						p.addCard(deckIndex, deck.pop());
////						p.addCard(1, deck.pop());
////						
////						//coontinue requestPlay:
////						
////					} else {
////						new IllegalStateException("this play is not possible, insuffient amount");
////					}
////				}
////			}
//			
//		case 5: //surrender
//			surrender(p,deckIndex);
////			//devolver la mitad de la apuesta
////			p.amount = p.amount + p.getDeck(deckIndex).bet/2;
////			
////			//darle la otra mitad al dealer
////			dealer.amount = dealer.amount + p.getDeck(deckIndex).bet/2;
////			
////			//re iniciar el deck
////			p.reInitDeck(deckIndex);
//		}
//	}
//}


//package com.alice.bj;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Stack;
//
//public class BlackJack {
//	
//	class Card {
//		private String name;
//		private int value;
//		private boolean tap;
//		
//		public void unTap() {
//			tap = false;
//		}
//		
//		public String getName() {
//			if (tap) {
//				return "";
//			}
//			return name;
//		}
//		
//		public int getValue() {
//			if (tap) {
//				new IllegalStateException("the value can not been seen");
//			}
//			return value;
//		}
//	}
//	
//	class Player {
//		
//		int amount;
//		
//		int roundBet;
//		
//		public Player(int amount) {
//			this.amount = amount;
//		}
//		
//		Map<Integer,Deck> decks = new HashMap<>();
//	
//		public void createDeck(int index, int bet) {
//			Deck deck = new Deck();
//			deck.bet = bet;
//			decks.put(index, deck);
//		}
//		
//		public void addBet(int index, int bet) {
//			decks.get(index).bet = decks.get(index).bet + bet;
//		}
//		
//		public void reInitDeck(int index) {
//			decks.remove(index);
//		}
//		
//		public void addCard(int deckIndex, Card card) {
//			if (decks.get(deckIndex)!=null) {
//			decks.get(deckIndex).addCard(card);
//			} else {
//				new IllegalStateException("the deck does not exists");
//			}
//		}
//		
//		public Deck getDeck(int index) {
//			return decks.get(index);
//		}
//	}
//	
//	class Deck {
//		List<Card> cards = new ArrayList<>();
//		int bet = 0;
//		boolean stand = false;
//		public int sum() {
//			int sum = 0;
//			for (Card card : cards) {
//				sum = sum + card.value;
//			}
//			return sum;
//		}
//		
//		public void addCard(Card card) {
//			cards.add(card);
//		}
//	}
//	
//	abstract class Stage {
//		
//		Stage stage;
//		
//		List<Player> players;
//		
//		Player dealer;
//		
//		Stack<Card> deck;
//		
//		Map<String,Stage> stages;
//		
//		public void init(
//				List<Player> players, Player dealer, 
//				Stack<Card> deck, Stage stage, Map<String,Stage> stages) {
//			this.players = players;
//			this.dealer = dealer;
//			this.deck = deck;
//			this.stage = stage;
//			this.stages = stages;
//		}
//		
//	    abstract boolean execute();
//	    
//	    public boolean run() {
//	    		boolean running = execute();
//	    		if (running) {
//	    			goToNextStage();
//	    		}
//	    		return running;
//	    }
//	    
//	    public void goToNextStage() {
//		    	if (stage!=null) {
//		    	  stage.execute();
//		    	}
//	    }
//	}
//
//	class InitDeckStage extends Stage {
//		@Override
//		public boolean execute() {
//			if (dealer.amount > playersPossibleBet() && players.size() > 0) {
//				deck.clear();
//				refillDeck(deck);
//				Collections.shuffle(deck);
//				for (Card card : deck) {
//					card.tap = true;
//				}
//				return true;
//			} else {
//				return false;
//			}
//		}
//		
//		private int playersPossibleBet() {
//			return 0;// This validation is more complex, but for practical purpose
//		}
//		
//		private void refillDeck(Stack deck) {
//			//add the cards to the deck
//		}
//	}
//	
//	class AskForBetStage extends Stage {
//		@Override
//		public boolean execute() {
//			for (Player p : players) {
//				
//				/*
//				 * this logic could be more complex using 
//				 * 
//				 * an approach of reduce the bet
//				 */
//				int bet = 10;//getFromConsole();
//				
//				if (p.amount > bet) {
//					p.amount = p.amount - bet;
//					
//					p.roundBet = bet;
//					
//				} else {
//					players.remove(p);
//				}
//			}
//			if (players.size() >0) {
//				return true;
//			} else {
//				return false;
//			}
//		}
//	}
//
//	class FirstCardStage extends Stage {
//		@Override
//		public boolean execute() {
//			for (Player p : players) {
//                p.createDeck(0,p.roundBet);
//                
//                Card card = deck.pop();
//                card.tap = false;
//                
//                p.addCard(0,card);
//			}
//			
//			return true;
//		}
//	}
//	
//	class FirstCardDealerStage extends Stage {
//		@Override
//		public boolean execute() {
//			dealer.createDeck(0, 0);
//			dealer.addCard(0, deck.pop());
//			return true;
//		}
//	}
//
//	class SecondCardStage extends Stage {
//		@Override
//		public boolean execute() {
//			for (Player p : players) {
//                p.addCard(0,deck.pop());
//			}
//			return true;
//		}
//	}
//	
//	class SecondCardDealerStage extends Stage {
//		@Override
//		public boolean execute() {
//			Card card = deck.pop();
//			card.unTap();
//			dealer.addCard(0,card);
//			return true;
//		}
//	}
//	
//	class DealerHandReviewStage extends Stage {
//		@Override
//		public boolean execute() {
//			if (dealer.getDeck(0).sum() == 21) {
//				for (Player p : players) {
//					//Se mira si el jugador tiene tambien tiene 21
//					if (p.getDeck(0).sum() == 21) {
//						
//						p.amount = p.amount + p.getDeck(0).bet;// recupera lo apostado
//						
//					} else {
//						//darle la plata al dealer
//						dealer.amount = dealer.amount + p.getDeck(0).bet;
//					}
//				}
//				//stages.get("InitDeckStage").execute();//continue: askBet //vuelve a empezar (pasa al siguiente player actually)
//				return false;
//			}
//			return true;
//		}
//	}
//
//	/**
//	 * Another approach for this Stage is to treat each
//	 * Phase not as a method but as a class which implement the
//	 * phase interface
//	 * 
//	 * So, in case a new Phase is included is a matter to add the new
//	 * interface implementation and add it to the pool of phases
//	 * 
//	 * @author luiscifuentes
//	 *
//	 */
//	class RequestPlayStage extends Stage {
//		
//		private boolean hit(Player p, int deckIndex) {
//			//Se le da una carta mas al jugador
//			p.addCard(deckIndex, deck.pop());
//			
//			//si se pasa de 21 termina el juego
//			if (p.getDeck(deckIndex).sum() > 21) {
//				//darle la plata al dealer
//				dealer.amount = dealer.amount + p.getDeck(deckIndex).bet;
//				
//				//reiniciar el deck del jugador
//				p.reInitDeck(deckIndex);
//				
//				return false;//continue: askBet //vuelve a empezar (pasa al siguiente player actually)
//			}
//			return true;//continue: requestPlay
//		}
//		
//		private void stand(Player p, int deckIndex) {
//			p.getDeck(deckIndex).stand = true;
//		}
//		
//		private void doubleDown(Player p, int deckIndex) {
//			int bet = p.getDeck(deckIndex).bet;
//			
//			if (p.amount > bet) {
//				p.amount = p.amount - bet;
//				
//				p.addBet(deckIndex, bet);
//				
//				if (hit(p,deckIndex)) {
//					stand(p,deckIndex);
//				}
//		
//			} else {
//				new IllegalStateException("this play is not possible, insuffient amount");
//			}
//		}
//		
//		private void split(Player p, int deckIndex) {
//			if (deckIndex == 0 && p.decks.keySet().size() == 1) {// por ahora solo un split
//				//verificar que se pueda
//				if (p.getDeck(deckIndex).cards.get(0).value == p.getDeck(deckIndex).cards.get(1).value) {
//					
//					if (p.amount > p.getDeck(deckIndex).bet) {
//						p.amount = p.amount - p.getDeck(deckIndex).bet;
//					
//						p.createDeck(1, p.getDeck(deckIndex).bet);
//						p.addCard(1, p.getDeck(deckIndex).cards.remove(1));
//						
//						//add cards to the new decks
//						p.addCard(deckIndex, deck.pop());
//						p.addCard(1, deck.pop());
//						
//						//continue requestPlay:
//						
//					} else {
//						new IllegalStateException("this play is not possible, insuffient amount");
//					}
//				}
//			}
//		}
//		
//		private void surrender(Player p, int deckIndex) {
//			p.amount = p.amount + p.getDeck(deckIndex).bet/2;
//			
//			//darle la otra mitad al dealer
//			dealer.amount = dealer.amount + p.getDeck(deckIndex).bet/2;
//			
//			//re iniciar el deck
//			p.reInitDeck(deckIndex);
//		}
//
//		@Override
//		public boolean execute() {
//			for (Player p : players) {
//				for (int i = 0; i < p.decks.keySet().size(); i++) {
//					int deckIndex = i;
//					
//					if (p.getDeck(deckIndex)!=null && !p.getDeck(deckIndex).stand) {
//						int play = 1;//from console (1 hit, 2 stand, 3 double, 4 split, 5 surrender)
//						
//						switch (play) {
//							case 1://hit
//								hit(p,deckIndex);
//							case 2://stand
//								stand(p,deckIndex);
//							case 3://double
//								doubleDown(p,deckIndex);
//							case 4://split
//								split(p,deckIndex);
//							case 5: //surrender
//								surrender(p,deckIndex);
//						}
//					}
//				}
//			}
//			return true;
//		}
//	}
//	
//	class DealerPlayStage extends Stage {
//		@Override
//		public boolean execute() {
//			dealer.getDeck(0).cards.get(0).unTap();
//			
//			while (dealer.getDeck(0).sum()<17) {
//				dealer.getDeck(0).addCard(deck.pop());
//			}
//			
//			for (Player p : players) {
//				for (int i = 0; i < p.decks.keySet().size(); i++) {
//					if (p.getDeck(i)!=null) {
//						if (dealer.getDeck(0).sum() > 21) {
//							p.amount = p.amount + 2*p.getDeck(i).bet;
//							//take out the money from the dealer
//							dealer.amount = dealer.amount - p.getDeck(i).bet;
//						} else if (dealer.getDeck(0).sum() == 21) {
//							if (p.getDeck(i).sum()==21) {
//								//return the bet
//								p.amount = p.amount + p.getDeck(i).bet;
//							} else {
//								// get the money from the deck bet
//								dealer.amount = dealer.amount + p.getDeck(i).bet;
//							}
//						} else if (dealer.getDeck(0).sum() >=17 && dealer.getDeck(0).sum() < 21) {
//							if (p.getDeck(i).sum() > dealer.getDeck(0).sum()) {
//								p.amount = p.amount + 2*p.getDeck(i).bet;
//								//take out the money from the dealer
//								dealer.amount = dealer.amount - p.getDeck(i).bet;
//							} else if (p.getDeck(i).sum() == dealer.getDeck(0).sum()) {
//								p.amount = p.amount + p.getDeck(i).bet;
//							} else if (p.getDeck(i).sum() < dealer.getDeck(0).sum()) {
//								// get the money from the deck bet
//								dealer.amount = dealer.amount + p.getDeck(i).bet;
//							}
//						}
//						p.reInitDeck(i);
//					}
//				}
//			}
//			return true;
//		}
//	}
//	
//	public void play() {
//		Stack<Card> deck = new Stack<>();
//		
//		List<Player> players = Arrays.asList(new Player(100), new Player(100));
//		
//		Player dealer = new Player(1000);
//		
//		
//		/*
//		 * Some considerations:
//		 * 
//		 * The idea is to create a flow of stages depending of the 
//		 * situations of the game 
//		 * (from one stage the game can move to a next stage)
//		 * 
//		 * The abstraction is based on the different situations (stages)
//		 * of the game
//		 * 
//		 * To make it more explicit (more verbose in terms of the business logic)
//		 * a higher amount of stages were consider, but to make it more 
//		 * practical in terms of development the amount could be reduced
//		 * to 4 stages
//		 * 
//		 * Another abstraction to reduce the complexity and increase
//		 * the object oriented model (increasing decoupling and cohesion)
//		 * is to use a DTO like ConfigData object which has as properties
//		 * the different entities of the model: players, dealer, deck (cards)
//		 * and use this DTO to init the different stages instead of pass as
//		 * parameter each of this entities
//		 * 
//		 * With this approach the api will not be changed if a new
//		 * game entity is included but the implementations
//		 * 
//		 */
//			Map<String,Stage> stages = new HashMap<>();
//			
//			InitDeckStage initDeckStage = new InitDeckStage();
//			AskForBetStage askForBetStage = new AskForBetStage();
//			FirstCardStage firstCardStage = new FirstCardStage();
//			FirstCardDealerStage firstCardDealerStage = new FirstCardDealerStage();
//			SecondCardStage secondCardStage = new SecondCardStage();
//			SecondCardDealerStage secondCardDealerStage = new SecondCardDealerStage();
//			DealerHandReviewStage dealerHandReviewStage = new DealerHandReviewStage();
//			RequestPlayStage requestPlayStage = new RequestPlayStage();
//			DealerPlayStage dealerPlayStage = new DealerPlayStage();
//			
//			initDeckStage.init(players, dealer, deck, askForBetStage, stages);
//			askForBetStage.init(players, dealer, deck, firstCardStage, stages);
//			firstCardStage.init(players, dealer, deck, firstCardDealerStage, stages);
//			firstCardDealerStage.init(players, dealer, deck, secondCardStage, stages);
//			secondCardStage.init(players, dealer, deck, secondCardDealerStage, stages);
//			secondCardDealerStage.init(players, dealer, deck, dealerHandReviewStage, stages);
//			dealerHandReviewStage.init(players, dealer, deck, requestPlayStage, stages);
//			requestPlayStage.init(players, dealer, deck, dealerPlayStage, stages);
//			dealerPlayStage.init(players, dealer, deck, null, stages);
//			
//			stages.put("initDeckStage", initDeckStage);
//			stages.put("askForBetStage", askForBetStage);
//			stages.put("firstCardStage", firstCardStage);
//			stages.put("firstCardDealerStage", firstCardDealerStage);
//			stages.put("secondCardStage", secondCardStage);
//			stages.put("secondCardDealerStage", secondCardDealerStage);
//			stages.put("dealerHandReviewStage", dealerHandReviewStage);
//			stages.put("requestPlayStage", requestPlayStage);
//			stages.put("dealerPlayStage", dealerPlayStage);
//			
//			//Init game
//			while (initDeckStage.run()) {
//				
//			}
//	}
//
//	public static void main(String[] args) {
//		Stack<Card> deck = new Stack<>();
//		
//		Player p = new Player(100);
//		Player dealer = new Player(1000);
//		
//		//askBet:
//		// pregunta por la apuesta
//		int bet = 10;//getFromConsole();
//		
//		if (p.amount > bet) {
//			p.amount = p.amount - bet;
//			//continue firstCard;
//		} else {
//			//continue surrender;
//		}
//		
//		firstCard:
//		//reparte la primera carta, osea el primer deck
//		p.createDeck(0, bet);
//		p.addCard(0, deck.pop());
//		
//		
//		//reparte la primera carta del dealer
//		dealer.createDeck(0, 0);
//		dealer.addCard(0, deck.pop());
//		
//		//reparte la segunda carta
//		p.addCard(0, deck.pop());
//		
//		//reparte la segunda carta del dealer
//		Card card = deck.pop();
//		card.unTap();
//		dealer.addCard(0,card);
//		
//		//Si la segunda carta del dealer da para 21, se termina la partida
//			if (dealer.getDeck(0).sum() == 21) {
//				
//				//Se mira si el jugador tiene tambien tiene 21
//				if (p.getDeck(0).sum() == 21) {
//					
//					p.amount = p.amount + p.getDeck(0).bet;// recupera lo apostado
//					
//				} else {
//					//darle la plata al dealer
//					dealer.amount = dealer.amount + p.getDeck(0).bet;
//				}
//				
//				//continue: askBet //vuelve a empezar (pasa al siguiente player actually)
//				
//			} else {
//				
//				//requestPlay:
//				for (int i = 0; i < p.decks.keySet().size(); i++) {
//					int deckIndex = i;
//					
//					//se le pregunta al jugador que hacer
//					int play = 1;//from console que quiere hacer (1 hit, 2 stand, 3 double, 4 split, 5 surrender)
//					
//					switch (play) {
//					case 1://hit
//						hit:
//						//Se le da una carta mas al jugador
//						p.addCard(deckIndex, deck.pop());
//						
//						//si se pasa de 21 termina el juego
//						if (p.getDeck(deckIndex).sum() > 21) {
//							//darle la plata al dealer
//							dealer.amount = dealer.amount + p.getDeck(deckIndex).bet;
//							
//							
//							//retirar jugador
//							
//							//reiniciar el deck del jugador
//							p.reInitDeck(deckIndex);
//							
//							//continue: askBet //vuelve a empezar (pasa al siguiente player actually)
//							
//						}
//						
//						//continue: requestPlay
//					case 2://stand
//						//continue with the dealer
//						
//					case 3://double
//						bet = p.getDeck(deckIndex).bet;
//						
//						if (p.amount > bet) {
//							p.amount = p.amount - bet;
//							
//							p.addBet(deckIndex, bet);
//							
//							hit();
//							
//							stand();
//							//continue hit;
//						}
//						
//					case 4://split
//						
//						if (deckIndex == 0 && p.decks.keySet().size() == 1) {// por ahora solo un split
//							//verificar que se pueda
//							if (p.getDeck(deckIndex).cards.get(0).value == p.getDeck(deckIndex).cards.get(1).value) {
//								
//								if (p.amount > p.getDeck(deckIndex).bet) {
//									p.amount = p.amount - p.getDeck(deckIndex).bet;
//								
//									p.createDeck(1, p.getDeck(deckIndex).bet);
//									p.addCard(1, p.getDeck(deckIndex).cards.remove(1));
//									
//									//add cards to the new decks
//									p.addCard(deckIndex, deck.pop());
//									p.addCard(1, deck.pop());
//									
//									//coontinue requestPlay:
//									
//								} else {
//									new IllegalStateException("this play is not possible, insuffient amount");
//								}
//							}
//						}
//						
//					case 5: //surrender
//						//devolver la mitad de la apuesta
//						p.amount = p.amount + p.getDeck(deckIndex).bet/2;
//						
//						//darle la otra mitad al dealer
//						dealer.amount = dealer.amount + p.getDeck(deckIndex).bet/2;
//						
//						//re iniciar el deck
//						p.reInitDeck(deckIndex);
//					}
//				}
//				
//				//dealerPlay:
//				dealer.getDeck(0).cards.get(0).unTap();
//				
//				while (dealer.getDeck(0).sum()<17) {
//					dealer.getDeck(0).addCard(deck.pop());
//				}
//				
//				for (int i = 0; i < p.decks.keySet().size(); i++) {
//					if (dealer.getDeck(0).sum() > 21) {
//						p.amount = p.amount + 2*p.getDeck(i).bet;
//						//take out the money from the dealer
//						dealer.amount = dealer.amount - p.getDeck(i).bet;
//					} else if (dealer.getDeck(0).sum() == 21) {
//						if (p.getDeck(i).sum()==21) {
//							//return the bet
//							p.amount = p.amount + p.getDeck(i).bet;
//						} else {
//							// get the money from the deck bet
//							dealer.amount = dealer.amount + p.getDeck(i).bet;
//						}
//					} else if (dealer.getDeck(0).sum() >=17 && dealer.getDeck(0).sum() < 21) {
//						if (p.getDeck(i).sum() > dealer.getDeck(0).sum()) {
//							p.amount = p.amount + 2*p.getDeck(i).bet;
//							//take out the money from the dealer
//							dealer.amount = dealer.amount - p.getDeck(i).bet;
//						} else if (p.getDeck(i).sum() == dealer.getDeck(0).sum()) {
//							p.amount = p.amount + p.getDeck(i).bet;
//						} else if (p.getDeck(i).sum() < dealer.getDeck(0).sum()) {
//							// get the money from the deck bet
//							dealer.amount = dealer.amount + p.getDeck(i).bet;
//						}
//					}
//					
//					p.reInitDeck(i);
//				}
//				
//				//askForBet
//				
//			}
//				
//				
//	}
//}
