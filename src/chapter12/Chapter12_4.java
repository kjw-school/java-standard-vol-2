package chapter12;

import java.util.ArrayList;

/**
 * 1.4 제한된 지네릭 클래스
 */
public class Chapter12_4 {

    /**
     * 지네릭 타입에 'extends'를 사용하면, 특정 타입의 자손들만 대입할 수 있게 제한할 수 있다.
     *<p>
     * class FruitBox<T extends Fruit> { // Fruit의 자손만 타입으로 지정가능
     *<p>
     *     ArrayList<T> list = new ArrayList<T>
     *<p>
     *         ...
     *<p>
     * }
     * <p>
     * <p>
     * 여전히 한 종류의 타입만 담을 수 있지만, Fruit클래스의 자손들만 담을 수 있다는 제한이 더 추가된 것이다.
     * FruitBox<Apple> appleBox = new FruitBox<Apple>(); // OK
     * FruitBox<Toy> toyBox = new FruitBox<Toy>(); // 에러. Toy는 Fruit의 자손이 아님
     * 다형성에서 조상타입의 참조변수로 자손타읍이 객체를 가리킬 수 있는 것처럼, 매개변수화된 타입의 자손 타입도 가능한 것이다. 타입 매개변수
     * T에 Object를 대입하면, 모든 종류의 객체를 저장할 수 있게 된다.
     * 만일 클래스가 아니라 인터페이스를 구현해야 한다는 제약이 필요하다면, 이때도 'extends'를 사용한다. 'implements'를 사용하지 않는 다는
     * 점에 주의하자
     * <p>
     * 클래스 Fruit의 자손이면서 Eatable인터페이스도 구현해야한다면 아래와 같이 '&'기호로 연결한다.
     * <p>
     * class FruitBox<T extends Fruit & Eatable> { ... }
     */
    static class Memo01 {}

    static class Fruit implements Eatable {
        public String toString() {return "Fruit";}
    }

    static class Apple extends Fruit { public String toString() {return "Apple";}}
    static class Grape extends Fruit { public String toString() {return "Grape";}}
    static class Toy { public String toString() {return "Toy";} }

    static interface Eatable {}

    static class FruitBoxEx2 {

        public static void main(String[] args) {
            FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
            FruitBox<Apple> appleBox = new FruitBox<Apple>();
            FruitBox<Grape> grapeBox = new FruitBox<Grape>();
            // FruitBox<Grape> grapeBox02 = new FruitBox<Apple>(); // 에러, 타입 불일치
            // FruitBox<Toy> toyBox = new FruitBox<Toy>(); // 에러.

            fruitBox.add(new Fruit());
            fruitBox.add(new Apple());
            fruitBox.add(new Grape());
            appleBox.add(new Apple());
            // appleBox.add(new Grape()); // 에러, Grape는 Apple의 자손이 아님
            grapeBox.add(new Grape());

            System.out.println("fruitBox-"+fruitBox);
            System.out.println("appleBox-"+appleBox);
            System.out.println("grapeBox-"+grapeBox);

        }

    }

    static class FruitBox<T extends Fruit & Eatable> extends Box<T> {}

    static class Box<T> {
        ArrayList<T> list = new ArrayList<T>();
        void add(T item) { list.add(item); }
        T get(int i) { return list.get(i); }
        int size() { return list.size(); }
        public String toString() { return list.toString(); }
    }

}
