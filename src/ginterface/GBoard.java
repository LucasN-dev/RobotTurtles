package ginterface;

import java.awt.*;

import javax.swing.JFrame;

import code.GameSettings;
import code.IceWall;
import code.Jewel;
import code.StoneWall;
import code.Board;
import code.TurtleTile;

public class GBoard extends Canvas {

	/**
	 * 
	 */

	// to avoid a warning on GBoard
	private static final long serialVersionUID = 1L;

	// in this array we store all the positions to place tiles on the board
	public static int[] giPosistions = { 45, 146, 247, 349, 450, 551, 653, 754, };
	public static JFrame f = new JFrame("Robot Turtles Java Edition");
	public static GBoard m = new GBoard();

	public void paint(Graphics g) {

		Object[][] board = Board.board;
		Toolkit t = Toolkit.getDefaultToolkit();

		Image background = t.getImage("src/images/Background.png");
		g.drawImage(background, 0, 0, this);

		// we check all the cell in the board and when there's an object in it we put it
		// on the graphic interface
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getClass() == TurtleTile.class) {
					String turtle = ((TurtleTile) board[i][j]).getType();
					char orientation = GameSettings.turtlesOrientations.get(turtle);
					Image im = t.getImage("src/images/" + turtle + "/" + orientation + ".png");
					g.drawImage(im, giPosistions[j], giPosistions[i], this);
				}

				else if (board[i][j].getClass() == Jewel.class) {
					String jewel = ((Jewel) board[i][j]).getType();
					Image im = t.getImage("src/images/" + jewel + ".png");
					g.drawImage(im, giPosistions[j], giPosistions[i], this);

				}

				else if (board[i][j].getClass() == StoneWall.class) {
					Image im = t.getImage("src/images/StoneWall.png");
					g.drawImage(im, giPosistions[j], giPosistions[i], this);
				}

				else if (board[i][j].getClass() == IceWall.class) {
					Image im = t.getImage("src/images/IceWall.png");
					g.drawImage(im, giPosistions[j], giPosistions[i], this);
				}

			}

		}

		// if the player is using the laser, we put the animation at the right place on
		// the board

		// it is very important in the gif image settings to have it only run once, to
		// avoid bugs
		if (GameSettings.drawLaser) {

			if (GameSettings.laserGIPosition[0] >= 0 && GameSettings.laserGIPosition[0] < 8
					&& GameSettings.laserGIPosition[1] >= 0 && GameSettings.laserGIPosition[1] < 8) {

				Image laser = t.getImage("src/images/Laser/" + GameSettings.laserOrientation + ".gif");
				g.drawImage(laser, giPosistions[GameSettings.laserGIPosition[1]],
						giPosistions[GameSettings.laserGIPosition[0]], this);
			} else if (GameSettings.laserGIPosition[0] == -1 && GameSettings.laserGIPosition[1] < 8) {
				Image laser = t.getImage("src/images/Laser/" + GameSettings.laserOrientation + ".gif");
				g.drawImage(laser, giPosistions[GameSettings.laserGIPosition[1]], -56, this);

			}

			else if (GameSettings.laserGIPosition[0] < 8 && GameSettings.laserGIPosition[1] == -1) {
				Image laser = t.getImage("src/images/Laser/" + GameSettings.laserOrientation + ".gif");
				g.drawImage(laser, -56, giPosistions[GameSettings.laserGIPosition[0]], this);

			}

		}
		// next part is important
		else {
			Image laser = t.getImage("src/images/Laser/" + GameSettings.laserOrientation + ".gif");
			g.drawImage(laser, -1000, -1000, this);
			laser.flush(); // the flush is necessary to reset the loop counter of the gif because it runs
							// only once to avoid other bugs
		}

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	//method to update the board when a player runs its program
	public static void updateGI() {
		f.getContentPane().removeAll();
		f.repaint();
		f.add(m);
		f.setSize(917, 949);
		f.setVisible(true);

	}

	//initialization
	public static void initializeBoardGI() {
		f.add(m);
		f.setSize(917, 949);
		f.setVisible(true);
	}

	public static void updateLaserGi(char orientation, int[] position) throws InterruptedException {

		// in case there's a laser being shot, we wait 1sec then stop trying to render
		// it.
		// it is nevertheless important to have a gif file that only runs once (that's
		// one parameter of gif files). If not the frame will keep refreshing even
		// though the gif's removed

		GameSettings.drawLaser = true;

		GameSettings.laserOrientation = orientation;
		GameSettings.laserGIPosition[0] = position[0];
		GameSettings.laserGIPosition[1] = position[1];

		switch (orientation) {

		// we have -2 because the laser image is two blocks long
		case 'N':
			GameSettings.laserGIPosition[0] = GameSettings.laserGIPosition[0] - 2;
			break;
		case 'S':
			GameSettings.laserGIPosition[0] = GameSettings.laserGIPosition[0] + 1;
			break;
		case 'E':
			GameSettings.laserGIPosition[1] = GameSettings.laserGIPosition[1] + 1;
			break;
		case 'W':
			GameSettings.laserGIPosition[1] = GameSettings.laserGIPosition[1] - 2;
			break;
		}

		GBoard.updateGI();
		Thread.sleep(1000);

		GameSettings.drawLaser = false;

	}

}