package chapter14;

public class Chapter14_2_6 {

	/**
	 * <h5>2.6 collect()</h5><br>
	 * collect()는 스트림의 요소를 수집하는 최종 연산으로 앞서 배운 리듀싱(reducing)과 유사하다.<br>
	 * collect()가 스트림의 요소를 수집하려면, 어떻게 수집할 것인가에 대한 방법이 정의되어 있어야 하는데, 이 방법을 정의한 것인 바로 컬렉터(collector)이다.<br>
	 * 컬렉터는 Collector인터페이스를 구현한 것으로, 직접 구현할 수도 있고 미리 작성된 것을 사용할 수도 있다.<br>
	 * <pre><code>
	 *     collect() 스트림의 최종연산, 매개변수로 컬렉터를 필요로 한다.
	 *     Collector 인터페이스, 컬렉터는 이 인터페이스를 구현해야한다.
	 *     Collectors 클래스, static메서드로 미리 작성된 컬렉터를 제공한다.
	 * </code></pre>
	 * <br>
	 * collect()는 매개변수의 타입이 Collector인데, 매개변수가 Collector를 구현한 클래스의 객체이어야 한다는 뜻이다.
	 */
	class Memo1 {

	}

	/**
	 * <h5>스트림을 컬렉션과 배열로 변환 - toList(), toSet(), toMap(), toCollection(), toArray()</h5><br>
	 * 스트림의 모든 요소를 컬렉션에 수집하려면, Collectors클래스의 toList()와 같은 메서드를 사용하면 된다.<br>
	 * List나 Set이 아닌 특정 컬렉션을 지정하려면, toCollection()에 해당 컬렉션의 생성자 참조를 매개변수로 넣어주면 된다.<br>
	 * <pre><code>
	 *     List<String> names = stuStream.map(Student::getName).collect(Collectors.toList());
	 *     ArrayList<String> list = names.stream().collect(Collectors.toCollection(ArrayList::new));
	 * </code></pre>
	 * <br>
	 * Map은 키와 값의 쌍으로 저장해야하므로 객체의 어떤 필드를 키로 사용할지와 값으로 사용할지를 지정해줘야 한다.<br>
	 * <pre><code>
	 *     Map<String, Person> map = personStream.collect(Collectors.toMap(p -> p.getRegId(), p -> p));
	 * </code></pre>
	 * <br>
	 * 위의 문장은 요소 타입이 Person인 스트림에서 사람의 주민번호(regId)를 키로 하고, 값으로 Person객체를 그대로 저장한다.<br>
	 * <small>※항등 함수를 의미하는 람다식 'p -> p' 대신 Function.identity()를 쓸 수도 있다.</small><br>
	 * 스트림에 저장된 요소들을 "T[]"타입의 배열로 변환하려면, toArray()를 사용하면 된다.<br>
	 * 단, 해당 타입의 생성자 참조를 매개변수로 지정해줘야 한다. 만일 매개변수를 지정하지 않으면 반환되는 배열의 타입은 'Object[]'이다.
	 */
	class Memo2 {

	}

	/**
	 * <h5>리듀싱 - reducing()</h5><br>
	 * 리듀싱 역시 collect()로 가능하다.<br>
	 * <pre><code>
	 *     IntStream intStream = new Random().ints(1, 46).distinct().limit(6);
	 *     OptionalInt max = intStream.reduce(Integer::max);
	 *     Optional<Integer> max = intStream.boxed().collect(reducing(Integer::max));
	 *     long sum = intStream.reduce(0, (a,b) -> a + b);
	 *     long sum = intStream.boxed().collect(reducing(Integer::max));
	 *
	 *     int grandTotal = stuStream.map(Student::getTotalScore).reduce(0, Integer::sum);
	 *     int grandTotal = stuStream.collect(reducing(0, Student::getTotalScore, Integer::sum));
	 * </code></pre>
	 * <br>
	 * Collectors.reducing()에는 아래와 같이 3가지 종류가 있다. 세 번째 메서드만 제외하고 reduce()와 같다.
	 * <pre><code>
	 *     Collector reducing(BinaryOperator<T> op)
	 *     Collector reducing(T identity, BinaryOperator<T> op)
	 *     Collector reducing(U identity, Function<T, U> mapper, BinaryOperator<U> op)
	 * </code></pre>
	 *
	 */
	class Memo3 {

	}

	/**
	 * <h5>문자열 결합 - joining()</h5><br>
	 * 문자열 스트림의 모든 요소를 하나의 문자열로 연결해서 반환한다. 구분자를 지정해줄 수도 있고, 접두사와 접미사도 지정가능하다.<br>
	 * 스트림의 요소가 String이나 StringBuffer처럼 CharSequence의 자손인 경우에만 결합이 가능하므로 스트림의 요소가 문자열이 아닌 경우에는 먼저 map()을 이용해서 스트림의 요소를 문자열로 변환해야 한다.
	 */
	class Memo4 {

	}

	/**
	 * <h5>그룹화와 분할 - groupingBy(), partitioningBy()</h5><br>
	 * 그룹화는 스트림의 요소를 특정 기준으로 그룹화하는 것을 의미하고, 분할은 스트림의 요소를 두 가지, 지정된 조건에 일치하는 그룹과 일치하지 않는 그룹으로의 분할을 의미한다.<br>
	 * 아래의 메서드 정의에서 알 수 있듯이, groupingBy()는 스트림의 요소를 Function으로, partitioningBy()는 Predicate로 분류한다.<br>
	 * <pre><code>
	 *     Collector groupingBy(Function classifier)
	 *     Collector partitioningBy(Predicate predicate)
	 * </code></pre>
	 * <br>
	 * 메서드의 정의를 보면 groupingBy()와 partitioningBy()가 분류를 Function으로 하느냐 Predicate로 하느냐의 차이만 있을 뿐 동일하다는 것을 알 수 있다.<br>
	 * 스트림을 두 개의 그룹으로 나눠야 한다면, 당연히 partitioningBy()로 분할하는 것이 더 빠르다. 그 외에는 groupingBy()를 쓰면 된다. 그리고 그룹화와 분할의 결과는 Map에 담겨 반환된다.
	 */
	class Memo5 {

	}

	/**
	 * <h5>partitioningBy()에 의한 분류</h5><br>
	 * <pre><code>
	 *     // 1. 기본분할
	 *     Map<Boolean, List<Student>> stuBySex = stuStream.collect(partitioningBy(Student::isMale)); // 학생들을 성별로 분할
	 *     List<Student> maleStudent = stuBySex.get(true); // Map에서 남학생 목록을 얻는다.
	 *     List<Student> femaleStudent = stuBySex.get(false); // Map에서 여학생 목록을 얻는다.
	 * </code></pre>
	 * <br>
	 * <pre><code>
	 *     // counting() 을 추가하여 남학생의 수와 여학생의 수 구하기
	 *     // 2. 기본 분할 + 통계 정보
	 *     Map<Boolean, Long> stuNumBySex = stuStream.collect(partitioningBy(Student::isMale, counting()));
	 * </code></pre>
	 * <br>
	 * <pre><code>
	 *     counting()대신 summingLong()을 사용하여, 남학생과 여학생의 총점을 구하기
	 *     Map<Boolean, Optional<Student>> topScoreBySex = stuStream.collect(partitioningBy(Student::isMale, maxBy(comparingInt(Student::getScore))));
	 * </code></pre>
	 * <br>
	 * maxBy()는 반환타입이 Optional&lt;T&gt;라서 위와 같은 결과가 나왔다. Optional&lt;Student&gt;가 아닌 Student를 반환 결과로 얻으려면, 아래와 같이 collectingAndThen()과 Optional::get을 함께 사용하면 된다.<br>
	 * <pre><code>
	 *     Map<Boolean, Student> topScoreBySex = stuStream.collect(partitioningBy(Student::isMale, collectingAndThen(maxBy(comparingInt(Student::getScore)), Optional::get)));
	 * </code></pre>
	 * <br>
	 * <pre><code>
	 *     // 성적이 150점 아래인 학생들 불합격처리, 불합격자를 성별로 분류
	 *     Map<Boolean, Map<Boolean, List<Student>>> failedStuBySex = stuStream.collect(partitioningBy(Student::isMale, partitioningBy(s -> s.getScore() < 150)));
	 *     List<Student> failedMaleStu = failedStuBySex.get(true).get(true);
	 *     List<Student> failedFemaleStu = failedStuBySex.get(false).get(true);
	 * </code></pre>
	 */
	class Memo6 {

	}

	/**
	 * <h5>groupingBy()에 의한 분류</h5><br>
	 * stuStream을 반 별로 그룹지어 Map에 저장하는 방법은 다음과 같다.<br>
	 * <pre><code>
	 *     Map<Integer, List<Student>> stuByBan = stuStream.collect(groupingBy(Student::getBan)); // toList()가 생략됨
	 * </code></pre>
	 * <br>
	 * groupingBy()로 그룹화를 하면 기본적으로 List&lt;T&gt;에 담는다. 만일 원한다면, toList()대신 toSet()이나 toCollection(HashSet::new)을 사용할 수도 있다.<br>
	 * <pre><code>
	 *     Map<Integer, List<Student>> stuByBan = stuStream.collect(groupingBy(Student::getBan, toList())); // toList() 생략가능
	 *     Map<Integer, HashSet<Student>> stuByHak = stuStream.collect(groupingBy(Student::getHak, toCollection(HashSet::new)));
	 * </code></pre>
	 * <br>
	 * <pre><code>
	 *     // 성적의 등급으로 그룹화 하기
	 *     Map<Student.Level, Long> stuByLevel = stuStream.collect(groupingBy(s -> {
	 *         if(s.getScore() >= 200) // return Student.Level.HIGH:
	 *         else if(s.getScore() >= 100) // return Student.Level.MID;
	 *         else // return Student.Level.LOW;
	 *        }, counting()));
	 * </code></pre>
	 */
	class Memo7 {

	}

}
