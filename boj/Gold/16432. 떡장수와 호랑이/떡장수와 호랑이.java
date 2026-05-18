import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] dp;
	static int[][] save;

    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st;

         n = Integer.parseInt(br.readLine());
         
         dp = new int[n + 1][10];
         save = new int[n + 1][10];
         
         for (int i = 1; i <= n; i++) {
        	 st = new StringTokenizer(br.readLine());
        	 
        	 int t = Integer.parseInt(st.nextToken());
        	 
        	 while (t-- > 0) {
        		 int d = Integer.parseInt(st.nextToken());
        		 
        		 for (int j = 1; j <= 9; j++) {
        			 if (j == d) continue;
        			 
        			 if (dp[i][d] < dp[i - 1][j] + 1) {
        				 dp[i][d] = dp[i - 1][j] + 1;
        				 save[i][d] = j;
        			 }
        		 }
        	 }
         }
         
         int cur = 0;
         for (int i = 1; i <= 9; i++) {
        	 if (dp[n][i] == n) {
        		 cur = i;
        		 break;
        	 }
         }
         
         if (cur == 0) {
        	 System.out.println(-1);
         } else {
        	 StringBuilder sb = new StringBuilder();
        	 
        	 for (int i = n; i >= 1; i--) {
        		 sb.append(cur).append('\n');
        		 cur = save[i][cur];
        	 }

        	 System.out.println(sb.reverse());
         }
    }
}