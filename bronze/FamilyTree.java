import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class FamilyTree {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("family.in")));
		String[] temp = br.readLine().split(" ");
		int numRelations = Integer.parseInt(temp[0]);
		String personA = temp[1];
		String personB = temp[2];
		
		HashMap<String, String> family = new HashMap<String, String>();
		for(int i = 0; i < numRelations; i++) {
			String[] temp2 = br.readLine().split(" ");
			family.put(temp2[1], temp2[0]);
		}
		
		HashMap<String, Integer> personARelatives = new HashMap<String, Integer>();
		String currPerson = personA;
		int tracker = 0;
		personARelatives.put(personA, 0);
		while(family.containsKey(currPerson)) {
			//personARelatives.put(currPerson, tracker);
			currPerson = family.get(currPerson);
			tracker++;
			personARelatives.put(currPerson, tracker);
		}
		
		currPerson = personB;
		tracker = 0;
		while(!personARelatives.containsKey(currPerson) && family.containsKey(currPerson)) {
			currPerson = family.get(currPerson);
			tracker++;
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("family.out")));
		if(!personARelatives.containsKey(currPerson)) {
			System.out.println("NOT RELATED");
			pw.write("NOT RELATED");
			pw.close();
			System.out.println(tracker);
			System.out.println(personARelatives);
			return;
		}
		
		int personADist = personARelatives.get(currPerson);
		int personBDist = tracker;
		
		if(personBDist > personADist) {
			personBDist = personADist;
			personADist = tracker;
			
			String temp3 = personA;
			personA = personB;
			personB = temp3;
		}
		
		if(personADist == 1 && personBDist == 1) {
			System.out.println("SIBLINGS");
			pw.write("SIBLINGS");
			pw.close();
			return;
		}
		
		if(personBDist == 1) {
			pw.write(personB + " is the ");
			for(int i = 0; i < personADist - 2; i++) {
				pw.write("great-");
			}
			pw.write("aunt of " + personA);
			pw.close();
			return;
		}
		
		if(personBDist == 0) {
			pw.write(personB + " is the ");
			for(int i = 0; i < personADist - 2; i++) {
				pw.write("great-");
			}
			if(personADist >= 2) pw.write("grand-");
			pw.write("mother of " + personA);
			pw.close();
			return;
		}
		
		
		
		pw.write("COUSINS");
		pw.close();
	}

}
