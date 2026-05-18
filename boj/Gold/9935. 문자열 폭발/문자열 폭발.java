import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	
	static String str;
	static String exp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str = br.readLine();
		exp = br.readLine();
		int n = exp.length();
		
		Deque<Character> dq = new ArrayDeque<>();
		Deque<Integer> stack = new ArrayDeque<>();
		
		
		int idx = 0;
		
		for (int i = 0; i < str.length(); i++) {
//			System.out.println("dq : " + dq);
//			System.out.println("stack : " + stack);
//			System.out.println("idx : " + idx);
			
			if (idx == n) {
				for (int t = 0; t < n; t++) {
					dq.pollLast();
				}
				
				idx = stack.pollLast();
			}
			
			
			
			if (str.charAt(i) == exp.charAt(0)) {
				stack.offer(idx);
				idx = 1;
			} else if (str.charAt(i) == exp.charAt(idx)) idx++;
			else {
				stack = new ArrayDeque<>();
				stack.add(0);
				idx = 0;
			}
			
			dq.offer(str.charAt(i));
		}

		if (idx == n) {
			for (int t = 0; t < n; t++) {
				dq.pollLast();
			}
		}
		
		if (dq.isEmpty()) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			while (!dq.isEmpty()) {
				sb.append(dq.pollFirst());
			}
			
			System.out.println(sb);
		}
	}
}
