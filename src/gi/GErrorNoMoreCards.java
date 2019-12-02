package gi;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GErrorNoMoreCards {
	
	public static boolean closed;

	public GErrorNoMoreCards() {
		
		closed=false;
		
		Frame f = new Frame("Error!");

		Font myFont = new Font("Large", Font.BOLD, 25);
		
		
		Label label = new Label("The main deck and the discard deck are empty.");
		label.setBounds(20, 40, 600, 50);
		label.setFont(myFont);
		f.add(label);
		
		
		JButton bok=new JButton("OK");  
	    bok.setBounds(263,100,70,50); 
	    f.add(bok);
		
		f.setSize(600, 180);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
		f.setLocation(x, y);

		f.setLayout(null);
		f.setVisible(true);
		
		
		bok.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
			            closed=true;
			            f.dispose();
			            
			        }  
			    }); 
	}
}
