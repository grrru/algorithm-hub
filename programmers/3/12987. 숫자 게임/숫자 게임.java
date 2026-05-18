import java.util.*;

class Solution {
    
    int ans;
    boolean[] visit;
    int[] brr;
    int n;
    
    public int solution(int[] arr, int[] B) {
        
        brr = B;
        Arrays.sort(brr);
        n = B.length;
        visit = new boolean[n];
        int s = 0;
        
        for (int i = 0; i < n; i++) {
            int idx = binarySearch(arr[i] + 1);
            
            if (idx == n) {
                while (visit[s]) s++;
                visit[s++] = true;
                continue;
            }
            
            if (arr[i] < brr[idx]) ans++;
            visit[idx] = true;
        }
        
        return ans;
    }
    
    int binarySearch(int x) {
        int res = 0;
        int start = 0;
        int end = n - 1;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            if (brr[mid] > x) {
                res = mid;
                end = mid - 1;
            } else if (brr[mid] < x) {
                start = mid + 1;
            } else {
                res = mid;
                break;
            }
        }
        
        while (res < n && visit[res]) res++;
        
        return res;
    }
    
}