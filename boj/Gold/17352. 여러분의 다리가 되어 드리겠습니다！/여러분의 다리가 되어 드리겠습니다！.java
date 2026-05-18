import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int n;
  static int[] parent;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    n = Integer.parseInt(br.readLine());

    if (n == 2) {
      System.out.println(1 + " " + 2);
      return;
    }

    parent = new int[n + 1];
    for (int i = 1; i <= n; i++) parent[i] = i;

    for (int i = 0; i < n - 2; i++) {
      st = new StringTokenizer(br.readLine());

      union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

    int p = find(1);
    for (int i = 2; i <= n; i++) {
      if (find(i) != p) {
        System.out.println(1 + " " + i);
        break;
      }
    }
  
  }

  static int find(int a) {
    if (parent[a] == a) return a;
    return parent[a] = find(parent[a]);
  }

  static void union(int a, int b) {
    int x = find(a);
    int y = find(b);

    if (x == y) return;
    parent[x] = y;
  }
}