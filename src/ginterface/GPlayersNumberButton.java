package ginterface;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import code.GameSettings;

public class GPlayersNumberButton {

	public static boolean ticked = false;
	public static boolean PlayersNumberChosen = false;

	public GPlayersNumberButton() {

		
		
		JFrame f = new JFrame("Welcome to Robot Turtles Java Edition!");

		
		
		JLabel label = new JLabel();
		label.setFont(new Font("StandardSmall", Font.BOLD, 18));
		label.setBounds(238, 20, 260, 50);
		label.setText("Select the number of players:");
		label.setForeground(Color.BLACK);
		label.setOpaque(false);

		
		
		
		JCheckBox ch1 = new JCheckBox("2");
		ch1.setBounds(300, 60, 50, 50);
		ch1.setForeground(Color.BLACK);
		ch1.setOpaque(false);
		JCheckBox ch2 = new JCheckBox("3");
		ch2.setBounds(350, 60, 50, 50);
		ch2.setForeground(Color.BLACK);
		ch2.setOpaque(false);
		JCheckBox ch3 = new JCheckBox("4");
		ch3.setBounds(400, 60, 50, 50);
		ch3.setForeground(Color.BLACK);
		ch3.setOpaque(false);
		ButtonGroup bgr = new ButtonGroup();
		bgr.add(ch1);
		bgr.add(ch2);
		bgr.add(ch3);
		f.add(ch1);
		f.add(ch2);
		f.add(ch3);
		
		
		f.add(label);

		JButton b = new JButton(new ImageIcon("src/images/Play.jpg"));
		b.setOpaque(false);
		b.setBounds(303, 190, 130, 54);
		f.add(b);
		
		
		JLabel bg = new JLabel(new ImageIcon("src/images/MenuBG.jpg"));
		bg.setBounds(0, 0, 736, 520);
		f.add(bg);
		

		f.setSize(736, 520);

		// on centre la fenetre

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
		f.setLocation(x, y);

		f.setLayout(null);
		f.setVisible(true);
		ch1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				GameSettings.numberPlayers = 2;
				ticked = true;
			}
		});
		ch2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				GameSettings.numberPlayers = 3;
				ticked = true;
			}
		});
		ch3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				GameSettings.numberPlayers = 4;
				ticked = true;
			}
		});

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ticked) {
					PlayersNumberChosen = true;
					f.dispose();
				}
			}
		});

		// to close an AWT window when the close button is pressed
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				f.dispose(); // use dispose method
			}
		});

	}

	public static void main(String args[]) throws InterruptedException {
		new GPlayersNumberButton();
		while (!PlayersNumberChosen) {
			Thread.sleep(500);
			// petite astuce pas tres opti pour attendre que la fenetre se ferme ( et donc
			// que le nombre de joueurs soit choisi pour passer a la suite)
		}

	}
}