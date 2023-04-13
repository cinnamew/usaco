import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lonely {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long numCows = Integer.parseInt(br.readLine());
		String[] cows = br.readLine().split("");
		long ans = 0;
		//finding lone Gs
		long left = 0;
		long right = 0;
		for(int i = 0; i < numCows; i++) {
			//how many Hs are to the left
			if(cows[i].equals("H")) left++;
			else {
				//how many Hs are to the right
				for(int j = i + 1; j < numCows; j++) {
					if(cows[j].equals("G")) break;
					else right++;
				}
				ans += numLonelyPhotos(left, right);
				//System.out.printf("LONE G: At position %d, %d left Hs & %d right Hs\n", i, left, right);
				//for the rest u don't need to recalculate the # of Hs to the left
				left = right;
				i += right++;
				right = 0;
			}
		}
		
		left = 0;
		right = 0;
		//finding lone Hs
		for(int i = 0; i < numCows; i++) {
			//how many Gs are to the left
			if(cows[i].equals("G")) left++;
			else {
				//how many Gs are to the right
				for(int j = i + 1; j < numCows; j++) {
					if(cows[j].equals("H")) break;
					else right++;
				}
				ans += numLonelyPhotos(left, right);
				//System.out.printf("LONE H: At position %d, %d left Gs & %d right Gs\n", i, left, right);
				//for the rest u don't need to recalculate the # of Hs to the left
				left = right;
				i += right++;
				right = 0;
			}
		}
		
		System.out.println(ans);
		
		br.close();
	}
	
	public static long numLonelyPhotos(long l, long r) {
		if(l == 0) {
			if(r == 0) return 0;
			else return r - 1;
		}else if(r == 0) return l -1;
		return (2*l - 1) + (r - 1)*(l + 1);
	}
	
}
