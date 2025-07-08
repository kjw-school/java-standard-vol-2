package chapter10;

import static java.lang.System.*;

import java.util.Calendar;

/**
 * <h1>Chapter10. 날짜와 시간 & 형식화 date, time and formatting</h1>
 */
public class Chapter10 {

	/**
	 * <h4>1. 날짜와 시간</h5><br>
	 * <h5>1.1 Calendar와 Date</h5><br>
	 * Date는 날짜와 시간을 다룰 목적으로 JDK1.0부터 제공되어온 클래스이다.<br>
	 * Date클래스 역시 기능이 부족했기 때문에, 서둘러 Calendar라는 새로운 클래스를 그 다음 버젼인 JDK1.1부터 제공하기 시작했다.<br>
	 * Calendar는 Date보다는 훨씬 나았지만 몇 가지 단점들이 발견되었다.<br>
	 * JDK1.8부터 'java.time패키지'로 기존의 단점들을 개선한 새로운 클래스들이 추가되었다
	 */
	class Memo1 {

	}

	/**
	 * <h5>Calendar와 GregorianCalendar</h5><br>
	 * Calendar는 추상클래스이기 때문에 직접 객체를 생성할 수 없고, 메서드를 통해서 완전히 구현된 클래스의 인스턴스를 얻어야 한다.<br>
	 * <code>
	 *     Calendar cal = new Calendar(); // 에러!!! 추상클래스는 인스턴스를 생성할 수 없다.<br>
	 *
	 *     // OK, getInstance()메서드는 Calendar 클래스를 구현한 클래스의 인스턴스를 반환한다.<br>
	 *     Calendar cal = Calendar.getInstance();
	 * </code>
	 * <br>
	 * Calendar를 상속받아 완전히 구현한 클래스로는 GregorianCalendar와 BuddhistCalendar가 있는데 getInstance()는 시스템의 국가와 지역설정을 확인해서 태국인 경우에는 BuddhistCalendar의 인스턴스를 반환하고, 그 외에는 GregorianCalendar의 인스턴스를 반환한다.<br>
	 * GregorianCalendar는 Calendar를 상속받아 오늘날 전세계 공통으로 사용하고 있는 그레고리력에 맞게 구현한 것으로 태국을 제외한 나머지 국가에서는 GregorianCalendar를 사용하면 된다.<br>
	 * getInstance()메서드가 static인 이유는 메서드 내의 코드에서 인스턴스 변수를 사용하거나 인스턴스 메서드를 호출하지 않기 때문이며, 또 다른 이유는 getInstance()가 static이 아니라면 위와 같이 객체를 생성한 다음에 호출해야하는데 Calendar는 추상클래스이기 때문에 객체를 생성할 수 없기 때문이다
	 */
	class Memo2 {

	}

	/**
	 * <h5>Date와 Calendar간의 변환</h5><br>
	 * Calendar가 새로 추가되면서 Date는 대부분의 메서드가 'deprecated'되었으므로 잘 사용되지 않는다. 그럼에도 불구하고 여전히 Date를 필요로 하는 메서드들이 있기 때문에 Calendar를 Date로 또는 그 반대로 변환할 일이 생긴다.<br>
	 * <code>
	 *     1. Calendar를 Date로 변환<br>
	 *     Calendar cal = Calendar.getInstance();<br>
	 *     ...<br>
	 *     Date d = new Date(cal.getTimeInMillis()); // Date(long date)<br>
	 *	   <br>
	 *     2. Date를 Calendar로 변환<br>
	 *     Date d = new date();<br>
	 *     ...
	 *     Calendar cal = Calendar.getInstnace();<br>
	 *     cal.seTime(d)
	 * </code>
	 */
	class Memo3 {

	}

	static class CalendarEx1 {

		public static void main(String[] args) {
			// 기본적으로 현재날짜와 시간으로 설정된다.
			Calendar today = Calendar.getInstance();

			out.println("이 해의 년도 : " + today.get(Calendar.YEAR));
			out.println("월(0~11, 0:1월): " + today.get(Calendar.MONTH));
			out.println("이 해의 몇 째 주: " + today.get(Calendar.WEEK_OF_YEAR));
			out.println("이 달의 몇 째 주: " + today.get(Calendar.WEEK_OF_MONTH));

			// DATE와 DAY_OF_MONTH는 같다.
			out.println("이 달의 몇 일: " + today.get(Calendar.DATE));
			out.println("이 달의 몇 일: " + today.get(Calendar.DAY_OF_MONTH));
			out.println("이 해의 몇 일: " + today.get(Calendar.DAY_OF_YEAR));
			out.println("요일(1~7, 1:일요일): " + today.get(Calendar.DAY_OF_WEEK)); // 1:일요일, 2:월요일, ... 7: 토요일
			out.println("이 달의 몇 째 요일: " + today.get(Calendar.DAY_OF_WEEK_IN_MONTH));
			out.println("오전_오후(0:오전, 1:오후): " + today.get(Calendar.AM_PM));
			out.println("시간(0~11): " + today.get(Calendar.HOUR));
			out.println("시간(0~23): " + today.get(Calendar.HOUR_OF_DAY));
			out.println("분(0~59): " + today.get(Calendar.MINUTE));
			out.println("초(0~59): " + today.get(Calendar.SECOND));
			out.println("1000분의 1초(0~999): " + today.get(Calendar.MILLISECOND));
			// 천분의 1초를 시간으로 표시하기 위해 3600000으로 나누었다.(1시간 = 60 * 60초)
			out.println("TimeZone(-12~+12): " + (today.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000)));
			out.println("이 달의 마지막날: " + today.getActualMaximum(Calendar.DATE)); // 이 달의 마지막 일을 찾는다.

		}

	}

	/**
	 * getInstance()를 통해서 얻은 인스턴스는 기본적으로 현재 시스템의 날짜와 시간에 대한 정보를 담고 있다.<br>
	 * 원하는 날짜나 시간으로 설정하려면 set메서드를 사용하면 된다.<br>
	 * get메서드의 매개변수로 사용되는 int값들은 Calendar에 정의된 static상수이다.<br>
	 * 한 가지 주의해야할 것은 get(Calendar.MONTH)로 얻어오는 값의 범위가 1~12가 아닌 0~11이라는 것이다. 그래서 get(Calendar.MONTH)로 얻어오는 값이 0이면 1월을 의미하고, 11이면 12월을 의미한다.<br>
	 * <small>※ clear()는 모든 필드의 값을, clear(int field)는 지정된 필드의 값을 기본값으로 초기화 된다.</small><br>
	 * 두 날짜간의 차이를 구하기 위해서는 두 날짜를 최소단위인 초단위로 변경한 다음 그 차이를 구하면 된다.<br>
	 * getTimeInMillis()는 1/1000초 단위로 값을 반환하기 때문에 초단위로 얻기 위해서는 1000으로 나눠 주어야 하고, 일단위로 얻기 위해서는 '24(시간) * 60(분) * 60(초) * 1000'으로 나누어야 한다.<br>
	 * 시간상의 전후를 알고 싶을 때는 두 날짜간의 차이가 양수인지 음수인지를 판단하면 될 것이다.<br>
	 * 아니면, 간단히 'boolean after(Object when)' 와 'boolean before(Object when)'를 사용해도 좋다.<br>
	 * 가장 큰 단위인 시간 단위(3600초)로 나누고 남은 나머지를 다시 분 단위(60초)로 나누면 그 나머지는 초 단위의 값이 된다.<br>
	 * 'add(int field, int amount)'를 사용하면 지정한 필드의 값을 원하는 만큼 증가 또는 감소시킬 수 있기 때문에 add메서드를 이용하면 특정 날짜 또는 시간을 기점으로 해서 일정기간 전후의 날짜와 시간을 알아낼 수 있다.<br>
	 * 'roll(int field, int amount)'도 지정한 필드의 값을 증가 또는 감소시킬 수 있는데, add메서드와의 차이점은 다른 필드에 영향을 미치지 않는다는 것이다.<br>
	 * 예를 들어 add메서드로 날짜필드(Calendar.DATE)의 값을 31만큼 증가시켰다면 다음 달로 넘어가므로 월 필드(Calendar.MONTH)의 값도 1 증가하지만, roll메서드는 같은 경우에 월 필드의 값은 변하지 않고 일 필드의 값만 바뀐다.<br>
	 * 단, 한 가지 예외가 있는데 일 필드(Calendar.DATE)가 말일(end of month) 일 때, roll메서드를 이용해서 월 필드(Calendar.MONTH)를 변경하면 일 필드(Calendar.DATE)에 영향을 미칠 수 있다.<br>
	 * <pre><code>
	 *     boolean isLeapYear(int year)
	 *      : 매개변수 year가 윤년이면 true를 그렇지 않으면 false를 반환한다.
	 *     int dayDiff(int y1, int m1, int d1, int y2, int m2, int d2)
	 *      : 두 날짜간의 차이를 일단위로 반환한다.
	 *     int getDayOfWeek(int year, int month, int day)
	 *      : 지정한 날짜의 요일을 반환한다.(1~, 1이 일요일)
	 *     String convertDayToDate(int day)
	 *      : 일단위의 값을 년월일의 형태의 문자열로 변환하여 반환한다.
	 *     int convertDateToDay(int year, int month, int day)
	 *      : 년월일을 입력받아서 일단위로 변환한다.
	 * </code></pre>
	 * <br>
	 * <small>※일 단위로 변환할 때 서기 1년 1월 1일부터의 일(日)의 수를 계산하였지만, Calendar의 경우 1970년 1월 1일을 기준으로 계산한다. 그래서 1970년 1월 1일 이전의 날짜에 대해 getTimeInMillis()를 호출하면 음수를 결과로 얻는다.</small>
	 */
	class Memo4 {

	}

}
