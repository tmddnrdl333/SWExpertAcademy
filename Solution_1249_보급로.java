package ps_SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 35,916kb
// 166ms

public class Solution_1249_보급로 {

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				char[] temp = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j + 1] = temp[j] - '0';
				}
			}

			int[][] dp = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				Arrays.fill(dp[i], -1);
			}
			dp[1][1] = 0;

			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] { 1, 1 });
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				int ct = dp[cur[0]][cur[1]];
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if (nr == 0 || nc == 0 || nr == N + 1 || nc == N + 1)
						continue;
					int nt = ct + map[nr][nc];
					if (dp[nr][nc] == -1 || nt < dp[nr][nc]) {
						dp[nr][nc] = nt;
						q.add(new int[] { nr, nc });
					}
				}
			}

			sb.append("#" + tc + " " + dp[N][N] + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}