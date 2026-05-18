import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static int n;
  static final int MOD = 987654321;
  static long[] dp;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    dp = new long[n + 1];

    dp[0] = 1;
    dp[2] = 1;

    System.out.println(solution(n));
  }

  static long solution(int cur) {
    if (dp[cur] != 0) return dp[cur];

    long res = 0;

    for (int i = 0; i <= cur - 2; i += 2) {
      res += solution(i) * solution(cur - 2 - i);
      res %= MOD;
    }

    return dp[cur] = res;
  }
}