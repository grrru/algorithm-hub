import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	
	static int n;
	static int m;
	static List<Integer>[] graph;
    
    @SuppressWarnings("unchecked")
	public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	graph = new List[n + 1];
    	for (int i = 1; i <= n; i++) graph[i] = new ArrayList<Integer>();
    	
    	while (m-- > 0) {
    		st = new StringTokenizer(br.readLine());
    		
    		int u = Integer.parseInt(st.nextToken());
    		int v = Integer.parseInt(st.nextToken());
    		
    		graph[u].add(v);
    		graph[v].add(u);
    	}
    	
    	int res = Integer.MAX_VALUE;
    	
    	for (int i = 1; i < n; i++) {
    		for (int j = i + 1; j < n; j++) {
    			if (!graph[i].contains(j)) continue;
    			
    			for (int k : graph[j]) {
    				if (!graph[i].contains(k)) continue;
    				
    				int t = graph[i].size() + graph[j].size() + graph[k].size() - 6;
    				
    				res = Math.min(res, t);
    			}
    		}
    	}
    	
    	System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }
}