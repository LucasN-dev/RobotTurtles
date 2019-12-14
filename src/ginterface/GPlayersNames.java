package ginterface;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import code.GameSettings;

public class GPlayersNames {

	public static boolean PlayersNamesChosen = false;

	public GPlayersNames() {

		JFrame f = new JFrame("Choose players' names");

		Label label = new Label();
		label.setFont(new Font("StandardSmall", Font.BOLD, 18));
		label.setBounds(20, 5, 350, 40);
		label.setText("Type in players' names");
		f.add(label);

		Label label2 = new Label();
		label2.setFont(new Font("StandardSmallSmall", Font.BOLD, 14));
		label2.setBounds(22, 35, 350, 30);
		label2.setText("(10 characters maximum)");
		f.add(label2);

		JTextField name1, name2, name3, name4;
		name1 = new JTextField("Player 1");
		name1.setBounds(50, 80, 200, 30);
		name2 = new JTextField("Player 2");
		name2.setBounds(50, 130, 200, 30);
		name3 = new JTextField("Player 3");
		name3.setBounds(50, 180, 200, 30);
		name4 = new JTextField("Player 4");
		name4.setBounds(50, 230, 200, 30);
		
		

		int height = 305;

		switch (GameSettings.numberPlayers) {
		case 2:
			f.add(name1);
			f.add(name2);
			break;
		case 3:
			height = 355;
			f.add(name1);
			f.add(name2);
			f.add(name3);
			break;
		case 4:
			height = 405;
			f.add(name1);
			f.add(name2);
			f.add(name3);
			f.add(name4);
			break;

		}

		Button b = new Button("Confirm");
		b.setBounds(95, height - 125, 100, 50);
		f.add(b);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - f.getWidth()) / 2.4);
		int y = (int) ((dimension.getHeight() - f.getHeight()) / 2.7);
		f.setLocation(x, y);
		f.setSize(320, height);
		f.setLayout(null);
		f.setVisible(true);

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (name1.getText().length() > 10 || name2.getText().length() > 10 || name3.getText().length() > 10
						|| name4.getText().length() > 10) {
					Label label3 = new Label();
					label3.setFont(new Font("StandardSmallSmall", Font.BOLD, 14));
					label3.setBounds(22, 35, 350, 30);
					label3.setText("(10 characters maximum)");
					label3.setForeground(Color.RED);
					f.remove(label2);
					f.add(label3);
					f.invalidate();
					f.validate();
					f.repaint();
				}

				else {
					ArrayList<String> Names = new ArrayList<String>();
					Names.add(name1.getText());
					Names.add(name2.getText());
					Names.add(name3.getText());
					Names.add(name4.getText());
					
					GameSettings.PlayersNames= new ArrayList<String>();
							
					for (int i=0; i<GameSettings.numberPlayers; i++) {
						GameSettings.PlayersNames.add(Names.get(i));
					}
					f.dispose();
					PlayersNamesChosen=true;
				}
			}
		});

	}

	public static void main(String args[]) throws InterruptedException {
		new GPlayersNames();
		while (!PlayersNamesChosen) {
			Thread.sleep(500);
			// petite astuce pas tres opti pour attendre que la fenetre se ferme ( et donc
			// que le nombre de joueurs soit choisi pour passer a la suite)
		}

	}
}