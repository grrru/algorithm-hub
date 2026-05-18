import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static List<Integer>[] adj;

	static boolean[] visited;
	static int[] arr;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		adj = new List[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();

			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				adj[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		arr = new int[m + 1];

		for (int i = 1; i <= n; i++) {
			visited = new boolean[m + 1];

			if (dfs(i)) ans++;
		}

		System.out.println(ans);
	}

	static boolean dfs(int cur) {

		for (int want : adj[cur]) {

			if (visited[want]) continue;

			visited[want] = true;

			if (arr[want] == 0 || dfs(arr[want])) {
				arr[want] = cur;

				return true;
			}
		}

		return false;
	}
}
