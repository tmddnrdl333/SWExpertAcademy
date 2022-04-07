package ps_SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1767_프로세서_연결하기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, max, min, totalCnt;
	static int[][] map;
	static List<int[]> list;

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<>();
			max = 0;
			min = Integer.MAX_VALUE;
			totalCnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 가장자리 코어는 스킵하자
					if ((i == 0 || i == N - 1 || j == 0 || j == N - 1) && map[i][j] == 1)
						continue;
					if (map[i][j] == 1) {
						list.add(new int[] { i, j });
						totalCnt++;
					}
				}
			}
			go(0, 0);

			sb.append("#" + tc + " " + min + "\n");

		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	// 부분집합으로 코어 전선놓기 시도
	// cCnt : 전원과 연결된 코어 수
	public static void go(int index, int cCnt) {

		// 기저 조건
		if (index == totalCnt) {
			int res = getLength();
			if (max < cCnt) {
				max = cCnt;
				min = res;
			} else if (max == cCnt) { // 최대 연결 코어수가 같다면,
				min = res < min ? res : min;
			}
			return;
		}

		int[] core = list.get(index);
		int r = core[0];
		int c = core[1];
		// 전선 놓아보기 (4방향으로)
		for (int d = 0; d < 4; d++) {
			if (isAvailable(r, c, d)) { // 현재 코어의 r,c 위치에서 d 방향으로 전선을 놓을 수 있다면
				setStatus(r, c, d, 2); // 전선 놓기
				go(index + 1, cCnt + 1);
				setStatus(r, c, d, 0); // 전선 지우기
			}
		}
		// 전선 놓지 않기
		go(index + 1, cCnt);
	}

	// r,c 위치에서 d 방향으로 전선을 놓을 수 있는지
	public static boolean isAvailable(int r, int c, int d) {
		int nr = r, nc = c;
		while (true) {
			nr += dr[d];
			nc += dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N)
				break;
			else if (map[nr][nc] > 0)
				return false;
		}
		return true;
	}

	// r,c 위치에서 d 방향으로 전선을 놓거나(2) 지우거나(0)
	public static void setStatus(int r, int c, int d, int s) {
		int nr = r, nc = c;
		while (true) {
			nr += dr[d];
			nc += dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N)
				break;
			map[nr][nc] = s;
		}
	}

	// 놓아진 전선의 길이의 합 계산
	public static int getLength() {
		int lCnt = 0;
		for (int r = 0; r < N; r++)
			for (int c = 0; c < N; c++)
				if (map[r][c] == 2)
					lCnt++;
		return lCnt;
	}
}

// 못 풀었습니다 ㅎㅎ,,
