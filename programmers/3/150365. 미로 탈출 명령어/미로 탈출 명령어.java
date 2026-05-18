import java.util.*;

class Solution {
    
    int[] arr;
    int[] cnt;
    int n;
    int m;
    int[] di = {1, 0, 0, -1};
    int[] dj = {0, -1, 1, 0};
    
    public String solution(int nn, int mm, int x, int y, int r, int c, int k) {
        
        n = nn;
        m = mm;
        
        StringBuilder sb = new StringBuilder();
        
        arr = new int[k];
        int t = Math.abs(x - r) + Math.abs(y - c);
        
        if (t > k || t % 2 != k % 2) return "impossible";
        
        cnt = new int[4]; // d l r u
        
        int a = r - x;
        if (a >= 0) {
            cnt[0] = a;
        } else {
            cnt[3] = -a;
        }
            
        int b = c - y;
        if (b >= 0) {
            cnt[2] = b;
        } else {
            cnt[1] = -b;
        }
        
        int rest = k - (cnt[0] + cnt[1] + cnt[2] + cnt[3]);
        
        for (int i = 0; i < k; i++) {
            for (int d = 0; d < 4; d++) {
                if (cnt[d] == 0) {
                    if (rest == 0) continue;
                    if (!bound(x + di[d], y + dj[d])) continue;
                    
                    rest -= 2;
                    cnt[3 - d]++;
                    x += di[d];
                    y += dj[d];
                    arr[i] = d;
                    break;
                } else {
                    if (!bound(x + di[d], y + dj[d])) continue;
                    cnt[d]--;
                    x += di[d];
                    y += dj[d];
                    arr[i] = d;
                    break;
                }
            }
        }
        
        for (int i = 0; i < k; i++) {
            if (arr[i] == 0) sb.append('d');
            else if (arr[i] == 1) sb.append('l');
            else if (arr[i] == 2) sb.append('r');
            else sb.append('u');
        }
        
        return sb.toString();
    }
    boolean bound(int x, int y) {
        return x >= 1 && x <= n && y >= 1 && y <= m;
    }
}