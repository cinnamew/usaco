import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class StuckInARut {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numCows = Integer.parseInt(br.readLine());
		ArrayList<GrassCow> north = new ArrayList<>();
		ArrayList<GrassCow> east = new ArrayList<>();
		ArrayList<GrassCow> origCows = new ArrayList<>();
		
		for(int i = 0; i < numCows; i++) {
			String[] temp = br.readLine().split(" ");
			int x = Integer.parseInt(temp[1]);
			int y = Integer.parseInt(temp[2]);
			GrassCow c = new GrassCow(x, y);
			if(temp[0].equals("E")) {
				east.add(c);
			}else {
				north.add(c);
			}
			origCows.add(c);
		}
		
		Collections.sort(north, (o1, o2) -> ((GrassCow)o1).x-((GrassCow)o2).x);
		Collections.sort(east, (o1, o2) -> ((GrassCow)o1).y-((GrassCow)o2).y);

		//System.out.println(north);
		//System.out.println(east);
		
		for(int i = 0; i < east.size(); i++) {
			GrassCow currEast = east.get(i);
			for(int j = 0; j < north.size(); j++) {
				if(currEast.getStopped() != -1) continue;
				GrassCow currNorth = north.get(j);
				if(currNorth.getStopped() != -1) continue;
				//System.out.println("curr north: " + currNorth);
				//System.out.println("curr east: " + currEast);
				if(currNorth.getX() < currEast.getX()) continue;
				if(currEast.getY() < currNorth.getY()) continue;
				int xDist = currNorth.getX() - currEast.getX();
				int yDist = currEast.getY() - currNorth.getY();
				if(xDist > yDist) {
					currEast.setStopped(xDist);
					break;
				}else if(yDist > xDist) {
					currNorth.setStopped(yDist);
				}
				//System.out.println("curr north: " + currNorth);
				//System.out.println("curr east: " + currEast);
			}
		}
		
		for(int i = 0; i < origCows.size(); i++) {
			int dist = origCows.get(i).getStopped();
			if(dist == -1) System.out.println("Infinity");
			else System.out.println(dist);
		}
		
	}

}

class GrassCow {
	int stopped = -1;
	int x;
	int y;
	
	
	public int getStopped() {
		return stopped;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setStopped(int stopped) {
		this.stopped = stopped;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public GrassCow(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return x + ", " + y + "; " + stopped;
	}
}
