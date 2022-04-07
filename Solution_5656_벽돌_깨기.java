package ps_SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 25,216kb
// 192ms

public class Solution_5656_벽돌_깨기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, W, H;
	static int[][] map, copy;
	static int min;

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			copy = new int[H][W];
			min = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			// 순서 완탐
			// 맵 복사해와서 테스트하고 결과 기록하기
			int last = (int) Math.pow(W, N);
			for (int seq = 0; seq < last; seq++) {
				int digit = seq;
				copyMap();
				// copy에 시행
				for (int i = 0; i < N; i++) {
					go(digit % W);
					digit /= W;
				}
				// 결과 기록
				int res = count();
				min = res < min ? res : min;
				if (min == 0)
					break;
			}
			sb.append("#" + tc + " " + min + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static void copyMap() {
		for (int i = 0; i < H; i++)
			for (int j = 0; j < W; j++)
				copy[i][j] = map[i][j];
	}

	public static void go(int digit) {
		int row = H - 1;
		for (; row >= 0; row--) {
			if (copy[row][digit] == 0)
				break;
		}
		++row;
		if (row == H)
			return;
		explode(row, digit);
		gravity();
	}

	public static void explode(int r, int c) {
		int val = copy[r][c];
		copy[r][c] = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r;
			int nc = c;
			for (int l = val; l > 1; l--) {
				nr += dr[d];
				nc += dc[d];
				if (nr < 0 || nc < 0 || nr >= H || nc >= W)
					break;
				explode(nr, nc);
			}
		}
	}

	public static void gravity() {
		for (int j = 0; j < W; j++)
			for (int i = H - 2; i >= 0; i--)
				if (copy[i][j] != 0 && copy[i + 1][j] == 0) {
					// 내려갈 수 있는 만큼 내려가기
					int ni = i;
					while (true) {
						ni++;
						if (ni >= H || copy[ni][j] != 0) {
							copy[ni - 1][j] = copy[i][j];
							copy[i][j] = 0;
							break;
						}
					}
				}
	}

	public static int count() {
		int cnt = 0;
		for (int i = 0; i < H; i++)
			for (int j = 0; j < W; j++)
				if (copy[i][j] != 0)
					cnt++;
		return cnt;
	}
}