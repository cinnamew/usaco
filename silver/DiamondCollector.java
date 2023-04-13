import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class DiamondCollector {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("diamond.in")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int numDias = Integer.parseInt(temp[0]);
		int maxSizeDiff = Integer.parseInt(temp[1]);
		int[] dias = new int[numDias];
		for(int i = 0; i < numDias; i++) {
			dias[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(dias);
		
		int pointer = 0;
		int[] right = new int[numDias];
		int[] maxRight = new int[numDias];
		for(int i = 0; i < numDias; i++) {
			while(pointer < numDias && dias[pointer] - dias[i] <= maxSizeDiff) {
				pointer++;
			}
			right[i] = pointer - i;
			
		}
		
		for(int i = numDias - 1; i >= 0; i--) {
			if(i == numDias - 1) {
				maxRight[i] = right[i];
			}else maxRight[i] = Math.max(maxRight[i + 1], right[i]);
		}
		
		int[] left = new int[numDias];
		pointer = numDias - 1;
		for(int i = numDias - 1; i >= 0; i--) {
			while(pointer >= 0 && dias[i] - dias[pointer] <= maxSizeDiff) {
				pointer--;
			}
			left[i] = i - pointer;
		}
		
		int[] maxLeft = new int[numDias];
		for(int i = 0; i < numDias; i++) {
			if(i == 0) {
				maxLeft[i] = left[i];
			}else maxLeft[i] = Math.max(maxLeft[i - 1], left[i]);
		}
		int ans = 0;
		for(int i = 0; i < numDias - 1; i++) {
			ans = Math.max(ans, maxLeft[i] + maxRight[i + 1]);
		}
		
		//System.out.println(ans);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
		pw.write("" + ans);
		pw.close();
	}
}
