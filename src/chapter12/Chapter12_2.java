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
     *
     */

}
