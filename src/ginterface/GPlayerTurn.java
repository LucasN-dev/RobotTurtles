package ginterface;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import code.GameSettings;
import code.Player;

public class GPlayerTurn {

	public static boolean boolChoice;
	public HashMap<Integer, JButton> buttons;

	public GPlayerTurn(Player p) {
		boolChoice = false;
		Frame f = new Frame("What do you want to do ?");

		Label labelJoueur = new Label("It's " + p.getName() + "'s turn.");
		labelJoueur.setBounds(375, 30, 320, 50);
		Font myFont = new Font("Large", Font.BOLD, 25);
		labelJoueur.setFont(myFont);
		f.add(labelJoueur);

		Label label1 = new Label("What do you want to do ?");
		label1.setBounds(335, 80, 320, 50);
		label1.setFont(myFont);
		f.add(label1);

		Label VotreJeu = new Label("Your hand:");
		VotreJeu.setBounds(50, 170, 200, 25);
		VotreJeu.setFont(myFont);
		f.add(VotreJeu);

		Button b1 = new Button("Complete my program");
		b1.setBounds(122, 480, 170, 50);
		f.add(b1);

		if (!p.stoneWalls.isEmpty() || !p.iceWalls.isEmpty()) {
			Button b2 = new Button("Build a wall");
			b2.setBounds(414, 480, 170, 50);
			f.add(b2);

			b2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					GameSettings.playerChoice = 2;
					boolChoice = true;
				}
			});

		}

		Button b3 = new Button("Run my program");
		b3.setBounds(706, 480, 170, 50);
		f.add(b3);

		if (!p.bugUsed) {
			Button bBug = new Button("Bug a player");
			bBug.setBounds(414, 580, 170, 50);
			f.add(bBug);
			
			bBug.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					GameSettings.playerChoice = 4;
					p.bugUsed=true;
					boolChoice = true;
				}
			});
			
		}

		JLabel bTortue = new JLabel(new ImageIcon("src/images/" + p.getTurtle().getType() + "/S.png"));
		bTortue.setBounds(890, 40, 98, 98);
		f.add(bTortue);

		Font myFont2 = new Font("LessLarge", Font.BOLD, 18);

		Label stoneNumber = new Label("x" + p.stoneWalls.size());
		stoneNumber.setBounds(25, 140, 40, 15);
		stoneNumber.setFont(myFont2);
		f.add(stoneNumber);

		Label iceNumber = new Label("x" + p.iceWalls.size());
		iceNumber.setBounds(125, 140, 40, 15);
		iceNumber.setFont(myFont2);
		f.add(iceNumber);

		JLabel sWalls = new JLabel(new ImageIcon("src/images/StoneWall.png"));
		sWalls.setBounds(10, 40, 98, 98);
		f.add(sWalls);

		JLabel iWalls = new JLabel(new ImageIcon("src/images/IceWall.png"));
		iWalls.setBounds(110, 40, 98, 98);
		f.add(iWalls);

		buttons = new HashMap<Integer, JButton>();
		for (int i = 0; i < p.hand.size(); i++) {

			JButton b = new JButton(new ImageIcon("src/images/" + p.hand.get(i).getType() + ".png"));
			b.setBounds(50 + 182 * i, 220, 162, 219);
			b.setRolloverEnabled(false);
			b.setBorderPainted(false);
			buttons.put(i, b);

			f.add(b);
		}

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - f.getWidth()) / 2.2);
		int y = (int) ((dimension.getHeight() - f.getHeight()) / 4.2);
		f.setLocation(x, y);

		f.setSize(1000, 690);

		f.setLayout(null);
		f.setVisible(true);

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				GameSettings.playerChoice = 1;
				boolChoice = true;
			}
		});

		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				GameSettings.playerChoice = 3;
				boolChoice = true;
			}
		});

		// to close an AWT window when the close button is pressed
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				f.dispose(); // use dispose method
			}
		});
	}

}