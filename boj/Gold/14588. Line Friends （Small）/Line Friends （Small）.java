import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] graph;
	static int[][] arr;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		graph = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) Arrays.fill(graph[i], 100_000);
		arr = new int[n + 1][2];
				
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			
			for (int j = 1; j < i; j++) check(i, j);
		}
		
		makeGraph();
		
		int q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			sb.append(graph[x][y] == 100_000 ? -1 : graph[x][y]).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void makeGraph() {
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i == j || i == k || j == k) continue;
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
	}
	
	static void check(int i, int j) {
	    if (arr[i][0] <= arr[j][1] && arr[j][0] <= arr[i][1]) {
	        graph[i][j] = graph[j][i] = 1;
	    }
	}
}