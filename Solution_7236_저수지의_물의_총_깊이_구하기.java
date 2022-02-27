package study_0224;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_7236_저수지의_물의_총_깊이_구하기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					if (str.charAt(2 * j) == 'W') {
						map[i][j] = 1;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1)
						for (int dir = 0; dir < 8; dir++) {
							int di = i + dr[dir];
							int dj = j + dc[dir];
							if (di == -1 || dj == -1 || di == N || dj == N)
								continue;
							if (map[di][dj] > 0)
								map[i][j]++;
						}
				}
			}
			int max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					max = map[i][j] > max ? map[i][j] : max;
				}
			}
			max = max > 1 ? max - 1 : max;
			sb.append("#" + tc + " " + max + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}