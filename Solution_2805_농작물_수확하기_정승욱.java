package algorithm_ws.swea;

import java.util.Scanner;

public class Solution_2805_농작물_수확하기_정승욱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] N = new int[T]; // 테케별 농장 크기
		int[][][] map = new int[T][][]; // 테케별 2차원 농장 맵
		for (int i = 0; i < T; i++) {
			N[i] = sc.nextInt();
			map[i] = new int[N[i]][N[i]];
			for (int j = 0; j < N[i]; j++) {
				String s = sc.next();
				for (int k = 0; k < N[i]; k++) {
					map[i][j][k] = s.charAt(k) - '0';
				}
			}
		}
		sc.close();
		// 입력 끝
		for (int i = 0; i < T; i++) {
			int size = N[i];
			int sum = 0;
			for (int j = 0; j <= size / 2; j++) { // 반 행
				for (int k = size / 2 - j; k <= size / 2 + j; k++) { // 열
					sum += map[i][j][k];
				}
			}
			for (int j = 1; j <= size / 2; j++) { // 남은 반 행
				for (int k = 0 + j; k < size - j; k++) {
					sum += map[i][j + size / 2][k];
				}
			}
			System.out.println("#" + (i + 1) + " " + sum);
		} // T
	}
}
