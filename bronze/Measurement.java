import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class Measurement {
	
	public static void main(String[] args)throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new  FileReader (new  File("measurement.in")));
		int numMeasurements = Integer.parseInt(br.readLine());
		Entry[] entries = new Entry[numMeasurements];
		for(int i = 0; i < numMeasurements; i++) {
			String[] temp = br.readLine().split(" ");
			int cow = 0;
			if(temp[1].equals("Elsie")) cow = 1;
			else if(temp[1].equals("Mildred")) cow = 2;
			entries[i] = new Entry(Integer.parseInt(temp[0]), cow, Integer.parseInt(temp[2]));
		}
		Arrays.sort(entries);
		int[] currMilks = {7, 7, 7};	//bessie, elsie, mildred
		String currTop = "ALL";
		int numChanges = 0;
		for(int i = 0; i < numMeasurements; i++) {
			Entry currEntry = entries[i];
			currMilks[currEntry.getCow()] += currEntry.getIncrement();
			String newTop = "A";
			if(currMilks[0] == currMilks[1] && currMilks[0] == currMilks[2]) {
				newTop = "ALL";
			}else if(currMilks[0] > currMilks[1] && currMilks[2] >= currMilks[1]) {
				if(currMilks[0] == currMilks[2]) {
					newTop = "AC";
				}else if(currMilks[2] > currMilks[0]) newTop = "C";
			}else if(currMilks[1] > currMilks[2]) {
				if(currMilks[0] == currMilks[1]) {
					newTop = "AB";
				}else if(currMilks[1] > currMilks[0]){
					newTop = "B";
				}
			}else if(currMilks[2] > currMilks[0]) {
				if(currMilks[1] == currMilks[2]) {
					newTop = "BC";
				}else if(currMilks[2] > currMilks[1]){
					newTop = "C";
				}
			}
			if(!newTop.equals(currTop)) {
				currTop = newTop;
				numChanges++;
			}
		}
		
		System.out.println(numChanges);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
		pw.write("" + numChanges);
		pw.close();
	}

}

class Entry implements Comparable<Entry> {
	int time;
	int cow;
	int increment;
	
	public int getTime() {
		return time;
	}

	public int getCow() {
		return cow;
	}

	public int getIncrement() {
		return increment;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setCow(int cow) {
		this.cow = cow;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public Entry(int time, int cow, int increment) {
		this.time = time;
		this.cow = cow;
		this.increment = increment;
	}

	@Override
	public int compareTo(Entry o) {
		if(time > o.getTime()) return 1;
		else if(time < o.getTime()) return -1;
		return 0;
	}
	
	
}