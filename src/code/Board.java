package code;

public class Board {

	public static Object[][] board;

	public void setBoard() {
		board = new Object[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = "      ";
			}
		}
		
		switch (GameSettings.numberPlayers) {
		case 2:
			board[0][1] = GameSettings.turtles.get("BlueTurtle");
			// GestionJeu.Tortues.remove("BlueTurtle");
			GameSettings.updateTurtlePosition("BlueTurtle", 0, 1);
			GameSettings.updateTurtleStartingPosition("BlueTurtle", 0, 1);

			board[0][5] = GameSettings.turtles.get("RedTurtle");
			// GestionJeu.Tortues.remove("RedTurtle");
			GameSettings.updateTurtlePosition("RedTurtle", 0, 5);
			GameSettings.updateTurtleStartingPosition("RedTurtle", 0, 5);

			board[7][3] = GameSettings.jewels.get("GreenJewel");
			// GestionJeu.Joyaux.remove("GreenJewel");

			for (int i = 0; i < 8; i++) {
				board[i][7] = GameSettings.stoneWalls.get(0);
				GameSettings.stoneWalls.remove(0);
			}
			break;
		case 3:
			board[0][0] = GameSettings.turtles.get("BlueTurtle");
			// GestionJeu.Tortues.remove("BlueTurtle");
			GameSettings.updateTurtlePosition("BlueTurtle", 0, 0);
			GameSettings.updateTurtleStartingPosition("BlueTurtle", 0, 0);

			board[0][3] = GameSettings.turtles.get("RedTurtle");
			// GestionJeu.Tortues.remove("RedTurtle");
			GameSettings.updateTurtlePosition("RedTurtle", 0, 3);
			GameSettings.updateTurtleStartingPosition("RedTurtle", 0, 3);

			board[0][5] = GameSettings.turtles.get("GreenTurtle");
			// GestionJeu.Tortues.remove("GreenTurtle");
			GameSettings.updateTurtlePosition("GreenTurtle", 0, 5);
			GameSettings.updateTurtleStartingPosition("GreenTurtle", 0, 5);

			board[7][0] = GameSettings.jewels.get("PurpleJewel");
			GameSettings.jewels.remove("PurpleJewel");

			board[7][3] = GameSettings.jewels.get("GreenJewel");
			GameSettings.jewels.remove("GreenJewel");

			board[7][5] = GameSettings.jewels.get("BlueJewel");
			GameSettings.jewels.remove("BlueJewel");

			for (int i = 0; i < 8; i++) {
				board[i][7] = GameSettings.stoneWalls.get(0);
				GameSettings.stoneWalls.remove(0);
			}

			break;
		case 4:
			board[0][0] = GameSettings.turtles.get("BlueTurtle");
			// GestionJeu.Tortues.remove("BlueTurtle");
			GameSettings.updateTurtlePosition("BlueTurtle", 0, 0);
			GameSettings.updateTurtleStartingPosition("BlueTurtle", 0, 0);

			board[0][2] = GameSettings.turtles.get("RedTurtle");
			// GestionJeu.Tortues.remove("RedTurtle");
			GameSettings.updateTurtlePosition("RedTurtle", 0, 2);
			GameSettings.updateTurtleStartingPosition("RedTurtle", 0, 2);

			board[0][5] = GameSettings.turtles.get("GreenTurtle");
			// GestionJeu.Tortues.remove("GreenTurtle");
			GameSettings.updateTurtlePosition("RedTurtle", 0, 5);
			GameSettings.updateTurtleStartingPosition("RedTurtle", 0, 5);

			board[0][7] = GameSettings.turtles.get("PurpleTurtle");
			// GestionJeu.Tortues.remove("PurpleTurtle");
			GameSettings.updateTurtlePosition("PurpleTurtle", 0, 7);
			GameSettings.updateTurtleStartingPosition("PurpleTurtle", 0, 7);

			board[7][1] = GameSettings.jewels.get("PurpleJewel");
			GameSettings.jewels.remove("PurpleJewel");

			board[7][6] = GameSettings.jewels.get("BlueJewel");
			GameSettings.jewels.remove("BlueJewel");

		}
	}

	public Object[][] getBoard() {
		return board;
	}

	public void printBoard() {
		// for testing without GI

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getClass() == TurtleTile.class) {
					System.out.print(((TurtleTile) board[i][j]).getType());
				}

				else if (board[i][j].getClass() == Jewel.class) {
					System.out.print(((Jewel) board[i][j]).getType());

				}

				else {
					System.out.print(board[i][j]);
				}

			}
			System.out.println("");

		}
	}
}
