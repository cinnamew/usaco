import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class Rut_notworking {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("rut.in"));
		int cowNum = Integer.parseInt(br.readLine());
		RutCow[] cows = new RutCow[cowNum];
		
		for(int i = 0; i < cowNum; i++) {
			String[] temp = br.readLine().split(" ");
			boolean goesNorth = false;
			if(temp[0].equals("N")) {
				goesNorth = true;
			}
			cows[i] = new RutCow(goesNorth, Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
		}
		
		int[] ansCows = new int[cowNum];
		
		HashSet<String> eatenCells = new HashSet<>();
		
		int runTill = 6;
		boolean areInfinites = false;
		HashSet<Integer> infinites = new HashSet<>();
		//ArrayList<Integer> infinites = new ArrayList<Integer>();
		
		for(int i = 0; i < runTill; i++) {
			for(int j = 0; j < cowNum; j++) {
				RutCow CurrCow = cows[j];
				if(!CurrCow.isStopped) {
					String currCowCell = CurrCow.getxCoor() + ", " + CurrCow.getyCoor();
					eatenCells.add(currCowCell);
					if(CurrCow.isGoesNorth()) {
						CurrCow.setyCoor(CurrCow.getyCoor() + 1);
					}else {
						CurrCow.setxCoor(CurrCow.getxCoor() + 1);
					}
					ansCows[j]++;
					
					String currCowCoor = CurrCow.getxCoor() + ", " + CurrCow.getyCoor();
					if(eatenCells.contains(currCowCoor)) {
						CurrCow.setStopped(true);
						for(int k = 0; k < j; k++) {
							if((cows[k].getxCoor() + ", " + cows[k].getyCoor()).equals(currCowCoor)) {
								CurrCow.setStopped(false);
							}
						}
					}else {
						eatenCells.add(currCowCoor);
					}
					if(i == runTill - 1) {
						for(int k = 0; k < cowNum; k++) {
							RutCow checkCow = cows[k];
							if(k != j) {
								if(!checkCow.isStopped) {
									if(CurrCow.isGoesNorth()) {
										if(checkCow.getyCoor() > CurrCow.getyCoor()) {
											/*runTill++;
											break;*/
											ansCows[j] += checkCow.getyCoor() - CurrCow.getyCoor();
										}
									}else {
										if(checkCow.getxCoor() > CurrCow.getxCoor()) {
											/*runTill++;
											break;*/
											ansCows[j] += checkCow.getxCoor() - CurrCow.getxCoor();
										}
									}
								}
								if(k == cowNum - 1) {
									areInfinites = true;
									infinites.add(j);
								}
							}else if(k == cowNum - 1) {
								areInfinites = true;
								infinites.add(j);
							}
						}
					}
				}
			}
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < cowNum; i++) {
			if(!areInfinites) {
				pw.write(ansCows[i] + "\n");
			}else {
				if(infinites.contains(i)) {
					pw.write("Infinity\n");
				}else {
					pw.write(ansCows[i] + "\n");
				}
			}
		}
		pw.flush();
	}

}

class RutCow {
	boolean goesNorth;
	int xCoor;
	int yCoor;
	boolean isStopped = false;
	
	public boolean isStopped() {
		return isStopped;
	}

	public void setStopped(boolean isStopped) {
		this.isStopped = isStopped;
	}

	public RutCow(boolean goesNorth, int xCoor, int yCoor) {
		this.goesNorth = goesNorth;
		this.xCoor = xCoor;
		this.yCoor = yCoor;
	}
	
	public boolean isGoesNorth() {
		return goesNorth;
	}
	public void setGoesNorth(boolean goesNorth) {
		this.goesNorth = goesNorth;
	}
	public int getxCoor() {
		return xCoor;
	}
	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}
	public int getyCoor() {
		return yCoor;
	}
	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
}