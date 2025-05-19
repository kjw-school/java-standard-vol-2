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
	 * <h5>변환 - map()</h5>
	 */
	class Memo4 {

	}

}
