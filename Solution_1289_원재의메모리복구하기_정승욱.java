package algorithm_ws.swea;

import java.util.Scanner;

public class Solution_1289_원재의메모리복구하기_정승욱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		String[] str = new String[T];
		for (int i = 0; i < T; i++) {
			str[i] = sc.next();
		}
		sc.close();
		//
		for (int i = 0; i < T; i++) {
			int cnt = 0;
			String tmp = str[i];
			for (int j = 0; j < tmp.length(); j++) {
				if (tmp.charAt(j) - '0' == cnt % 2)
					continue;
				else
					cnt++;
			}
			System.out.println("#" + (i + 1) + " " + cnt);
		}
	}
}
