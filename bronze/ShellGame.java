import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ShellGame {
	
	public static void main(String[] args)throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new  FileReader (new  File("shell.in")));
		int numSwaps = Integer.parseInt(br.readLine());
		String[] swaps = new String[numSwaps];
		for(int i = 0; i < numSwaps; i++) {
			swaps[i] = br.readLine();
		}
		
		int max = 0;
		int curr = 0;
		for(int correct = 0; correct < 3; correct++) {
			curr = 0;
			int correctPos = correct + 1;
			for(int i = 0; i < numSwaps; i++) {
				String[] temp = swaps[i].split(" ");
				int a = Integer.parseInt(temp[0]);
				int b = Integer.parseInt(temp[1]);
				int guess = Integer.parseInt(temp[2]);
				correctPos = swap(a, b, correctPos);
				if(guess == correctPos) {
					curr++;
				}
			}
			if(curr > max) max = curr;
		}
		
		//System.out.println(max);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shell.out")));
		pw.write("" + max);
		pw.close();
	}
	
	public static int swap(int a, int b, int currPosition) {
		if(a != currPosition && b != currPosition) return currPosition;
		else if(b == currPosition) return a;
		return b;
	}
	
}
