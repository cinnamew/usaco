import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConvolutedIntervals {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		int[] starts = new int[M + 1];
		int[] ends = new int[M + 1];
		for(int i = 0; i < N; i++) {
			String[] temp2 = br.readLine().split(" ");
			starts[Integer.parseInt(temp2[0])]++;
			ends[Integer.parseInt(temp2[1])]++;
		}
		
		long[] startSums = new long[2*M + 1];
		long[] endSums = new long[2*M + 1];
		
		for(int i = 0; i <= M; i++) {
			for(int j = 0; j <= M; j++) {
				startSums[i + j] += (long)starts[i] * starts[j];
				endSums[i + j] += (long)ends[i] * ends[j];
			}
		}
		
		long ans = 0;
		for(int i = 0; i <= 2*M; i++) {
			ans += startSums[i];
			System.out.println(ans);
			ans -= endSums[i];
		}
		
		br.close();
	}
}
