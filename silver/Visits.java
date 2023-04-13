import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Visits {
	
	static int[] nodeGoTo;
	static long[] weight;
	static boolean[] visited;
	
	static int[] parents;
	
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numCows = Integer.parseInt(br.readLine());
		//1 INDEXED
		nodeGoTo = new int[numCows + 1];
		weight = new long[numCows + 1];
		visited = new boolean[numCows + 1];
		parents = new int[numCows + 1];
		
		//init dsu :D
		for(int i = 0; i < parents.length; i++) {
			parents[i] = i;
			visited[i] = false;
		}
		
		long ans = 0;
		
		for(int i = 1; i <= numCows; i++) {
			String[] temp = br.readLine().split(" ");
			nodeGoTo[i] = Integer.parseInt(temp[0]);
			weight[i] = Long.parseLong(temp[1]);
			parents[i] = find(nodeGoTo[i]);
			ans += weight[i];
		}
		
		ArrayList<Integer> setParents = new ArrayList<>();
		//finding set parents
		for(int i = 1; i <= numCows; i++) {
			if(parents[i] == i) setParents.add(i);
		}
		
		//1 -> 2 -> 3 -> 4 -> 3 case only 3 & 4 part of cycle
		for(int i = 0; i < setParents.size(); i++) {
			int alyssa = setParents.get(i);	//cuz she's a starter haha i'm so funny
			//run through set & find the start
			int temp = alyssa;
			while(true) {
				if(visited[temp]) {
					alyssa = temp;
					break;
				}
				if(!visited[temp]) {
					visited[temp] = true;
					temp = nodeGoTo[temp];
				}
				
			}
			
			//run through cycle to find the minimum
			int curr = nodeGoTo[alyssa];
			long min = Long.MAX_VALUE;
			while(true) {
				if(weight[curr] < min) min = weight[curr];
				if(curr == alyssa) {
					break;
				}
				curr = nodeGoTo[curr];
			}
			
			ans -= min;
			//delete min from answer
		}
		
		
		System.out.println(ans);
		
		
		br.close();
	}
	
	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
}
