package chapter14;

/**
 * <h1>2.4 Optional&lt;T&gt; 와 OptionalInt</h1><br>
 * Optional&lt;T&gt;은 지네릭 클래스로 'T타입의 객체'를 감싸는 래퍼 클래스이다. 그래서 Optional타입의 객체에는 모든 타입의 참조변수를 담을 수 있다.<br>
 * <small>※java.util.Optional은 JDK1.8부터 추가되었다.</small><br>
 * <pre><code>
 *     public final class Optioanl<T> {
 *         private final T value; // T타입의 참조변수
 *         ...
 *     }
 * </code></pre>
 * <br>
 * 최종 연산의 결과를 그냥 반환하는 게 아니라 Optional객체에 담아서 반환하는 것이다.
 */
public class Chapter14_2_4 {

	/**
	 * <h5>Optional객체 생성하기</h5><br>
	 * Optional객체를 생성할 때는 of() 또는 ofNullable()을 사용한다.<br>
	 * 만일 참조변수의 값이 null일 가능성이 있으면, of()대신 ofNullable()을 사용해야한다. of()는 매개변수의 값이 null이면 NullPointerException이 발생하기 때문이다.<br>
	 * Optional&lt;T&gt;타입의 참조변수를 기본값으로 초기화할 때는 empty()를 사용한다.
	 */
	class Memo1 {

	}

	/**
	 * <h5>Optional객체의 값 가져오기</h5><br>
	 * Optional객체에 저장된 값을 가져올 때는 get()을 사용한다. 값이 null일 때는 NoSuchElementException이 발생하며, 이를 대비해서 orElse()로 대체할 값을 지정할 수 있다.<br>
	 * orElse()의 변형으로는 null을 대체할 값을 변환하는 람다식을 지정할 수 있는 orElseGet()과 null일 때 지정된 예외를 발생시키는 orElseThrow()가 있다.<br>
	 * Stream처럼 Optional객체에도 filter(), map(), flatMap()을 사용할 수 있다.<br>
	 * 만일 Optional객체의 값이 null이면, 이 메서드들은 아무 일도 하지 않는다.<br>
	 * isPresent()는 Optional객체의 값이 null이면 false를, 아니면 true를 반환한다.
	 */
	class Memo2 {

	}

	/**
	 * <h5>OptionalInt, OptionalLong, OptionalDouble</h5><br>
	 * IntStream과 같은 기본형 스트림에는 Optional도 기본형을 값으로 하는 OptionalInt, OptionalLong, OptionalDouble을 반환한다.
	 */
	class Memo3 {

	}

}
