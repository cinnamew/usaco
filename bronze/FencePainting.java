import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FencePainting {

	public static void main(String[] args)throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("paint.in"));
		String[] temp = br.readLine().split(" ");
		int a = Integer.parseInt(temp[0]);
		int b = Integer.parseInt(temp[1]);
		String[] temp2 = br.readLine().split(" ");
		int c = Integer.parseInt(temp2[0]);
		int d = Integer.parseInt(temp2[1]);
		
		int sum = b - a;
		if(d > b) {
			sum += d - (Math.max(b, c));
		}
		
		if(a > c) {
			sum += (Math.min(a, d)) - c;
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));
		pw.write("" + sum);
		//System.out.println(sum);
		pw.flush();
		
	}

}
