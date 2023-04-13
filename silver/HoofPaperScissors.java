import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class HoofPaperScissors {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("hps.in")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[] john = new char[n];
		int[] hPre = new int[n];
		int[] pPre = new int[n];
		int[] sPre = new int[n];
		
		for(int i = 0; i < n; i++) {
			char temp = br.readLine().charAt(0);
			john[i] = temp;
			if(i == 0) {
				hPre[i] = 0;
				pPre[i] = 0;
				sPre[i] = 0;
			}else {
				hPre[i] = hPre[i - 1];
				pPre[i] = pPre[i - 1];
				sPre[i] = sPre[i - 1];
			}
			if(temp == 'H') {
				hPre[i]++;
			}else if(temp == 'P') {
				pPre[i]++;
			}else sPre[i]++;
		}
		
		int[] hSuf = new int[n];
		int[] pSuf = new int[n];
		int[] sSuf = new int[n];
		
		hSuf[0] = 0;
		pSuf[0] = 0;
		sSuf[0] = 0;
		
		//why do u need suffix counts? can't u just subtract the previous prefix count?
		
		for(int i = 0; i < n; i++) {
			if(i != 0) {
				hSuf[i] = hSuf[i - 1];
				pSuf[i] = pSuf[i - 1];
				sSuf[i] = sSuf[i - 1];
			}
			
			if(john[n - i - 1] == 'H') hSuf[i]++;
			else if(john[n - i - 1] == 'P') pSuf[i]++;
			else sSuf[i]++;
		}
		
		int ans = 0;
		for(int i = 0; i <= n; i++) {
			int pre;
			int suf;
			if(i == 0) pre = 0;
			else pre = Math.max(Math.max(hPre[i - 1], pPre[i - 1]), sPre[i - 1]);
			if(i == n) suf = 0;
			else suf = Math.max(Math.max(hSuf[n - i - 1], pSuf[n - i - 1]), sSuf[n - i - 1]);
			
			ans = Math.max(ans, pre + suf);
		}
		
		System.out.println(ans);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		pw.write("" + ans);
		pw.close();
		
	}

}
