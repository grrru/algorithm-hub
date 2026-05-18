import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int k;
	static int[] visit;
	static Set<Integer> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		visit = new int[k + 1];
		
		while (n-- > 0) set.add(Integer.parseInt(br.readLine()));
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(0);
		
		int res = -1;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int t : set) {
				if (cur + t > k) continue;
				if (visit[cur + t] != 0) continue;
				
				visit[cur + t] = visit[cur] + 1;
				
				if (cur + t == k) {
					res = visit[cur + t];
					break;
				}
				
				queue.offer(cur + t);
			}
		}
		
		System.out.println(res);
	}
}
