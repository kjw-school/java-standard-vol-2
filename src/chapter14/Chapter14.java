package chapter14;

/**
 * <h1>1. 람다식(Lambda expression)</h1>
 */
public class Chapter14 {

	/**
	 * <p>
	 *     자바가 1996년에 처음 등장한 이후로 두 번의 큰 변화가 있었는데, 한번은 JDK1.5부터 추가된 지네릭스(generics)의 등장이고,<br>
	 *     또 한 번은 JDK1.8부터 추가된 람다식(lambda expression)의 등장이다.<br>
	 *     특히 람다식의 도입으로 인해, 이제 자바는 객체지향언어인 동시에 함수형 언어가 되었다.
	 * </p>
	 */
	class Memo01 {
	}

	/**
	 * <p>
	 *     <h5>1.1 람다식이란?</h5><br>
	 *     람다식(Lambda expression)은 간단히 말해서 메서드를 하나의 '식(expression)'으로 표현한 것이다.<br>
	 *     람다식은 함수를 간략하면서도 명확한 식으로 표현할 수 있게 해준다.<br>
	 *     메서드를 람다식으로 표현하면 메서드의 이름과 반환값이 없어지므로, 람다식을 '익명 함수(anonymous function)'이라고도 한다.<br>
	 *     <code>
	 *         int[] arr = new int[5];<br>
	 *         Arrays.setAll(arr, <b>(i) -> (int)(Math.random()*5)+1)</b>;
	 *     </code>
	 *     <br>
	 *     람다식은 메서드의 매개변수로 전달되어지는 것이 가능하고, 메서드의 결과로 반환될 수도 있다. 람다식으로 인해 메서드를 변수처럼 다루는 것이 가능해진 것이다.<br>
	 *     ※메서드와 함수의 차이<br>
	 *     - 객체지향개념에서는 함수(function)대신 객체의 행위나 동작을 의미하는 메서드(method)라는 용어를 사용한다.<br>
	 *       메서드는 함수와 같은 의미이지만, 특정 클래스에 반드시 속해야 한다는 제약이 있기 때문에 기존의 함수와 같은 의미의 다른 용어를 선택해서 사용한 것이다.<br>
	 *       그러나 이제 다시 람다식을 통해 메서드가 하나의 독립적인 기능을 하기 때문에 함수라는 용어를 사용하게 되었다.<br>
	 * </p>
	 */
	class Memo02 {
	}

	/**
	 * <p>
	 *     <h5>1.2 람다식 작성하기</h5><br>
	 *		람다식은 '익명 함수'답게 메서드에서 이름과 반환타입을 제거하고 매개변수 선언부와 몸통{} 사이에 '->'를 추가한다.<br>
	 *		반환타입 메서드이름(매개변수 선언) {<br>
     *  			&nbsp;문장들<br>
	 *		}<br>
	 *		↓<br>
	 *		(매개변수 선언) -> {<br>
	 *  		&nbsp;문장들<br>
	 *		}
	 *		<br>
	 *		반환값이 있는 메서드의 경우, return문 대신 '식(expression)'으로 대신할 수 있다.<br>
	 *		식의 연산결과가 자동적으로 반환값이 된다. 이때는 '문장(statement)'이 아닌 '식'이므로 끝에 ';'을 붙이지 않는다.<br>
	 *		(int a, int b) -> {return <b>a > b ? a : b;</b>} -> (int a, int b) -> <b>a > b ? a : b</b>
	 *		<br>
	 *		람다식에 선언된 매개변수의 타입은 추론이 가능한 경우는 생략할 수 있는데, 대부분의 경우에 생략가능하다.<br>
	 *		람다식에 반환타입이 없는 이유도 항상 추론이 가능하기 때문이다.
	 *		(<b>int</b> a, <b>int</b> b) -> a > b ? a: b -> (a, b) -> a > b ? a : b
	 *		<br>
	 *		<small>※'(int a,b) -> a > b ? a : b' 와 같이 두 매개변수 중 어느 하나의 타입만 생략하는 것은 허용되지 않는다.</small>
	 *		<br>
	 *		선언된 매개변수가 하나뿐인 경우에는 괄호()를 생략할 수 있다. 단, 매개변수의 타입이 있으면 괄호()를 생략할 수 없다.<br>
	 *		마찬가지로 괄호{} 안의 문장이 하나일 때는 괄호{}를 생략할 수 있다. 이 때 문장의 끝에 ';'을 붙이지 않아야 한다는 것에 주의하자.
	 *		<br>
	 *		그러나 괄호{}안의 문장이 return문일 경우 괄호{}를 생략할 수 없다.
	 * </p>
	 */
	class Memo03 {
	}

	/**
	 * <h5>1.3 함수형 인터페이스(Functional Interface)</h5><br>
	 * 사실 람다식은 익명 클래스의 객체와 동등하다.<br>
	 * <code>타입 f = (int a, int b) -> a > b ? a: b; // 참조변수의 타입을 뭘로 해야 할까?</code>
	 * <br>
	 * 참조변수 f의 타입은 어떤 것이어야 할까? 참조형이니까 클래스 또는 인터페이스가 가능하다. 그리고 람다식과 동등한 메서드가 정의되어 있는 것이어야 한다.<br>
	 * 그래야만 참조변수로 익명 객체(람다식)의 메서드를 호출할 수 있기 때문이다.
	 * <br>
	 * 익명 객체를 람다식으로 대체가 가능한 이유는, 람다식도 실제로 익명 객체이고, 해당 인터페이스를 구현한 익명 객체의 메서드와 람다식의 매개변수의 타입과 개수 그리고 반환값이 일치하기 떄문이다.
	 * <br>
	 * 하나의 메서드가 선언된 인터페이스를 정의해서 람다식을 다루는 것은 기존의 자바의 규칙들을 어기지 않으면서도 자연스럽다.
	 * <br>
	 * 그래서 인터페이스를 통해 람다식을 다루기로 결정되었으며, 람다식을 다루기 위한 인터페이스를 '함수형 인터페이스(functional interface)'라고 부르기로 했다.
	 * <br>
	 * 함수형 인터페이스에는 오직 하나의 추상 메서드만 정의되어 있어야 한다는 제약이 있다. 그래야 람다식돠 인터페이스의 메서드가 1:1로 연결될 수 있기 때문이다.<br>
	 * 반면에 static메서드와 default메서드의 개수에는 제약이 없다.<br>
	 * <small>※'@FunctionalInterface'를 붙이면, 컴파일러가 함수형 인터페이스를 올바르게 정의하였는지 확인해주므로, 꼭 붙이도록 하자.</small>
	 * <br>
	 * <b>함수형 인터페이스 타입의 매개변수와 반환타입</b>
	 * <br>
	 * 메서드의 반환타입이 함수형 인터페이스타입이라면, 이 함수형 인터페이스의 추상메서드와 동등한 람다식을 가리키는 참조변수를 반환하거나 람다식을 직접 반환할 수 있다.<br>
	 * 람다식을 참조변수로 다룰 수 있다는 것은 메서드를 통해 람다식을 주고받을 수 있다는 것을 의미한다. 즉, 변수처럼 메서드를 주고받는 것이 가능해진 것이다.<br>
	 * 사실상 메서드가 아니라 객체를 주고받는 것이라 근본적으로 달라진 것은 아무것도 없다.
	 */
	class Memo04 {
	}

	@FunctionalInterface
	interface MyFunction {
		void run(); // public abstract void run();
	}

	static class LambdaEx1 {

		static void execute(MyFunction f) { // 매개변수의 타입이 MyFunction인 메서드
			f.run();
		}

		static MyFunction getMyFunction() {  // 반환타입이 MyFunction인 메서드
			MyFunction f = () -> System.out.println("f3.run()");
			return f;
		}

		public static void main(String[] args) {
			// 람다식으로 MyFunction의 run()을 구현
			MyFunction f1 = () -> System.out.println("f1.run()");

			MyFunction f2 = new MyFunction() { // 익명클래스로 run()을 구현
				public void run() {
					System.out.println("f2.run()");
				}
			};

			MyFunction f3 = getMyFunction();

			f1.run();
			f2.run();
			f3.run();

			execute(f1);
			execute(() -> System.out.println("run()"));

		}

	}

	/**
	 * <h5>람다식의 타입과 형변환</h5><br>
	 * 함수형 인터페이스로 람다식을 참조할 수 있는 것일 뿐, 람다식의 타입이 함수형 인터페이스의 타입과 일치하는 것은 아니다.<br>
	 * 람다식은 익명 객체이고 익명 객체는 타입이 없다. 정확히는 타입은 있지만 컴파일러가 임의로 이름을 정하기 때문에 알 수 없는 것이다.<br>
	 * 그래서 대입 연산자의 양변의 타입을 일치시키기 위해 아래와 같이 형변환이 필요하다.<br>
	 * MyFunction f = (MyFunction)( () -> {} ); // 양변의 타입이 다르므로 형변환이 필요<br>
	 * 람다식은 MyFunction인터페이스를 직접 구현하지 않았지만, 이 인터페이스를 구현한 클래스의 객체와 완전히 동일하기 때문에 위와 같은 형변환을 허용한다.<br>
	 * 그리고 이 형변환은 생략가능하다.<br>
	 * 람다식은 이름이 없을 뿐 분명히 객체인데도, 아래와 같이 Object타입으로 형변환 할 수 없다. 람다식은 오직 함수형 인터페이스로만 형변환이 가능하다.<br>
	 * Object obj = (Object)( () -> {} ) // 에러. 함수형 인터페이스로만 형변환 가능
	 */
	class Memo05 {
	}

	/**
	 * 실행 결과를 보면, 컴파일러가 람다식의 타입을 어떤 형식으로 만들어내는지 알 수 있다.<br>
	 * 일반적인 익명 객체라면, 객체의 타입이 '외부클래스이름$번호'와 같은 형식으로 타입이 결정되었을 텐데, 람다식의 타입은 '외부클래스이름$$Lambda$번호'와 같은 형식으로 되어 있는 것을 확인할 수 있다.
	 */
	static class LambdaEx02 {

		@FunctionalInterface
		interface MyFunction {
			void myMethod(); // public abstract void myMethod();
		}

		public static void main(String[] args) {
			MyFunction f = () -> { // MyFunction f = (MyFunction)( () -> {} );
			};
			Object obj = (MyFunction)(() -> { // Object타입으로 형변환이 생략됨
			});
			String str = ((Object)(MyFunction)(() -> {
			})).toString();

			System.out.println(f);
			System.out.println(obj);
			System.out.println(str);

			// System.out.println(() -> ()); 에러. 람다식은 Object타입으로 형변환이 안됨
			System.out.println((MyFunction)(() -> {
			}));

			// System.out.println(() -> {}).toString(); 에러
			System.out.println(((Object)(MyFunction)(() -> {
			})).toString());

		}

	}

	/**
	 * <h5>외부 변수를 참조하는 람다식</h5><br>
	 * 람다식도 익명 객체, 즉 익명 클래스의 인스턴스이므로 람다식에서 외부에 선언된 변수에 접근하는 규칙은 앞서 익명 클래스에서 배운 것과 동일하다.<br>
	 * 람다식 내에서 참조하는 지역변수는 final이 붙지 않았어도 상수로 간주된다.<br>
	 * 외부 지역변수와 같은 이름의 람다식 매개변수는 허용되지 않는다.
	 */
	static class LambdaEx03 {

		@FunctionalInterface
		interface MyFunction {
			void myMethod();
		}

		static class Outer {
			int val = 10; // Outer.this.val

			class Inner {
				int val = 20; // this.val

				void method(int i) {// void method(final int i) {
					int val = 30; // final int val = 30;
					// i = 10; 에러. 상수의 값을 변경할 수 없음.

					MyFunction f = () -> {
						System.out.println(" i :" + i);
						System.out.println(" val :" + val);
						System.out.println(" this.val :" + ++this.val);
						System.out.println("Outer.this.val :" + ++Outer.this.val);
					};

					f.myMethod();

				} // Innter클래스의 끝

			} // Outer 클래스의 끝
		}

		public static void main(String[] args) {
			Outer outer = new Outer();
			Outer.Inner inner = outer.new Inner();
			inner.method(100);
		}

	}

	/**
	 * <h5>1.4 java.util.function패키지</h5><br>
	 * java.util.function패키지에 일반적으로 자주 쓰이는 형식의 메서드를 함수형 인터페이스로 미리 정의해 놓았다.<br>
	 * <table border="1">
	 *     <tr>
	 *         <th>함수형 인터페이스</th>
	 *         <th>메서드</th>
	 *         <th>설명</th>
	 *     </tr>
	 *     <tr>
	 *         <td>java.lang.Runnable</td>
	 *         <td>void run()</td>
	 *         <td>매개변수도 없고, 반환값도 없음.</td>
	 *     </tr>
	 *     <tr>
	 *         <td>Supplier&lt;T&gt;</td>
	 *         <td>T get()</td>
	 *         <td>매개변수는 없고, 반환값만 있음</td>
	 *     </tr>
	 *     <tr>
	 *         <td>Consumer&lt;T&gt;</td>
	 *         <td>void accept(T t)</td>
	 *         <td>Supplier와 반대로 매개변수만 있고, 반환값이 없음</td>
	 *     </tr>
	 *     <tr>
	 *         <td>Function&lt;T,R&gt;</td>
	 *         <td>R apply(T t)</td>
	 *         <td>일반적인 함수, 하나의 매개변수를 받아서 결과를 반환</td>
	 *     </tr>
	 *     <tr>
	 *         <td>Predicate&lt;T&gt;</td>
	 *         <td>boolean test(T t)</td>
	 *         <td>조건식을 표현하는데 사용됨. 매개변수는 하나, 반환 타입은 boolean</td>
	 *     </tr>
	 * </table><br>
	 * Predicate는 조건식을 함수로 표현하는데 사용된다.
	 */
	class Memo06 {

	}

	/**
	 * <h5>조건식에 표현에 사용되는 Predicate</h5><br>
	 * Predicate는 Function의 변형으로, 반환타입이 boolean이라는 것만 다르다, Predicate는 조건식을 람다식으로 표현하는데 사용된다.<br>
	 * <small>수학에서 결과로 true 또는 false를 반환하는 함수를 '프레디케이트(predicate)'라고 한다.</small><br>
	 * <code>
	 *     Predicate&lt;String&gt; isEmptyStr = s -> s.length() == 0;<br>
	 *     String s = "";<br>
	 *
	 *     if(isEmptyStr.test(s)) // if(s.length == 0)<br>
	 *     System.,out.println("This is an empty String.");
	 * </code>
	 */
	class Memo07 {

	}

	/**
	 * <h5>매개변수가 두 개인 함수형 인터페이스</h5><br>
	 * 매개변수의 개수가 2개인 함수형 인터페이스는 이름 앞에 접두사 'Bi'가 붙는다.
	 * <table border="1">
	 *     <thead>
	 *         <td>함수형 인터페이스</td>
	 *         <td>메서드</td>
	 *         <td>설명</td>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>BiConsumer&lt;T,U&gt;</td>
	 *             <td>T, U -> void accept(T t, U u)</td>
	 *             <td>두 개의 매개변수만 있고 반환값이 없음</td>
	 *         </tr>
	 *         <tr>
	 *             <td>BiPredicate&lt;T,U&gt;</td>
	 *             <td>T, U -> boolean test(T t, U u) -> boolean</td>
	 *             <td>조건식을 표현하는데 사용됨, 매개변수는 둘, 반환값은 boolean</td>
	 *         </tr>
	 *         <tr>
	 *             <td>BiFunction&lt;T,U,R&gt;</td>
	 *             <td>T,U -> R apply(T t, U u) -> R</td>
	 *             <td>두 개의 매개변수를 받아서 하나의 결과를 반환</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 */
	class Memo08 {

	}

	/**
	 * <h5>UnaryOperator와 BinaryOperator</h5><br>
	 * Function의 또 다른 변형으로 UnaryOperator와 BinaryOperator가 있는데, 매개변수의 타입과 반환타입의 타입이 모두 일치한다는 점만 제외하고는 Function과 같다.<br>
	 * <table border="1">
	 *     <thead>
	 *         <td>함수형 인터페이스</td>
	 *         <td>메서드</td>
	 *         <td>설명</td>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>UnaryOperator&lt;T&gt;</td>
	 *             <td>T -> T apply(T t)</td>
	 *             <td>Function의 자손, Function과 달리 매개변수와 결과의 타입이 같다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>BinaryOpoerator&lt;T&gt;</td>
	 *             <td>T, T -> T apply(T t, T t)</td>
	 *             <td>BiFunction의 자손, BiFunction과 달리 매개변수와 결과의 타입이 같다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 */
	class Memo09 {

	}

	/**
	 * <h5>컬렉션 프레임웍과 함수형 인터페이스</h5><br>
	 */
	class Memo10 {
	}

}
