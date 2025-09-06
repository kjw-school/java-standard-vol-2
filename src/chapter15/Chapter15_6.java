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

	/**
	 * <h5>6.2 표준입출력의 대상변경 - setOut(), setErr(), setIn()</h5><br>
	 * 초기에는 System.in, System.out, System.er의 입출력대상이 콘솔화면이지만, setIn(), setOut(), setErr()를 사용하면 입출력을 콘솔 이외에 다른 입출력대상으로 변경하는 것이 가능하다.<br>
	 * <table>
	 *     <thead>
	 *         <th>메서드</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>static void setOut(PrintStream out)</td>
	 *             <td>System.out의 출력을 지정된 PrintStream으로 변경</td>
	 *         </tr>
	 *         <tr>
	 *             <td>static void setErr(PrintStream err)</td>
	 *             <td>System.err의 출력을 지정한 PrintStream으로 변경</td>
	 *         </tr>
	 *         <tr>
	 *             <td>static void setIn(InputStream in)</td>
	 *             <td>System.in의 입력을 지정한 InputStream으로 변경</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * 그러나 JDK1.5부터 Scanner클래스가 제공되면서 System.in으로부터 데이터를 입력받아 작업하는 것이 편리해졌다.<br>
	 * System.out, System.err 모두 출력대상이 콘솔이기 때문에 System.out대신 System.err을 사용해도 같은 결과를 얻는다.
	 */
	class Memo2 {

	}

	/**
	 * <h5>6.3 RandomAccessFile</h5><br>
	 * 자바에서는 입력과 출력이 각각 분리되어 별도로 작업을 하도록 설계되어 있는데, RandomAccessFile만은 하나의 클래스로 파일에 대한 입력과 출력을 모두 할 수 있도록 되어있다.<br>
	 * InputStream이나 OutputStream으로부터 상속받지 않고, DataInput인터페이스와 DataOutput인터페이스를 구현했기 때문에 읽기와 쓰기가 모두 가능하다.<br>
	 * 사실 DataInputStream은 DataInput인터페이스를, DataOutputStream은 DataOutput인터페이스를 구현했다. 이 두 클래스의 기본 자료형(primitive data type)을 읽고 쓰기위한 메서드들은 모두 이 2개의 인터페이스에 정의되어있는 것들이다.<br>
	 * 따라서, RandomAccessFile클래스도 DataInputStream과 DataOutputStream처럼, 기본자료형 단위로 데이터를 읽고 쓸 수 있다.<br>
	 * 그래도 역시 RandomAccessFile클래스의 가장 큰 장점은 파일의 어느 위치에나 읽기/쓰기가 가능하다는 것이다. 다른 입출력 클래스들은 입출력소스에 순차적으로 읽기/쓰기를 하기 때문에 읽기와 쓰기가 제한적인데 반해서 RandomAccessFile클래스는 파일에 읽고 쓰는 위치에 제한이 없다.<br>
	 * 이것을 가능하게 하기 위해서 내부적으로 파일 포인터를 사용하는데, 입출력 시에 작업이 수행되는 곳이 바로 파일 포인터가 위치한 곳이 된다.<br>
	 * 파일 포인터의 위치는 파일의 제일 첫 부분(0부터 시작)이며, 읽기 또는 쓰기를 수행할 때 마다 작업이 수행된 다음 위치로 이동하게 된다.<br>
	 * 순차적으로 읽기나 쓰기를 한다면, 파일 포인터를 이동시키기 위해 별도의 작업이 필요하지 않지만, 파일의 임의의 위치에 있는 내용에 대해서 작업하고자 한다면, 먼저 파일 포인터를 원하는 위치로 옮긴 다음 작업을 해야 한다.<br>
	 * 현재 작업 중인 파일에서 파일 포인터의 위치를 알고 싶을 때는 getFilePointer()를 사용하면 되고, 파일 포인터의 위치를 옮기기 위해서는 seek(long pos)나 skipBytes(int n)를 사용하면 된다.<br>
	 * <small>※사실 모든 입출력에 사용되는 클래스들은 입출력 시 다음 작업이 이루어질 위치를 저장하고 있는 포인터를 내부적으로 갖고 있다. 다만 내부적으로만 사용될 수 있기 때문에 작업자가 포인터의 위치를 마음대로 변경할 수 없다는 것이 RandomAccessFile과 다른 점이다.</small>
	 */
	class Memo3 {

	}

	/**
	 * <h5>6.4 File</h5><br>
	 * 자바에서는 File클래스를 통해서 파일과 디렉토리를 다룰 수 있도록 하고 있다. 그래서 File인스턴스는 파일 일 수도 있고 디렉토리일 수도 있다.<br>
	 * File의 생성자와 경로와 관련된 메서드<br>
	 * <table>
	 *     <thead>
	 *         <th>생성자 / 메서드</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>File(String fileName)</td>
	 *             <td>
	 *                 주어진 문자열(fileName)을 이름으로 갖는 파일을 위한 File인스턴스를 생성한다. 파일 뿐만 아니라 디렉토리도 같은 방법으로 다룬다.<br>
	 *                 여기서 fileName은 주로 경로(path)를 포함해서 지정해주지만, 파일 이름만 사용해도 되는 데 이 경우 프로그램이 실행되는 위치가 경로(path)로 간주된다.
	 *             </td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 File(String pathName, String fileName)<br>
	 *                 File(File pathName, String fileName)
	 *             </td>
	 *             <td>
	 *                 파일의 경로와 이름을 따로 분리해서 지정할 수 있도록 한 생성자, 이 중 두 번째 것은 경로를 문자열이 아닌 File인스턴스인 경우를 위해서 제공된 것이다.
	 *             </td>
	 *         </tr>
	 *         <tr>
	 *             <td>File(URI uri)</td>
	 *             <td>지정된 uri로 파일을 생성</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String getName()</td>
	 *             <td>파일이름을 String으로 반환</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String getPath()</td>
	 *             <td>파일의 경로(path)를 String으로 변환</td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 String getAbsolutePath()<br>
	 *                 File getAbsoluteFile()
	 *             </td>
	 *             <td>
	 *                 파일의 정대 경로를 String으로 반환<br>
	 *                 파일의 절대경로를 File로 반환
	 *             </td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 String getParent()<br>
	 *                 File getParentFile()
	 *             </td>
	 *             <td>
	 *                 파일의 조상 디렉토리를 String으로 반환<br>
	 *                 파일의 조상 디렉토리를 File로 반환
	 *             </td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 String getCanonicalPath()<br>
	 *                 File getCanonicalFile()
	 *             </td>
	 *             <td>
	 *                 파일의 정규경로를 String으로 반환<br>
	 *                 파일의 정규경로를 File로 반환
	 *             </td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * 경로와 관련된 File의 멤버변수<br>
	 * <table>
	 *     <thead>
	 *         <th>멤버변수</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>static String pathSeparator</td>
	 *             <td>OS에서 사용하는 경로(path) 구분자, 윈도우 ";", 유닉스";"</td>
	 *         </tr>
	 *         <tr>
	 *             <td>static char pathSeparatorChar</td>
	 *             <td>OS에서 사용하는 경로(path) 구분자, 윈도우에서는 ';', 유닉스':'</td>
	 *         </tr>
	 *         <tr>
	 *             <td>static String separator</td>
	 *             <td>OS에서 사용하는 이름 구분자, 윈도우 "\", 유닉스 "/"</td>
	 *         </tr>
	 *         <tr>
	 *             <td>static char separatorChar</td>
	 *             <td>OS에서 사용하는 이름 구분자, 윈도우 "\", 유닉스 "/"</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * 절대경로(absolute path)는 파일시스템의 루트(root)로부터 시작하는 파일의 전체 경로를 의미한다.<br>
	 * OS에 따라 다르지만, 하나의 파일에 대해 둘 이상의 절대경로가 존재할 수 있다.<br>
	 * 현재 디렉토리를 의미하는 '.'와 같은 기호나 링크를 포함하고 있는 경우가 이에 해당한다.<br>
	 * 그러나 정규경로(canonical path)는 기호나 링크 등을 포함하지 않는 유일한 경로를 의미한다.<br>
	 * 시스템속성 중에서 user.dir의 값을 확인하면 현재 프로그램이 실행 중인 디렉토리를 알 수 있다.<br>
	 * 그리고 우리가 OS의 시스템변수로 설정하는 classpath외에 sun.boot.class.path라는 시스템속성에 기본적인 classpath가 있어서 기본적인 경로들은 이미 설정되어 있다.<br>
	 * 그래서 처음에 JDK설치 후 classpath를 따로 지정해주지 않아도 되는 것이다.<br>
	 * 이 속성은 JDK1.2이후부터 추가된 것이라 그 이전의 버전에서는 rt.jar와 같은 파일을 classpath에 지정해주어야 했다.<br>
	 * File인스턴스를 생성했다고 해서 파일이나 디렉토리가 생성되는 것은 아니라는 것이다.<br>
	 * 파일명이나 디렉토리명으로 지정된 문자열이 유효하지 않더라도 컴파일 에러나 예외를 발생시키지 않는다.<br>
	 * 새로운 파일을 생성하기 위해서는 File인스턴스를 생성한 다음, 출력스트림을 생성하거나 createNewFile()을 호출해야한다.<br>
	 * <pre><code>
	 *     1. 이미 존재하는 파일을 참조할 때 :
	 *     File file = new File("c:\\jdk1.8\\work\\ch15", "FileEx1.java");
	 *     2. 기존에 없는 파일을 새로 생성할 때 :
	 *     File f = new File("c:\\jdk1.8\\work\\ch15", "NewFile.java");
	 *     f.createNewFile(); // 새로운 파일이 생성된다.
	 * </code></pre>
	 */
	class Memo4 {

	}

}
