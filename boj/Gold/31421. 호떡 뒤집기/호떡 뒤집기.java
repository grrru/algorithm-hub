import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static int[] prefix;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        
        if (str.equals("W")) {
        	System.out.println(0);
        } else if (str.equals("B")) {
        	System.out.println(-1);
        }
        if (n == 1) return;
        
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
        	if (str.charAt(i) != str.charAt(i + 1)) list.add(i + 1);
        }
        
        if (list.size() == 0) {
        	if (str.charAt(n - 1) == 'W') System.out.println(0);
        	else System.out.println(-1);
        	return;
        }
        
        if (list.size() == 1) {
        	if (str.charAt(n - 1) == 'W') {
        		System.out.println(1);
        		System.out.println(list.get(0));
        	}
        	else System.out.println(-1);
        	return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append('\n');
        prefix = new int[n + 1];
        
        for (int i = 0; i < list.size() - 2; i++) {
        	prefix[1]++;
        	prefix[list.get(i) + 1]--;
        	sb.append(list.get(i)).append('\n');
        }
        
        if (str.charAt(n - 1) == 'W') {
        	prefix[1]++;
        	prefix[list.get(list.size() - 2) + 1]--;
        	prefix[1]++;
        	prefix[list.get(list.size() - 1) + 1]--;
        	sb.append(list.get(list.size() - 2)).append('\n');
        	sb.append(list.get(list.size() - 1)).append('\n');
        } else {
        	prefix[1]++;
        	prefix[list.get(list.size() - 1) + 1]--;
        	prefix[list.get(list.size() - 2) + 1]++;
        	sb.append(list.get(list.size() - 1)).append('\n');
        	sb.append(list.get(list.size() - 2)).append('\n');
        }
        
        boolean flag = true;
        
        for (int i = 1; i <= n; i++) {
        	prefix[i] += prefix[i - 1];
        	
        	if (str.charAt(i - 1) == 'W') {
        		if (prefix[i] % 2 == 0) continue;
        		
        		flag = false;
        		break;
        		
        	} else {
        		if (prefix[i] % 2 == 1) continue;
        		
        		flag = false;
        		break;
        	}
        }
        
        if (!flag) System.out.println(-1);
        else System.out.println(sb);
    }
}