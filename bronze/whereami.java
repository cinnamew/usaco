import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class whereami {

	public static void main(String[] args)throws IOException {
		BufferedReader w = new BufferedReader(new FileReader("whereami.in"));
		int mailboxNum = Integer.parseInt(w.readLine());
		String mailboxCodes = w.readLine();
		boolean okay = true;
		for(int i = 1; i < mailboxNum; i++) {
			okay = true;
			for(int j = 0; j < mailboxNum-i; j++) {
				String check = mailboxCodes.substring(j, j+i);
				boolean hi = false;
				for(int m = 1; m <= mailboxNum-j-i; m++) {
					if(check.equals(mailboxCodes.substring((j+m), (j+m+i)))) {
						okay = false;
						hi = true;
						break;
					}
				}
				if(hi == true) {
					break;
				}
			}
			if(okay == true) {
				//System.out.println(i);
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("whereami.out")));
				pw.write(""+i);
				pw.flush();
				break;
			}
			
		}
		
	}

}
