package algorithm_hw;

import java.util.Scanner;
import java.util.Stack;

public class Solution_1218_괄호짝짓기_정승욱 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder res = new StringBuilder();
		int[] n = new int[10];
		String[] str = new String[10];
		for (int i = 0; i < 10; i++) {
			n[i] = sc.nextInt();
			str[i] = sc.next();
		}
		sc.close();
		outer: for (int i = 0; i < 10; i++) {
			Stack<Integer> st = new Stack<>();
			for (int j = 0; j < n[i]; j++) {
				char tmp = str[i].charAt(j);
				if (tmp == '{')
					st.push(1);
				else if (tmp == '[')
					st.push(2);
				else if (tmp == '(')
					st.push(3);
				else if (tmp == '<')
					st.push(4);
				else if (!st.isEmpty()) {
					int tmp2 = st.peek();
					if (tmp == '}' && tmp2 == 1)
						st.pop();
					else if (tmp == ']' && tmp2 == 2)
						st.pop();
					else if (tmp == ')' && tmp2 == 3)
						st.pop();
					else if (tmp == '>' && tmp2 == 4)
						st.pop();
					else {
						res.append("#"+(i+1)+" 0\n");
						continue outer;
					}
				} else {
					res.append("#"+(i+1)+" 0\n");
					continue outer;
				}
			}
			if (st.isEmpty())
				res.append("#"+(i+1)+" 1\n");
		}
		System.out.println(res);

	}

}
