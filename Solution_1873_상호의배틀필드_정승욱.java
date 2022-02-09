package algorithm_ws.swea;

import java.util.Scanner;

public class Solution_1873_상호의배틀필드_정승욱 {
	static int r, c, dir; // 탱크의 행, 열, 방향
	static int H, W;
	static char[][] map;
	static int[][] dc = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			H = sc.nextInt();
			W = sc.nextInt();
			map = new char[H][W];
			for (int j = 0; j < H; j++) {
				map[j] = sc.next().toCharArray();
			}
			int N = sc.nextInt();
			char[] input = new char[N];
			input = sc.next().toCharArray();

			//
			// 탱크의 위치와 방향을 입력해놓자.
			getTankPoDi(i);
			// 탱크 위치와 방향
			// 커맨드 입력
			for (int j = 0; j < N; j++) {
				char tmp = input[j];
				if (tmp == 'S') {
					shoot(i);
				} else if (tmp == 'L') {
					dir = 0;
					go(i);
				} else if (tmp == 'U') {
					dir = 1;
					go(i);
				} else if (tmp == 'R') {
					dir = 2;
					go(i);
				} else if (tmp == 'D') {
					dir = 3;
					go(i);
				}

			}
			char tmp = '1';
			if (dir == 0)
				tmp = '<';
			if (dir == 1)
				tmp = '^';
			if (dir == 2)
				tmp = '>';
			if (dir == 3)
				tmp = 'v';
			map[r][c] = tmp;

			System.out.print("#" + (i + 1) + " ");
			for (int j = 0; j < H; j++) {
				for (int k = 0; k < W; k++)
					System.out.print(map[j][k]);
				System.out.println();
			}

		}
		sc.close();

	}

	public static void getTankPoDi(int i) {
		for (int j = 0; j < H; j++) {
			for (int k = 0; k < W; k++) {
				int tmp = map[j][k];
				if (tmp == '<' || tmp == '^' || tmp == '>' || tmp == 'v') {
					r = j;
					c = k;
					map[j][k] = '.';
					if (tmp == '<') {
						dir = 0;
					} else if (tmp == '^') {
						dir = 1;
					} else if (tmp == '>') {
						dir = 2;
					} else if (tmp == 'v') {
						dir = 3;
					}
				}
			}
		}
	}

	public static void shoot(int i) {
		int nr = r;
		int nc = c;

		while (true) {
			nr += dc[dir][0];
			nc += dc[dir][1];
			if (nr == -1 || nr == H || nc == -1 || nc == W) {
				break;
			} else {
				char tmp = map[nr][nc];
				if (tmp == '.' || tmp == '-')
					continue;
				else if (tmp == '#')
					break;
				else if (tmp == '*') {
					map[nr][nc] = '.';
					break;
				}
			}

		}

	}

	public static void go(int i) {
		int nr = r + dc[dir][0];
		int nc = c + dc[dir][1];
		if (nr == -1 || nr == H || nc == -1 || nc == W) {
			return;
		} else if (map[nr][nc] == '.') {
			r = nr;
			c = nc;
		} else
			return;
	}
}
