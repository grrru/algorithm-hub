import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int n;
	static int[] arr;
	static boolean[] visit;
	static boolean[] cycle;
	static boolean[] res;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		visit = new boolean[n + 1];
		res = new boolean[n + 1];
		
		for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= n; i++) {
			cycle = new boolean[n + 1];
			dfs(i);
		}
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append('\n');
		
		for (int i = 1; i <= n; i++) {
			if (res[i]) sb.append(i).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int cur) {
		if (cycle[cur]) {
			int target = cur;
			do {
				cnt++;
				res[cur] = true;
				cur = arr[cur];
				
			} while (cur != target);
			return;
		}
		if (visit[cur]) return;
		
		visit[cur] = true;
		cycle[cur] = true;
		
		dfs(arr[cur]);
	}
}