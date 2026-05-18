import java.util.*;

class Solution {
    
    int n;
    int[][] arr;
    int[] grr;
    Map<String, Integer> map;
    int[] res;
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        n = friends.length;
        arr = new int[n + 1][n + 1];
        grr = new int[n];
        map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(friends[i], i);
        
        for (String str : gifts) {
            String[] s = str.split(" ");
            String a = s[0];
            String b = s[1];
            
            int ai = map.get(a);
            int bi = map.get(b);
            
            arr[ai][bi]++;
            arr[ai][n]++;
            arr[n][bi]++;
            grr[ai]++;
            grr[bi]--;
        }
        
        res = new int[n];
        
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                
                if (arr[i][j] > arr[j][i]) res[i]++;
                else if (arr[i][j] < arr[j][i]) res[j]++;
                else {
                    if (grr[i] == grr[j]) continue;
                    
                    if (grr[i] > grr[j]) res[i]++;
                    else res[j]++;
                }
            }
        }
        
        for (int i = 0; i < n; i++) answer = Math.max(answer, res[i]);
        
        return answer;
    }
}