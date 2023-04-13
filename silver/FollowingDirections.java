import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FollowingDirections {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		boolean[][] facingRight = new boolean[N + 1][N + 1];
		int[][] costs = new int[N+1][N+1];
		int[][] numCows = new int[N+1][N+1];
		
		for(int i = 0; i <= N; i++) {
			String[] temp2 = br.readLine().split(" ");
			String[] temp = temp2[0].split("");
			
			if(i == N) {
				for(int j = 0; j < N; j++) {
					costs[i][j] = Integer.parseInt(temp2[j]);
				}
				break;
			}
			
			for(int j = 0; j < N; j++) {
				if(temp[j].equals("R")) facingRight[i][j] = true;
				else facingRight[i][j] = false;
				//numCows[i][j] = 1;
			}
			
			costs[i][N] = Integer.parseInt(temp2[1]);
		}
		
		
		//cow placement
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(facingRight[i][j]) {
					numCows[i][j + 1] += numCows[i][j] + 1;
				}else {
					numCows[i + 1][j] += numCows[i][j] + 1;
				}
			}
		}
		
//		for(int i = 0; i <= N; i++) {
//			for(int j = 0; j <= N; j++) {
//				System.out.print(numCows[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		calculateCost(numCows, costs, N);
		
		
		int numDays = Integer.parseInt(br.readLine());
		for(int i = 0; i < numDays; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			
			int tempRow = row;
			int tempCol = col;
			
			int currCows = numCows[row][col] + 1;
			
			if(facingRight[row][col]) {
				//add
				tempRow++;
				while(tempRow <= N && tempCol <= N) {
					numCows[tempRow][tempCol] += currCows;
					if(facingRight[tempRow][tempCol]) {
						tempCol++;
					}else tempRow++;
				}
				
				//remove
				tempRow = row;
				tempCol = col + 1;
				while(tempRow <= N && tempCol <= N) {
					numCows[tempRow][tempCol] -= currCows;
					if(facingRight[tempRow][tempCol]) {
						tempCol++;
					}else tempRow++;
				}
				
			}else {
				//add
				tempCol++;
				while(tempRow <= N && tempCol <= N) {
					numCows[tempRow][tempCol] += currCows;
					if(facingRight[tempRow][tempCol]) {
						tempCol++;
					}else tempRow++;
				}
				
				//remove
				tempRow = row + 1;
				tempCol = col;
				while(tempRow <= N && tempCol <= N) {
					numCows[tempRow][tempCol] -= currCows;
					if(facingRight[tempRow][tempCol]) {
						tempCol++;
					}else tempRow++;
				}
				
			}
			
			facingRight[row][col] = !facingRight[row][col];
			
			calculateCost(numCows, costs, N);
			
		}
		
		
		
		br.close();
		pw.close();
	}
	
	private static void calculateCost(int[][] numCows, int[][] costs, int N) {
		int cost = 0;
//		int N = numCows.length - 1;
		for(int i = 0; i < N; i++) {
			cost += numCows[i][N]*costs[i][N];
			cost += numCows[N][i]*costs[N][i];
		}
		System.out.println(cost + "");
	}
	
}
