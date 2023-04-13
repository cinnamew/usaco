import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Breedflip {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("breedflip.in"));
		int numCows = Integer.parseInt(br.readLine());
		String startBreeds = br.readLine();
		String endBreeds = br.readLine();
		int numFlips = 0;
		
		for(int i = 0; i < numCows; i++) {
			boolean inSegment = false;
			for(int j = i; j < numCows; j++) {
				if(startBreeds.charAt(j) == endBreeds.charAt(j)) {
					i = j;
					if(inSegment == true) {
						numFlips ++;
					}
					inSegment = false;
					break;
				}else {
					inSegment = true;
				}
			}
		}
		
		System.out.println(numFlips);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("breedflip.out")));
		pw.write("" + numFlips);
		pw.flush();
	}

}
