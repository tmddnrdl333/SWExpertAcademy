package algorithm_ws.swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 24864kb
// 1415ms

public class Solution_6808_규영이와_인영이의_카드게임 {
	static int[] arr = new int[9];
	static int[] brr;
	static int N = 9;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			boolean[] isSel = new boolean[19];
			for (int i = 0; i < 9; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				isSel[arr[i]] = true;
			}
			brr = new int[9];
			int cnt = 0;
			for (int i = 1; i < 19; i++) {
				if (isSel[i])
					continue;
				brr[cnt++] = i;
			}
			int awin = 0, bwin = 0;
			do {
				int a = 0, b = 0;
				// 누가 이기는지
				for (int i = 0; i < 9; i++) {
					if (arr[i] > brr[i])
						a += arr[i] + brr[i];
					else
						b += arr[i] + brr[i];
				}
				if (a > b)
					awin++;
				else
					bwin++;
			} while (np());
			sb.append("#" + tc + " " + awin + " " + bwin + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static boolean np() {

		// 1. 교환 위치 찾기
		int i = N - 1;
		while (i > 0 && brr[i - 1] >= brr[i])
			--i;
		if (i == 0)
			return false;
		// 2. 교환 위치에 교환할 값 찾기
		int j = N - 1;
		while (brr[i - 1] >= brr[j])
			--j;

		// 3. 교환 위치와 교환할 값 교환하기
		swap(i - 1, j);

		// 4. 교환 위치 뒤(꼭대기)부터 맨 뒤까지 만들 수 있는 가장 작은 순열 생성(오름차순 정렬)
		int k = N - 1;
		while (i < k) {
			swap(i++, k--);
		}

		return true;
	}

	public static void swap(int i, int j) {
		int temp = brr[i];
		brr[i] = brr[j];
		brr[j] = temp;
	}
}
