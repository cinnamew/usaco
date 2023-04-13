import java.util.*;
import java.io.*;

public class Generator {

    public static void main(String[] args) throws IOException {
        System.out.println(generate(5, 3));
    }
    
    public static String generate(int n, int k) {
        String ret = "";
        ret+= n + " " + k + "\n";
        
        char[][] arr = new char[n][n];
        Random rand = new Random();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int x = rand.nextInt(n);
                if(x==2 && (i!=0 && j!=0) && (i!=n-1 && j!=n-1)) {
                    arr[i][j] = 'H';
                }else {
                    arr[i][j] = '.';
                }
            }
        }
        for(int i=0; i<n; i++) {
            String s = "";
            for(int j=0; j<n; j++) {
                s+=arr[i][j];
            }
            ret+=s+"\n";
        }
        return ret;
    }

}