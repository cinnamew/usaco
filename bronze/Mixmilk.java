import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Mixmilk {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mixmilk.in"));
		String[] temp1 = br.readLine().split(" ");
		int bucketOneCap = Integer.parseInt(temp1[0]);
		int bucketOne = Integer.parseInt(temp1[1]);
		
		String[] temp2 = br.readLine().split(" ");
		int bucketTwoCap = Integer.parseInt(temp2[0]);
		int bucketTwo = Integer.parseInt(temp2[1]);
		
		String[] temp3 = br.readLine().split(" ");
		int bucketThreeCap = Integer.parseInt(temp3[0]);
		int bucketThree = Integer.parseInt(temp3[1]);
		
		int currentBucket = 1;
		
		for(int i = 0; i < 100; i++) {
			if(currentBucket == 1) {
				if(bucketTwoCap - bucketTwo >= bucketOne) {
					bucketTwo += bucketOne;
					bucketOne = 0;
				}else {
					int diff = bucketOne - (bucketTwoCap - bucketTwo);
					bucketTwo = bucketTwoCap;
					bucketOne = diff;
				}
				currentBucket = 2;
			}else if(currentBucket == 2) {
				if(bucketThreeCap - bucketThree >= bucketTwo) {
					bucketThree += bucketTwo;
					bucketTwo = 0;
				}else {
					int diff = bucketTwo - (bucketThreeCap - bucketThree);
					bucketThree = bucketThreeCap;
					bucketTwo = diff;
				}
				currentBucket = 3;
			}else {
				if(bucketOneCap - bucketOne >= bucketThree) {
					bucketOne += bucketThree;
					bucketThree = 0;
				}else {
					int diff = bucketThree - (bucketOneCap - bucketOne);
					bucketOne = bucketOneCap;
					bucketThree = diff;
				}
				currentBucket = 1;
			}
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));
		pw.write(bucketOne + "\n" + bucketTwo + "\n" + bucketThree);
		pw.flush();
	}

}
