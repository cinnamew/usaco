import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SkiDesign {

	public static void main(String[] args)throws IOException {
		//BufferedReader br = new BufferedReader(new  FileReader (new  File("skidesign.in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numHills = Integer.parseInt(br.readLine());
		int[] hillHeights = new int[numHills];
		int lowestHill = Integer.MAX_VALUE;
		int tallestHill = 0;
		int secondLowest = 0;
		int secondTallest = 0;
		int tallestHillIndex = 0;
		int lowestHillIndex = 0;
		
		for(int i = 0; i < numHills; i++) {
			hillHeights[i] = Integer.parseInt(br.readLine());
			if(hillHeights[i] < lowestHill)  {
				secondLowest = lowestHill;
				lowestHill = hillHeights[i];
				lowestHillIndex = i;
			}
			if(hillHeights[i] > tallestHill) {
				secondTallest = tallestHill;
				tallestHill = hillHeights[i];
				tallestHillIndex = i;
			}
		}
		
		int money = 0;
		boolean avoidTax = false;
		if(tallestHill - lowestHill <= 17) avoidTax = true;
		
		while(avoidTax == false) {
			if(secondLowest - lowestHill < tallestHill - secondTallest) {
				int x = tallestHill - 17 - lowestHill;
				hillHeights[tallestHillIndex] -= x;
				money += x*x;
			}else {
				int x = tallestHill - 17 - lowestHill;
				hillHeights[lowestHillIndex] += x;
				money += x*x;
			}
			
			if(Math.max(secondTallest, hillHeights[tallestHillIndex]) - Math.min(hillHeights[lowestHillIndex], secondLowest) <= 17) {
				avoidTax = true;
				break;
			}
			
			tallestHill = 0;
			lowestHill = Integer.MAX_VALUE;
			for(int i = 0; i < numHills; i++) {
				if(hillHeights[i] < lowestHill)  {
					secondLowest = lowestHill;
					lowestHill = hillHeights[i];
					lowestHillIndex = i;
				}
				if(hillHeights[i] > tallestHill) {
					secondTallest = tallestHill;
					tallestHill = hillHeights[i];
					tallestHillIndex = i;
				}
			}
			
		}
		
		System.out.println(money);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
		pw.write("");
		pw.close();
	}

}
