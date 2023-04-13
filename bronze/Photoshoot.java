import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Photoshoot {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("photo.in"));
		int cowNum = Integer.parseInt(br.readLine());
		
		int[] cowSums = new int[cowNum - 1];
		String[] cowSumsStr = br.readLine().split(" ");
		boolean[] usedCows = new boolean[cowNum + 1];
		
		for(int i = 0; i < cowNum - 1; i++) {
			cowSums[i] = Integer.parseInt(cowSumsStr[i]);
		}
		
		int[] answer = new int[cowNum];
		for(int i = 1; i <= cowNum; i++) {
			Arrays.fill(usedCows, false);
			usedCows[i] = true;
			answer[0] = i;
			for(int j = 1; j < cowNum; j++) {
				int previousCow = answer[j-1];
				answer[j] = cowSums[j-1] - previousCow;
				if(cowSums[j-1] - previousCow <= 0 || cowSums[j-1] - previousCow > cowNum || usedCows[cowSums[j-1] - previousCow] == true) {
					break;
				}
				usedCows[answer[j]] = true;
			}
			if(answer[cowNum - 1] != 0) {
				break;
			}
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("photo.out")));
		String finalLine = "";
		for(int i = 0; i < cowNum; i++) {
			if(i == cowNum - 1) {
				finalLine += (answer[i]);
			}else {
				finalLine += (answer[i] + " ");
			}
		}
		pw.write(finalLine);
		pw.flush();
	}
}
