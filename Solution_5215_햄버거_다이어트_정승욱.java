package algorithm_ws.swea;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution_5215_햄버거_다이어트_정승욱 {
	static int max = 0;
	static int limit = 0;
	static List<Integer> ti = new ArrayList<>();
	static List<Integer> ki = new ArrayList<>();
	static List<Boolean> isSel = new ArrayList<>();
	static int res = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] N = new int[T];
		int[] L = new int[T];
		int[][] Ti = new int[T][];
		int[][] Ki = new int[T][];
		// 입력
		for (int i = 0; i < T; i++) {
			N[i] = sc.nextInt();
			L[i] = sc.nextInt();
			Ti[i] = new int[N[i]];
			Ki[i] = new int[N[i]];
			for (int j = 0; j < N[i]; j++) {
				Ti[i][j] = sc.nextInt();
				Ki[i][j] = sc.nextInt();
			}
		}
		sc.close();
		// 계산
		for (int i = 0; i < T; i++) {
			ti.clear();
			ki.clear();
			isSel.clear();
			res = 0;
			max = N[i];
			limit = L[i];
			for (int j = 0; j < max; j++) {
				ti.add(Ti[i][j]);
				ki.add(Ki[i][j]);
				isSel.add(false);
			}
			calc(0);
			System.out.println("#" + (i + 1) + " " + res);
		}

	}

	public static void calc(int cnt) {
		if (cnt == max) {
			int score = 0;
			int cal = 0;
			for (int i = 0; i < max; i++) {
				if (isSel.get(i)) {
					if (cal + ki.get(i) <= limit)
						cal += ki.get(i);
					else
						break;
					score += ti.get(i);
				}
			}
			res = (score > res) ? score : res;
			return;
		}
		isSel.set(cnt, true);
		calc(cnt + 1);
		isSel.set(cnt, false);
		calc(cnt + 1);

	}

}
