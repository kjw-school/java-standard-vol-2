package chapter15;

/**
 * <h1>4. 문자기반 스트림</h1><br>
 */
public class Chapter15_4 {

	/**
	 * <h5>4.1 Reader와 Writer</h5><br>
	 * 바이트기반 스트림의 조상이 InputStream/OutputStream인 것과 같이 문자기반의 스트림에서는 Reader/Writer가 그와 같은 역할을 한다.<br>
	 * Reader의 메서드
	 * <table>
	 *     <thead>
	 *         <th>메서드</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>abstract void close()</td>
	 *             <td>입력스트림을 닫음으로써 사용하고 있던 자원을 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void mark(int readlimit)</td>
	 *             <td>현재위치를 표시해놓는다. 후에 reset()에 의해서 표시해 놓은 위치로 다시 돌아갈 수 있다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean markSupported()</td>
	 *             <td>mark()와 reset()을 지원하는지를 알려 준다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int read()</td>
	 *             <td>입력소스로부터 하나의 문자를 읽어 온다. char의 범위인 0~65535범위의 정수를 반환하며, 입력스트림의 마지막 데이터에 도달하면, -1을 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int read(char[] c)</td>
	 *             <td>입력소스로부터 매개변수로 주어진 배열 c의 크기만큼 읽어서 배열 c에 저장한다. 읽어 온 데이터의 개수 또는 -1을 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>abstract int read(char[] c, int off, int len)</td>
	 *             <td>입력소스로부터 최대 len개의 문자를 읽어서, 배열 c의 지정된 위치(off)부터 읽은 만큼 저장한다. 읽어 온 데이터의 개수 또는 -1을 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int read(CharBuffer target)</td>
	 *             <td>입력소스로부터 읽어서 문자버퍼(target)에 저장한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean ready()</td>
	 *             <td>입력소스로부터 데이터를 읽을 준비가 되어있는지 알려 준다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void reset()</td>
	 *             <td>입력소스에서의 위치를 마지막으로 mark()가 호출되었던 위치로 되돌린다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>long skip(long n)</td>
	 *             <td>현재 위치에서 주어진 문자 수(n)만큼을 건너뛴다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * Writer의 메서드<br>
	 * <table>
	 *     <thead>
	 *         <th>메서드</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>Writer append(char c)</td>
	 *             <td>지정된 문자를 출력소스에 출력한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Writer append(CharSequence c)</td>
	 *             <td>지정된 문자열(CharSequence)을 출력소스에 출력한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Writer append(CharSequence c, int start, int end)</td>
	 *             <td>지정된 문자열(CharSequence)의 일부를 출력소스에 출력(CharBuffer, String, StringBuffer가 CharSequence를 구현)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>abstract void close()</td>
	 *             <td>출력스트림을 닫음으로써 사용하고 있던 자원을 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>abstract void flush()</td>
	 *             <td>스트림의 버퍼에 있는 모든 내용을 출력소스에 쓴다.(버퍼가 있는 스트림에만 해당됨)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void write(int b)</td>
	 *             <td>주어진 값을 출력소스에 쓴다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void write(char[] c)</td>
	 *             <td>주어진 배열 c에 저장된 모든 내용을 출력소스에 쓴다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>abstract void write(char[] c, int off, int len)</td>
	 *             <td>주어진 배열 c에 저장된 내용 중에서 off번째부터 len길이만큼만 출력소스에 쓴다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void write(String str)</td>
	 *             <td>주어진 문자열(str)을 출력소스에 쓴다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void write(String str, int off, int len)</td>
	 *             <td>주어진 문자열(str)의 일부를 출력소스에 쓴다.(off번째 문자부터 len개 만큼의 문자열)</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * 한 가지 더 얘기하고 싶은 것음 문자기반 스트림이라는 것이 단순히 2 byte로 스트림을 처리하는 것만을 의미하지는 않는다는 것이다.<br>
	 * 문자 데이터를 다루는데 필요한 또 하나의 정보는 인코딩(encoding)이다.<br>
	 * 문자기반 스트림, 즉 Reader/Writer 그리고 그 자손들은 여러 종류의 인코딩과 자바에서 사용하는 유니코드(UTF-16)간의 변환을 자동적으로 처리해준다.<br>
	 * Reader는 특정 인코딩을 읽어서 유니코드로 변환하고 Writer는 유니코드를 특정 인코딩으로 변환하여 저장한다.
	 */
	class Memo1 {

	}

	/**
	 * <h5>4.2 FileReader와 FileWriter</h5><br>
	 * FileReader/FileWriter는 파일로부터 텍스트데이터를 읽오, 파일에 쓰는데 사용된다.
	 */
	class Memo2 {

	}

	/**
	 * <h5>4.3 PipedReader와 PipedWriter</h5><br>
	 * PipedReader/PipedWriter는 쓰레드 간에 데이터를 주고받을 떄 사용된다.<br>
	 * 다른 스트림과는 달리 입력과 출력스트림을 하나의 스트림으로 연결(connect)해서 데이터를 주고받는다는 특징이 있다.<br>
	 * 스트림을 생성한 다음에 어느 한쪽 쓰레드에서 connect()를 호출해서 입력스트림과 출력스트림을 연결한다.<br>
	 * 입출력을 마친 후에는 어느 한쪽 스트림만 닫아도 나머지 스트림은 자동으로 닫힌다.<br>
	 * 이 점을 제외하고는 일반 입출력방법과 다르지 않다.
	 */
	class Memo3 {

	}

	/**
	 * <h5>4.4 StringReader와 StringWriter</h5><br>
	 * StringReader/StringWriter는 CharArrayReader/CharArrayWriter와 같이 입출력 대상이 메모리인 스트림이다.<br>
	 * StringWriter에 출력되는 데이터는 내부의 StringBuffer에 저장되며 StringWriter의 다음과 같은 메서드를 이용해서 저장된 데이터를 얻을 수 있다.<br>
	 * <pre><code>
	 *     StringBuffer getBuffer() - StringWriter에 출력한 데이터가 저장된 StringBuffer를 반환한다.
	 *     String toString() - StringWriter에 출력된 (StringBuffer에 저장된) 문자열을 반환한다.
	 * </code></pre>
	 */
	class Memo4 {

	}

}
