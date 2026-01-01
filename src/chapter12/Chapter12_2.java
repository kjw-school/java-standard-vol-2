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
	 * <h5>모든 열거형의 조상 - java.lang.Enum</h5><br>
	 * values()는 열거형의 모든 상수를 배열에 담아 반환한다. 이 메서드는 모든 열거형이 가지고 잇는 것으로 컴파일러가 자동으로 추가해 준다.<br>
	 * ordinal()은 모든 열거형의 조상인 java.lang.Enum클래스에 정의된 것으로, 열거형 상수가 정의된 순서(0부터 시작)를 정수로 반환한다.<br>
	 * <table border="1">
	 *     <thead>
	 *         <td>메서드</td>
	 *         <td>설명</td>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>Class&lt;E&gt; getDeclaringClass()</td>
	 *             <td>열거형의 Class객체를 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String name()</td>
	 *             <td>열거형 상수의 이름을 문자열로 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int ordinal()</td>
	 *             <td>열거형 상수가 정의된 순서를 반환한다.(0부터 시작)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>T valueOf(Class&lt;T&gt; enumType, String name)</td>
	 *             <td>지정된 열거형에서 name과 일치하는 열거형 상수를 반환한다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * 이외에도 values()처럼 컴파일러가 자동적으로 추가해주는 메서드가 하나 더 있다.
	 * <pre><code>
	 *     static E values()
	 *     static E valueOf(String name)
	 * </code></pre>
	 * <br>
	 * 이 메서드는 열거형 상수의 이름으로 문자열 상수에 대한 참조를 얻을 수 있게 해준다.
	 * <pre><code>
	 *     Direction d = Direction.valueOf("WEST)";
	 *
	 *     System.out.println(d); // WEST
	 *     System.out.println(Direction.WEST == Direction.valueOf("WEST")); // true
	 * </code></pre>
	 */
	class Memo03 {

	}

	/**
	 * <h5>2.3 열거형에 멤버 추가하기</h5><br>
	 * Enum클래스에 정의된 ordinal()이 열거형 상수가 정의된 순서를 반환하지만, 이 값을 열거형 상수의 값으로 사용하지 않는 것이 좋다.<br>
	 * 이 값은 내부적인 용도로만 사용되기 위한 것이기 때문이다.<br>
	 * 열거형 상수의 값이 불연속적인 경우에는 이때는 다음과 같이 열거형 상수의 이름 옆에 원하는 값을 괄호()와 함께 적어주면 된다.<br>
	 * <code>
	 *     enum Direction { EAST(1), SOUTH(5), WEST(-1), NORTH(10) }
	 * </code>
	 * <br>
	 * 그리고 지정된 값을 저장할 수 있는 인스턴스 변수와 생성자를 새로 추가해 주어야 한다.<br>
	 * 이 때 주의할 점은, 먼저 열거형 상수를 모두 정의한 다음에 다른 멤버들을 추가해야한다는 것이다.<br>
	 * 열거형 상수의 마지막에 ';'도 잊지 말아야 한다.<br>
	 * <pre><code>
	 *
	 *     enum Direction {
	 *         EAST(1), SOUTh(5), WESt(-1), NORTH(10); // 끝에 ';'를 추가해야 한다.
	 *
	 *         private final int value; //; 정수를 저장할 필드(인스턴스 변수)를 추가
	 *         Direction(int value) { this.value = value; } // 생성자 추가
	 *         public int getValue() { return value; }
	 *     }
	 *
	 * </code></pre>
	 * <br>
	 * <code>
	 *     Direction d = new Direction(1); // 에러, 열거형의 생성자는 외부에서 호출불가
	 * </code>
	 * <br>
	 * 열거형 Direction에 새로운 생성자가 추가되었지만, 위와 같이 열거형의 객체를 생성할 수 없다. 열거형의 생성자는 제어자가 묵시적으로 private이기 때문이다.<br>
	 * 하나의 열거형 상수에 여러 값을 지정할 수도 있다. 다만 그에 맞게 인스턴스 변수와 생성자 등을 새로 추가해주어야 한다.<br>
	 * <pre><code>
	 *
	 *     enum Direction {
	 *
	 *         EAST(1, ">"), SOUTH(2, "V"), WEST(3, "<"), NORTH(4, "^");
	 *
	 *         private final int value;
	 *         private final String symbol;
	 *
	 *         Direction(int value, String symbol) { // 접근 제어자 private이 생략됨
	 *             this.value = value;
	 *             this.symbol = symbol;
	 *         }
	 *
	 *         public int getValue() { return value; }
	 *         public String getSymbol() { return symbol;}
	 *
	 *     }
	 *
	 * </code></pre>
	 *
	 */
	class Memo4 {

	}

	/**
	 * <h5>얼겨헝에 추상 메서드 추가하기</h5>
	 */
	class Memo5 {

	}

}
