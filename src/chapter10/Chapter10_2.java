package chapter10;

import static java.lang.System.*;

import java.text.ChoiceFormat;
import java.text.MessageFormat;

/**
 * <h1>2. 형식화 클래스</h1><br>
 * 이 클래스는 java.text패키지에 포함되어 있으며 숫자, 날짜, 텍스트 데이터를 일정한 형식에 맞게 표현할 수 있는 방법을 객체지향적으로 설계하여 표준화하였다.<br>
 * 형식화 클래스는 형식화에 사용될 패턴을 정의하는데, 데이터를 정의된 패턴에 맞춰 형식화할 수 있을 뿐만 아니라 역으로 형식화된 데이터에서 원래의 데이터를 얻어낼 수도 있다.
 */
public class Chapter10_2 {

	/**
	 * <h5>2.1 DecimalFormat</h5><br>
	 * 형식화 클래스 중에서 숫자를 형식화 하는데 사용되는 것이 DecimalFormat이다.<br>
	 * DecimalFormat을 이용하면 숫자 데이터를 정수, 부동소수점, 금액 등의 다양한 형식으로 표현할 수 있으며, 반대로 일정한 형식의 텍스트 데이터를 숫자로 쉽게 변환하는 것도 가능하다.
	 */
	class Memo1 {

	}

	/**
	 * <h5>2.2 SimpleDateFormat</h5><br>
	 * 앞에서 날짜를 계산할 때 Date와 Calendar를 사용해서 날짜를 계산하는 방법을 배웠는데, 이제는 출력하는 방법에 대해서 배울 차례이다.<br>
	 * Date와 Calendar만으로 날짜 데이터를 원하는 형태로 다양하게 출력하는 것은 불편하고 복잡하다. 그러나 SimpleDateFormat을 사용하면 이러한 문제들이 간단히 해결된다.<br>
	 * <small>※ DateFormat은 추상클래스로 SimpleDateFormat의 조상이다. DateFormat는 추상클래스이므로 인스턴스를 생성하기 위해서는 getDateInstance()와 같은 static메서드를 이용해야 한다. getDateInstance()에 의해서 반한되는 것은 DateFormat을 상속받아 완전하게 구현한 SimpleDateFormat인스턴스이다.</small><br>
	 * <table>
	 *     <thead>
	 *         <th>기호</th>
	 *         <th>의미</th>
	 *         <th>보기</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>G</td>
	 *             <td>연대(BC, AD)</td>
	 *             <td>AD</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Y</td>
	 *             <td>년도</td>
	 *             <td>2006</td>
	 *         </tr>
	 *         <tr>
	 *             <td>M</td>
	 *             <td>월(1~12 또는 1월~12월)</td>
	 *             <td>10 또는 10월, OCT</td>
	 *         </tr>
	 *         <tr>
	 *             <td>w</td>
	 *             <td>년의 몇 번째 주(1~53)</td>
	 *             <td>50</td>
	 *         </tr>
	 *         <tr>
	 *             <td>W</td>
	 *             <td>월의 몇 번째 주(1~5)</td>
	 *             <td>4</td>
	 *         </tr>
	 *         <tr>
	 *             <td>D</td>
	 *             <td>년의 몇 번째 일(1~366)</td>
	 *             <td>100</td>
	 *         </tr>
	 *         <tr>
	 *             <td>d</td>
	 *             <td>월의 몇 번째 일(1~31)</td>
	 *             <td>15</td>
	 *         </tr>
	 *         <tr>
	 *             <td>F</td>
	 *             <td>월의 몇 번째 요일(1~5)</td>
	 *             <td>1</td>
	 *         </tr>
	 *         <tr>
	 *             <td>E</td>
	 *             <td>요일</td>
	 *             <td>월</td>
	 *         </tr>
	 *         <tr>
	 *             <td>a</td>
	 *             <td>오전/오후(AM,PM)</td>
	 *             <td>PM</td>
	 *         </tr>
	 *         <tr>
	 *             <td>H</td>
	 *             <td>시간(0~23)</td>
	 *             <td>20</td>
	 *         </tr>
	 *         <tr>
	 *             <td>k</td>
	 *             <td>시간(1~24)</td>
	 *             <td>13</td>
	 *         </tr>
	 *         <tr>
	 *             <td>K</td>
	 *             <td>시간(0~11)</td>
	 *             <td>10</td>
	 *         </tr>
	 *         <tr>
	 *             <td>h</td>
	 *             <td>시간(1~12)</td>
	 *             <td>11</td>
	 *         </tr>
	 *         <tr>
	 *             <td>m</td>
	 *             <td>분(0~59)</td>
	 *             <td>35</td>
	 *         </tr>
	 *         <tr>
	 *             <td>s</td>
	 *             <td>초(0~59)</td>
	 *             <td>55</td>
	 *         </tr>
	 *         <tr>
	 *             <td>S</td>
	 *             <td>천분의 일초(0~999)</td>
	 *             <td>253</td>
	 *         </tr>
	 *         <tr>
	 *             <td>z</td>
	 *             <td>Time zone(General time zone)</td>
	 *             <td>GMT+9:00</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Z</td>
	 *             <td>Time zone(RFC 822 time zone)</td>
	 *             <td>+0900</td>
	 *         </tr>
	 *         <tr>
	 *             <td>'</td>
	 *             <td>escape문자(특수문자를 사용하는데 사용)</td>
	 *             <td>없음</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * SimpleDateFormat을 사용하는 방법은 간단하다. 먼저 원하는 출력형식의 패턴을 작성하여 SimpleDateFormat인스턴스를 생성한 다음, 출력하고자 하는 Date인스턴스를 가지고 format(Date d)를 호출하면 지정한 출력형식에 맞게 변환된 문자열을 얻게 된다.<br>
	 * <pre><code>
	 *     Date today = new Date();
	 *     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	 *
	 *     // 오늘 날짜를 yyyy-MM-dd형태로 변환하여 반환한다.
	 *     String result = df.format(today);
	 * </code></pre>
	 */
	class Memo2 {

	}

	/**
	 * <h5>2.3 ChoiceFormat</h5><br>
	 * ChoiceFormat은 특정 범위에 속하는 값을 문자열로 변환해준다.
	 */
	class Memo3 {

	}

	static class Ex01 {
		public static void main(String[] args) {
			double[] limits = {60, 70, 80, 90}; // 낮은 값부터 큰 값의 순서로 적어야한다.
			// limits, grades간의 순서와 개수를 맞추어야 한다.
			String[] grades = {"D", "C", "B", "A"};

			int[] scores = {100, 95, 88, 70, 52, 60, 70};

			ChoiceFormat form = new ChoiceFormat(limits, grades);

			for (int i = 0; i < scores.length; i++) {
				out.println(scores[i] + ":" + form.format(scores[i]));
			}
		} // main
	}

	/**
	 * <h5>2.4 MessageFormat</h5><br>
	 * MessageFormat은 데이터를 정해진 양식에 맞게 출력할 수 있도록 도와준다.<br>
	 * 데이터가 들어갈 자리를 마련해 놓은 양식을 미리 작성하고 프로그램을 이용해서 다수의 데이터를 같은 양식으로 출력할 때 사용하면 좋다.
	 */
	class Memo4 {

	}

	static class Ex02 {
		public static void main(String[] args) {
			String msg = "Name: {0} \nTel: {1} \nAge:{2} \nBirthday:{3}";

			Object[] arguments = {
				"이자바", "02-123-1234", "27", "07-09"
			};

			String result = MessageFormat.format(msg, arguments);
			out.println(result);
		}
	}

}
