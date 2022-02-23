package algorithm_ws.swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 48,400kb
// 217ms

// prim

public class Solution_1251_하나로 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][2];
			st = new StringTokenizer(br.readLine()); // x좌표
			for (int i = 0; i < N; i++)
				map[i][0] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine()); // y좌표
			for (int i = 0; i < N; i++)
				map[i][1] = Integer.parseInt(st.nextToken());
			double E = Double.parseDouble(br.readLine()); // 세율
			// 입력 끝

			//
			long[][] adjMatrix = new long[N][N];
			long[] minEdge = new long[N]; // 타 정점에서 자신으로 간선 비용 중 최소 비용 저장
			boolean[] visited = new boolean[N]; // 우리 무리에 껴있는지
			// adjMatrix 생성
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					adjMatrix[i][j] = (long) (Math.pow(map[i][0] - map[j][0], 2) + Math.pow(map[i][1] - map[j][1], 2));
				}
				minEdge[i] = Long.MAX_VALUE;
			}

			long result = 0; // MST비용
			minEdge[0] = 0; // 자신으로는 0

			for (int i = 0; i < N; i++) { // N개 정점 모두 연결해보자
				// 신장트리 연결되지 않은 정점 중 가장 유리한 비용의 정점 선택
				long min = Long.MAX_VALUE;
				int minVertex = 0;

				for (int j = 0; j < N; j++) {
					if (!visited[j] && min > minEdge[j]) {
						min = minEdge[j];
						minVertex = j;
					}
				}

				// 선택된 정점을 신장트리에 포함시킴
				visited[minVertex] = true;
				result += min;

				// 선택된 정점 기준으로 신장트리 포함되지 않은 다른 정점으로의 간선 비용을 따져보고 최소값 갱신
				for (int j = 0; j < N; j++) {
					if (!visited[j] && minEdge[j] > adjMatrix[minVertex][j]) {
						minEdge[j] = adjMatrix[minVertex][j];
					}
				}
			}
			sb.append("#" + tc + " ").append(Long.toString(Math.round(result * E))).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
