package algorithm_ws.swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 30,240kb
// 166ms

public class Solution_4012_요리사_정승욱 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[] pick;
	static int[][] S;

	static int min;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine()); // N개의 식재료
			S = new int[N][N]; // Sij는 i와 j의 시너지
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 입력 끝

			// 대칭으로 합치기
			// i와 j를 썼으면 Sij만 더하면 되게 함
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					S[i][j] += S[j][i];
				}
			}

			// N 개 중 N/2개를 골라야됨 (조합)
			// 2개의 N/2개 무리를 각각 맛을 구해야됨 (반복문)
			// 그 차를 구하고 min값을 찾아야됨
			pick = new int[N / 2]; // 반만 고른 배열
			min = Integer.MAX_VALUE;
			combi(0, 0);
			sb.append("#" + tc + " " + min + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static void combi(int cnt, int start) {
		// 기본
		if (cnt == N / 2) {
			int[] nopick = new int[N / 2];
			int noflag = (1 << N) - 1;
			for (int i = 0; i < N / 2; i++) {
				noflag -= 1 << pick[i];
			}
			int ncnt = 0;
			for (int i = 0; i < N; i++) {
				if ((noflag & 1 << i) != 0) {
					nopick[ncnt] = i;
					ncnt++;
				}
				if (ncnt == N / 2)
					break;
			}
			int res = Math.abs(taste(pick) - taste(nopick));
//			System.out.println(res);
			min = res < min ? res : min;
			return;
		}
		// 유도
		for (int i = start; i < N; i++) {
			pick[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}

	public static int taste(int[] pick) {
		// pick에 있는건 index, 그 index에 해당하는 맛 시너지들을 모두 더한다
		int sum = 0;
		for (int i = 0; i < N / 2 - 1; i++) {
			for (int j = i + 1; j < N / 2; j++) {
				int big = Math.max(pick[i], pick[j]);
				int small = pick[i] + pick[j] - big;
				sum += S[small][big];
			}
		}
		return sum;
	}
}
