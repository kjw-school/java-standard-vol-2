package chapter14;

/**
 * <h5>1.5 Function의 합성과 Predicate의 결합</h5><br>
 * java.util.function패키지의 함수형 인터페이스에는 추상메서드 외에도 디폴트 메서드와 static메서드가 정의되어 있다.<br>
 * <small>※ 원래 Function인터페이스는 반드시 두개의 타입을 지정해 줘야하기 때문에, 두 타입이 같아도 Function&lt;T&gt;라고 쓸 수 없다. Function&lt;T,T&gt;라고 써야 한다.</small><br>
 * <pre><code>
 *     Function
 *     default <V> Function<T, V> andThen(Function<? super R, ? extends V> after)
 *     default <V> Function<V, R> compost(Function<? super V, ? extends T> before)
 *     static <T> Function<T, T> identity()
 *
 *     Predicate
 *     default Predicate<T> and(Predicate<? super T> other)
 *     default Predicate<T> or(Predicate<? super T> other)
 *     default PRedicate<T> negate()
 *     static <T> Predicate<T> isEqual(Object targetRef)
 * </code></pre>
 */
public class Chapter14_1_5 {

	/**
	 * <h5>Function의 합성</h5><br>
	 * 수학에서 두 함수를 합성해서 하나의 새로운 함수를 만들어 낼 수 있는 것처럼, 두 람다식을 합성해서 새로운 람다식을 만들 수 있다.<br>
	 * 두 함수의 합성은 어느 함수를 먼저 적용하느냐에 따라 달라진다.
	 */
	class Memo1 {

	}

	/**
	 * <h5>Predicate의 결합</h5><br>
	 * 여러 조건식을 논리 연산자인 &&(and), ||(or), !(not)으로 연결해서 하나의 식을 구성할 수 있는 것처럼, 여러 Predicate를 and(), or(), negate()로 연결해서 하나의 새로운 Predicate로 결합할 수 있다.
	 */
	class Memo2 {

	}

}
