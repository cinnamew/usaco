import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class YearOfTheCow {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("yearofthecow.in"));
		
		String[] zodiac = {"Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "Goat", "Monkey", "Rooster", "Dog", "Pig", "Rat"};
		
		int lines = Integer.parseInt(br.readLine());
		ZodiacCow[] cows = new ZodiacCow[lines + 1];
		cows[0] = new ZodiacCow("Bessie", 0, "Ox");
		
		for(int i = 0; i < lines; i++) {
			String[] temp = br.readLine().split(" ");
			String name = temp[0];
			boolean previous = false;
			if(temp[3].equals("previous")) {
				previous = true;
			}
			String yearOf = temp[4];
			String startFrom = temp[7];
			String zodiacStart = "";
			ZodiacCow compareTo = cows[0];
			for(int j = 0; j <= i; j++) {
				if(cows[j].getName().equals(startFrom)) {
					zodiacStart = cows[j].getYearOf();
					compareTo = cows[j];
				}
			}
			
			int counter = compareTo.getDiffFromBessie();
			int startIndex = 0;
			for(int j = 0; j < zodiac.length; j++) {
				if(zodiacStart.equals(zodiac[j])) {
					startIndex = j;
				}
			}
			for(int j = startIndex; j < zodiac.length;) {
				if(previous) j--;
				else j++;
				
				if(previous) counter--;
				else counter++;
				
				if(j >= zodiac.length) {
					j = 0;
				}else if(j < 0) {
					j = zodiac.length - 1;
				}
				
				if(yearOf.equals(zodiac[j])) {
					break;
				}
				
				
			}
			
			cows[i + 1] = new ZodiacCow(name, counter, yearOf);
			
			if(name.equals("Elsie")) {
				//System.out.println(Math.abs(counter));
				PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
				pw.write("" + Math.abs(counter));
				pw.close();
				break;
			}
			
		}
		
		
		
	}

}

class ZodiacCow {
	String name = "";
	int diffFromBessie = 0;
	String yearOf = "Ox";
	
	public ZodiacCow(String n, int bessieDiff, String yearOf) {
		name = n;
		diffFromBessie = bessieDiff;
		this.yearOf = yearOf;
	}
	
	public String getName() {
		return name;
	}
	public int getDiffFromBessie() {
		return diffFromBessie;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDiffFromBessie(int diffFromBessie) {
		this.diffFromBessie = diffFromBessie;
	}

	public String getYearOf() {
		return yearOf;
	}

	public void setYearOf(String yearOf) {
		this.yearOf = yearOf;
	}
	
}