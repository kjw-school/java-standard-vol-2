package chapter14;

/**
 * <h1>2.7 Collector구현하기</h1><br>
 * 컬렉터를 작성한다는 것은 Collector인터페이스를 구현한다는 것을 의미한다.<br>
 * <pre><code>
 *     public interface Collector<T, A, R> {
 *         Supplier<A> supplier();
 *         BiConsumer<A, T> accumulator();
 *         BinaryOperator<A> combiner();
 *         Function<A, R> finisher();
 *
 *         Set<Characteristics> characteristics(); // 컬렉터의 특성이 담긴 Set을 반환
 *     }
 * </code></pre>
 * <br>
 * 직접 구현해야하는 것은 위의 5개의 메서드인데, characteristics()를 제외하면 모두 반환타입이 함수형 인터페이스이다. 즉, 4개의 람다식을 작성하면 되는 것이다.
 * <pre><code>
 *     supplier() 작업 결과를 저장할 공간을 제공
 *     accumulator() 스트림의 요소를 수집(collect)할 방법을 제공
 *     combiner() 두 저장공간을 병합할 방법을 제공(병렬 스트림)
 *     finisher() 결과를 최종적으로 변환할 방법을 제공
 * </code></pre>
 * <br>
 * supplier()는 수집 결과를 저장할 공간을 제공하기 위한 것이고, accumulator()는 스트림의 요소를 어떻게 supplier()가 제공한 공간에 누적할 것인지를 정의한다.<br>
 * combiner()는 병렬 스트림인 경우, 여러 쓰레드에 의해 처리된 결과를 어떻게 합칠 것인가를 정의한다. 그리고, finisher()는 작업결과를 변환하는 일을 하는데 변환이 필요없다면, 항등 함수인 Function.identity()를 반환하면 된다.<br>
 * <pre><code>
 *     public Function finisher() {
 *         return Function.identity(); // 항등 함수를 반환, return x -> x; 와 동일
 *     }
 * </code></pre>
 * characteristics()는 컬렉터가 수행하는 작업의 속성에 대한 정보를 제공하기 위한 것이다.<br>
 * <pre><code>
 *     Characteristics.CONCURRENT 병렬로 처리할 수 있는 작업
 *     Characteristics.UNORDERED 스트림의 요소의 순서가 유지될 필요가 없는 작업
 *     Characteristics.IDENTITY_FINISH finisher()가 항등 함수인 작업
 * </code></pre>
 * <br>
 * finisher()를 제외하고 supplier(), accumulator(), combiner()는 모두 앞서 리듀싱에 대해서 배울 때 등장했던 개념들이다. 결국 Collector도 내부적으로 처리하는 과정이 리듀싱과 같다는 것을 의미한다.<br>
 * 이쯤에서 reduce()와 collect()의 차이에 대해서 궁금할 것인데, 이 둘은 근본적으로 하는 일이 같다. <br>
 * collect()는 앞서 살펴 본 것처럼, 그룹화와 분할, 집계 등에 유용하게 쓰이고, 병렬화에 있어서 reduce()보다 collect()가 더 유리하다.
 */
public class Chapter14_2_7 {

}
