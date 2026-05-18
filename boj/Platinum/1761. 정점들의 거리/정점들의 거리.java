import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static List<Edge>[] graph;
	static int[] depth;
	static int[][] parent;
	static int[][] dist;
	static int m;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        
        for (int i = 1; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	
        	graph[u].add(new Edge(v, w));
        	graph[v].add(new Edge(u, w));
        }
        
        depth = new int[n + 1];
        depth[1] = 1;
        parent = new int[n + 1][17];
        dist = new int[n + 1][17];
        
        dfs(1);
        
        m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
        	st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	
        	sb.append(LCA(u, v)).append('\n');
        }
        
        System.out.println(sb);
    }
    
    static long LCA(int u, int v) {
    	long ans = 0;
    	
    	if (depth[u] < depth[v]) {
    		int tmp = u;
    		u = v;
    		v = tmp;
    	}
    	
    	int k = 16;
    	while (depth[u] != depth[v]) {
    		if (1 << k > depth[u] - depth[v]) {
    			k--;
    			continue;
    		}
    		ans += dist[u][k];
    		u = parent[u][k];
    	}
    	
    	if (u == v) return ans;
    	
    	k = 16;
    	while (parent[u][k] == 0 || parent[v][k] == 0) k--;
    	
    	
    	while (parent[u][0] != parent[v][0]) {
    		if (parent[u][k] == parent[v][k]) {
    			k--;
    			continue;
    		}
    		
    		ans += dist[u][k];
    		ans += dist[v][k];
    		u = parent[u][k];
    		v = parent[v][k];
    	}
    	
    	ans += dist[u][0] + dist[v][0];
    	
    	return ans;
    }
    
    static void dfs(int cur) {
    	for (Edge next : graph[cur]) {
    		if (depth[next.to] != 0) continue;
    		
    		depth[next.to] = depth[cur] + 1;
    		
    		parent[next.to][0] = cur;
    		dist[next.to][0] = next.w;
    		
    		for (int i = 1; i <= 16; i++) {
    			if (parent[next.to][i - 1] == 0) break;
    			parent[next.to][i] = parent[parent[next.to][i - 1]][i - 1];
    			dist[next.to][i] = dist[next.to][i - 1] + dist[parent[next.to][i - 1]][i - 1];
    		}
    		
    		dfs(next.to);
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