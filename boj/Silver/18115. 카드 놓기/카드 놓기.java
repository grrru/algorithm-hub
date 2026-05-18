import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		Deque<Integer> dq = new ArrayDeque<>();
		
		for (int i = n - 1; i >= 0; i--) {
			switch(arr[i]) {
			case 1:
				dq.offerFirst(n - i);
				break;
			case 2:
				int k = dq.poll();
				dq.offerFirst(n - i);
				dq.offerFirst(k);
				break;
			case 3:
				dq.offer(n - i);
				break;
			}
		}
		
		while (!dq.isEmpty()) sb.append(dq.poll()).append(" ");
		System.out.println(sb);
	}
}
