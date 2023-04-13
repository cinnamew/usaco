import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class AngryCows {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("angry.in")));
		int numHayBales = Integer.parseInt(br.readLine());
		int[] bales = new int[numHayBales];
		for(int i = 0; i < numHayBales; i++) {
			bales[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bales);
		
		/*
		 * pseudocode uwu
		 * keep ans var
		 * for loop that runs through all hay bales
		 * 	keep counter & add every time a new hay bale is met
		 * 	//go left
		 * 
		 * 	while loop to find leftmost hay bale, simulate until end
		 * 		- can keep a var to track the last bale u hit(int prob, store its location)
		 * 		- reset int to -1 once u find the last one(check @ end if var == -1)
		 * 		- need to add to ans var every time hay bale is hit
		 * 		while loop condition: int != -1 && not @ beginning(j == 0)
		 * 
		 * 	//go right
		 * 	while loop to find rightmost hay bale, simulate until end
		 * 	
		 * 	set ans var to max of ans & counter
		 * return ans
		 */
		
		
		for(int i = 0; i < numHayBales; i++) {
			
		}
		
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		pw.write("");
		pw.close();
	}
}
