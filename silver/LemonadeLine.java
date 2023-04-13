import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class LemonadeLine {

	public static void main(String[] args)throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new  FileReader (new  File("lemonade.in")));
		int numCows = Integer.parseInt(br.readLine());
		String[] temp = br.readLine().split(" ");
		int[] cows = new int[numCows];
		
		for(int i = 0; i < numCows; i++) {
			cows[i] = Integer.parseInt(temp[i]);
		}
		Arrays.sort(cows);
		int ans = 0;
		
		for(int i = numCows - 1; i >= 0; i--) {
			if(cows[i] >= ans) ans++;
			else break;
		}
		//System.out.println(ans);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
		pw.write("" + ans);
		pw.close();
	}

}
