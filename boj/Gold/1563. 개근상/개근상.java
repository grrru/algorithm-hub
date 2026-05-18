import java.io.*;

public class Main {

  static final int MOD = 1_000_000;
  static int n;
  static int[][][][] dp;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    dp = new int[3][4][3][n + 1];

    int res = solve(0, 0, 0, 1) + solve(1, 0, 1, 1) + solve(2, 1, 0, 1);
    res %= MOD;

    System.out.println(res);
  }

  static int solve(int cur, int seq, int cnt, int max) {

    if (dp[cur][seq][cnt][max] != 0) return dp[cur][seq][cnt][max];

    if (cnt == 2) return 0;
    if (seq == 3) return 0;
    if (max == n) return 1;

    int k = 0;

    k += solve(0, 0, cnt, max + 1);
    k += solve(1, 0, cnt + 1, max + 1);

    if (cur == 2) {
      k += solve(2, seq + 1, cnt, max + 1);
    } else {
      k += solve(2, 1, cnt, max + 1);
    }

    k %= MOD;
    return dp[cur][seq][cnt][max] = k;
  }
}