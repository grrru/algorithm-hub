import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[][] sumJ = new int[n + 1][m + 1];
        int[][] sumO = new int[n + 1][m + 1];
        int[][] sumI = new int[n + 1][m + 1];
        
        int q = Integer.parseInt(br.readLine());
        
        for (int i= 0; i < n; i++) {
        	String str = br.readLine();
        	
        	for (int j = 0; j < m; j++) {
        		sumJ[i + 1][j + 1] += sumJ[i][j + 1] + sumJ[i + 1][j] - sumJ[i][j];
        		sumO[i + 1][j + 1] += sumO[i][j + 1] + sumO[i + 1][j] - sumO[i][j];
        		sumI[i + 1][j + 1] += sumI[i][j + 1] + sumI[i + 1][j] - sumI[i][j];
        		
        		switch (str.charAt(j)) {
				case 'J':
					sumJ[i + 1][j + 1]++;
					break;
				case 'O':
					sumO[i + 1][j + 1]++;
					break;
				case 'I':
					sumI[i + 1][j + 1]++;
					break;
				default:
					break;
				}
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (q-- > 0) {
        	st = new StringTokenizer(br.readLine());
        	
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	
        	int j = sumJ[c][d] - sumJ[c][b - 1] - sumJ[a - 1][d] + sumJ[a - 1][b - 1];
        	int o = sumO[c][d] - sumO[c][b - 1] - sumO[a - 1][d] + sumO[a - 1][b - 1];
        	int i = sumI[c][d] - sumI[c][b - 1] - sumI[a - 1][d] + sumI[a - 1][b - 1];
        	
        	sb.append(j).append(' ').append(o).append(' ').append(i).append('\n');
        }
        System.out.println(sb);
    }
}