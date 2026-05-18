import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		Deque<int[]> queue = new ArrayDeque<>();
		
		int D = Integer.MAX_VALUE;
		
		for (int i = 0; i < n; i++) {
			int k = Integer.parseInt(st.nextToken());
			while (!queue.isEmpty() && queue.peekLast()[0] >= k) {
				queue.pollLast();
			}
			
			queue.offer(new int[] {k, i});
					
			if (i - l == queue.peek()[1]) {
				queue.poll();
			}
			D = queue.peek()[0];
			sb.append(D).append(' ');
		}
		System.out.println(sb);
	}

}
