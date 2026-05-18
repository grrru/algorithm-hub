import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
	
	static StringBuilder res;
		
	public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int t = Integer.parseInt(br.readLine());
    	Map<Integer, StringBuilder> map = new HashMap<>();
    	
    	StringBuilder sb = new StringBuilder();
    	
    	while (t-- > 0) {
    		int n = Integer.parseInt(br.readLine());
    		
    		if (map.containsKey(n)) {
    			sb.append(map.get(n)).append('\n');
    			continue;
    		}
    		
    		res = new StringBuilder();
    		char[] chars = new char[n - 1];
    		
    		perm(0, chars);
    		
    		sb.append(res).append('\n');
    		map.put(n, res);
    	}
    	
    	System.out.println(sb);
    }
	
	static void perm(int depth, char[] chars) {
		if (depth == chars.length) {
			if (isRight(chars)) {
				for (int i = 1; i < depth + 1; i++) res.append(i).append(chars[i - 1]);
				res.append(depth + 1).append('\n');
			}
			return;
		}
		
		chars[depth] = ' ';
		perm(depth + 1, chars);
		chars[depth] = '+';
		perm(depth + 1, chars);
		chars[depth] = '-';
		perm(depth + 1, chars);
	}
	
	static boolean isRight(char[] chars) {
		int n = chars.length;
		
		List<Integer> list = new ArrayList<>();
		
		int j = 0;
		for (; j <= n;) {
			int k = j + 1;
			while (j != n && chars[j] == ' ') {
				k *= 10;
				k += ++j + 1;
			}
			j++;
			list.add(k);
		}
		
		int res = list.get(0);
		
		int idx = 0;
		
		for (int i = 1; i < list.size(); i++) {
			while (chars[idx] == ' ') idx++;
			if (chars[idx] == '+') res += list.get(i);
			else res -= list.get(i);
			idx++;
		}
		return res == 0;
	}
}