import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;

public class Badmilk {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("badmilk.in")));
		String[] temp = br.readLine().split(" ");
		int numCows = Integer.parseInt(temp[0]);
		int milkTypes = Integer.parseInt(temp[1]);
		int numDrinks = Integer.parseInt(temp[2]);
		int numSickCows = Integer.parseInt(temp[3]);
		
		Milk[] milkTasting = new Milk[numDrinks];
		
		for(int i = 0; i < numDrinks; i++) {
			String[] temp2 = br.readLine().split(" ");
			milkTasting[i] = new Milk(Integer.parseInt(temp2[0]), Integer.parseInt(temp2[1]), Integer.parseInt(temp2[2]));
		}
		
		Arrays.sort(milkTasting);
		
		int[] sickCows = new int[numSickCows];
		int[] sickCowTimes = new int[numSickCows];
		for(int i = 0; i < numSickCows; i++) {
			String[] temp2 = br.readLine().split(" ");
			sickCows[i] = Integer.parseInt(temp2[0]);
			sickCowTimes[i] = Integer.parseInt(temp2[1]);
		}
		
		int maxCowsInfected = 0;
		
		for(int i = 1; i <= milkTypes; i++) {
			HashSet<Integer> tempSickCows = new HashSet<Integer>();
			int[] drinkingTime = new int[numCows];
			boolean validMilkType = true;
			
			for(int j = 0; j < numDrinks; j++) {
				if(milkTasting[j].getType() == i) {
					tempSickCows.add(milkTasting[j].getCow());
					if(drinkingTime[milkTasting[j].getCow() - 1] == 0) {
						drinkingTime[milkTasting[j].getCow() - 1] = milkTasting[j].getTime();
					}
					
				}
			}
			
			for(int j = 0; j < numSickCows; j++) {
				if(!(tempSickCows.contains(sickCows[j]) && drinkingTime[sickCows[j] - 1] < sickCowTimes[j])) {
					validMilkType = false;
					break;
				}
			}
			
			if(validMilkType) {
				if(tempSickCows.size() > maxCowsInfected) maxCowsInfected = tempSickCows.size();
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("badmilk.out")));
		pw.write("" + maxCowsInfected);
		pw.close();
		System.out.println(maxCowsInfected);
		
	}

}

class Milk implements Comparable {
	int cow;
	int type;
	int time;
	
	public Milk(int cow, int type, int time) {
		this.cow = cow;
		this.type = type;
		this.time = time;
	}

	public int getCow() {
		return cow;
	}

	public int getType() {
		return type;
	}

	public int getTime() {
		return time;
	}

	@Override
	public int compareTo(Object otherMilk) {
		if(time < ((Milk)otherMilk).getTime()) {
			return -1;
		}else if(time == ((Milk)otherMilk).getTime()) {
			return 0;
		}
		return 1;
	}
	
}