import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;


public class Main {
		
	public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	String str = br.readLine();
    	int n = str.length();
    	
    	Deque<Node> dq = new ArrayDeque<>();
    	
    	long[] arr = new long[n + 1];
    	int[] parent = new int[n + 1];
    	
    	run:
    	for (int i = 1; i <= n; i++) {    		
    		char c = str.charAt(i - 1);
    		
    		switch (c) {
    		case '(':
    			
    		case '[':
    			if (dq.isEmpty()) parent[i] = 0;
    			else parent[i] = dq.peekLast().idx;
    			dq.add(new Node(c, i));	
    			
    			break;
    		case ')':
    			if (dq.isEmpty() || dq.peekLast().c == '[') {
    				arr[0] = 0;
    				break run;
    			}
    			
    			Node cur = dq.pollLast();
    			int p = parent[cur.idx];
    			arr[p] += 2 * (arr[cur.idx] == 0 ? 1 : arr[cur.idx]);
    			
    			break;
    		case ']':
    			if (dq.isEmpty() || dq.peekLast().c == '(') {
    				arr[0] = 0;
    				break run;
    			}
    			
    			cur = dq.pollLast();
    			p = parent[cur.idx];
    			arr[p] += 3 * (arr[cur.idx] == 0 ? 1 : arr[cur.idx]);
    			
    			break;
    		}
    	}
    	    	
    	System.out.println(dq.isEmpty() ? arr[0] : 0);
    	
    }
	
	static class Node {
		char c;
		int idx;
		
		public Node(char c, int idx) {
			this.c = c;
			this.idx = idx;
		}
	}
}