package chapter10;

/**
 * <h1>3. java.time패키지</h1><br>
 * Date와 Calendar가 가지고 있던 단점들을 해소하기 위해 드디어 JDK1.8부터 'java.time패키지'가 추가되었다.<br>
 * 이 패키지는 4개의 하위 패키지를 가지고 있다.<br>
 * <table>
 *     <thead>
 *         <th>패키지</th>
 *         <th>설명</th>
 *     </thead>
 *     <tbody>
 *         <tr>
 *             <td>java.time</td>
 *             <td>날짜와 시간을 다루는데 필요한 핵심 클래스들을 제공</td>
 *         </tr>
 *         <tr>
 *             <td>java.time.chrono</td>
 *             <td>표준(ISO)이 아닌 달력 시스템을 위한 클래스들을 제공</td>
 *         </tr>
 *         <tr>
 *             <td>java.time.format</td>
 *             <td>날짜와 시간을 파싱하고, 형식화하기 위한 클래스들을 제공</td>
 *         </tr>
 *         <tr>
 *             <td>java.time.temporal</td>
 *             <td>날짜와 시간의 필드(field)와 단위(unit)을 위한 클래스들을 제공</td>
 *         </tr>
 *         <tr>
 *             <td>java.time.zone</td>
 *             <td>시간대(time-zone)와 관련된 클래스들을 제공</td>
 *         </tr>
 *     </tbody>
 * </table>
 * <br>
 * 패키지들에 속한 클래스들의 가장 큰 특징은 String클래스처럼 '불변(immutable)'이라는 것이다.<br>
 * 날짜나 시간을 변경하는 메서드들은 기존의 객체를 변경하는 대신 향상 변경된 새로운 객체를 반환한다.<br>
 * 기존 Calendar클래스는 변경가능하므로, 멀티 쓰레드 환경에서 안전하지 못하다.<br>
 * 멀티 쓰레드 환경에서는 동시에 여러 쓰레드가 같은 객체에 접근할 수 있기 때문에, 변경 가능한 객체는 데이터가 잘못된 가능성이 있으며, 이를 쓰레드에 안전(thread-safe)하지 않다고 한다.
 */
public class Chapter10_3 {

	/**
	 * <h5>3.1 java.time패키지의 핵심 클래스</h5><br>
	 * 날짜와 시간을 하나로 표현하는 Calendar클래스와 달리, java.time패키지에서는 날짜와 시간을 별도의 클래스로 분리해 놓았다.<br>
	 * 시간을 표현할 때는 LocalTime클래스를 사용하고, 날짜를 표현할 때는 LocalDate클래스를 사용한다.<br>
	 * 날짜와 시간이 모두 필요할 때는 LocalDateTime클래스를 사용하면 된다.<br>
	 * <pre><code>
	 *     LocalDate(날짜) + LocalTime(시간) -> LocalDateTime(날짜와 & 시간)
	 * </code></pre>
	 * <br>
	 * 여기에 시간대(time-zone)까지 다뤄야 한다면, ZonedDateTime클래스를 사용하자<br>
	 * <pre><code>
	 *     LocalDateTime + 시간대 -> ZonedDateTime
	 * </code></pre>
	 * <br>
	 * Calendar는 ZonedDateTime처럼, 날짜와 시간 그리고 시간대까지 모두 가지고 있다.<br>
	 * Date와 유사한 클래스로는 Instant가 있는데, 이 클래스는 날짜와 시간을 초 단위(정확히는 나노초)로 표현한다.<br>
	 * 날짜와 시간을 초단위로 표현한 값을 타임스탬프(time-stamp)라고 부르는데, 이 값은 날짜와 시간을 하나의 정수로 표현할 수 있으므로 날짜와 시간의 차이를 계산하거나 순서를 비교하는데 유리해서 데이터베이스에 많이 사용된다.
	 */
	class Memo1 {

	}

	/**
	 * <h5>Period와 Duration</h5><br>
	 * 날짜와 시간의 간격을 표현하기 위한 클래스로 있는데, Period는 두 날짜간의 차이를 표현하기 위한 것이고, Duration은 시간의 차이를 표현하기 위한 것이다.<br>
	 * <pre><code>
	 *     날짜 - 날짜 = Period
	 *     시간 - 시간 = Duration
	 * </code></pre>
	 */
	class Memo2 {

	}

	/**
	 * <h5>객체 생성하기 - now(), of()</h5><br>
	 * java.time패키지에 속한 클래스의 객체를 생성하는 가장 기본적인 방법은 now()와 of()를 사용하는 것이다.<br>
	 * now()는 현재 날짜와 시간을 저장하는 객체를 생성한다.<br>
	 * <pre><code>
	 *     LocalDate date = LocalDate.now(); // 2015-11-23
	 *     LocalTime time = LocalTime.now(); // 21:54:01.875
	 *     LocalDateTime dateTime = LocalDateTime.now(); // 2015-11-23T21:54:01.875
	 *     ZonedDateTime dateTimeInkr = ZonedDateTime.now(); // 2015-11-23T21:54:01.875+09:00[Asia/Seoul]
	 * </code></pre>
	 * <br>
	 * of()는 단순히 해당 필드의 값을 순서대로 지정해 주기만 하면 된다. 각 클래스마다 다양한 종류의 of()가 정의되어 있다.
	 */
	class Memo3 {

	}

	/**
	 * <h5>Temporal과 TemporalAmount</h5><br>
	 *
	 */
	class Memo4 {

	}

}
