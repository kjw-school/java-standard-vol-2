package chapter14;

import java.util.Iterator;
import java.util.stream.Stream;

/**
 * <h1>2. 스트림(stream)</h1>
 */
public class Chapter14_2 {

	/**
	 * <p>
	 *     <h5>2.1 스트림이란?</h5><br>
	 *     지금까지 우리는 많은 수의 데이터를 다룰 때, 컬렉션이나 배열에 데이터를 담고 원하는 결과를 얻기 위해 {@code for}문과 {@link Iterator}를 이용해서<br>
	 *     코드를 작성해왔다. 그러나 이러한 방식으로 작성된 코드는 너무 길고 알아보기 어렵다. 그리고 재사용성도 떨어진다.<br>
	 *      또 다른 문제는 데이터 소스마다 다른 방식으로 다뤄야한다는 것이다. {@link java.util.Collection}이나 {@link Iterator}와 같은 인터페이스를 이용해서 컬렉션을<br>
	 *     다루는 방식을 표준화하기는 했지만, 각 컬렉션 클래스에는 같은 기능의 메서드들이 중복해서 정의되어 있다.<br>
	 *     이러한 문제점들을 해결하기 위해서 만든 것이 '스트림({@link Stream})'이다. 스트림은 데이터 소스를 추상화하고, 데이터를 다루는데 자주 사용되는 메서드들을 정의해 놓았다.<br>
	 *     데이터소스를 추상화하엿다는 것은, 데이터 소스가 무엇이던 간에 같은 방식으로 다룰 수 있게 되었다는 것과 코드의 재사용성이 높아진다는 것을 의미한다.<br>
	 *     스트림을 이용하면, 배열이나 컬렉션뿐만 아니라 파일에 저장된 데이터도 모두 같은 방식으로 다룰 수 있다.
	 * </p>
	 */
	class Memo1 {
	}

	/**
	 * <p>
	 *     <h5>스트림은 데이터 소스를 변경하지 않는다.</h5><br>
	 *     스트림은 데이터 소스로 부터 데이터를 일기만할 뿐, 데이터 소스를 변경하지 않는다는 차이가 있다. 필요하다면, 정렬된 결과를 컬렉션이나 배열에 담아서 반환할 수도 있다.
	 * </p>
	 */
	class Memo2 {
	}

	/**
	 * <p>
	 *     <h5>스트림은 일회용이다.</h5><br>
	 *     스트림은 {@link Iterator}처럼 일회용이다. {@link Iterator}로 컬렉션의 요소를 모두 읽고 나면 다시 사용할 수 없는 것처럼,<br>
	 *     스트림도 한번 사용하면 닫혀서 다시 사용할 수 없다. 필요하다면 스트림을 다시 생성해야한다.
	 * </p>
	 */
	class Memo3 {
	}

	/**
	 * <p>
	 *     <h5>스트림은 작업을 내부 반복으로 처리한다.</h5><br>
	 *     스트림을 이용한 작업이 간결할 수 있는 비결중의 하나가 바로 '내부 반복'이다. 내부 반복이라는 것은 반복문을 메서드의 내부에 숨길 수 있다는 것을 의미한다.
	 * </p>
	 */
	class Memo4 {
	}

	/**
	 *  <h5>스트림의 연산</h5><br>
	 *  스트림이 제공하는 다양한 연산을 이용해서 복잡한 작업들을 간단히 처리할 수 있다.<br>
	 *  <small>※ 스트림에 정의된 메서드 중에서 데이터 소스를 다루는 작업을 수행하는 것을 연산(operation)이라고 한다.</small><br>
	 *  스트림이 제공하는 연산은 중간 연산과 최종 연산으로 분류할 수 있는데, 중간 연산은 연산결과를 스트림으로 반환하기 떄문에 중간 연산을 연속해서 연결할 수 있다.<br>
	 *  반면에 최종 연산은 스트림의 요소를 소모하면서 연산을 수행하므로 단 한번만 연산이 가능하다.<br>
	 *  <b>중간 연산</b> - 연산 결과가 스트림인 연산. 스트림에 연속해서 중간 연산할 수 있음.<br>
	 *  <b>최종 연산</b> - 연산 결과가 스트림이 아닌 연산. 스트림의 요소를 소모하므로 단 한번만 가능<br>
	 *  모든 중간 연산의 결과는 스트림이지만, 연산 전의 스트림과 같은 것은 아니다.<br>
	 *  스트림의 중간 연산 목록<br>
	 *  <table border="1">
	 *      <thead>
	 *          <th>중간 연산</th>
	 *          <th>설명</th>
	 *      </thead>
	 *      <tbody>
	 *          <tr>
	 *              <td>Stream&lt;T&gt; distinct()</td>
	 *              <td>중복을 제거</td>
	 *          </tr>
	 *          <tr>
	 *              <td>Stream&lt;T&gt; filter(Predicate&lt;T&gt; predicate)</td>
	 *              <td>조건에 안 맞는 요소 제외</td>
	 *          </tr>
	 *          <tr>
	 *              <td>Stream&lt;T&gt; limit(long maxSize)</td>
	 *              <td>스트림의 일부를 잘라낸다.</td>
	 *          </tr>
	 *          <tr>
	 *              <td>Stream&lt;T&gt; skip(long n)</td>
	 *              <td>스트림의 일부를 건너뛴다.</td>
	 *          </tr>
	 *          <tr>
	 *              <td>Stream&lt;T&gt; peek(Consumer&lt;T&gt; comparator)</td>
	 *              <td>스트림의 요소에 작업수행</td>
	 *          </tr>
	 *          <tr>
	 *              <td>
	 *                  Stream&lt;T&gt; sorted()<br>
	 *                  Stream&lt;T&gt; sorted(Comparator&lt;T&gt; comparator)
	 *              </td>
	 *              <td>
	 *                  스트림의 요소를 정렬한다.
	 *              </td>
	 *          </tr>
	 *          <tr>
	 *              <td>
	 *                  Stream&lt;R&gt; map(Function&lt;T, R&gt; mapper)<br>
	 *                  DoubleStream mapToDouble(ToDoubleFunction&lt;T&gt; mapper)<br>
	 *                  IntStream mapToInt(ToIntFunction&lt;T&gt; mapper)<br>
	 *                  LongStream mapToLong(ToLongFunction&lt;T&gt; mapper)<br>
	 *                  Stream&lt;R&gt; flatMap(Function&lt;T, Stream&lt;R&gt;&gt; mapper)<br>
	 *                  DoubleStream flatMapToDouble(Function&lt;T, DoubleStream&gt; m)<br>
	 *                  IntStream flatMapToInt(Function&lt;T, IntStream&gt; m)<br>
	 *                  LongStream flatMapToLong(Function&lt;T, LongStream&gt; m)
	 *              </td>
	 *              <td>
	 *                  스트림의 요소를 변환한다.
	 *              </td>
	 *          </tr>
	 *      </tbody>
	 *  </table>
	 *  <br>
	 *  스트림의 최종 연산 목록<br>
	 *  <table border="1">
	 *      <thead>
	 *          <th>최종 연산</th>
	 *          <th>설명</th>
	 *      </thead>
	 *      <tbody>
	 *          <tr>
	 *              <td>
	 *                  void foreach(consumer&lt;? super T&gt; action)<br>
	 *                  void forEachOrdered(Consumer&lt;? super T&gt; action)
	 *              </td>
	 *              <td>각 요소에 지정된 작업 수행</td>
	 *          </tr>
	 *          <tr>
	 *              <td>long count()</td>
	 *              <td>스트림의 요소의 개수 반환</td>
	 *          </tr>
	 *          <tr>
	 *              <td>
	 *                  Optional&lt;T&gt; max(Comparator&lt;? super T&gt; comparator)<br>
	 *                  Optional&lt;T&gt; min(Comparator&lt;? super T&gt; comparator)
	 *              </td>
	 *              <td>스트림의 최대값/최소값을 반환</td>
	 *          </tr>
	 *          <tr>
	 *              <td>
	 *                  Optional&lt;T&gt; findAny() // 아무거나 하나<br>
	 *                  Optional&lt;T&gt; findFirst() // 첫 번째 요소
	 *              </td>
	 *              <td>스트림의 요소 하나를 반환</td>
	 *          </tr>
	 *          <tr>
	 *              <td>
	 *                  boolean allMatch(Predicate&lt;&gt; p) // 모두 만족하는지<br>
	 *                  boolean anyMatch(Predicate&lt;&gt; p) // 하나라도 만족하는지<br>
	 *                  boolean noneMatch(Predicate&lt;&gt; p) // 모두 만족하지 않는지
	 *              </td>
	 *              <td>주어진 조건을 모든 요소가 만족시키는지, 만족시키지 않는지 확인</td>
	 *          </tr>
	 *          <tr>
	 *              <td>
	 *                  Object[] toArray()<br>
	 *                  A[] toArray(IntFunction&lt;A[]&gt; generator)
	 *              </td>
	 *              <td>스트림의 모든 요소를 배열로 반환</td>
	 *          </tr>
	 *          <tr>
	 *              <td>
	 *                  Optional&lt;T&gt; reduce(BinaryOperator&lt;T&gt; accumulator)<br>
	 *                  T reduce(T identity, BinaryOperator&lt;T&gt; accumulator)<br>
	 *                  U reduce(U identity, BiFunction&lt;U, T, U&gt; accumulator, BinaryOperator&lt;U&gt; combiner)
	 *              </td>
	 *              <td>스트림의 요소를 하나씩 줄여가면서(리듀싱) 계산한다.</td>
	 *          </tr>
	 *          <tr>
	 *              <td>
	 *                  R collect(Collector&lt;T, A, R&gt; collector)<br>
	 *                  R collect(Supplier&lt;R&gt; supplier, BiConsumer&lt;R, T&gt; accumulator, BiConsumer&lt;R, R&gt; combiner)
	 *              </td>
	 *              <td>스트림의 요소를 수집한다. 주로 요소를 그룹화하거나 분할한 결과를 컬렉션에 담아 반환하는데 사용된다.</td>
	 *          </tr>
	 *      </tbody>
	 *  </table>
	 *  <br>중간 연산은 map()과 flatMap(), 최종 연산은 reduce()와 collect()가 핵심이다.
	 */
	class Memo5 {
	}

	/**
	 * <h5>지연된 연산</h5><br>
	 * 스트림 연산에서 한 가지 중요한 점은 최종 연산이 수행되기 전까지는 중간 연산이 수행되지 않는다는 것이다.<br>
	 * 중간 연산을 호출하는 것은 단지 어떤 작업이 수행되어야하는지를 지정해주는 것일 뿐이다.<br>
	 * 최종 연산이 수행되어야 비로소 스트림의 요소들이 중간 연산을 거쳐 최종 연산에서 소모된다.
	 */
	class Memo6 {

	}

	/**
	 * <h5>Stream&lt;Integer&gt;와 IntStream</h5><br>
	 * 요소의 타입이 T인 스트림은 기본적으로 Stream&lt;T&gt;이지만, 오토박싱&언박싱으로 인한 비효율을 줄이기 위해 데이터 소스의 요소를 기본형으로 다루는 스트림, IntStream, LongStream, DoubleStream이 제공된다.<br>
	 * 일반적으로 Stream&lt;Integer&gt; 대신 IntStream을 사용하는 것이 더 효율적이고, IntStream에는 int타입의 값으로 작업하는데 유용한 메서드들이 포함되어 있다.
	 */
	class Memo7 {

	}

	/**
	 * <h5>병렬 스트림</h5><br>
	 * 스트림으로 데이터를 다룰 때의 장점 중 하나가 바로 병렬 처리가 쉽다는 것이다.<br>
	 * fork&join프레임웍으로 작업을 병렬처리하는 것을 배웠는데, 병렬 스트림은 내부적으로 이 프레임웍을 이용해서 자동적으로 연산을 병렬로 수행한다.<br>
	 * 우리가 할일이라고는 그저 스트림에 parallel()이라는 메서드를 호출해서 병렬로 연산을 수행하도록 지사하면 될 뿐이다.<br>
	 * 반대로 병렬로 처리되지 않게 하려면 sequential()을 호출하면 된다.<br>
	 * 모든 스트림은 기본적으로 병렬 스트림이 아니므로 sequential()을 호출한 필요가 없다. 이 메서드는 parallel()을 호출한 것을 취소할 때만 사용한다.<br>
	 * <small>※ parallel()과 sequential()은 새로운 스트림을 생성하는 것이 아니라, 그저 스트림의 속성을 변경할 뿐이다.</small>
	 */
	class Memo8 {

	}

}
