import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int w;
	static int h;
	static int[][][][] dp;
	static final int MOD = 100000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		dp = new int[2][2][h + 1][w + 1];
		
		for (int i = 1; i <= h; i++) dp[0][0][i][1] = 1;
		for (int i = 1; i <= w; i++) dp[1][0][1][i] = 1;
		
		for (int i = 2; i <= h; i++) {
			for (int j = 2; j <= w; j++) {
				dp[0][0][i][j] += dp[0][0][i - 1][j] + dp[0][1][i - 1][j];
				dp[0][1][i][j] += dp[1][0][i - 1][j];
				
				dp[1][0][i][j] += dp[1][0][i][j - 1] + dp[1][1][i][j - 1];
				dp[1][1][i][j] += dp[0][0][i][j - 1];
				
				dp[0][0][i][j] %= MOD;
				dp[0][1][i][j] %= MOD;
				dp[1][0][i][j] %= MOD;
				dp[1][1][i][j] %= MOD;
			}
		}
		System.out.println((dp[0][0][h][w] + dp[0][1][h][w] + dp[1][0][h][w] + dp[1][1][h][w]) % MOD);
	}
}
