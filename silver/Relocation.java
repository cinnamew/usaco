import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Relocation {
	static int[] markets;
	static ArrayList<ArrayList<Pair>> roads;
	static int[][] dists;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new  FileReader (new  File("relocate.in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numTowns = Integer.parseInt(st.nextToken());
		int numRoads = Integer.parseInt(st.nextToken());
		int numMarkets = Integer.parseInt(st.nextToken());
		markets = new int[numMarkets];
		dists = new int[numMarkets][numTowns + 1];
		visited = new boolean[numMarkets][numTowns + 1];
		roads = new ArrayList<ArrayList<Pair>>(numTowns + 1);
		
		for(int i = 0; i < numMarkets; i++) {
			markets[i] = Integer.parseInt(br.readLine());
		}
		for(int i = 1; i <= numTowns; i++) {
			for(int j = 0; j < numMarkets; j++) {
				
				dists[j][i] = Integer.MAX_VALUE;
			}
			roads.add(new ArrayList<>());
		}
		
		roads.add(new ArrayList<>());
		
		for (int i = 0; i < numRoads; i++) {
			int u, v, w;	//from u to v w/ weight w
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			roads.get(u).add(new Pair(w, v));
			roads.get(v).add(new Pair(w, u));
		}
		int[] marketIndices = new int[numMarkets];
		
		for(int i = 0; i < numMarkets; i++) {
			marketIndices[i] = i;
			dijkstra(i, markets[i]);
		}
		
		List<List<Integer>> permutations = permute(marketIndices);
		int ans = Integer.MAX_VALUE;
		for(int i = 0; i < permutations.size(); i++) {
			int temp = 0;
			List<Integer> currPerm = permutations.get(i);
			for(int j = 1; j < currPerm.size(); j++) {
				temp += dists[currPerm.get(j)][markets[currPerm.get(j - 1)]];
			}
			int closestTownDist = Integer.MAX_VALUE;
			for(int j = 1; j <= numTowns; j++) {
				boolean temp3 = false;
				for(int k = 0; k < numMarkets; k++) {
					if(markets[k] == j) {
						temp3 = true;
						break;
					}
				}
				if(temp3) continue;
				int temp2 = dists[currPerm.get(0)][j] + dists[currPerm.get(numMarkets - 1)][j];
				if(temp2 < closestTownDist) closestTownDist = temp2;
			}
			temp += closestTownDist;
			if(temp < ans) ans = temp;
		}
		
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("relocate.out")));
		pw.write("" + ans);
		System.out.println(ans);
		pw.close();
		br.close();
	}
	
	public static List<List<Integer>> permute(int[] nums) {
	    List<List<Integer>> results = new ArrayList<List<Integer>>();
	    if (nums == null || nums.length == 0) {
	        return results;
	    }
	    List<Integer> result = new ArrayList<>();
	    dfs(nums, results, result);
	    return results;
	}

	public static void dfs(int[] nums, List<List<Integer>> results, List<Integer> result) {
	    if (nums.length == result.size()) {
	        List<Integer> temp = new ArrayList<>(result);
	        results.add(temp);
	    }        
	    for (int i=0; i<nums.length; i++) {
	        if (!result.contains(nums[i])) {
	            result.add(nums[i]);
	            dfs(nums, results, result);
	            result.remove(result.size() - 1);
	        }
	    }
	}

	
	public static void dijkstra(int index, int market) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(0, market));
		
		while(!pq.isEmpty()) {
			//pop it & get the node + weight
			Pair temp = pq.poll();
			int dist = temp.a;
			int node = temp.b;
			
			//only go on to the rest if it's the first time node is visited
			if(visited[index][node]) {
				continue;
			}
			
			visited[index][node] = true;
			
			//loop through all connected ones to node
			for(Pair p : roads.get(node)) {
				int newNodeDist = p.a + dist;
				int newNode = p.b;
				if(newNodeDist < dists[index][newNode]) {
					dists[index][newNode] = newNodeDist;
					pq.add(new Pair(newNodeDist, newNode));	//new pair bc old one = edge(not between first & this)
				}
			}
		}
		
	}

}

class Pair implements Comparable<Pair> {
	public int a;	//dist
	public int b;	//node
	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public int compareTo(Pair o) {
		if(o.a > a) return -1;
		if(o.a == a) return 0;
		return 1;
		//return (int)(a - o.getA());
	}
	
}
