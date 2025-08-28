package chapter15;

import static java.lang.System.*;

import java.io.IOException;

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
	 * 선언부분만을 봐서는 out, err, in의 탕비은 InputStream과 PrintStream이지만 실제로는 버퍼를 이용하는 BufferedInputStream과 BufferedOutputStream의 인스턴스를 사용한다.<br>
	 * 콘솔입력은 버퍼를 가지고 있기 때문에 Backspace키를 이용해서 편집이 가능하며 한 번에 버퍼의 크기만큼 입력이 가능하다.<br>
	 * 그래서 Enter키나 입력의 끝을 알리는 '^z'를 누르기 전까지는 아직 데이터가 입력 중인 것으로 간주되어 커서가 입력을 계속 기다리는 상태(블락킹 상태)에 머무르게 된다.<br>
	 * 콘솔에 데이터를 입력하고 Enter키를 누르면 입력대기상태에서 벗어나 입력된 데이터를 읽기 시작하고 입력된 데이터를 모두 읽으면 다시 입력대기 상태가 된다.<br>
	 * 이러한 과정이 반복되다가 사용자가 '^z'를 입력하면, read()는 입력이 종료되었음을 인식하고 -1을 반환하여 while문을 벗어나 프로그램이 종료된다.<br>
	 * Enter키를 누르는 것은 두 개의 특수문자 '\r'과 '\n'이 입력된 것으로 간주된다. '\r'은 캐리지리턴(carriage return),즉 커서를 현재 라인의 첫 번째 컬럼으로 이동시키고<br>
	 * '\n'은 커서를 다음 줄로 이동시키는 줄바꿈(new line)을 한다.<br>
	 * 그래서 Enter키를 누르면, 캐리지리턴과 줄바꿈이 수행되어 다음 줄의 첫 번째 칼럼으로 커서가 이동하는 것이다.<br>
	 * 여기서 한 가지 문제는 Enter키도 사용자입력으로 간주되어 매 입력마다 '\r'과 '\n'이 붙기 때문에 이 들을 제거해주어야 하는 불편함이 있다는 것이다.<br>
	 * 이러한 불편함을 제거하려면 전에 살펴본 것과 같이 System.in에 BufferedReader를 이용해서 readLine()을 통해 라인단위로 데이터를 입력받으면 된다.<br>
	 * 텍스트기반의 사용자인터페이스 시대에 탄생한 C언어는 콘솔이 데이터를 입력받는 주요 수단이었지만, 자바가 탄생한 그래픽기반의 사용자인터페이스 시대는 콘솔을 통해서 데이터를 입력받는 경우는 드물기 때문에 Java에서 콘솔을 통한 입력에 대한 지원이 미약했다.<br>
	 * 나중에 Scanner와 Consoel같은 클래스가 추가되면서 많이 보완되었다.
	 *
	 */
	class Memo1 {

	}

	public static class StandardIOEx1 {
		public static void main(String[] args) {

			try {
				int input = 0;

				while ((input = System.in.read()) != -1) {
					out.println("input :" + input + ", (char)input:" + (char)input);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
