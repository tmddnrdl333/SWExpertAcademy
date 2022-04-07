package ps_SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 93,368kb
// 502ms

public class Solution_5643_키_순서_LiveVer {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M;
	static int[][] aM;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			aM = new int[N + 1][N + 1];

			for (int i = 1; i <= N; i++)
				aM[i][0] = -1;

			int a, b;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				aM[a][b] = 1;
			}

			for (int i = 1; i <= N; i++) {
				// 탐색 전인 학생들만 탐색하도록
				if (aM[i][0] == -1)
					gtDFS(i);
			}

			// 자신보다 작은 학생 수 카운트
			for (int j = 1; j <= N; j++) {
				for (int i = 1; i <= N; i++) {
					aM[0][j] += aM[i][j];
				}
			}

			int answer = 0; // 자신의 키를 알 수 있는 학생 수
			for (int i = 1; i <= N; i++) {
				if (aM[i][0] + aM[0][i] == N - 1)
					answer++;
			}
			sb.append("#" + tc + " " + answer + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void gtDFS(int cur) {

		for (int i = 1; i <= N; i++) {
			if (aM[cur][i] != 0) { // 나보다 큰 학생이면

				if (aM[i][0] == -1)
					gtDFS(i); // 탐색 전이면 탐색하기

				if (aM[i][0] > 0) { // i보다 큰 학생이 있다면
					// 나보다 큰 학생이 알고있는 다른 학생과의 키 관계를 나와의 직접 관계로 맵핑
					// cur < i < ? 라면 cur < ? 로 만들자
					for (int j = 1; j <= N; j++) {
						if (aM[i][j] == 1) {
							aM[cur][j] = 1;
						}
					}
				}

			}
		}

		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			cnt += aM[cur][i];
		}
		aM[cur][0] = cnt;

	}

}