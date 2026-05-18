import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int n;
	static boolean[] isNotPrime;
	static final int MOD = 987654321;
	static long ans = 1;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		if (n == 1) {
			System.out.println(1);
			return;
		}
		
		isNotPrime = new boolean[n + 1];
		isNotPrime[2] = false;
		
		for (int i = 2; i <= n; i++) {
			if (!isNotPrime[i]) {
				for (int j = i * 2; j <= n; j += i) {
					isNotPrime[j] = true;
				}
				
				long k = i;
				while (k <= n) k *= i;
				k /= i;
				
				ans *= k;
				ans %= MOD;
			}
		}
		
		System.out.println(ans);
	}
}