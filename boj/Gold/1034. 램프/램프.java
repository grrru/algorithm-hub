import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static String[] s;
	static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	map = new HashMap<>();
    	int ans = 0;
    	s = new String[n];
    	for (int i = 0; i < n; i++) {
    		s[i] = br.readLine();
    	}
    	
    	int k = Integer.parseInt(br.readLine());
    	
    	for (int i = 0; i < n; i++) {
    		String str = s[i];
    		if (map.containsKey(str)) {
    			map.put(str, map.get(str) + 1);
    			ans = Math.max(ans, map.get(str));
    			continue;
    		}
    		
    		int res = 0;
    		for (int j = 0; j < m; j++) {
    			if (str.charAt(j) == '0') res++;
    		}
    		
    		if (res > k || (k - res) % 2 != 0) continue;
    		
    		map.put(str, 1);
    		ans = Math.max(ans, 1);
    	}
    	
    	System.out.println(ans);
    }
}
