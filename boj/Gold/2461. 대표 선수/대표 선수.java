import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int[][] arr;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        int min = Integer.MAX_VALUE;
        int max = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < m; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        	Arrays.sort(arr[i]);
        	
        	if (min > arr[i][0]) min = arr[i][0];
        	if (max < arr[i][0]) max = arr[i][0];
        	pq.offer(new Node(i, 0, arr[i][0]));
        }
        
        int ans = max - min;
        
        while (!pq.isEmpty()) {
        	Node cur = pq.poll();
        	
        	if (cur.y == m - 1) break;
        	
        	Node next = new Node(cur.x, cur.y + 1, arr[cur.x][cur.y + 1]);
        	pq.offer(next);
        	
        	if (max < next.w) max = next.w;
        	min = pq.peek().w;
        	
        	if (ans > max - min) ans = max - min;
        	
        }
        
        System.out.println(ans);
    }
    
    static class Node implements Comparable<Node> {
    	int x;
    	int y;
    	int w;
    	
		public Node(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
    }
}