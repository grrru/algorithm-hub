import java.util.*;

class Solution {
    
    final int MOD = 10_007;
    int n;
    int[] tops;
    
    int T;
    int[] dp;
    
    public int solution(int _n, int[] _tops) {
        n = _n;
        tops = _tops;
        
        T = 2 * n + 1;
        dp = new int[T + 1];
        
        return makeDp(1);
    }
    
    int makeDp(int cur) {
        if (cur == T) return dp[cur] = 1;
        if (cur > T) return 1;
        if (dp[cur] != 0) return dp[cur];
        
        int res = makeDp(cur + 1) + makeDp(cur + 2);
        res %= MOD;
        
        if (cur % 2 == 0 && tops[(cur - 2) / 2] == 1) {
            res += makeDp(cur + 1);
            res %= MOD;
        }
        
        dp[cur] = res;
        return dp[cur];
    }
}