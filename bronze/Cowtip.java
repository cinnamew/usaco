import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Cowtip {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("cowtip.in")));
		int n = Integer.parseInt(br.readLine());
		int[][] cows = new int[n][n];
		for(int i = 0; i < n; i++) {
			String[] temp = br.readLine().split("");
			for(int j = 0; j < n; j++) {
				cows[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
		int flips = 0;

		for(int i = n - 1; i >= 0; i--) {
			for(int j = n - 1; j >= 0; j--) {
				if(cows[i][j] == 1) {
					flips++;
					for(int k = 0; k <= i; k++) {
						for(int l = 0; l <= j; l++) {
							if(cows[k][l] == 1) {
								cows[k][l] = 0;
							}else {
								cows[k][l] = 1;
							}
						}
					}
				}
			}
		}
		
		System.out.println(flips);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowtip.out")));
		pw.write("" + flips);
		pw.close();
		
	}

}
