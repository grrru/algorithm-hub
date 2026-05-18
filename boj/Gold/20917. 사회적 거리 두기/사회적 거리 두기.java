import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int sc;
	static int[] arr;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			sc = Integer.parseInt(st.nextToken());
			arr = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(arr);

			int s = 1;
			int e = arr[n - 1] - arr[0];
			int d = e;
			while (s <= e) {
				int mid = (s + e) / 2;

				int cnt = 1;
				int cur = arr[0];
				boolean flag = false;
				for (int i = 1; i < n; i++) {
					if (arr[i] - cur >= mid) {
						if (++cnt == sc) {
							d = mid;
							s = mid + 1;
							flag = true;
							break;
						}
						cur = arr[i];
					}
				}

				if (!flag)
					e = mid - 1;
			}

			sb.append(d).append('\n');
		}
		System.out.println(sb);
	}
}
