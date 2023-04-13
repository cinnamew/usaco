import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BerryPicking {

	public static void main(String[] args)throws IOException {
		//BufferedReader br = new BufferedReader(new  FileReader (new  File("berrypicking.in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int numTrees = Integer.parseInt(temp[0]);
		int numBaskets = Integer.parseInt(temp[1]);
		String[] temp2 = br.readLine().split(" ");
		int[] trees = new int[numTrees];
		for(int i = 0; i < numTrees; i++) {
			trees[i] = Integer.parseInt(temp2[i]);
		}
		
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("berrypicking.out")));
		pw.write("");
		pw.close();
	}

}
