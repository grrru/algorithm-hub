import java.util.*;

class Solution {
    
    int n;
    
    int[] arr;
    int[] brr;
    boolean[] visit;
    int[][] dice;
    
    int win = 0;
    
    int k;
    int[] answer;
    
    public int[] solution(int[][] dic) {
        dice = dic;
        
        n = dice.length;
        answer = new int[n / 2];
        makeK();
        
        visit = new boolean[n + 1];
        comb(1, 0);
        
        return answer;
    }
    
    void comb(int cur, int cnt) {
        if (cnt == n / 2) {
            
            check();
            
            return;
        }
        
        if (cur == n) return;
        
        visit[cur] = true;
        comb(cur + 1, cnt + 1);
        visit[cur] = false;
        comb(cur + 1, cnt);
    }
    
    void check() {
        arr = new int[k + 1];
        brr = new int[k + 1];
        
        int[] aidx = new int[n / 2];
        int[] bidx = new int[n / 2];
        
        int ai = 0;
        int bi = 0;
        for (int i = 1; i <= n; i++) {
            if (visit[i]) aidx[ai++] = i;
            else bidx[bi++] = i;
        }
        
        int[] idx = new int[n / 2];

        for (int i = 0; i < k; i++) {
            int t = i;
            for (int u = 0; u < idx.length; u++) {
                idx[u] = t % 6;
                t /= 6;
                
                arr[i + 1] += dice[aidx[u] - 1][idx[u]];
                brr[i + 1] += dice[bidx[u] - 1][idx[u]];
            }
        }
        
        Arrays.sort(arr);
        Arrays.sort(brr);
        
        int aWin = 0;
        int bWin = 0;
        
        for (int i = 1; i <= k; i++) {
            int cur = arr[i];
            
            int s = 1;
            int e = k;
            int mid = 1;
            
            int res = 0;
            
            while (s <= e) {
                mid = (s + e) / 2;
                
                if (brr[mid] >= cur) {
                    e = mid - 1;
                } else {
                    res = mid;
                    s = mid + 1;
                }
            }
            
            aWin += res;
            
            cur = brr[i];
            
            s = 1;
            e = k;
            mid = 1;
            res = 0;
            
            while (s <= e) {
                mid = (s + e) / 2;
                
                if (arr[mid] >= cur) {
                    e = mid - 1;
                } else {
                    res = mid;
                    s = mid + 1;
                }
            }
            
            bWin += res;
        }
        
//         System.out.println(bWin);
//         System.out.println(Arrays.toString(bidx));
        
//         System.out.println(aWin);
//         System.out.println(Arrays.toString(aidx));
        
        if (aWin > bWin && aWin > win) {
            win = aWin;
            answer = aidx;
        } else if (aWin < bWin && bWin > win) {
            win = bWin;
            answer = bidx;
        }
    }
    
    void makeK() {
        int t = n / 2;
        k = 1;
        while (t-- > 0) k *= 6;
    }
}