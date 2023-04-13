import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;


public class CardGame {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("cardgame.in")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numCards = Integer.parseInt(br.readLine());
		int[] elsie = new int[numCards];
		TreeSet<Integer> bessieTop = new TreeSet<Integer>();
		for(int i = 1; i <= numCards*2; i++) {
			bessieTop.add(i);
		}
		for(int i = 0; i < numCards; i++) {
			elsie[i] = Integer.parseInt(br.readLine());
			bessieTop.remove(elsie[i]);
		}
		TreeSet<Integer> bessieBottom = new TreeSet<Integer>();
		for(int i = 0; i < numCards/2; i++) {
			bessieBottom.add(bessieTop.pollFirst());
		}
		
		//System.out.println(bessieTop);
		//System.out.println(bessieBottom);
		
		int ans = 0;
		for(int i = 0; i < numCards/2; i++) {
			
			if(bessieTop.ceiling(elsie[i] + 1) == null) bessieTop.pollFirst();
			else {
				ans++;
				bessieTop.remove(bessieTop.ceiling(elsie[i] + 1));
			}
		}
		
		System.out.println(ans);
		
		for(int i = 0; i < numCards/2; i++) {
			
			if(bessieBottom.floor(elsie[i + numCards/2] - 1) == null) bessieBottom.pollLast();
			else {
				ans++;
				bessieBottom.remove(bessieBottom.floor(elsie[i + numCards/2] - 1));
			}
		}
		//System.out.println(ans);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
		pw.write("" + ans);
		pw.close();
	}

}
