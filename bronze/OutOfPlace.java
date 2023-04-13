import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class OutOfPlace {

	public static void main(String[] args)throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new  FileReader (new  File("outofplace.in")));
		int numCows = Integer.parseInt(br.readLine());
		int[] cowHeights = new int[numCows];
		int before = 0;
		int bessie = 0;
		int bessieLocation = 0;
		for(int i = 0; i < numCows; i++) {
			cowHeights[i] = Integer.parseInt(br.readLine());
		}
		
		
		//find bessie
		boolean goForward = true;
		for(int i = 0; i < numCows; i++) {
			if(cowHeights[i] < before) {
				if(i != numCows - 1 && cowHeights[i - 1] > cowHeights[i + 1]) {
					bessieLocation = i - 1;
					bessie = cowHeights[i - 1];
				}else {
					goForward = false;
					bessieLocation = i;
					bessie = cowHeights[i];
				}
			}
			before = cowHeights[i];
		}
		
		System.out.println(bessie);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("outofplace.out")));
		
		
		//find answer
		int ans = 0;
		if(goForward) {
			for(int i = bessieLocation + 1; i <= numCows - 1; i++) {
				if(cowHeights[i] >= bessie) {
					pw.write("" + ans);
					pw.close();
					System.out.println(ans);
					return;
				}
				if(i == numCows - 1 || cowHeights[i] != cowHeights[i + 1]) ans++;
				
			}
		}else {
			for(int i = bessieLocation - 1; i >= 0; i--) {
				if(cowHeights[i] <= bessie) {
					pw.write("" + ans);
					pw.close();
					System.out.println(ans);
					return;
				}
				if(i != 0 && cowHeights[i] != cowHeights[i - 1]) ans++;
				else if(i == 0) ans++;
			}
		}
		System.out.println(ans);
		pw.write("" + ans);
		pw.close();
	}

}
