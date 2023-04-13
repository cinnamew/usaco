/*
ID: jolie.h1
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;

public class ride {

	public static void main(String[] args)throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("ride.in"));
		String comet = f.readLine();
		String group = f.readLine();
		//String comet = "ABSTAR";
		//String group = "USACO";
		
		int comNum = makeNumber(comet);
		int groupNum = makeNumber(group);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
		if((comNum%47)==(groupNum%47)) {
			pw.write("GO\n");
			//System.out.println("GO");
		}else {
			pw.write("STAY\n");
			//System.out.println("STAY");
		}
		pw.flush();
		
	}
	
	public static int makeNumber(String s) {
		int ans = 1;
		for(int i = 0; i<s.length(); i++) {
			char c = s.charAt(i);
			ans *= ((int)c-64);
		}
		return ans;
	}

}
