package algorithm_ws;

import java.util.Scanner;

public class Solution_1954_달팽이숫자_정승욱 {
	static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] N = new int[T];
		for (int i = 0; i < T; i++) {
			N[i] = sc.nextInt();
		}
		sc.close();
		// 계산
		for (int i = 0; i < T; i++) {
			int[][] arr = new int[N[i]][N[i]];
			int x = 0;
			int y = 0;
			int s = 0;
			for (int j = 1; j <= N[i] * N[i]; j++) {
				arr[y][x] = j;
				int nx = x + dir[s][0];
				int ny = y + dir[s][1];
				if (nx == N[i] || nx == -1 || ny == N[i] || ny == -1) {
					s = (s + 1) % 4;
					x = x + dir[s][0];
					y = y + dir[s][1];
					continue;
				} else if (arr[ny][nx] != 0) {
					s = (s + 1) % 4;
					x = x + dir[s][0];
					y = y + dir[s][1];
					continue;
				}
				x = nx;
				y = ny;
			}
			System.out.println("#" + (i + 1));
			for (int j = 0; j < N[i]; j++) {
				for (int k = 0; k < N[i]; k++) {
					System.out.print(arr[j][k] + " ");
				}
				System.out.println();
			}
		}

	}
}
