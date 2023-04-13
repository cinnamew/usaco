import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Acowdemia3 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int rows = Integer.parseInt(temp[0]);
		int columns = Integer.parseInt(temp[1]);
		
		String[][] pasture = new String[rows][columns];
		for(int i = 0; i < rows; i++) {
			pasture[i] = br.readLine().split(" ");
		}

	}

}
