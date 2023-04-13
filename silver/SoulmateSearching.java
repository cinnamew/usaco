import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SoulmateSearching {

	int numOperations = 0;

	public static void main(String[] args)throws IOException {
		SoulmateSearching aaa = new SoulmateSearching();
		Scanner sc = new Scanner(System.in);
        int numPairs = sc.nextInt();

        for(int i = 0; i < numPairs; i++) {
            long first = sc.nextLong();
            long second = sc.nextLong();
            long ans = aaa.operations(first, second);
            aaa.numOperations = 0;
            System.out.println(ans);
        }
	}

	public long operations(long a, long b) {
		if(a == b) return numOperations;

		if(a > b) {
			if(a%2 == 1) {
				numOperations += 2;
				return operations((a + 1)/2, b);
			}
			numOperations++;
			return operations(a/2, b);
		}

		if(a == 1) {
			numOperations++;
			return operations(2, b);
		}

		long case1 = numOperations + eee(a, b);	//go down to closest multiple of 2 then mass multiply
		long case2 = numOperations;	//go up to closest multiple of 2 then mass multiply
		numOperations = 0;
		case2 += operations(a, b/(getClosestPowerOf2(a, b))) + eee(b/(getClosestPowerOf2(a, b)), b);
		return Math.min(case1,case2);

	}

	public long eee(long a, long b){
		long next = b/(getClosestPowerOf2(a,b)/2);
		if(next < b){
			return next - a + 1 + (eee(next*2, b));
		}
		return next - a;
	}

	//doesn't actually get the closest :')
	public long getClosestPowerOf2(long a, long b) {
		long check = b;
		long ret = 1;
		while(true) {
			if(check >= a){
				ret = ret*2;
				check = b/ret;
			}else {
				return ret;
			}
		}
	}

}
