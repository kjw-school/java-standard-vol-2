package chapter15;

/**
 * <h1>6. 표준입출력과 File</h1>
 */
public class Chapter15_6 {

	/**
	 * <h5>6.1 표준입출력 - System.in, System.out, System.err</h5><br>
	 * 표준입출력은 콘솔(console, 도스창)을 통한 데이터 입력과 콘솔로의 데이터 출력을 의미한다.<br>
	 * 자바에서는 표준 입출력(standard I/O)을 위해 3가지 입출력 스트림, System.in, System.out, System.err을 제공하는데, 이 들은 자바 어플리케이션의 실행과 동시에 사용할 수 있게 자동적으로 생성되기 때문에 개발자가 별도로 스트림을 생성하는 코드를 작성하지 않고도 사용이 가능하다.<br>
	 * 자바를 처음 시작할 때부터 지금까지 줄 곧 사용해온 System.out을 스트림의 생성없이 사용할 수 있었던 것이 바로 이러한 이유 때문이다.<br>
	 * <pre><code>
	 *     System.in - 콘솔로부터 데이터를 입력받는데 사용
	 *     System.out - 콘솔로 데이터를 출력하는데 사용
	 *     System.err - 콘솔로 데이터를 출력하는데 사용
	 * </code></pre>
	 * <br>
	 * System클래스이 소스에서 알 수 있듯이 in, out, err은 System클래스에 선언된 클래스변수(static변수)이다.<br>
	 * 선언부분만을 봐서는 out, err, in의 탕비은 InputStream과 PrintStream이지만 실제로는 버퍼를 이용하는 BufferedInputStream과 BufferedOutputStream의 인스턴스를 사용한다.
	 */
	class Memo1 {

	}

}
