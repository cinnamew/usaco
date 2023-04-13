import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Backforth {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("backforth.in"));
		String[] temp1 = br.readLine().split(" ");
		String[] temp2 = br.readLine().split(" ");
		
		ArrayList<Integer> barnOne = new ArrayList<Integer>();
		ArrayList<Integer> barnTwo = new ArrayList<Integer>();
		
		for(int i = 0; i < 10; i++) {
			barnOne.add(Integer.parseInt(temp1[i]));
		}
		for(int i = 0; i < 10; i++) {
			barnTwo.add(Integer.parseInt(temp2[i]));
		}
		
		ArrayList<Integer> allReadings = new ArrayList<Integer>();
		
		for(int monday = 0; monday < 10; monday++) {
			int barnOneMonday = barnOne.get(monday);
			barnTwo.add(barnOneMonday);
			barnOne.remove(monday);
			for(int tue = 0; tue < 11; tue++) {
				int barnTwoTuesday = barnTwo.get(tue);
				barnOne.add(barnTwoTuesday);
				barnTwo.remove(tue);
				for(int wed = 0; wed < 10; wed++) {
					int barnOneWed = barnOne.get(wed);
					barnTwo.add(barnOneWed);
					barnOne.remove(wed);
					for(int thurs = 0; thurs < 11; thurs++) {
						int barnTwoThurs = barnTwo.get(thurs);
						barnOne.add(barnTwoThurs);
						barnTwo.remove(thurs);
						
						int reading = barnTwoTuesday + barnTwoThurs;
						reading -= (barnOneMonday + barnOneWed);
						
						if(!allReadings.contains(reading)) allReadings.add(reading);
						
						barnTwo.add(thurs, barnTwoThurs);
						barnOne.remove(barnOne.size() - 1);
					}
					barnOne.add(wed, barnOneWed);
					barnTwo.remove(barnTwo.size() - 1);
				}
				barnTwo.add(tue, barnTwoTuesday);
				barnOne.remove(barnOne.size() - 1);
			}
			barnOne.add(monday, barnOneMonday);
			barnTwo.remove(barnTwo.size() - 1);
		}
		
		System.out.println(allReadings.size());
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("backforth.out")));
		pw.write("" + allReadings.size());
		pw.flush();
		
	}

}
