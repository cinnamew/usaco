import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SocdistI {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new  FileReader (new  File("AAA.in")));
		
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("AAA.out")));
		pw.write("");
		pw.close();
	}

}
