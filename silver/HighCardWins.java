import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;

public class HighCardWins {
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("highcard.in")));
		int numCards = Integer.parseInt(br.readLine());
		TreeSet<Integer> bessie = new TreeSet<Integer>();
		for(int i = 1; i <= numCards*2; i++) {
			bessie.add(i);
		}
		int[] elsie = new int[numCards];
		for(int i = 0; i < numCards; i++) {
			elsie[i] = Integer.parseInt(br.readLine());
			bessie.remove(elsie[i]);
		}
		
		int ans = 0;
		for(int i = 0; i < numCards; i++) {
			int e = elsie[i];
			if(bessie.ceiling(elsie[i] + 1) == null) bessie.pollFirst();
			else {
				ans++;
				bessie.remove(bessie.ceiling(elsie[i] + 1));
			}
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
		pw.write("" + ans);
		pw.close();
	}
	
}
