package ps_SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 24,964kb
// 129ms

public class Solution_1953_탈주범_검거 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] visit;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 }; // 상 우 하 좌
	static int[] type = { 0, 15, 5, 10, 3, 6, 12, 9 }; // type별 갈 수 있는 곳에 bit masking한 값

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visit = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int cur = Integer.parseInt(st.nextToken());
					map[i][j] = type[cur];
				}
			}

			int time = 1;
			Queue<int[]> q = new LinkedList<>();
			visit[R][C] = true;
			int cnt = 1;
			q.add(new int[] { R, C });

			while (!q.isEmpty()) {
				if (time == L)
					break;
				time++;
				int size = q.size();
				for (; size != 0; size--) {
					int[] cur = q.poll();
					int cType = map[cur[0]][cur[1]];
					for (int d = 0; d < 4; d++) {
						if ((cType & 1 << d) != 0) {
							int nr = cur[0] + dr[d];
							int nc = cur[1] + dc[d];
							if (nr == N || nc == M || nr == -1 || nc == -1)
								continue;
							if (visit[nr][nc])
								continue;
							// 목적지에서도 나에게 연결 되는지
							int nType = map[nr][nc];
							if ((nType & 1 << (d + 2) % 4) == 0) // 상 우 하 좌 니까 +2)%4
								continue;

							visit[nr][nc] = true;
							cnt++;
							q.add(new int[] { nr, nc });
						}
					}

				}
			}
			sb.append("#" + tc + " " + cnt + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}