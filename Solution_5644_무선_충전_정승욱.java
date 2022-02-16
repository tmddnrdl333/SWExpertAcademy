package algorithm_ws.swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 27,792kb
// 195ms

public class Solution_5644_무선_충전_정승욱 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int A; // 충전소 개수
	static int[] Amove, Bmove;
	static int[][] map;
	static int[] P; // 충전소 성능
	// 0:이동x 1:상 2:우 3:하 4:좌
	static int[][] move = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int Ar, Ac, Br, Bc;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine()); // test case 수
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 이동 시간 0~M 까지 M+1개
			A = Integer.parseInt(st.nextToken()); // 충전소 수 A개
			Amove = new int[M]; // A 이동
			Bmove = new int[M]; // B 이동
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				Amove[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				Bmove[i] = Integer.parseInt(st.nextToken());
			}
			// 맵 구성
			map = new int[11][11];
			P = new int[A]; // 충전소별 성능
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken()); // Coverage
				P[i] = Integer.parseInt(st.nextToken()); // Perfomance
				int size = 0;
				for (int r = y - C; r <= y + C; r++) {
					if (r > 0 && r < 11)
						for (int c = x - size; c <= x + size; c++) {
							if (c < 1 || c > 10)
								continue;
							map[r][c] |= 1 << i; // 충전소 1은 0001, 충전소 2는 0010, 둘 다 있으면 0011
						}
					if (r < y)
						size++;
					else
						size--;
				}
			}
			// 입력 끝

			//
			// 테케별 위치 초기화
			Ar = 1;
			Ac = 1;
			Br = 10;
			Bc = 10;
			// M초 동안 각 움직임에 대해 충전량 계산
			int charged = 0;
			for (int i = 0; i <= M; i++) {
				// 현재 위치에 대한 충전 값 반환,
				// 현재 시간에 대한 누적 충전 값 갱신
				charged += chargeResult();
				if (i == M)
					break;
				// 다음 위치로 변경
				nextPosi(i);
			}
			sb.append("#" + tc + " " + charged + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	// 이동 (벽 부딛히는 경우 없다는 조건)
	public static void nextPosi(int i) {
		int am = Amove[i];
		int bm = Bmove[i];
		Ar += move[am][0];
		Ac += move[am][1];
		Br += move[bm][0];
		Bc += move[bm][1];
	}

	public static int chargeResult() {
//		map[Ar][Ac]
//		map[Br][Bc] 에 대하여 값을 확인하고...
//		둘 다 0인 경우 그냥 0 반환
//		둘 중 하나만 0 아닌 경우, 그것의 가장 큰 성능 반환
//		둘 다 0이 아닌 경우...
//			둘 다 비트가 하나면서 같은 경우 무조건 나눠가지지만, 합하면 어차피 그 값 반환
//		
		int aflag = map[Ar][Ac];
		int bflag = map[Br][Bc];
		if (aflag == 0 && bflag == 0) {// 둘 다 0인 경우 0 충전
			return 0;
		} else if (aflag == 0 || bflag == 0) { // 하나만 0 아닌 경우, 가능한 최고 성능의 충전소에서 충전
			int flag = aflag + bflag;
			int maxCharge = 0;
			for (int i = 0; i < A; i++) {
				if ((flag & 1 << i) != 0) {
					int charge = P[i];
					maxCharge = charge > maxCharge ? charge : maxCharge;
				}
			}
			return maxCharge;
		} else { // 둘 다 0 아닌 경우...
			if (aflag == bflag && chargerCnt(aflag) == 1) { // 둘이 완전 같으면서 비트가 하나 뿐이면 나눠먹기
				for (int i = 0; i < A; i++)
					if ((aflag & 1 << i) != 0)
						return P[i];
			} else { // 그게 아니면... 우선 A와 B 각각 1위, 2위 고성능 충전량과 그 인덱스 알아놓기
				int[][] aps = new int[A][2];
				for (int i = 0; i < A; i++) {
					if ((aflag & 1 << i) != 0) {
						aps[i][0] = P[i];
						aps[i][1] = i;
					}
				}
				int[][] bps = new int[A][2];
				for (int i = 0; i < A; i++) {
					if ((bflag & 1 << i) != 0) {
						bps[i][0] = P[i];
						bps[i][1] = i;
					}
				}
				Arrays.sort(aps, (o1, o2) -> o2[0] - o1[0]);
				Arrays.sort(bps, (o1, o2) -> o2[0] - o1[0]);
				if (aps[0][1] != bps[0][1]) { // 둘의 1위 충전소가 인덱스가 다르다면 합해서 반환
					return aps[0][0] + bps[0][0];
				} else { // 둘의 1위 충전소가 인덱스가 같다면, 2위 충전소 둘 중 고성능에 합해서 반환
					int sum = aps[0][0];
					int tmp = Math.max(aps[1][0], bps[1][0]);
					return sum + tmp;
				}
			}
		}
		return 1000000;
	}

	public static int chargerCnt(int flag) {
		int cnt = 0;
		for (int i = 0; i < A; i++) {
			if ((flag & 1 << i) != 0)
				cnt++;
		}
		return cnt;
	}
}
