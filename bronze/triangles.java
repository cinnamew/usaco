import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class triangles {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
		int posts = Integer.parseInt(br.readLine());
		int[] xValues = new int[posts];
		int[] yValues = new int[posts];
		for(int i = 0; i < posts; i++) {
			String[] newPost = br.readLine().split(" ");
			xValues[i] = Integer.parseInt(newPost[0]);
			yValues[i] = Integer.parseInt(newPost[1]);
		}
		
		int[] answerXValues = new int[3];
		int[] answerYValues = new int[3];
		int maxSum = 0;
		for(int i = 0; i < posts; i++) {
			answerXValues[0] = xValues[i];
			answerYValues[0] = yValues[i];
			for(int j = i + 1; j < posts; j++) {
				if(xValues[j] == answerXValues[0]) {
					answerXValues[1] = xValues[j];
					answerYValues[1] = yValues[j];
					for(int k = 0; k < posts; k++) {
						if(yValues[k] == answerYValues[1] || yValues[k] == answerYValues[0]) {
							answerXValues[2] = xValues[k];
							answerYValues[2] = yValues[k];
							int base = Math.abs(answerXValues[2] - answerXValues[0]);
							int height = Math.abs(answerYValues[1] - answerYValues[0]);
							if(maxSum < height*base) {
								maxSum = height*base;
							}
						}
					}
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
		pw.write("" + maxSum);
		pw.flush();
		System.out.println(maxSum);
	}
}
