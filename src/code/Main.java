package code;

import java.util.ArrayList;

import javax.swing.JFrame;

import ginterface.GPlayersNumberButton;
import ginterface.GCompleteProgram;
import ginterface.GDiscard;
import ginterface.GErrorNoMoreCards;
import ginterface.GPlayersNames;
import ginterface.GBoard;
import ginterface.GPlayerTurn;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

	public static void main(String[] args) throws InterruptedException { // le throws c'est a cause du thread.sleep du
																			// choix du nb de joueurs

		// initialisation joueurs
		GPlayersNumberButton.main(args);

		GPlayersNames.main(args);

		ArrayList<Player> players = new ArrayList<Player>();

		for (int i = 0; i < GameSettings.getNumberOfPlayers(); i++) {

			// this is where we randomly choose who goes first
			int randomNum = ThreadLocalRandom.current().nextInt(0, GameSettings.playersNames.size());
			String randomName = GameSettings.playersNames.get(randomNum);
			GameSettings.playersNames.remove(randomNum);

			Player p = new Player();
			p.setName(randomName);
			players.add(p);

		}

		// initialisation tuiles et plateau

		GameSettings.gameEnd = false;

		GameSettings.setTiles();

		GameSettings.setTurtlesPositions();
		GameSettings.setTurtlesOrientations();

		Board board = new Board();

		board.setBoard();

		board.getBoard();

		board.printBoard();

		GBoard.initializeBoardGI();

		switch (GameSettings.numberPlayers) {
		case 2:
			players.get(0).setTurtle(GameSettings.turtles.get("BlueTurtle"));
			players.get(1).setTurtle(GameSettings.turtles.get("RedTurtle"));
			break;

		case 3:
			players.get(0).setTurtle(GameSettings.turtles.get("RedTurtle"));
			players.get(1).setTurtle(GameSettings.turtles.get("BlueTurtle"));
			players.get(2).setTurtle(GameSettings.turtles.get("GreenTurtle"));

			break;

		case 4:
			players.get(0).setTurtle(GameSettings.turtles.get("RedTurtle"));
			players.get(1).setTurtle(GameSettings.turtles.get("BlueTurtle"));
			players.get(2).setTurtle(GameSettings.turtles.get("GreenTurtle"));
			players.get(3).setTurtle(GameSettings.turtles.get("PurpleTurtle"));

			break;

		}

		for (int i = 0; i < GameSettings.numberPlayers; i++) {
			Player p = players.get(i);

			p.setDeck();
			p.mixDeck();
			p.setDiscardDeck();
			p.setHand();
			Program program = new Program();
			p.setProgram(program);

		}

		/**
		 * while (!GestionJeu.partieGagne) { for (int i=0; i<GestionJeu.NombreDeJoueurs;
		 * i++) { Joueur J = Joueurs.get(i); J.completerProgramme(); } }
		 **/

		while (!GameSettings.gameEnd) {

			for (int i = 0; i < players.size(); i++) {

				System.out.println("C'est au joueur " + (i + 1) + " de jouer");

				Player p = players.get(i);
				
				//if there's only one player left playing, the game ends.
				
				if (GameSettings.numberPlayers-GameSettings.turtlesOutOfTheGame.size() == 1) {
					GameSettings.gameEnd=true;
					// TODO: victory GI + ranking
				}

				if (!GameSettings.turtlesOutOfTheGame.contains(p.getTurtle().getType()) && !GameSettings.gameEnd) {
					new GPlayerTurn(p);

					while (!GPlayerTurn.boolChoice) {
						Thread.sleep(500);
						// petite astuce pas tres opti pour attendre que la fenetre se ferme ( et donc
						// que le nombre de joueurs soit choisi pour passer a la suite)
					}

					for (int k = 0; k < p.getHand().size(); k++) {
						System.out.println(p.getHand().get(k).getType());
					}

					switch (GameSettings.playerChoice) {

					case 1:
						GCompleteProgram.completed = false; // si on met pas ça on rentre jamais dans la boucle après la
															// première iteration
						while (!GCompleteProgram.completed) {

							p.program.completeProgram(p);
						}
						break;

					case 2:
						// TODO fonction placer un mur
						break;

					case 3:
						p.program.runProgram(board, p);
						break;

					}

					// pour test
					board.printBoard();

					if (p.hand.size() != 0) {
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
						// rien
						break;
					}

					if (p.deck.size() < (5 - p.hand.size())) {
						// on transfere la pile de discard vers la pioche s'il reste moins
						// de carte dans la pioche que de carte à piocher
						p.discardDeckToDeck();
						System.out.println("Transfert de cartes");
					}

					if (p.deck.size() == 0 && p.discardDeck.size() == 0) {
						GErrorNoMoreCards.closed = false;
						new GErrorNoMoreCards();

						while (!GErrorNoMoreCards.closed) {
							Thread.sleep(300);
						}
					}

					else {
						p.drawCards();
					}

					System.out.println("Main " + p.hand.size());
					System.out.println("Pioche " + p.deck.size());
					System.out.println("Discard " + p.discardDeck.size());

				}
			}
		}

	}

}
