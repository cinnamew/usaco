import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Circlecross {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("circlecross.in")));
		
		int[] enterPoints = new int[26];
		int[] exitPoints = new int[26];
		
		Arrays.fill(enterPoints, -1);
		
		String line = br.readLine();
		for(int i = 0; i < 52; i++) {
			char currPoint = line.charAt(i);
			int index = ((int)currPoint) - 65;
			if(enterPoints[index] == -1) {
				enterPoints[index] = i;
			}else {
				exitPoints[index] = i;
			}
		}
		
		int crosses = 0;
		
		for(int i = 0; i < 26; i++) {
			int enter = enterPoints[i];
			int exit = exitPoints[i];
			
			for(int j = enter + 1; j < exit; j++) {
				int currPoint = (int)line.charAt(j) - 65;
				if(enterPoints[currPoint] < enter || exitPoints[currPoint] > exit) {
					crosses++;
				}
			}
		}
		
		crosses /= 2;
		System.out.println(crosses);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
		pw.write("" + crosses);
		pw.close();
		
	}

}