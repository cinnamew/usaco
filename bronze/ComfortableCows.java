import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ComfortableCows {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("comfcows.in"));
		int numCows = Integer.parseInt(br.readLine());
		int[][] cowPos = new int[numCows][2];
		int maxX = 0;
		int maxY = 0;
		
		for(int i = 0; i < numCows; i++) {
			String[] temp = br.readLine().split(" ");
			int x = Integer.parseInt(temp[0]);
			int y = Integer.parseInt(temp[1]);
			if(x > maxX) maxX = x;
			if(y > maxY) maxY = y;
			cowPos[i][0] = x;
			cowPos[i][1] = y;
		}
		
		int[][] cows = new int[maxY + 1][maxX + 1];
		
		int comfCows = 0;
		for(int i = 0; i < numCows; i++) {
			int x = cowPos[i][0];
			int y = cowPos[i][1];
			cows[y][x] += 10;
			//top
			if(y > 0) {
				cows[y-1][x] += 1;
				if(cows[y - 1][x] == 14) comfCows--;
				else if(cows[y-1][x] == 13) comfCows++;
			}
			//check right
			if(x < maxX) {
				cows[y][x + 1] += 1;
				if(cows[y][x + 1] == 14) comfCows--;
				else if(cows[y][x + 1] == 13) comfCows++;
			}
			//check bottom
			if(y < maxY) {
				cows[y+1][x] += 1;
				if(cows[y+1][x] == 14) comfCows--;
				else if(cows[y+1][x] == 13) comfCows++;
			}
			//check left
			if(x > 0) {
				cows[y][x - 1] += 1;
				if(cows[y][x - 1] == 14) comfCows--;
				else if(cows[y][x - 1] == 13) comfCows++;
			}
			//int neighboringCows = 0;
			
			
			/*for(int j = 0; j <= i; j++) {
				x = cowPos[j][0];
				y = cowPos[j][1];
				//check top
				if(y > 0) {
					if(cows[y-1][x] == 1) {
						neighboringCows++;
					}
				}
				//check right
				if(x < maxX) {
					if(cows[y][x + 1] == 1) {
						neighboringCows++;
					}
				}
				//check bottom
				if(y < maxY) {
					if(cows[y+1][x] == 1) {
						neighboringCows++;
					}
				}
				//check left
				if(x > 0) {
					if(cows[y][x - 1] == 1) {
						neighboringCows++;
					}
				}
				if(neighboringCows == 3) {
					comfCows++;
				}
				neighboringCows = 0;
			}*/
			System.out.println(comfCows);
		}
		
		
		
	}

}
