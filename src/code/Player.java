package code;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import ginterface.GEndTurnChoice;
import ginterface.GBuildWall;
import ginterface.GDiscard;

public class Player {

	public TurtleTile turtle;
	public ArrayList<Card> deck;
	public ArrayList<Card> discardDeck;
	public ArrayList<Card> hand;
	public String name;
	public ArrayList<StoneWall> stoneWalls;
	public ArrayList<IceWall> iceWalls;

	// for the bug card
	public boolean bugUsed;
	public boolean isBugged;

	public Program program;

	// all the setters are here to initialize the payer's stuff, we could have put
	// all of this in a constructor
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setWalls() {
		stoneWalls = new ArrayList<StoneWall>();
		iceWalls = new ArrayList<IceWall>();

		for (int i = 0; i < 3; i++) {
			StoneWall stoneWall = new StoneWall();
			this.stoneWalls.add(stoneWall);
		}

		for (int i = 0; i < 2; i++) {
			IceWall iceWall = new IceWall();
			this.iceWalls.add(iceWall);
		}
	}

	public void setTurtle(TurtleTile playerTurtle) {
		// we bind a turtle to a player
		this.turtle = playerTurtle;
	}

	public TurtleTile getTurtle() {
		return this.turtle;
	}

	// cards and hand
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

		// we initialize the bug tile that is out of the main deck
		bugUsed = false;
		isBugged = false;
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

		// we mix the deck 3 times for an efficient mix
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < deck.size(); i++) {
				// random index
				int positionRandom = ThreadLocalRandom.current().nextInt(0, deck.size());

				// we save the card at index i
				Card pivot = this.deck.get(i);

				// we put the card at the random index at the index i
				this.deck.set(i, this.deck.get(positionRandom));

				// we put index i card at the the random index
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

	// method to discrd some cards, calls the graphic interface
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

	// when there are no more cards in the main deck, we put the discard deck in the
	// main deck
	public void discardDeckToDeck() {
		while (!this.discardDeck.isEmpty()) {
			this.deck.add(this.discardDeck.get(0));
			this.discardDeck.remove(0);
		}
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	// build wall method, calls the graphic interface
	public void buildWall() throws InterruptedException {
		new GBuildWall(this);

		while (!GBuildWall.done) {
			Thread.sleep(300);
		}

	}

	// choice at the end of the turn, calls the graphic interface
	public void endTurnChoice() throws InterruptedException {
		new GEndTurnChoice(this.hand);

		while (!GEndTurnChoice.boolEndTurnChoice) {
			Thread.sleep(300);
		}
	}

}
