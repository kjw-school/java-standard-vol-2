package chapter11;

import static java.lang.System.*;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <h1>1.7 Comparator와 Comparable</h1><br>
 * Arrays.sort()를 호출만 하면 컴퓨터가 알아서 배열을 정렬하는 것처럼 보이지만, 사실은 Character클래스의 Comparable의 구현에 의해 정렬되었던 것이다.<br>
 * Comparator와 Comparable은 모두 인터페이스로 컬렉션을 정렬하는데 필요한 메서드를 정의하고 있으며, Comparable을 구현하고 있는 클래스들은 같은 타입의 인스턴스끼리 서로 비교할 수 있는 클래스들,<br>
 * 주로 Integer와 같은 wrapper클래스와 String, Date, File과 같은 것들이며 기본적으로 오름차순, 즉 작은 값에서부터 큰 값의 순으로 정렬되도록 구현되어 잇다. Comparable을 구현한 클래스는 정렬이 가능하다는 것을 의미한다.<br>
 * <pre><code>
 *     public interface Comparator {
 *         int compare(Object o1, Object o2);
 *         boolean equals(Object obj);
 *     }
 *
 *     public interface Comparable {
 *         public int compareTo(Object o);
 *     }
 * </code></pre>
 * <br>
 * <small>※Comparable은 java.lang패키지에 있고, Comparator는 java.util패키지에 있다.</small><br>
 * compare()와 compareTo()는 선언형태와 이름이 약간 다를 뿐 두 객체를 비교한다는 같은 기능을 목적으로 고안된 것이다.<br>
 * compareTo()의 반환값은 int이지만 실제로는 비교하는 두 객체가 같으면 0, 비교하는 값보다 작으면 음수, 크면 양수를 반환하도록 구현해야 한다.<br>
 * compare()도 객체를 비교해서 음수, 0, 양수 중의 하나를 반환하도록 구현해야한다.<br>
 * Comparable을 구현한 클래스들이 기본적으로 오름차순으로 정렬되어 있지만, 내림차순으로 정렬한다던가 아니면 다른 기준에 의해서 정렬되도록 하고 싶을 때 Comparator를 구현해서 정렬기준을 제공할 수 있다.<br>
 * <pre><code>
 *     Comparable - 기본 정렬기준을 구현하는데 사용.
 *     Comparator - 기본 정렬기준 외에 다른 기준으로 정렬하고자할 때 사용
 * </code></pre>
 */
public class Chapter11_1_7 {

	static class Ex1 {
		public static void main(String[] args) {
			String[] strArr = {"cat", "Dog", "lion", "tiger"};

			Arrays.sort(strArr); // String의 Comparabl구현에 의한 정렬
			out.println("strArr=" + Arrays.toString(strArr));

			Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER); // 대소문자 구분안함
			out.println("strARr=" + Arrays.toString(strArr));

			Arrays.sort(strArr, new Descending()); // 역순 정렬
			out.println("strArr=" + Arrays.toString(strArr));
		}

		static class Descending implements Comparator {
			public int compare(Object o1, Object o2) {
				if (o1 instanceof Comparable && o2 instanceof Comparable) {
					Comparable c1 = (Comparable)o1;
					Comparable c2 = (Comparable)o2;
					return c1.compareTo(c2) * -1; // -1을 곱해서 기본 정렬방식의 역으로 변경한다.
					// 또는 c2.compareTo(c1)와 같이 순서를 바꿔도 된다.
				}
				return -1;
			}
		}
	}

	/**
	 * Arrays.sort()는 배열을 정렬할 때, Comparator를 지정해주지 않으면 저장하는 객체<sub>주로 Comparable을 구현한 클래스의 객체</sub>에 구현된 내용에 따라 정렬된다.<br>
	 * String의 Comparator구현은 문자열이 사전 순으로 정렬되도록 작성되어 있다.<br>
	 * 문자열의 오름차순 정렬은 공백, 숫자, 대문자, 소문자의 순으로 정렬되는 것을 의미한다.<br>
	 * 정확히 얘기하면 문자의 유니코드의 순서가 작은 값에서부터 큰 값으로 정렬되는 것이다.<br>
	 * String의 기본 정렬을 반대로 하는 것, 즉 문자열을 내림차순(descending order)을 구현하는 것은 아주 간단하다.<br>
	 * 단지 String에 구현된 compareTo()의 결과에 -1을 곱하기만 하면 된다.<br>
	 * 또는 비교하는 객체의 위치를 바꿔서 c2.compareTo(c1)과 같이 해도 된다.
	 */
	class Memo1 {

	}

}
