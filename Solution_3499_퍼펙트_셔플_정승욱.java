package algorithm_ws.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3499_퍼펙트_셔플_정승욱 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] N = new int[T]; // 총 카드 수
		String[][] card = new String[T][]; // 테케별 카드 배열
		for (int i = 0; i < T; i++) {
			N[i] = Integer.parseInt(br.readLine());
			card[i] = new String[N[i]];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N[i]; j++) {
				card[i][j] = st.nextToken();
			}
		}
		// 입력 끝
		//
		for (int i = 0; i < T; i++) {
			int num = N[i]; // 총 카드 수
			int d1 = (num + 1) / 2; // 많은 반
			int d2 = num - d1; // 나머지 적은 반
			String[] D1 = new String[d1];
			String[] D2 = new String[d2];
			for (int j = 0; j < d1; j++) {
				D1[j] = card[i][j];
			}
			for (int j = 0; j < d2; j++) {
				D2[j] = card[i][j + d1];
			}
			for (int j = 0; j < num; j++) {
				card[i][j] = (j % 2 == 0) ? D1[j / 2] : D2[j / 2];
			}
		}
		// 출력
		for (int i = 0; i < T; i++) {
			System.out.print("#" + (i + 1) + " ");
			for (int j = 0; j < N[i]; j++) {
				System.out.print(card[i][j] + " ");
			}
			System.out.println();
		}
	}
}
