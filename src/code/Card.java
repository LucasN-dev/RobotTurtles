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

	public void runCard(Object[][] board, Player p) {

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

						// case where the turtle is about to hit a wall
						try {
							if (((StoneWall) board[position[0] - 1][position[1]]).getType().equals("StoneWall")
									|| ((IceWall) board[position[0] - 1][position[1]]).getType().equals("IceWall")) {
								// the turtle doesn't move but turns around
								orientation = 'S';
								GameSettings.updateTurtleOrientation(p.turtle.getType(), orientation);
							}

						} catch (Exception e) {
							// case where there's no turtle and no wall
							position[0] = position[0] - 1;
							board[positionSave[0]][positionSave[1]] = "      ";
							board[position[0]][position[1]] = p.turtle;
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
						try {
							if (((StoneWall) board[position[0]][position[1] + 1]).getType().equals("StoneWall")
									|| ((IceWall) board[position[0]][position[1] + 1]).getType().equals("IceWall")) {
								// the turtle doesn't move but turns around
								orientation = 'W';
								GameSettings.updateTurtleOrientation(p.turtle.getType(), orientation);
							}

						} catch (Exception e) {
							position[1] = position[1] + 1;
							board[positionSave[0]][positionSave[1]] = "      ";
							board[position[0]][position[1]] = p.turtle;
						}

						System.out.println("yes");
						p.discardDeck.add(this);
						instructions.remove();
					}
				}

				else if (orientation == 'S') {

					try {
						if (((TurtleTile) board[position[0]][position[1] + 1]).getType().equals("PurpleTurtle")
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
						try {
							if (((StoneWall) board[position[0] + 1][position[1]]).getType().equals("StoneWall")
									|| ((IceWall) board[position[0] + 1][position[1]]).getType().equals("IceWall")) {
								// the turtle doesn't move but turns around
								orientation = 'N';
								GameSettings.updateTurtleOrientation(p.turtle.getType(), orientation);
							}

						} catch (Exception e) {
							position[0] = position[0] + 1;
							board[positionSave[0]][positionSave[1]] = "      ";
							board[position[0]][position[1]] = p.turtle;
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
						try {
							if (((StoneWall) board[position[0]][position[1] - 1]).getType().equals("StoneWall")
									|| ((IceWall) board[position[0]][position[1] - 1]).getType().equals("IceWall")) {
								// the turtle doesn't move but turns around
								orientation = 'E';
								GameSettings.updateTurtleOrientation(p.turtle.getType(), orientation);
							}

						} catch (Exception e) {
							position[1] = position[1] - 1;
							board[positionSave[0]][positionSave[1]] = "      ";
							board[position[0]][position[1]] = p.turtle;
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
				// le cas ou on essaye de sortir du plateau, on renvoi la tortue à la case
				// départ
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
	}

}
