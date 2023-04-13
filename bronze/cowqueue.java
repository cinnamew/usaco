import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class cowqueue {

	public static void main(String[] args)throws IOException {
		
		BufferedReader cq = new BufferedReader(new FileReader("cowqueue.in"));
		int cowNum = Integer.parseInt(cq.readLine());
		
		Cows cows[] = new Cows[cowNum];
		
		for(int i = 0; i < cowNum; i++) {
			String[] temp = (cq.readLine()).split(" ");
			cows[i] = new Cows(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
		}
		
		Arrays.sort(cows);
		
		for(int i = 0; i < cowNum-1; i++) {
			if(cows[i].getTotal() > cows[i+1].getTimeArrived()) {
				cows[i+1].setTimeArrived(cows[i].getTotal());
			}
		}
		
		//System.out.println(cows[cowNum-1].getTotal());
		cq.close();
		
		PrintWriter cqpw = new PrintWriter(new BufferedWriter(new FileWriter("cowqueue.out")));
		cqpw.write("" + cows[cowNum-1].getTotal());
		cqpw.flush();
		cqpw.close();
		
	}

}

class Cows implements Comparable{
	int timeArrived;
	int questionTime;
	
	public Cows(int timeArrived, int questionTime) {
		this.timeArrived = timeArrived;
		this.questionTime = questionTime;
	}
	
	public int getTimeArrived() {
		return timeArrived;
	}
	
	public int getQuestionTime() {
		return questionTime;
	}
	
	public int getTotal() {
		return timeArrived + questionTime;
	}
	
	public void setTimeArrived(int timeArrived) {
		this.timeArrived = timeArrived;
	}
	
	@Override
	public int compareTo(Object otherCow) {
		if(this.timeArrived == ((Cows)otherCow).getTimeArrived()) {
			return 0;
		}else if(this.timeArrived > ((Cows)otherCow).getTimeArrived()) {
			return 1;
		}else {
			return -1;
		}
	}
}