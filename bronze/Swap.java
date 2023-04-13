import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Swap {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("swap.in"));
		String[] temp = br.readLine().split(" ");
		int numCows = Integer.parseInt(temp[0]);
		int totalSwaps = Integer.parseInt(temp[1]);
		//int[] cowLineup = new int[numCows];
		String[] stringFirstSwap = br.readLine().split(" ");
		String[] stringSecondSwap = br.readLine().split(" ");
		int[] firstSwap = {Integer.parseInt(stringFirstSwap[0]), Integer.parseInt(stringFirstSwap[1])};
		int[] secondSwap = {Integer.parseInt(stringSecondSwap[0]), Integer.parseInt(stringSecondSwap[1])};
		
		/*for(int i = 0; i < numCows; i++) {
			cowLineup[i] = i + 1;
		}*/
		
		int[] finalLine = swapCows(firstSwap[0], firstSwap[1], secondSwap[0], secondSwap[1], numCows, totalSwaps);
		
		/*for(int i = 0; i < totalSwaps; i++) {
			swapCows(firstSwap, cowLineup);
			swapCows(secondSwap, cowLineup);
		}*/
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
		for(int i = 0; i < numCows; i++) {
			System.out.println(finalLine[i]);
			pw.write("" + finalLine[i] + "\n");
		}
		br.close();
		pw.close();
	}
	
	public static int[] swapCows(int startA, int endA, int startB, int endB, int numCows, int swaps) {
		int[] newLine = new int[numCows];
		
		for(int i = 1; i <= numCows; i++) {
			int cycleLength = 1;
			int currentIndex = newSpot(startA, endA, startB, endB, i);
			//figure out how many swaps it takes for cow @ i to return to index i
			while(currentIndex != i) {
				cycleLength++;
				currentIndex = newSpot(startA, endA, startB, endB, currentIndex);
			}
			
			int remainder = swaps%cycleLength;
			for(int j = 0; j < remainder; j++) {
				currentIndex = newSpot(startA, endA, startB, endB, currentIndex);
			}
			newLine[currentIndex - 1] = i;
		}
		
		return newLine;
	}
	
	public static int newSpot(int startA, int endA, int startB, int endB, int cowIndex) {
		int endPos = cowIndex;
		if(startA <= cowIndex && cowIndex <= endA) endPos = startA + endA - cowIndex;
		if(startB <= endPos && endPos <= endB) endPos = startB + endB - endPos;
		
		return endPos;
	}
	
	/*public static void swapCows(int[] swap, int[] cowLineup) {
		int j = swap[1];
		
		for(int i = swap[0]; i < j; i++) {
			int tempJ = cowLineup[j - 1];
			cowLineup[j - 1] = cowLineup[i - 1];
			cowLineup[i - 1] = tempJ;
			j--;
		}
	}*/
	
	/*public static boolean checkCows(int[] cowLineup) {
		for(int i = 0; i < cowLineup.length; i++) {
			if(cowLineup[i] != i-1) {
				return false;
			}
		}
		return true;
	}*/

}
