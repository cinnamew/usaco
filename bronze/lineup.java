import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class lineup {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lineup.in"));
		
		Cow[] cowLine = new Cow[8];
		
		String[] finalLine = new String[8];
		
		String[] cowNames = {"Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue"};
		
		for(int i = 0; i < 8; i++) {
			cowLine[i] = new Cow(cowNames[i]);
		}
		
		boolean[] checked = new boolean[8];
		
		int friends = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < friends; i++) {
			String[] temp = (br.readLine()).split(" ");
			String firstCow = (temp[0]);
			String secondCow = (temp[temp.length - 1]);
			
			int firstCowIndex = Arrays.binarySearch(cowNames, firstCow);
			int secondCowIndex = Arrays.binarySearch(cowNames, secondCow);
			
			if(cowLine[firstCowIndex].getFriend1() != null) {
				cowLine[firstCowIndex].setFriend2(secondCow);
			}else {
				cowLine[firstCowIndex].setFriend1(secondCow);
			}
			
			if(cowLine[secondCowIndex].getFriend1() != null) {
				cowLine[secondCowIndex].setFriend2(firstCow);
			}else {
				cowLine[secondCowIndex].setFriend1(firstCow);
			}
		}
		
		int previousCow = 0;
		boolean endOfGroup = true;
		for(int i = 0; i < 8; i++) {
			if(!endOfGroup) {
				int friend1Pos = Arrays.binarySearch(cowNames, cowLine[previousCow].getFriend1());
				if(checked[friend1Pos] && cowLine[previousCow].getFriend2() != null) {
					int friend2Pos = Arrays.binarySearch(cowNames, cowLine[previousCow].getFriend2());
					finalLine[i] = cowNames[friend2Pos];
					checked[friend2Pos] = true;
					if(cowLine[friend2Pos].getFriends() > 1) {
						previousCow = friend2Pos;
					}else {
						endOfGroup = true;
					}
				}else {
					finalLine[i] = cowNames[friend1Pos];
					checked[friend1Pos] = true;
					if(cowLine[friend1Pos].getFriends() > 1) {
						previousCow = friend1Pos;
					}else {
						endOfGroup = true;
					}
				}
			}else {
				for(int j = 0; j < 8; j++) {
					if(!checked[j] && cowLine[j].getFriends() != 2) {
						finalLine[i] = cowNames[j];
						previousCow = j;
						checked[j] = true;
						if(cowLine[j].getFriends() == 1) {
							endOfGroup = false;
						}
						break;
					}
				}
			}
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lineup.out")));
		
		for(int i = 0; i < finalLine.length; i++) {
			System.out.println(finalLine[i]);
			pw.write(finalLine[i] + "\n");
		}
		pw.flush();
		
	}
	
	public static int findIndex(ArrayList<Cow> cowLine, String name) {
		for(int i = 0; i < cowLine.size(); i++) {
			if(cowLine.get(i).getName().equals(name)) {
				return i;
			}
		}
		
		return 0;
	}

}

class Cow {
	String name;
	String friend1;
	String friend2;
	int friends;
	
	public Cow(String name) {
		this.name = name;
		friend1 = null;
		friend2 = null;
		friends = 0;
	}
	
	public String getFriend1() {
		return friend1;
	}
	
	public String getFriend2() {
		return friend2;
	}
	
	public void setFriend1(String friend1) {
		this.friend1 = friend1;
		friends = 1;
	}
	
	public void setFriend2(String friend2) {
		this.friend2 = friend2;
		friends = 2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getFriends() {
		return friends;
	}
}