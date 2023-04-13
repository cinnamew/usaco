import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class KeyboardI {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numCons = Integer.parseInt(br.readLine());
		int numVowels = Integer.parseInt(br.readLine());
		String[] temp = br.readLine().split(" ");
		int[] consVals = new int[temp.length];
		String[] temp2 = br.readLine().split(" ");
		int[] vowelVals = new int[temp2.length];
		Key[] keyDiff = new Key[temp.length];
		for(int i = 0; i < temp.length; i++) {
			consVals[i] = Integer.parseInt(temp[i]);
			vowelVals[i] = Integer.parseInt(temp2[i]);
			keyDiff[i] = new Key(vowelVals[i], consVals[i], i);
		}
		Arrays.sort(keyDiff);
		
		
		
	}

}

class Key implements Comparable<Key> {
	int vowelVal;
	int consVal;
	int index;
	int diff;
	boolean consLarger;
	
	public Key(int v, int c, int i) {
		vowelVal = v;
		consVal = c;
		index = i;
		diff = Math.abs(vowelVal - consVal);
		if(consVal > vowelVal) consLarger = true;
	}
	
	//sort by difference
	@Override
	public int compareTo(Key otherKey) {
		if(diff > otherKey.getDiff()) return 1;
		else if(diff == otherKey.getDiff()) return 0;
		else return -1;
	}

	public int getIndex() {
		return index;
	}

	public int getDiff() {
		return diff;
	}

	public boolean isConsLarger() {
		return consLarger;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setDiff(int diff) {
		this.diff = diff;
	}

	public void setConsLarger(boolean consLarger) {
		this.consLarger = consLarger;
	}
	
}