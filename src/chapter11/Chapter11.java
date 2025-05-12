package chapter11;

/**
 * <h1>Chapter11. 컬렉션 프레임웍 Collections Framework</h1>
 */
public class Chapter11 {

	/**
	 * <h5>1. 컬렉션 프레임웍(Collections Framework)</h5><br>
	 * 컬렉션 프레임웍이란, '데이터 군(群)을 저장하는 클래스들을 표준화한 설계'를 뜻한다.<br>
	 * 컬렉션(Collection)은 다수(多數)의 데이터, 즉 데이터 그룹을, 프레임웍은 표준화된 프로그래밍 방식을 의미한다.<br>
	 * <small>Java API문서에서는 컬렉션 프레임웍을 '데이터 군(群, group)을 다루고 표현하기 위한 단일화된 구조(architecture)'라고 정의하고 있다.</small><br>
	 * JDK1.2 이전까지는 Vector, Hashtable, Properties와 같은 컬렉션 클래스, 다수의 데이터를 저장할 수 있는 클래스, 들을 서로 다른 각자의 방식으로 처리해야 했으나 JDK1.2부터 컬렉션 프레임웍이 등장하면서 다양한 종류의 컬렉션 클래스가 추가되고 모든 컬렉션클래스를 표준화된 방식으로 다룰 수 있도록 체계화되었다.<br>
	 * <small>앞으로 Vector와 같이 다수의 데이터를 저장할 수 있는 클래스를 '컬렉션 클래스'라고 하겠다.</small>
	 */
	class Memo1 {

	}

	/**
	 * <h5>1.1 컬렉션 프레임웍의 핵심 인터페이스</h5><br>
	 * 컬렉션 프레임웍에서는 컬렉션데이터 그룹을 크게 3가지 타입이 존재한다고 인식하고 각컬렉션을 다루는데 필요한 기능을 가진 3개의 인터페이스를 정의하였다.<br>
	 * 인터페이스 List와 Set의 공통된 부분을 다시 뽑아서 새로운 인터페이스인 Collection을 추가로 정의하였다.<br>
	 * <small>JDK1.5부터 Iterable인터페이스가 추가되고 이를 Collection인터페이스가 상속받도록 변겨되었으나 이것은 단지 인터페이스들의 공통적인 메서드인 iterator()를 뽑아서 중복을 제거하기 위한 것에 불과하므로 상속계층도에서 별 의미가 없다.</small><br>
	 * <table border="1">
	 *     <thead>
	 *         <td>인터페이스</td>
	 *         <td>특징</td>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>List</td>
	 *             <td>순서가 있는 데이터의 집합, 데이터의 중복을 허용한다. 예)대기자 명단</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Set</td>
	 *             <td>순서를 유지하지 않는 데이터의 집합, 데이터의 중복을 허용하지 않는다. 예)양의 정수집합, 소수의 집합, 구현클래스: HastSet, TreeSet 등</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Map</td>
	 *             <td>키(key)와 값(value)의 쌍(pair)으로 이루어진 데이터의 집합, 순서는 유지되지 않으며, 키는 중복을 허용하지 않고, 값은 중복을 허용한다.예)우편번호, 지역번호(전화번호), 구현클래스: HashMap, TreeMap, Hashtable, Properties 등</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * <small>키(Key)란, 데이터 집합 중에서 어떤 값(value)을 찾는데 열쇠(key)가 된다는 의미에서 붙여진 이름이다. 그래서 키(Key)는 중복을 허용하지 않는다.</small><br>
	 * 컬렉션 프레임웍의 모든 컬렉션 클래스들은 List, Set, Map 중의 하나를 구현하고 있으며,<br>
	 * 구현한 인터페이스의 이름이 클래스의 이름에 포함되어있어서 이름만으로도 클래스의 특징을 쉽게 알 수 있도록 되어있다.<br>
	 */
	class Memo2 {
	}

	/**
	 * <h5>Collection인터페이스</h5><br>
	 * Collection인터페이스는 컬렉션 클래스에 저장된 데이터를 읽고, 추가하고 삭제하는 등 컬렉션을 다루는데 가장 기본적인 메서드들을 정의하고 있다.<br>
	 * <b>Collection인터페이스에 정의된 메서드</b><br>
	 * <table border="1">
	 * 		<thead>
	 * 		 	<td>메서드</td>
	 * 		 	<td>설명</td>
	 * 		</thead>
	 * 		<tbody>
	 * 		 	<tr>
	 * 		 	    <td>boolean add(Object o), boolean addAll(Collection c)</td>
	 * 		 	    <td>지정된 객체(o) 또는 Collection(c)의 객체들을 Collection에 추가한다.</td>
	 * 		 	</tr>
	 * 		 	<tr>
	 * 		 	    <td>void clean()</td>
	 * 		 	    <td>Collection의 모든 객체를 삭제한다.</td>
	 * 		 	</tr>
	 * 		 	<tr>
	 * 		 	    <td>boolean contains(Object o), boolean containsAll(Collection c)</td>
	 * 		 	    <td>지정된 객체(o) 또는 Collection의 객체들이 Collection에 포함되어 있는지 확인한다.</td>
	 * 		 	</tr>
	 * 		 	<tr>
	 * 		 	    <td>boolean equals(Object o)</td>
	 * 		 	    <td>동일한 Collection인지 비교한다.</td>
	 * 		 	</tr>
	 * 		 	<tr>
	 * 		 	    <td>int hashCode()</td>
	 * 		 	    <td>Collection의 hash code를 반환한다.</td>
	 * 		 	</tr>
	 * 		 	<tr>
	 * 		 	    <td>boolean isEmpty()</td>
	 * 		 	    <td>Collection이 비어있는지 확인한다.</td>
	 * 		 	</tr>
	 * 		 	<tr>
	 * 		 	    <td>Iterator iterator()</td>
	 * 		 	    <td>Collection Iterator를 얻어서 반환한다.</td>
	 * 		 	</tr>
	 * 		 	<tr>
	 * 		 	    <td>boolean remove(Object o)</td>
	 * 		 	    <td>지정된 객체를 삭제한다.</td>
	 * 		 	</tr>
	 * 		 	<tr>
	 * 		 	    <td>boolean removeAll(Collection c)</td>
	 * 		 	    <td>지정된 Collection에 포함된 객체들을 삭제한다.</td>
	 * 		 	</tr>
	 * 		 	<tr>
	 * 		 	    <td>boolean retainAll(Collection c)</td>
	 * 		 	    <td>지정된 Collection에 포함된 객체만을 남기고 다른 객체들은 Collection 에서 삭제한다. 이 작업으로 인해 Collection에 변화가 있으면 true를 그렇지 않으면 false를 반환한다.</td>
	 * 		 	</tr>
	 * 		 	<tr>
	 * 		 	    <td>int size()</td>
	 * 		 	    <td>Collection에 저장된 객체의 개수를 반환한다.</td>
	 * 		 	</tr>
	 * 		 	<tr>
	 * 		 	    <td>Object[] toArray()</td>
	 * 		 	    <td>Collection에 저장된 객체를 객체배열(Object[])로 반환한다.</td>
	 * 		 	</tr>
	 * 		 	<tr>
	 * 		 	    <td>Object[] toArray(Object[] a)</td>
	 * 		 	    <td>지정된 배열에 Collection의 객체를 저장해서 반환한다.</td>
	 * 		 	</tr>
	 * 		</tbody>
	 * </table>
	 */
	class Memo3 {

	}

	/**
	 * <h5>List인터페이스</h5><br>
	 * List인터페이스는 중복을 허용하면서 저장순서가 유지되는 컬렉션을 구현하는데 사용된다.<br>
	 */
	class Memo4 {

	}

	/**
	 * <h5>Set인터페이스</h5><br>
	 * Set인터페이스는 중복을 허용하지 않고 저장순서가 유지되지 않는 컬렉션 클래스를 구현하는데 사용된다.<br>
	 * Set인터페이스를 구현한 클래스로는 HastSet, TreeSet 등이 있다.
	 */
	class Memo5 {

	}

	/**
	 * <h5>Map인터페이스</h5><br>
	 * Map인터페이스는 키(key)와 값(value)을 하나의 쌍으로 묶어서 저장하는 컬렉션 클래스를 구현하는 데 사용된다.<br>
	 * 키는 중복될 수 없지만 값은 중복을 허용한다.<br>
	 * 기존에 저장된데이터와 중복된 키와 값을 저장하면 기존의 값은 없어지고 마지막에 저장된 값이 남게된다.<br>
	 * <small>Map이란 개념은 어떤 두 값을 연결한다는 의미에서 붙여진 이름이다.</small><br>
	 * Set keySet() - Map에 저장된 모든 key객체를 반환한다.<br>
	 * Collection values() - Map에 저장된 모든 value객체를 반환한다.<br>
	 * values()에서는 반환타입이 Collection이고, keySet()에서는 반환타입이 Set인 것에 주목하자,<br>
	 * Map인터페이스에서 값(value)은 중복을 허용하기 때문에 Collection타입으로 반환하고, 키(key)는 중복을 허용하지 않기 때문에 Set타입으로 반환한다.
	 */
	class Memo6 {

	}

	/**
	 * <h5>Map.Entry인터페이스</h5><br>
	 * Map.Entry인터페이스는 Map인터페이스의 내부 인터페이스이다.<br>
	 * 내부 클래스와 같이 인터페이스도 인터페이스 안에 인터페이스를 정의하는 내부 인터페이스(inner interface)를정의하는 것이 가능하다.<br>
	 * Map에 저장되는 key-value쌍을 다루기 위해 내부적으로 Entry인터페이스를 정의해놓았다.<br>
	 */
	class Memo7 {
	}

}
