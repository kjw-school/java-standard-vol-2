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

	/**
	 * <h5>2.6 collect()</h5><br>
	 * collect()는 스트림의 요소를 수집하는 최종 연산으로 앞서 배운 리듀싱(reducing)과 유사하다.<br>
	 * collect()가 스트림의 요소를 수집하려면, 어떻게 수집할 것인가에 대한 방법이 정의되어 있어야 하는데, 이 방법을 정의한 것인 바로 컬렉터(collector)이다.<br>
	 * 컬렉터는 Collector인터페이스를 구현한 것으로, 직접 구현할 수도 있고 미리 작성된 것을 사용할 수도 있다.<br>
	 * <pre><code>
	 *     collect() 스트림의 최종연산, 매개변수로 컬렉터를 필요로 한다.
	 *     Collector 인터페이스, 컬렉터는 이 인터페이스를 구현해야한다.
	 *     Collectors 클래스, static메서드로 미리 작성된 컬렉터를 제공한다.
	 * </code></pre>
	 * <br>
	 * collect()는 매개변수의 타입이 Collector인데, 매개변수가 Collector를 구현한 클래스의 객체이어야 한다는 뜻이다.
	 */
	class Memo5 {

	}

	/**
	 * <h5>스트림을 컬렉션과 배열로 변환 - toList(), toSet(), toMap(), toCollection(), toArray()</h5><br>
	 * 스트림의 모든 요소를 컬렉션에 수집하려면, Collectors클래스의 toList()와 같은 메서드를 사용하면 된다.<br>
	 * List나 Set이 아닌 특정 컬렉션을 지정하려면, toCollection()에 해당 컬렉션의 생성자 참조를 매개변수로 넣어주면 된다.<br>
	 * <pre><code>
	 *     List<String> names = stuStream.map(Student::getName).collect(Collectors.toList());
	 *     ArrayList<String> list = names.stream().collect(Collectors.toCollection(ArrayList::new));
	 * </code></pre>
	 * <br>
	 * Map은 키와 값의 쌍으로 저장해야하므로 객체의 어떤 필드를 키로 사용할지와 값으로 사용할지를 지정해줘야 한다.<br>
	 * <pre><code>
	 *     Map<String, Person> map = personStream.collect(Collectors.toMap(p -> p.getRegId(), p -> p));
	 * </code></pre>
	 * <br>
	 * 위의 문장은 요소 타입이 Person인 스트림에서 사람의 주민번호(regId)를 키로 하고, 값으로 Person객체를 그대로 저장한다.<br>
	 * <small>※항등 함수를 의미하는 람다식 'p -> p' 대신 Function.identity()를 쓸 수도 있다.</small><br>
	 * 스트림에 저장된 요소들을 "T[]"타입의 배열로 변환하려면, toArray()를 사용하면 된다.<br>
	 * 단, 해당 타입의 생성자 참조를 매개변수로 지정해줘야 한다. 만일 매개변수를 지정하지 않으면 반환되는 배열의 타입은 'Object[]'이다.
	 */
	class Memo6 {

	}

	/**
	 * <h5>통계 - counting(), summingInt(), averagingInt(), maxBy(), minBy()</h5>
	 */
	class Memo7 {

	}

}
