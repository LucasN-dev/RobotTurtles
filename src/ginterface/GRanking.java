package ginterface;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import code.GameSettings;

public class GRanking {

	public int[] xPosistions = { 455, 290, 630, 80 };
	public int[] yPosistions = { 62, 136, 150, 400 };

	public GRanking() {
		
		JFrame f = new JFrame("Game results");

		Font myFont = new Font("Large", Font.BOLD, 25);

		
		JLabel f1 = new JLabel(
				new ImageIcon("src/images/fire1.gif"));
		f1.setBounds(0, 40, 277, 269);
		f.add(f1);
		
		JLabel f2 = new JLabel(
				new ImageIcon("src/images/fire2.gif"));
		f2.setBounds(770, 40, 277, 269);
		f.add(f2);
		
		
		for (int i = 0; i < GameSettings.ranking.size(); i++) {
			
			
			JLabel bTortue = new JLabel(
					new ImageIcon("src/images/" + GameSettings.ranking.get(i).getTurtle().getType() + "/S.png"));
			bTortue.setBounds(xPosistions[i], yPosistions[i], 98, 98);
			f.add(bTortue);
			
			
			
			Label label = new Label(GameSettings.ranking.get(i).getName());
			label.setBounds(xPosistions[i]-10, yPosistions[i]-40, 160, 30);
			label.setFont(myFont);
			f.add(label);

		}
		
		JLabel podium = new JLabel(new ImageIcon("src/images/podium.png"));
		podium.setBounds(175, 100, 650, 400);
		f.add(podium);

		f.setSize(1025, 600);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
		f.setLocation(x, y);

		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	
	public static void main(String args[]) throws InterruptedException {
		new GRanking();
		
	}
	
}
