import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AllData extends JFrame{
	
	JTable table;
	JScrollPane scrollPane;
	JButton goBack, exit;
	
	public AllData() {
		
		getContentPane().setBackground(Color.CYAN);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel title = new JLabel("All Population Data");
		title.setFont(title.getFont().deriveFont(32.0f));
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 2;
		c.weighty = 0.8;
		c.gridx = 0;
		c.gridy = 0;
		add(title, c);
		
		allDataTable(c);
		
		goBack = new JButton("Return");
		goBack.setFont(goBack.getFont().deriveFont(24.0f));
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
				OptionScreen os = new OptionScreen();
			}
		});
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 2;
		c.ipadx = 0;
		c.ipady = 0;
		c.weighty = 0.8;
		c.gridx = 2;
		c.gridy = 9;
		add(goBack, c);
		
		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
				
		});
		c.fill = GridBagConstraints.CENTER;
		exit.setFont(exit.getFont().deriveFont(24.0f));
		c.gridwidth = 2;
		c.weighty = 0.8;
		c.gridx = 3;
		c.gridy = 9;
		add(exit, c);
		
		setVisible(true);
	}
	
	void allDataTable(GridBagConstraints c) {
		//Make headers for the columns
		String[] columns = {"State/Region", "2010", "2011", 
				"2012","2013", "2014",
				"2015", "2016"};
		//Make a 2D array of all the classes
		Object[][] data2D = new Object[Reader.place.size()][8];
		for(int i = 0; i < Reader.place.size(); i++) {
			String temp = Reader.place.get(i);
			if(temp.charAt(0) == '.') temp = temp.substring(1, temp.length());
			data2D[i][0] = temp;
			for(int j = 0; j < Reader.data.get(i).size(); j++) {
				data2D[i][j+1] = Reader.data.get(i).get(j);
			}
		}
		//Create the table and add a scroll pane to panel
		table = new JTable(data2D, columns);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.getColumnModel().getColumn(7).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(5);
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(5);
		table.getColumnModel().getColumn(5).setPreferredWidth(90);
		table.getColumnModel().getColumn(6).setPreferredWidth(5);
		table.setFont(table.getFont().deriveFont(20.0f));
		table.setRowHeight(30);
		scrollPane  = new JScrollPane(table);
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 4;
		c.ipady = 150;
		c.ipadx = 900;
		c.gridheight = 3;
		c.gridx = 0;
		c.gridy = 1;
		add(scrollPane, c);
		setVisible(true);
		
	}
}
