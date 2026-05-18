import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, (o1, o2) -> {
			return o1[0] - o2[0];
		});
		
		int cnt = 1;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			if (pq.isEmpty()) {
				pq.offer(arr[i][1]);
				continue;
			}
			
			int end = pq.poll();
			pq.offer(arr[i][1]);
			
			if (end <= arr[i][0]) continue;
			
			pq.offer(end);
			cnt++;
		}
		
		System.out.println(cnt);
	}
}
