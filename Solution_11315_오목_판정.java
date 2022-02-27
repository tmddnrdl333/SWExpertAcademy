package study_0224;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_11315_오목_판정 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static char[][] arr;
	static boolean res = false;

	static int[] di = { 1, 1, 1, 0 };
	static int[] dj = { -1, 0, 1, 1 };

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			res = false;
			N = Integer.parseInt(br.readLine());
			arr = new char[N][N];
			for (int i = 0; i < N; i++)
				arr[i] = br.readLine().toCharArray();
			//

			o: for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 'o')
						fourway(i, j);
					if (res)
						break o;
				}
			}

			//
			sb.append("#" + tc + " ");
			if (res)
				sb.append("YES\n");
			else
				sb.append("NO\n");

		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static void fourway(int i, int j) {
		for (int dir = 0; dir < 4; dir++) {
			int ni = i + di[dir];
			int nj = j + dj[dir];
			if (ni == -1 || nj == -1 || ni == N || nj == N) {
				continue;
			}
			if (arr[ni][nj] == 'o') {
				int cnt = 0;
				for (int k = 1; k < 4; k++) {
					int nni = ni + k * di[dir];
					int nnj = nj + k * dj[dir];
					if (nni == -1 || nnj == -1 || nni == N || nnj == N) {
						break;
					}
					if (arr[nni][nnj] == 'o')
						cnt++;

				}
				if (cnt == 3) {
					res = true;
					return;
				}
			}
		}
	}
}