package chapter14;

/**
 * <h1>2.3 스트림의 중간연산</h1>
 */
public class Chapter14_2_3 {

	/**
	 * <h5>스트림 자르기 - skip(), limit()</h5><br>
	 * skip()과 limit()은 스트림의 일부를 잘라낼 때 사용하며, 사용법은 아주 간단하다.<br>
	 * skip(3)은 처음 3개의 요소를 건너뛰고, limit(5)는 스트림의 요소를 5개로 제한한다.<br>
	 * <pre><code>
	 *     Stream<T> skip(long n)
	 *     Stream<T> limit(long maxSize)
	 * </code></pre>
	 */
	class Memo1 {

	}

	/**
	 * <h5>스트림의 요소 걸러내기 - filter(), distinct()</h5><br>
	 * distinct()는 스트림에서 중복된 요소들을 제거하고, filter()는 주어진 조건(Predicate)에 맞지 않는 요소를 걸러낸다.<br>
	 * <pre><code>
	 *     Stream<T> filter(Predicate<? super T> predicate)
	 *     Stream<T> distinct()
	 * </code></pre>
	 * <br>
	 * distinct()의 사용법은 간단하다.<br>
	 * <pre><code>
	 *   IntStream intStream = IntStream.of(1, 2, 2, 3, 3, 3, 4, 5, 5, 6);
	 *   IntStream.distinct().forEach(System.out::print); // 123456
	 * </code></pre>
	 * <br>
	 * filter()는 매개변수로 Predicate를 필요로 하는데, 아래와 같이 연산결과가 boolean인 람다식을 사용해도 된다.<br>
	 * <pre><code>
	 *     IntStream intStream = IntStream.rangeClosed(1, 10); // 1~10
	 *     IntStream.filter(i -> i%2 == 0).forEach(System.out::print); // 246810
	 * </code></pre>
	 * 필요하다면 filter()를 다른 조건으로 여러 번 사용할 수도 있다.<br>
	 * <pre><code>
	 *     // 아래의 두 문장은 동일한 결과를 얻는다.
	 *     intStream.filter(i -> i%2!= 0 && i%3 != 0).forEach(System.out::print); // 157
	 *     intStream.filter(i -> i%2!=0).filter(1 -> i%3 !=0).forEach(System.out::print);
	 * </code></pre>
	 */
	class Memo2 {

	}

	/**
	 * <h5>정렬 - sorted()</h5><br>
	 * 스트림을 정렬할 때는 sorted()를 사용하면 된다.<br>
	 * <pre><code>
	 *     Stream<T> sorted()
	 *     Stream<T> sorted(Comparator<? super T> comparator)
	 * </code></pre>
	 * <br>
	 * sorted()는 지정된 Comparator로 스트림을 정렬하는데, Comparator대신 int값을 반환하는 람다식을 사용하는 것도 가능하다. Comparator를 지정하지 않으면 스트림의 요소의 기본 정렬 기준(Comparable)으로 정렬한다.<br>
	 * 단, 스트림의 요소가 Comparable을 구현한 클래스가 아니면 예외가 발생한다.<br>
	 * 스트림의 요소가 Comparable을 구현한 경우, 매개변수 하나짜리를 사용하면 되고 그렇지 않은 경우, 추가적인 매개변수로 정렬기준(Comparator)을 따로 지정해 줘야한다.<br>
	 * 비교대상이 기본형인 경우, comparing()대신 위의 메서드를 사용하면 오토박싱과 언박싱과정이 없어서 더 효율적이다. 그리고 정렬 조건을 추가할 때는 thenComparing()을 사용한다.
	 */
	class Memo3 {

	}

	/**
	 * <h5>변환 - map()</h5><br>
	 * 스트림의 요소에 저장된 값 중에서 원하는 필드만 뽑아내거나 특정 형태로 변환해야 할 때가 있다. 이 때 사용하는 것이 바로 map()이다.<br>
	 * 이 메서드의 선언부는 아래와 같으며, 매개변수로 T타입을 R타입으로 변환해서 반환하는 함수를 지정해야 한다.<br>
	 * <pre><code>
	 *     Stream<R> map(Function<? super T, ? extends R> mapper)
	 * </code></pre>
	 * <br>
	 * map()역시 중간 연산이므로, 연산 결과는 String을 요소로 하는 스트림이다.그리고 map()도 filter()처럼 하나의 스트림에 여러 번 적용할 수 있다.
	 *
	 */
	class Memo4 {

	}

	/**
	 * <h5>조회 - peek()</h5><br>
	 * 연산과 연산 사이에 올바르게 처리되었는지 확인하고 싶다면, peek()를 사용하자.<br>
	 * forEach()와 달리 스트림의 요소를 소모하지 않으므로 연산 사이에 여러 번 끼워 넣어도 문제가 되지 않는다.
	 */
	class Memo5 {

	}

	/**
	 * <h5>mapToInt(), mapToLong(), mapToDouble()</h5><br>
	 * map()은 연산의 결과로 Stream&lt;T&gt;타입의 스트림을 반환하는데, 스트림의 요소를 숫자로 변환하는 경우 IntStream과 같은 기본형 스트림으로 변환하는 것이 더 유용할 수 있다.<br>
	 * Stream&lt;T&gt;타입의 스트림을 기본형으로 변환할 때 사용하는 메서드<br>
	 * <pre><code>
	 *     DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper)
	 *     IntStream mapToInt(ToIntFunction<? super T> mapper)
	 *     LongStream mapToLong(ToLongFunction<? super T> mapper)
	 * </code></pre>
	 * <br>
	 * IntStream과 같은 기본형 스트림은 아래와 같이 숫자를 다루는데 편리한 메서드들을 제공한다.<br>
	 * <small>※max()와 min()은 Stream에도 정의되어 있지만, 매개변수로 Comparator를 지정해야 한다는 차이가 있다.</small><br>
	 * <pre><code>
	 *     int sum() 스트림의 모든 요소의 총합
	 *     OptionalDouble average() sum() / (double)count()
	 *     OptionalInt max() 스트림의 요소 중 제일 큰 값
	 *     OptionalInt min() 스트림의 요소 중 제일 작은 값
	 * </code></pre>
	 * <br>
	 * OptionalInt, OptionalDouble 등은 일종의 래퍼 클래스로 각각 int값과 Double값을 내부적으로 가지고 있다.<br>
	 * 그리고 이 메서드들은 최종연산이기 때문에 호출 후에 스트림이 닫힌다는 점을 주의해야 한다.<br>
	 * IntStream을 Stream&lt;T&gt;로 변환할 때는 mapToObj()를, Stream&lt;Integer&gt;로 변환할 때는 boxed()를 사용한다.<br>
	 * 참고로 CharSequence에 정의된 chars()는 String이나 StringBuffer에 저장된 문자들을 IntStream으로 다룰 수 있게 해준다.
	 */
	class Memo6 {

	}

	/**
	 * <h5>flatMap() - Stream&lt;T[]&gt;를 Stream&lt;T&gt;로 변환</h5><br>
	 * 스트림의 요소가 배열이거나 map()의 연산결과가 배열인 경우, 즉 스트림의 타입이 Stream&lt;T[]&gt;인 경우, Stream&lt;T&gt;로 다루는 것이 더 편리할 때가 있다. 그럴 때는 map()대신 flatMap()을 사용하면 된다.<br>
	 * map()과 flatMap()의 차이<br>
	 * <pre><code>
	 *     Stream<String> map() -> Stream<Stream<String>>
	 *     Stream<String> flatMap() -> Stream<String>
	 * </code></pre>
	 * <br>
	 * 드물지만, 스트림을 요소로 하는 스트림, 즉 스트림의 스트림을 하나의 스트림으로 합칠 때도 flatMap()을 사용한다.
	 */
	class Memo7 {

	}

}
