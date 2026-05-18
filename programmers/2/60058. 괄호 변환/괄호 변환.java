import java.util.*;

class Solution {
    
    int[] prefix;
    int n;
    StringBuilder sb = new StringBuilder();
    String str;
    
    public String solution(String p) {
        
        n = p.length();
        prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i];
            if (p.charAt(i) == '(') prefix[i + 1]++;
        }
        
        str = p;
        recursion(1, n);
        
        return sb.toString();
    }
    
    void recursion(int start, int end) {
        if (start > end) return;
        
        for (int i = start + 1; i <= end; i += 2) {
            if ((prefix[i] - prefix[start - 1]) * 2 == i + 1 - start) {
                
                if (checkComplete(start, i)) {
                    for (int j = start; j <= i; j++) {
                        sb.append(str.charAt(j - 1));
                    }
                    recursion(i + 1, end);
                    return;
                }
                
                sb.append('(');
                recursion(i + 1, end);
                sb.append(')');
                
                for (int j = start + 1; j < i; j++) {
                    sb.append(str.charAt(j - 1) == '(' ? ')' : '(');
                }
                
                break;
            }
        }
    }
    
    boolean checkComplete(int start, int end) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = start; i <= end; i++) {
            if (str.charAt(i - 1) == '(') {
                stack.add(1);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pollLast();
            }
        }
        
        return stack.isEmpty();
    }
}