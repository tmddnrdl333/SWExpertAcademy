package ps_SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 93,876kb
// 2,390ms

public class Solution_5643_키_순서 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M;
	static boolean[][] aM;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			aM = new boolean[N][N];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				aM[from][to] = true;
			}
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if (i == k)
						continue;
					for (int j = 0; j < N; j++) {
						if (j == i || j == k)
							continue;
						aM[i][j] = aM[i][j] || aM[i][k] && aM[k][j];
					}
				}
			}

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				int res = 0;
				for (int j = 0; j < N; j++)
					res += aM[i][j] || aM[j][i] ? 1 : 0;
				if (res == N - 1)
					cnt++;
			}

			sb.append("#" + tc + " " + cnt + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}