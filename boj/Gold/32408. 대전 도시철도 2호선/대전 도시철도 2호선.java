import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] visit;
	static int[] back;
	static List<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		graph = new List[n + 1];
		for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
		
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[u].add(v);
			graph[v].add(u);
		}
		
		
		back = new int[n + 1];
		visit = new int[n + 1];
		makeTree(1);
		
		boolean[] line1 = new boolean[n + 1];
		
		int k = n;
		while (k != 0) {
			line1[k] = true;
			k = back[k];
		}
		
		List<Integer> list = new ArrayList<>();
		
		k = n;
		long total = 0;
		
		while (k != 0) {
			for (int next : graph[k]) {
				if (line1[next]) continue;
				list.add(visit[next]);
				total += visit[next];
			}
			k = back[k];
		}
		
		long res = 0;
		for (int t : list) res += t * (total - t);
		
		System.out.println(res / 2);
	}
	
	static int makeTree(int cur) {
		if (visit[cur] != 0) return visit[cur];
		
		visit[cur] = 1;
		
		for (int next : graph[cur]) {
			if (visit[next] != 0) continue;
			
			back[next] = cur;
			visit[cur] += makeTree(next);
		}
		
		return visit[cur];
	}
}