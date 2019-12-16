package ginterface;

import java.awt.*;
import java.awt.event.*;

import code.GameSettings;

public class GPlayersNumberButton {

	public static boolean ticked = false;
	public static boolean PlayersNumberChosen = false;

	public GPlayersNumberButton() {

		Frame f = new Frame("Choose the number of players");

		Label label = new Label();
		label.setFont(new Font("StandardSmall", Font.BOLD, 18));
		label.setBounds(28, 40, 350, 50);
		label.setText("Choose the number of players:");

		CheckboxGroup cbg = new CheckboxGroup();
		Checkbox checkBox1 = new Checkbox("2", cbg, false);
		checkBox1.setBounds(100, 100, 50, 50);
		Checkbox checkBox2 = new Checkbox("3", cbg, false);
		checkBox2.setBounds(150, 100, 50, 50);
		Checkbox checkBox3 = new Checkbox("4", cbg, false);
		checkBox3.setBounds(200, 100, 50, 50);
		f.add(checkBox1);
		f.add(checkBox2);
		f.add(checkBox3);

		f.add(label);

		Button b = new Button("Confirm");
		b.setBounds(75, 190, 200, 50);
		f.add(b);

		f.setSize(350, 275);

		// on centre la fenetre

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
		f.setLocation(x, y);

		f.setLayout(null);
		f.setVisible(true);
		checkBox1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				GameSettings.numberPlayers = 2;
				ticked = true;
			}
		});
		checkBox2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				GameSettings.numberPlayers = 3;
				ticked = true;
			}
		});
		checkBox3.addItemListener(new ItemListener() {
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