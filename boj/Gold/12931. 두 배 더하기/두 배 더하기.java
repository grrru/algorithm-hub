import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] arr;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());

		int sum = 1;
		int cnt = 0;
		
		while (sum != 0) {
			sum = 0;
			boolean flag = false;
			for (int i = 1; i <= n; i++) {
				if (arr[i] % 2 == 0) {
					sum += arr[i];
					continue;
				}

				sum = 1;
				arr[i]--;
				cnt++;
				flag = true;
				break;
			}

			if (flag) continue;

			if (sum == 0) break;

			for (int i = 1; i <= n; i++) arr[i] /= 2;
			cnt++;
		}

		System.out.println(cnt);
	}
}