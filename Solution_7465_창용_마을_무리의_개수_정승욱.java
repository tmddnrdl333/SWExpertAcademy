package algorithm_hw.swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 26,332kb
// 137ms

public class Solution_7465_창용_마을_무리의_개수_정승욱 {
	static int N;
	static int[] person;

	// 생성
	public static void makeSet() {
		person = new int[N];
		for (int i = 0; i < N; i++)
			person[i] = i;
	}

	// 대표자 찾기
	public static int findSet(int a) {
		if (a == person[a])
			return a;
		return person[a] = findSet(person[a]);
	}

	// 합치기
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;
		person[bRoot] = aRoot;
		return true;
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			// N명 개체 만듬
			makeSet();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				union(a, b);
			}

			for (int i = 0; i < N; i++)
				findSet(i);
			Set<Integer> set = new HashSet<>();
			for (int i = 0; i < N; i++)
				set.add(person[i]);
			sb.append("#" + tc + " " + set.size() + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
