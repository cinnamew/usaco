import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MilkOrder {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("milkorder.in")));
		String[] temp = br.readLine().split(" ");
		int numCows = Integer.parseInt(temp[0]);
		int numHierarchyCows = Integer.parseInt(temp[1]);
		int numPickyCows = Integer.parseInt(temp[2]);
		
		int[] hierarchyCows = new int[numHierarchyCows];
		String[] temp2 = br.readLine().split(" ");
		for(int i = 0 ; i < numHierarchyCows; i++) {
			hierarchyCows[i] = Integer.parseInt(temp2[i]);
		}
		
		
		int[] cowLine = new int[numCows + 1];
		int[] cowPositions = new int[numCows + 1];
		
		int[] pickyCows = new int[numPickyCows];
		int[] pickyCowSpots = new int[numPickyCows];
		boolean cowOnePicky = false;
		for(int i = 0; i < numPickyCows; i++) {
			String[] temp3 = br.readLine().split(" ");
			pickyCows[i] = Integer.parseInt(temp3[0]);
			pickyCowSpots[i] = Integer.parseInt(temp3[1]);
			cowLine[pickyCowSpots[i]] = pickyCows[i];
			cowPositions[Integer.parseInt(temp3[0])] = Integer.parseInt(temp3[1]);
			if(pickyCows[i] == 1) {
				cowOnePicky = true;
				System.out.println(pickyCowSpots[i]);
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
				pw.write("" + pickyCowSpots[i]);
				pw.close();
				return;
			}
		}
		
		int cowOnePlace = 0;
		int[] prevCowLine = copyArray(cowLine);
		int[] prevCowPositions = copyArray(cowPositions);
		
		
		for(int i = 1; i <= numCows; i++) {
			if(cowLine[i] != 0) continue;
			cowLine[i] = 1;
			cowPositions[1] = i;
			int index = 1;
			boolean isValid = true;
			for(int j = 0; j < numHierarchyCows; j++) {
				if(cowPositions[hierarchyCows[j]] != 0) {
					if(index > cowPositions[hierarchyCows[j]]) {
						isValid = false;
						break;
					}else {
						index = cowPositions[hierarchyCows[j]];
					}
					
				}else {
					while(index < cowLine.length && cowLine[index] != 0) {
						index++;
					}
					if(index < cowLine.length) {
						cowLine[index] = hierarchyCows[j];
						cowPositions[hierarchyCows[j]] = index;
						
					}else {
						isValid = false;
					}
					
				}
			}
			/*for(int w = 0; w < cowLine.length; w++) {
				System.out.println(cowLine[w]);
			}*/
			if(isValid) {
				cowOnePlace = i;
				break;
			}
			cowLine = copyArray(prevCowLine);
			cowPositions = copyArray(prevCowPositions);
			
		}
		
		/*
		for(int i = 1; i < numCows + 1; i++) {
			if(cowLine[i] == 0) {
				cowOnePlace = i;
				break;
			}
		}*/
		
		
		System.out.println(cowOnePlace);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
		pw.write("" + cowOnePlace);
		pw.close();
		
		
	}
	
	static int[] copyArray(int[] arr) {
		int[] newArr = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			newArr[i] = arr[i];
		}
		return newArr;
	}

}
