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
	 * LocalDate, LocalTime, LocalDateTime, ZonedDateTime등 날짜와 시간을 표현하기 위한 클래스들은 모두 Temporal, TemporalAccessor, TemporalAdjuster인터페이스를 구현했고,<br>
	 * Duration과 Period는 TemporalAmount인터페이스를 구현하였다.<br>
	 * 매개변수의 타입이 Temporal로 시작하는 것들은 대부분 날짜와 시간을 위한 것이므로, TemporalAmount인지 아닌지만 확인하면 된다.<br>
	 * <small>※ 'temporal'과 'chrono'의 의미가 모두 시간(time)인데도, 'time'대신 굳이 이런 어려운 용어를 쓴 이유는 시간(시분초)과 더 큰 개념의 시간(년월일시분초)을 구분하기 위해서이다.</small><br>
	 * <pre><code>
	 *     Temporal, TemporalAccessor, TemporalAdjuster를 구현한 클래스
	 *     		- LocalDate, LocalTime, LocalDateTime, ZonedDateTime, Instant 등
	 *     TemporalAmount를 구현한 클래스
	 *      	- Period, Duration
	 * </code></pre>
	 */
	class Memo4 {

	}

	/**
	 * <h5>TemporalUnit과 TemporalField</h5><br>
	 * 날짜와 시간의 단위를 정의해 놓은 것이 TemporalUnit인터페이스이고, 이 인터페이스를 구현한 것이 열거형 ChronoUnit이다.<br>
	 * TemporalField는 년, 월, 일 등 날짜와 시간의 필드를 정의해 놓은 것으로, 열거형 ChronoField가 이 인터페이스를 구현하였다.
	 */
	class Memo5 {

	}

	/**
	 * <h5>3.2 LocalDate와 LocalTime</h5><br>
	 * LocalDate와 LocalTime은 java.time패키지의 가장 기본이 되는 클래스이며, 나머지 클래스들은 이들의 확장이므로 이 두 클래스만 잘 이해하고 나면 나머지는 아주 쉬워진다.<br>
	 * 객체를 생성하는 방법은 현재의 날짜와 시간을 LocalDate와 LocalTime으로 각각 반환하는 now()와 지정된 날짜와 시간으로 LocalDate와 LocalTime객체를 생성하는 of()가 있다. 둘 다 static메서드이다.
	 */
	class Memo6 {

	}

	/**
	 * <h5>필드의 값 변경하기 - with(), plus(), minus()</h5><br>
	 * 날짜와 시간에서 특정 필드 값을 변경하려면, with로 시작하는 메서드를 사용하면 된다.<br>
	 * with()를 사용하면, 원하는 필드를 직접 지정할 수 있다.<br>
	 * 필드를 변경하는 메서드들은 항상 새로운 객체를 생성해서 반환하므로 아래와 같이 대입 연산자를 같이 사용해야한다는 것을 잊으면 안 된다.<br>
	 * 특정 필드에 값을 더하거나 빼는 plush()와 minus()가 있다.<br>
	 * LocalTime의 truncatedTo()는 지정된 것보다 작은 단위의 필드를 0으로 만든다.
	 */
	class Memo7 {

	}

	/**
	 * <h5>날짜와 시간의 비교 - isAfter(), isBefore(), isEqual()</h5><br>
	 * LocalDate와 LocalTime도 compareTo()가 적절히 오버라이딩되어 있어서, compareTo()로 비교할 수 있다.<br>
	 * equals()가 있는데도, isEqual()을 제공하는 이유는 연표(chronology)가 다른 두 날짜를 비교하기 위해서이다.<br>
	 * 모든 필드가 일치해야하는 equals()와 달리 isEqual()은 오직 날짜만 비교한다.
	 */
	class Memo8 {

	}

	/**
	 * <h5>3.3 Instant</h5><br>
	 * Instant는 에포크 타임(EPOCH TIME, 1970-01-01 00:00:00 UTC)부터 경과된 시간을 나노초 단위로 표현한다.<br>
	 * 사람에겐 불편하지만, 단일 진법으로만 다루기 때문에 계산하기 쉽다.<br>
	 * Instant를 생성할 때는 now()와 ofEpochSecond()를 사용한다.<br>
	 * Instant는 시간을 초 단위와 나노초 단위로 나누어 저장한다. 오라클 데이터베이스의 타임스탬프(timestamp)처럼 밀리초 단위의 EPOCH TIME을 필요로 하는 경우를 위해 toEpochMilli()가 정의되어 있다.<br>
	 * Instant는 항상 UTC(+00:00)를 기준으로 하기 때문에, LocalTime과 차이가 있을 수 있다.<br>
	 * UTC는 'Coordinated Universal Time'의 약어로 '세계 협정시'이라고 하며, 1972년 1월 1일부터 시행된 국제 표준시이다. 이전에 사용되던 GMT(Greenwich Mean Time)와 UTC는 거의 같지만, UTC가 좀 더 정확하다.<br>
	 * <small>※ CUT가 아니라 UTC가 된 이유는 영어와 프랑스어 표기의 중간 형태를 선택했기 때문이다.</small>
	 */
	class Memo9 {

	}

	/**
	 * <h5>Instant와 Date간의 변환</h5><br>
	 * Instant는 기존의 java.util.Date를 대체하기 위한 것이며, JDK1.8부터 Date에 Instant로 변환할 수 있는 새로운 메서드가 추가되었다.
	 */
	class Memo10 {

	}

	/**
	 * <h5>3.4 LocalDateTime과 ZonedDateTime</h5><br>
	 * LocalDate와 LocalTime을 합쳐 놓은 것이 LocalDateTime이고, LocalDateTime에 시간대(time zone)을 추가한 것이 ZonedDateTime이다.<br>
	 * <pre><code>
	 *     LocalDate + LocalTime -> LocalDateTime
	 *     LocalDateTime + 시간대 -> ZonedDateTime
	 * </code></pre>
	 */
	class Memo11 {

	}

	/**
	 * <h5>LocalDate와 LocalTime으로 LocalDateTime만들기</h5><br>
	 * LocalDate와 LocalTime으로 합쳐서 하나의 LocalDateTime을 만들 수 있다.<br>
	 * <pre><code>
	 *     LocalDate date = LocalDate.of(2015, 12, 31);
	 *     LocalTime time = LocalTime.of(12,34,56);
	 *
	 *     LocalDateTime dt = LocalDateTime.of(date, time);
	 *     LocalDateTime dt2 = date.atTime(time);
	 *     LocalDateTime dt3 = time.atDate(date);
	 *     LocalDateTime dt4 = date.atTime(12, 34, 56);
	 *     LocalDateTime dt5 = time.atDate(LocalDate.of(2015, 12, 31));
	 *     LocalDateTime dt6 = date.atStartOfDay(); // dt6 = date.atTime(0, 0, 0);
	 * </code></pre>
	 */
	class Memo12 {

	}

	/**
	 * <h5>LocalDateTime의 변환</h5><br>
	 * <pre><code>
	 *     LocalDateTime dt = LocalDateTime.of(2015, 12, 31, 12, 34, 56);
	 *     LocalDate date = dt.toLocalDate(); // LocalDateTime -> LocalDate
	 *     LocalTime time = dt.toLocalTime(); // LocalDateTime -> LocalTime
	 * </code></pre>
	 */
	class Memo13 {

	}

	/**
	 * <h5>LocalDateTime으로 ZonedDateTime만들기</h5><br>
	 * LocalDateTime에 시간대(time-zone)를 추가하면, ZonedDateTime이 된다.<br>
	 * 새로운 시간 패키지에서는 ZonedId라는 클래스를 사용한다.<br>
	 * ZonedId는 일광 절약시간(DST, Daylight Saving Time)을 자동적으로 처리해주므로 더 편리하다.<br>
	 * LocalDateTime에 atZone()으로 시간대 정보를 추가하면, ZonedDateTime을 얻을 수 있다.<br>
	 * <small>※사용가능한 ZonedId의 목록은 ZonedId.getAvailableZonedIds()로 얻을 수 있다.</small>
	 */
	class Memo14 {

	}

	/**
	 * <h5>ZoneOffset</h5><br>
	 * UTC로부터 얼마만큼 떨어져 있는지를 ZonedOffSet으로 표현한다.
	 */
	class Memo15 {

	}

	/**
	 * <h5>OffsetDateTime</h5><br>
	 * ZonedDateTime은 ZoneId로 구역을 표현하는데, ZonedId가 아닌 ZoneOffset을 사용하는 것이 OffsetDateTime이다.<br>
	 * ZoneId는 일광절약시간처럼 시간대와 관련된 규칙들을 포함하고 있는데, ZoneOffset은 단지 시간대를 시간의 차이로만 구분한다.<br>
	 * 컴퓨터에게 일광절약시간처럼 계절별로 시간을 더했다 뺐다하는 것과 같은 행위는 위험하기 그지없다.<br>
	 * 아무런 변화 없이 일관된 시간체계를 유지하는 것이 더 안전하다.<br>
	 * 같은 지역 내의 컴퓨터 간에 데이터를 주고받을 때, 전송시간을 표현하기에 LocalDateTime이면, 충분하겠지만, 서로 다른 시간대에 존재하는 컴퓨터간의 통신에는 OffsetDateTime이 필요하다.
	 */
	class Memo16 {

	}

	/**
	 * <h5>3.5 TemporalAdjusters</h5><br>
	 * 자주 쓰일만한 날짜 계산들을 대신 해주는 메서드를 정의해놓은 것이 TemporalAdjusters클래스이다.<br>
	 * TemporalAdjusters의 메서드<br>
	 * <table>
	 *     <thead>
	 *         <th>메서드</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>firstDayOfNextYear()</td>
	 *             <td>다음 해의 첫날</td>
	 *         </tr>
	 *         <tr>
	 *             <td>firstDayOfNextMonth()</td>
	 *             <td>다음 달의 첫 날</td>
	 *         </tr>
	 *         <tr>
	 *             <td>firstDayOfYear()</td>
	 *             <td>올 해의 첫 날</td>
	 *         </tr>
	 *         <tr>
	 *             <td>firstDayOfMonth()</td>
	 *             <td>이번 달의 첫 날</td>
	 *         </tr>
	 *         <tr>
	 *             <td>lastDayOfYear()</td>
	 *             <td>올 해의 마지막 날</td>
	 *         </tr>
	 *         <tr>
	 *             <td>lastDayOfMonth()</td>
	 *             <td>이번 달의 마지막 날</td>
	 *         </tr>
	 *         <tr>
	 *             <td>firstInMonth (DayOfWeek dayOfWeek)</td>
	 *             <td>이번 달의 첫 번째 ?요일</td>
	 *         </tr>
	 *         <tr>
	 *             <td>lastInMonth (DayOfWeek dayOfWeek)</td>
	 *             <td>이번 달의 마지막 ?요일</td>
	 *         </tr>
	 *         <tr>
	 *             <td>previous (DayOfWeek dayOfWeek)</td>
	 *             <td>지난 ?요일(당일 미포함)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>previousOrSame (DayOfWeek dayOfWeek)</td>
	 *             <td>지난 ?요일(당일 포함)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>next (DayOfWeek dayOfWeek)</td>
	 *             <td>다음 ?요일(당일 미포함)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>nextOrSame (DayOfWeek dayOfWeek)</td>
	 *             <td>다음 ?요일(당일 포함)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>dayOfWeekInMonth(int ordinal, DayOfWeek dayOfWeek)</td>
	 *             <td>이번 달의 n번째 ?요일</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 */
	class Memo17 {

	}

	/**
	 * <h5>3.6 Period와 Duration</h5><br>
	 * Period는 날짜의 차이를, Duration은 시간의 차이를 계산하기 위한 것이다.<br>
	 * <pre><code>
	 *     날짜 - 날짜 = Period
	 *     시간 - 시간 = Duration
	 * </code></pre>
	 */
	class Memo18 {

	}

	/**
	 * <h5>between()</h5><br>
	 * 예를 들어 두 날짜 date1과 date2의 차이를 나타내는 Period는 between()으로 얻을 수 있다.<br>
	 * <pre><code>
	 *     LocalDate date1 = LocalDate.of(2014, 1, 1);
	 *     LocalDate date2 = LocalDate.of(2015, 12, 31);
	 *
	 *     Period pe = Period.between(date1, date2);
	 * </code></pre>
	 * <br>
	 * date1이 date2보다 날짜 상으로 이전이면 양수로, 이후면 음수로 Period에 저장된다.<br>
	 * 그리고 시간차이를 구할 때는 Duration을 사용한다는 것을 제외하고는 Period와 똑같다.<br>
	 * <pre><code>
	 *     LocalTime time1 = LocalTime.of(00, 00, 00);
	 *     LocalTime time2 = LocalTime.of(12, 34, 56); // 12시 34분 56초
	 *
	 *     Duration du = Duration.between(time1, time2);
	 * </code></pre>
	 */
	class Memo19 {

	}

	/**
	 * <h5>between()과 until()</h5><br>
	 * until()은 between()과 거의 같은 일을 한다. between()은 static메서드이고, until()은 인스턴스 메서드라는 차이가 있다.
	 */
	class Memo20 {

	}

	/**
	 * <h5>사칙연산, 비교연산, 기타 메서드</h5><br>
	 * plus(), minus()외에 곱셈과 나눗셈을 위한 메서드도 있다.<br>
	 * Period에 나눗셈을 위한 메서드가 없는데, Period는 날짜의 기간을 표현하기 위한 것이므로 나눗셈을 위한 메서드가 별로 유용하지 않기 때문에 넣지 않는 것이다.
	 */
	class Memo21 {

	}

	/**
	 * <h5>3.7 파싱과 포멧</h5><br>
	 * 날짜와 시간을 원하는 형식으로 출력하고 해석(파싱, parsing)하는 방법<br>
	 * 형식화(formatting)와 관련된 클래스들은 java.time.format패키지에 들어있는데, 이 중에서 DateTimeFormatter가 핵심이다.<br>
	 * 이 클래스에는 자주 쓰이는 다양성 형식들을 기본적으로 정의하고 있으며, 그 외의 형식이 필요하다면 직접 정의해서 사용할 수도 있다.
	 */
	class Memo22 {

	}

	/**
	 * <h5>로케일에 종속된 형식화</h5><br>
	 * DateTimeFormatter의 static메서드 ofLocalizedDate(), ofLocalizedTime(), ofLocalizedDateTime()은 로케일(locale)에 종속적인 포맷터를 생성한다.
	 */
	class Memo23 {

	}

	/**
	 * <h5>출력형식 직접 정의하기</h5><br>
	 * DateTimeFormatter의 ofPattern()으로 원하는 출력형식을 직접 작성할 수도 있다.<br>
	 * <pre><code>
	 *     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	 * </code></pre>
	 */
	class Memo24 {

	}

	/**
	 * <h5>문자열을 날짜와 시간으로 파싱하기</h5><br>
	 * 문자열을 날짜 또는 시간으로 변환하려면 static메서드 parse()를 사용하면 된다.<br>
	 * 날짜와 시간을 표현하는데 사용되는 클래스에는 이 메서드가 거의 다 포함되어 있다.<br>
	 * parse()는 오버로딩된 메서드가 여러 개 있는데, 그중에서 다음의 2개가 자주 쓰인다.<br>
	 * <pre><code>
	 *     static LocalDateTime parse(CharSequence text)
	 *     static LocalDateTime parse(CharSequence text, DateTimeFormatter formatter)
	 * </code></pre>
	 * <br>
	 * DateTimeFormatter에 상수로 정의된 형식을 사용할 때는 다음과 같이 한다.<br>
	 * <pre><code>
	 *     LocalDate date = LocalDate.parse("2016-01-02", DateTimeFormatter.ISO_LOCAL_DATE);
	 * </code></pre>
	 */
	class Memo25 {

	}

}