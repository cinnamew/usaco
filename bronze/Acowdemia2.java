
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Acowdemia2 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int publications = Integer.parseInt(temp[0]);
		int numProfs = Integer.parseInt(temp[1]);
		
		String[] profs = br.readLine().split(" ");
		int[][] listings = new int[numProfs][numProfs];
		
		for(int i = 0; i < publications; i++) {
			String[] order = br.readLine().split(" ");
			HashMap<String, Integer> levels = new HashMap<String, Integer>();
			int currTopLevel = 0;
			levels.put(order[0], currTopLevel);
			String prevProf = order[0];
			for(int j = 1; j < numProfs; j++) {
				String currProf = order[j];
				if(currProf.compareTo(prevProf) > 0) {
					levels.put(currProf, levels.get(prevProf));
				}else {
					levels.put(currProf, currTopLevel + 1);
					currTopLevel++;
				}
			}
			for(int j = 0; j < numProfs; j++) {
				for(int k = 0; k < numProfs; k++) {
					
				}
			}
		}
		
	}

}
