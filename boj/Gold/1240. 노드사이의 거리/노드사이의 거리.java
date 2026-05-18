import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
    static int n;
    static int m;
    static List<Edge>[] graph;
    static int[] dist;
    static int[] parent;
    static int[] pdist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
        	graph[i] = new ArrayList<>();
        }
        
        for (int i = 1; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	
        	graph[u].add(new Edge(v, w));
        	graph[v].add(new Edge(u, w));
        }
        
        makeDist();
        
        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
        	st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	
        	sb.append(easyLca(u, v)).append('\n');
        }
        
        System.out.println(sb);
    }
    
    static int easyLca(int u, int v) {
    	int res = 0;
    	
    	while (dist[u] > dist[v]) {
    		res += pdist[u];
    		u = parent[u];
    	}
    	
    	while (dist[v] > dist[u]) {
    		res += pdist[v];
    		v = parent[v];
    	}
    	
    	while (u != v) {
    		res += pdist[u];
    		res += pdist[v];
    		u = parent[u];
    		v = parent[v];
    	}
    	
    	return res;
    }
    
    static void makeDist() {
    	dist = new int[n + 1];
    	dist[1] = 1;
    	Queue<Integer> queue = new ArrayDeque<>();
    	queue.offer(1);
    	boolean[] visit = new boolean[n + 1];
    	visit[1] = true;
    	
    	parent = new int[n + 1];
    	pdist = new int[n + 1];
    	
    	while (!queue.isEmpty()) {
    		int cur = queue.poll();
    		
    		for (Edge next : graph[cur]) {
    			if (visit[next.to]) continue;
    			visit[next.to] = true;
    			dist[next.to] = dist[cur] + 1;
    			queue.offer(next.to);
    			parent[next.to] = cur;
    			pdist[next.to] = next.w;
    		}
    	}
    }
    
    static class Edge {
    	int to;
    	int w;
    	
		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
    }
}