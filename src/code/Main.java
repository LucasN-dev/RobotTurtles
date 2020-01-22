package code;

import java.util.ArrayList;

import ginterface.GPlayersNumberButton;
import ginterface.GRanking;
import ginterface.GCompleteProgram;
import ginterface.GDiscard;
import ginterface.GErrorNoMoreCards;
import ginterface.GNextPlayer;
import ginterface.GPlayersNames;
import ginterface.GBoard;
import ginterface.GBugPlayer;
import ginterface.GPlayerTurn;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		// we call the graphic interface to get the number of players
		GPlayersNumberButton.main(args);

		// we get the players' names
		GPlayersNames.main(args);

		// we initialize all the players
		GameSettings.players = new ArrayList<Player>();

		for (int i = 0; i < GameSettings.getNumberOfPlayers(); i++) {

			// this is where we randomly choose who goes first
			int randomNum = ThreadLocalRandom.current().nextInt(0, GameSettings.playersNames.size());
			String randomName = GameSettings.playersNames.get(randomNum);
			GameSettings.playersNames.remove(randomNum);

			Player p = new Player();
			p.setName(randomName);
			GameSettings.players.add(p);

		}

		// initialization of the board and tiles

		GameSettings.gameEnd = false;

		GameSettings.setTiles();

		GameSettings.setTurtlesPositions();
		GameSettings.setTurtlesOrientations();

		Board board = new Board();

		board.setBoard();

		board.getBoard();

		// used for debugging
		// board.printBoard();

		GBoard.initializeBoardGI();

		switch (GameSettings.numberPlayers) {
		case 2:
			GameSettings.players.get(0).setTurtle(GameSettings.turtles.get("BlueTurtle"));
			GameSettings.players.get(1).setTurtle(GameSettings.turtles.get("RedTurtle"));
			GameSettings.jewelsAmount = 1; // used for the placing wall function
			break;

		case 3:
			GameSettings.players.get(0).setTurtle(GameSettings.turtles.get("RedTurtle"));
			GameSettings.players.get(1).setTurtle(GameSettings.turtles.get("BlueTurtle"));
			GameSettings.players.get(2).setTurtle(GameSettings.turtles.get("GreenTurtle"));
			GameSettings.jewelsAmount = 3; // used for the placing wall function

			break;

		case 4:
			GameSettings.players.get(0).setTurtle(GameSettings.turtles.get("RedTurtle"));
			GameSettings.players.get(1).setTurtle(GameSettings.turtles.get("BlueTurtle"));
			GameSettings.players.get(2).setTurtle(GameSettings.turtles.get("GreenTurtle"));
			GameSettings.players.get(3).setTurtle(GameSettings.turtles.get("PurpleTurtle"));
			GameSettings.jewelsAmount = 2; // used for the placing wall function

			break;

		}

		// initialization of decks and programs
		for (int i = 0; i < GameSettings.numberPlayers; i++) {
			Player p = GameSettings.players.get(i);

			p.setDeck();
			p.mixDeck();
			p.setDiscardDeck();
			p.setHand();
			p.setWalls();
			Program program = new Program();
			p.setProgram(program);

		}

		// here we manage all the game's turns
		while (!GameSettings.gameEnd) {

			for (int i = 0; i < GameSettings.players.size(); i++) {

				Player p = GameSettings.players.get(i);

				// if there's only one player left playing, the game ends.
				if (GameSettings.players.size() - GameSettings.playersOutOfTheGame.size() == 1) {
					GameSettings.gameEnd = true;

					// we need to add the last player at the bottom of the ranking but it is not
					// necessarily the next player in the "players" ArrayList because we don't
					// remove the players who reached a jewel from this list to avoid bugs. So we
					// check which player is in "players" but not in "ranking" and we add him at the
					// end

					for (int j = 0; j < GameSettings.players.size(); j++) {
						Player plast = GameSettings.players.get(j);
						if (!GameSettings.ranking.contains(plast)) {
							GameSettings.ranking.add(plast);
						}

					}
					GRanking.main(args);
					break;
				}

				// the next player's if the game has not ended and the player is still in the
				// game
				if (!GameSettings.gameEnd && !GameSettings.playersOutOfTheGame.contains(p)) {

					// call the graphic interface to tell the player to give the computer to the
					// next one
					new GNextPlayer(p);

					while (!GNextPlayer.closed) {
						Thread.sleep(500);
						// little trick, not very clean, we found. We have to wait for the player to
						// confirm its action on the graphic interface for it close, instead everything
						// opens one after an other. We created the infinite while loop that waits
						// checks if the player has made his choice waits again etc etc. Not very
						// code/performance friendly but better than nothing, we use this loop quite
						// many times in the code
					}

					// new gi player turn
					new GPlayerTurn(p);

					while (!GPlayerTurn.boolChoice) {
						Thread.sleep(500);
					}

					switch (GameSettings.playerChoice) {

					case 1:
						GCompleteProgram.completed = false;

						while (!GCompleteProgram.completed) {

							p.program.completeProgram(p);
						}
						break;

					case 2:
						p.buildWall();
						GBoard.updateGI();
						break;

					case 3:
						p.program.runProgram(board, p);
						break;

					case 4:
						// bug
						new GBugPlayer(p);

						while (!GBugPlayer.done) {
							Thread.sleep(300);
						}

						GBugPlayer.done = false;
					}

					// if the player is in ranking this means he just reached a jewel, no need to
					// ask him what to do with his cards if there are no more cards to draw, same.

					if (p.hand.size() != 0 && !GameSettings.ranking.contains(p)) {
						p.endTurnChoice();
					} else {
						GameSettings.playerChoice = 2;
					}

					switch (GameSettings.playerChoice) {

					case 1:
						GDiscard.completed = false;

						while (!GDiscard.completed) {
							p.discardHand();
						}

						break;

					case 2:
						// nothing
						break;
					}

					if (p.deck.size() < (5 - p.hand.size())) {
						// if there are no more cards in the main deck
						p.discardDeckToDeck();
						
					}

					if (p.deck.size() == 0 && p.discardDeck.size() == 0) {
						// if there are no more cards in the main deck nor in the discard deck, it shows
						// an error window
						GErrorNoMoreCards.closed = false;
						new GErrorNoMoreCards();

						while (!GErrorNoMoreCards.closed) {
							Thread.sleep(300);
						}
					}

					else {
						p.drawCards();
					}

				}
			}
		}

	}

}
