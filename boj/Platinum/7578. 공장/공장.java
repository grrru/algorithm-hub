

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] temp;
	static int[] map;
	static int[] tree;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		temp = new int[1000001];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) temp[Integer.parseInt(st.nextToken())] = i;

		map = new int[n + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) map[temp[Integer.parseInt(st.nextToken())]] = i;

		long ans = 0;
		tree = new int[n * 4];

		for (int i = 1; i <= n; i++) {
			updateTree(map[i], 1, n, 1);

			ans += getTree(map[i] + 1, n, 1, n, 1);
		}

		System.out.println(ans);

	}

	static int getTree(int s, int e, int l, int r, int cur) {
		if (s > r || e < l) return 0;
		int res = 0;

		if (s <= l && r <= e) {
			res += tree[cur];
		} else {
			int mid = (l + r) / 2;
			res += getTree(s, e, l, mid, cur * 2);
			res += getTree(s, e, mid + 1, r, cur * 2 + 1);
		}

		return res;
	}

	static void updateTree(int node, int s, int e, int cur) {
		if (s > node || e < node) return;

		tree[cur]++;

		if (s == e) return;

		int mid = (s + e) / 2;
		updateTree(node, s, mid, cur * 2);
		updateTree(node, mid + 1, e, cur * 2 + 1);
	}
}