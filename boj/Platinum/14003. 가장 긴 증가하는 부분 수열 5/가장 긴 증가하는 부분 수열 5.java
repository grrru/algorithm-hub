import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] lis;
	static int[] dp;
	static int[] arr;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		lis = new int[n + 1];
		dp = new int[n + 1];
		lis[0] = Integer.MIN_VALUE;
		arr = new int[n + 1];
		max = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int k = 0;
		for (int i = 1; i <= n; i++) {
			int cur = arr[i];
			
			if (cur > lis[k]) {
				lis[++k] = cur;
				dp[i] = k;
			}
			else {
				int idx = Arrays.binarySearch(lis, 0, k, cur);
//				System.out.println(idx);
				if (idx < 0) {
					idx = -(idx + 1);
				}
				
				lis[idx] = cur;
				dp[i] = idx;
			}
			if (dp[i] > max) max = dp[i];
//			System.out.println(Arrays.toString(lis));
//			System.out.println(Arrays.toString(dp));
//			System.out.println();
		}
		sb.append(max).append('\n');
		
		int[] ans = new int[max + 1];
		int size = max;
		int i = n;
		while (size > 0) {
			for (; i > 0; i--) {
				if (dp[i] == size) {
					ans[size--] = arr[i];
					break;
				}
			}
		}
		
		for (int t = 1; t <= max; t++) sb.append(ans[t]).append(" ");
		System.out.println(sb);
	}
}