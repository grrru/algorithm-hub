import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

  static String str;
  static StringBuilder sb = new StringBuilder();
  static boolean[] visit;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    str = br.readLine();

    visit = new boolean[str.length()];
    sol(0, visit.length - 1);

    System.out.println(sb);
  }

  static void sol(int s, int e) {

    if (s > e) return;

    int idx = s;

    for (int i = s; i <= e; i++) {
      if (str.charAt(i) < str.charAt(idx)) idx = i;
    }

    visit[idx] = true;

    for (int i = 0; i < visit.length; i++) {
      if (visit[i]) sb.append(str.charAt(i));
    }

    sb.append('\n');

    sol(idx + 1, e);
    sol(s, idx - 1);
  }
}