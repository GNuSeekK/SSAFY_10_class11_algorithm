import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class keesung {

    static int[][] costs;
    static HashMap<Integer, HashMap<Integer, Integer>> hashMap = new HashMap<>();

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 1000000000;
        for (int i = 1; i <= n; i++) {
            hashMap.put(i, new HashMap<>());
        }
        for (int[] fare : fares) {
            int nodeA = fare[0];
            int nodeB = fare[1];
            int cost = fare[2];
            hashMap.get(nodeA).put(nodeB, cost);
            hashMap.get(nodeB).put(nodeA, cost);
            // hashMap으로 각 노드들을 연결 시킴
        }

        // s, a, b에서 모든 지점에 갈 수 있는 비용 계산해서 담기

        costs = new int[3][n + 1];
        calcCost(0, s, n);
        calcCost(1, a, n);
        calcCost(2, b, n);

        // 모든 지점 탐색하며, 각 지점에서 s, a, b를 갈 경우 cost를 갱신해줌
        for (int i = 1; i <= n; i++) {
            int tmp = costs[0][i] + costs[1][i] + costs[2][i];
            if (tmp == 0) {
                continue; // 0일 경우 s, a, b와 연결되어 있지 않은 경우이므로 빼야함
            }
            answer = Math.min(tmp, answer);
        }

        return answer;
    }

    public void calcCost(int index, int start, int n) {
        // 각 cost를 계산해서 넣어주는 연산
        int[] costArr = costs[index];
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        queue.offer(new int[] { start, 0 });
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int node = arr[0];
            if (visited[node]) {
                continue;
            }
            int cost = arr[1];
            costArr[node] = cost;
            visited[node] = true;
            for (int child : hashMap.get(node).keySet()) {
                int newCost = hashMap.get(node).get(child) + cost;
                queue.offer(new int[] { child, newCost });
            }

        }

    }

}