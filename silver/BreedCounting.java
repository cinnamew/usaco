import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BreedCounting {
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("bcount.in")));
		String[] temp = br.readLine().split(" ");
		int numCows = Integer.parseInt(temp[0]);
		int numQueries = Integer.parseInt(temp[1]);
		int[] Hs = new int[numCows];
		int[] Gs = new int[numCows];
		int[] Js = new int[numCows];
		for(int i = 0; i < numCows; i++) {
			if(i != 0) {
				Hs[i] = Hs[i - 1];
				Gs[i] = Gs[i - 1];
				Js[i] = Js[i - 1];
			}
			
			int curr = Integer.parseInt(br.readLine());
			if(curr == 1) {
				Hs[i]++;
			}else if(curr == 2) {
				Gs[i]++;
			}else Js[i]++;
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
		pw.write("");
		pw.close();
		br.close();
	}

}
