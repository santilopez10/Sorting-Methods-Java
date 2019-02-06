import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
	
	static ArrayList<String> place = new ArrayList<>();
	static ArrayList<ArrayList<String>> data = new ArrayList<>();
	
	//Read each line of the CSV file
	public Reader() {
		String fileName = "C:src\\ds.csv";
		String temp = null;
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			bufferedReader.readLine(); //Gets rid of year line
			int count = 0;
			while((temp = bufferedReader.readLine()) != null) {
				decode(temp, count);
				count++;
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex){
			System.out.println( "Unable to open file '" + fileName + "'");
			ex.printStackTrace();
		}
		catch (IOException ex) {
			System.out.println( "Error reading file '" + fileName + "'");
			ex.printStackTrace();
		}
	}
	
	//Copy info into the corresponding ArrayList
	public void decode(String x, int line) {
		String[] strArr = x.split(",");
		place.add(strArr[0]);
		data.add(new ArrayList<String>());
		for(int i = 1; i < strArr.length; i++) {
			data.get(line).add(strArr[i]);
		}
	}
	
}
