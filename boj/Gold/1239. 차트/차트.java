import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] arr;
	static int[] curr;
	static int[] prefix;
	static int ans;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());

		curr = new int[n + 1];
		prefix = new int[n + 1];

		curr[1] = arr[1];
		prefix[1] = arr[1];
		boolean[] visit = new boolean[n + 1];
		visit[1] = true;

		perm(2, visit);

		System.out.println(ans);
	}

	static void perm(int cur, boolean[] visit) {
		if (cur == n + 1) {
			for (int i = 2; i <= n; i++) prefix[i] = prefix[i - 1] + curr[i];
			
			int res = 0;
			for (int i = 1; i <= n; i++) {
				for (int j = i + 1; j <= n; j++) {
					if (prefix[j] - prefix[i] == 50) res++;
				}
			}

			ans = Math.max(ans, res);

			return;
		}

		for (int i = 1; i <= n; i++) {
			if (visit[i]) continue;
			visit[i] = true;
			curr[cur] = arr[i];
			perm(cur + 1, visit);
			visit[i] = false;
		}
	}
}
