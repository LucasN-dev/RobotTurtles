package ginterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import code.Card;
import code.TurtleTile;

public class GCompleteProgram {

	public static boolean completed = false;
	public static boolean closed = false;
	public HashMap<Integer, JButton> buttons;

	public GCompleteProgram(ArrayList<Card> Main, ArrayDeque<Card> program, TurtleTile t) {

		completed = false;
		closed = false;

		JFrame f = new JFrame("Complete your program");

		JButton bterminer = new JButton("Done");
		bterminer.setBounds(408, 400, 120, 50);
		f.add(bterminer);

		JLabel label1, label2;
		label1 = new JLabel("Click the card to add it to your program.");
		label1.setBounds(10, 20, 600, 30);
		label1.setFont(new Font("Standard", Font.BOLD, 18));
		label2 = new JLabel("Click on done once you are done.");
		label2.setBounds(10, 60, 600, 30);
		label2.setFont(new Font("Standard", Font.BOLD, 18));
		f.add(label1);
		f.add(label2);

		buttons = new HashMap<Integer, JButton>();
		for (int i = 0; i < Main.size(); i++) {

			JButton b = new JButton(new ImageIcon("src/images/" + Main.get(i).getType() + ".png"));
			b.setBounds(40 + 182 * i, 120, 162, 219);
			buttons.put(i, b);

			f.add(b);
		}

		JButton bTortue = new JButton(new ImageIcon("src/images/" + t.getType() + "/S.png"));
		bTortue.setBounds(876, 1, 98, 98);
		bTortue.setRolloverEnabled(false);
		bTortue.setBorderPainted(false);
		f.add(bTortue);

		bTortue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: put sound when the player clicks the turtle
			}
		});

		
		// we put as many action listener as needed
		try {
			buttons.get(0).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					program.add(Main.get(0));
					Main.remove(0);
					closed = true;
					f.dispose();

				}
			});

			buttons.get(1).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					program.add(Main.get(1));
					Main.remove(1);
					closed = true;
					f.dispose();

				}
			});
			buttons.get(2).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					program.add(Main.get(2));
					Main.remove(2);
					closed = true;
					f.dispose();

				}
			});

			buttons.get(3).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					program.add(Main.get(3));
					Main.remove(3);
					closed = true;
					f.dispose();

				}
			});
			buttons.get(4).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					program.add(Main.get(4));
					Main.remove(4);
					closed = true;
					f.dispose();

				}
			});
		} catch (Exception e) {

		}

		bterminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				closed = true;
				completed = true;
				f.dispose();

			}
		});

		// we adjust the window position

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - f.getWidth()) / 2.2);
		int y = (int) ((dimension.getHeight() - f.getHeight()) / 4.2);
		f.setLocation(x, y);

		f.setSize(1000, 600);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}