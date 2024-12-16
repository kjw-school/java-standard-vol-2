package chapter12;

// 1.3 지네릭 클래스의 객체 생성과 사용
public class Chapter12_3 {

    /**
     * 지네릭 클래스 Box<T>가 다음과 같이 정의되어 있다고 가정하자. 이 Box<T>의 객체에는 한 가지 종류, 즉 T타입의 객체만 저장할 수 있다.
     * 전과 달리 ArrayList를 이용해서 여러 객체를 저장할 수 있도록 하였다.
     * class Box<T> {
     *     ArrayList<T> list = new ArrayList<T>();
     *
     *     void addItem(T item) { list.add(item); }
     *     T get(int i) { return list.get(i); }
     *     ArrayList<T> getList() { return list; }
     *     int size() { return list.size(); }
     *     public String toString() { return list.toString(); }
     * }
     * Box<T>의 객체를 생성할 때는 다음과 같이 한다. 참조변수와 생성자에 대입된 타입(매개변수화된 타입)이 일치해야 한다. 일치하지 않으면
     * 에러가 발생한다.
     * Box<Apple> appleBox = new Box<Apple>(); // OK
     * Box<Apple> appleBox = new Box<Grape>(); // 에러
     * 두 타입이 상송관계에 있어도 마찬가지이다. Apple이 Fruit의 자손이라고 가정하자.
     * Box<Fruit> appleBox = new Box<Apple>(); // 에러. 대입된 타입이 다르다.
     * 단, 두 지네릭 클래스의 타입이 상속관계에 있고, 대입된 타입이 같은 것은 괜찮다. FruitBox는 Box의 자손이라고 가정하자.
     * Box<Apple> appleBox = new FruitBox<Apple>(); // OK. 다형성
     * JDK1.7부터는 추정이 가능한 경우 타입을 생략할 수 있게 되었다. 참조변수의 타입으로부터 Box가 Apple타입의 객체만 저장한다는 것을 알
     * 수 있기 때문에, 생성자에 반복해서 타입을 지정해주지 않아도 되는 것이다. 따라서 아래의 두 문장은 동일하다.
     * Box<Apple> appleBox = new Box<Apple>();
     * Box<Apple> appleBox = new Box<>(); // OK. JDK1.7부터 생략가능
     */

    class Fruit { public String toString() { return "Fruit"; } }
    class Apple extends Fruit { public String toString() { return "Apple"; } }
    class Grape extends Fruit { public String toString() { return "Grape"; } }
    class Toy { public String toString() { return "Toy"; } }

}
