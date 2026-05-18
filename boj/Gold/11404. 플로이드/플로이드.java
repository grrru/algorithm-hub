import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static long[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new long[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(arr[i], Integer.MAX_VALUE);
		}
		
		m = Integer.parseInt(br.readLine());
		
		int start, end, weight;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			
			arr[start][end] = Math.min(arr[start][end], weight);
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i == j) continue;
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (arr[i][j] == Integer.MAX_VALUE) arr[i][j] = 0;
				sb.append(arr[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}