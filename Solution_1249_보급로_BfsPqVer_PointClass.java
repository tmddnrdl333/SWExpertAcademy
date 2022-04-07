package ps_SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

// 23.976kb
// 131ms

public class Solution_1249_보급로_BfsPqVer_PointClass {

	static class Point implements Comparable<Point> {
		int r, c, t;

		public Point(int r, int c, int t) {
			dp[r][c] = t;
			this.r = r;
			this.c = c;
			this.t = t;
		}

		@Override
		public int compareTo(Point o) {
			return this.t - o.t;
		}
	}

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

			PriorityQueue<Point> pq = new PriorityQueue<>();
			pq.add(new Point(0, 0, 0));
			while (!pq.isEmpty()) {
				Point cur = pq.poll();
				if (cur.r == N - 1 && cur.c == N - 1)
					break;
				int ct = dp[cur.r][cur.c];
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					if (nr == -1 || nc == -1 || nr == N || nc == N)
						continue;
					if (dp[nr][nc] != -1)
						continue;
					pq.add(new Point(nr, nc, ct + map[nr][nc]));
				}
			}

			sb.append("#" + tc + " " + dp[N - 1][N - 1] + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}