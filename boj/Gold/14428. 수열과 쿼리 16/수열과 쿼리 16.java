import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] arr;
	static int[] tree;
	static int N;
	static int[] pos;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());

		N = n * 4;
		tree = new int[N + 1];
		pos = new int[n + 1];

		makeTree(1, 1, n);

		int Q = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());

			if (cmd == 1) {
				changeTree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			} else {
				sb.append(findTree(1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 1, n)).append('\n');
			}
		}
		System.out.println(sb);
	}
	static int makeTree(int cur, int s, int e) {

		if (s == e) {
			tree[cur] = s;
			pos[s] = cur;
			return tree[cur];
		}

		int mid = (s + e) / 2;

		int lIdx = makeTree(cur * 2, s, mid);
		int rIdx = makeTree(cur * 2 + 1, mid + 1, e);

		if (arr[lIdx] <= arr[rIdx]) tree[cur] = lIdx;
		else tree[cur] = rIdx;

		return tree[cur];
	}

	static void changeTree(int i, int v) {
		int cur = pos[i];
		arr[i] = v;
		while (cur != 0) {
			int p = cur / 2;
			int c = arr[tree[p * 2]] <= arr[tree[p * 2 + 1]] ? tree[p * 2] : tree[p * 2 + 1];

			tree[p] = c;
			cur /= 2;
		}
	}
	
	static int findTree(int cur, int l, int r, int s, int e) {
		if (r < s || e < l) return -1;
		if (l <= s && e <= r) return tree[cur];

		int mid = (s + e) / 2;
		int lIdx = findTree(cur * 2, l, r, s, mid);
		int rIdx = findTree(cur * 2 + 1, l, r, mid + 1, e);

		if (lIdx == -1) return rIdx;
		if (rIdx == -1) return lIdx;
		
		if (arr[lIdx] <= arr[rIdx]) return lIdx;
		else return rIdx;
	}
}
