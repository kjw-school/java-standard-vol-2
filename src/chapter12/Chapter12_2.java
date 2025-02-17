package chapter12;

/**
 * 1.2 지네릭 클래스의 선언
 */
public class Chapter12_2 {

    /**
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

    /**
     * 지네릭스의 용어
     * class Box<T> {}, Box: 원시타입, Box<T>: 지네릭 클래스
     * Box<T>, 지네릭 클래스, 'T의 Box' 또는 'T Box'라고 읽는다.
     * T, 타입 변수 또는 타입 매개변수.(T는 타입문자)
     * Box, 원시 타입(raw type)
     * 타입 문자 T는 지네릭 클래스 Box<T>의 타입 변수 또는 타입 매개변수라고 하는데, 메서드의 매개변수와 유사한 면이 있기 때문이다.
     * 그래서 아래와 같이 타입 매개변수에 타입을 지정하는 것을 '지네릭 타입 호출'이라고 하고, 지정된 타입'String'을 '매개변수화된 타입(para
     * meterized type)'이라고 한다.
     * Box<String> b = new Box<String>();, String: 대입된 타입(매개변수화된 타입, parameterized type), Box<String>: 지네릭 타입
     * 호출
     */

    /**
     * 지네릭스의 제한
     * 지네릭 클래스 Box의 객체를 생성할 때, 객체별로 다른 타입을 지정하는 것은 적절하다. 지네릭스는 이처럼 인스턴스별로 다르게 동작하도록
     * 하려고 만든 기능이니까.
     * 그러나 모든 객체에 대해 동일하게 동작해야하는 static멤버에 타입 변수 T를 사용할 수 없다. T는 인스턴스변수로 간주되기 때문이다.
     * static멤버는 인스턴스변수를 참조할 수 없다.
     * class Box<T> {
     *     static T item; // 에러
     *     static int compare(T t1, T t2) {...} // 에러
     * }
     * static멤버는 타입 변수에 지정된 타입, 즉 대입된 타입의 종류에 관계없이 동일한 것이어야 하기 때문이다. 지네릭타입의 배열을 생성하는 것
     * 도 허용되지 않는다. 지네릭 배열 타입의 참조변수를 선언하는 것은 가능하지만, 'new T[10]'과 같이 배열을 생성하는 것은 안 된다는 뜻
     * 이다.
     * class Box<T> {
     *     T[] itemArr; // OK. T타입의 배열을 위한 참조변수
     *     ...
     *     T[] toArray() {
     *         T[] tmpArr = new T[itemArr.length]; // 에러. 지네릭 배열 생성불가
     *         ...
     *         return temArr;
     *         ...
     *     }
     *     ...
     * }
     * 지네릭 배열을 생성할 수 없는 것은 new연산자 때문인데, 이 연산자는 컴파일 시점에 타입 T가 뭔지 정확히 알아야 한다. instanceof연산자
     * 도 new연산자와 같은 이유로 T를 피연산자로 사용할 수 없다.
     * 꼭 지네릭 배열을 생성해야할 필요가 있을 때는, new연산자대신 'Reflection API'의 newInstance()와 같이 동적으로 객체를 생성하는
     * 메서드로 배열을 생성하거나, Object배열을 생성해서 복사한 다음에 'T[]'로 형변환하는 방법 등을 사용한다.
     */


}