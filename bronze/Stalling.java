import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Stalling {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new  FileReader (new  File("stalling.in")));
		int numCows = Integer.parseInt(br.readLine());
		Integer[] cows = new Integer[numCows];
		String[] temp = br.readLine().split(" ");
		for(int i = 0; i < numCows; i++) {
			cows[i] = Integer.parseInt(temp[i]);
		}
		int[] stalls = new int[numCows];
		String[] temp2 = br.readLine().split(" ");
		for(int i = 0; i < numCows; i++) {
			stalls[i] = Integer.parseInt(temp2[i]);
		}
		
		long permutations = 1;
		
		for(int i = 1; i <= numCows; i++) {
			permutations *= i;
		}
		
		Arrays.sort(cows, Collections.reverseOrder());
		
		long accum = 1;
		/*for(int i = 0; i < numCows; i++) {
			int subAccum = 0;
			for(int j = 0; j < numCows; j++) {
				if(cows[j] < stalls[j]) subAccum++;
			}
			accum*= subAccum - 1;
		}*/
		
		
		//int restrictions = 0;
		//ArrayList<Integer> restrictedCows = new ArrayList<Integer>();
		//ArrayList<Integer> restrictedStalls = new ArrayList<Integer>();
		
		int[] restrictions = new int[cows.length];
		
		for(int i = 0; i < stalls.length; i++) {
			for(int j = 0; j < numCows; j++) {
				if(stalls[i] < cows[j]) {
					//restrictions++;
					/*if(!restrictedCows.contains(cows[j])) {
						restrictedCows.add(cows[j]);
					}*/
					restrictions[j]++;
					/*if(!restrictedStalls.contains(stalls[i])) {
						restrictedStalls.add(stalls[i]);
					}*/
				}
			}
		}
		
		for(int i = 0; i < numCows; i++) {
			accum *= (numCows - restrictions[i] - i);
		}
		
		System.out.println(accum);
		
		/*long restrictedPermutations = 1;
		for(int i = 1; i <= numCows - 1; i++) {
			restrictedPermutations *= i;
		}
		restrictedPermutations *= restrictions;
		
		permutations -= restrictedPermutations;
		
		//overlapped restrictions(subtracted twice)!
		//reusing this variable bc idk save space but it's for the overlapped spaces
		/*restrictedPermutations = 1;
		for(int i = 1; i <= numCows - restrictedCows.size(); i++) {
			restrictedPermutations *= i;
		}*/
		
		
		
		/*for(int k = 1; k <= restrictions; k++){
			for(int i = 1; i <= numCows - restrictedCows.size(); i++) {
				restrictedPermutations *= i;
			}
		   //accum+=(N-k)!
		}
		//return N!-accum
		permutations += restrictedPermutations;
		System.out.println(permutations);*/
	}

}
