import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static long[] arr;
	static long[] tree;
	static int[] pos;
	static final long MOD = 1_000_000_007L;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int T = M + K;

		int N = n * 4;
		tree = new long[N + 1];

		arr = new long[n + 1];
		pos = new int[n + 1];
		for (int i = 1; i <= n; i++) arr[i] = Long.parseLong(br.readLine());
		makeTree(1, 1, n);

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());

			int cmd = Integer.parseInt(st.nextToken());

			if (cmd == 1) {
				changeTree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			} else {
				sb.append(findTree(1, 1, n, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append('\n');
			}
		}

		System.out.println(sb);
	}

	static long findTree(int cur, int s, int e, int l, int r) {
		if (e < l || r < s) return -1;
		if (l <= s && e <= r) return tree[cur];

		int mid = (s + e) / 2;
		long left = findTree(cur * 2, s, mid, l, r);
		long right = findTree(cur * 2 + 1, mid + 1, e, l, r);

		if (left == -1) return right;
		if (right == -1) return left;

		return left * right % MOD;
	}

	static void changeTree(int idx, int c) {

		int p = pos[idx] / 2;
		int ch1;
		int ch2;

		tree[pos[idx]] = c;
		arr[idx] = c;

		while (p != 0) {
			ch1 = p * 2;
			ch2 = p * 2 + 1;

			tree[p] = tree[ch1] * tree[ch2] % MOD;
			p /= 2;
		}
	}

	static long makeTree(int cur, int s, int e) {
		if (s == e) {
			tree[cur] = arr[s];
			pos[s] = cur;
			return tree[cur];
		}

		int mid = (s + e) / 2;
		long l = makeTree(cur * 2, s, mid);
		long r = makeTree(cur * 2 + 1, mid + 1, e);
		tree[cur] = l * r % MOD;
		return tree[cur];
	}
}
