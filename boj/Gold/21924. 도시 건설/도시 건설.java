
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;

    static PriorityQueue<Edge> pq;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();
        long res = 0;

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(u, v, w));
            res += w;
        }

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++)
            parent[i] = i;

        int cnt = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (union(cur.u, cur.v)) {
                res -= cur.w;
                if (++cnt == n - 1)
                    break;
            }
        }

        if (cnt != n - 1) {
            System.out.println(-1);
        } else {
            System.out.print(res);
        }
    }

    static int find(int a) {
        if (parent[a] == a)
            return a;

        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b) {
        if (find(a) == find(b))
            return false;

        parent[find(a)] = find(b);

        return true;
    }

    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
}
