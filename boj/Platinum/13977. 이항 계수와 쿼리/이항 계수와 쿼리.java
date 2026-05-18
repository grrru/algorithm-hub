import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static long[] fac;
	static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		fac = new long[4000001];
		fac[0] = 1;
		for (int i = 1; i <= 4000000; i++) fac[i] = (fac[i - 1] * i) % MOD;
		
		m = Integer.parseInt(br.readLine());
		
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			long up = fac[n];
			long down = (fac[k] * fac[n - k]) % MOD;
			
			long ans = (up * power(down, MOD - 2)) % MOD;
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}
	static long power(long a, long p) {
		if (p == 0) return 1;
		if (p == 1) return a;
		
		long k = power(a, p / 2) % MOD;
		if (p % 2 == 0) return k * k % MOD;
		else return (k * k % MOD) * a % MOD;
	}
}
