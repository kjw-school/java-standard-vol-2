package chapter12;

import java.util.ArrayList;

/**
 * <h1>1. 지네릭스(Generics)</h1><br>
 * JDK1.5에서 처음 도욉된 지네릭스는 JDK1.8부터 도입된 람다식만큼 큰 변화였다.
 */
public class Chapter12 {

	/**
	 * <h5>1.1 지네릭스란?</h5><br>
	 * 지네릭스는 다양한 타입의 객체들을 다루는 메서드나 컬렉션 클래스에 컴파일 시의 타입체크(compile-time type check)를 해주는 기능이다.<br>
	 * 객체의 타입을 컴파일 시에 체크하기 때문에 객체의 타입 안정성을 높이고 형변환의 번거로움이 줄어든다.<br>
	 * 타입 안정성을 높인다는 것은 의도하지 않은 타입의 객체가 저장되는 것을 막고, 저장된 객체를 꺼내올 때 원래의 타입과 다른 타입으로 잘못<br>
	 * 형변환되어 발생할 수 있는 오류를 줄여준 다는 뜻이다.<br>
	 * 지네릭스의 장점<br>
	 * 1. 타입 안정성을 제공한다.<br>
	 * 2. 타입체크와 형변환을 생략할 수 있으므로 코드가 간결해 진다.<br>
	 */
	class Memo01{}

	/**
	 * <h5>1.2 지네릭 클래스의 선언</h5><br>
	 * 지네릭 타입은 클래스와 메서드에 선언할 수 있다.
	 * class Box {
	 *     Object item;
	 *
	 *     void setItem(Object item) { this.item = item; }
	 *     Object getItem() { return item; }
	 * }
	 * 이 클래스를 지네릭 클래스로 변경하면 다음과 같이 클래스 옆에 '<T>'를 붙이면 된다. 그리고 'Object'를 모두 'T'로 바꾼다.
	 * class Box<T> {
	 *     T item;
	 *     void setItem(T item) { this.item = item; }
	 *     T getItem() { return item; }
	 * }
	 * Box<T>에서 T를 '타입 변수(type variable)'라고 하며, 'Type'의 첫 글자에서 따온 것이다. 타입 변수는 T가 아닌 다른 것을 사용해도
	 * 된다. ArrayList<E>의 경우, 타입 변수 E는 'Element(요소)'의 첫글자를 따서 사용했다. 타입 변수가 여러 개인 경우에는 Map<K,V>와
	 * 같이 콤마','를 구분자로 나열하면 된다. K는 Key(키)를 의미하고, V는 Value(값)을 의미한다. 무조건 'T'를 사용하기보다 가능하면, 이처럼
	 * 상황에 맞게 의미있는 문자를 선택해서 사용하는 것이 좋다. 이들은 기호만 다를 뿐 '임이의 참조형 타입'을 의미한다는 것은 모두 같다.
	 * 기존에는 다양한 종류의 타입을 다루는 메서드의 매개변수나 리턴타입으로 Object타입의 참조변수를 많이 사용했고, 그로 인해 형변환이 불가피
	 * 했지만, 이젠 Object타입 대신 원하는 타입을 지정하기만 하면 되는 것이다.
	 * 이제 지네릭 클래스가 된 Box클래스의 객체를 생성할 때는 다음과 같이 참조변수와 생성자에 타입 T대신에 사용될 실제 타입을 지정해주어야
	 * 한다.
	 * Box<String> b = new Box<String>(); // 타입 T 대신, 실제 타입을 지정
	 * b.setItem(new Object()); // 에러, String 이외의 타입을 지정불가
	 * b.setItem("ABC"); // OK, String타입이므로 가능
	 * String item = b.getItem(); // 형변환이 필요없음
	 * 위의 코드에서 타입 T대신에 String타입을 지정해줬으므로, 지네릭 클래스 Box<T>는 다음과 같이 정의된 것과 같다.
	 * class Box { // 지네릭 타입을 String으로 지정
	 *  String item;
	 *  void setItem(String item) { this.item = item; }
	 *  String getItem() { return item; }
	 * }
	 */
	class Memo02{}

	/**
	 * <h5>지네릭스의 용어</h5><br>
	 * class Box<T> {}, Box: 원시타입, Box<T>: 지네릭 클래스<br>
	 * Box<T>, 지네릭 클래스, 'T의 Box' 또는 'T Box'라고 읽는다.<br>
	 * T, 타입 변수 또는 타입 매개변수.(T는 타입문자)<br>
	 * Box, 원시 타입(raw type)<br>
	 * 타입 문자 T는 지네릭 클래스 Box<T>의 타입 변수 또는 타입 매개변수라고 하는데, 메서드의 매개변수와 유사한 면이 있기 때문이다.<br>
	 * 그래서 아래와 같이 타입 매개변수에 타입을 지정하는 것을 '지네릭 타입 호출'이라고 하고, 지정된 타입'String'을 '매개변수화된 타입(parameterized type)'이라고 한다.<br>
	 * Box<String> b = new Box<String>();, String: 대입된 타입(매개변수화된 타입, parameterized type), Box<String>: 지네릭 타입호출
	 */
	class Memo03{}

	/**
	 * <h5>지네릭스의 제한</h5><br>
	 * 지네릭 클래스 Box의 객체를 생성할 때, 객체별로 다른 타입을 지정하는 것은 적절하다. 지네릭스는 이처럼 인스턴스별로 다르게 동작하도록하려고 만든 기능이니까.<br>
	 * 그러나 모든 객체에 대해 동일하게 동작해야하는 static멤버에 타입 변수 T를 사용할 수 없다. T는 인스턴스변수로 간주되기 때문이다.<br>
	 * static멤버는 인스턴스변수를 참조할 수 없다.<br>
	 * <code><br>
	 * 		class Box&lt;T&gt; {<br>
	 * 			static T item; // 에러<br>
	 * 			static int compare(T t1, T t2) {...} // 에러<br>
	 * 		}<br>
	 * </code><br>
	 * static멤버는 타입 변수에 지정된 타입, 즉 대입된 타입의 종류에 관계없이 동일한 것이어야 하기 때문이다. 지네릭타입의 배열을 생성하는 것도 허용되지 않는다.<br>
	 * 지네릭 배열 타입의 참조변수를 선언하는 것은 가능하지만, 'new T[10]'과 같이 배열을 생성하는 것은 안 된다는 뜻이다.<br>
	 * <code><br>
	 * 		class Box&lt;T&gt; {<br>
	 *			T[] itemArr; // OK. T타입의 배열을 위한 참조변수<br>
	 *			...<br>
	 *			T[] toArray() {<br>
	 *				T[] tmpArr = new T[itemArr.length]; // 에러. 지네릭 배열 생성불가<br>
	 *				...<br>
	 *				return temArr;<br>
	 *				...<br>
	 *			}<br>
	 *			...<br>
	 * 		}<br>
	 * </code>
	 * 지네릭 배열을 생성할 수 없는 것은 new연산자 때문인데, 이 연산자는 컴파일 시점에 타입 T가 뭔지 정확히 알아야 한다. instanceof연산자도 new연산자와 같은 이유로 T를 피연산자로 사용할 수 없다.<br>
	 * 꼭 지네릭 배열을 생성해야할 필요가 있을 때는, new연산자대신 'Reflection API'의 newInstance()와 같이 동적으로 객체를 생성하는메서드로 배열을 생성하거나, Object배열을 생성해서 복사한 다음에 'T[]'로 형변환하는 방법 등을 사용한다.
	 */
	class Memo04{}

	/**
	 * <h5>1.3 지네릭 클래스의 객체 생성과 사용</h5>
	 * 지네릭 클래스 Box&lt;T&gt;가 다음과 같이 정의되어 있다고 가정하자. 이 Box&lt;T&gt;의 객체에는 한 가지 종류, 즉 T타입의 객체만 저장할 수 있다.<br>
	 * 전과 달리 ArrayList를 이용해서 여러 객체를 저장할 수 있도록 하였다.<br>
	 * <code><br>
	 *  class Box<T> {<br>
	 *     ArrayList&lt;T&gt; list = new ArrayList&lt;T&gt;();<br>
	 *     <br>
	 *     void addItem(T item) { list.add(item); }<br>
	 *     T get(int i) { return list.get(i); }<br>
	 *     ArrayList<T> getList() { return list; }<br>
	 *     int size() { return list.size(); }<br>
	 *     public String toString() { return list.toString(); }<br>
	 *  }<br>
	 * </code><br>
	 * Box&lt;T&gt;의 객체를 생성할 때는 다음과 같이 한다. 참조변수와 생성자에 대입된 타입(매개변수화된 타입)이 일치해야 한다. 일치하지 않으면<br>
	 * 에러가 발생한다.<br>
	 * Box&lt;Apple&gt; appleBox = new Box&lt;Apple&gt;(); // OK<br>
	 * Box&lt;Apple&gt; appleBox = new Box&lt;Grape&gt;(); // 에러<br>
	 * 두 타입이 상송관계에 있어도 마찬가지이다. Apple이 Fruit의 자손이라고 가정하자.<br>
	 * Box&lt;Fruit&gt; appleBox = new Box<Apple&gt;(); // 에러. 대입된 타입이 다르다.<br>
	 * 단, 두 지네릭 클래스의 타입이 상속관계에 있고, 대입된 타입이 같은 것은 괜찮다. FruitBox는 Box의 자손이라고 가정하자.<br>
	 * Box&lt;Apple&gt; appleBox = new FruitBox&lt;Apple&gt;(); // OK. 다형성<br>
	 * JDK1.7부터는 추정이 가능한 경우 타입을 생략할 수 있게 되었다. 참조변수의 타입으로부터 Box가 Apple타입의 객체만 저장한다는 것을 알<br>
	 * 수 있기 때문에, 생성자에 반복해서 타입을 지정해주지 않아도 되는 것이다. 따라서 아래의 두 문장은 동일하다.<br>
	 * Box&lt;Apple&gt; appleBox = new Box&lt;Apple&gt;();<br>
	 * Box&lt;Apple&gt; appleBox = new Box&lt;&gt;(); // OK. JDK1.7부터 생략가능<br>
	 */
	class Memo05{}

	static class Fruit { public String toString() { return "Fruit"; } }
	static class Apple extends Fruit { public String toString() { return "Apple"; } }
	static class Grape extends Fruit { public String toString() { return "Grape"; } }
	static class Toy { public String toString() { return "Toy"; } }

	static class FruitBox1 {

		public static void main(String[] args) {
			Box<Fruit> fruitBox = new Box<Fruit>();
			Box<Apple> appleBox = new Box<Apple>();
			Box<Toy> toyBox = new Box<Toy>();
			// Box<Grape> grapeBox = new Box<Apple>(); // 에러, 타입 불일치

			fruitBox.add(new Fruit());
			fruitBox.add(new Apple()); // OK. void add(Fruit item)

			appleBox.add(new Apple());
			appleBox.add(new Apple());
			// appleBox.add(new Toy()); // 에러. Box<Apple>에는 Apple만 담을 수 있음

			toyBox.add(new Toy());
			// toyBox.add(new Apple()); // 에러. Box<Toy>에는 Apple을 담을 수 없음

			System.out.println(fruitBox);
			System.out.println(appleBox);
			System.out.println(toyBox);

		}

	}

	static class Box<T> {
		ArrayList<T> list = new ArrayList<T>();
		void add(T item) { list.add(item); }
		T get(int i) { return list.get(i); }
		int size() { return list.size(); }
		public String toString() { return list.toString(); }
	}

	/**
	 * <h5>1.4 제한된 지네릭 클래스</h5><br>
	 * 지네릭 타입에 'extends'를 사용하면, 특정 타입의 자손들만 대입할 수 있게 제한할 수 있다.<br>
	 * <code><br>
	 * 		class FruitBox&lt;T extends Fruit&gt; { // Fruit의 자손만 타입으로 지정가능<br>
	 * 		ArrayList&lt;T&gt; list = new ArrayList&lt;T&gt;<br>
	 * 		...<br>
	 * 		}<br>
	 * </code><br>
	 * 여전히 한 종류의 타입만 담을 수 있지만, Fruit클래스의 자손들만 담을 수 있다는 제한이 더 추가된 것이다.<br>
	 * <code><br>
	 * 		FruitBox&lt;Apple&gt; appleBox = new FruitBox&lt;Apple&gt;(); // OK<br>
	 * 		FruitBox&lt;Toy&gt; toyBox = new FruitBox&lt;Toy&gt;(); // 에러. Toy는 Fruit의 자손이 아님<br>
	 * </code><br>
	 * 다형성에서 조상타입의 참조변수로 자손타읍이 객체를 가리킬 수 있는 것처럼, 매개변수화된 타입의 자손 타입도 가능한 것이다. 타입 매개변수<br>
	 * T에 Object를 대입하면, 모든 종류의 객체를 저장할 수 있게 된다.<br>
	 * 만일 클래스가 아니라 인터페이스를 구현해야 한다는 제약이 필요하다면, 이때도 'extends'를 사용한다. 'implements'를 사용하지 않는 다는점에 주의하자<br>
	 * 클래스 Fruit의 자손이면서 Eatable인터페이스도 구현해야한다면 아래와 같이 '&'기호로 연결한다.<br>
	 * <code><br>
	 *     class FruitBox&lt;T extends Fruit & Eatable&gt; { ... }<br>
	 * </code>
	 */
	class Memo06{}

	static class Fruit2 implements Eatable {
		public String toString() {return "Fruit";}
	}

	static class Apple2 extends Fruit2 { public String toString() {return "Apple";}}
	static class Grape2 extends Fruit2 { public String toString() {return "Grape";}}
	static class Toy2 { public String toString() {return "Toy";} }

	static interface Eatable {}

	static class FruitBoxEx2 {

		public static void main(String[] args) {
			FruitBox<Fruit2> fruitBox = new FruitBox<Fruit2>();
			FruitBox<Apple2> appleBox = new FruitBox<Apple2>();
			FruitBox<Grape2> grapeBox = new FruitBox<Grape2>();
			// FruitBox<Grape> grapeBox02 = new FruitBox<Apple>(); // 에러, 타입 불일치
			// FruitBox<Toy> toyBox = new FruitBox<Toy>(); // 에러.

			fruitBox.add(new Fruit2());
			fruitBox.add(new Apple2());
			fruitBox.add(new Grape2());
			appleBox.add(new Apple2());
			// appleBox.add(new Grape()); // 에러, Grape는 Apple의 자손이 아님
			grapeBox.add(new Grape2());

			System.out.println("fruitBox-"+fruitBox);
			System.out.println("appleBox-"+appleBox);
			System.out.println("grapeBox-"+grapeBox);

		}

	}

	static class FruitBox<T extends Fruit2 & Eatable> extends Box2<T> {}

	static class Box2<T> {
		ArrayList<T> list = new ArrayList<T>();
		void add(T item) { list.add(item); }
		T get(int i) { return list.get(i); }
		int size() { return list.size(); }
		public String toString() { return list.toString(); }
	}

	/**
	 * <h5>와일드 카드</h5>
	 * <pre>
	 * class Juicer {
	 *  static Juice makeJuice(FruitBox&lt;Fruit&gt; box) { // <Fruit>으로 지정
	 *      String tmp = "";
	 *      for(Fruit f : box.getList()) tmp += f + " ";
	 *      return new Juice(tmp)
	 *      }
	 * }
	 * </pre>
	 * <br>
	 * Juicer클래스는 지네릭 클래스가 아닌데다, 지네릭 클래스라고 해도 static메서드에는 타입 매개변수 T를 매개변수에 사용할 수 없으므로 아
	 * 예 지네릭스를 적용하지 않던가, 위와 같이 타입 매개변수 대신, 특정 타입을 지정해줘야 한다.
	 * <br>
	 * FruitBox&lt;Fruit&gt; fruitBox = new FruitBox&lt;Fruit&gt;();<br>
	 * FruitBox&lt;Apple&gt; appleBox = new FruitBox&lt;Apple&gt;();
	 * <br>
	 * ...
	 * <br>
	 * System.out.println(Juicer.makeJuice(fruitBox)); // OK, FruitBox&lt;Fruit&gt<br>
	 * System.out.println(Juicer.makeJuice(appleBox)); // 에러, FruitBox&lt;Apple&gt
	 * <br>
	 * 이렇게 지네릭 타입을 'FruitBox&lt;Fruit&gt'로 고정해 놓으면, 위의 코ㅓ드에서 알 수 있듯이 'FruitBox&lt;Apple&gt'타입의 객체는 makeJuice()
	 * 의 매개변수가 될 수 없으므로, 다음과 같이 여러 가지 타입의 매개변수를 갖는 makeJuice()를 만들 수 밖에 없다.
	 * <br>
	 * <pre>
	 * static Juice makeJuice(FruitBox&lt;Fruit&gt box) {
	 *  String tmp = "";
	 *  for(Fruit f : box.getList()) tmp += f + " ";
	 *  return new Juice(tmp)
	 * }
	 * </pre>
	 * <br>
	 * <pre>
	 * static Juice makeJuice(FruitBox&lt;Apple&gt box) {
	 *  String tmp = "";
	 *  for(Fruit f : box.getList()) tmp += f + " ";
	 *  return new Juice(tmp)
	 * }
	 * </pre>
	 * <br>
	 * 그러나 위와 같이 오버로딩하면, 컴파일 에러가 발생한다. <b>지네릭 타입이 다른 것만으로는 오버로딩이 성립하지 않기 때문이다.</b>
	 * 지네릭 타입은 컴파일러가 컴파일할 때만 사용하고 제거해버린다. 그래서 위의 두 메서드는 오버로딩이 아니라 '메서드 중복 정의'이다.
	 * <br>
	 * 이럴 때 사용하기 위해 고안된 것이 바로 '와일드 카드'이다. 와일드 카드는 기호 '?'로 표현하는데, 와일드 카드는 어떠한 타입도 될 수 있다.<br>
	 * '?'만으로는 Object타입과 다를 게 없으므로, 다음과 같이 'extends'와 'super'로 상한(upper bound)과 하한(lower bound)을 제한할 수 있다.<br>
	 * <blockquote>
	 *      <b><? extends T></b> - 와일드 카드의 상한 제한. T와 그 자손들만 가능<br>
	 *      <b><? spuer T></b> - 와일드 카드의 하한 제한. T와 그 조상들만 가능<br>
	 *      <b><?></b> - 제한 없음. 모든 타입이 가능. <? extends Object>와 동일
	 * </blockquote><br>
	 * <small>※지네릭 클래스와 달리 와일드 카드에는 '&'를 사용할 수 없다. 즉 &lt;? extends T & E&gt;와 같이 할 수 없다.</small><br>
	 * 와일드 카드를 사용해서 makeJuice()의 매개변수 타입을 FruitBox&lt;Fruit&gt;에서 FruitBox&lt;? extends Fruit&gt;으로 바꾸면 다음과 같이 된다.<br>
	 * <blockquote><br>
	 *     static Juice makeJuice(FruitBox<? extends Fruit> box) {<br>
	 *         String tmp = "";<br>
	 *         for(Fruit f : box.getList()) tmp += f + " ";<br>
	 *         return new Juice(tmp);<br>
	 *     }<br>
	 * </blockquote><br>
	 * 이제 이 메서드의 매개변수로 FruitBox&lt;Fruit&gt;뿐만 아니라, FruitBox&lt;Apple&gt;와 FruitBox&lt;Grape&gt;도 가능하게 된다.<br>
	 * <small>※매개변수의 타입을 'FruitBox&lt;? extends Object&gt;로 하면, 모든 종류의 FruitBox가 매개변수로 가능하다.</small><br>
	 * 매개변수의 타입을 FruitBox&lt;? extends Object&gt;로 하면, 모든 종류의 FruitBox가 이 메서드의 매개변수로 가능해진다.<br>
	 * 대신, 전과 달리 box의 요소가 Fruit의 자손이라는 보장이 없으므로 아래의 for문에서 box에 저장된 요소를 Fruit타입의 참조변수로 못받는다.<br>
	 * <blockquote><br>
	 *      static Juice makeJuice(FruitBox<? extends Fruit> box) {<br>
	 *          String tmp = "";<br>
	 *          for(Fruit f : box.getList()) tmp += f + " "; // 에러. Fruit이 아닐 수 있음<br>
	 *          return new Juice(tmp);<br>
	 *      }
	 * </blockquote><br>
	 * 그러나 실제로 테스트 해보면 문제없이 컴파일되는데 그 이유는 지네릭 클래스 FruitBox를 제한했기 때문이다.<br>
	 * class FruitBox&lt;<b>T extends Fruit</b>&gt; extends Box&lt;T&gt; {}<br>
	 * 컴파일러는 위 문장으로부터 모든 FruitBox의 요소들이 Fruit의 자손이라는 것을 알고 있으므로 문제 삼지 않는 것이다.
	 */
	class Memo07{}
}
