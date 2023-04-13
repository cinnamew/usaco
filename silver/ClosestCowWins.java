import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ClosestCowWins {
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int numPatches = Integer.parseInt(temp[0]);
		int numNCows = Integer.parseInt(temp[1]);
		int numJCows = Integer.parseInt(temp[2]);
		GrassPatch[] patches = new GrassPatch[numPatches];
		for(int i = 0; i < numPatches; i++) {
			String[] temp2 = br.readLine().split(" ");
			patches[i] = new GrassPatch(Integer.parseInt(temp2[0]), Integer.parseInt(temp2[1]));
		}
		Arrays.sort(patches);
		int[] NCows = new int[numNCows];
		for(int i = 0; i < numNCows; i++) {
			NCows[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(NCows);
		
		int leftPointer = 0;
		int rightPointer = 0;
		
		long[] possibleAns = new long[2*numNCows];	//tastiness of putting each cow in each position
		//left/first
		while(patches[leftPointer].getPos() < NCows[0]) {
			possibleAns[0] += patches[leftPointer].getTastiness();
			leftPointer++;
			rightPointer++;
		}
		
		//middle
		for(int i = 1; i < numNCows; i++) {
			int dist = NCows[i] - NCows[i - 1];
			long oneCow = 0, moreCows = 0;
			leftPointer = rightPointer;
			while(patches[rightPointer].getPos() < NCows[i]) {
				if(patches[rightPointer].getPos() - patches[leftPointer].getPos() >= dist/2) {
					possibleAns[2*i - 1] = Math.max(possibleAns[2*i - 1], oneCow);
					oneCow -= patches[leftPointer].getTastiness();
					leftPointer++;
				}else {
					oneCow += patches[rightPointer].getTastiness();
					moreCows += patches[rightPointer].getTastiness();
					rightPointer++;
				}
				
			}
			possibleAns[2*i - 1] = Math.max(possibleAns[2*i - 1], oneCow);
			possibleAns[2*i] = moreCows - possibleAns[2*i - 1];	//additional tastiness possible if we add 1 more cow
		}
		
		//last
		while(rightPointer < numPatches) {
			possibleAns[2*numNCows - 1] += patches[rightPointer].getTastiness();
			rightPointer++;
		}
		//System.out.println(Arrays.toString(possibleAns));
		
		//taking the N max ans
		Arrays.sort(possibleAns);
		long ans = 0;
		
		for(int i = 2*numNCows - 1; i > 2*numNCows - 1 - numJCows; i--) {
			ans += possibleAns[i];
		}
		
		System.out.println(ans);
		
		br.close();
	}
}

class GrassPatch implements Comparable<GrassPatch> {
	int pos;
	int tastiness;
	public GrassPatch(int pos, int tastiness) {
		this.pos = pos;
		this.tastiness = tastiness;
	}
	public int getPos() {
		return pos;
	}
	public int getTastiness() {
		return tastiness;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public void setTastiness(int tastiness) {
		this.tastiness = tastiness;
	}
	@Override
	public int compareTo(GrassPatch o) {
		if(pos > o.getPos()) return 1;
		else if(pos == o.getPos()) return 0;
		return -1;
	}
	
}