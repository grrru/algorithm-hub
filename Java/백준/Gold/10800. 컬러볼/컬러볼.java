import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static List<Ball>[] arr;
	static int[] prefixSum;
	static int[] colors;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		prefixSum = new int[2001];
		
		arr = new List[2001];
		for (int i = 1; i <= 2000; i++) arr[i] = new ArrayList<>();
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			arr[s].add(new Ball(i, c));
			prefixSum[s] += s;
		}
		
		for (int i = 1; i <= 2000; i++) {
			prefixSum[i] += prefixSum[i - 1];
		}
		
		int[] ans = new int[n + 1];
		colors = new int[n + 1];
		
		for (int s = 1; s <= 2000; s++) {
			for (Ball cur : arr[s]) {
				ans[cur.idx] = prefixSum[s - 1] - colors[cur.c];
			}
			for (Ball cur : arr[s]) {
				colors[cur.c] += s;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) sb.append(ans[i]).append('\n');
		System.out.println(sb);
	}
	
	static class Ball {
		int idx;
		int c;
		
		public Ball(int idx, int c) {
			this.idx = idx;
			this.c = c;
		}
		
	}
}
