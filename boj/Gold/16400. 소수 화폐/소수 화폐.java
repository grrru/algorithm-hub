import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int n;
	static boolean[] prime;
	static int[] dp;
	static final int MOD = 123456789;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		makePrime();
		
		dp = new int[n + 1];
		
		dp[0] = 1;
		
		for (int i = 1; i <= n; i++) {
			if (prime[i]) continue;
			
			for (int j = i; j <= n; j++) {
				dp[j] += dp[j - i];
				dp[j] %= MOD;
			}
		}
		
		System.out.println(dp[n]);
	}
	
	static private void makePrime() {
		prime = new boolean[n + 1];
		
		prime[1] = true;
		
		for (int i = 2; i <= n; i++) {
			if (prime[i]) continue;
			
			for (int j = i * 2; j <= n; j += i) prime[j] = true;
		}
	}
	
}