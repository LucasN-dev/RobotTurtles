package ginterface;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import code.GameSettings;
import code.Jewel;
import code.StoneWall;
import code.Board;
import code.TurtleTile;

public class GBoard extends Canvas {

	public int[] giPosistions = { 45, 146, 247, 349, 450, 551, 653, 754, };
	public static JFrame f = new JFrame("Robot Turtles Java Edition");
	public static GBoard m = new GBoard();

	public void paint(Graphics g) {

		Object[][] board = Board.board;
		Toolkit t = Toolkit.getDefaultToolkit();

		Image background = t.getImage("src/images/Background.png");
		g.drawImage(background, 0, 0, this);

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

			}

		}

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
		// next part is very important
		else {
			Image laser = t.getImage("src/images/Laser/" + GameSettings.laserOrientation + ".gif");
			g.drawImage(laser, -1000, -1000, this);
			laser.flush(); // the flush is necesary to reset the loop counter of the gif because it runs
							// only onceto avoid other bugs
		}

	}

	public static void updateGI() {
		f.getContentPane().removeAll();
		f.repaint();
		f.add(m);
		f.setSize(917, 949);
		f.setVisible(true);

	}

	public static void initializeBoardGI() {
		f.add(m);
		f.setSize(917, 949);
		f.setVisible(true);
	}

}