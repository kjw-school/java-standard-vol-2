package chapter12;

/**
 * <h1>2. 열거형(enums)</h1>
 */
public class Chapter12_2 {

	/**
	 * <p>
	 * 		<h5>2.1 열거형 이란?</h5><br>
	 * 		열거형은 서로 관련된 상수를 편리하게 선언하기 위한 것으로 여러 상수를 정의할 때 사용하면 유용하다.<br>
	 * 		원래 자바는 C언어와 달리 열거형이라는 것이 존재하지 않았으나 JDK1.5부터 새로 추가되었다.<br>
	 * 		자바의 열거형은 C언어의 열거형보다 더 향상된 것으로 열거형이 갖는 값뿐만 아니라 타입도 관리하기 때문에 보다 논리적인 오류를 줄일 수 있다.<br>
	 * 		<code>
	 * 		 	class Card {<br>
	 * 		 	    static final int CLOVER = 0;<br>
	 * 		 	    static final int HEART = 1;<br>
	 * 		 	    static final int DIAMOND = 2;<br>
	 * 		 	    static final int SPADE = 3;<br>
	 * 		 	    <br>
	 * 		 	    static final int TWO = 0;<br>
	 * 		 	    static final int THREE = 0;<br>
	 * 		 	    static final int FOUR = 0;<br>
	 * 		 	    <br>
	 * 		 	    final int kind;<br>
	 * 		 	    final int num;<br>
	 * 		 	    <br>
	 *            }<br>
	 * 		↓<br>
	 *			class Card {<br
	 *				enum Kind { CLOVER, HEART, DIAMOND, SPADE; } // 열거형 Kind를 정의<br>
	 *				enum num { TWO, THREE, FOUR; } // 열거형 num를 정의<br>
	 *				final Kind kind;<br>
	 *				final Num num;<br>
	 *            }<br>
	 * 		</code><br>
	 * 		기존의 많은 언어들, 예를 들어 C언어에서는 타입이 달라도 값이 같으면 조건식결과가 참(true)이였으나, 자바의 열거형은<br>
	 * 		'타입에 안전한 열거형(typesafe enum)'이라서 실제 값이 같아도 타입이 다르면 컴파일 에러가 발생한다.<br>
	 * 		이처럼 값뿐만 아니라 타입까지 체크하기 때문에 타입에 안전하다고 하는 것이다.<br>
	 * 		<code>
	 * 			if(Card.CLOVER == Card.TWO) // true지만 false이어야 의미상 맞음.<br>
	 * 			if(Card.Kind.CLOVER == Card.Value.TWO) // 컴파일 에러. 값은 같지만 타입이 다름.
	 * 		</code>
	 * 		<br>
	 * 		그리고 더 중요한 것은 상수의 값이 바뀌면, 해당 상수를 참조하는 모든 소스를 다시 컴파일해야 한다는 것이다.<br>
	 * 		하지만 열거형 상수를 사용하면, 기존의 소스를 다시 컴파일하지 않아도 된다.
	 * </p>
	 */
	static class Memo01 {
	}

	/**
	 * <h5>2.2 열거형의 정의와 사용</h5><br>
	 * 열거형을 정의하는 방법은 간단하다. 다음과 같이 괄호 {}안에 상수의 이름을 나열하기만 하면 된다.<br>
	 * <code>
	 *     enum 열거형 이름 { 상수명1, 상수명2, ... }
	 * </code>
	 * <br>
	 * 이 열거형에 정의된 상수를 사용하는 방법은 '열거형이름.상수명'이다. 클래스의 static변수를 참조하는 것과 동일하다.<br>
	 * 열거형 상수간의 비교에는 '=='를 사용할 수 있다. equals()가 아닌 '=='로 비교가 가능하다는 것은 그만큼 빠른 성능을 제공한다는 얘기다.<br>
	 * 그러나 '&lt;', '&gt;'와 같은 비교연산자는 사용할 수 없고 compareTo()는 사용가능하다. 앞서 배운 것과 같이 compareTo()는 두 비교대상이 같으면 0, 왼쪽이 크면 양수, 오른쪽이 크면 음수를 반환한다.
	 * 주의할 점은 case문에 열거형의 이름은 적지 않고 상수의 이름만 적어야 한다는 제약이 있다.
	 */
	static class Memo02 {
	}

	/**
	 * <h5>모든 열거형의 조상 - java.lang.Enum</h5>
	 */
	class Memo03 {

	}

}
