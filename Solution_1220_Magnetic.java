package study_0224;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_1220_Magnetic {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			} // map 구성 끝
			int cnt = 0;
			for (int c = 0; c < N; c++) {
				boolean flag = true; //
				for (int r = 0; r < N; r++) {
					if (flag) { // 2는 패스, 1만나면 2 찾기
						if (map[r][c] == 1)
							flag = false;
					} else {
						if (map[r][c] == 2) {
							cnt++;
							flag = true;
						}
					}
				}
			}
			sb.append("#" + tc + " " + cnt + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
