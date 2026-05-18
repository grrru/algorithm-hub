import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int n;
  static long[] dp;
  static int[] a;
  static int[] b;
  static List<Edge>[] graph;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    n = Integer.parseInt(br.readLine());

    a = new int[n + 1];
    b = new int[n + 1];

    graph = new List[n + 1];
    for (int i = 0; i <= n; i++)
      graph[i] = new ArrayList<>();

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= n; i++)
      a[i] = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= n; i++) {
      b[i] = Integer.parseInt(st.nextToken());

      if (bound(i - a[i]))
        graph[i - a[i]].add(new Edge(i, b[i]));
      if (bound(i + a[i]))
        graph[i + a[i]].add(new Edge(i, b[i]));

      if (bound(i - a[i]) && bound(i + a[i]))
        continue;
      graph[0].add(new Edge(i, b[i]));
    }

    dp = new long[n + 1];
    Arrays.fill(dp, Long.MAX_VALUE);
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    pq.offer(new Edge(0, 0));
    dp[0] = 0;

    while (!pq.isEmpty()) {
      Edge cur = pq.poll();
      if (dp[cur.to] < cur.w)
        continue;

      for (Edge next : graph[cur.to]) {
        if (dp[next.to] > dp[cur.to] + next.w) {
          dp[next.to] = dp[cur.to] + next.w;
          pq.offer(new Edge(next.to, dp[next.to]));
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= n; i++)
      sb.append(dp[i]).append(' ');
    System.out.println(sb);
  }

  static class Edge implements Comparable<Edge> {
    int to;
    long w;

    public Edge(int to, long w) {
      this.to = to;
      this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
      return Long.compare(this.w, o.w);
    }
  }

  static boolean bound(int k) {
    return k >= 1 && k <= n;
  }
}