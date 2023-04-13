import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class gymnastics {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("gymnastics.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] ranking = new int[K][N];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j = 0; j < N; j++) {
				ranking[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int pairs = 0;
		
		for(int i = 1; i < N; i++) {
			for(int j = i + 1; j <= N; j++) {
				boolean iGreater = false;
				boolean jGreater = false;
				for(int k = 0; k < K; k++) {
					int iPos = 0;
					int jPos = 0;
					for(int l = 0; l < N; l++) {
						if(ranking[k][l] == i) {
							iPos = l;
						}else if(ranking[k][l] == j) {
							jPos = l;
						}
					}
					
					if(iPos > jPos) {
						iGreater = true;
					}else {
						jGreater = true;
					}
					
				}
				if(iGreater == false || jGreater == false) {
					pairs++;
				}
			}
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gymnastics.out")));
		pw.write("" + pairs);
		pw.flush();
	}

}
