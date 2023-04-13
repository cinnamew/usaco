import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class CowFrisbee {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numCows = Integer.parseInt(br.readLine());
		int[] cows = new int[numCows];
		String[] temp = br.readLine().split(" ");
		for(int i = 0; i < numCows; i++) {
			cows[i] = Integer.parseInt(temp[i]);
		}
		
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		
		//4 3 1 2 5 6 7
		
		//4 3 1 0 0 0 0 2
		
		long ans = 0;
        for(int i = 0; i < cows.length; i++) {
            int currHeight = cows[i];
            while(!stack.isEmpty() && cows[stack.peekLast()] < currHeight) {
            	if(stack.peekLast() == i - 1) ans -= 2;	//for the double counting
            	ans += (long)i - stack.pollLast() + 1;
            }
            //neighbor
            if(!stack.isEmpty() && stack.peekLast() != i - 1) {
            	ans += (long)i - stack.peekLast() + 1;
            }
            
            stack.addLast(i);
            
            if(i != cows.length - 1) ans += 2;	//for every pair
            //would probably add 1 too many times for 3, 4 / 4, 10 / etc.
        }
		
        System.out.println(ans);
        
		br.close();
	}
}
