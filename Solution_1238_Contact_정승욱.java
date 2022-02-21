package algorithm_hw.swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 19,312kb
// 115ms

public class Solution_1238_Contact_정승욱 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken()) - 1;

			st = new StringTokenizer(br.readLine());
			boolean[][] adjArr = new boolean[100][100];
			for (int i = 0; i < N; i += 2) {
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				adjArr[from][to] = true;
			}

			// BFS
			boolean[] isVisit = new boolean[100];
			Queue<Integer> q = new LinkedList<>();
			Queue<Integer> res = new LinkedList<>();
			q.add(V);
			isVisit[V] = true;
			while (!q.isEmpty()) {
				res.clear();
				int siblings = q.size();
				for (; siblings > 0; siblings--) {
					int cur = q.poll();
					res.add(cur);
					for (int i = 0; i < 100; i++) {
						if (!isVisit[i] && adjArr[cur][i]) {
							isVisit[i] = true;
							q.add(i);
						}
					}
				}
			}

			int max = 0;
			for (int num : res) {
				max = num > max ? num : max;
			}

			sb.append("#" + tc + " " + (max + 1) + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}
}
