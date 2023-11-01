import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 57528KB, 712ms
public class B1374 {
	// ���� Ŭ����
	static class Lecture implements Comparable<Lecture> {
		int startTime; // ���۽ð�
		int endTime; // ����ð�
		
		public Lecture(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}
		
		@Override
		public int compareTo(Lecture o) { // ���۽ð�, ����ð� ������ �������� ����
			if(this.startTime == o.startTime) {
				return this.endTime - o.endTime;
			}
			return this.startTime - o.startTime;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		PriorityQueue<Lecture> lectures = new PriorityQueue<>();
		PriorityQueue<Integer> endTime = new PriorityQueue<>(); // ����ð��� ����
		// �Է� �ޱ�
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			lectures.offer(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		// ���۽ð�, ����ð� ������ ���ĵ� �Է°����� ������� �����鼭 
		// ����ð��� ����� �켱���� ť�� ���� ���� ������ ���۽ð����� �۰ų� ������ ����ð� poll �� ���� ���� ������ ����ð� offer
		// -> �� ���ǽǿ��� ���� ���� ���� ���� ���� �� ���� ���� ������ �� �����ϱ�
		for (int i = 0; i < N; i++) {
			Lecture curLecture = lectures.poll();
			if(!endTime.isEmpty() && endTime.peek() <= curLecture.startTime) {
				endTime.poll();
			}
			endTime.offer(curLecture.endTime);
		}
		
		// ����ð� ���� ���
		bw.write(endTime.size() + "");
		bw.flush();
		bw.close();
		br.close();		
	}
}
