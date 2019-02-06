import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class OptionScreen extends JFrame{
	
	public OptionScreen() {
		
		getContentPane().setBackground(Color.CYAN);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JButton all, compare, exit;
		
		JLabel title = new JLabel("What Would You Like To Do?");
		title.setFont(title.getFont().deriveFont(32.0f));
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 4;
		c.weighty = 0.8;
		c.gridx = 0;
		c.gridy = 0;
		add(title, c);
		
		//All data button
		all = new JButton("View All Data");
		all.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
				AllData ads = new AllData();
			}
				
		});
		all.setFont(all.getFont().deriveFont(26.0f));
		c.insets = new Insets(0,5,0,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.weighty = 0.8;
		c.gridx = 0;
		c.gridy = 1;
		add(all, c);
		
		//All data button
		compare = new JButton("Compare Data");
		compare.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
				Compare cs = new Compare();
			}
				
		});
		compare.setFont(compare.getFont().deriveFont(26.0f));
		c.insets = new Insets(0,5,0,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.weighty = 0.8;
		c.gridx = 2;
		c.gridy = 1;
		add(compare, c);		
		
		//Exit button
		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
				
		});
		c.fill = GridBagConstraints.CENTER;
		exit.setFont(exit.getFont().deriveFont(24.0f));
		c.gridwidth = 1;
		c.weighty = 0.8;
		c.gridx = 1;
		c.gridy = 4;
		add(exit, c);
		
		setVisible(true);
	}
}
