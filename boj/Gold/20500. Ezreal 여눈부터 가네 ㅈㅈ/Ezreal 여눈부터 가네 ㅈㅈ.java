import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static final int MOD = 1_000_000_007;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[][] dp = new int[n + 1][n + 1];

		for (int i = 0; i <= n; i++)
			dp[i][0] = dp[i][i] = 1;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j < i; j++) {
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				dp[i][j] %= MOD;
			}
		}

		int res = 0;

		if (n % 3 == 0) {
			for (int i = 0; i < n; i += 3) {
				res += dp[n - 1][n - 1 - i];
				res %= MOD;
			}
		} else if (n % 3 == 1) {
			for (int i = 2; i < n; i += 3) {
				res += dp[n - 1][n - 1 - i];
				res %= MOD;
			}
		} else {
			for (int i = 1; i < n; i += 3) {
				res += dp[n - 1][n - 1 - i];
				res %= MOD;
			}
		}

		System.out.println(res);
	}
}