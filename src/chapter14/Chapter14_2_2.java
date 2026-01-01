package chapter14;

/**
 * <h1>2.2 스트림 만들기</h1>
 */
public class Chapter14_2_2 {

	/**
	 * <h5>컬렉션</h5><br>
	 * 컬렉션의 최고 조상인 Collection에 stream()이 정의되어 있다. 그래서 Collection의 자손인 List와 Set을 구현한 컬렉션 클래스들은 모두 이 메서드로 스트림을 생성할 수 있다.<br>
	 * stream()은 해당 컬렉션을 소스(source)로 하는 스트림을 반환한다.<br>
	 * Stream&lt;T&gt; Collection.stream()<br>
	 * forEach()는 지정된 작업을 스트림의 모든 요소에 대해 수행한다.<br>
	 * <pre><code>
	 *     intStream.forEach(System.out::println); // 스트림의 모든 요소를 출력한다.
	 *     intStream.forEach(System.out::println); // 에러. 스트림이 이미 닫혔다.
	 * </code></pre>
	 * <br>
	 * 한 가지 주의할 점은 forEach()가 스트림의 요소를 소모하면서 작업을 수행하므로 같은 스트림에 forEach()를 두 번 호출할 수 없다는 것이다.<br>
	 * 그래서 스트림의 요소를 한번 더 출력하려면 스트림을 새로 생성해야 한다. forEach()에 의해 스트림의 요소가 소모되는 것이지, 소스의 요소가 소모되는 것은 아니기 때문에 같은 소스로부터 다시 스트림을 생성할 수 있다.<br>
	 */
	class Memo1 {
	}

	/**
	 * <h5>배열</h5><br>
	 * 배열을 소스로 하는 스트림을 생성하는 메서드는 다음과 같이 Stream과 Arrays에 static메서드로 정의되어 있다.<br>
	 * <pre><code>
	 *     Stream<T> Stream.of(T... values) // 가변인자
	 *     Stream<T> Stream.of(T[])
	 *     Stream<T> Arrays.stream(T[])
	 *     Stream<T> Arrays.stream(T[] array, int startInclusive, int endExclusive)
	 * </code></pre>
	 */
	class Memo2 {

	}

	/**
	 * <h5>특정 범위의 정수</h5><br>
	 * IntStream과 LongStream은 다음과 같이 지정된 범위의 연속된 정수를 스트림으로 생성해서 반환하는 range()와 rangeClosed()를 가지고 있다.<br>
	 * <pre><code>
	 *     IntStream IntStream.range(int begin, int end)
	 *     IntStream IntStream.rangeClosed(int begin, int end)
	 * </code></pre>
	 * <br>
	 * range()의 경우 경계의 끝인 end가 범위에 포함되지 않고, rangeClosed()의 경우는 포함된다.
	 */
	class Memo3 {

	}

	/**
	 * <h5>임의의 수</h5><br>
	 * 난수를 생성하는데 사용하는 Random클래스에는 아래와 같은 인스턴스 메서드들이 포함되어 있다. 이 메서드들은 해당 타입의 난수들로 이루어진 스트림을 반환한다.<br>
	 * <pre><code>
	 *     IntStream ints()
	 *     LongStream longs()
	 *     DoubleStream doubles()
	 * </code></pre>
	 * <br>
	 * 이 메서드들이 반환하는 스트림은 크기가 정해지지 않은 '무한 스트림(infinite stream)'이므로 limit()도 같이 사용해서 스트림의 크기를 제한해 주어야 한다.<br>
	 * limit()은 스트림의 개수를 지정하는데 사용되며, 무한 스트림을 유한 스트림으로 만들어 준다.<br>
	 * <pre><code>
	 *     IntStream intStream = new Random().ints(); // 무한 스트림
	 *     IntStream.limit(5).forEach(System.out::println); // 5개의 요소만 출력한다.
	 * </code></pre>
	 * <br>
	 * 아래의 메서드들은 매개변수로 스트림의 크기를 지정해서 '유한 스트림'을 생성해서 반환하므로 limit()을 사용하지 않아도 된다.<br>
	 * <pre><code>
	 *     IntStream ints(long streamSize)
	 *     LongStream longs(long streamSize)
	 *     DoubleStream doubles(long streamSize)
	 * </code></pre>
	 * <br>
	 * 위 메서드들에 의해 생성된 스트림의 난수는 아래의 범위를 갖는다.<br>
	 * Integer.MIN_VALUE <= ints() <= Integer.MAX_VALUE<br>
	 * Long.MIN_VALUE <= longs() <= Long.MAX_VALUE<br>
	 * 0.0 <= doubles() < 1.0<br>
	 * 지정된 범위(begin~end)의 난수를 발생시키는 스트림을 얻는 메서드는 아래와 같다. 단, end는 범위에 포함되지 않는다.<br>
	 * <pre><code>
	 *     IntStream ints(int begin, int end)
	 *     LongStream longs(long begin, long end)
	 *     DoubleStream doubles(double begin, double end)
	 *     IntStream ints(long streamSize, int begin, int end)
	 *     LongStream longs(long streamSize, long begin, long end)
	 *     DoubleStream doubles(long streamSize, double begin, double end)
	 * </code></pre>
	 */
	class Memo4 {

	}

	/**
	 * <h5>람다식 - iterate(), generate()</h5><br>
	 * Stream클래스의 iterate()와 generate()는 람다식을 매개변수로 받아서, 이 람다식에 의해 계산되는 값들을 요소로 하는 무한 스트림을 생성한다.<br>
	 * <pre><code>
	 *     static <T> Stream<T> iterate(T seed, UnaryOperator<T> f)
	 *     static <T> Stream<T> generate(Supplier<T> s)
	 * </code></pre>
	 * <br>
	 * iterate()는 씨앗값(seed)으로 지정된 값부터 시작해서, 람다식 f에 의해 계산된 결과를 다시 seed값으로 해서 계산을 반복한다.<br>
	 * generate()도 iterate()처럼, 람다식에 의해 계산되는 값을 요소로 하는 무한 스트림을 생성해서 반환하지만, iterate()와 달리, 이전 결과를 이용해서 다음 요소를 계산하지 않는다.<br>
	 * 한 가지 주의할 점은 iterate()와 generate()에 의해 생성된 스트림을 아래와 같이 기본형 스트림 타입의 참조변수로 다룰 수 없다는 것이다.<br>
	 * <pre><code>
	 *     IntStream evenStream = Stream.iterate(0, n -> n+2); // 에러.
	 *     DoubleStream randomStream = Stream.generate(Math::random); // 에러.
	 * </code></pre>
	 */
	class Memo5 {

	}

	/**
	 * <h5>파일</h5><br>
	 * java.nio.file.Files는 파일을 다루는데 필요한 유용한 메서드들을 제공하는데, list()는 지정된 디렉토리(dir)에 있는 파일의 목록을 소스로 하는 스트림을 생성해서 반환한다.<br>
	 * <pre><code>
	 *     Stream<Path> Files.list(Path dir)
	 * </code></pre>
	 * <br>
	 * <small>※ Path는 하나의 파일 또는 경로를 의미한다.</small><br>
	 * 그리고 파일의 한 행(line)을 요소로 하는 스트림을 생성하는 메서드도 있다.<br>
	 * 아래의 세 번째 메서드는 BufferedReader클래스에 속한 것인데, 파일 뿐만 아니라 다른 입력대상으로부터도 데이터를 행단위로 읽어올 수 있다.<br>
	 * <pre><code>
	 *     Stream<String> Files.lines(Path path)
	 *     Stream<String> Files.lines(Path path, Charset cs)
	 *     Stream<String> lines() // BufferedReader클래스의 메서드
	 * </code></pre>
	 */
	class Memo6 {

	}

	/**
	 * <h5>빈 스트림</h5><br>
	 * 요소가 하나도 없는 비어있는 스트림을 생성할 수도 있다.<br>
	 * <pre><code>
	 *     Stream emptyStream = Stream.empty() // empty()는 빈 스트림을 생성해서 반환한다.
	 * </code></pre>
	 */
	class Memo7 {

	}

	/**
	 * <h5>두 스트림의 연결</h5><br>
	 * Stream의 static메서드인 concat()을 사용하면, 두 스트림을 하나로 연결할 수 있다. 물론 연결하려는 두 스트림의 요소는 같은 타입이어야 한다.<br>
	 */
	class Memo8 {

	}

}
