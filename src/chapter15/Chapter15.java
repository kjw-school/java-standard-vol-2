package chapter15;

/**
 * <h1>1. 자바에서의 입출력</h1>
 */
public class Chapter15 {

	/**
	 * <h5>1.1 입출력이란?</h5><br>
	 * I/O란 Input과 Output의 약자로 입력과 출력, 간단히 줄여서 입출력이라고 한다. 입출력은 컴퓨터 내부 또는 외부의 장치와 프로그램간의 데이터를 주고받는 것을 말한다.
	 */
	class Memo1 {

	}

	/**
	 * <h5>1.2 스트림(stream)</h5><br>
	 * 자바에서 입출력을 수행하려면, 즉 어느 한쪽에서 다른 쪽으로 데이터를 전달하려면, 두 대상을 연결하고 데이터를 전송할 수 있는 무언가가 필요한데 이것을 스트림(stream)이라고 정의했다.<br>
	 * <b>스트림이란 데이터를 운반하는데 사용되는 연결통로이다.</b><br>
	 * 스트림은 연속적인 데이터의 흐름을 물에 비유해서 붙여진 이름인데, 여러 가지로 유사한 점이 많다. 물이 한쪽 방향으로만 흐르는 것과 같이 스트림은 단방향통신만 가능하기 때문에 하나의 스트림으로 입력과 출력을 동시에 처리할 수 없다.<br>
	 * 그래서 입력과 출력을 동시에 수행하려면 입력을 위한 입력스트림(input stream)과 출력을 위한 출력스트림(output stream), 모두 2개의 스트림이 필요하다.<br>
	 * 스트림은 먼저 보낸 데이터를 먼저 받게 되어 있으면 중간에 건너뜀 없이 연속적으로 데이터를 주고받는다.
	 */
	class Memo2 {

	}

	/**
	 * <h5>1.3 바이트기반 스틤 - InputStream, OutputStream</h5><br>
	 * 스트림은 바이트 단위로 데이터를 전송하며 입출력 대상에 따라 다음과 같은 입출력스트림이 있다.<br>
	 * <table>
	 *     <thead>
	 *         <th>입력스트림</th>
	 *         <th>출력스트림</th>
	 *         <th>입출력 대상의 종류</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>FileInputStream</td>
	 *             <td>FileOutputStream</td>
	 *             <td>파일</td>
	 *         </tr>
	 *         <tr>
	 *             <td>ByteArrayInputStream</td>
	 *             <td>ByteArrayOutputStream</td>
	 *             <td>메모리(byte배열)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>PipedInputStream</td>
	 *             <td>PipedOutputStream</td>
	 *             <td>프로세스(프로세스간의 통신)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>AudioInputStream</td>
	 *             <td>AudioOutputStream</td>
	 *             <td>오디오장치</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * 이들은 모두 InputStream 또는 OutputStream의 자손들이며, 각각 읽고 쓰는데 필요한 추상 메서드를 자신에 맞게 구현해 놓았다.<br>
	 * 자바에서는 java.io 패키지를 통해서 많은 종류의 입출력관련 클래스들을 제공하고 있으며, 입출력을 처리할 수 있는 표준화된 방법을 제공함으로써 입출력의 대상이 달라져도 동일한 방법으로 입출력이 가능하기 때문에 프로그래밍을 하기에 편리하다.<br>
	 * <table>
	 *     <thead>
	 *         <th>InputStream</th>
	 *         <th>OutputStream</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>abstract int read()</td>
	 *             <td>abstract void write(int b)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int read(byte[] b)</td>
	 *             <td>void write(byte[] b)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int read(byte[] a, int off, int len)</td>
	 *             <td>void write(byte[] b, int off, int len)</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * <pre><code>
	 *     public abstract class InputStream {
	 *         ...
	 *         // 입력스트림으로 부터 1 byte를 읽어서 반환한다. 읽을 수 없으면 -1을 반환한다.
	 *         abstract int read();
	 *
	 *         // 입력스트림으로 부터 len개의 byte를 읽어서 byte배열 b의 off위치부터 저장한다.
	 *         int read(byte[] b, int off, int len) {
	 *             ...
	 *             for(int i = off; i < off + len; i++) {
	 *                 // read()를 호출해서 데이터를 읽어서 배열을 채운다.
	 *                 b[i] = (byte)read();
	 *             }
	 *         }
	 *         ...
	 *         // 입력스트림으로부터 byte배열 b의 크기만큼 데이터를 읽어서 배열 b에 저장한다.
	 *         int read(byte[] b) {
	 *             return read(b, 0, b.length);
	 *         }
	 *         ...
	 *     }
	 * </code></pre>
	 */
	class Memo3 {

	}

	/**
	 * <h5>1.4 보조 스트림</h5><br>
	 * 스트림의 기능을 보완하기 위한 보조스트림이 제공된다. 보조스트림은 실제 데이터를 주고받는 스트림이 아니기 때문에 데이터를 입출력할 수 있는 기능은 없지만,<br>
	 * 스트림의 기능을 향상시키거나 새로운 기능을 추가할 수 있다. <br>
	 * 그래서 보조스트림만으로는 입출력을 처리할 수 없고, 스트림을 먼저 생성한 다음에 이를 이용해서 보조스트림을 생성해야 한다.<br>
	 * <table>
	 *     <thead>
	 *         <th>입력</th>
	 *         <th>출력</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>FilterInputStream</td>
	 *             <td>FilterOutputStream</td>
	 *             <td>필터를 이용한 입출력 처리</td>
	 *         </tr>
	 *         <tr>
	 *             <td>BufferedInputStream</td>
	 *             <td>BufferedOutputStream</td>
	 *             <td>버퍼를 이용한 입출력 성능향상</td>
	 *         </tr>
	 *         <tr>
	 *             <td>DataInputStream</td>
	 *             <td>DataOutputStream</td>
	 *             <td>int, float와 같은 기본형 단위(primitive type)로 데이터를 처리하는 기능</td>
	 *         </tr>
	 *         <tr>
	 *             <td>SequenceInputStream</td>
	 *             <td>없음</td>
	 *             <td>두 개의 스트림을 하나로 연결</td>
	 *         </tr>
	 *         <tr>
	 *             <td>LineNumberInputStream</td>
	 *             <td>없음</td>
	 *             <td>읽어 온 데이터의 라인 번호를 카운트(JDK1.1 부터 LineNumberReader로 대체)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>ObjectInputStream</td>
	 *             <td>ObjectOutputStream</td>
	 *             <td>데이터를 객체단위로 읽고 쓰는데 사용, 주로 파일을 이용하며 객체 직렬화와 관련있음</td>
	 *         </tr>
	 *         <tr>
	 *             <td>없음</td>
	 *             <td>PrintStream</td>
	 *             <td>버퍼를 이용하며, 추가적인 print관련 기능(print, printf, println메서드)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>PushbackInputStream</td>
	 *             <td>없음</td>
	 *             <td>버퍼를 이용해서 읽어 온 데이터를 다시 되돌리는 기능(unread, push back to buffer)</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 */
	class Memo4 {

	}

	/**
	 * <h5>1.5 문자기반 스트림 - Reader, Writer</h5><br>
	 * 바이트기반이라 함은 입출력 단위가 1 byte라는 뜻이다. 이미 알고 있는 것과 같이 C언어와 달리 Java에서는 한문자를 의미하는 char형이 1 byte가 아니라 2 byte이기 때문에 바이트기반의 스트림으로 2 byte인 문자를 처리하는 데는 어려움이 있다.<br>
	 * <pre><code>
	 *     InputStream -> Reader
	 *     OutputStream -> Writer
	 * </code></pre>
	 * <br>
	 * <table>
	 *     <thead>
	 *         <th>바이트기반 스트림</th>
	 *         <th>문자기반 스트림</th>
	 *     </thead>
	 *     <tbody>
	 *		   <tr>
	 *		       <td>FileInputStream, FileOutputStream</td>
	 *		       <td>FileReader, FileWriter</td>
	 *		   </tr>
	 *		   <tr>
	 *		       <td>ByteArrayInputStream, ByteArrayOutputStream</td>
	 *		       <td>CharArrayReader, CharArrayWriter</td>
	 *		   </tr>
	 *		   <tr>
	 *		       <td>PipedInputStream, PipedOutputStream</td>
	 *		       <td>PipedReader, PipedWriter</td>
	 *		   </tr>
	 *		   <tr>
	 *		       <td>StringBufferInputStream(deprecated), StringBufferOutputStream(deprecated)</td>
	 *		       <td>StringReader, StringWriter</td>
	 *		   </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * 문자기반 스트림의 이름은 바이트기반 스트림의 이름에서 InputStream은 Reader로 OutputStream은 Writer로만 바꾸면 된다.<br>
	 * 단, ByteArrayInputStream에 대응하는 문자기반 스트림은 char배열을 사용하는 CharArrayReader이다.<br>
	 * 보조스트림 역시 다음과 같은 문자 기반 보조스트림이 존재하며 사용목적과 방식은 바이트기반 보조스트림과 다르지 않다.<br>
	 * <table>
	 *     <thead>
	 *         <th>바이트기반 보조스트림</th>
	 *         <th>문자기반 보조스트림</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>BufferedInputStream, BufferedOutputStream</td>
	 *             <td>BufferedReader, BufferedWriter</td>
	 *         </tr>
	 *         <tr>
	 *             <td>FilterInputStream, FilerOutputStream</td>
	 *             <td>FilterReader, FilterWriter</td>
	 *         </tr>
	 *         <tr>
	 *             <td>LineNumberInputStream(deprecated)</td>
	 *             <td>LineNumberReader</td>
	 *         </tr>
	 *         <tr>
	 *             <td>PrintStream</td>
	 *             <td>PrintWriter</td>
	 *         </tr>
	 *         <tr>
	 *             <td>PushbackInputStream</td>
	 *             <td>PushbackReader</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 */
	class Memo5 {

	}

}

