package ps_SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 101,680kb
// 4,181ms

public class Solution_1263_사람_네트워크2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[][] aM;
	static int INF = 9999999;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			aM = new int[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					int cur = Integer.parseInt(st.nextToken());
					if (i != j && cur == 0)
						aM[i][j] = INF;
					else
						aM[i][j] = cur;
				}

			for (int k = 0; k < N; k++) { // 경유지
				for (int i = 0; i < N; i++) { // 출발지
					if (i == k)
						continue;
					for (int j = 0; j < N; j++) { // 도착지
						if (j == k || j == i)
							continue;
						aM[i][j] = Math.min(aM[i][j], aM[i][k] + aM[k][j]);
					}
				}
			} //

			int res[] = new int[N];
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++)
					res[i] += aM[i][j];
				min = res[i] < min ? res[i] : min;
			}
			sb.append("#" + tc + " " + min + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}