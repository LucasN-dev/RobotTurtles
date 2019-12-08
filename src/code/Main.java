package code;
import java.util.ArrayList;

import javax.swing.JFrame;

import ginterface.GPlayersNumberButton;
import ginterface.GCompleteProgram;
import ginterface.GDiscard;
import ginterface.GErrorNoMoreCards;
import ginterface.GBoard;
import ginterface.GPlayerTurn;

public class Main {

	public static void main(String[] args) throws InterruptedException { // le throws c'est a cause du thread.sleep du
																			// choix du nb de joueurs

		// initialisation joueurs
		GPlayersNumberButton.main(args);

		ArrayList<Player> Joueurs = new ArrayList<Player>();

		for (int i = 0; i < GameSettings.getNumberOfPlayers(); i++) {

			Player j = new Player();
			Joueurs.add(j);

		}

		// initialisation tuiles et plateau

		GameSettings.victory = false;

		GameSettings.setTiles();

		GameSettings.setTurtlesPositions();
		GameSettings.setTurtlesOrientations();

		Board Plateau = new Board();

		Plateau.setBoard();

		Plateau.getBoard();

		Plateau.printBoard();

		GBoard.initializeBoardGI();

		switch (GameSettings.numberPlayers) {
		case 2:
			Joueurs.get(0).setTurtle(GameSettings.turtles.get("BlueTurtle"));
			Joueurs.get(1).setTurtle(GameSettings.turtles.get("RedTurtle"));
			break;

		case 3:
			Joueurs.get(0).setTurtle(GameSettings.turtles.get("RedTurtle"));
			Joueurs.get(1).setTurtle(GameSettings.turtles.get("BlueTurtle"));
			Joueurs.get(2).setTurtle(GameSettings.turtles.get("GreenTurtle"));

			break;

		case 4:
			Joueurs.get(0).setTurtle(GameSettings.turtles.get("RedTurtle"));
			Joueurs.get(1).setTurtle(GameSettings.turtles.get("BlueTurtle"));
			Joueurs.get(2).setTurtle(GameSettings.turtles.get("GreenTurtle"));
			Joueurs.get(3).setTurtle(GameSettings.turtles.get("PurpleTurtle"));

			break;

		}

		for (int i = 0; i < GameSettings.numberPlayers; i++) {
			Player J = Joueurs.get(i);

			J.setDeck();
			J.mixDeck();
			J.setDiscardDeck();
			J.setHand();
			Program program = new Program();
			J.setProgram(program);
			
		}

		/**
		 * while (!GestionJeu.partieGagne) { for (int i=0; i<GestionJeu.NombreDeJoueurs;
		 * i++) { Joueur J = Joueurs.get(i); J.completerProgramme(); } }
		 **/

		while (!GameSettings.victory) {

			for (int i = 0; i < Joueurs.size(); i++) {

				System.out.println("C'est au joueur " + (i + 1) + " de jouer");
				
				Player J = Joueurs.get(i);

				new GPlayerTurn(i + 1, J);

				while (!GPlayerTurn.boolChoice) {
					Thread.sleep(500);
					// petite astuce pas tres opti pour attendre que la fenetre se ferme ( et donc
					// que le nombre de joueurs soit choisi pour passer a la suite)
				}

				

				for (int k = 0; k < J.getHand().size(); k++) {
					System.out.println(J.getHand().get(k).getType());
				}

				switch (GameSettings.playerChoice) {

				case 1:
					GCompleteProgram.completed = false; // si on met pas ça on rentre jamais dans la boucle après la
															// première iteration
					while (!GCompleteProgram.completed) {

						J.program.completeProgram(J);
					}
					break;

				case 2:
					// TODO fonction placer un mur
					break;

				case 3:
					J.program.runProgram(Plateau,J);
					break;

				}

				//pour test
				Plateau.printBoard();
				
				
				

				if (J.hand.size()!=0) {
					J.endTurnChoice();
				}
				else {
					GameSettings.playerChoice=2;
				}

				switch (GameSettings.playerChoice) {

				case 1:
					GDiscard.completed=false;
					
					while (!GDiscard.completed) {
						J.discardHand();
					}
					
					break;

				case 2:
					//rien
					break;
				}
				
				
				if (J.deck.size()<(5-J.hand.size())) {
					//on transfere la pile de discard vers la pioche s'il reste moins
					//de carte dans la pioche que de carte à piocher
					J.discardDeckToDeck();
					System.out.println("Transfert de cartes");
				}
				
				if (J.deck.size()==0 && J.discardDeck.size()==0) {
					GErrorNoMoreCards.closed=false;
					new GErrorNoMoreCards();
					
					while (!GErrorNoMoreCards.closed) {
						Thread.sleep(300);
					}
				}
				
				else {
					J.drawCards();
				}

				
				
				
				
				
				
				System.out.println("Main "+J.hand.size());
				System.out.println("Pioche "+J.deck.size());
				System.out.println("Discard "+J.discardDeck.size());
			}
		}

	}

}
