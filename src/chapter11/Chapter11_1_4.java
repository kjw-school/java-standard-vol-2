package chapter11;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

/**
 * <h1>1.4 Stack과 Queue</h1>
 */
public class Chapter11_1_4 {

	/**
	 * 스택은 마지막에 저장한 데이터를 가장 먼저 꺼내게 되는 LIFO(Last In Frist Out)구조로 되어있고, 큐는 처음에 저장한 데이터를 가장 먼저 꺼내게 되는 FIFO(First In First Out)구조로 되어 있다.
	 * <br>
	 * 순차적으로 데이터를 추가하고 삭제하는 스택에는 ArrayList와 같은 배열기반의 컬렉션 클래스가 적합하지만, 큐는 데이터를 꺼낼 때 항상
	 * <br>
	 * 첫 번째 저장된 데이터를 삭제하므로, ArrayList와 같은 배열기반의 컬렉션 클래스를 사용한다면 데이터를 꺼낼 때 마다 빈 공간을 채우기 위해<br>
	 * 데이터의 복사가 발생하므로 비효율적이다. 그래서 큐는 ArrayList보다 데이터의 추가/삭제가 쉬운 LinkedList로 구현하는 것이 더 적합하다.<br>
	 * <b>Stack의 메서드</b>
	 * <table>
	 *     <tr>
	 *         <th style="border: 1px solid white; text-align: center; vertical-align: center;">메서드</th>
	 *         <th style="border: 1px solid white; text-align: center; vertical-align: center;">설명</th>
	 *     </tr>
	 *     <tr>
	 *         <td style="border: 1px solid white;">boolean empty()</td>
	 *         <td style="border: 1px solid white;">Stack이 비어있는지 알려준다.</td>
	 *     </tr>
	 *     <tr>
	 *         <td style="border: 1px solid white;">Object peek()</td>
	 *         <td style="border: 1px solid white;">Stack의 맨 위에 저장된 객체를 반환, pop()과 달리 Stack에서 객체를 꺼내지는 않음.(비었을 때는 EmptyStackException 발생)</td>
	 *     </tr>
	 *     <tr>
	 *         <td style="border: 1px solid white;">Object pop()</td>
	 *         <td style="border: 1px solid white;">Stack의 맨 위에 저장된 객체를 꺼낸다.(비었을 때는 EmptystackException 발생)</td>
	 *     </tr>
	 *     <tr>
	 *         <td style="border: 1px solid white;">Object push(Object item)</td>
	 *         <td style="border: 1px solid white;">Stack에 객체(item)를 저장한다.</td>
	 *     </tr>
	 *     <tr>
	 *         <td style="border: 1px solid white;">int search(Object o)</td>
	 *         <td style="border: 1px solid white;">Stack에서 주어진 객체(o)를 찾아서 그위치를 반환, 못 찾으면 -1을 반환.(배열과 달리 위치는 0이 아닌 1부터 시작</td>
	 *     </tr>
	 * </table>
	 * <b>Queue의 메서드</b>
	 * <br>
	 * 메서드 | 설명
	 * <br>
	 * boolean add(Object o) | 지정된 객체를 Queue에 추가한다. 성공하면 true를 반환, 저장공간이 부족하면 IllegalStateException발생
	 * <br>
	 * Object remove() | Queue에서 객체를 꺼내 반환, 비어있으면 NoSuchElementException발생
	 * <br>
	 * boolean offer(Object o) | Queue에 객체를 저장, 성공하면 true, 실패하면 false를 반환
	 * <br>
	 * Object poll() | Queue에서 객체를 꺼내서 반환, 비어있으면 null을 반환
	 * <br>
	 * Object peek() | 삭제없이 요소를 읽어 온다. Queue가 비어있으면 null을 반환
	 */
	class Memo01 {
	}

	static class StackQueueEx {

		public static void main(String[] args) {

			Stack st = new Stack();
			Queue q = new LinkedList(); // Queue인터페이스의 구현체인 LinkedList를 사용

			st.push("0");
			st.push("1");
			st.push("2");

			q.offer("0");
			q.offer("1");
			q.offer("2");

			System.out.println("= Stack = ");
			while (!st.empty()) {
				System.out.println(st.pop());
			}

			System.out.println("= Queue = ");
			while (!q.isEmpty()) {
				System.out.println(q.poll());
			}

		}

	}

	/**
	 * 자바에서는 스택을 Stack클래스로 구현하여 제공하고 있지만 큐는 Queue인터페이스로만 정의해 놓았을 뿐 별도의 클래스를 제공하고 있지 않다.
	 * <br>
	 * 대신 Queue인터페이스를 구현한 클래스들이 있어서 이 들 중의 하나를 선택해서 사용하면 된다.
	 */
	class Memo02 {
	}

	/**
	 * <h5>Stack직접 구현하기</h5>
	 * <br>
	 * Stack은 컬렉션 프레임웍 이전부터 존재하던 것이기 때문에 ArrayList가 아닌 Vector로 부터 상속받아 구현하였다.
	 */
	static class MyStack extends Vector {

		public Object push(Object item) {
			addElement(item);
			return item;
		}

		public Object pop() {
			Object obj = peek();
			// 만일 Stack이 비어있으면 peek() 메서드가 EmptyStackException을 발생시킨다.
			// 마지막 요소를 삭제한다. 배열의 index가 0 부터 시작하므로 1을 빼준다.
			removeElementAt(size() - 1);
			return obj;
		}

		public Object peek() {
			int len = size();

			if (len == 0)
				throw new EmptyStackException();
			// 마지막 요소를 반환한다. 배열의 index가 0 부터 시작하므로 1을 빼준다.
			return elementAt(len - 1);
		}

		public boolean empty() {
			return size() == 0;
		}

		public int search(Object o) {
			int i = lastIndexOf(o); // 끝에서부터 객체를 찾는다.
			// 반환값은 저장된 위치(배열의 index)이다.

			if (i >= 0) { // 객체를 찾은 경우
				return size() - i; // Stack은 맨 위에 저장된 객체의 index를 1로 정의하기 때문에 계산을 통해서 구한다.
			}

			return -1; // 해당 객체를 찾지 못하면 -1를 반환한다.

		}

	}

	/**
	 * <h5>스택과 큐의 활용</h5>
	 * <br>
	 * <b>스택의 활용 예</b> - 수식계산, 수식괄호검사, 워드프로세서의 undo/redo, 웹브라우저의 뒤로/앞으로
	 * <br>
	 * <b>큐의 활용 예</b> - 최근사용문서, 인쇄작업 대기목록, 버퍼(buffer)
	 */
	class Memo03 {
	}

	static class StackEx1 {

	}

	/**
	 * <h5>PriorityQueue</h5><br>
	 * Queue인터페이스의 구현체 중의 하나로, 저장한 순서에 관계없이 우선순위(priority)가 높은 것부터 꺼내게 된다는 특징이 있다. null은 저장할 수 없다.<br>
	 * PriorityQueue는 저장공간으로 배열을 사용하며, 각 요소를 '힙(heap)'이라는 자료구조의 형태로 저장한다.<br>
	 * <small>※ 자료구조 힙(heap)은 앞서 배운 JVM의 힙(heap)과 이름만 같을 뿐 다른 것이다.</small>
	 */
	class Memo4 {

	}

	/**
	 * <h5>Deque(Double-Ended Queue)</h5><br>
	 * Queue의 변형으로, 한 쪽 끝으로만 추가/삭제할 수 있는 Queue와 달ㄹ리, Deque(덱, 또는 디큐라고 읽음)은 양쪽 끝에 추가/삭제가 가능하다.<br>
	 * Deque의 조상은 Queue이며, 구현체로는 ArrayDeque과 LinkedList 등이 있다.<br>
	 * 덱은 스택과 큐를 하나로 합쳐놓은 것과 같으며 스택으로 사용할 수도 있고, 큐로 사용할 수도 있다.<br>
	 * 덱(Deque)의 메서드에 대응하는 큐와 스택의 메서드
	 * <table border="1">
	 *     <thead>
	 *         <th>Deque</th>
	 *         <th>Queue</th>
	 *         <th>Stack</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>offerLast()</td>
	 *             <td>offer()</td>
	 *             <td>push()</td>
	 *         </tr>
	 *         <tr>
	 *             <td>pollLast()</td>
	 *             <td>-</td>
	 *             <td>pop()</td>
	 *         </tr>
	 *         <tr>
	 *             <td>pollFirst()</td>
	 *             <td>poll()</td>
	 *             <td>-</td>
	 *         </tr>
	 *         <tr>
	 *             <td>peekFirst()</td>
	 *             <td>peek()</td>
	 *             <td>-</td>
	 *         </tr>
	 *         <tr>
	 *             <td>peekLAst()</td>
	 *             <td>-</td>
	 *             <td>peek()</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 */
	class Memo5 {
	}
}
