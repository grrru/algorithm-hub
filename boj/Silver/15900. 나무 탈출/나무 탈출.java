import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    
	static int n;
	static int[] dp;
	static List<Integer>[] graph;
	static int res;

    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st;
         
         n = Integer.parseInt(br.readLine());
         
         graph = new List[n + 1];
         for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
         
         for (int i = 0; i < n - 1; i++) {
        	 st = new StringTokenizer(br.readLine());
        	 
        	 int u = Integer.parseInt(st.nextToken());
        	 int v = Integer.parseInt(st.nextToken());
        	 
        	 graph[u].add(v);
        	 graph[v].add(u);
         }
         
         dp = new int[n + 1];
         
         dp[1] = 1;
         
         makeDp(1);
         
         System.out.println(res % 2 == 0 ? "No" : "Yes");
    }
    
    static void makeDp(int cur) {
    	int cnt = 0;

    	for (int next : graph[cur]) {
    		if (dp[next] != 0) continue;
    		
    		dp[next] = dp[cur] + 1;
    		cnt++;
    		makeDp(next);
    	}
    	
    	if (cnt == 0) res += dp[cur] - 1;
    }
}