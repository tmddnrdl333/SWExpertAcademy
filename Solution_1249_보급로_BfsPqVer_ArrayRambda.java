package ps_SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_1249_보급로_BfsPqVer_ArrayRambda {

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[][] map;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				char[] temp = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = temp[j] - '0';
				}
			}

			dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dp[i], -1);
			}
			dp[0][0] = 0;

			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[2] - o2[2]));
			pq.offer(new int[] { 0, 0, 0 });

			while (!pq.isEmpty()) {
				int[] cur = pq.poll();
				if (cur[0] == N - 1 && cur[1] == N - 1)
					break;
				int ct = dp[cur[0]][cur[1]];
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if (nr == -1 || nc == -1 || nr == N || nc == N)
						continue;
					if (dp[nr][nc] != -1)
						continue;
					int nt = ct + map[nr][nc];
					dp[nr][nc] = nt;
					pq.offer(new int[] { nr, nc, nt });
				}
			}

			sb.append("#" + tc + " " + dp[N - 1][N - 1] + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}