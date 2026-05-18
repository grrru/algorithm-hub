import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		if (n <= 6) {
			System.out.println(n);
			return;
		}
		
		long[] dp = new long[n + 1];
		for (int i = 1; i <= 6; i++) dp[i] = i;
		
		for (int i = 7; i <= n; i++) {
			dp[i] = Math.max(dp[i], dp[i - 1] + 1);
			dp[i] = Math.max(dp[i], dp[i - 3] * 2);
			dp[i] = Math.max(dp[i], dp[i - 4] * 3);
			dp[i] = Math.max(dp[i], dp[i - 5] * 4);
		}
		
		System.out.println(dp[n]);
		
	}
}
