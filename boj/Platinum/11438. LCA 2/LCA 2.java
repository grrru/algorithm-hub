import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static List<Integer>[] graph;
	static int[] depth;
	static int[][] parent;
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
        	
        	graph[u].add(v);
        	graph[v].add(u);
        }
        
        depth = new int[n + 1];
        parent = new int[n + 1][20];
        
        depth[1] = 1;
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
    
    static int LCA(int u, int v) {
    	
    	if (depth[u] < depth[v]) {
    		int tmp = u;
    		u = v;
    		v = tmp;
    	}
    	
    	int k = 19;
    	
    	while (depth[u] != depth[v]) {
    		int diff = depth[u] - depth[v];
    		
    		while ((1 << k) > diff) {
    			k--;
    		}
    		u = parent[u][k];
    	}
    	
    	if (u == v) return u;
    	
    	k = 19;
    	while (parent[u][k] == 0 || parent[v][k] == 0) k--;
    	
    	while (parent[u][0] != parent[v][0]) {
    		
    		while (parent[u][k] == parent[v][k]) k--;
    		
    		u = parent[u][k];
    		v = parent[v][k];
    	}
    	
    	return parent[u][0];
    }
    
    static void dfs(int cur) {
    	for (int next : graph[cur]) {
    		if (depth[next] != 0) continue;
    		depth[next] = depth[cur] + 1;
    		
    		parent[next][0] = cur;
    		int k = 1;
    		
    		while (parent[next][k - 1] != 0) {
    			parent[next][k] = parent[parent[next][k - 1]][k++ - 1];
    		}
    		
    		dfs(next);
    	}
    }
}