import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Shell {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int numSwaps = Integer.parseInt(br.readLine());
		ShellSwap[] swaps = new ShellSwap[numSwaps];
		ArrayList<Integer> currShells = new ArrayList<Integer>(); 
		
		for(int i = 0; i < numSwaps; i++) {
			currShells.add(i + 1);
			String[] temp = br.readLine().split(" ");
			swaps[i] = new ShellSwap(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
		}
		
		int currentGuessStart = 1;
		int maxCorrectGuesses = 0;
		
		//currentGuessStart = where the pebble starts under
		//
		
		for(int i = 0; i < 3; i++) {
			currentGuessStart = i + 1;
			int currentGuess = currentGuessStart;
			//find where shell1 is
			//replace shell1 w/ shell2
			//repeat for shell2
			//
		}
	}

}

class ShellSwap {
	int shell1;
	int shell2;
	int guess;
	
	public ShellSwap(int shell1, int shell2, int guess) {
		this.shell1 = shell1;
		this.shell2 = shell2;
		this.guess = guess;
	}

	public int getShell1() {
		return shell1;
	}


	public int getShell2() {
		return shell2;
	}


	public int getGuess() {
		return guess;
	}
	
	
	
}
