package chapter15;

/**
 * <h1>3. 바이트기반의 보조스트림</h1><br>
 */
public class Chapter15_3 {

	/**
	 * <h5>3.1 FilterInputStream과 FilterOutputStream</h5><br>
	 * FilterInputStream/FilterOutputStream은 InputStream/OutputStream의 자손이면서 모든 보조스트림의 조상이다.<br>
	 * 보조스트림은 자체적으로 입출력을 수행할 수 없기 때문에 기반스트림을 필요로 한다.<br>
	 * <pre><code>
	 *     protected FilterInputStream(InputStream in)
	 *     public FilterOutputStream(OutputStream out)
	 * </code></pre>
	 * <br>
	 * FilterInputStream/FilterOutputStream의 모든 메서드는 단순히 기반스트림의 메서드를 그대로 호출할 뿐이다.<br>
	 * FilterInputStream/FilterOutputStream자체로는 아무런 일도 하지 않음을 의미한다.<br>
	 * FilterInputStream/FilterOutputStream은 상속을 통해 원하는 작업을 수행하도록 읽고 쓰는 메서드를 오버라이딩해야 한다.<br>
	 * <pre><code>
	 *     public class FilterInputStream extends InputStream {
	 *         protected volatile InputStream in;
	 *         protected FilterInputStream(InputStream in) {
	 *             this.in = in;
	 *         }
	 *         public int read() throws IOException {
	 *             return in.read();
	 *         }
	 *         ...
	 *     }
	 * </code></pre>
	 * <br>
	 * 생성자 FilterInputStream(InputStream in)는 접근 제어자가 protected이기 때문에 Filter InputStream의 인스턴스를 생성해서 사용할 수 없고 상속을 통해서 오버라이딩되어야한다.<br>
	 * FilterInputStream/FilterOutputStream을 상속받아서 기반스트림에 보조기능을 추가한 보조스트림 클래스는 다음과 같다.<br>
	 * <pre><code>
	 *     FilterInputStream의 자손 - BufferedInputStream, DataInputStream, PushbackInputStream 등
	 *     FilterOutputStream의 자손 - BufferedOutputStream, DataOutputStream, PrintStream
	 * </code></pre>
	 */
	class Memo1 {

	}

	/**
	 * <h5>3.2 BufferedInputStream과 BufferedOutputStream</h5><br>
	 * BufferedInputStream/BufferedOutputStream은 스트림의 입출력 효율을 높이기 위해 버퍼를 사용하는 보조스트림이다.<br>
	 * 한 바이트씩 입출력하는 것 보다는 버퍼(바이트배열)를 이용해서 한 번에 여러 바이트를 입출력하는 것이 빠르게 때문에 대부분의 입출력 작업에 사용된다.<br>
	 * BufferedInputStream의 생성자<br>
	 * <table>
	 *     <thead>
	 *         <th>생성자</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>BufferedInputStream(InputStream in, int size)</td>
	 *             <td>주어진 InputStream인스턴스를 입력소스(input source)로 하며 지정된 크기(byte단위)의 버퍼를 갖는 BufferedInputStream인스턴스를 생성한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>BufferedInputStream(InputStream in)</td>
	 *             <td>주어진 InputStream인스턴스를 입력소스(input source)로 하며 버퍼의 크기를 지정해주지 않으므로 기본적으로 8192byte 크기의 버퍼를 갖게 된다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * BufferedInputStream의 버퍼크기는 입력소스로부터 한 번에 가져올 수 있는 데이터의 크기로 지정하면 좋다.<br>
	 * 보통 입력소스가 파일인 경우 8192(8K) 정도의 크기로 하는 것이 보통이며, 버퍼의 크기를 변경해가면서 테스트하면 최적의 버퍼크기를 알아낼 수 있다.<br>
	 * 프로그램에서 입력소스로부터 데이터를 읽기 위해 처음으로 read메서드를 호출하면, BufferedInputStream은 입력소스로 부터 버퍼 크기만큼의 데이터를 읽어다 자신의 내부 버퍼에 저장한다.<br>
	 * 이제 프로그램에서는 BufferedInputStream의 버퍼에 저장된 데이터를 읽으면 되는 것이다.<br>
	 * 외부의 입력소스로 부터 읽는 것보다 내부의 버퍼로 부터 읽는 것이 훨씬 빠르기 때문에 그만큼 작업 효율이 높아진다.<br>
	 * 프로그램에서 버퍼에 저장된 모든 데이터를 다 읽고 그 다음 데이터를 읽기위해 read메서드가 호출되면, BufferedInputStream은 입력소스로부터 다시 버퍼크기 만큼의 데이터를 읽어다 버퍼에 저장해 놓는다.<br>
	 * 이와 같은 작업이 계속해서 반복된다.<br>
	 * BufferedOutputStream의 생성자와 메서드<br>
	 * <table>
	 *     <thead>
	 *         <th>메서드 / 생성자</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>BufferedOutputStream(OutputStream out, int size)</td>
	 *             <td>주어진 OutputStream인스턴스를 출력소스(output source)로하며 지정된 크기(단위byte)의 버퍼를 갖는 BufferedOutputStream인스턴스를 생성한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>BufferedOutputStream(OutputStream out)</td>
	 *             <td>주어진 OutputStream인스턴스를 출력소스(output source)로하며 버퍼의 크기를 지정해주지 않으므로 기본적으로 8192 byte 크기의 버퍼를 갖게된다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>flush()</td>
	 *             <td>버퍼의 모든 내용을 출력소스에 출력한 다음, 버퍼를 비운다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>close()</td>
	 *             <td>flush()를 호출해서 버퍼의 모든 내용을 출력소스에 출력하고, BufferedOutputStream인스턴스가 사용하던 모든 자원을 반환한다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * BufferedOutputStream 역시 버퍼를 이용해서 출력소스와 작업을 하게 되는데, 입력소스로부터 데이터를 읽을 때와는 반대로,<br>
	 * 프로그램에서 write메서드를 이용한 출력이 BufferedOutputStream의 버퍼에 저장된다.<br>
	 * 버퍼가 가득 차면, 그 때 버퍼의 모든 내용을 출력소스에 출력한다. 그리고는 버퍼를 비우고 다시 프로그램으로부터의 출력을 저장할 준비를 한다.<br>
	 * 버퍼가 가득 찼을 때만 출력소스에 출력을 하기 때문에, 마지막 출력부분이 출력소스에 쓰이지 못하고 BufferedOutputStream의 버퍼에 남아있는 채로 프로그램이 종료될 수 있다는 점을 주의해야한다.<br>
	 * 그래서 프로그램에서 모든 출력작업을 마친 후 BufferedOutputStream에 close()나 flush()를 호출해서 마지막에 버퍼에 있는 모든 내용이 출력소스에 출력되도록 해야 한다.<br>
	 * <small>※BufferedOutputStream의 close()는 flush()를 호출하여 버퍼의 내용을 출력스트림에 쓰도록 한 후, BufferedOutputStream인스턴스의 참조변수에 null를 지정함으로써 사용하던 자원들이 반환되게 한다.</small>
	 */
	class Memo2 {

	}

	/**
	 * <h5>3.3 DataInputStream과 DataOutputStream</h5><br>
	 * DataInputStream/DataOutputStream도 각각 FilterInputStream/FilterOutputStream의 자손이며 DataInputStream은 DataInput인터페이스를,<br>
	 * DataOutputStream은 DataOutput인터페이스를 각각 구현하였기 때문에, 데이터를 읽고 쓰는데 있어서 byte단위가 아닌, 8가지 기본 자료형의 단위로 읽고 쓸 수 있다는 있다는 장점이 있다.<br>
	 * DataOutputStream이 출력하는 형식은 각 기본 자료형 값을 16진수로 표현하여 저장한다.<br>
	 * DataInputStream의 생성자와 메서드<br>
	 * <table>
	 *     <thead>
	 *         <th>메서드 / 생성자</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>DataInputStream(InputStream in)</td>
	 *             <td>주어진 InputStream인스턴스를 기반스트림으로 하는 DataInputStream인스턴스를 생성한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 boolean readBoolean()<br>
	 *                 byte readByte()<br>
	 *                 char readChar()<br>
	 *                 short readShort()<br>
	 *                 int readInt()<br>
	 *                 long readLong()<br>
	 *                 float readFloat()<br>
	 *                 double readDouble()<br>
	 *                 int readUnsignedByte()<br>
	 *                 int readUnsignedShort()
	 *             </td>
	 *             <td>각 타입에 맞게 값을 읽어온다. 더 이상 읽을 값이 엇ㅂ으면 EOFException을 발생시킨다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 void readFully(byte[] b)<br>
	 *                 void readFully(byte[] b, int off, int len)
	 *             </td>
	 *             <td>입력스트림에서 지정된 배열의 크기만큼 또는 지정된 위치에서 len만큼 데이터를 읽어온다. 파일의 끝에 도달하면 EOFException이 발생하고, I/O에러가 발생하면 IOException n이 발생한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String readUTF()</td>
	 *             <td>UTF-8형식으로 쓰여진 문자를 읽는다. 더 이상 읽은 값이 없으면 EOFException이 발생한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>static String readUTF(DataInput in)</td>
	 *             <td>입력스트림(in)에서 UTF-8형식의 유니코드를 읽어온다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int skipBytes(int n)</td>
	 *             <td>현재 읽고 있는 위치에서 지정된 숫자(n) 만큼을 건너뛴다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * DataOutputStream의 생성자와 메서드<br>
	 * <table>
	 *     <thead>
	 *         <th>메서드 / 생성자</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>DataOutputStream(OutputStream out)</td>
	 *             <td>주어진 OutputStream인스턴스를 기반스트림으로 하는 DataOutputStream인스턴스를 생성한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 void writeBoolean(boolean b)<br>
	 *                 void writeByte(int b)<br>
	 *                 void writeChar(int c)<br>
	 *                 void writeChars(String s)<br>
	 *                 void writeShort(int s)<br>
	 *                 void writeInt(int i)<br>
	 *                 void writeLong(long l)<br>
	 *                 void writeFloat(float f)<br>
	 *                 void writeDouble(double d)
	 *             </td>
	 *             <td>각 자료형에 알맞은 값들을 출력한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void writeUTF(String s)</td>
	 *             <td>UTF형식으로 문자를 출력한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void writeChars(String s)</td>
	 *             <td>주어진 문자열을 출력한다. writeChar(int c)메서드를 여러번 호출한 결과와 같다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int size()</td>
	 *             <td>지금까지 DataOutputStream에 쓰여진 byte의 수를 알려 준다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 */
	class Memo3 {

	}

	/**
	 * <h5>3.4 SequenceInputStream</h5><br>
	 * SequenceInputStream은 여러 개의 입력스트림을 연속적으로 연결해서 하나의 스트림으로부터 데이터를 읽는 것과 같이 처리할 수 있도록 도와준다.<br>
	 * SequenceInputStream의 생성자를 제외하고 나머지 작업은 다른 입력스트림과 다르지 않다.<br>
	 * 큰 파일을 여러 개의 작은 파일로 나누었다가 하나의 파일로 합치는 것과 같은 작업을 수행할 때 사용하면 좋을 것이다.<br>
	 * <small>※ SequenceInputStream은 다른 보조스트림들과는 달리 FilterInputStream의 자손이 아닌 InputStream을 바로 상속받아서 구현하였다.</small><br>
	 * SequenceInputStream의 생성자<br>
	 * <table>
	 *     <thead>
	 *         <th>메서드 / 생성자</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>SequenceInputStream(Enumeration e)</td>
	 *             <td>Enumeration에 저장된 순서대로 입력스트림을 하나의 스트림으로 연결한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>SequenceInputStream(InputStream s1, InputStream s2)</td>
	 *             <td>두 개의 입력스트림을 하나로 연결한다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 */
	class Memo4 {

	}

	/**
	 * <h5>3.5 PrintStream</h5><br>
	 * PrintStream은 데이터를 기반스트림에 다양한 형태로 출력할 수 있는 print, println, printf와 같은 메서드를 오버로딩하여 제공한다.<br>
	 * PrintStream은 데이터를 적절한 문자로 출력하는 것이기 때문에 문자기반 스트림의 역할을 수행한다.<br>
	 * 그래서 JDK1.1에서 부터 PrintStream보다 향상된 기능의 문자기반 스트림인 PrintWriter가 추가되었으나 그 동안 매우 빈번히 사용되던 System.out이 PrintStream이다 보니 둘 다 사용할 수밖에 없게 되엇다.<br>
	 * PrintStream과 PrintWriter는 거의 같은 기능을 가지고 있지만 PrintWriter가 PrintStream에 비해 다양한 언어의 문자를 처리하는데 적합하기 때문에 가능하면 PrintWriter를 사용하는 것이 좋다.<br>
	 * PrintStream의 생성자와 메서드<br>
	 * <table>
	 *     <thead>
	 *         <th>생성자 / 메서드</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>
	 *                 PrintStream(File file)<br>
	 *                 PrintStream(File file, String csn)<br>
	 *                 PrintStream(OutputStream out)<br>
	 *                 PrintStream(OutputStream out, boolean autoFlush)<br>
	 *                 PrintStream(OutputStream out, boolean autoFlush, String encoding)<br>
	 *				   PrintStream(String fileName)<br>
	 *				   PrintStream(String fileName, String csn)
	 *             </td>
	 *             <td>
	 *                 지정된 출력스트림을 기반으로 하는 PrintStream인스턴스를 생성한다.<br>
	 *                 autoFlush의 값을 true로 하면 println메서드가 호출되거나 개행문자가 출력될 때 자동으로 flush된다. 기본값을 false이다.
	 *             </td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean checkError()</td>
	 *             <td>스트림을 flush하고 에러가 발생했는지를 알려 준다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 void print(boolean b) | void println(boolean b)<br>
	 *                 void print(char c) | void println(char c)<br>
	 *                 void print(char[] c) | void println(char[] c)<br>
	 *                 void print(double d) | void println(double d)<br>
	 *                 void print(float f) | void println(float f)<br>
	 *                 void print(int i) | void println(int i)<br>
	 *                 void print(long l) | void println(long l)<br>
	 *                 void print(Object o) | void println(Object o)<br>
	 *                 void print(String s) | void println(String s)
	 *             </td>
	 *             <td>인자로 주어진 값을 출력소스에 문자로 출력한다. println메서드는 출력 후 줄바꿈을 하고, print메서드는 줄을 바꾸지 않는다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void println()</td>
	 *             <td>줄바꿈 문자(line separator)를 출력함으로써 줄을 바꾼다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>PrintStream printf(String format, Object... args)</td>
	 *             <td>정형화된(formatted) 출력을 가능하게 한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>protected void setError()</td>
	 *             <td>작업 중에 오류가 발생했음을 알린다. (setError()를 호출한 후에, checkError()를 호출하면 true를 반환한다.)</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * printf()는 JDK1.5부터 추가된 것으로, C언어와 같이 편리한 형식화된 출력을 지원하게되었다.<br>
	 * 정수의 출력에 사용할 수 있는 옵션<br>
	 * <table>
	 *     <thead>
	 *         <th>format</th>
	 *         <th>설명</th>
	 *         <th>결과(int i = 65)</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>%d</td>
	 *             <td>10진수(decimal integer)</td>
	 *             <td>65</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%o</td>
	 *             <td>8진수(octal integer)</td>
	 *             <td>101</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%x</td>
	 *             <td>16진수(hexadecimal integer)</td>
	 *             <td>41</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%c</td>
	 *             <td>문자</td>
	 *             <td>A</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%s</td>
	 *             <td>문자열</td>
	 *             <td>65</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%5d</td>
	 *             <td>5자리 숫자, 빈자리는 공백으로 채운다.</td>
	 *             <td>   65</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%-5d</td>
	 *             <td>5자리 숫자, 빈자리는 공백으로 채운다.(왼쪽 정렬)</td>
	 *             <td>65</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%05d</td>
	 *             <td>5자리 숫자, 빈자리는 0으로 채운다.</td>
	 *             <td>00065</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * 문자열의 출력에 사용될 수 있는 옵션<br>
	 * <tbody>
	 *     <thead>
	 *         <th>format</th>
	 *         <th>설명</th>
	 *         <th>결과(String str = "ABC")</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>%s</td>
	 *             <td>문자열(string)</td>
	 *             <td>ABC</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%5s</td>
	 *             <td>5자리 문자열, 빈자리는 공백으로 채운다.</td>
	 *             <td>   ABC</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%-5s</td>
	 *             <td>5자리 문자열, 빈자리는 공백으로 채운다.(왼쪽 정렬)</td>
	 *             <td>ABC</td>
	 *         </tr>
	 *     </tbody>
	 * </tbody>
	 * <br>
	 * 실수의 출력에 사용될 수 있는 옵션<br>
	 * <table>
	 *     <thead>
	 *         <th>format</th>
	 *         <th>설명</th>
	 *         <th>결과(float f = 1234.56789f)</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>%e</td>
	 *             <td>지수형태표현(exponent)</td>
	 *             <td>1.234568e+03</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%f</td>
	 *             <td>10진수(decimal float)</td>
	 *             <td>1234.56789</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%3.1f</td>
	 *             <td>출력될 자리수를 최소 3자리(소수점포함), 소수점 이하 1자리 (2번째 자리에서 반올림)</td>
	 *             <td>1234.6</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%8.1f</td>
	 *             <td>소수점 이상 최소 6자리, 소수점 이하 1자리, 출력될 자리수를 최소 8자리(소수점포함)를 확보한다. 빈자리는 공백으로 채워진다.(오른쪽 정렬)</td>
	 *             <td>   1234.6</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%08.1f</td>
	 *             <td>소수점이상 최소 6자리, 소수점 이하 1자리, 출력될 자리수를 최소 8자리(소수점포함)를 확보한다. 빈자리는 0으로 채워진다.</td>
	 *             <td>001234.6</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%-8.1f</td>
	 *             <td>소수점이상 최소 6자리, 소수점 이하 1자리, 출력될 자리수를 최소 8자리(소수점포함)를 확보한다. 빈자리는 공백으로 채워진다.(왼쪽 정렬)</td>
	 *             <td>1234.6</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * 특수문자를 출력하는 옵션<br>
	 * <table>
	 *     <thead>
	 *         <th>format</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>\f</td>
	 *             <td>탭(tab)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%n</td>
	 *             <td>줄바꿈 문자(new line)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>%%</td>
	 *             <td>%</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * 날짜와 시간의 출력에 사용할 수 있는 옵션<br>
	 * <table>
	 *     <thead>
	 *         <th>format</th>
	 *         <th>설명</th>
	 *         <th>결과</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>
	 *                 %tR<br>
	 *                 %tH:%tM
	 *             </td>
	 *             <td>시분(24시간)</td>
	 *             <td>
	 *                 21:05<br>
	 *                 21:05
	 *             </td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 %tT<br>
	 *                 %tH:%tM:%tS
	 *             </td>
	 *             <td>시분초(24시간)</td>
	 *             <td>
	 *                 21:05:33<br>
	 *                 21:05:33
	 *             </td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 %tD<br>
	 *                 %tm/%td/%ty
	 *             </td>
	 *             <td>월일년</td>
	 *             <td>
	 *                 11/16/15<br>
	 *                 11/16/15
	 *             </td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 %tF<br>
	 *                 %tY-%tm-%td
	 *             </td>
	 *             <td>년월일</td>
	 *             <td>
	 *                 2015-11-16<br>
	 *                 2015-11-16
	 *             </td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 */
	class Memo5 {

	}

}

