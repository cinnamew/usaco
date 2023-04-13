import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Sleepy {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sleepy.in"));
		int numCows = Integer.parseInt(br.readLine());
		
		int[] cows = new int[numCows];
		String[] tempCows = br.readLine().split(" ");
		for(int i = 0; i < numCows; i++) {
			cows[i] = Integer.parseInt(tempCows[i]);
		}
		
		int previousCow = cows[numCows - 1];
		int lastSortedCowIndex = numCows - 1;
		
		for(int i = numCows - 2; i >= 0; i--) {
			if(cows[i] < previousCow) {
				lastSortedCowIndex = i;
			}else {
				break;
			}
			previousCow = cows[i];
		}
		
		int answer = lastSortedCowIndex;
		//System.out.println(answer);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
		pw.write("" + answer);
		pw.flush();
	}

}
