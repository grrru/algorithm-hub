import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Stack<int[]> stack = new Stack<>();
		

		
		for (int i = 1; i <= n; i++) {
			int k = Integer.parseInt(st.nextToken());
			
			while (!stack.isEmpty() && stack.peek()[0] < k) {
				stack.pop();

			}
			
			if (stack.isEmpty()) sb.append(0).append(' ');
			else sb.append(stack.peek()[1]).append(' ');
			stack.push(new int[] {k, i});
		}
		
		System.out.println(sb);
	}
}
