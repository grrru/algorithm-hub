import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static long n;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Long.parseLong(br.readLine());

		long res = n;

		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				while (n % i == 0) {
					n /= i;
				}

				res -= res / i;
			}
		}

		if (n > 1) {
			res -= res / n;
		}

		System.out.println(res);
	}
}
