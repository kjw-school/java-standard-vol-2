package chapter14;

/**
 * <h1>1.6 메서드 참조</h1><br>
 * 람다식이 하나의 메서드만 호출하는 경우에는 '메서드 참조(method reference)'라는 방법으로 람다식을 간략히 할 수 있다.<br>
 * <pre><code>
 *     Function<String, Integer> f = (String s) -> Integer.parseInt(s);
 * </code></pre>
 * <br>
 * <pre><code>
 *     Function<String, Integer> f = (String s) -> Integer.parseInt(s);
 *     Function<String, Integer> f = Integer::parseInt; // 메서드 참조
 * </code></pre>
 * <br>
 * 위 메서드 참조에서 람다식의 일부가 생략되었지만, 컴파일러는 생략된 부분을 우변의 parseInt()메서드의 선어부로부터, 또는 좌변의 Function인터페이스에 지정된 지네릭 타입으로부터 쉽게 알아낼 수 있다.<br>
 * <pre><code>
 *     BiFunction<String, String, Boolean> f = (s1, s2) -> s1.equals(s2);
 *     BiFunction<String, String, Boolean> f = String::equals; // 메서드 참조
 * </code></pre>
 * <br>
 * 매개변수 s1과 s2를 생략해버리고 나면 equals만 남는데, 두 개의 String을 받아서 Boolean을 반환하는 equals라는 이름의 메서드는 다른 클래스에도 존재할 수 있기 때문에 equals앞에 클래스 이름은 반드시 필요하다.<br>
 * 메서드 참조를 사용할 수 있는 경우가 한 가지 더 있는데, 이미 생성된 객체의 메서드를 람다식에서 사용한 경우에는 클래스 이름 대신 그 객체의 참조변수를 적어줘야 한다.
 * <pre><code>
 *     MyClass obj = new MyClass();
 *     Function<String, Boolean> f = (x) -> obj.equals(x); // 람다식
 *     Function<String, Boolean> f2 = obj::equals; // 메서드 참조
 * </code></pre>
 * <br>
 * <table border="1">
 *     <thead>
 *         <th>종류</th>
 *         <th>람다</th>
 *         <th>메서드 참조</th>
 *     </thead>
 *     <tbody>
 *         <tr>
 *             <td>static메서드 참조</td>
 *             <td>(x) -> ClassName.method(x)</td>
 *             <td>ClassName::method</td>
 *         </tr>
 *         <tr>
 *             <td>인스턴스메서드 참조</td>
 *             <td>(obj, x) -> obj.method(x)</td>
 *             <td>ClassName::method</td>
 *         </tr>
 *         <tr>
 *             <td>특정 객체 인스턴스메서드 참조</td>
 *             <td>(x) -> obj.method(x)</td>
 *             <td>obj::method</td>
 *         </tr>
 *     </tbody>
 * </table>
 * <br>
 * <pre><code>
 *     하나의 메서드만 호출하는 람다식은 '클래스이름::메서드이름' 또는 '참조변수::메서드이름'으로 바꿀 수 있다.
 * </code></pre>
 */
public class Chapter14_1_6 {

	/**
	 * <h5>생성자의 메서드 참조</h5><br>
	 * 생성자를 호출하는 람다식도 메서드 참조로 변환할 수 있다.<br>
	 * <pre><code>
	 *     Supplier<Myclass> s = () -> ney MyClass(); // 람다식
	 *     Supplier<MyClass> s = MyClass::new; // 메서드 참조
	 * </code></pre>
	 * <br>
	 * 매개변수가 있는 생성자라면, 매개변수의 개수에 따라 알맞은 함수형 인터페이스를 사용하면 된다.<br>
	 * 배열을 생성할 때는 아래와 같이 하면 된다.<br>
	 * <pre><code>
	 *     Function<Integer, int[]> f = x -> new int[x]; // 람다식
	 *     Function<Integer, int[]> f = int[]::new; // 메서드 참조
	 * </code></pre>
	 * <br>
	 * 메서드 참조는 람다식을 마치 static변수처럼 다룰 수 있게 해준다.
	 */
	class Memo1 {

	}
}
