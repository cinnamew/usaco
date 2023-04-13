import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class Cownomics {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("cownomics.in")));
		String[] temp = br.readLine().split(" ");
		int cowTypeNum = Integer.parseInt(temp[0]);
		int nucleotidesPerCow = Integer.parseInt(temp[1]);
		
		char[][] spottedCows = new char[cowTypeNum][nucleotidesPerCow];
		char[][] plainCows = new char[cowTypeNum][nucleotidesPerCow];
		
		for(int i = 0; i < cowTypeNum; i++) {
			String[] temp2 = br.readLine().split("");
			for(int j = 0; j < nucleotidesPerCow; j++) {
				spottedCows[i][j] = temp2[j].charAt(0);
				//System.out.println(spottedCows[i][j]);
			}
		}
		
		for(int i = 0; i < cowTypeNum; i++) {
			String[] temp2 = br.readLine().split("");
			for(int j = 0; j < nucleotidesPerCow; j++) {
				plainCows[i][j] = temp2[j].charAt(0);
			}
		}
		
		//ArrayList<Integer> positions = new ArrayList<Integer>();
		int positions = 0;
		
		for(int i = 0; i < nucleotidesPerCow; i++) {
			HashSet<String> spotNucleotides = new HashSet<String>();
			for(int j = 0; j < cowTypeNum; j++) {
				spotNucleotides.add(spottedCows[j][i] + "");
			}
			boolean allUnique = true;
			for(int j = 0; j < cowTypeNum; j++) {
				if(spotNucleotides.contains(plainCows[j][i] + "")) {
					//spotNucleotides.remove(plainCows[j][i] + "");
					allUnique = false;
					break;
				}
			}
			if(!allUnique) {
				continue;
			}
			
			if(spotNucleotides.size() > 0) {
				positions++;
				//System.out.println(spotNucleotides);
			}
		}
		
		System.out.println(positions);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		pw.write("" + positions);
		pw.close();
	}

}
