import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

// �� ������ �ٽ��� Ʈ���� ������ ���� ����
// 1. ��Ʈ�κ��� ���� �� ��带 ���Ѵ�.
// 2. �� ��忡�� ���� �� ������ �Ÿ��� Ʈ���� ������ �ȴ�.
public class B1167 {
	
	static class Node {
		int index;
		int dist;
		public Node(int index, int dist) {
			this.index = index;
			this.dist= dist;
		}
	}
	
	public static ArrayList<ArrayList<Node>> tree = new ArrayList<ArrayList<Node>>();
	public static int ans = 0, maxNode = 0;
	public static boolean visited[];
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// ���� ���� �Է� �ޱ�
		int V = Integer.parseInt(br.readLine());
		
		for (int i = 0; i <= V; i++) {
			tree.add(new ArrayList<Node>());
		}

		// ���� �Է� �ޱ�
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int E1 = Integer.parseInt(st.nextToken());
			int E2;
			while((E2 = Integer.parseInt(st.nextToken())) != -1) {
				int dist = Integer.parseInt(st.nextToken());
				tree.get(E1).add(new Node(E2, dist));
			}
		}

		// 1. ��Ʈ�κ��� ���� �� ��带 ���Ѵ�.
		visited = new boolean[V + 1];
		dfs(1, 0);

		// 2. �� ��忡�� ���� �� ������ �Ÿ��� Ʈ���� ������ �ȴ�.
		visited = new boolean[V + 1];
		dfs(maxNode, 0);
		
		// ���� ���
		bw.write(ans + "");
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dfs(int edge, int dist) {
		visited[edge] = true;
		for (Node node : tree.get(edge)) {
			if(visited[node.index]) {
				// ���̻� �����ϱ� max ������ update
				if(ans < dist) {
					ans = dist;
					maxNode = edge;
				}
			}
			else {
				dfs(node.index, dist + node.dist);
			}
		}
	}

}
