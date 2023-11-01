import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 11668KB, 76ms	
public class B1111 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		String ans = String.valueOf(num[N - 1]);
		if(N > 2) {
			// 1��° ���� & 2��° ���� ���� ��� �ڿ� �ٸ��� B
			if(num[0] == num[1]) {
				for (int i = 2; i < num.length; i++) {
					if(num[i] != num[i - 1]) {
						ans = "B";
						break;
					}
				}
			}
			// 1��° ���� & 2��° ���� �ٸ� ���
			else {
				// 2��° ���� & 3��° ���� ���� ��� �ڿ� �ٸ��� B 
				if(num[1] == num[2]) {
					for (int i = 3; i < N; i++) {
						if(num[i] != num[i - 1]) {
							ans = "B";
							break;
						}
					}
				}
				// 2��° ���� & 3��° ���� �ٸ� ���
				else {
					double check = ((double) (num[1] - num[2])) / (num[0] - num[1]);
					// A�� ������ ���
					if(Math.floor(check) == check) {
						int A = (int) check;
						int B = num[2] - num[1] * A;
						// ���� A, B�� ���� ���� �� ���� ��� B
						for (int i = 3; i < N; i++) {
							if(A * num[i - 1] + B != num[i]) {
								ans = "B";
								break;
							}
						}
						// ��δ� ���� �� ������ A * ���� �� + B
						if(!ans.equals("B")) ans = String.valueOf(A * num[N - 1] + B);
					}
					// A�� ������ �ƴ� ��� B
					else ans = "B";
				}
			}
		}
		// N�� 1�� ��� & N�� 2�� �� 1��° ���ڿ� 2��° ���ڰ� �ٸ� ��� A
		else if(N == 1 || (N == 2 && num[0] != num[1])) {
			ans = "A";
		}
		// ��� ���
		bw.write(ans + "");
		bw.flush();
		bw.close();
		br.close();		
	}
}
