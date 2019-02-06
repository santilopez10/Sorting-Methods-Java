import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Compare extends JFrame{
	
	JButton goBack, exit, enter, enter2, bubble, merge, eQuick, pQuick;
	JTable table;
	JScrollPane scrollPane;
	double[] change = new double[Reader.place.size()];
	JComboBox<String> yearY, yearX, sort;
	
	public Compare() {
		
		//Set Frame and its settings
		getContentPane().setBackground(Color.CYAN);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Title
		JLabel title = new JLabel("Compare Data");
		title.setFont(title.getFont().deriveFont(32.0f));
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 7;
		c.weighty = 0.8;
		c.gridx = 0;
		c.gridy = 0;
		add(title, c);
		//Second Title
		JLabel text1 = new JLabel("Percent Change In Population Of All States And Regions");
		c.insets = new Insets(0,5,0,5);
		text1.setFont(text1.getFont().deriveFont(26.0f));
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 5;
		c.weighty = 0.8;
		c.gridx = 0;
		c.gridy = 1;
		add(text1, c);
		
		JLabel text2 = new JLabel("From");
		c.insets = new Insets(0,5,0,5);
		text2.setFont(text2.getFont().deriveFont(26.0f));
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.weighty = 0.8;
		c.gridx = 0;
		c.gridy = 2;
		add(text2, c);
		
		//Drop down for year X
		String[] options = {"2010", "2011", 
				"2012", "2013", "2014", "2015", "2016"};
		yearX = new JComboBox<String>(options);
		c.fill = GridBagConstraints.CENTER;
		yearX.setFont(yearX.getFont().deriveFont(26.0f));
		c.gridwidth = 1;
		c.weighty = 0.8;
		c.gridx = 1;
		c.gridy = 2;
		add(yearX, c);
		
		JLabel text3 = new JLabel(" To ");
		c.insets = new Insets(0,5,0,5);
		text3.setFont(text3.getFont().deriveFont(26.0f));
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.weighty = 0.8;
		c.gridx = 2;
		c.gridy = 2;
		add(text3, c);
		
		//Drop down for year Y
		yearY = new JComboBox<String>(options);
		c.fill = GridBagConstraints.CENTER;
		yearY.setFont(yearY.getFont().deriveFont(26.0f));
		c.gridwidth = 1;
		c.weighty = 0.8;
		c.gridx = 3;
		c.gridy = 2;
		add(yearY, c);
		
		JLabel text5 = new JLabel("Sorted Using ");
		c.insets = new Insets(0,5,0,5);
		text5.setFont(text5.getFont().deriveFont(26.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.weighty = 0.8;
		c.gridx = 0;
		c.gridy = 3;
		add(text5, c);
		
		//Drop down for sorting type
		String[] types = {"Bubble Sort", "Merge Sort", "Quick Sort", "Enhanced Quick Sort"};
		sort = new JComboBox<String>(types);
		c.fill = GridBagConstraints.HORIZONTAL;
		sort.setFont(sort.getFont().deriveFont(26.0f));
		c.gridwidth = 1;
		c.weighty = 0.8;
		c.gridx = 2;
		c.gridy = 3;
		add(sort, c);
		
		//Enter button that creates a table of the percents and sorts them
		enter = new JButton("Enter");
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Remove any conflicting components
				try {
					remove(table);
					remove(scrollPane);
				}
				catch(Exception e) {
					
				}
				revalidate();
				repaint();
				allDataTable(c, (String)yearX.getSelectedItem(), (String)yearY.getSelectedItem(), yearX.getSelectedIndex(), yearY.getSelectedIndex());
			}
				
		});
		enter.setFont(enter.getFont().deriveFont(26.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.weighty = 0.8;
		c.gridx = 3;
		c.gridy = 3;
		add(enter, c);
		
		JLabel text4 = new JLabel("Closest Change To ");
		c.insets = new Insets(0,5,0,5);
		text4.setFont(text4.getFont().deriveFont(26.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.weighty = 0.8;
		c.gridx = 0;
		c.gridy = 4;
		add(text4, c);
		
		//Drop down for which state/region we want to see the closest state/region
		String[] places = new String[Reader.place.size()];
		for(int i = 0; i < Reader.place.size(); i++) {
			places[i] = Reader.place.get(i);
			if(places[i].charAt(0) == '.') places[i] = places[i].substring(1, places[i].length());
		}
		final JComboBox<String> place = new JComboBox<String>(places);
		c.fill = GridBagConstraints.HORIZONTAL;
		place.setFont(place.getFont().deriveFont(26.0f));
		c.gridwidth = 2;
		c.weighty = 0.8;
		c.gridx = 2;
		c.gridy = 4;
		add(place, c);
		
		enter2 = new JButton("Enter");
		enter2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Remove any conflicting components
				try {
					remove(table);
					remove(scrollPane);
				}
				catch(Exception e) {
					
				}
				revalidate();
				repaint();
				closest((String)place.getSelectedItem(), c);
				
			}
				
		});
		enter2.setFont(enter2.getFont().deriveFont(26.0f));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.weighty = 0.8;
		c.gridx = 4;
		c.gridy = 4;
		add(enter2, c);
		
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
		c.gridx = 4;
		c.gridy = 9;
		add(exit, c);
		
		
		setVisible(true);
	}
	
	void allDataTable(GridBagConstraints c, String yearX, String yearY, int yX, int yY) {
		//Make headers for the columns
		String[] columns = {"State/Region", yearX + " To " + yearY};
		sort(this.sort.getSelectedIndex(), yX, yY);
		//Make a 2D array of all the Places
		Object[][] data2D = new Object[Reader.place.size()][2];
		for(int i = 0; i < Reader.place.size(); i++) {
			String temp = Reader.place.get(i);
			if(temp.charAt(0) == '.') temp = temp.substring(1, temp.length());
			data2D[i][0] = temp;
			data2D[i][1] = change[i];
		}
		//Create the table and add a scroll pane to panel
		table = new JTable(data2D, columns);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.setFont(table.getFont().deriveFont(20.0f));
		table.setRowHeight(30);
		scrollPane  = new JScrollPane(table);
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 4;
		c.ipady = 400;
		c.ipadx = 100;
		c.gridheight = 4;
		c.gridx = 6;
		c.gridy = 1;
		add(scrollPane, c);
		setVisible(true);
		
	}
	
	//Method that gets the percent changes based on selected years and sorts them
	void sort(int choice, int yX, int yY) {
		double[] temp = new double[change.length];
		//Get the percent changes
		for(int i = 0; i < Reader.place.size(); i++) {
			double change1 = (Double.parseDouble(Reader.data.get(i).get(yY)) -  Double.parseDouble(Reader.data.get(i).get(yX)))
								/ Double.parseDouble(Reader.data.get(i).get(yX));
			change1 = ((double)((int) (change1 * 10000))) / 100;
			change[i] = change1;
			temp[i] = change1;
		}
		//Sort the changes using the specified algorithm and time it
		switch(choice) {
			case 0: long start = System.currentTimeMillis();
					bubbleSort();
					JOptionPane.showMessageDialog(null, "Time for BubbleSort: " + (System.currentTimeMillis() - start));
					fixAll(temp);
				break;
			case 1: start = System.currentTimeMillis();
					mergeSort(change, 0, change.length - 1);
					JOptionPane.showMessageDialog(null, "Time for MergeSort: " + (System.currentTimeMillis() - start));
					fixAll(temp);
				break;
			case 2: start = System.currentTimeMillis();
					pQuickSort(0, change.length - 1);
					JOptionPane.showMessageDialog(null, "Time for QuickSort: " + (System.currentTimeMillis() - start));
					fixAll(temp);
				break;
			case 3: start = System.currentTimeMillis();
					eQuickSort(0, change.length - 1);
					JOptionPane.showMessageDialog(null, "Time for eQuickSort: " + (System.currentTimeMillis() - start));
					fixAll(temp);
				break;
		}
	}
	
	
	void bubbleSort() {
		for(int i = Reader.place.size() - 1; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				if(change[j] > change[j+1]) {
					double temp = change[j+1];
					change[j+1] = change[j];
					change[j] = temp;
				}
			}
		}
	}
	
	public static double[] mergeSort(double[] arr, int start, int end) {
		//Base Case: if size is 1 then return arr of singular int
		if(start == end) return arr;
		
		//Make an array for the left and right sides of arr
		int mid = start + ((end - start) / 2);
		double[] left = new double[mid - start + 1];
		left = mergeR(arr, left, start, mid, 0);
		double[] right = new double[end - mid];
		right = mergeR(arr, right, mid + 1, end, 0);
		
		//Recursively go through and make arrays for the left and right
		mergeSort(left, 0, left.length - 1);
		mergeSort(right, 0, right.length - 1);
		
		//Compare the heads of the left and right arrays and add the smaller of the two to the array
		int i = 0;
		int iL = 0;
		int iR = 0;
		while(i != arr.length) {
			if(iL < left.length && (iR == right.length || left[iL] < right[iR])) {
				arr[i] = left[iL];
				i++;
				iL++;
			}
			else{
				arr[i] = right[iR];
				i++;
				iR++;
			}
		}
		return arr;
	}
	
	//MergeSort helper that copies array to a new one
	public static double[] mergeR(double[] arr, double[] newArr, int start, int end, int newStart) {
		//Base Case: if we have copied all we needed then return the new array	
		if(start > end) return newArr;
		//Copy current index of old array to new array
		newArr[newStart] = arr[start];
		//Recursively do this until we have copied all necessary index's
		mergeR(arr, newArr, start + 1, end, newStart + 1);
		return newArr;
	}
	
	//Plain QuickSort with pivot in middle
	void pQuickSort(int left, int right) {
		int iL = left;
		int iR = right;
		//Make pivot the middle
		double pivot = change[left+(right-left)/2];
		// Divide into two arrays
		while (iL <= iR) {
			//Move both sides until they need to swap or are at the pivot
			while (change[iL] < pivot) iL++;
			while (change[iR] > pivot) iR--;
			//Swap if not at pivot
			if (iL <= iR) {
				double temp = change[iL];
				change[iL] = change[iR];
				change[iR] = temp;
				//move both sides in
				iL++;
				iR--;
			}
		}
		//call pQuickSort() method recursively
		if (left < iR) pQuickSort(left, iR);
		if (iL < right) pQuickSort(iL, right);
	}
    	
	
	//
	void eQuickSort(int left, int right) {
		//When selection is smaller than 10 doubles then insertion sort is used instead
		//Since it behaves closer to O(log(n)) with smaller arrays
		if(right - left <= 10) {
			insertionNR(left, right);
			return;
	    }
		int iL = left;
	    int iR = right;
	    double pivot = change[left+(right-left)/2];
	    while (iL <= iR) {
	    	while (change[iL] < pivot) iL++;
	    	while (change[iR] > pivot) iR--;
	    	if (iL <= iR) {
	    		double temp = change[iL];
	    		change[iL] = change[iR];
	    		change[iR] = temp;
	    		iL++;
	    		iR--;
	        }
	    }
	    //call pQuickSort() method recursively
	    if (left < iR) eQuickSort(left, iR);
	    if (iL < right) eQuickSort(iL, right);
	}
	
	
	//Enhanced QuickSort Helper
	void insertionNR(int left, int right) {
		int size = right - left;
		for(int i = 0; i < size; i++) {
			//Go through the doubles to the left of the "wall"
			for(int j = i - 1; j >= 0; j--) {
				//Keep swapping "wall" double until it larger than the int to the left
				if(change[j+1] < change[j]) {
					double temp = change[j];
					change[j] = change[j+1]; 
					change[j+1] = temp;
				}
			}
		}
	}
	
	//Method that finds the closest change to the chosen state/region
	void closest(String pick, GridBagConstraints c) {
		int index = 0;
		//Find the chosen state/region
		for(int i = 0; i < Reader.place.size(); i++) {
			if(Reader.place.get(i).equals(pick)) index = i;
			else if(Reader.place.get(i).equals("." + pick)) index = i;
		}
		//Compare the chosen state/region to its sorted neighbors
		double distance1 = Math.abs(change[index]-change[index+1]);
		double distance2 = Math.abs(change[index]-change[index-1]);
		//Get the closer of the two
		if(distance1 < distance2) {
			closeTable(index, index+1, c);
		}
		else {
			closeTable(index, index-1, c);
		}
	}
	
	//Make a table from just the two close states/regions
	void closeTable(int index, int index2, GridBagConstraints c) {
		//Make headers for the columns
		String[] columns = {"State/Region", yearX.getSelectedItem() + " To " + yearY.getSelectedItem()};
		//Make a 2D array of the states/regions
		Object[][] data2D = new Object[2][2];
		//Copy the name and percent change into the array
		String temp = Reader.place.get(index);
		if(temp.charAt(0) == '.') temp = temp.substring(1, temp.length());
		data2D[0][0] = temp;
		data2D[0][1] = change[index];
		//Repeat for second state/region
		temp = Reader.place.get(index2);
		if(temp.charAt(0) == '.') temp = temp.substring(1, temp.length());
		data2D[1][0] = temp;
		data2D[1][1] = change[index2];
		//Create the table and add a scroll pane to panel
		table = new JTable(data2D, columns);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.setFont(table.getFont().deriveFont(20.0f));
		table.setRowHeight(30);
		scrollPane  = new JScrollPane(table);
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 4;
		c.ipady = 400;
		c.ipadx = 100;
		c.gridheight = 4;
		c.gridx = 6;
		c.gridy = 1;
		add(scrollPane, c);
		setVisible(true);
	}
	
	//Once the percent changes are sorted, match their index's up with the corresponding 
	//Index for the place ArrayList and data ArrayList in Reader
	void fixAll(double[] copy) {
		for(int x = 0; x < change.length; x++) {
		for(int i = 0; i < change.length; i++) {
			for(int j= 0; j < change.length; j++) {
				if(copy[i] == change[j] && i != j) {
					double tmp = copy[i];
					copy[i] = copy[j];
					copy[j] = tmp;
					String temp1 = Reader.place.get(j);
					Reader.place.set(j, Reader.place.get(i));
					Reader.place.set(i, temp1);
					ArrayList<String> temp2 = Reader.data.get(j);
					Reader.data.set(j, Reader.data.get(i));
					Reader.data.set(i, temp2);
				}
			}
		}}
	}
}
