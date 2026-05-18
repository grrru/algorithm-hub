import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int q;
	static long[][][][][][] map;
	static List<Edge>[] list;
	static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        
        map = new long[5][5][5][5][5][5];
        list = new List[5];
        
        for (int i = 0; i < 5; i++) list[i] = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	int p = st.nextToken().charAt(0) - 'A';
        	list[p].add(new Edge(u, v));
        }
        
        boolean[] visit = new boolean[5];
        int[] res = new int[5];
        comb(0, visit, res);
        
        
        while (q-- > 0) {
        	st = new StringTokenizer(br.readLine());
        	int[][] arr = new int[5][2];
        	for (int i = 0; i < 5; i++) {
        		arr[i][0] = i;
        		arr[i][1] = Integer.parseInt(st.nextToken());
        	}
        	
        	Arrays.sort(arr, (o1, o2) -> {
        		return o1[1] - o2[1];
        	});

        	long ans = 0;
        	for (int i = 0; i < 5; i++) {
        		ans += arr[i][1] * map[arr[0][0]][arr[1][0]][arr[2][0]][arr[3][0]][arr[4][0]][i];
        	}
        	
        	sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }
    static void comb(int depth, boolean[] visit, int[] res) {
    	if (depth == 5) {
    		kruskal(res);
    		return;
    	}
    	
    	for (int i = 0; i < 5; i++) {
    		if (visit[i]) continue;
    		
    		visit[i] = true;
    		res[depth] = i;
    		comb(depth + 1, visit, res);
    		visit[i] = false;
    	}
    }
    
    static void kruskal(int[] res) {
		int cnt = 0;
		parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;
		
        run:
        for (int i = 0; i < 5; i++) {
        	for (int j = 0; j < list[res[i]].size(); j++) {
        		Edge cur = list[res[i]].get(j);
        		
        		if (!union(cur.u, cur.v)) continue;
        		map[res[0]][res[1]][res[2]][res[3]][res[4]][i]++;
        		if (++cnt == n - 1) break run;
        	}
        }
	}
	static int find(int a) {
    	if (parent[a] == a) return a;
    	return parent[a] = find(parent[a]);
    }
    
    static boolean union(int a, int b) {
    	int x = find(a);
    	int y = find(b);
    	if (x == y) return false;
    	
    	parent[x] = y;
    	return true;
    }
    
    static class Edge {
    	int u;
    	int v;
    	
		public Edge(int u, int v) {
			super();
			this.u = u;
			this.v = v;
		}
    }
}