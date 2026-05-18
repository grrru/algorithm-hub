import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int n;
  static int m;
  static int r;
  static int[] items;
  static int[][] dist;
  static final int INF = 2_000;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    r = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    items = new int[n + 1];
    for (int i = 1; i <= n; i++) items[i] = Integer.parseInt(st.nextToken());

    dist = new int[n + 1][n + 1];
    for (int i = 1; i <= n; i++) Arrays.fill(dist[i], INF);

    for (int i = 0; i < r; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());

      dist[a][b] = dist[b][a] = Math.min(dist[a][b], d);
    }

    for (int k = 1; k <= n; k++) {
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {

          if (i == k || k == j || i == j) continue;
          dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        }
      }
    }

    int cnt = 0;

    for (int i = 1; i <= n; i++) {

      int res = 0;

      for (int j = 1; j <= n; j++) {
        if (i == j || dist[i][j] <= m) res += items[j];
      }

      if (res > cnt) cnt = res;
    }
    
    System.out.println(cnt);
  }
}