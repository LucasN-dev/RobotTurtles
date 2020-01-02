package code;

import java.util.ArrayDeque;

import ginterface.GBoard;
import ginterface.GDiscard;

public class Card {

	public String type;

	public Card(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void runCard(Object[][] board, Player p) throws InterruptedException {

		ArrayDeque<Card> instructions = p.program.getProgram();
		int[] position = GameSettings.turtlesPositions.get(p.turtle.getType());
		char orientation = GameSettings.turtlesOrientations.get(p.turtle.getType());
		int[] positionSave = new int[2];

		if (this.getType().equals("BlueCard")) {
			try {
				positionSave[0] = position[0];
				positionSave[1] = position[1];
				if (orientation == 'N') {

					// case where the turtle is about to hit another turtle
					try {
						if (((TurtleTile) board[position[0] - 1][position[1]]).getType().equals("PurpleTurtle")
								|| ((TurtleTile) board[position[0] - 1][position[1]]).getType().equals("GreenTurtle")
								|| ((TurtleTile) board[position[0] - 1][position[1]]).getType().equals("RedTurtle")
								|| ((TurtleTile) board[position[0] - 1][position[1]]).getType().equals("BlueTurtle")) {

							// we put both turtles back at their starting positions
							GameSettings.turtleCollisionBackToStart(p.turtle,
									(TurtleTile) board[position[0] - 1][position[1]], board);
							// we remove the turtle for the previous positions
							board[positionSave[0]][positionSave[1]] = "      ";
							board[positionSave[0] - 1][positionSave[1]] = "      ";

							p.discardDeck.add(this);
							instructions.remove();
						}

					} catch (Exception ex) {

						// case where the turtle is about to hit a stone wall
						try {
							if (((StoneWall) board[position[0] - 1][position[1]]).getType().equals("StoneWall")) {
								// the turtle doesn't move but turns around
								orientation = 'S';
								GameSettings.updateTurtleOrientation(p.turtle.getType(), orientation);
							}

						} catch (Exception e) {

							// if it's not a stone wall we check for ice walls
							// case where the turtle is about to hit an ice wall
							try {
								if (((IceWall) board[position[0] - 1][position[1]]).getType().equals("IceWall")) {
									// the turtle doesn't move but turns around
									orientation = 'S';
									GameSettings.updateTurtleOrientation(p.turtle.getType(), orientation);
								}
							} catch (Exception e2) {
								// case where there's no turtle and no wall
								position[0] = position[0] - 1;
								board[positionSave[0]][positionSave[1]] = "      ";
								board[position[0]][position[1]] = p.turtle;
							}

						}
					}

					System.out.println("yes");
					p.discardDeck.add(this);
					instructions.remove();
				}

				else if (orientation == 'E') {

					try {
						if (((TurtleTile) board[position[0]][position[1] + 1]).getType().equals("PurpleTurtle")
								|| ((TurtleTile) board[position[0]][position[1] + 1]).getType().equals("GreenTurtle")
								|| ((TurtleTile) board[position[0]][position[1] + 1]).getType().equals("RedTurtle")
								|| ((TurtleTile) board[position[0]][position[1] + 1]).getType().equals("BlueTurtle")) {

							// we put both turtles back at their starting positions
							GameSettings.turtleCollisionBackToStart(p.turtle,
									(TurtleTile) board[position[0]][position[1] + 1], board);
							board[positionSave[0]][positionSave[1]] = "      ";
							board[positionSave[0]][positionSave[1] + 1] = "      ";
							p.discardDeck.add(this);
							instructions.remove();
						}

					} catch (Exception ex) {

						// case where the turtle is about to hit a stone wall
						try {
							if (((StoneWall) board[position[0]][position[1] + 1]).getType().equals("StoneWall")) {
								// the turtle doesn't move but turns around
								orientation = 'W';
								GameSettings.updateTurtleOrientation(p.turtle.getType(), orientation);
							}

						} catch (Exception e) {

							// if it's not a stone wall we check for ice walls
							// case where the turtle is about to hit an ice wall
							try {
								if (((IceWall) board[position[0]][position[1] + 1]).getType().equals("IceWall")) {
									// the turtle doesn't move but turns around
									orientation = 'W';
									GameSettings.updateTurtleOrientation(p.turtle.getType(), orientation);
								}
							} catch (Exception e2) {
								position[1] = position[1] + 1;
								board[positionSave[0]][positionSave[1]] = "      ";
								board[position[0]][position[1]] = p.turtle;
							}
						}
					}
					System.out.println("yes");
					p.discardDeck.add(this);
					instructions.remove();
				}

				else if (orientation == 'S') {
					
					System.out.println("un");
					
					try {
						if (((TurtleTile) board[position[0] + 1][position[1]]).getType().equals("PurpleTurtle")
								|| ((TurtleTile) board[position[0] + 1][position[1]]).getType().equals("GreenTurtle")
								|| ((TurtleTile) board[position[0] + 1][position[1]]).getType().equals("RedTurtle")
								|| ((TurtleTile) board[position[0] + 1][position[1]]).getType().equals("BlueTurtle")) {

							// we put both turtles back at their starting positions
							GameSettings.turtleCollisionBackToStart(p.turtle,
									(TurtleTile) board[position[0] + 1][position[1]], board);
							board[positionSave[0]][positionSave[1]] = "      ";
							board[positionSave[0] + 1][positionSave[1]] = "      ";
							p.discardDeck.add(this);
							instructions.remove();
						}

					} catch (Exception ex) {
						System.out.println("deux");
						// case where the turtle is about to hit a stone wall
						try {
							if (((StoneWall) board[position[0] + 1][position[1]]).getType().equals("StoneWall")) {
								System.out.println("PIERRE");
								// the turtle doesn't move but turns around
								orientation = 'N';
								GameSettings.updateTurtleOrientation(p.turtle.getType(), orientation);
							}

						} catch (Exception e) {
							System.out.println("trois");
							// if it's not a stone wall we check for ice walls
							// case where the turtle is about to hit an ice wall
							try {
								if (((IceWall) board[position[0] + 1][position[1]]).getType().equals("IceWall")) {
									System.out.println("quatre");
									// the turtle doesn't move but turns around
									orientation = 'N';
									GameSettings.updateTurtleOrientation(p.turtle.getType(), orientation);
								}
							} catch (Exception e2) {
								System.out.println("what");
								position[0] = position[0] + 1;
								board[positionSave[0]][positionSave[1]] = "      ";
								board[position[0]][position[1]] = p.turtle;
							}
						}
					}
					System.out.println("yes");
					p.discardDeck.add(this);
					instructions.remove();
				}

				else if (orientation == 'W') {

					try {
						if (((TurtleTile) board[position[0]][position[1] - 1]).getType().equals("PurpleTurtle")
								|| ((TurtleTile) board[position[0]][position[1] - 1]).getType().equals("GreenTurtle")
								|| ((TurtleTile) board[position[0]][position[1] - 1]).getType().equals("RedTurtle")
								|| ((TurtleTile) board[position[0]][position[1] - 1]).getType().equals("BlueTurtle")) {

							// we put both turtles back at their starting positions
							GameSettings.turtleCollisionBackToStart(p.turtle,
									(TurtleTile) board[position[0]][position[1] - 1], board);
							board[positionSave[0]][positionSave[1]] = "      ";
							board[positionSave[0]][positionSave[1] - 1] = "      ";
							p.discardDeck.add(this);
							instructions.remove();
						}

					} catch (Exception ex) {

						// case where the turtle is about to hit a stone wall
						try {
							if (((StoneWall) board[position[0]][position[1] - 1]).getType().equals("StoneWall")) {
								// the turtle doesn't move but turns around
								orientation = 'E';
								GameSettings.updateTurtleOrientation(p.turtle.getType(), orientation);
							}

						} catch (Exception e) {

							// if it's not a stone wall we check for ice walls
							// case where the turtle is about to hit an ice wall
							try {
								if (((IceWall) board[position[0]][position[1] - 1]).getType().equals("IceWall")) {
									// the turtle doesn't move but turns around
									orientation = 'E';
									GameSettings.updateTurtleOrientation(p.turtle.getType(), orientation);
								}
							} catch (Exception e2) {
								position[1] = position[1] - 1;
								board[positionSave[0]][positionSave[1]] = "      ";
								board[position[0]][position[1]] = p.turtle;
							}
						}
					}
					System.out.println("yes");
					p.discardDeck.add(this);
					instructions.remove();
				}

				GameSettings.updateTurtlePosition(p.turtle.getType(), position[0], position[1]);
				Thread.sleep(700);
				GBoard.updateGI();

			} catch (Exception e) {
				// le cas ou on essaye de sortir du plateau, on renvoi la tortue � la case
				// d�part
				position[0] = GameSettings.turtlesStartingPositions.get(p.turtle.getType())[0];
				position[1] = GameSettings.turtlesStartingPositions.get(p.turtle.getType())[1];
				board[position[0]][position[1]] = p.turtle;

				p.discardDeck.add(this);
				instructions.remove();
				orientation = 'S';
				GameSettings.updateTurtleOrientation(p.turtle.getType(), orientation);
				GameSettings.updateTurtlePosition(p.turtle.getType(), position[0], position[1]);

			}
		} else if ((this.getType().equals("YellowCard"))) {
			switch (orientation) {
			case 'N':
				orientation = 'W';
				break;
			case 'S':
				orientation = 'E';
				break;
			case 'E':
				orientation = 'N';
				break;
			case 'W':
				orientation = 'S';
				break;

			}
			GameSettings.updateTurtleOrientation(p.turtle.getType(), orientation);
			p.discardDeck.add(this);
			instructions.remove();

		} else if ((this.getType().equals("PurpleCard"))) {
			switch (orientation) {
			case 'N':
				orientation = 'E';
				break;
			case 'S':
				orientation = 'W';
				break;
			case 'E':
				orientation = 'S';
				break;
			case 'W':
				orientation = 'N';
				break;

			}

			GameSettings.updateTurtleOrientation(p.turtle.getType(), orientation);
			p.discardDeck.add(this);
			instructions.remove();

		}

		else if ((this.getType().equals("LaserCard"))) {
			int[] targetPosition = { position[0], position[1] };
			boolean laserOutOfBoard = false;
			switch (orientation) {
			case 'N':
				try {
					targetPosition[0] = targetPosition[0] - 1;
					while (board[targetPosition[0]][targetPosition[1]].equals("      ")) {
						targetPosition[0] = targetPosition[0] - 1;
					}
				} catch (Exception e) {
					// case where the laser gets out of the board, nothing happens
					laserOutOfBoard = true;
					GBoard.updateLaserGi(orientation, position);

				}
				break;
			case 'S':
				try {
					targetPosition[0] = targetPosition[0] + 1;
					while (board[targetPosition[0]][targetPosition[1]].equals("      ")) {
						targetPosition[0] = targetPosition[0] + 1;
					}
				} catch (Exception e) {
					// case where the laser gets out of the board, nothing happens
					laserOutOfBoard = true;
					GBoard.updateLaserGi(orientation, position);

				}

				break;
			case 'E':

				try {
					targetPosition[1] = targetPosition[1] + 1;
					while (board[targetPosition[0]][targetPosition[1]].equals("      ")) {
						targetPosition[1] = targetPosition[1] + 1;
					}
				} catch (Exception e) {
					// case where the laser gets out of the board, nothing happens
					laserOutOfBoard = true;
					GBoard.updateLaserGi(orientation, position);

				}
				break;
			case 'W':
				try {
					targetPosition[1] = targetPosition[1] - 1;
					while (board[targetPosition[0]][targetPosition[1]].equals("      ")) {
						targetPosition[1] = targetPosition[1] - 1;
					}
				} catch (Exception e) {
					// case where the laser gets out of the board, nothing happens
					laserOutOfBoard = true;
					GBoard.updateLaserGi(orientation, position);

				}
				break;

			}
			System.out.print(targetPosition[0] + " ");
			System.out.println(targetPosition[1]);

			if (!laserOutOfBoard) {
				try {
					if (((StoneWall) board[targetPosition[0]][targetPosition[1]]).getType().equals("StoneWall")) {
						// the laser hits the wall and does nothing
						GBoard.updateLaserGi(orientation, position);
					}

				} catch (Exception e) {
					// if it's not a stone wall we carry on other cases
					try {
						if (((IceWall) board[targetPosition[0]][targetPosition[1]]).getType().equals("IceWall")) {
							// if it's an ice wall it gets destroyed

							GBoard.updateLaserGi(orientation, position);
							board[targetPosition[0]][targetPosition[1]] = "      ";

						}

					} catch (Exception e2) {
						// if it's not an ice wall we carry on other cases
						try {
							String turtleType = ((TurtleTile) board[targetPosition[0]][targetPosition[1]]).getType();
							if (turtleType.equals("PurpleTurtle") || turtleType.equals("GreenTurtle")
									|| turtleType.equals("RedTurtle") || turtleType.equals("BlueTurtle")) {
								// if it's a turtle, it depends on the number o players
								if (GameSettings.numberPlayers == 2) {
									// switch case depending on the turtle current orientation, as it has to turn
									// around
									GBoard.updateLaserGi(orientation, position);

									switch (GameSettings.turtlesOrientations.get(turtleType)) {
									case 'N':
										GameSettings.turtlesOrientations.put(turtleType, 'S');
										break;
									case 'S':
										GameSettings.turtlesOrientations.put(turtleType, 'N');
										break;
									case 'E':
										GameSettings.turtlesOrientations.put(turtleType, 'W');
										break;
									case 'W':
										GameSettings.turtlesOrientations.put(turtleType, 'E');
										break;

									}

								}
								// if there are more than two players, we put the turtle back to its starting
								// position
								else {

									GBoard.updateLaserGi(orientation, position);

									GameSettings.updateTurtlePosition(turtleType,
											GameSettings.turtlesStartingPositions.get(turtleType)[0],
											GameSettings.turtlesStartingPositions.get(turtleType)[1]);
									GameSettings.updateTurtleOrientation(turtleType, 'S');
									board[GameSettings.turtlesStartingPositions
											.get(turtleType)[0]][GameSettings.turtlesStartingPositions
													.get(turtleType)[1]] = board[targetPosition[0]][targetPosition[1]];

									board[targetPosition[0]][targetPosition[1]] = "      ";

								}
							}

						} catch (Exception e3) {
							// last possibility : it's a jewel, the laser gets reflected and the turtle goes
							// back to its starting place
							if (GameSettings.numberPlayers == 2) {
								// switch case depending on the turtle current orientation, as it has to turn
								// around
								GBoard.updateLaserGi(orientation, position);

								String turtleType = p.getTurtle().getType();

								switch (GameSettings.turtlesOrientations.get(turtleType)) {
								case 'N':
									GameSettings.turtlesOrientations.put(turtleType, 'S');
									break;
								case 'S':
									GameSettings.turtlesOrientations.put(turtleType, 'N');
									break;
								case 'E':
									GameSettings.turtlesOrientations.put(turtleType, 'W');
									break;
								case 'W':
									GameSettings.turtlesOrientations.put(turtleType, 'E');
									break;

								}

							}

							else {
								GBoard.updateLaserGi(orientation, position);

								board[position[0]][position[1]] = "      ";

								GameSettings.updateTurtlePosition(p.getTurtle().getType(),
										GameSettings.turtlesStartingPositions.get(p.getTurtle().getType())[0],
										GameSettings.turtlesStartingPositions.get(p.getTurtle().getType())[1]);
								GameSettings.updateTurtleOrientation(p.getTurtle().getType(), 'S');

								board[GameSettings.turtlesStartingPositions
										.get(p.getTurtle().getType())[0]][GameSettings.turtlesStartingPositions
												.get(p.getTurtle().getType())[1]] = p.getTurtle();
							}

						}

					}

				}
			}
			p.discardDeck.add(this);
			instructions.remove();

		}
	}

}
