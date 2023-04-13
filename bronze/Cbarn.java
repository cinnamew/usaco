import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Cbarn {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));
		
		int numBarns = Integer.parseInt(br.readLine());
		int[] barns = new int[numBarns];
		
		for(int i = 0; i < numBarns; i++) {
			barns[i] = Integer.parseInt(br.readLine());
		}
		
		int minSum = Integer.MAX_VALUE;
		for(int startBarn = 0; startBarn < numBarns; startBarn++) {
			int sum = 0;
			for(int currBarn = startBarn; currBarn < numBarns; currBarn++) {
				int cowsNeeded = barns[currBarn];
				int diff = currBarn - startBarn;
				sum += cowsNeeded*diff;
			}
			
			for(int currBarn = 0; currBarn < startBarn; currBarn++) {
				int cowsNeeded = barns[currBarn];
				int diff = numBarns - startBarn + currBarn;
				sum += cowsNeeded*diff;
			}
			
			if(sum < minSum) minSum = sum;
		}
		
		//System.out.println(minSum);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		pw.write("" + minSum);
		pw.close();
		
	}

}
