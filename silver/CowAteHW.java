import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CowAteHW {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new  FileReader (new  File("homework.in")));
		int numTests = Integer.parseInt(br.readLine());
		ArrayList<Integer> a = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {	//can also do for loop till numTests
			a.add(Integer.parseInt(st.nextToken()));
		}
		
		
		double max = 0;
		int currMin = Integer.MAX_VALUE;
		//int prevMin;
		int currSum = 0;
		ArrayList<Integer> kangarookiwi = new ArrayList<>();	//ks
		//ArrayList<Integer> b = new ArrayList<>();
		for(int i = 0; i < numTests; i++) {
			if(i == 0 || i == 1) {
				//b.add(a.get(i));
				currSum += a.get(i);
				if(currMin > a.get(i)) currMin = a.get(i);
				continue;
			}
			int cute = a.get(a.size() - i);	//current num
			//prevMin = currMin;
			
			if(cute < currMin) currMin = cute;
			
			currSum += cute; //add to sum
			
			double tempScore = ((double)currSum - currMin)/i; //calculate score
			if(tempScore > max) tempScore = max;
			
			//add min back
			currSum += currMin;
			
		}
		
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		br.close();
		pw.close();
	}
	
}
