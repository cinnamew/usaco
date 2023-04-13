import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;

public class FindAndReplace {
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		
		int numLines = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < numLines; i++) {
			String first = br.readLine();
			String second = br.readLine();
			System.out.println(solve(first, second));
		}
		br.close();
		pw.close();
	}
	
	private static int solve(String a, String b) {
		int[] map = new int[52];
		boolean[] alreadyChecked = new boolean[52];
		boolean[] used = new boolean[52];
		HashMap<Integer, Integer> directMap = new HashMap<Integer, Integer>();
		//HashSet<Integer> stringBVals = new HashSet<Integer>();
		int[] fiftytwoChecker = new int[52];
		char[] stringa = a.toCharArray();
		char[] stringb = b.toCharArray();
		int counter = 0;
		
		for(int i = 0; i < a.length(); i++) {
			int first = getAsciiValue(stringa[i]);
			int second = getAsciiValue(stringb[i]);
			
			if(directMap.containsKey(first)) {
				if(directMap.get(first) != second) return -1;
			}else {
				directMap.put(first, second);
				if(first != second) counter++;
			}
			map[first] = second;
			fiftytwoChecker[second]=1;
		}
		
		boolean fiftytwoChars = true;
		for(int i = 0; i < 52; i++){
			if(fiftytwoChecker[i] == 0){
				fiftytwoChars = false;
				break;
			}
		}
		
		if(fiftytwoChars){
			fiftytwoChars = false;
			for(int i = 0; i < 52; i++){
				if(i != map[i]){
					fiftytwoChars = true;
				}
			}
		};
		
		int numCycles = 0;
		boolean[] partOfCycle = new boolean[52];
		int numToSubtract = 0;
		for(int i = 0; i < 52; i++) {
			if(!directMap.containsKey(i) || alreadyChecked[i] == true || directMap.get(i) == i) continue;
			int curr = i;
			//boolean foundNewCycle = false;
			while(true) {
				alreadyChecked[curr] = true;
				curr = map[curr];
				
				if(curr == i) {
					if(fiftytwoChars){
						return -1;
					}
					numCycles++;
					//foundNewCycle = true;
					int temp = map[curr];
					while(true) {
						partOfCycle[temp] = true;
						if(temp == curr) {
							break;
						}
						temp = map[temp];
					}
					break;
				}
				if(partOfCycle[curr] && !partOfCycle[i]) {
					//if the cycle was already used, then skip
					if(!used[curr]){
						//node pointing in
						numToSubtract++;
						//make the whole cycle "used", so you won't subtract multiple times
						int tempcurr = curr;
						while(true){
							used[tempcurr] = true;
							tempcurr = map[tempcurr];
							if(tempcurr == curr){
								break;
							}
						}
					}
				}
				if(!directMap.containsKey(curr) || directMap.get(curr) == curr || alreadyChecked[curr]) break;
			}
		}
		return counter + numCycles - numToSubtract;
	}
	
	private static int getAsciiValue(char c) {
		int temp = (int)c;
		
		if(temp <= 90) {
            return temp - 39;
		}
		
		return temp - 97;
		
	}
	
}

