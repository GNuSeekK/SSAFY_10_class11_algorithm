import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Stack;

// 11632KB, 76ms
public class B1918 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// (,) -> *,/ -> +,- �� �켱���� HashMap ����
		HashMap<Character, Integer> priority = new HashMap<>();
		priority.put('(', 1); priority.put(')', 1);
		priority.put('*', 2); priority.put('/', 2);
		priority.put('+', 3); priority.put('-', 3);
		
		String infix = br.readLine(); // ���� ǥ���
		Stack<Character> stack = new Stack<>(); // �����ڸ� �����ϴ� ����
		String ans = "";
		
		// ���� ǥ��� ��ȯ
		for (int i = 0; i < infix.length(); i++) {
			char cur = infix.charAt(i);
			if(priority.containsKey(cur)) { // (, ), *, /, +, - �� ���
				if(cur == '(') {
					stack.push(cur); 
				}
				else if(cur == ')') { // ( ���� ������ pop �Ͽ� ans�� ����
					while(!stack.isEmpty() && stack.peek() != '(') {
						ans += stack.pop();
					}
					stack.pop(); // ( pop
				}
				else { // *, /, +, - �� ��� Stack ������ ���� ���� ������ �켱�������� ���� ���� ��Ÿ�� ������ pop �Ͽ� ans�� ����
					while(!stack.isEmpty() && stack.peek() != '(' && priority.get(stack.peek()) <= priority.get(cur)) {
						ans += stack.pop();
					}
					stack.push(cur); // ���� �������� �켱���� Ȯ������ �����Ƿ� push
				}
			}
			else { // �ǿ������� ��� �׳� ans�� ����
				ans += cur;
			}
			
		}
		
		// ���� ������ pop �Ͽ� ans�� ����
		while(!stack.isEmpty()) ans += stack.pop();
		
		// ��� ���
		bw.write(ans);
		bw.flush();
		bw.close();
		br.close();	
	}

}
