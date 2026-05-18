import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static List<Integer>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] time = new int[n + 1];
		int[] deg = new int[n + 1];
		int[] arr = new int[n + 1];
		
		graph = new List[n + 1];
		for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			
			time[i] = Integer.parseInt(st.nextToken());
			int k;
			
			while ((k = Integer.parseInt(st.nextToken())) != -1) {
				deg[i]++;
				graph[k].add(i);
			}
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		for (int i = 1; i <= n; i++) {
			if (deg[i] != 0) continue;
			
			queue.offer(i);

			for (int next : graph[i]) {
				arr[next] = Math.max(arr[next], time[i]);
			}
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			arr[cur] += time[cur];
			
			for (int next : graph[cur]) {
				
				arr[next] = Math.max(arr[next], arr[cur]);
				
				if (--deg[next] != 0) continue;
				
				queue.offer(next);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) sb.append(arr[i]).append('\n');
		System.out.println(sb);
	}
}
