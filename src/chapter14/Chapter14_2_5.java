package chapter14;

/**
 * <h1>2.5 스트림의 최종 연산</h1><br>
 * 최종 연산은 스트림의 요소를 소모해서 결과를 만들어낸다. 그래서 최종 연산후에는 스트림이 닫히게 되고 더 이상 사용할 수 없다.<br>
 * 최종 연산의 결과는 스트림의 요소의 합과 같은 단일 값이거나, 스트림의 요소가 담긴 배열 또는 컬렉션일 수 있다.
 */
public class Chapter14_2_5 {

	/**
	 * <h5>forEach()</h5><br>
	 * forEach()는 peek()와 달리 스트림의 요소를 소모하는 최종연산이다.<br>
	 * <pre><code>
	 *     void forEach(Consumer<? super T> action)
	 * </code></pre>
	 */
	class Memo1 {

	}

	/**
	 * <h5>조건 검사 - allMatch(), anyMatch(), noneMatch(), findFirst(), findAny()</h5><br>
	 * 스트림의 요소에 대해 지정된 조건에 모든 요소가 일치하는 지, 일부가 일치하는지 아니면 어떤 요소도 일치하지 않는지 확인하는데 사용할 수 있는 메서드들이다.<br>
	 * 이 메서드들은 모두 매개변수로 Predicate를 요구하며, 연산결과로 boolean을 반환한다.<br>
	 * 스트림의 요소 중에서 조건에 일치하는 첫 번째 것을 반환하는 findFirst()가 있는데, 주로 filter()와 함께 사용되어 조건에 맞는 스트림의 요소가 있는지 확인하는데 사용된다.<br>
	 * 병렬 스트림의 경우에는 findFirst()대신 findAny()를 사용해야 한다.<br>
	 * findAny()와 findFirst()의 반환 타입은 Optional&ltT&gt;이며, 스트림의 요소가 없을 때는 비어있는 Optional객체를 반환한다.<br>
	 * <small>※비어있는 Optional객체는 내부적으로 null을 저장하고 있다.</small>
	 */
	class Memo2 {

	}

	/**
	 * <h5>통계 - count(), sum(), average(), max(), min</h5>
	 */
	class Memo3 {

	}

}
