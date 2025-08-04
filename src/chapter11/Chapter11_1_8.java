package chapter11;

/**
 * <h1>1.8 HashSet</h1><br>
 * HashSet은 Set인터페이스를 구현한 가장 대표적인 컬렉션이며, Set인터페이스의 특정대로 HashSet은 중복된 요소를 저장하지 않는다.<br>
 * HashSet에 새로운 요소를 추가할 때는 add메서드나 addAll메서드를 사용하는데, 만일 HashSet에 이미 저장되어 있는 요소의 중복된 요소를 추가하고자 한다면 이 메서드들은 false를 반환함으로써 중복된 요소이기 때문에 추가해 실패했다는 것을 알린다.<br>
 * ArrayList와 같이 List인터페이스를 구현한 컬렉션과 달리 HashSet은 저장순서를 유지하지 않으므로 저장순서를 유지하고자 한다면 LinkedHashSet을 사용해야 한다.<br>
 * <small>※ HashSet은 내부적으로 HashMap을 이용해서 만들어졌으며, HashSet이란 이름은 해싱(hashing)을 이용해서 구현했기 때문에 붙여진 것이다.</small>
 * <br>
 * HashSet의 메서드<br>
 * <table>
 *     <thead>
 *         <th>생성자 또는 메서드</th>
 *         <th>설명</th>
 *     </thead>
 *     <tbody>
 *         <tr>
 *             <td>HashSet()</td>
 *             <td>HashSet객체를 생성한다.</td>
 *         </tr>
 *         <tr>
 *             <td>HashSet(Collection c)</td>
 *             <td>주어진 컬렉션을 포함하는 HashSet객체를 생성한다.</td>
 *         </tr>
 *         <tr>
 *             <td>HashSet(int initialCapacity)</td>
 *             <td>주어진 값을 초기용량으로하는 HashSet객체를 생성한다.</td>
 *         </tr>
 *         <tr>
 *             <td>HashSet(int initialCapacity, float loadFactor)</td>
 *             <td>초기용량과 load factor를 지정하는 생성자.</td>
 *         </tr>
 *         <tr>
 *             <td>boolean add(Object o)</td>
 *             <td>새로운 객체를 저장한다.</td>
 *         </tr>
 *         <tr>
 *             <td>boolean addAll(Collection c)</td>
 *             <td>주어진 컬렉션에 저장된 모든 객체들을 추가한다.(합집합)</td>
 *         </tr>
 *         <tr>
 *             <td>void clear()</td>
 *             <td>저장된 모든 객체를 삭제한다.</td>
 *         </tr>
 *         <tr>
 *             <td>Object clone()</td>
 *             <td>HashSet을 복제해서 반환한다.(얕은 복사)</td>
 *         </tr>
 *         <tr>
 *             <td>boolean contains(Object o)</td>
 *             <td>지정된 객체를 포함하고 있는지 알려준다.</td>
 *         </tr>
 *         <tr>
 *             <td>boolean containsAll(Collection c)</td>
 *             <td>주어진 컬렉션에 저장된 모든 객체들을 포함하고 있는지 알려준다.</td>
 *         </tr>
 *         <tr>
 *             <td>boolean isEmpty()</td>
 *             <td>HashSet이 비어있는지 알려준다.</td>
 *         </tr>
 *         <tr>
 *             <td>Iterator iterator()</td>
 *             <td>iterator를 반환한다.</td>
 *         </tr>
 *         <tr>
 *             <td>boolean remove(Object o)</td>
 *             <td>지정된 객체를 HashSet에서 삭제한다.(성공하면 true, 실패하면 false)</td>
 *         </tr>
 *         <tr>
 *             <td>boolean removeAll(Collection c)</td>
 *             <td>주어진 컬렉션에 저장된 모든 객체와 동일한 것들을 HashSet에서 모두 삭제한다.(차집합)</td>
 *         </tr>
 *         <tr>
 *             <td>boolean retainAll(Collection c)</td>
 *             <td>주어진 컬렉션에 저장된 객체와 동일한 것만 남기고 삭제한다.(교집합)</td>
 *         </tr>
 *         <tr>
 *             <td>int size()</td>
 *             <td>저장된 객체의 개수를 반환한다.</td>
 *         </tr>
 *         <tr>
 *             <td>Object[] toArray()</td>
 *             <td>저장된 객체들을 객체배열의 형태로 반환한다.</td>
 *         </tr>
 *         <tr>
 *             <td>Object[] toArray(Object[] a)</td>
 *             <td>저장된 객체들을 주어진 객체배열(a)에 담는다.</td>
 *         </tr>
 *     </tbody>
 * </table>
 * <br>
 * <small>※ load factor는 컬렉션 클래스에 저장공간이 가득 차기 전에 미리 용량을 확보하기 위한 것으로 이 값을 0.8로 지정하면, 저장공간의 80%가 채워졌을 때 용량이 두 배로 늘어난다. 기본값은 0.75 즉 75%이다.</small>
 * <br>
 * 중복을 제거하는 동시에 저장한 순서를 유지하고자 한다면 HashSet대신 LinkedHashSet을 사용해야한다.<br>
 * HashSet의 add메서드는 새로운 요소를 추가하기 전에 기존에 저장된 요소와 같은 것인지 판별하기 위해 추가하려는 요소의 equals()와 hashCode()를 호출하기 때문에 equals()와 hashCode()를 목적에 맞게 오버라이딩해야 한다.<br>
 * 오버라이딩을 통해 작성된 hashCode()는 다음의 세 가지 조건을 만족 시켜야 한다.<br>
 * <pre><code>
 *	1. 실행 중인 애플리케이션 내의 동일한 객체에 대해서 여러 번 hashCode()를 호출해도 동일한 int값을 반환해야한다. 하지만, 실행시마다 동일한 int값을 반환할 필요는 없다.(단, equals메서드의 구현에 사용된 멤버변수와 값이 바뀌지 않았다고 가정한다.)
 *  2. equals메서드를 이용한 비교에 의해서 true를 얻은 두 객체에 대해 각각 hashCode()를 호출해서 얻은 결과는 반드시 같아야 한다.
 *  3. equals메서드를 호출했을 때 false를 반환하는 두 객체는 hashCode() 호출에 대해 같은 int값을 반환하는 경우가 있어도 괜찮지만, 해싱(hashing)을 사용하는 컬렉션의 성능을 향상시키기 위해서는 다른 int값을 반환하는 것이 좋다.
 * </code></pre>
 *
 */
public class Chapter11_1_8 {
}
