package ginterface;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import code.GameSettings;

public class GRanking {

	public int[] xPosistions = { 375, 82, 447 };
	public int[] yPosistions = { 62, 136, 136 };

	public GRanking() {
		
		JFrame f = new JFrame("Game results");

		Font myFont = new Font("Large", Font.BOLD, 25);

		JButton podium = new JButton(new ImageIcon("src/images/podium.png"));
		podium.setBounds(100, 100, 650, 400);
		podium.setRolloverEnabled(false);
		podium.setBorderPainted(false);
		f.add(podium);
		
		//GameSettings.ranking.size()
		
		for (int i = 0; i < 3; i++) {
			// GameSettings.ranking.get(i).getTurtle().getType()
			
			JLabel bTortue = new JLabel(
					new ImageIcon("src/images/" + "RedTurtle" + "/S.png"));
			bTortue.setBounds(xPosistions[i], yPosistions[i], 98, 98);
			//bTortue.setRolloverEnabled(false);
			//bTortue.setBorderPainted(false);
			f.add(bTortue);
			
			//GameSettings.ranking.get(i).getName()
			
			Label label = new Label("lol");
			label.setBounds(100, 40, 600, 50);
			label.setFont(myFont);
			f.add(label);

		}

		f.setSize(875, 600);

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
