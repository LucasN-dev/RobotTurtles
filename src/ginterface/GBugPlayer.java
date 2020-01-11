package ginterface;

import java.awt.Dimension;
import java.awt.Font;
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
		label.setBounds(60, 25, 300, 50);
		Font myFont = new Font("Large", Font.BOLD, 22);
		label.setFont(myFont);
		f.add(label);

		int xi=0;
		
		for (int i = 0; i < GameSettings.numberPlayers; i++) {
			
			Player p2=GameSettings.players.get(i);
			
			if (!p2.equals(p)
					&& !GameSettings.playersOutOfTheGame.contains(p2) && !p2.isBugged) {
				
				
				Label name = new Label(p2.getName());
				name.setBounds(60+xpos[xi], 200, 98, 20);
				Font myFont2 = new Font("LessLarge", Font.BOLD, 12);
				name.setFont(myFont2);
				f.add(name);
				
				
				JButton b = new JButton(
						new ImageIcon("src/images/" + p2.getTurtle().getType() + "/S.png"));
				
			
				
				b.setBounds(60+xpos[xi], 100, 98, 98);
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

		f.setSize(98*(xi+1)+130, 350);

		f.setLayout(null);
		f.setVisible(true);
	}
}
