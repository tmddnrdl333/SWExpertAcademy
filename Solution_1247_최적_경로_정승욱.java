package algorithm_ws.swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 24,652kb
// 555ms

public class Solution_1247_최적_경로_정승욱 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[] seq;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine()); // 고객 수
			st = new StringTokenizer(br.readLine());
			int[][] posi = new int[N + 2][2];

			// 0: 회사, 1: 집, 2~N+1: 고객
			for (int i = 0; i < N + 2; i++) {
				posi[i][0] = Integer.parseInt(st.nextToken());
				posi[i][1] = Integer.parseInt(st.nextToken());
			}
			// 입력 끝

			// 인접 행렬 생성
			int[][] dist = new int[N + 2][N + 2];
			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < N + 2; j++) {
					if (i == j)
						continue;
					int distance = Math.abs(posi[i][0] - posi[j][0]) + Math.abs(posi[i][1] - posi[j][1]);
					dist[i][j] = distance;
					dist[j][i] = distance;
				}
			}

			// 0 회사, 1 집 제외 2~N+1 까지 순열을 다 돌려서, 간선들의 합을 구하고 회사와 집까지 포함한 값의 최소를 찾자
			// 경로 초기화
			seq = new int[N];
			for (int i = 0; i < N; i++)
				seq[i] = i + 2;
			// 순열 돌리면서 값 구하고 최소 찾기
			int resMin = Integer.MAX_VALUE;
			do {
				int partSum = 0;
				partSum += dist[0][seq[0]];
				for (int i = 1; i < N; i++)
					partSum += dist[seq[i - 1]][seq[i]];
				partSum += dist[seq[N - 1]][1];
				resMin = partSum < resMin ? partSum : resMin;
			} while (np());

			sb.append("#" + tc + " " + resMin + "\n");
		} // tc
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static boolean np() {
		// 1. 교환 위치 찾기
		int i = N - 1;
		while (i > 0 && seq[i - 1] >= seq[i])
			--i;
		if (i == 0)
			return false;

		// 2. 교환 위치에 교환할 값 찾기
		int j = N - 1;
		while (seq[i - 1] >= seq[j])
			--j;

		// 3. 교환 위치와 교환할 값 교환하기
		swap(i - 1, j);

		// 4. 교환 위치 뒤(꼭대기)부터 맨 뒤까지 만들 수 있는 가장 작은 순열 생성 (오름차순)
		int k = N - 1;
		while (i < k)
			swap(i++, k--);

		return true;

	}

	public static void swap(int i, int j) {
		int temp = seq[i];
		seq[i] = seq[j];
		seq[j] = temp;
	}
}
