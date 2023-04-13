import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WordProcessor {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("word.in"));

		String[] limits = br.readLine().split(" ");
		int words = Integer.parseInt(limits[0]);
		int maxChars = Integer.parseInt(limits[1]);
		
		String[] essay = br.readLine().split(" ");
		int currentChars = 0;
		String endEssay = "";
		for(int i = 0; i < words; i++) {
			if(currentChars + essay[i].length() > maxChars) {
				currentChars = essay[i].length();
				endEssay += "\n" + essay[i];
			}else {
				if(currentChars == 0) {
					endEssay += essay[i];
				}else {
					endEssay += " " + essay[i];
				}
				currentChars += essay[i].length();
			}
		}
		
		System.out.println(endEssay);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("word.out")));
		pw.write(endEssay);
		pw.flush();
	}

}
