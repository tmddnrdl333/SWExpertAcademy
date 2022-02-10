package algorithm_ws.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1233_사칙연산_유효성_검사_정승욱 {
	static int N;
	static int[] tArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			int res = -1;
			N = Integer.parseInt(br.readLine());
			tArr = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();
				int tmp1 = (int) st.nextToken().charAt(0);
				tArr[i] = (tmp1 < '0') ? 0 : 1; // 수식이면0 숫자면1
				try {
					st.nextToken();
					st.nextToken();
				} catch (Exception e) {
					if (tArr[i] == 0) {
						res = 0; // 자식이 없는데 숫자도 아니면 할 필요도 없다.
					}
				}
			}
			// 입력 끝
			if (res != 0) { // 할 필요도 없다.
				res = testValid(1);
			} // if

			System.out.println("#" + tc + " " + res);
		}
	}

	public static int testValid(int idx) {
		if (tArr[idx] == 0) { // 수식이라면 자식 둘이 유효한지 본다.
			if (testValid(idx * 2) + testValid(idx * 2 + 1) == 2) {
				return 1;
			}
			return 0;
		} else { // 숫자라면 자식이 없어야 한다.
			if (idx * 2 > N)
				return 1;
			return 0;
		}
	}

}
