import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	static int n;
	static Stack<Point> stack;
	static long ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		stack = new Stack<>();
		ans = 0;
		
		stack.add(new Point(Integer.parseInt(br.readLine()), 1));
		
		for (int i = 1; i < n; i++) {
			int cur = Integer.parseInt(br.readLine());
			while (!stack.isEmpty() && stack.peek().x < cur) {
				ans += stack.pop().y;
			}

			int cnt = 1;
			while (!stack.isEmpty() && stack.peek().x == cur) {
				ans += stack.peek().y;
				cnt += stack.pop().y;
			}
			
			if (!stack.isEmpty()) ans++;
			stack.add(new Point(cur, cnt));
		}
		System.out.println(ans);
		
	}
}
