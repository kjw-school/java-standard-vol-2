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
	 * Date클래스 역시 기능이 부족햇기 때문에, 서둘러 Calendar라는 새로운 클래스를 그 다음 버젼인 JDK1.1부터 제공하기 시작했다.<br>
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
	 * 한 가지 주의해야할 것은 get(Calendar.MONTH)로 얻어오는 값의 범위가 1~12가 아닌 0~11이라는 것이다. 그래서 get(Calendar.MONTH)로 얻어오는 값이 0이면 1월을 의미하고, 11이면 12월을 의미한다.
	 */
	class Memo4 {

	}

}
