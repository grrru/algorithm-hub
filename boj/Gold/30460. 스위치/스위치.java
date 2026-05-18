import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] arr;
	static int[] dp;
	static final int MIN = Integer.MIN_VALUE;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		dp = new int[n + 1];
		Arrays.fill(dp, MIN);
		System.out.println(makeDp(1));
	}

	static int makeDp(int cur) {
		if (cur > n)
			return 0;
		if (dp[cur] != MIN)
			return dp[cur];

		int res = arr[cur] * 2;
		if (cur + 1 <= n)
			res += arr[cur + 1] * 2;
		if (cur + 2 <= n)
			res += arr[cur + 2] * 2;

		res += makeDp(cur + 3);

		return dp[cur] = Math.max(res, arr[cur] + makeDp(cur + 1));
	}
}
