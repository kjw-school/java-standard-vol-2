package chapter11;

/**
 * <h1>1.6 Arrays</h1><br>
 * Arrays클래스에는 배열을 다루는데 유용한 메서드가 정의되어 있다.<br>
 * <small>※ Arrays에 정의된 메서드는 모두 static메서드이다.</small>
 */
public class Chapter11_1_6 {

	/**
	 * <h5>배열의 복사 - copyOf(), copyOfRange()</h5><br>
	 * copyOf()는 배열 전체를, copyOfRange()는 배열의 일부를 복사해서 새로운 배열을 만들어 반환한다. copyOfRange()에 지정된 범위의 끝은 포함되지 않는다.
	 */
	class Memo1 {

	}

	/**
	 * <h5>배열 채우기 - fill(), setAll()</h5><br>
	 * fill()은 배열의 모든 요소를 지정된 값으로 채운다. setAll()은 배열을 채우는데 사용할 함수형 인터페이스를 매개변수로 받는다.<br>
	 * 이 메서드를 호출할 때는 함수형 인터페이스를 구현한 객체를 매개변수로 지정하던가 아니면 람다식을 지정해야 한다.
	 */
	class Memo2 {

	}

	/**
	 * <h5>배열의 정렬과 검색 - sort(), binarySearch()</h5><br>
	 * sort()는 배열을 정렬할 때, 그리고 배열에 저장된 요소를 검색할 때는 binarySearch()를 사용한다. binarySearch()는 배열에서 지정된 값이 저장된 위치(index)를 찾아서 반환하는데, 반드시 배열이 정렬된 상태이어야 올바른 결과를 얻는다.<br>
	 * 배열의 첫 번째 요소부터 순서대로 하나씩 검색하는 것을 '순차 검색(linear search)'이라고 하는데, 이 검색 방법은 배열이 정렬되어 있을 필요는 없지만 배열의 요소를 하나씩 비교하기 때문에 시간이 많이 걸린다.<br>
	 * 이진 검색(binary search)은 배열의 검색할 범위를 반복적으로 절반씩 줄여나가면서 검색하기 때문에 검색속도가 상당히 빠르다. 배열의 길이가 10배가 늘어나도 검색 횟수는 3~4회 밖에 늘어나지 않으므로 큰 배열의 검색에 유리하다. 배열이 정렬이 되어 있는 경우에만 사용할 수 있다는 단점이 있다.
	 */
	class Memo3 {

	}

	/**
	 * <h5>배열의 비교와 출력 - equals(), toString()</h5><br>
	 * toString()은 배열의 모든 요소를 문자여롤 편하게 출력할 수 있다.<br>
	 * toString()은 일차원 배열에만 사용할 수 있으므로, 다차원 배열에는 deepTo String()을 사용해야 한다.<br>
	 * deepToString()은 배열의 모든 요소를 재귀적으로 접근해서 문자열을 구성하므로 2차원뿐만 아니라 3차원 이상의 배열에도 동작한다.<br>
	 * equals()는 두 배열에 저장된 모든 요소를 비교해서 같으면 true, 다르면 false를 반환한다. equals()도 일차원 배열에만 사용가능하므로, 다차원 배열의 비교에는 deepEquals()를 사용해야 한다.<br>
	 * 2차원 String배열을 equals()로 비교하면 배열에 저장된 내용이 같은데도 false를 결과로 얻는다. 다차원 배열은 '배열의 배열'의 형태로 구성하기 때문에 equals()로 비교하면,<br>
	 * 문자열을 비교하는 것이 아니라 '배열에 저장된 배열의 주소'를 비교하게 된다. 서로 다른 배열은 항상 주소가 다르므로 false를 결과로 얻는다.
	 */
	class Memo4 {

	}

	/**
	 * <h5>배열을 List로 변환 - asList(Object... a)</h5><br>
	 * asList()는 배열을 List에 담아서 반환한다. 매개변수의 타입이 가변인수라서 배열 생성없이 저장할 요소들만 나열하는 것도 가능하다.<br>
	 * 한 가지 주의할 점은 asList()가 반환한 List의 크기를 변경할 수 없다는 것이다.<br>
	 * 즉, 추가 또는 삭제가 불가능하다. 저장된 내용은 변경가능하다. 만일 크기를 변경할 수 있는 List가 필요하다면, 다음과 같이 하면 된다.<br>
	 * <pre><code>
	 *     List list = new ArrayList(Arrays.asList(1,2,3,4,5));
	 * </code></pre>
	 */
	class Memo5 {

	}

	/**
	 * <h5>parallelXXX(), spliterator(), stream()</h5><br>
	 * 이 외에도 'parallel'로 시작하는 이름의 메서드들이 있는데, 이 메서드들은 보다 빠른 결과를 얻기 위해 여러 쓰레드가 작업을 나누어 처리하도록 한다.<br>
	 * spliterator()는 여러 쓰레드가 처리할 수 있게 하나의 작업을 여러 작업으로 나누는 Spliterator를 반환하며, stream()은 컬렉션을 스트림으로 변환한다.
	 */
	class Memo6 {

	}

}
