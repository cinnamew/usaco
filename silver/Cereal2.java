import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Cereal2 {
	
	static int[] parents;
	static int[] size;
	static List[] cereals;
	static int[] visited;
	
	static ArrayList<Integer> cowOrder = new ArrayList<>();

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int numCows = Integer.parseInt(temp[0]);
		int numCereals = Integer.parseInt(temp[1]);
		visited = new int[numCows];
		parents = new int[numCereals];
		cereals = new List[numCereals];	//stores cows w/ that cereal as either 1st or 2nd choice
		size = new int[numCereals];
		for(int i = 0; i < numCereals; i++) {
			parents[i] = i;
			size[i] = 1;
			cereals[i] = new ArrayList<CerealCow>();
		}
		
		int numHungryCows = 0;
		for(int i = 0; i < numCows; i++) {
			String[] temp2 = br.readLine().split(" ");
			int firstFav = Integer.parseInt(temp2[0]);
			int secondFav = Integer.parseInt(temp2[1]);
			if(findParent(firstFav) == findParent(secondFav)) {
				numHungryCows++;
			}else union(firstFav, secondFav);
			CerealCow a = new CerealCow(i + 1, firstFav, secondFav, false);
			CerealCow b = new CerealCow(i + 1, secondFav, firstFav, true);	//need both to detect cycle
			cereals[firstFav].add(a);
			cereals[secondFav].add(b);
		}
		
		for(int i = 0; i < numCereals; i++) {
			if(visited[i] == 0) {
				visited[i] = 1;
				dfs(i);
			}
		}
		
		System.out.println(numHungryCows);
		
		
		br.close();
	}
	
	private static void dfs(int startFrom) {
		for(int i = 0; i < cereals[startFrom].size(); i++) {
			if(visited[((CerealCow)cereals[startFrom].get(i)).getTwo()] == 1) {
				return;
			}
			
			cowOrder.add(((CerealCow)cereals[startFrom].get(i)).getCow());
			
			visited[((CerealCow)cereals[startFrom].get(i)).getTwo()] = 1;
			dfs(((CerealCow)cereals[startFrom].get(i)).getTwo());
			
		}
	}
	
	public static int findParent(int node) {
		if(parents[node] == node) return node;
		parents[node] = findParent(parents[node]);
		return parents[node];
	}
	
	public static void union(int node1, int node2) {
		int parent1 = findParent(node1);
		int parent2 = findParent(node2);
		if(parent1 == parent2) return;
		if(size[parent2] > size[parent1]) {
			int temp = parent1;
			parent1 = parent2;
			parent2 = parent1;
		}
		size[parent1] += size[parent2];
		parents[parent2] = parent1;
	}
	
}

class CerealCow {
	int cow;
	int one;
	int two;
	boolean goingToFav;
	
	public CerealCow(int cow, int one, int two, boolean g) {
		this.cow = cow;
		this.one = one;
		this.two = two;
		goingToFav = g;
	}
	
	public int getCow() {
		return cow;
	}
	public int getOne() {
		return one;
	}
	public int getTwo() {
		return two;
	}
	public void setCow(int cow) {
		this.cow = cow;
	}
	public void setOne(int firstFav) {
		one = firstFav;
	}
	public void setTwo(int secondFav) {
		two = secondFav;
	}
	public boolean isGoingToFav() {
		return goingToFav;
	}
	public void setGoingToFav(boolean goingToFav) {
		this.goingToFav = goingToFav;
	}
	
}