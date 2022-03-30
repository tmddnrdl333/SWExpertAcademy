package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3124_최소_스패닝_트리 {

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int V, E;
	static Edge[] edgeList;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edgeList = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(from, to, weight);
			}
			Arrays.sort(edgeList);
			makeSet();

			long result = 0, cnt = 0;
			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					result += edge.weight;
					if (++cnt == V - 1)
						break;
				}
			}
			sb.append("#" + tc + " " + result + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	// ## union-find
	static int[] parents;

	// 초기 설정
	public static void makeSet() {
		parents = new int[V];
		// 자신의 부모노드를 자신의 값으로 세팅
		for (int i = 0; i < V; i++)
			parents[i] = i;
	}

	// a의 집합 찾기 : a의 대표자 찾기
	public static int findSet(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = findSet(parents[a]); // path compression
	}

	// a, b 두 집합 합치기 (a를 b밑에 넣기)
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;

		// a밑에 b를 넣기 (b짱의 부모를 a짱으로 설정)
		parents[bRoot] = aRoot;
		return true;
	}
	// ## union-find
}