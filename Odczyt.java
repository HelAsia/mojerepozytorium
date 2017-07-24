package cwiczenia;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Odczyt {
	
	public void odczytWyswietl (BufferedReader file) throws IOException{
	// loading and showing dates.
		try{
			file = new BufferedReader(new FileReader("E:\\PROJEKTY\\Konwersja\\PLIK_DEGIRO.csv"));
			 String textLine = file.readLine();
			 
			  do {
			    
			    Pattern pattern = Pattern.compile("([0-9]{2})-([0-9]{2})-([0-9]{4}),([0-9]{2}:[0-9]{2})");
				Matcher matcher = pattern.matcher(textLine);
				matcher.find();
				
				Pattern pattern2 = Pattern.compile(":[0-9]{2},([^,]*)");
				Matcher matcher2 = pattern2.matcher(textLine);
				matcher2.find();
				
				Pattern pattern3 = Pattern.compile("([-]*[0-9]+),PLN");
				Matcher matcher3 = pattern3.matcher(textLine);
				matcher3.find();
				
				Pattern pattern4 = Pattern.compile("\",PLN,\"(.*)\"");
				Matcher matcher4 = pattern4.matcher(textLine);
				matcher4.find();
				
				String year = matcher.group(3);
				String month =matcher.group(2);
				String day = matcher.group(1);
				String time = matcher.group(4);
				String name = matcher2.group(1);
				String buySale = matcher3.group(1);
				String dash = "-";
				String howMuch = null;
				String price = matcher4.group(1);
				
				if (buySale.substring(0,1).equals(dash)){
					howMuch = buySale.substring(1);
					buySale = "SPRZEDA¯";
				}
				else{
					howMuch = buySale;
					buySale = "KUPNO";
				}
				
				String text = String.format("%s-%s-%s,%s:00;%s;%s;%s;%s;00.00 \n",year,month,day,time,name,buySale,howMuch,price);
				
				FileWriter file2 = new FileWriter("myfund.csv", true);
				file2.write(text);
				file2.close();

				System.out.printf("%s-%s-%s,%s:00;%s;%s;%s;%s;00.00 \n",year,month,day,time,name,buySale,howMuch,price);
				
				textLine = file.readLine();
				
				
			  } while(textLine != null);
		}
		catch (FileNotFoundException e){
			System.out.println("Nie znaleziono pliku. Wczytaj ponownie.");
		}
	}
}