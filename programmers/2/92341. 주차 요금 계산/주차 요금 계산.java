import java.util.*;

class Solution {
    
    int[] time;
    int[] in;
    int[] ans;
    
    public int[] solution(int[] fees, String[] records) {
        
        time = new int[10000];
        in = new int[10000];
        
        for (String str : records) {
            int t = makeTime(str);
            int num = makeNum(str);
            
            if (str.charAt(11) == 'I') {
                in[num] = t;
            } else {
                time[num] += t - in[num];
                in[num] = 0;
            }
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        
        for (int i = 0; i < 10000; i++) {
            if (in[i] != 0) {
                time[i] += 1440 - in[i];
            }
            
            if (time[i] == 0) continue;
            
            if (time[i] <= fees[0]) {
                queue.offer(fees[1]);
            } else {
                int cal = fees[1];
                int restTime = time[i] - fees[0];
                if (restTime % fees[2] == 0) {
                    cal += (restTime / fees[2]) * fees[3];
                } else {
                    cal += (restTime / fees[2] + 1) * fees[3];
                }
                
                queue.offer(cal);
            }
        }

        int k = queue.size();
        ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll();
        }
        return ans;
    }
    
    int makeNum(String str) {
        return (str.charAt(6) - '0') * 1000
            + (str.charAt(7) - '0') * 100
            + (str.charAt(8) - '0') * 10
            + str.charAt(9) - '0';
    }
    
    int makeTime(String str) {
        return (str.charAt(0) - '0') * 600 + (str.charAt(1) - '0') * 60
            + (str.charAt(3) - '0') * 10 + (str.charAt(4) - '0') + 1;
    }
}