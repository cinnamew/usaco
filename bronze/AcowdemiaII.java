import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AcowdemiaII {

	public static void main(String[] args)throws IOException {
		//reading in info
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int numPapers = Integer.parseInt(temp[0]);
		int numCows = Integer.parseInt(temp[1]);
		String[] members = br.readLine().split(" ");
		int[][] yay = new int[numCows][numCows];
		
		for(int i = 0; i < numPapers; i++) {
			String[] names = br.readLine().split(" ");
			for(int cow = 0; cow < numCows; cow++) {
				String name = names[cow];
				
			}
		}
		
		
	}
	
}
