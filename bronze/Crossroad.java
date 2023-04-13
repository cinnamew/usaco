import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Crossroad {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("crossroad.in")));
		int sightings = Integer.parseInt(br.readLine());
		int[] positions = new int[11];
		Arrays.fill(positions, -1);
		int crosses = 0;
		for(int i = 0; i < sightings; i++) {
			String[] temp = br.readLine().split(" ");
			int cow = Integer.parseInt(temp[0]);
			int side = Integer.parseInt(temp[1]);
			
			if(positions[cow] == -1) {
				positions[cow] = side;
			}else if(positions[cow] != side) {
				positions[cow] = side;
				crosses++;
			}
		}
		
		System.out.println(crosses);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("crossroad.out")));
		pw.write("" + crosses);
		pw.close();
	}

}
