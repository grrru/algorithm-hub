import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int[][] arr;
	static long[][] upDp;
	static long[][] downDp;
	static final long visit = Long.MIN_VALUE;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n + 2][m + 2];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		if (n == 1 && m == 1) {
			System.out.println(arr[1][1] * 2);
			return;
		}

		upDp = new long[n + 2][m + 2];
		downDp = new long[n + 2][m + 2];

		for (int i = 0; i <= n + 1; i++) {
			Arrays.fill(upDp[i], visit);
			Arrays.fill(downDp[i], visit);
		}

		makeUpDp(1, m);
		makeDownDp(1, 1);

		long result = Long.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				result = Math.max(result, upDp[i][j] + downDp[i][j]);
			}
		}

		// for (int i = n + 1; i >= 0; i--) {
		// for (int j = 0; j <= m + 1; j++) {
		// System.out.print(upDp[i][j] + " ");
		// }
		// System.out.println();
		// }

		// System.out.println();

		// for (int i = n + 1; i >= 0; i--) {
		// for (int j = 0; j <= m + 1; j++) {
		// System.out.print(downDp[i][j] + " ");
		// }
		// System.out.println();
		// }

		System.out.println(result);
	}

	static long makeUpDp(int x, int y) {
		if (upDp[x][y] != visit) {
			return upDp[x][y];
		}

		if (x == n && y == 1) {
			return upDp[x][y] = arr[x][y];
		}

		if (x == n)
			return upDp[x][y] = makeUpDp(x, y - 1) + arr[x][y];
		if (y == 1)
			return upDp[x][y] = makeUpDp(x + 1, y) + arr[x][y];

		upDp[x][y] = Math.max(makeUpDp(x + 1, y), makeUpDp(x, y - 1)) + arr[x][y];

		return upDp[x][y];
	}

	static long makeDownDp(int x, int y) {
		if (downDp[x][y] != visit) {
			return downDp[x][y];
		}

		if (x == n && y == m) {
			return downDp[x][y] = arr[x][y];
		}
		if (x == n)
			return downDp[x][y] = makeDownDp(x, y + 1) + arr[x][y];
		if (y == m)
			return downDp[x][y] = makeDownDp(x + 1, y) + arr[x][y];

		downDp[x][y] = Math.max(makeDownDp(x + 1, y), makeDownDp(x, y + 1)) + arr[x][y];

		return downDp[x][y];
	}
}
