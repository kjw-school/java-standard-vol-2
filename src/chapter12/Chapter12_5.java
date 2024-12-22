package chapter12;

/**
 * 와일드 카드
 */
public class Chapter12_5 {

    /**
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
     * 이럴 때 사용하기 위해 고안된 것이 바로 '와일드 카드'이다. 와일드 카드는 기호 '?'로 표현하는데, 와일드 카드는 어떠한 타입도 될 수 있다.
     */
    static class Memo01{}

}
