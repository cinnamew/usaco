import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;

public class ConnectingTwoBarns {
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numSubTests = Integer.parseInt(br.readLine());
		for(int a = 0; a < numSubTests; a++) {
			String[] temp = br.readLine().split(" ");
			int numFields = Integer.parseInt(temp[0]);
			int numPaths = Integer.parseInt(temp[1]);
			List<Integer>[] fields = new List[numFields];
			for(int i = 0; i < numFields; i++) {
				fields[i] = new ArrayList<Integer>();
			}
			for(int i = 0; i < numPaths; i++) {
				String[] temp2 = br.readLine().split(" ");
				fields[Integer.parseInt(temp2[0]) - 1].add(Integer.parseInt(temp2[1]) - 1);
				fields[Integer.parseInt(temp2[1]) - 1].add(Integer.parseInt(temp2[0]) - 1);
			}
			
			TreeSet<Integer> first = new TreeSet<Integer>();	//farms connected to first farm
			TreeSet<Integer> last = new TreeSet<Integer>();		//farms connected to last farm
			
			ArrayDeque<Integer> q = new ArrayDeque<Integer>();	//can add to front or back
			q.add(0);
			boolean[] visited = new boolean[numFields];
			visited[0] = true;
			//farms connected to first (bfs from first)
			while(q.size() > 0) {
				int field = q.pollFirst();
				first.add(field);
				for(int i = 0; i < fields[field].size(); i++) {
					int nextField = fields[field].get(i);
					if(visited[nextField]) continue;
					q.add(nextField);
					visited[nextField] = true;
				}
			}
			
			if(visited[numFields - 1]) {
				System.out.println(0);
				continue;
			}
			
			//farms connected to last (bfs from last)
			q.add(numFields - 1);
			visited[numFields - 1] = true;
			long onePathAns = Long.MAX_VALUE;
			while(q.size() > 0) {
				int field = q.pollFirst();
				last.add(field);
				//have to do below bc might be null
				if(first.floor(field) != null) onePathAns = (long) Math.min(onePathAns, Math.pow((long)(field - first.floor(field)), 2));
				if(first.ceiling(field) != null) onePathAns = Math.min(onePathAns, (long)Math.pow((first.ceiling(field) - field), 2));
				for(int i = 0; i < fields[field].size(); i++) {
					int nextField = fields[field].get(i);
					if(visited[nextField]) continue;
					q.add(nextField);
					visited[nextField] = true;
				}
			}
			
			long twoPathAns = Long.MAX_VALUE;
			for(int i = 0; i < numFields; i++) {
				if(visited[i]) continue;
				q.add(i);
				visited[i] = true;
				long smallestToFirst = Long.MAX_VALUE/2;	// /2 bc don't want to overflow
				long smallestToLast = Long.MAX_VALUE/2;
				while(q.size() > 0) {
					int field = q.pollFirst();
					
					//first
					if(first.floor(field) != null) smallestToFirst = Math.min(smallestToFirst, (long)Math.pow((field - first.floor(field)), 2));
					if(first.ceiling(field) != null) smallestToFirst = Math.min(smallestToFirst, (long)Math.pow((first.ceiling(field) - field), 2));
					
					//last
					if(last.floor(field) != null) smallestToLast = Math.min(smallestToLast, (long)Math.pow((field - last.floor(field)), 2));
					if(last.ceiling(field) != null) smallestToLast = Math.min(smallestToLast, (long)Math.pow((last.ceiling(field) - field), 2));
					
					for(int j = 0; j < fields[field].size(); j++) {
						int nextField = fields[field].get(j);
						if(visited[nextField]) continue;
						q.add(nextField);
						visited[nextField] = true;
					}
				}
				//System.out.println(i + ", " + smallestToFirst + " " + smallestToLast);
				twoPathAns = Math.min(twoPathAns, smallestToFirst + smallestToLast);
			}
			//System.out.println(onePathAns + " " + twoPathAns);
			System.out.println(Math.min(twoPathAns, onePathAns));
		}
		
		br.close();
	}
}
