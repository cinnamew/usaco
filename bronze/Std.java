import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Std {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("std.in"));
		String[] temp = br.readLine().split(" ");
		ArrayList<Integer> ints = new ArrayList<Integer>();
		//int[] allInts = new int[7];
		
		int abc = 0;
		int abcPlace = 0;
		for(int i = 0; i < 7; i++) {
			/*allInts[i] = Integer.parseInt(temp[i]);
			if(allInts[i] > allInts[i - 1]) {
				abc = allInts[i];
			}*/
			ints.add(i, Integer.parseInt(temp[i]));
			if(ints.get(i) > abc) {
				abc = ints.get(i);
				abcPlace = i;
			}
		}
		//remove a + b + c
		ints.remove(abcPlace);
		
		Collections.sort(ints);
		
		//this is either a + b, b + c, or a + c
		int secondLargest = ints.get(ints.size() - 1);
		ints.remove(ints.size() - 1);
		
		int ansa = 0;
		int ansb = 0;
		int ansc = 0;
		//possible error: if a, b, and/or c overlap(should be different numbers)
		for(int a = 0; a < ints.size(); a++) {
			for(int b = a; b < ints.size(); b++) {
				if(ints.get(a) + ints.get(b) == secondLargest) {
					for(int c = 0; c < ints.size(); c++) {
						if(ints.get(a) + ints.get(b) + ints.get(c) == abc) {
							ansa = ints.get(a);
							ansb = ints.get(b);
							ansc = ints.get(c);
						}
					}
				}/*else {
					break;
				}*/
			}
		}
		
		int[] answers = new int[3];
		answers[0] = ansa;
		answers[1] = ansb;
		answers[2] = ansc;
		Arrays.sort(answers);
		
		//System.out.println("a: " + ansa + "\nb: " + ansb + "\nc: " + ansc);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.write(answers[0] + " " + answers[1] + " " + answers[2]);
		pw.flush();
	}

}
