import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Uddered {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new  FileReader (new  File("uddered.in")));
		
		String temp = br.readLine();
		char[] cowphabet = temp.toCharArray();
		
		String temp2 = br.readLine();
		ArrayList<Character> lettersHeard = new ArrayList<Character>();
		
	    for(int i = 0; i < temp2.length(); i++){
	    	lettersHeard.add(temp2.charAt(i));
	    }
		
		int minTimes = 0;
		boolean countedAlphabet = false;
		while(lettersHeard.size() >= 1) {
			countedAlphabet = false;
			for(int i = 0; i < cowphabet.length; i++) {
				if(cowphabet[i] == lettersHeard.get(0)) {
					if(!countedAlphabet) minTimes++;
					countedAlphabet = true;
					lettersHeard.remove(0);
					if(lettersHeard.size() == 0) break;
				}
			}
		}
		
		//System.out.println(minTimes);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.write("" + minTimes);
		pw.flush();
		
	}

}
