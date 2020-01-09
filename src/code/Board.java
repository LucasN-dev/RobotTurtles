package code;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Comparator;

public class Board {

	public static Object[][] board;

	public void setBoard() {
		board = new Object[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = "      ";
			}
		}

		GameSettings.jewelsPositions = new LinkedHashMap<Jewel, int[]>();

		switch (GameSettings.numberPlayers) {
		case 2:
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
			board[0][0] = GameSettings.turtles.get("BlueTurtle");
			GameSettings.updateTurtlePosition("BlueTurtle", 0, 0);
			GameSettings.updateTurtleStartingPosition("BlueTurtle", 0, 0);

			board[0][3] = GameSettings.turtles.get("RedTurtle");
			GameSettings.updateTurtlePosition("RedTurtle", 0, 3);
			GameSettings.updateTurtleStartingPosition("RedTurtle", 0, 3);

			board[0][5] = GameSettings.turtles.get("GreenTurtle");
			GameSettings.updateTurtlePosition("GreenTurtle", 0, 5);
			GameSettings.updateTurtleStartingPosition("GreenTurtle", 0, 5);

			board[7][0] = GameSettings.jewels.get("PurpleJewel");
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("PurpleJewel"), new int[] { 7, 0 });

			board[7][3] = GameSettings.jewels.get("GreenJewel");
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("GreenJewel"), new int[] { 7, 3 });

			board[7][5] = GameSettings.jewels.get("BlueJewel");
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("BlueJewel"), new int[] { 7, 5 });

			for (int i = 0; i < 8; i++) {
				board[i][7] = GameSettings.stoneWalls.get(0);
				GameSettings.stoneWalls.remove(0);
			}

			break;
		case 4:
			board[0][0] = GameSettings.turtles.get("BlueTurtle");
			GameSettings.updateTurtlePosition("BlueTurtle", 0, 0);
			GameSettings.updateTurtleStartingPosition("BlueTurtle", 0, 0);

			board[0][2] = GameSettings.turtles.get("RedTurtle");
			GameSettings.updateTurtlePosition("RedTurtle", 0, 2);
			GameSettings.updateTurtleStartingPosition("RedTurtle", 0, 2);

			board[0][5] = GameSettings.turtles.get("GreenTurtle");
			GameSettings.updateTurtlePosition("RedTurtle", 0, 5);
			GameSettings.updateTurtleStartingPosition("RedTurtle", 0, 5);

			board[0][7] = GameSettings.turtles.get("PurpleTurtle");
			GameSettings.updateTurtlePosition("PurpleTurtle", 0, 7);
			GameSettings.updateTurtleStartingPosition("PurpleTurtle", 0, 7);

			board[7][1] = GameSettings.jewels.get("PurpleJewel");
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("PurpleJewel"), new int[] { 7, 1 });

			board[7][6] = GameSettings.jewels.get("BlueJewel");
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("PurpleJewel"), new int[] { 7, 6 });

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

	public static boolean bfs() {

		// the idea is that we get from one turtle's position and we try to reach every
		// other turtle and every Jewel, if they are all reachable then the new wall
		// doesn't block anything, if not then it can't be placed

		int reachableJewels = 0;
		int reachableTurtles = 0;

		boolean[][] discovered = new boolean[8][8];

		Queue<String> queue = new LinkedList<>();

		queue.add(GameSettings.turtlesPositions.get(GameSettings.players.get(0).getTurtle().getType())[0] + ","
				+ GameSettings.turtlesPositions.get(GameSettings.players.get(0).getTurtle().getType())[1]);

		System.out.println("Breadth-First Traversal: ");
		while (queue.isEmpty() == false) {

			String pos = queue.remove();
			int x = Integer.parseInt(pos.split(",")[0]);
			int y = Integer.parseInt(pos.split(",")[1]);

			if (x < 0 || y < 0 || x >= 8 || y >= 8 || discovered[x][y])
				continue;

			discovered[x][y] = true;
			System.out.println(x + "," + y + " ");

			IceWall booleanIceWall = new IceWall();

			// below we want to test if what's inside the board's cell is a String (meaning
			// it's empty) or an iceWall (breakable), we have to instantiate the wall and
			// create a String because we are in a static method thus we can't just write
			// IceWall.getClass() as it is a non-static reference

			if (board[x][y].getClass() == "String".getClass()
					|| board[x][y].getClass() == booleanIceWall.getClass()
					|| board[x][y].getClass() == GameSettings.turtles
							.get(GameSettings.turtles.keySet().toArray()[0]).getClass()) {

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

			// the last case where it's Stone Wall, we do nothing, we don't have to visit it
		}

		System.out.println(reachableJewels + "J " + reachableTurtles + "T");

		if (reachableJewels == GameSettings.jewelsAmount && reachableTurtles == GameSettings.numberPlayers) {
			return false;
		} else {
			return true;
		}

	}
}
