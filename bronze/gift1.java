/*
ID: jolieh1
LANG: JAVA
TASK: gift1
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class gift1 {

	public static void main(String[] args)throws IOException {
		BufferedReader g = new BufferedReader(new FileReader("gift1.in"));
		int pnum = Integer.parseInt(g.readLine());
		Account gifters[] = new Account[pnum];
		for(int i = 0; i < pnum; i++) {
			String pname = g.readLine();
			gifters[i] = new Account(pname);
		}
		for(int i = 0; i < gifters.length; i++) {
			String name = g.readLine();
			String temp = g.readLine();
			String[] together = temp.split(" ");
			int money = Integer.parseInt(together[0]);
			int recievers = Integer.parseInt(together[1]);
			int perPerson = 0;
			int remainder = 0;
			if(recievers != 0) {
				perPerson = money/recievers;
				remainder = money%recievers;
			}
			
			Account giver = getAcc(name, gifters);
			giver.addBal(remainder-money);
			
			for(int j = 0; j < recievers; j++) {
				String recName = g.readLine();
				Account reciever = getAcc(recName, gifters);
				reciever.addBal(perPerson);
			}
			
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		
		Arrays.sort(gifters);
		
		for(int i = 0; i < gifters.length; i++) {
			pw.write("" + gifters[i].getName()+" "+gifters[i].getBal() + "\n");
			System.out.println(gifters[i].getName()+" "+gifters[i].getBal());
		}
		
		pw.flush();
	}
	
	public static Account getAcc(String name, Account[] gifters) {
		for(int i = 0; i < gifters.length; i++) {
			if(gifters[i].getName().equals(name)) {
				return gifters[i];
			}
		}
		return null;
	}

}

class Account implements Comparable{
	String name;
	int bal;
	
	
	
	public Account() {
		name = "";
		bal = 0;
	}
	
	public Account(String name) {
		this.name = name;
	}
	
	public Account(String name, int bal) {
		this.name = name;
		this.bal = bal;
	}
	
	public String getName() {
		return name;
	}
	public int getBal() {
		return bal;
	}
	public void setBal(int bal) {
		this.bal = bal;
	}
	public void addBal(int add) {
		bal += add;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Object otherAcc) {
		if(bal<((Account)otherAcc).getBal()) {
			return -1;
		}else if(bal == ((Account)otherAcc).getBal()) {
			return 0;
		}
		return 1;
	}
}