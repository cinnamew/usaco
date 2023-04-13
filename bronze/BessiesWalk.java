import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BessiesWalk {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numTestCases = Integer.parseInt(br.readLine());
		for(int e = 0; e < numTestCases; e++) {
			String[] temp = br.readLine().split(" ");
			int n = Integer.parseInt(temp[0]);	//length of single side
			int k = Integer.parseInt(temp[1]);	//max turns
			int ans = 0;
			char[][] field = new char[n][n];
			for(int i = 0; i < n; i++) {
				String[] temp2 = br.readLine().split("");
				for(int j = 0; j < n; j++) {
					field[i][j] = temp2[j].charAt(0);
				}
			}

			//K = 1
			boolean okay = true;
			//down -> right
			for(int i = 0; i < n; i++) {
				if(field[i][0] == 'H') {
					okay = false;
					break;
				}
			}
			for(int i = 0; i < n; i++) {
				if(field[field.length - 1][i] == 'H') {
					okay = false;
					break;
				}
			}
			if(okay) ans++;
			okay = true;
			//right -> down
			for(int i = 0; i < n; i++) {
				if(field[0][i] == 'H') {
					okay = false;
					break;
				}
			}
			for(int i = 0; i < n; i++) {
				if(field[i][field.length - 1] == 'H') {
					okay = false;
					break;
				}
			}
			if(okay) ans++;

			if(k == 1) {
				System.out.println(ans);
				continue;
			}


			//K = 2

			//right down right
			for(int i = 1; i < n - 1; i++) {
				if(field[0][i] == 'H') break;
				okay = true;
				//moving down
				for(int j = 0; j < n; j++) {
					if(field[j][i] == 'H') {
						okay = false;
						break;
					}
				}
				if(!okay) continue;
				//moving right
				for(int j = i; j < n; j++) {
					if(field[n - 1][j] == 'H') {
						okay = false;
						break;
					}
				}
				if(!okay) continue;
				ans++;
			}

			//down right down
			for(int i = 1; i < n - 1; i++) {
				if(field[i][0] == 'H') break;
				okay = true;
				//moving right
				for(int j = 0; j < n; j++) {
					if(field[i][j] == 'H') {
						okay = false;
						break;
					}
				}
				if(!okay) continue;
				//moving down
				for(int j = i; j < n; j++) {
					if(field[j][n - 1] == 'H') {
						okay = false;
						break;
					}
				}
				if(!okay) continue;
				ans++;
			}

			if(k == 2) {
				System.out.println(ans);
				continue;
			}

			//K = 3
			
			
			//(i, j): coordinates for second turning point
			//down right down right
			for(int i = 1; i < n - 1; i++) {
				if(field[i][0] == 'H') {
					break;
				}
				for(int j = 1; j < n - 1; j++) {
					okay = true;

					//moving right
					for(int a = 0; a <= j; a++) {
						if(field[i][a] == 'H') {
							okay = false;
							break;
						}
					}
					if(!okay) continue;
					for(int a = i; a < n; a++) {
						if(field[a][j] == 'H') {
							okay = false;
							break;
						}
					}
					
					if(!okay) continue;
					//moving right
					for(int a = j; a < n; a++) {
						if(field[n - 1][a] == 'H') {
							okay = false;
							break;
						}
					}

					if(okay) ans++;

				}
			}

			//right down right down
			for(int j = 1; j < n - 1; j++) {
				if(field[0][j] == 'H') {
					break;
				}
				for(int i = 1; i < n - 1; i++) {
					okay = true;
					
						//moving down
					for(int a = 0; a <= i; a++) {
						if(field[a][j] == 'H') {
							okay = false;
							break;
						}
					}
					if(!okay) continue;
						//moving right
					for(int a = j; a < n; a++) {
						if(field[i][a] == 'H') {
							okay = false;
							break;
						}
					}
					if(!okay) continue;
						//moving down
					for(int a = i; a < n; a++) {
						if(field[a][n - 1] == 'H') {
							okay = false;
							break;
						}
					}
					if(okay) ans++;
				}
			}

			System.out.println(ans);
		}
		br.close();
	}

}
