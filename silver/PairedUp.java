import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PairedUp {

	public static void main(String[] args)throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new  FileReader (new  File("pairup.in")));
		int numLines = Integer.parseInt(br.readLine());
		PairedUpCow[] cows = new PairedUpCow[numLines];
		for(int i = 0; i < numLines; i++) {
			String[] temp = br.readLine().split(" ");
			int e = Integer.parseInt(temp[0]);
			int milkOutput = Integer.parseInt(temp[1]);
			cows[i] = new PairedUpCow(e, milkOutput);
		}
		
		Arrays.sort(cows);
		
		int max = 0;
		int leftPointer = 0;
		int rightPointer = cows.length - 1;
		while(leftPointer <= rightPointer) {
			PairedUpCow left = cows[leftPointer];
			PairedUpCow right = cows[rightPointer];
			
			int time = left.getTime() + right.getTime();
			max = Math.max(max, time);
			int temp = Math.min(left.getNumCows(), right.getNumCows());
			left.setNumCows(left.getNumCows() - temp);
			right.setNumCows(right.getNumCows() - temp);
			
			if(left.getNumCows() <= 0) leftPointer++;
			if(right.getNumCows() <= 0) rightPointer--;
		}
		
		//System.out.println(max);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
		pw.write("" + max);
		pw.close();
		br.close();
	}
}

class PairedUpCow implements Comparable<PairedUpCow> {
	int numCows;
	int time;
	public PairedUpCow(int n, int t) {
		numCows = n;
		time = t;
	}
	
	public int getNumCows() {
		return numCows;
	}
	public int getTime() {
		return time;
	}
	public void setNumCows(int numCows) {
		this.numCows = numCows;
	}
	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public int compareTo(PairedUpCow o) {
		if(time < o.getTime()) return -1;
		else if(time > o.getTime()) return 1;
		return 0;
	}
	
}
