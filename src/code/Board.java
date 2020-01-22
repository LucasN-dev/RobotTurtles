package code;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Board {

	public static Object[][] board;

	// we initialize the board, we put an empty String for an empty cell
	public void setBoard() {
		board = new Object[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = "      ";
			}
		}

		// this hashmap will store the jewels position
		GameSettings.jewelsPositions = new LinkedHashMap<Jewel, int[]>();

		// we set the turtles and jewels position depending on the number of players
		switch (GameSettings.numberPlayers) {
		// 2 players
		case 2:
			// we update Turtles starting and current positions

			board[0][1] = GameSettings.turtles.get("BlueTurtle");
			GameSettings.updateTurtlePosition("BlueTurtle", 0, 1);
			GameSettings.updateTurtleStartingPosition("BlueTurtle", 0, 1);

			board[0][5] = GameSettings.turtles.get("RedTurtle");
			GameSettings.updateTurtlePosition("RedTurtle", 0, 5);
			GameSettings.updateTurtleStartingPosition("RedTurtle", 0, 5);

			board[7][3] = GameSettings.jewels.get("GreenJewel");
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("GreenJewel"), new int[] { 7, 3 });

			for (int i = 0; i < 8; i++) {
				board[i][7] = GameSettings.stoneWalls.get(0);
				GameSettings.stoneWalls.remove(0);
			}
			break;

		case 3:
			// 3 players
			board[0][0] = GameSettings.turtles.get("BlueTurtle");
			GameSettings.updateTurtlePosition("BlueTurtle", 0, 0);
			GameSettings.updateTurtleStartingPosition("BlueTurtle", 0, 0);

			board[0][3] = GameSettings.turtles.get("RedTurtle");
			GameSettings.updateTurtlePosition("RedTurtle", 0, 3);
			GameSettings.updateTurtleStartingPosition("RedTurtle", 0, 3);

			board[0][6] = GameSettings.turtles.get("GreenTurtle");
			GameSettings.updateTurtlePosition("GreenTurtle", 0, 6);
			GameSettings.updateTurtleStartingPosition("GreenTurtle", 0, 6);

			board[7][0] = GameSettings.jewels.get("PurpleJewel");
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("PurpleJewel"), new int[] { 7, 0 });

			board[7][3] = GameSettings.jewels.get("GreenJewel");
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("GreenJewel"), new int[] { 7, 3 });

			board[7][6] = GameSettings.jewels.get("BlueJewel");
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("BlueJewel"), new int[] { 7, 6 });

			// we put some walls on the far right column
			for (int i = 0; i < 8; i++) {
				board[i][7] = GameSettings.stoneWalls.get(0);
				GameSettings.stoneWalls.remove(0);
			}

			break;
		case 4:
			// 4 players
			board[0][0] = GameSettings.turtles.get("BlueTurtle");
			GameSettings.updateTurtlePosition("BlueTurtle", 0, 0);
			GameSettings.updateTurtleStartingPosition("BlueTurtle", 0, 0);

			board[0][2] = GameSettings.turtles.get("RedTurtle");
			GameSettings.updateTurtlePosition("RedTurtle", 0, 2);
			GameSettings.updateTurtleStartingPosition("RedTurtle", 0, 2);

			board[0][5] = GameSettings.turtles.get("GreenTurtle");
			GameSettings.updateTurtlePosition("GreenTurtle", 0, 5);
			GameSettings.updateTurtleStartingPosition("GreenTurtle", 0, 5);

			board[0][7] = GameSettings.turtles.get("PurpleTurtle");
			GameSettings.updateTurtlePosition("PurpleTurtle", 0, 7);
			GameSettings.updateTurtleStartingPosition("PurpleTurtle", 0, 7);

			board[7][1] = GameSettings.jewels.get("PurpleJewel");
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("PurpleJewel"), new int[] { 7, 1 });

			board[7][6] = GameSettings.jewels.get("BlueJewel");
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("BlueJewel"), new int[] { 7, 6 });

		}

	}

	// method to return the board
	public Object[][] getBoard() {
		return board;
	}

	public void printBoard() {
		// for testing without graphic interface

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getClass() == TurtleTile.class) {

				}

				else if (board[i][j].getClass() == Jewel.class) {

				}

				else {

				}

			}

		}
	}

	// this is a BFS to check if a wall is blocking something when building one
	public static boolean blockingWallCheck() {

		// the idea is that we get from one turtle's position and we try to reach every
		// other turtle and every Jewel, if they are all reachable then the new wall
		// doesn't block anything, if not then it can't be placed

		// variables with the number of reachable stuff
		int reachableJewels = 0;
		int reachableTurtles = 0;
		int reachableStartingPositions = 0;

		// the visited cells
		boolean[][] discovered = new boolean[8][8];

		// queue of cells
		Queue<String> queue = new LinkedList<>();

		// the first cell we visit is one of the turtle cells
		queue.add(GameSettings.turtlesPositions.get(GameSettings.players.get(0).getTurtle().getType())[0] + ","
				+ GameSettings.turtlesPositions.get(GameSettings.players.get(0).getTurtle().getType())[1]);

		while (queue.isEmpty() == false) {

			// we use String for the positions because it is easier to compare than 2Ds
			// arrays

			String pos = queue.remove();
			int x = Integer.parseInt(pos.split(",")[0]);
			int y = Integer.parseInt(pos.split(",")[1]);

			if (x < 0 || y < 0 || x >= 8 || y >= 8 || discovered[x][y])
				continue;

			discovered[x][y] = true;

			IceWall booleanIceWall = new IceWall();

			// below we want to test if what's inside the board's cell is a String (meaning
			// it's empty) or an iceWall (breakable), we have to instantiate the wall and
			// create a String because we are in a static method thus we can't just write
			// IceWall.getClass() as it is a non-static reference

			if (board[x][y].getClass() == "String".getClass() || board[x][y].getClass() == booleanIceWall.getClass()
					|| board[x][y].getClass() == GameSettings.turtles.get(GameSettings.turtles.keySet().toArray()[0])
							.getClass()) {

				queue.add(x + "," + (y - 1));
				queue.add(x + "," + (y + 1));
				queue.add((x - 1) + "," + y);
				queue.add((x + 1) + "," + y);

				if (board[x][y].getClass() == GameSettings.turtles.get(GameSettings.turtles.keySet().toArray()[0])
						.getClass()) {
					reachableTurtles = reachableTurtles + 1;
				}
			}

			else if (board[x][y].getClass() == GameSettings.jewels.get(GameSettings.jewels.keySet().toArray()[0])
					.getClass()) {
				reachableJewels = reachableJewels + 1;
			}

			// we check if it's a turtle staring position because it has to be free if a
			// turtle is teleported back to start
			for (int r = 0; r < GameSettings.numberPlayers; r++) {
				if (x == GameSettings.turtlesStartingPositions
						.get(GameSettings.turtlesStartingPositions.keySet().toArray()[r])[0]
						&& y == GameSettings.turtlesStartingPositions
								.get(GameSettings.turtlesStartingPositions.keySet().toArray()[r])[1]) {
					reachableStartingPositions = reachableStartingPositions + 1;

				}
			}

			// the last case where it's Stone Wall, we do nothing, we don't have to visit it
		}

		// if everything is reachable, it returns "true"
		if (reachableJewels == GameSettings.jewelsAmount && reachableTurtles == GameSettings.numberPlayers
				&& reachableStartingPositions == GameSettings.numberPlayers) {
			return false;
		} else {
			return true;
		}

	}
}
