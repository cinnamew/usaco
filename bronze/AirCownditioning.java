import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AirCownditioning {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numStalls = Integer.parseInt(br.readLine());
		String[] target = br.readLine().split(" ");
		String[] currTemps = br.readLine().split(" ");
		int[] diffs = new int[numStalls + 2];
		for(int i = 0; i < numStalls; i++) {
			diffs[i + 1] = Integer.parseInt(target[i]) - Integer.parseInt(currTemps[i]);
		}
		
		int inc = 0;
		int dec = 0;
		
		for(int i = 1; i < numStalls + 2; i++) {
			int diff = diffs[i] - diffs[i - 1];
			if(diff > 0) inc += diff;
			else dec -= diff;
		}
		
		System.out.println(Math.max(inc, dec));
		
		br.close();
	}

}
