package algorithm_ws.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1228_암호문1_정승욱 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 0; tc < 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			List<Integer> code = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				code.add(Integer.parseInt(st.nextToken()));
			}
			int C = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[][] command = new int[C][];
			for (int i = 0; i < C; i++) {
				st.nextToken();
				int commandI0 = Integer.parseInt(st.nextToken()); // 어느위치에
				int commandI1 = Integer.parseInt(st.nextToken()); // 몇 개
				command[i] = new int[commandI1 + 2];
				command[i][0] = commandI0;
				command[i][1] = commandI1;
				for (int j = 0; j < command[i][1]; j++) {
					command[i][2 + j] = Integer.parseInt(st.nextToken());
				}
			}
			// 입력 끝
			// 연산
			for (int i = 0; i < C; i++) {
				List<Integer> tmp = new LinkedList<>();
				for (int j = 0; j < command[i][1]; j++) {
					tmp.add(command[i][2 + j]);
				}
				code.addAll(command[i][0], tmp);
			}
			// 출력
			System.out.print("#" + (tc + 1));
			for (int j = 0; j < 10; j++) {
				System.out.print(" " + code.get(j));
			}
			System.out.println();
		}
	}
}
