package ginterface;

import java.awt.Dimension;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import code.GameSettings;
import code.Player;

public class GBugPlayer {
	
	public static boolean done=false;
	
	public GBugPlayer(Player p) {
		
		int[] xpos= {20, 140, 260};
		
		JFrame f = new JFrame("Bug a player!");

		Label label = new Label("Select a player to bug");
		label.setBounds(10, 10, 200, 50);

		int xi=0;
		
		for (int i = 0; i < GameSettings.numberPlayers; i++) {
			
			Player p2=GameSettings.players.get(i);
			
			if (!p2.equals(p)
					&& !GameSettings.playersOutOfTheGame.contains(p2)) {
				JButton b = new JButton(
						new ImageIcon("src/images/" + p2.getTurtle().getType() + "/S.png"));
				
			
				
				b.setBounds(xpos[xi], 20, 98, 98);
				f.add(b);
				xi=xi+1;
				
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						p2.program.reverseProgram();
						p2.isBugged=true;
						done=true;
						f.dispose();
					}
				});
			}
		}
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - f.getWidth()) / 2.2);
		int y = (int) ((dimension.getHeight() - f.getHeight()) / 4.2);
		f.setLocation(x, y);

		f.setSize(98*(xi+1), 200);

		f.setLayout(null);
		f.setVisible(true);
	}
}