import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ContaminatedMilk {

	public static void main(String[] args)throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new  FileReader (new  File("badmilk.in")));
		String[] temp = br.readLine().split(" ");
		int numPpl = Integer.parseInt(temp[0]);
		int numMilks = Integer.parseInt(temp[1]);
		int numTests = Integer.parseInt(temp[2]);
		int numSick = Integer.parseInt(temp[3]);
		
		//hashmap of milks; milk #, num ppl who drank the milk
		HashMap<Integer, Integer> badMilks = new HashMap<>();
		
		Person[] ppl = new Person[numPpl];
		for(int i = 0; i < numPpl; i++) {
			ppl[i] = new Person();
		}
		for(int i = 0; i < numTests; i++) {
			String[] temp2 = br.readLine().split(" ");
			int p = Integer.parseInt(temp2[0]) - 1;
			int m = Integer.parseInt(temp2[1]);
			int t = Integer.parseInt(temp2[2]);
			
			if(!badMilks.containsKey(m)) {
				badMilks.put(m, 1);
			}else {
				boolean alreadyDrankThisMilk = false;
				for(int j = 0; j < ppl[p].getDrinks().size(); j++) {
					if(ppl[p].getDrinks().get(j).getMilk() == m) {
						alreadyDrankThisMilk = true;
					}
				}
				if(!alreadyDrankThisMilk) badMilks.replace(m, badMilks.get(m) + 1);
			}
			ppl[p].addToDrinks(t, m);
		}
		
		for(int i = 0; i < numPpl; i++) {
			Collections.sort(ppl[i].getDrinks());
		}
		
		
		
		//going through sick ppl
		for(int i = 0; i < numSick; i++) {
			String[] temp2 = br.readLine().split(" ");
			int p = Integer.parseInt(temp2[0]);
			int t = Integer.parseInt(temp2[1]);
			
			int numDrank = numMilksBeforeTime(t, ppl[p - 1].getDrinks());
			ArrayList<Integer> possibleBadMilks = ppl[p - 1].getDrinksUntilIndex(numDrank);
			
			for(int j = 0; j < numMilks; j++) {
				boolean couldBeBadMilk = false;
				for(int k = 0; k < possibleBadMilks.size(); k++) {
					if(j + 1 == possibleBadMilks.get(k)) {
						couldBeBadMilk = true;
					}
				}
				if(couldBeBadMilk) continue;
				if(badMilks.containsKey(j + 1)) {
					badMilks.remove(j + 1);
				}
			}
			System.out.println("Person: " + p + "; " + numDrank);
			
		}
		
		/*for(int i = 0; i < badMilks.size(); i++) {
			System.out.println("For milk " + (i + 1) + ": " + badMilks.get(i + 1));
		}*/
		
		
		int max = Collections.max(badMilks.values());
		
		//System.out.println(max);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("badmilk.out")));
		pw.write("" + max);
		pw.close();
		br.close();
	}
	
	public static int numMilksBeforeTime(int time, ArrayList<Drink> milks) {
		int low = 0;
		int high = milks.size() - 1;
		if(time < milks.get(0).getTime()) return 0;
		if(time > milks.get(high).getTime()) return high + 1;
		while(low != high - 1) {
			int mid = (low + high)/2;
			if(milks.get(mid).getTime() > time) {
				high = mid;
			}else if(milks.get(mid).getTime() < time) {
				low = mid;
			}else {
				return mid;
			}
		}
		return high;	//low++
	}
}

class Person {
	ArrayList<Drink> drinks = new ArrayList<>();

	public ArrayList<Drink> getDrinks() {
		return drinks;
	}

	public void addToDrinks(int time, int milk) {
		drinks.add(new Drink(time, milk));
	}
	
	public ArrayList<Integer> getDrinksUntilIndex(int index) {
		ArrayList<Integer> a = new ArrayList<>();
		for(int i = 0; i < index; i++) {
			a.add(drinks.get(i).getMilk());
		}
		return a;
	}
	
}

class Drink implements Comparable {
	int time;
	int milk;
	public Drink(int time, int milk) {
		this.time = time;
		this.milk = milk;
	}
	
	public int getTime() {
		return time;
	}

	public int getMilk() {
		return milk;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setMilk(int milk) {
		this.milk = milk;
	}

	@Override
	public String toString() {
		return "T: " + time + "; M: " + milk;
	}

	@Override
	public int compareTo(Object o) {
		Drink other = (Drink)o;
		if(time > other.getTime()) {
			return 1;
		}else if(time < other.getTime()) {
			return -1;
		}
		return 0;
	}
}