package chapter11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * <h1>1.2 ArrayList</h1><br>
 * ArrayList는 List인터페이스를 구현하기 때문에 데이터의 저장순서가 유지되고 중복을 허용한다는 특징을 갖는다.<br>
 * ArrayList는 기존의 Vector를 개선한 것으로 Vector와 구현원리와 기능적인 측면에서 동일하다고 할 수 있다.<br>
 * ArrayList는 Object배열을 이용해서 데이터를 순차적으로 저장한다.<br>
 * 배열에 더 이상 저장할공간이 없으면 보다 큰 새로운 배열을 생성해서 기존의 배열에 저장된 내용을 새로 배열로 복사한 다음에 저장된다.
 */
public class Chapter11_1_2 {

	/**
	 * <b>ArrayList의 생성자와 메서드</b><br>
	 * <table border="1">
	 *     <thead>
	 *         <td>메서드</td>
	 *         <td>설명</td>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>ArrayList()</td>
	 *             <td>크기가 10인 ArrayList를 생성</td>
	 *         </tr>
	 *         <tr>
	 *             <td>ArrayList(Collection c)</td>
	 *             <td>주어진 컬렉션이 저장된 ArrayList를 생성</td>
	 *         </tr>
	 *         <tr>
	 *             <td>ArrayList(int initialCapacity)</td>
	 *             <td>지정된 초기용량을 갖는 ArrayList를 생성</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean add(Object c)</td>
	 *             <td>ArrayList의 마지막에 객체를 추가, 성공하면 true</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void add(int index, Object element)</td>
	 *             <td>지정된 위치(index)에 객체를 저장</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean addAll(Collection c)</td>
	 *             <td>주어진 컬렉션의 모든 객체를 저장한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean addAll(int index, Collection c)</td>
	 *             <td>지정된 위치부터 주어진 컬렉션의 모든 객체를 저장한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void clear()</td>
	 *             <td>ArrayList를 완전히 비운다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Object clone()</td>
	 *             <td>ArrayList를 복제한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean contains(Object o)</td>
	 *             <td>지정된 객체(o)가 ArrayList에 포함되어 있는지 확인</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void ensureCapacity(int minCapacity)</td>
	 *             <td>ArrayList의 용량이 최소한 minCapacity가 되도록 한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Object get(int index)</td>
	 *             <td>지정된 위치(index)에 저장된 객체를 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int indexOf(Object o)</td>
	 *             <td>지정된 객체가 저장된 위치를 찾아 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean isEmpty()</td>
	 *             <td>ArrayList가 비어있는지 확인한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Iterator iterator()</td>
	 *             <td>ArrayList의 Iterator객체를 반환</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int lastIndexOf(Object o)</td>
	 *             <td>객체(o)가 저장된 위치를 끝부터 역방향으로 검색해서 반환</td>
	 *         </tr>
	 *         <tr>
	 *             <td>ListIterator listIterator()</td>
	 *             <td>ArrayList의 ListIterator를 반환</td>
	 *         </tr>
	 *         <tr>
	 *             <td>ListIterator listIterator(int index)</td>
	 *             <td>ArrayList의 지정된 위치부터 시작하는 ListIterator를 반환</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Object remove(int index)</td>
	 *             <td>지정된 위치(index)에 있는 객체를 제거한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean remove(Object o)</td>
	 *             <td>지정된 객체를 제거한다.(성공하면 true, 실패하면 false)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean removeAll(Collection c)</td>
	 *             <td>지정한 컬렉션에 저장된 것과 동일한 객체들을 ArrayList에서 제거한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean retainAll(Collection c)</td>
	 *             <td>ArrayList에 저장된 객체 중에서 주어진 컬렉션과 공통된 것들만을 남기고 나머지는 삭제한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Object set(int index, Object element)</td>
	 *             <td>주어진 객체(element)를 지정된 위치(index)에 저장한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int size()</td>
	 *             <td>ArrayList에 저장된 객체의 개수를 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void sort(Comparator c)</td>
	 *             <td>지정된 정렬기준(c)으로 ArrayList를 정렬</td>
	 *         </tr>
	 *         <tr>
	 *             <td>List subList(int fromIndex, int toIndex)</td>
	 *             <td>formIndex부터 toIndex사이에 저장된 객체를 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Object[] toArray()</td>
	 *             <td>ArrayList에 저장된 모든 객체들을 객체배열로 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Object[] toArray(Object[] a)</td>
	 *             <td>ArrayList에 저장된 모든 객체들을 객체배열 a에 담아 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void trimToSize()</td>
	 *             <td>용량을 크기에 맞게 줄인다.(빈 공간을 없앤다.)</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 */
	class Memo1 {

	}

	static class ArrayListEx1 {
		public static void main(String[] args) {
			ArrayList list1 = new ArrayList(10);
			list1.add(new Integer(5));
			list1.add(new Integer(4));
			list1.add(new Integer(2));
			list1.add(new Integer(0));
			list1.add(new Integer(1));
			list1.add(new Integer(3));

			ArrayList list2 = new ArrayList(list1.subList(1, 4));
			print(list1, list2);

			Collections.sort(list1); // list1과 list2를 정렬한다.
			Collections.sort(list2); // Collections.sort(List 1)
			print(list1, list2);

			System.out.println("list1.containsAll(list2):" + list1.containsAll(list2));

			list2.add("B");
			list2.add("C");
			list2.add(3, "A");
			print(list1, list2);

			list2.set(3, "AA");
			print(list1, list2);

			// list1에서 list2와 겹치는 부분만 남기고 나머지는 삭제한다.
			System.out.println("list1.retainAll(list2):" + list1.retainAll(list2));

			print(list1, list2);

			// list2에서 list1에 포함된 객체들을 삭제한다.
			for (int i = list2.size() - 1; i >= 0; i--) {
				if (list1.contains(list2.get(i))) {
					list2.remove(i);
				}
			}

			print(list1, list2);

		}

		static void print(ArrayList list1, ArrayList list2) {
			System.out.println("list1:" + list1);
			System.out.println("list2:" + list2);
			System.out.println("");
		}

	}

	/**
	 * ArrayList는 List인터페이스를 구현했기 때문에 저장된 순서를 유지한다는 것을 알 수 있다.
	 */
	static class ArrayListEx2 {
		public static void main(String[] args) {

			final int LIMIT = 10; // 자르고자 하는 글자의 개수를 지정한다.
			String source = "0123456789abcdefghijABCDEFGHIJ!@#$%^&*()ZZZ";
			int length = source.length();

			List list = new ArrayList(length / LIMIT + 10); // 크기를 약간 여유 있게 잡는다.
			for (int i = 0; i < length; i += LIMIT) {
				if (i + LIMIT < length) {
					list.add(source.substring(i, i + LIMIT));
				} else {
					list.add(source.substring(i));
				}
			}

			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}

		}

	}

	/**
	 * ArrayList를 생성할 때, 저장할 요소의 개수를 고려해서 실제 저장할 개수보다 약간 여유있는 크기로 하는 것이 좋다. 생성할 때 지정한
	 * 크기보다 더 많은 객체를 저장하면 자동적으로 크기가 늘어나기는 하지만 이 과정에서 처리시간이 많이 소요되기 떄문이다.
	 */
	static class VectorEx1 {
		public static void main(String[] args) {
			Vector v = new Vector(5); // 용량(capacity)이 5인 Vector를 생성한다.
			v.add("1");
			v.add("2");
			v.add("3");
			print(v);

			v.trimToSize(); // 빈 공간을 없앤다. (용량과 크기가 같아진다.)
			System.out.println("=== After trimToSize() ===");
			print(v);

			v.ensureCapacity(6);
			System.out.println("=== After ensureCapacity(6) ===");
			print(v);

			v.setSize(7);
			System.out.println("=== After setSize(7) ===");
			print(v);
		}

		public static void print(Vector v) {
			System.out.println(v);
			System.out.println("size :" + v.size());
			System.out.println("capacity :" + v.capacity());
		}

	}

	/**
	 * v.trimToSize()를 호출하면 v의 빈 공간을 없애서 size와 capacitry를 같게 한다. 배열은 크기를 변경할 수 없기 때문에 새로운배열을 생성해서 그 주소값을 변수 v에 할당한다.<br>
	 * 기존의 Vector인스턴스는 더 이상 사용할 수 없으면, 후에 가비지 컬렉터(garbagecollector)에 의해서 메모리에서 제거된다.<br>
	 * v.ensureCapacity(6)는 v의 capacity가 최소한 6이 되도록 한다. 만일 v의 capacity가 6이상 이라면 아무 일도 일어나지 않는다.<br>
	 * 현재는 v의 capacity가 3이므로 크기가 6인 배열을 생성해서 v의 내용을 복사했다. 기존의 인스턴스를 다시 사용하는 것이 아니라 새로운 인스턴스를 생성하였음에 주의하자.<br>
	 * v.setSize(7)는 v의 size가 7이 되도록 한다. 만일 v의 capacity가 충분하면 새로 인스턴스를 생성하지 않아도 되지만 지금은 capacity가 6이므로 새로운 인스턴스를 생성해야한다.<br>
	 * Vector는 capacity가 부족할 경우 자동적으로 기존의 크기보다 2배의 크기로 증가된다. 그래서 v의 capacity는 12가 된다.
	 */
	class Memo01 {
	}

	/**
	 * ArrayList나 Vector 같이 배열을 이용한 자료구조는 데이터를 읽어오고 저장하는 데는 효율이 좋지만, 용량을 변경해야할 때는 새로운 배열을 생성한 후 기존의 배열로부터 새로 생성된 배열로 데이터를 복사해야하기 때문에 상당히 효율이 떨어진다는 단점을 가지고 있다.<br>
	 * <small>※ 인터페이스를 구현할 때 인터페이스에 정의된 모든 메서드를 구현해야 한다. 일부 메서드만 구현했다면 추상클래스로 선언해야한다. 그러나 JDK1.8부터 List인터페이스에 3개의 디폴트 메서드가 추가되었으며, 이 들은 구현하지 않아도 된다.</small><br>
	 * System.arraycopy(data, 3, data, 2, 2) - data[3]에서 data[2]로 2개의 데이터를 복사하라는 의미이다.<br>
	 * 배열에 객체를 순차적으로 저장할 때와 객체를 마지막에 저장된 것부터 삭제하려면 System.arraycopy()를 호출하지 않기 때문에 작업시간이짧지만,<br>
	 * 배열의 중간에 위치한 객체를 추가하거나 삭제하는 경우 System.arraycopy()를 호출해서 다른 데이터의 위치를 이동시켜 줘야 하기때문에 다루는 데이터의 개수가 많을수록 작업시간이 오래 걸린다는 것이다.
	 */
	class Memo02 {
	}

}
