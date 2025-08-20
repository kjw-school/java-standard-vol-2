package chapter11;

/**
 * <h1>1.10 HashMap과 Hashtable</h1><br>
 * Hashtable과 HashMap의 관계는 Vector와 ArrayList의 관계와 같아서 Hashtable보다는 새로운 버전인 HashMap을 사용할 것을 권한다.<br>
 * HashMap은 Map을 구현했으므로 앞에서 살펴본 Map의 툭징, 키(key)와 값(value)을 묶어서 하나의 데이터(entry)로 지정한다는 특징을 갖는다.<br>
 * 해싱(hashing)을 사용하기 때문에 많은 양의 데이터를 검색하는데 있어서 뛰어난 성능을 보인다.<br>
 * HashMap은 Entry라는 내부 클래스를 정의하고, 다시 Entry타입의 배열을 선언하고 있다.<br>
 * 키(key)와 값(value)은 별개의 값이 아니라 서로 관련된 값이기 때문에 각각의 배열로 선언하기 보다는 하나의 클래스로 정의해서 하나의 배열로 다루는 것이 데이터의 무결성(integrity)적인 측면에서 더 바람직하기 때문이다.<br>
 * HashMap은 키와 값을 각각 Object타입으로 저장한다. 즉 (Object, Object)의 형태로 저장하기 때문에 어떠한 객체도 저장할 수 있다면 키는 주로 String을 대문자 또는 소문자로 통일해서 사용하곤 한다.<br>
 * <pre><code>
 *     키(key) - 컬렉션 내의 키(key) 중에서 유일해야 한다.
 *     값(value) - 키(key)와 달리 데이터의 중복을 허용한다.
 * </code></pre>
 * <br>
 * 키는 저장된 값을 찾는데 사용되는 것이기 때문에 컬렉션 내에서 유일(unique)해야 한다.<br>
 * HashMap에 저장된 데이터를 하나의 키로 검색했을 때 결과가 단 하나이어야 함을 뜻한다.<br>
 * 만일 하나의 키에 대해 여러 검색결과 값을 얻는다면 원하는 값이 어떤 것인지 알 수 없기 때문이다.<br>
 * Map은 값은 중복을 허용하지만 키는 중복을 허용하지 않기 때문에 저장하려는 두 데이터 중에서 어느 쪽을 키로 할 것인지를 잘 결정해야한다.<br>
 * <small>※ Hashtable은 키(key)나 값(value)으로 null을 허용하지 않지만, HashMap은 허용한다. 그래서 'map.put(null, null)', 이나 'map.get(null)', 과 같이 할 수 있다.</small><br>
 * entrySet()을 이용해서 키와 값을 함께 읽어 올 수도 있고 keySet()이나 values()를 이용해서 키와 값을 따로 읽어 올 수 있다.<br>
 * HashMap은 데이터를 키와 값을 모두 Object타입으로 저장하기 때문에 HashMap의 값(value)으로 HashMap을 다시 저장할 수 있다. 이렇게 함으로써 하나의 키에 다시 복수의 데이터를 저장할 수 있다.
 */
public class Chapter11_1_10 {

	/**
	 * <h5>해싱과 해시함수</h5><br>
	 * 해싱이란 해시함수(hash function)를 이용해서 데이터를 해시테이블(hash table)에 저장하고 검색하는 기법을 말한다.<br>
	 * 해시함수는 데이터가 저장되어 있는 곳을 알려 주기 때문에 다량의 데이터 중에서도 원하는 데이터를 빠르게 찾을 수 있다.<br>
	 * 해싱에서 사용하는 자료구조는 배열과 링크드 리스트의 조합으로 되어 있다.<br>
	 * 저장할 ㄷ게이터의 키를 해시함수에 넣으면 배열의 한 요소를 얻게 되고, 다시 그 곳에 연결되어 있는 링크드 리스트에 저장하게 된다.<br>
	 * 링크드 리스트는 검색에 불리한 자료구조이기 때문에 링크드 리스트의 크기가 커질수록 검색속도가 떨어지게 된다.<br>
	 * 배열은 배열의 크기가 커져도, 원하는 요소가 몇 번쨰에 있는 지만 알면 아래의 공식에 의해서 빠르게 원하는 값을 찾을 수 있다.<br>
	 * <pre><code>
	 *     배열의 인덱스가 n인 요소의 주소 = 배열의 시작주소 + type의 size * n
	 * </code></pre>
	 * <br>
	 * 하나의 서랍에 많은 데이터가 저장되어 있는 형태보다는 많은 서랍에 하나의 데이터만 저장되어 있는 형태가 더 빠른 검색결과를 얻을 수 있다.<br>
	 * 하나의 링크드 리시트(서랍)에 최소한의 데이터만 저장되려면, 저장된 데이터의 크기를 고려해서 HashMap의 크기를 적절하게 지정해주어야 하고, 해시함수가 서로 다른 키(주민번호)에 대해서 중복된 해시코드(서랍위치)의 반환을 최소화해야 한다.<br>
	 * 해상을 구현하는 과정에서 제일 중요한 것은 해시함수의 알고리즘이다.<br>
	 * 실제로는 HashMap과 같이 해싱을 구현한 컬렉션 클래스에서는 Object클래스에 정의된 hashCode()를 해시함수로 사용한다. <br>
	 * Object클래스에 정의된 hashCode()는 객체의 주소를 이용하는 알고리즘으로 해시코드를 만들어 내기 때문에 모든 객체에 대해 hashCode()를 호출한 결과가 서로 유일한 훌륭한 방법이다.<br>
	 * 서로 다른 두 객체에 대해 equals()로 비교한 결과가 true인 동시에 hashCode()의 반환값이 같아야 같은 객체로 인식한다.<br>
	 * HashMap에서도 같은 방법으로 객체를 구별하며, 이미 존재하는 키에 대한 값을 저장하면 기존의 값을 새로운 값으로 덮어쓴다.<br>
	 * <small>※ equals()로 비교한 결과가 false이고 해시코드가 같은 경우는 같은 링크드 리스트(서랍)에 저장된 서로 다른 두 데이터가 된다.</small>
	 */
	class Memo1 {

	}

	/**
	 * <h5>1.11 TreeMap</h5><br>
	 * TreeMap은 이름에서 알 수 있듯이 이진검색트리의 형태로 키와 값의 쌍으로 이루어진 데이터를 저장한다.<br>
	 * 검색과 정렬에 적합한 컬렉션 클래스이다.<br>
	 * 검색에 관한한 대부분의 경우에서 HashMap이 TreeMap보다 더 뛰어나므로 HashMap을 사용하는 것이 좋다. 다만 범위 검색이나 정렬이 필요한 경우에는 TreeMap을 사용하자.
	 */
	class Memo2 {

	}

	/**
	 * <h5>1.12 Properties</h5><br>
	 * Properties는 HashMap의 구버전인 Hashtable을 상속받아 구현한 것으로, Hashtable은 키와 값을 (Object, Object)의 형태로 저장하는데 비해 Properties는 (String, String)의 형태로 저장하는 보다 단순화된 컬렉션클래스이다.<br>
	 * 주로 애플리케이션의 환경설정과 관련된 속성(property)을 저장하는데 사용되며 데이터를 파일로부터 읽고 쓰는 편리한 기능을 제공한다.<br>
	 * 데이터를 저장하는데 사용되는 setProperty()는 단순히 Hashtable의 put메서드를 호출할 뿐이다. 그리고 setProperty()는 기존에 같은 키로 저장된 값이 있는 경우 그 값을 Object타입으로 반환하며, 그렇지 않을 때는 null을 반환한다.<br>
	 * <pre><code>
	 *     Object setProperty(String key, String value)
	 * </code></pre>
	 * <br>
	 * getProperty()는 Properties에 저장된 값을 읽어오는 일을 하는데, 만일 읽어오려는 키가 존재하지 않으면 지정된 기본값(defaultValue)을 반환한다.<br>
	 * <pre><code>
	 *     String getProperty(String key)
	 *     String getProperty(String key, String defaultValue)
	 * </code></pre>
	 * <br>
	 * list메서드를 이용하면 Properties에 저장된 모든 데이터를 화면 또는 파일에 편리하게 출력할 수 있다.<br>
	 * <pre><code>
	 *     void list(PrintStream out)
	 *     void list(PrintWriter out)
	 * </code></pre>
	 * <br>
	 * System클래스의 getProperties()를 호출하면 시스템과 관련된 속성이 저장된 Properties를 가져올 수 있다.<br>
	 * 이 중에서 원하는 속성을 getProperty()를 통해 얻을 수 있다.
	 */
	class Memo3 {

	}

	/**
	 * <h5>1.13 Collections</h5><br>
	 *
	 */
	class Memo4 {

	}

}
