package chapter12;

/**
 * <h1>와일드 카드</h1>
 */
// TODO 12_1 ~ 12_4 는 Chapter12 클래스에 모두 정리해야함
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
    static class Memo01{}

    static class FruitBoxEx1 {

    }

}
