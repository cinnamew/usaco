import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Daisies {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("daisies.in"));
		int numFlowers = Integer.parseInt(br.readLine());
		String[] temp = br.readLine().split(" ");
		int[] flowers = new int[numFlowers];
		int sumOfAll = 0;
		for(int i = 0; i < numFlowers; i++) {
			flowers[i] = Integer.parseInt(temp[i]);
			//sumOfAll += flowers[i];
		}
		
		//float realAverage = (float)sumOfAll/numFlowers;
		//int average = Math.round(realAverage);
		
		ArrayList<Integer> picFlowers = new ArrayList<Integer>();
		
		int avFlowers = 0;
		/*for(int i = 0; i < numFlowers; i++) {
			for(int j = i; j < numFlowers; j++) {
				/*if(flowers[i] == average) {
					avFlowers++;
				}
				for(int k = i; k <= j; k++) {
					if(flowers[k] == average && k != i) {
						avFlowers++;
					}
				}*/
				/*sumOfAll += flowers[i];
				picFlowers.add(flowers[i]);
				if(i != j) {
					for(int k = i + 1; k <= j; k++) {
						sumOfAll += flowers[k];
						picFlowers.add(flowers[k]);
					}
				}
				
			}
		}*/
		int picNumFlowers = picFlowers.size();
		
		/*float realAverage = (float)sumOfAll/picNumFlowers;
		int average = Math.round(realAverage);
		
		for(int i = 0; i < picNumFlowers; i++) {
			if(picFlowers.get(i) == average) {
				avFlowers++;
			}
		}*/
		
		for(int i = 0; i < numFlowers; i++) {
			for(int j = i; j < numFlowers; j++) {
				int groupSum = 0;
				for(int k = i; k <= j; k++) {
					groupSum += flowers[k];
				}
				float groupAv = (float)groupSum/(j - i + 1);
				/*if(j - i == 0) {
					groupAv = groupSum;
				}else {
					groupAv = (float)groupSum/(j - i + 1);
				}*/
				for(int k = i; k <= j; k++) {
					if(flowers[k] == groupAv) {
						avFlowers++;
						//System.out.println("i: " + i + ", j: " + j);
						break;
					}
				}
				
			}
		
		}
		
		//System.out.println(avFlowers);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.write("" + avFlowers);
		pw.flush();
	}

}
