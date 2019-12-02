package gi;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import code.GameSettings;
import code.Player;

public class GPlayerTurn {

	public static boolean boolChoice;
	public HashMap<Integer, JButton> buttons;
	
	public GPlayerTurn(int numjoueur, Player j) {
		boolChoice= false;
		Frame f = new Frame("What do you want to do ?");

		Label labelJoueur = new Label("It's player "+numjoueur + "'s turn.");
		labelJoueur.setBounds(375, 30, 350, 50);
		Font myFont = new Font("Large", Font.BOLD, 25);
		labelJoueur.setFont(myFont);
		f.add(labelJoueur);
		
		Label label1 = new Label("What do you want to do ?");
		label1.setBounds(335, 80, 320, 50);
		label1.setFont(myFont);
		f.add(label1);
		
		Label VotreJeu = new Label("Your hand:");
		VotreJeu.setBounds(50, 170, 200, 40);
		VotreJeu.setFont(myFont);
		f.add(VotreJeu);
		
		

		Button b1 = new Button("Complete my program");
		b1.setBounds(122, 480, 170, 50);
		f.add(b1);

		Button b2 = new Button("Build a wall");
		b2.setBounds(414, 480, 170, 50);
		f.add(b2);

		Button b3 = new Button("Run my program");
		b3.setBounds(706, 480, 170, 50);
		f.add(b3);
		
		
		JButton bTortue = new JButton(new ImageIcon("src/images/"+j.getTortue().getType()+"/S.png"));
		bTortue.setBounds(890 , 40, 98, 98);
		bTortue.setRolloverEnabled(false);
		bTortue.setBorderPainted(false);
		f.add(bTortue);
		
		
		buttons = new HashMap<Integer, JButton>();
		for (int i = 0; i < j.hand.size(); i++) {
			
			JButton b = new JButton(new ImageIcon("src/images/"+j.hand.get(i).getType()+".png"));
			b.setBounds(50 + 182 * i, 220, 162, 219);
			b.setRolloverEnabled(false);
			b.setBorderPainted(false);
			buttons.put(i,b);
			
			
			f.add(b);
		}
		

		f.setSize(1000, 600);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
		f.setLocation(x, y);

		f.setLayout(null);
		f.setVisible(true);

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				GameSettings.playerChoice=1;
				boolChoice=true;
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				GameSettings.playerChoice=2;
				boolChoice=true;
			}
		});
		
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				GameSettings.playerChoice=3;
				boolChoice=true;
			}
		});

	}

}