import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CowSignal {

	public static void main(String[] args)throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new  FileReader (new  File("cowsignal.in")));
		String[] temp = br.readLine().split(" ");
		int height = Integer.parseInt(temp[0]);
		int length = Integer.parseInt(temp[1]);
		int scaleBy = Integer.parseInt(temp[2]);
		char[][] orig = new char[height][length];
		char[][] newArr = new char[height*scaleBy][length*scaleBy];
		
		for(int i = 0; i < height; i++) {
			String temp2 = br.readLine();
			for(int j = 0; j < length; j++) {
				orig[i][j] = temp2.charAt(j);
			}
		}
		
		//go through each line
		for(int i = 0; i < height; i++) {
			//expand right
			for(int j = 0; j < length; j++) {
				char curr = orig[i][j];
				for(int k = 0; k < scaleBy; k++) {
					newArr[i*scaleBy][k + j*scaleBy] = curr;
				}
			}
			//expand down
			for(int j = 0; j < scaleBy; j++) {
				newArr[i*scaleBy + j] = newArr[i*scaleBy];
			}
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowsignal.out")));
		
		for(int i = 0; i < height*scaleBy; i++) {
			for(int j = 0; j < length*scaleBy; j++) {
				//System.out.print(newArr[i][j]);
				pw.write("" + newArr[i][j]);
			}
			//System.out.println();
			pw.println();
		}
		
		pw.close();
		br.close();
	}
	
}
