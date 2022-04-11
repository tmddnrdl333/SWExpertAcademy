package ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 19,188kb
// 133ms

public class Solution_5607_조합 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static long[] fac;
	static final long p = 1234567891;

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(br.readLine());
		int[][] inp = new int[T + 1][2];
		int max = 0;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			inp[tc][0] = a;
			inp[tc][1] = b;
			max = a > max ? a : max;
			max = b > max ? b : max;
		}
		fac = new long[max + 1];
		fac[1] = 1;
		for (int i = 2; i <= max; i++) {
			fac[i] = fac[i - 1] % p * i % p;
		}
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " " + comb(inp[tc][0], inp[tc][1]) + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static long pow(long a, long b) {
		if (b == 0)
			return 1;
		long res = pow(a, b / 2) % p;
		if (b % 2 == 0)
			return (res % p) * (res % p) % p;
		else
			return ((res % p) * (res % p) % p) * a % p;
	}

	public static long comb(int n, int r) {
		long res = ((fac[n] % p) * (pow(fac[n - r], p - 2) % p) % p) * (pow(fac[r], p - 2) % p);
		return res % p;
	}
}