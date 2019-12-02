package code;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import gi.GEndTurnChoice;
import gi.GCompleteProgram;
import gi.GDiscard;
import gi.GBoard;

public class Player {

	public TurtleTile turtle;
	public ArrayList<Card> deck;
	public ArrayList<Card> discardDeck;
	public ArrayList<Card> hand;

	//public ArrayDeque<Card> program;
	
	public Program program;
	
	// public static int[] position;
	// public static char direction;

	public void setTurtle(TurtleTile playerTurtle) {
		// on assigne la tortue au joueur
		this.turtle = playerTurtle;
	}

	public TurtleTile getTortue() {
		return this.turtle;
	}

	// cartes et main
	public void setDeck() {
		this.deck = new ArrayList<Card>();
		for (int i = 0; i < 18; i++) {
			Card Carte = new Card("BlueCard");
			this.deck.add(Carte);
		}

		for (int i = 0; i < 8; i++) {
			Card Carte = new Card("YellowCard");
			this.deck.add(Carte);
		}

		for (int i = 0; i < 8; i++) {
			Card Carte = new Card("PurpleCard");
			this.deck.add(Carte);
		}

		for (int i = 0; i < 3; i++) {
			Card Carte = new Card("LaserCard");
			this.deck.add(Carte);
		}
	}

	public ArrayList<Card> getDeck() {
		return this.deck;
	}

	public void setDiscardDeck() {
		this.discardDeck = new ArrayList<Card>();
	}

	public ArrayList<Card> getDiscardDeck() {
		return this.discardDeck;
	}

	public void mixDeck() {

		// on melange 3 fois pour un melange super efficace
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < deck.size(); i++) {
				// on prend un index aleatoire
				int positionRandom = ThreadLocalRandom.current().nextInt(0, deck.size());

				// on sauvegarde la carte a l'index i
				Card pivot = this.deck.get(i);

				// on met la carte a l'index aleatoire a la place de la carte a l'index i
				this.deck.set(i, this.deck.get(positionRandom));

				// on met la carte a l'index i a la place de la carte a l'index aleatoire
				this.deck.set(positionRandom, pivot);

			}
		}
	}

	public void setHand() {
		this.hand = new ArrayList<Card>();
		for (int i = 0; i < 5; i++) {
			this.hand.add(deck.get(0));
			this.deck.remove(0);
		}
	}

	public ArrayList<Card> getHand() {
		return this.hand;
	}

	public void discardHand() throws InterruptedException {

		new GDiscard(this.hand, this.discardDeck, this.turtle);

		while (!GDiscard.closed) {
			Thread.sleep(300);
		}
	}

	public void drawCards() {
		int len = this.hand.size();
		for (int i = 0; i < 5 - len; i++) {
			if (!this.deck.isEmpty()) {
				this.hand.add(this.deck.get(0));
				this.deck.remove(0);
			}
		}
	}

	public void discardDeckToDeck() {
		while (!this.discardDeck.isEmpty()) {
			this.deck.add(this.discardDeck.get(0));
			this.discardDeck.remove(0);
		}
	}

	// programme
	
	

	public void setProgram(Program program) {
		this.program = program;
	}

	
	public void endTurnChoice() throws InterruptedException {
		new GEndTurnChoice(this.hand);

		while (!GEndTurnChoice.boolEndTurnChoice) {
			Thread.sleep(300);
		}
	}
	
	
}
