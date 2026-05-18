import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	
	static int n;
	static int[][] arr;
	static int[] lis;
	static int[] dp;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1][2];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr, (o1, o2) -> {
			return Integer.compare(o1[0], o2[0]);
		});
		
		lis = new int[n + 1];
		dp = new int[n + 1];
		
		int idx = 0;
		
		for (int i = 1; i <= n; i++) {
			if (lis[idx] < arr[i][1]) {
				lis[++idx] = arr[i][1];
				dp[i] = idx;
				continue;
			}
			
			int start = 1;
			int end = idx;
			int mid = 0;
			
			while (start <= end) {
				mid = (start + end) / 2;
				if (lis[mid] > arr[i][1]) {
					end = mid - 1;
				}
				else start = mid + 1;
			}
			
			lis[end + 1] = arr[i][1];
			dp[i] = end + 1;
		}
		
		sb.append(n - idx).append('\n');
		visit = new boolean[n + 1];
		int k = idx;
		int t = n;
		
		while (k > 0) {
			if (dp[t] == k) {
				k--;
				visit[t] = true;
			}
			t--;
		}
		
		for (int i = 1; i <= n; i++) {
			if (!visit[i]) sb.append(arr[i][0]).append('\n');
		}
		System.out.println(sb);
	}
}
