import java.util.*;

class Solution {
    
    int[] ans;
    int[][] edges;
    int n;
    Map<Integer, int[]> map;
    
    public int[] solution(int[][] edge) {

        edges = edge;
        Arrays.sort(edges, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        n = edges.length;
        ans = new int[4];
        
        map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            
            int[] in = map.getOrDefault(edges[i][1], new int[] {0, 0});
            in[0]++;
            map.put(edges[i][1], in);
            
            int[] out = map.getOrDefault(edges[i][0], new int[] {0, 0});
            out[1]++;
            map.put(edges[i][0], out);
        }

        Set<Integer> set = map.keySet();
        
        for (int k : set) {
            int[] res = map.get(k);
            if (res[0] == 0 && res[1] >= 2) {
                ans[0] = k;
                break;
            }
        }

        int startIdx = binary(ans[0]);
        
        while (startIdx < n && edges[startIdx][0] == ans[0]) {
            
            int pt = edges[startIdx][1];
            int cnt = 0;
            
            int[] cur = map.get(pt);
            cur[0]--;
            map.put(pt, cur);
            
            while (true) {
                // System.out.println("pt : " + pt);
                // System.out.println(Arrays.toString(cur));
                if (cur[1] == 0) {
                    if (cur[0] == 0) {
                        if (cnt == 0) ans[2]++;
                        else {
                            if (pt == edges[startIdx][1]) ans[1]++;
                            else ans[2]++;
                        }
                    }
                    else ans[2]++;
                    break;
                }
                
                if (cur[1] == 2) {
                    ans[3]++;
                    break;
                }
                
                cur[1]--;
                map.put(pt, cur);
                int nextIdx = binary(pt);
                pt = edges[nextIdx][1];
                cur = map.get(pt);
                cur[0]--;
                
                cnt++;
            }
            
            startIdx++;
        }
        
        return ans;
    }
    int binary(int k) {
        int start = 0;
        int end = n - 1;
        
        int idx = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            
            if (edges[mid][0] == k) {
                idx = mid;
                end = mid - 1;
            } else if (edges[mid][0] > k) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return idx;
    }
}