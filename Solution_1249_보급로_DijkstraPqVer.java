package ps_SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

// 35,916kb
// 166ms

public class Solution_1249_보급로_DijkstraPqVer {

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[][] map;
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				char[] temp = br.readLine().toCharArray();
				for (int j = 0; j < N; j++)
					map[i][j] = temp[j] - '0';
			}

			int answer = dijkstra(0, 0);
			sb.append("#" + tc + " " + answer + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int dijkstra(int startR, int startC) {
		boolean[][] visited = new boolean[N][N];
		int[][] minTime = new int[N][N]; // 출발지에서 자신까지의 최소 복구시간 (계속 갱신 될)

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minTime[i][j] = INF;
			}
		}

		// 비용의 최소힙
		PriorityQueue<int[]> pQueue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});

		minTime[startR][startC] = 0;
		pQueue.offer(new int[] { startR, startC, 0 });

		int r, c, minCost, nr, nc, current[];
		while (true) {
			current = pQueue.poll(); // pQueue 안의 정점 중 출발지에서 자신으로의 비용이 최소인 정점의 정보
			r = current[0];
			c = current[1];
			minCost = current[2];

			if (visited[r][c])
				continue;

			visited[r][c] = true;
			if (r == N - 1 && c == N - 1) // 도착했다면 끝내기
				return minCost;

			// 현 정점의 인접정점 들여다보며 최소비용 갱신
			for (int d = 0; d < 4; d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				if (nr == -1 || nc == -1 || nr == N || nc == N)
					continue;
				if (minTime[nr][nc] > minCost + map[nr][nc]) {
					minTime[nr][nc] = minCost + map[nr][nc];
					pQueue.offer(new int[] { nr, nc, minTime[nr][nc] });
				}

			}

		}
	}
}