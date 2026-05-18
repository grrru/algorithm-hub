import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int n;
	static int m;
	static int[][] arr;
	
	static int[][] es;
	static int[][] en;
	static int[][] ws;
	static int[][] wn;
    
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	arr = new int[n][m];
    	es = new int[n][m];
    	en = new int[n][m];
    	ws = new int[n][m];
    	wn = new int[n][m];
    	
    	for (int i = 0; i < n; i++) {
    		String str = br.readLine();
    		for (int j = 0; j < m; j++) arr[i][j] = str.charAt(j) - '0';
    	}
    	
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < m; j++) {
    			
    			if (arr[i][j] == 0) continue;
    			
    			findES(i, j);
    			
    			findEN(i, j);
    			
    			findWS(i, j);
    			
    			findWN(i, j);
    		}
    	}
    	
//    	for (int i = 0; i < n; i++) System.out.println(Arrays.toString(es[i]));
//    	System.out.println();
//    	for (int i = 0; i < n; i++) System.out.println(Arrays.toString(en[i]));
//    	System.out.println();
//    	for (int i = 0; i < n; i++) System.out.println(Arrays.toString(ws[i]));
//    	System.out.println();
//    	for (int i = 0; i < n; i++) System.out.println(Arrays.toString(wn[i]));
    	
    	int res = 0;
    	
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < m; j++) {
    			if (arr[i][j] == 0) continue;

          for (int k = es[i][j]; k > res; k--) {
            if (isBound(i + k - 1, j + k - 1) && isBound(i + 2 * (k - 1), j) && isBound(i + k - 1, j - k + 1)) {
              if (ws[i + k - 1][j + k - 1] >= k && wn[i + 2 * (k - 1)][j] >= k && en[i + k - 1][j - k + 1] >= k) {
                res = Math.max(res, k);
                break;
              }
            }
          }

          for (int k = en[i][j]; k > res; k--) {
            if (isBound(i - k + 1, j + k - 1) && isBound(i - 2 * (k - 1), j) && isBound(i - k + 1, j - k + 1)) {
              if (wn[i - k + 1][j + k - 1] >= k && ws[i - 2 * (k - 1)][j] >= k && es[i - k + 1][j - k + 1] >= k) {
                res = Math.max(res, k);
                break;
              }
            }
          }

          for (int k = ws[i][j]; k > res; k--) {
            if (isBound(i + k - 1, j - k + 1) && isBound(i + 2 * (k - 1), j) && isBound(i + k - 1, j + k - 1)) {
              if (es[i + k - 1][j - k + 1] >= k && en[i + 2 * (k - 1)][j] >= k && wn[i + k - 1][j + k - 1] >= k) {
                res = Math.max(res, k);
                break;
              }
            }
          }
    		
          for (int k = wn[i][j]; k > res; k--) {
            if (isBound(i - k + 1, j - k + 1) && isBound(i - 2 * (k - 1), j) && isBound(i - k + 1, j + k - 1)) {
              if (en[i - k + 1][j - k + 1] >= k && es[i - 2 * (k - 1)][j] >= k && ws[i - k + 1][j + k - 1] >= k) {
                res = Math.max(res, k);
                break;
              }
            }
          }
    		}
    	}
    	
    	System.out.println(res);
    	
    }
    
    static int findES(int x, int y) {
    	if (!isBound(x, y)) return 0;
    	if (es[x][y] != 0) return es[x][y];
    	
    	if (arr[x][y] == 0) return 0;
    	
    	
    	return es[x][y] = 1 + findES(x + 1, y + 1);
    }
    
    static int findEN(int x, int y) {
    	if (!isBound(x, y)) return 0;
    	if (en[x][y] != 0) return en[x][y];
    	
    	if (arr[x][y] == 0) return 0;
    	
    	return en[x][y] = 1 + findEN(x - 1, y + 1);
    }
    
    static int findWS(int x, int y) {
    	if (!isBound(x, y)) return 0;
    	if (ws[x][y] != 0) return ws[x][y];
    	
    	if (arr[x][y] == 0) return 0;
    	
    	return ws[x][y] = 1 + findWS(x + 1, y - 1);
    }
    
    static int findWN(int x, int y) {
    	if (!isBound(x, y)) return 0;
    	if (wn[x][y] != 0) return wn[x][y];
    	
    	if (arr[x][y] == 0) return 0;
    	
    	return wn[x][y] = 1 + findWN(x - 1, y - 1);
    }
    
    static boolean isBound(int x, int y) {
    	return x >= 0 && x < n && y >= 0 && y < m;
    }
}



