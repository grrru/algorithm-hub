import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[][] arr;
	static int d;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new int[n][2];
		PriorityQueue<int[]> pq = new PriorityQueue<>(
				(o1, o2) -> {
					return o2[1] - o1[1];
				});
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i][0] = Math.min(x, y);
			arr[i][1] = Math.max(x, y);
		}

		Arrays.sort(arr, (o1, o2) -> {
			return o2[0] - o1[0];
		});

		d = Integer.parseInt(br.readLine());
		int cnt = 0;

		for (int i = 0; i < n; i++) {
			pq.offer(arr[i]);
			while (!pq.isEmpty()) {
				if (pq.peek()[1] > arr[i][0] + d)
					pq.poll();
				else
					break;
			}
			cnt = Math.max(cnt, pq.size());
		}

		System.out.println(cnt);
	}
}
