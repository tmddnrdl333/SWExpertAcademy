package algorithm_hw;

import java.util.Scanner;

public class Solution_9229_한빈이와SpotMart_정승욱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		int[] N = new int[TC];
		int[] M = new int[TC];
		int[][] w = new int[TC][];
		// 입력
		for (int i = 0; i < TC; i++) {
			N[i] = sc.nextInt();
			M[i] = sc.nextInt();
			w[i] = new int[N[i]];
			for (int j = 0; j < N[i]; j++) {
				w[i][j] = sc.nextInt();
			}
		}
		sc.close();
		// 계산
		for (int i = 0; i < TC; i++) {
			int lim = M[i]; // 제한
			int max = 0; // 최대 무게를 넣을 칸
			for (int j = 0; j < N[i]; j++) {
				for (int k = j + 1; k < N[i]; k++) {
					int wSum = w[i][j] + w[i][k];
					if (wSum > lim || wSum <= max) {
						continue;
					} else {
						max = wSum;
					}
				}
			}
			if (max != 0)
				System.out.println("#" + (i + 1) + " " + max);
			else
				System.out.println("#" + (i + 1) + " " + -1);
		}
	}
}
