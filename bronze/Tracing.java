import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Tracing {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("tracing.in"));
		String[] firstLine = br.readLine().split(" ");
		int numCows = Integer.parseInt(firstLine[0]);
		int numInteractions = Integer.parseInt(firstLine[1]);
		String cowsString = br.readLine();
		int[] cows = new int[numCows];
		for(int i = 0; i < numCows; i++) {
			cows[i] = Integer.parseInt(cowsString.charAt(i) + "");
		}
		Interactions[] intersArr = new Interactions[numInteractions];
		for(int i = 0; i < numInteractions; i++) {
			String[] tempInters = br.readLine().split(" ");
			intersArr[i] = new Interactions(Integer.parseInt(tempInters[0]), Integer.parseInt(tempInters[1]), Integer.parseInt(tempInters[2]));
		}
		
		Arrays.sort(intersArr);
		
		int validCows = 0;
		int smallestK = Integer.MAX_VALUE;
		int largestK = 0;
		
		//cow i = patient zero
		for(int i = 0; i < numCows; i++) {
			
			boolean validPatientZero = false;
			//j = # of interactions cow can spread cowvid
			for(int j = 0; j <= numInteractions; j++) {
				//check if cows don't follow variables
				CowvidCow[] testCows = new CowvidCow[numCows];
				for(int k = 0; k < numCows; k++) {
					if(k == i) {
						testCows[k] = new CowvidCow(true, j);
					}else {
						testCows[k] = new CowvidCow(false, 0);
					}
				}
				for(int k = 0; k < numInteractions; k++) {
					int cow1 = intersArr[k].getCow1() - 1;
					int cow2 = intersArr[k].getCow2() - 1;
					if(testCows[cow1].getInfected() && testCows[cow1].getNumInteractionsLeft() > 0) {
						if(!testCows[cow2].getInfected()) {
							testCows[cow2].setInfected(true);
							testCows[cow2].setNumInteractionsLeft(j);
						}else {
							testCows[cow2].setNumInteractionsLeft(testCows[cow2].getNumInteractionsLeft() - 1);
						}
						testCows[cow1].setNumInteractionsLeft(testCows[cow1].getNumInteractionsLeft() - 1);
					}else if(testCows[cow2].getInfected() && testCows[cow2].getNumInteractionsLeft() > 0) {
						if(!testCows[cow1].getInfected()) {
							testCows[cow1].setInfected(true);
							testCows[cow1].setNumInteractionsLeft(j);
						}else {
							testCows[cow1].setNumInteractionsLeft(testCows[cow1].getNumInteractionsLeft() - 1);
						}
						testCows[cow2].setNumInteractionsLeft(testCows[cow2].getNumInteractionsLeft() - 1);
					}
				}
				
				boolean works = true;
				for(int k = 0; k < numCows; k++) {
					if(cows[k] == 1 && !testCows[k].getInfected()) {
						works = false;
						break;
					}else if(cows[k] == 0 && testCows[k].getInfected()) {
						works = false;
						break;
					}
				}
				
				if(works) {
					smallestK = Math.min(smallestK, j);
					largestK = Math.max(largestK, j);
					validPatientZero = true;
				}
			}
			if(validPatientZero) validCows++;
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("tracing.out")));
		pw.write(validCows + " " + smallestK);
		if(largestK == numInteractions) {
			pw.write(" Infinity");
		}else {
			pw.write(" " + largestK);
		}
		
		pw.flush();
	}

}

class Interactions implements Comparable{
	int time;
	int cow1;
	int cow2;
	
	public Interactions(int time, int cow1, int cow2) {
		this.time = time;
		this.cow1 = cow1;
		this.cow2 = cow2;
	}

	@Override
	public int compareTo(Object otherInteraction) {
		int otherTime = ((Interactions)otherInteraction).getTime();
		if(time < otherTime) {
			return -1;
		}else if(time > otherTime) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public int getTime() {
		return time;
	}
	
	public int getCow1() {
		return cow1;
	}
	
	public int getCow2() {
		return cow2;
	}
}

class CowvidCow {
	boolean infected;
	int numInteractionsLeft;
	
	public CowvidCow(boolean infected, int numInteractionsLeft) {
		this.infected = infected;
		this.numInteractionsLeft = numInteractionsLeft;
	}
	
	public int getNumInteractionsLeft() {
		return numInteractionsLeft;
	}
	
	public void setNumInteractionsLeft(int numInteractionsLeft) {
		this.numInteractionsLeft = numInteractionsLeft;
	}
	
	public boolean getInfected() {
		return infected;
	}
	
	public void setInfected(boolean infected) {
		this.infected = infected;
	}
}