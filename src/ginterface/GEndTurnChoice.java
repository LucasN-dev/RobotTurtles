package ginterface;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import code.Card;
import code.GameSettings;

public class GEndTurnChoice {
	
	public HashMap<Integer, JButton> cardsButtons;
	public static boolean boolEndTurnChoice;

	
	public GEndTurnChoice(ArrayList<Card> Main) {
		boolEndTurnChoice= false;

		Frame f = new Frame("What do you want to do ?");

		Font myFont = new Font("Large", Font.BOLD, 25);
		
		
		Label label = new Label("What do you want to do with your hand of cards ?");
		label.setBounds(195, 40, 600, 50);
		label.setFont(myFont);
		f.add(label);
		
		cardsButtons = new HashMap<Integer, JButton>();
		for (int i = 0; i < Main.size(); i++) {
			
			JButton b = new JButton(new ImageIcon("src/images/"+Main.get(i).getType()+".png"));
			b.setBounds(50 + 182 * i, 140, 162, 219);
			cardsButtons.put(i,b);
			
			
			f.add(b);
		}

		Button b1 = new Button("Discard one or multiple cards");
		b1.setBounds(153, 450, 270, 50);
		f.add(b1);

		Button b2 = new Button("Keep my hand");
		b2.setBounds(553, 450, 270, 50);
		f.add(b2);

		

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - f.getWidth()) / 2.2);
		int y = (int) ((dimension.getHeight() - f.getHeight()) / 4.2);
		f.setLocation(x, y);
		f.setSize(1000, 600);
		f.setLayout(null);
		f.setVisible(true);

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				GameSettings.playerChoice=1;
				boolEndTurnChoice=true;
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				GameSettings.playerChoice=2;
				boolEndTurnChoice=true;
			}
		});
		
		

	}

}