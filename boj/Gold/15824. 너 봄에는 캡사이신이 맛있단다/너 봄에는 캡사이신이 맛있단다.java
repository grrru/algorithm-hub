import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] arr;
	static final int MOD = 1_000_000_007;
	static long[] multiple;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(arr);

		multiple = new long[n + 1];

		long res = 0;
		for (int i = 1; i <= n; i++) {

			long mul = (getMultiple(i - 1) - getMultiple(n - i) + MOD) % MOD;

			res += mul * arr[i];
			res %= MOD;
		}

		System.out.println(res);
	}

	static long getMultiple(int k) {
		if (multiple[k] != 0) return multiple[k];
		if (k == 0) return multiple[k] = 1;
		if (k == 1) return multiple[k] = 2;

		multiple[k] = 1;
		multiple[k] *= getMultiple(k / 2);
		multiple[k] %= MOD;

		if (k % 2 == 0) {
			multiple[k] *= getMultiple(k / 2);
			multiple[k] %= MOD;
		} else {
			multiple[k] *= getMultiple(k / 2 + 1);
			multiple[k] %= MOD;
		}

		return multiple[k];
	}
}