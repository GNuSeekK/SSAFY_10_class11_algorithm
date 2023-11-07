import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 11848KB, 84ms
public class B1043 {

	public static boolean visited[];
	public static HashSet<Integer> ans = new HashSet<Integer>(); // �����̰� ������ ���ؾ��ϴ� ��Ƽ Set
	public static HashSet<Integer>[] partyMember; // �� ��� ��ȣ ���� ���� ��Ƽ�� ���� ��� ��ȣ�� �ֱ�
	public static HashSet<Integer>[] party; // �� ��� ��ȣ ���� ���� ��Ƽ ��ȣ �ֱ�
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N, M, ������ �ƴ� ��� �Է� �ޱ�
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int truthCnt = Integer.parseInt(st.nextToken());
		Queue<Integer> truth = new LinkedList<>(); 
		for (int i = 0; i < truthCnt; i++) {
			truth.offer(Integer.parseInt(st.nextToken()));
		}
		
		// ��Ƽ���� ���� ��� ��ȣ �Է� �ޱ�
		partyMember = new HashSet[N + 1];
		party = new HashSet[N + 1];
		for (int i = 0; i <= N; i++) {
			partyMember[i] = new HashSet<Integer>();
			party[i] = new HashSet<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int temp[] = new int[cnt];
			for (int j = 0; j < cnt; j++) {
				int num = Integer.parseInt(st.nextToken());
				temp[j] = num;
				party[num].add(i); // �� ��� ��ȣ ���� ���� ��Ƽ ��ȣ �ֱ�
			}

			// �� ��� ��ȣ ���� ���� ��Ƽ�� ���� ��� ��ȣ�� �ֱ�
			for (int j = 0; j < cnt; j++) {
				for (int k = j + 1; k < cnt; k++) {
					partyMember[temp[j]].add(temp[k]);
					partyMember[temp[k]].add(temp[j]);
				}
			}
		}

		// dfs �����ϸ� �����̰� ������ ���ؾ��ϴ� ��Ƽ �ֱ�
		visited = new boolean[N + 1];
		while(!truth.isEmpty()) { // ������ �ƴ� �����
			int cur = truth.poll();
			if(!visited[cur])
				dfs(cur);
		}
		
		// ��ü ��Ƽ���� ������ ���ؾ��ϴ� ��Ƽ ���� ����� �̾߱� �� �� �ִ� ��Ƽ ���� ���
		bw.write((M - ans.size()) + "");
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dfs(int number) {
		visited[number] = true;
		Iterator<Integer> it = party[number].iterator();
		while (it.hasNext()) // �����̰� ������ ���ؾ��ϴ� ��Ƽ �ֱ�
		    ans.add(it.next());
		it = partyMember[number].iterator(); // ������ �ƴ� �����(number) ���� ��Ƽ�� ���� ����� dfs ����
		while (it.hasNext()) {
			int next = it.next();
			if(!visited[next]) dfs(next);
		}
	}

}
