package chapter11;

/**
 * 컬렉션 프레임웍 Collections Framework
 */
public class Chapter11 {

    /**
     * 1. 컬렉션 프레임웍(Collections Framework)
     * 컬렉션 프레임웍이란, '데이터 군(群)을 저장하는 클래스들을 표준화한 설계'를 뜻한다.
     * 컬렉션(Collection)은 다수(多數)의 데이터, 즉 데이터 그룹을, 프레임웍은 표준화된 프로그래밍 방식을 의미한다.
     * ※ Java API문서에서는 컬렉션 프레임웍을 '데이터 군(群, group)을 다루고 표현하기 위한 단일화된 구조(architecture)'라고 정의하고 있다.
     *
     * JDK1.2 이전까지는 Vector, Hashtable, Properties와 같은 컬렉션 클래스, 다수의 데이
     * 터를 저장할 수 있는 클래스, 들을 서로 다른 각자의 방식으로 처리해야 했으나 JDK1.2부
     * 터 컬렉션 프레임웍이 등장하면서 다양한 종류의 컬렉션 클래스가 추가되고 모든 컬렉션
     * 클래스를 표준화된 방식으로 다룰 수 있도록 체계화되었다.
     * ※ 앞으로 Vector와 같이 다수의 데이터를 저장할 수 있는 클래스를 '컬렉션 클래스'라고 하겠다.
     */

    /**
     * 1.1 컬렉션 프레임웍의 핵심 인터페이스
     * 컬렉션 프레임웍에서는 컬렉션데이터 그룹을 크게 3가지 타입이 존재한다고 인식하고 각
     * 컬렉션을 다루는데 필요한 기능을 가진 3개의 인터페이스를 정의하였다. 인터페이
     * 스 List와 Set의 공통된 부분을 다시 뽑아서 새로운 인터페이스인 Collection을 추가로 정
     * 의하였다.
     *              Collection
     *         ↗                 ↖       Map
     *        List              Set
     * ※ JDK1.5부터 Iterable인터페이스가 추가되고 이를 Collection인터페이스가 상속받도록 변겨되었으나 이것은 단지 인
     * 터페이스들의 공통적인 메서드인 iterator()를 뽑아서 중복을 제거하기 위한 것에 불과하므로 상속계층도에서 별 의미가 없다.
     *
     * 인터페이스 | 특징
     * List     | 순서가 있는 데이터의 집합, 데이터의 중복을 허용한다. 예)대기자 명단
     * Set      | 순서를 유지하지 않는 데이터의 집합, 데이터의 중복을 허용하지 않는다. 예) 양싀 정수집합, 소수의 집합, 구현클래스 : HashSet, TreeSet 등
     * Map      | 키(key)와 값(value)의 쌍(pair)으로 이루어진 데이터의 집합 순서는 유지되지 않으며, 키는 중복을 허용하지않고, 값은 중복을 허용한다. 예) 우편번호, 지역번호(전화번호), 구현클래스: HashMap, TreeMap, Hashtable, Properties 등
     * ※ 키(Key)란, 데이터 집합 중에서 어떤 값(value)을 찾는데 열쇠(key)가 된다는 의미에서 붙여진 이름이다. 그래서
     * 키(Key)는 중복을 허용하지 않는다.
     * 컬렉션 프레임웍의 모든 컬렉션 클래스들은 List, Set, Map 중의 하나를 구현하고 있으
     * 며, 구현한 인터페이스의 이름이 클래스의 이름에 포함되어있어서 이름만으로도 클래스
     * 의 특징을 쉽게 알 수 있도록 되어있다.
     *                      Object
     *  Object                ↑
     *    ↑                 AbstractCollection   Collection
     *  Vector                ↑                   ↑  
     *                      AbstractList         List
     *                        ↑
     *                      Vector ↗
     *  Collection인터페이스
     *  Collection인터페이스는 컬렉션 클래스에 저장된 데이터를 일고, 추가하고 삭제하는 등
     *  컬렉션을 다루는데 가장 기본적인 메서드들을 정의하고 있다.
     */

    /**
     * List인터페이스
     * List인터페이스는 중복을 허용하면서 저장순서가 유지되는 컬렉션을 구현하는데 사용된다.
     *                  List
     *       ↗          ↑             ↖
     * Vector           ArrayList     LinkedList
     * ↑
     * Stack
     */

    /**
     * Set인터페이스
     * Set인터페이스는 중복을 허용하지 않고 저장순서가 유지되지 않는 컬렉션 클래스를 구현
     * 하는데 사용된다. Set인터페이스를 구현한 클래스로는 HastSet, TreeSet 등이 있다.
     *          Set
     * ↗        ↑
     * HastSet  SortedSet
     *          ↑
     *          TreeSet
     */

    /**
     * Map인터페이스
     * Map인터페이스는 키(key)와 값(value)을 하나의 쌍으로 묶어서 저장하는 컬렉션 클래스
     * 를 구현하는 데 사용된다. 키는 중복될 수 없지만 값은 중복을 허용한다. 기존에 저장된
     * 데이터와 중복된 키와 값을 저장하면 기존의 값은 없어지고 마지막에 저장된 값이 남게
     * 된다.
     * ※ Map이란 갠며은 어떤 두 값을 연결한다는 의미에서 붙여진 이름이다.
     *                  Map
     *    ↗              ↑          ↖
     * Hashtable        HashMap     SortedMap
     *                  ↑               ↑
     *                  LinkedHashMap  TreeMap
     * Set keySet() - Map에 저장된 모든 key객체를 반환한다.
     * Collection values() - Map에 저장된 모든 value객체를 반환한다.
     * values()에서는 반환타입이 Collection이고, keySet()에서는 반환타입이 Set인 것에 주
     * 목하자, Map인터페이스에서 값(value)은 중복을 허용하기 때문에 Collection타입으로 반
     * 환하고, 키(key)는 중복을 허용하지 않기 때문에 Set타입으로 반환한다.
     */

    /**
     * Map.Entry인터페이스
     * Map.Entry인터페이스는 Map인터페이스의 내부 인터페이스이다. 내부 클래스와 같이 인
     * 터페이스도 인터페이스 안에 인터페이스를 정의하는 내부 인터페이스(inner interface)를
     * 정의하는 것이 가능하다.
     * Map에 저장되는 key-value쌍을 다루기 위해 내부적으로 Entry인터페이스를 정의해
     * 놓았다.
     */




}































