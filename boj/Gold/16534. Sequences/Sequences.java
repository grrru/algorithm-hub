import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int k;
	static long res;
	static long[][] dp;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		dp = new long[n + 1][m + 1];
		dp[0][0] = 1L;

		for (int i = 1; i <= m; i++) {
			for (int j = 0; j < i; j++) {
				for (int t = 0; t <= n; t++) {
					for (int d = t + 1; d <= t + k; d++) {
						if (d > n) break;
						dp[d][i] += dp[t][j];
					}
				}
			}
		}

		for (int i = 1; i <= m; i++) res += dp[n][i];

		System.out.println(res);
	}
}
