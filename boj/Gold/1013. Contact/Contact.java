import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {

	static Pattern pattern = Pattern.compile("(100+1+|01)+");

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) sb.append(pattern.matcher(br.readLine()).matches() ? "YES" : "NO").append('\n');
		System.out.println(sb);
	}
}
