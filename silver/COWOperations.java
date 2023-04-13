import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class COWOperations {

	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int length = s.length();
		char[] strArr = s.toCharArray();
		int[] shortest = new int[length];	//what string can b shortened to
		//initializing shortest
		for(int i = 0; i < length; i++) {
			int prev = 0;
			int curr = toInt(strArr[i]);
			if(i != 0) {
				prev = shortest[i - 1];
			}
			
			if(prev == 0) {	//empty
				shortest[i] = curr;
				continue;
			}
			if(prev == curr) {	//duplicate, remove
				shortest[i] = 0;
				continue;
			}
			//shorten to other letter
			shortest[i] = 6 - prev - curr;
		}
		
		//goin through queries! B)
		
		int numQueries = Integer.parseInt(br.readLine());
		for(int i = 0; i < numQueries; i++) {
			String[] temp = br.readLine().split(" ");	//god why is it 1 indexed :(
			int start = Integer.parseInt(temp[0]);
			int end = Integer.parseInt(temp[1]);
			
			int a = 0;
			if(start != 1) a = shortest[start - 2];
			int b = shortest[end - 1];
			if((a == 0 && b == 1) || (a == 1 && b == 0) || (a == 2 && b == 3) || (a == 3 && b == 2)) {
				System.out.print("Y");
			}else System.out.print("N");
		}
		
		
		br.close();
	}
	
	public static int toInt(char c) {
		switch(c) {
		case 'C':
			return 1;
		case 'O':
			return 2;
		default:
			return 3;
		}
	}
	
}
