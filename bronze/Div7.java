import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class Div7 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("div7.in"));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		
		int ans = 0;
		long prefixSum = 0;
		HashMap<Integer, Integer> firstIndex = new HashMap<Integer, Integer>();
		firstIndex.put(0, -1);
		
		for(int i = 0; i < num; i++) {
			int currNum = Integer.parseInt(br.readLine());
			prefixSum += currNum;
			if(firstIndex.containsKey((int)(prefixSum%7))) {
				int tempAns = i - firstIndex.get((int)(prefixSum%7));
				ans = Math.max(ans, tempAns);
			}else {
				firstIndex.put((int)(prefixSum%7), i);
				
			}
		}
		
		System.out.println(ans);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
		pw.write("" + ans);
		pw.flush();

	}

}
