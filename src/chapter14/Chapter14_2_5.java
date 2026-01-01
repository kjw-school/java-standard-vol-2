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
	 * <h5>통계 - count(), sum(), average(), max(), min()</h5><br>
	 * IOntStream과 같은 기본형 스트림에는 스트림의 요소들에 대한 통계 정보를 얻을 수 있는 메서드들이 있다.<br>
	 * 그러나 기본형 스트림이 아닌 경우에는 통계와 관련된 메서드들이 아래의 3개뿐이다.<br>
	 * <small>※기본형 스트림의 min(), max()와 달리 매개변수로 Comparator를 필요로 한다는 차이가 있다.</small><br>
	 * <pre><code>
	 *     long count()
	 *     Optional<T> max(Comparator<? super T> comparator)
	 *     Optional<T> min(Comparator<? super T> comparator)
	 * </code></pre>
	 */
	class Memo3 {

	}

	/**
	 * <h5>리듀싱 - reduce()</h5><br>
	 * reduce()는 이름에서 짐작할 수 있듯이, 스트림의 요소를 줄여나가면서 연산을 수행하고 최종결과를 반환한다.<br>
	 * 그래서 매개변수의 타입이 BinaryOperator&lt;T&gt;인 것이다.<br>
	 * 처음 두 요소를 가지고 연산한 결과를 가지고 그 다음 요소와 연산한다. 이 과정에서 스트림의 요소를 하나씩 소모하게 되며, 스트림의 모든 요소를 소모하게 되면 그 결과를 반환한다.<br>
	 * <pre><code>
	 *	 Optional<T> reduce(BinaryOperator<T> accumulator)
	 * </code></pre>
	 * <br>
	 * 연산결과의 초기값(identity)을 갖는 reduce()도 있는데, 이 메서드들은 초기값과 스트림의 첫 번째 요소로 연산을 시작한다.<br>
	 * 스트림의 요소가 하나도 없는 경우, 초기값이 반환되므로, 반환 타입이 Optional&lt;T&gt;가 아니라 T이다.<br>
	 * <small>※BinaryOperator&lt;T&gt; BiFunction의 자손이며, BiFunction&llt;T,T,T&gt;와 동등하다.</small><br>
	 * reduce()를 사용하는 방법은 간단하다. 그저 초기값(identity)과 어떤 연산(BinaryOperator)으로 스트림의 요소를 줄여나갈 것인지만 결정하면 된다.
	 */
	class Memo4 {

	}

	static class StreamEx5 {

	}

}
