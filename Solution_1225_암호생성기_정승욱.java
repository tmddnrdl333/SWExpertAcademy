package algorithm_ws;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_1225_암호생성기_정승욱 {
	static int[][] arr = new int[10][8];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 입력
		for (int i = 0; i < 10; i++) {
			int tmp = sc.nextInt();
			for (int j = 0; j < 8; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		// 계산
		for (int i = 0; i < 10; i++) {
			Queue<Integer> queue = new LinkedList<>();
			for (int j = 0; j < 8; j++) {
				queue.offer(arr[i][j]);
			}

			int minus = 1;
			while (true) {
				int tmp = queue.poll();
				if (tmp - minus > 0) {
					queue.offer(tmp - minus);
					minus = minus % 5 + 1;
					continue;
				} else {
					queue.offer(0);
					break;
				}
			}
			System.out.print("#" + (i + 1));
			for (int k = 0; k < 8; k++) {
				System.out.print(" " + queue.poll());
			}
			System.out.println();
		}
	}
}
