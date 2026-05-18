import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  static int m;
  static int n;
  static int[] parent;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    StringBuilder sb = new StringBuilder();
    while (true) {
      st = new StringTokenizer(br.readLine());
      m = Integer.parseInt(st.nextToken());
      n = Integer.parseInt(st.nextToken());

      if (m == 0) break;

      parent = new int[m];
      for (int i = 0; i < m; i++) parent[i] = i;

      int total = 0;
      PriorityQueue<Edge> pq = new PriorityQueue<>();
      while (n-- > 0) {
        st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        pq.offer(new Edge(x, y, d));
        total += d;
      }

      int res = 0;
      int cnt = 0;
      while (!pq.isEmpty()) {
        Edge cur = pq.poll();

        if (union(cur.x, cur.y)) {
          res += cur.d;
          if (++cnt == m - 1) break;
        }
      }
      sb.append(total - res).append('\n');
    }
    
    System.out.println(sb);
  }

  static class Edge implements Comparable<Edge> {
    int x;
    int y;
    int d;

    public Edge (int x, int y, int d) {
      this.x = x;
      this.y = y;
      this.d = d;
    }

    @Override
    public int compareTo (Edge o) {
      return Integer.compare(this.d, o.d);
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
}