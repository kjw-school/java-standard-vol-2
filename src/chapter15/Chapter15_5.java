package chapter15;

/**
 * <h1>5. 문자기반의 보조스트림</h1>
 */
public class Chapter15_5 {

	/**
	 * <h5>5.1 BufferedReader와 BufferedWriter</h5><br>
	 * BufferedReader/BufferedWriter는 버퍼를 이용해서 입출력의 효율을 높일 수 있도록 해주는 역할을 한다.<br>
	 * 버퍼를 이용하면 입출력의 효율이 비교할 수 없을 정도로 좋아지기 때문에 사용하는 것이 좋다.<br>
	 * BufferedReader의 readLine()을 사용하면 데이터를 라인단위로 읽을 수 있고 BufferedWriter는 newLine()이라는 줄바꿈 해주는 메서드를 가지고 있다.<br>
	 */
	class Memo1 {

	}

	/**
	 * <h5>5.2 InputStreamReader와 OutputStreamWriter</h5><br>
	 * InputStreamReader/OutputStreamWriter는 이름에서 알 수 있는 것과 같이 바이트기반 스트림을 문자기반 스트림으로 연결시켜주는 역할을 한다.<br>
	 * 그리고 바이트기반 스트림의 데이터를 지정된 인코딩의 문자데이터로 변환하는 작업을 수행한다.<br>
	 * <small>※InputStreamReader/OutputStreamWriter는 Reader/Writer의 자손이다.</small><br>
	 * InputStreamReader의 생성자와 메서드
	 * <table>
	 *     <thead>
	 *         <th>생성자 / 메서드</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>InputStreamReader(InputStream in)</td>
	 *             <td>OS에서 사용하는 기본 인코딩의 문자로 변환하는 InputStreamReader를 생성한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>InputStreamReader(InputStream in, String encoding)</td>
	 *             <td>지정된 인코딩을 사용하는 InputStreamReader를 생성한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String getEncoding()</td>
	 *             <td>InputStreamReader의 인코딩을 알려 준다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * OutputStreamWriter의 생성자와 메서드
	 * <br>
	 * <table>
	 *     <thead>
	 *         <th>생성자 / 메서드</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>OutputStreamWriter(OutputStream out)</td>
	 *             <td>OS에서 사용하는 기본 인코딩의 문자로 변환하는 OutputStreamWriter를 생성한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>OutputStreamWriter(OutputStream out, String encoding)</td>
	 *             <td>지정된 인코딩을 사용하는 OutputStreamWriter를 생성한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String getEncoding()</td>
	 *             <td>OutputStreamWriter의 인코딩을 알려 준다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * BufferedReader의 readLine()을 이용해서 사용자의 화면입력을 라인단위로 입력받으면 편리하다.<br>
	 * 그래서 BufferedReader와 InputStream인 System.in을 연결하기 위해 InputStreamReader를 사용하였다.<br>
	 * JDK1.5부터는 Scanner가 추가되어 이와 같은 방식을 사용하지 않아도 간단하게 처리가 가능하다.<br>
	 * 그리고, 현재 사용 중인 OS의 인코딩을 확인하려면 생성자 InputStreamReader(InputStream in)를 사용해서 InputStreamReader의 인스턴스를 생성한 다음, getEncoding()을 호출하면 된다.<br>
	 * 한글 윈도우즈에서 사용하는 인코딩의 종류는 MS949이다.
	 */
	class Memo2 {

	}

}
