package code;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.TreeSet;



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
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("GreenJewel"), new int[]{ 7,3 });

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
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("PurpleJewel"), new int[]{ 7,0 });

			board[7][3] = GameSettings.jewels.get("GreenJewel");
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("GreenJewel"), new int[]{ 7,3 });

			board[7][5] = GameSettings.jewels.get("BlueJewel");
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("BlueJewel"), new int[]{ 7,5 });

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
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("PurpleJewel"), new int[]{ 7,1 });

			board[7][6] = GameSettings.jewels.get("BlueJewel");
			GameSettings.jewelsPositions.put(GameSettings.jewels.get("PurpleJewel"), new int[]{ 7,6 });

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
	
	public void dfs(Player p) {
		ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
		TreeSet<int[]> discovered = new TreeSet<int[]>();
		ArrayList<int[]> path = new ArrayList<int[]>();

		queue.push(GameSettings.turtlesPositions.get(p.turtle.getType()));
		int[] node = {0, 0};
		while (queue.size() != 0) {
			node = queue.pop();
			if (!discovered.contains(node)) {
				discovered.add(node);
				path.add(node);

				try {

					for (int i = 0; i < ((this[node[0]][node[1]]).size()); i++) { // la on fait pour chaque voisin du node
						if (!discovered.contains((this[node[0]][node[1]]).get(i))) { // on verifie si le voisin du node est pas
																				// dans discovered

							// discovered.add((graph.get(node)).get(i));
							queue.push((this[node[0]][node[1]]).get(i));

						}
					}

				} catch (Exception e) {
					// cas ou il y a pas de voisin, size = null, on fait rien

				}

			}

		}
		System.out.println(path);
	}
}
