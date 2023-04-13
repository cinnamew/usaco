import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileReader;

public class MoreOddPhotos {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new  FileReader (new  File("moreoddphotos.in")));
		
		int numCows = Integer.parseInt(br.readLine());
		String[] temp = br.readLine().split(" ");
		int odds = 0;
		int evens = 0;
		
		for(int i = 0; i < temp.length; i++) {
			if(Integer.parseInt(temp[i])%2 == 0) {
				evens++;
			}else {
				odds++;
			}
		}
		
		int min = Integer.min(evens, odds);
		int groups = 2*min;
		evens -= min;
		odds -= min;
		
		//ended w/ odd
		//makes it end w/ even
		//no more odds
		
		if(evens > 0) {
			evens--;
			groups++;
		}else if(odds >= 2) {
			odds -= 2;
			groups++;
		}else if(odds == 1) {
			groups --;
		}
		
		
		
		if(odds > 0) {
			if(odds%3 == 0) {
				groups += odds/3 * 2;
			}
			else if(odds%3 == 2) {
				//groups += Math.floor(odds/2);
				groups += odds/3 * 2;
			}
			else if(odds%3 == 1 && odds != 1) {
				groups += (Math.floor(odds/3)*2 + 1);
			}
		}
		
		System.out.println(groups);
		
	}

}
