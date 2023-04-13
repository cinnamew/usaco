import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class AcowdemiaI {

	public static void main(String[] args)throws IOException {
		//reading in info
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int numPages = Integer.parseInt(temp[0]);
		int citationsAvailable = Integer.parseInt(temp[1]);
		int[] pages = new int[numPages];
		String[] temp2 = br.readLine().split(" ");
		for(int i = 0; i < numPages; i++) {
			pages[i] = Integer.parseInt(temp2[i]);
		}
		
		//sort
		Arrays.sort(pages);
		
		//finding h-index
		int hIndex = 0;
		HashMap<Integer, Integer> wee = new HashMap<>();
		for(int i = 0; i < numPages; i++) {
			int citations = pages[i];
			int n = numPages - i;
			if(citations > n) {
				if(n > hIndex) {
					hIndex = n;
				}
				wee.put(citations, n);
				continue;
			}
			if(!wee.containsKey(citations)) {
				wee.put(citations, n);
			}
			hIndex = Math.max(hIndex, citations);
		}
		
		//System.out.println(hIndex);
		
		//changing h-index
		if(wee.containsKey(hIndex)) {
			int alreadyHave = 0;
			//(ignore) && wee.containsKey(pages[numPages - wee.get(hIndex)])
			if(hIndex != numPages - 1 && wee.containsKey(hIndex + 1)) {
				alreadyHave = numPages - wee.get(hIndex + 1);
			}
			
			int needed = hIndex + 1 - alreadyHave;
			if(citationsAvailable >= needed) {
				System.out.println(hIndex + 1);
				return;
			}
		}
		System.out.println(hIndex);
		
	}
	
}
