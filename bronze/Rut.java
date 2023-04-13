import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Rut {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("rut.in"));
		int cowNum = Integer.parseInt(br.readLine());
		
		ArrayList<StuckCow> northCows = new ArrayList<StuckCow>();
		ArrayList<StuckCow> eastCows = new ArrayList<StuckCow>();
		
		for(int i = 0; i < cowNum; i++) {
			String[] temp = br.readLine().split(" ");
			boolean goesNorth = false;
			if(temp[0].equals("N")) {
				goesNorth = true;
				northCows.add(new StuckCow(goesNorth, Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), i));
			}else {
				eastCows.add(new StuckCow(goesNorth, Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), i));
			}
		}
		
		Collections.sort(northCows);
		Collections.sort(eastCows);
		
		int[] cowDists = new int[cowNum];
		
		for(int i = 0; i < eastCows.size(); i++) {
			StuckCow eastCow = eastCows.get(i);
			for(int j = 0; j < northCows.size(); j++) {
				StuckCow northCow = northCows.get(j);
				if(northCow.getxCoor() > eastCow.getxCoor() && eastCow.getyCoor() > northCow.getyCoor()) {
					int northDist = eastCow.getyCoor() - northCow.getyCoor();
					int eastDist = northCow.getxCoor() - eastCow.getxCoor();
					
					if(northDist > eastDist) {
						northCow.setStopped(true);
						northCows.remove(j);
						j--;
						cowDists[northCow.getOrder()] = northDist;
					}else if(eastDist > northDist){
						eastCow.setStopped(true);
						eastCows.remove(i);
						j = northCows.size();
						i--;
						cowDists[eastCow.getOrder()] = eastDist;
					}
				}
			}
		}
		
		
		for(int i = 0; i < cowNum; i++) {
			if(cowDists[i] == 0) {
				System.out.println("Infinity");
			}else {
				System.out.println(cowDists[i]);
			}
		}
		
	}

}

class StuckCow implements Comparable {
	boolean goesNorth;
	int xCoor;
	int yCoor;
	boolean isStopped = false;
	int order;

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isStopped() {
		return isStopped;
	}

	public void setStopped(boolean isStopped) {
		this.isStopped = isStopped;
	}

	public StuckCow(boolean goesNorth, int xCoor, int yCoor, int order) {
		this.goesNorth = goesNorth;
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.order = order;
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

	@Override
	public int compareTo(Object otherCowObject) {
		StuckCow othercow = (StuckCow)otherCowObject;
		if(othercow.isGoesNorth()) {
			if(othercow.getxCoor() > xCoor) {
				return -1;
			}
			return 1;
		}
		
		if(othercow.getyCoor() > yCoor) {
			return -1;
		}
		return 1;
	}
	
	
}