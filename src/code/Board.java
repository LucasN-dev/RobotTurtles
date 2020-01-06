package code;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
		

		int reachableJewels = 0;

		for (int l = 0; l < GameSettings.numberPlayers; l++) {
			
			System.out.println("l= " + l);
			
			ArrayDeque<int[]> queue = new ArrayDeque<int[]>();

			TreeSet<int[]> discovered = new TreeSet<int[]>(new Comparator<int[]>() {
				@Override
				public int compare(int[] a, int[] b) {
					if (a[0] == b[0] && a[1] == b[1]) {
						return 1;
					} else {
						return 0;
					}

				}
			});
			

			queue.add(GameSettings.turtlesPositions.get(GameSettings.turtlesPositions.keySet().toArray()[l]));
			int[] node = { 0, 0 };
			int[] neighbourNode = { 0, 0 };
			while (queue.size() != 0) {
				
				node = queue.poll();

				if (!discovered.contains(node)) {
					discovered.add(node);

					for (int i = 0; i < 4; i++) { // la on fait pour chaque voisin du node
						try {

							switch (i) {
							case 1:
								neighbourNode = node;
								neighbourNode[1] = neighbourNode[1] - 1;
								

								if (!discovered.contains(neighbourNode)) {
									try {
										if (board[neighbourNode[0]][neighbourNode[1]].equals("      ")) {
											// if the node is empty, we add it to the queue

											System.out.println(neighbourNode[0] + " " + neighbourNode[1]);
											
											queue.add(neighbourNode);
										}

									} catch (Exception e) {

										for (int k = 0; k < GameSettings.jewelsPositions.size(); k++) {
											if (GameSettings.jewelsPositions.get(GameSettings.jewelsPositions.keySet()
													.toArray()[k])[0] == neighbourNode[0]
													&& GameSettings.jewelsPositions.get(GameSettings.jewelsPositions
															.keySet().toArray()[k])[1] == neighbourNode[1]) {
												reachableJewels += 1;

											}
										}
									}
								}
								break;
							case 2:
								neighbourNode = node;
								neighbourNode[1] = neighbourNode[1] + 1;
								if (!discovered.contains(neighbourNode)) {
									try {
										if (board[neighbourNode[0]][neighbourNode[1]].equals("      ")) {
											// if the node is empty, we add it to the queue
											
											System.out.println(neighbourNode[0] + " " + neighbourNode[1]);
											
											queue.add(neighbourNode);
										}

									} catch (Exception e) {
										// nothing happens
										for (int k = 0; k < GameSettings.jewelsPositions.size(); k++) {
											if (GameSettings.jewelsPositions.get(GameSettings.jewelsPositions.keySet()
													.toArray()[k])[0] == neighbourNode[0]
													&& GameSettings.jewelsPositions.get(GameSettings.jewelsPositions
															.keySet().toArray()[k])[1] == neighbourNode[1]) {
												reachableJewels += 1;
											}
										}
									}
								}
								break;
							case 3:
								neighbourNode = node;
								neighbourNode[0] = neighbourNode[0] + 1;
								if (!discovered.contains(neighbourNode)) {
									try {
										if (board[neighbourNode[0]][neighbourNode[1]].equals("      ")) {
											// if the node is empty, we add it to the queue
											
											System.out.println(neighbourNode[0] + " " + neighbourNode[1]);
											
											queue.add(neighbourNode);
										}

									} catch (Exception e) {
										// nothing happens
										for (int k = 0; k < GameSettings.jewelsPositions.size(); k++) {
											if (GameSettings.jewelsPositions.get(GameSettings.jewelsPositions.keySet()
													.toArray()[k])[0] == neighbourNode[0]
													&& GameSettings.jewelsPositions.get(GameSettings.jewelsPositions
															.keySet().toArray()[k])[1] == neighbourNode[1]) {
												reachableJewels += 1;
											}
										}
									}
								}
								break;
							case 4:
								neighbourNode = node;
								neighbourNode[0] = neighbourNode[0] - 1;
								if (!discovered.contains(neighbourNode)) {
									try {
										if (board[neighbourNode[0]][neighbourNode[1]].equals("      ")) {
											// if the node is empty, we add it to the queue
											
											System.out.println(neighbourNode[0] + " " + neighbourNode[1]);
											
											queue.add(neighbourNode);
										}

									} catch (Exception e) {
										// nothing happens
										for (int k = 0; k < GameSettings.jewelsPositions.size(); k++) {
											if (GameSettings.jewelsPositions.get(GameSettings.jewelsPositions.keySet()
													.toArray()[k])[0] == neighbourNode[0]
													&& GameSettings.jewelsPositions.get(GameSettings.jewelsPositions
															.keySet().toArray()[k])[1] == neighbourNode[1]) {
												reachableJewels += 1;
											}
										}
									}
								}
								break;
							}

							discovered.add(neighbourNode);

						} catch (Exception e) {
						}

					}
				}

			}

		}
		System.out.println(reachableJewels);
		if (reachableJewels * GameSettings.numberPlayers == GameSettings.jewelsPositions.size()
				* GameSettings.numberPlayers) {
			return true;
		} else {
			return false;
		}
	}
}
