package live0228;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_교환학생 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			int n = Integer.parseInt(br.readLine());

			int[] arr = new int[7];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 7; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int min = Integer.MAX_VALUE;
			for (int start = 0; start < 7; start++) {
				// 수업이 시작하는 모든 요일 처리
				if (arr[start] == 0)
					continue;
				int day = start, cnt = 0;
				while (true) {
					if (arr[day % 7] == 1)
						cnt++;
					day++;
					if (cnt == n) {
						min = day - start < min ? day - start : min;
						break;
					}

				}
			}
			sb.append("#" + tc + " " + min + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}