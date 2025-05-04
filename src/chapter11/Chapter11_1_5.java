package chapter11;

/**
 * <h5>1.5 Iterator, ListIterator, Enumeration</h5><br>
 * Iterator, ListIterator, Enumeration은 모두 컬렉션에 저장된 요소를 접근하는데 사용되는 인터페이스이다, Enumeration은 Iterator의 구버젼이며, ListIterator는 Iterator의 기능을 향상 시킨 것이다.<br>
 */
public class Chapter11_1_5 {

	/**
	 * <h5>Iterator</h5><br>
	 * 컬렉션 프레임웍에서는 컬렉션에 저장된 요소들을 읽어오는 방법을 표준화하였다. 컬렉션에 저장된 각 요소에 접근하는 기능을 가진 Iterator인터페이스를 정의하고, Collection 인터페이스에는 'Iterator(Iterator를 구현한 클래스의 인스턴스)'를 반환하는 iterator()를 정의하고 있다.<br>
	 * iterator()는 Collection인터페이스에 정의된 메서드이므로 Collection인터페이스의 자손인 List와 Set에도 포함되어 있다. List나 Set인터페이스를 구현하는 컬렉션은 Iterator()가 각 컬렉션의 특징에 알맞게 작성되어 있다.<br>
	 * <table border="1">
	 *     <thead>
	 *         <th>메서드</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>boolean hashNext()</td>
	 *             <td>읽어 올 요소가 남아있는지 확인한다. 있으면 true, 없으면 false를 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Object next()</td>
	 *             <td>다음 요소를 읽어 온다. next()를 호출하기 전에 hashNext()를 호출해서 읽어 올 요소가 있는지 확인하는 것이 안전하다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void remove()</td>
	 *             <td>next()로 읽어 온 요소를 삭제한다. next()를 호출한 다음에 remove()를 호출해야한다.(선택적 기능)</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * Map인터페이스를 구현한 컬렉션 클래스는 키(key)와 값(value)을 쌍(pair)으로 저장하고 있기 때문에 iterator()를 직접 호출할 수 없고, 그 대신 keySet()이나 entrySet()과 같은 메서드를 통해서 키와 값을 각각 따로 Set의 형태로 얻어 온 후에 다시 iterator()를 호출해야 Iterator를 얻을 수 있다.
	 * <pre><code>
	 *     Map map = new HashMap();
	 *     ...
	 *     Iterator it = map.entrySet().iterator();
	 * </code></pre>
	 * <br>
	 * Iterator it = map.entrySet().iterator(); 는 아래의 두 문장을 하나로 합친 것이라고 이해하면 된다.<br>
	 * <pre><code>
	 *		Set set = map.entrySet();
	 *	    Iterator it = eSet.iterator();
	 * </code></pre>
	 * <br>
	 * <pre><code>
	 *     1. map.entrySet()의 실행결과가 Set이므로
	 *     Iterator it = map.entrySet().iterator(); -> Iterator it = Set인스턴스.iterator();
	 *     2. map.entrySet()를 통해 얻은 Set인스턴스의 iterator()를 호출해서 Iterator인스턴스를 얻는다.
	 *     Iterator it = Set인스턴스.iterator(); -> Iterator it = Iterator인스턴스;
	 *     3. 마지막으로 Iterator인스턴스의 참조가 it에 저장된다.
	 * </code></pre>
	 * <br>
	 * List클래스들은 저장순서를 유지하기 때문에 Iterator를 이용해서 읽어 온 결과 역시 저장 순서와 동일하지만 Set클래스들은 각 요소간의 순서가 유지 되지 않기 때문에 Iterator를 이용해서 자장된 요소들을 읽어 와도 처음에 저장된 순서와 같지 않다.
	 */
	class Memo1 {

	}

	/**
	 * <h5>ListIterator와 Enumeration</h5><br>
	 * Enumeration은 컬레션 프레임웍이 만들어지기 이전에 사용하던 것으로 Iterator의 구버젼이라고 생각하면 된다.<br>
	 * ListIterator는 Iterator를 상속받아서 기능을 추가한 것으로, 컬렉션의 요소에 접근할 때 Iterator는 단방향으로만 이동할 수 있는 데 반해 ListIterator는 양방향으로의 이동이 가능하다.<br>
	 * ArrayList나 LinkedList와 같이 List인터페이스를 구현한 컬렉션에서만 사용할 수 있다.<br>
	 * <pre><code>
	 *     Enumeration Iterator의 구버젼
	 *     ListIterator Iterator에 양방향 조회기능추가(List를 구현한 경우만 사용가능)
	 * </code></pre>
	 * <br>
	 * Enumeration과 Iterator는 메서드이름만 다를 뿐 기능은 같고, ListIterator는 Iterator에 이전방향으로의 접근 기능을 추가한 것일 뿐이라는 것을 알 수 있다.<br>
	 * Enumeration 인터페이스의 메서드<br>
	 * <table border="1">
	 *     <thead>
	 *         <th>메서드</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>boolean hasMoreElements()</td>
	 *             <td>읽어 욜 소가 남아있는지 확인한다. 있으면 true, 없으면 false를 반환한다. Iterator의 hasNext()와 같다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Object nextElement()</td>
	 *             <td>다음 요소를 읽어 온다. nextElement()를 호출하기 전에 hasMoreElements()를 호출해서 읽어올 요소가 남아있는지 확인하는 것이 안전하다. Iterator의 next()와 같다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * ListIterator의 메서드<br>
	 * <table border="1">
	 *     <thead>
	 *         <th>메서드</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>void add(Object o)</td>
	 *             <td>컬렉션에 새로운 객체(o)를 추가한다.(선택적 기능)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean hashNext()</td>
	 *             <td>읽어 올 다음 요소가 남아있는지 확인한다. 있으면 true, 없으면 false를 반환</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean hashPrevious()</td>
	 *             <td>읽어 올 이전 요소가 남아있는지 확인한다. 있으면 true, 없으면 false를 반환</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Object next()</td>
	 *             <td>다음 요소를 읽어 온다. previous()를 호출하기 전에 hashPrevious()를 호출해서 읽어 올 요소가 있는지 확인하는 것이 안전한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Object previous()</td>
	 *             <td>이전 요소를 읽어 온다. previous()를 호출하기 전에 hasPrevious()를 호출해서 읽어 올 요소가 있는지 확인하는 것이 안전하다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int nextIndex()</td>
	 *             <td>다음 요소의 index를 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int previousIndex()</td>
	 *             <td>이전 요소의 index를 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void remove()</td>
	 *             <td>next() 또는 previous()로 읽어 온 요소를 삭제한다. 반드시 next()나 previous()를 호출한 다음에 이 메서드를 호출해야 한다.(선택적 기능)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void set(Object o)</td>
	 *             <td>next() 또는 previous()로 읽어 온 요소를 지정된 객체(o)로 변경한다. 반드시 next()나 previous()를 호출한 다음에 이 메서드를 호출해야한다.(선택적 기능)</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * ListIterator는 양방향으로 이동하기 때문에 각 요소간의 이동이 자유롭다. 다만 이동하기 전에 반드시 hasNext()나 hasPreviouS()를 호출해서 이동할 수 있는지 확인해야 한다.
	 */
	class Memo2 {

	}

}
