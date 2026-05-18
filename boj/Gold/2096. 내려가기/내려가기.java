import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int n;
	static int[][][] dp;
    
	public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	
    	dp = new int[n + 1][3][2];
    	
    	for (int i = 1; i <= n; i++) {
    		st = new StringTokenizer(br.readLine());
    		
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());
    		
    		dp[i][0][0] = Math.min(dp[i - 1][0][0], dp[i - 1][1][0]) + a;
    		dp[i][1][0] = Math.min(Math.min(dp[i - 1][0][0], dp[i - 1][1][0]), dp[i - 1][2][0]) + b;
    		dp[i][2][0] = Math.min(dp[i - 1][1][0], dp[i - 1][2][0]) + c;
    		
    		dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][1][1]) + a;
    		dp[i][1][1] = Math.max(Math.max(dp[i - 1][0][1], dp[i - 1][1][1]), dp[i - 1][2][1]) + b;
    		dp[i][2][1] = Math.max(dp[i - 1][1][1], dp[i - 1][2][1]) + c;
    	}
    	
    	System.out.println(Math.max(Math.max(dp[n][0][1], dp[n][1][1]), dp[n][2][1]) + " " +
    			Math.min(Math.min(dp[n][0][0], dp[n][1][0]), dp[n][2][0]));
    }
}