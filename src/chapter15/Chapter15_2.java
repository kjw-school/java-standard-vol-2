package chapter15;

/**
 * <h1>2. 바이트기반 스트림</h1>
 */
public class Chapter15_2 {

	/**
	 * <h5>2.1 InputStream과 OutputStream</h5><br>
	 * InputStream과 OutputStream은 모든 바이트기반의 스트림의 조상이다.<br>
	 * InputStream의 메서드<br>
	 * <table>
	 *     <thead>
	 *         <th>메서드명</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>int available()</td>
	 *             <td>스트림으로부터 읽어 올 수 있는 데이터의 크기를 반환한다</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void close()</td>
	 *             <td>스트림을 닫음으로써 사용하고 있던 자원을 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void mark(int readlimit)</td>
	 *             <td>현재위치를 표시해 놓는다. 후에 reset()에 의해서 표시해 놓은 위치로 다시 돌아갈 수 있다. readlimit은 되돌아갈 수 있는 byte의 수이다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean markSupported()</td>
	 *             <td>mark()와 reset()을 지원하는지를 알려 준다. mark()와 reset()기능을 지원하는 것은 서택적이므로, mark()와 reset()을 사용하기 전에 markSupported()를 호출해서 지원여부를 확인해야한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>abstract int read()</td>
	 *             <td>1 byte를 읽어 온다(0~255사이의 값). 더 이상 읽어 올 데이터가 없으면 -1을 반환한다. abstract메서드라서 InputStream의 자손들은 자신의 상황에 알맞게 구현해야한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int read(byte[] b)</td>
	 *             <td>배열 b의 크기만큼 읽어서 배열을 채우고 읽어 온 데이터의 수를 반환한다. 반환하는 값은 항상 배열의 크기보다 작거나 같다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int read(byte[] b, int off, int len)</td>
	 *             <td>최대 len의 byte를 읽어서, 배열 b의 지정된 위치(off)부터 저장한다. 실제로 읽어 올 수 있는 데이터가 len개보다 적을 수 있다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void reset()</td>
	 *             <td>스트림에서 위치를 마지막으로 mark()이 호출되었던 위치로 되돌린다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>long skip(long n)</td>
	 *             <td>스트림에서 주어진 길이(n)만큼을 건너뛴다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * OutputStream의 메서드<br>
	 * <table>
	 *     <thead>
	 *         <th>메서드명</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>void close()</td>
	 *             <td>입력소스를 닫음으로써 사용하고 있던 자원을 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void flush()</td>
	 *             <td>스트림의 버퍼에 있는 모든 내용을 출력소스에 쓴다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>abstract void write(int b)</td>
	 *             <td>주어진 값을 출력소스에 쓴다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void write(byte[] b)</td>
	 *             <td>주어진 배열 b에 저장된 모든 내용을 출력소스에 쓴다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void write(byte[] b, int off, int len)</td>
	 *             <td>주어진 배열 b에 저장된 내용 중에서 off번째부터 len개 만큼만을 읽어서 출력소스에 쓴다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * <br>
	 * 스트림의 종류에 따라서 mark()와 reset()을 사용하여 이미 읽은 데이터를 되돌려서 다시 읽을 수 있다. 이 기능을 지원하는 스트림인지 확인하는 markSupported()를 통해서 알 수 있다.<br>
	 * flush()는 버퍼가 있는 출력스트림의 경우에만 의미가 있으며, OutputStream에 정의된 flush()는 아무런 일도 하지 않는다.<br>
	 * 프로그램이 종료될 때, 사용하고 닫지 않은 스트림을 JVM이 자동적으로 닫아 주기는 하지만, 스트림을 사용해서 모든 작업을 마치고 난 후에는 clone()를 호출해서 반드시 닫아 주어야 한다.<br>
	 * 그러나 ByteArrayInputStream과 같이 메모리를 사용하는 스트림과 System.in, System.out과 같은 표준 입출력 스트림은 닫아 주지 않아도 된다.
	 */
	class Memo1 {

	}

	/**
	 * <h5>2.2 ByteArrayInputStream과 ByteArrayOutputStream</h5><br>
	 * ByteArrayInputStream/ByteArrayOutputStream은 메모리, 즉 바이트배열에 데이터를 입출력 하는데 사용되는 스트림이다.<br>
	 * 주로 다른 곳에 입출력하기 전에 데이터를 임시로 바이트배열에 담아서 변환 등의 작업을 하는데 사용된다.<br>
	 * 바이트배열은 사용하는 자원이 메모리 밖에 없으므로 가비지컬렉터에 의해 자동적으로 자원을 반환하므로 close()를 이용해서 스트림을 닫지 않아도 된다.<br>
	 * 배열을 이용한 입출력은 작업의 효율을 증가시키므로 가능하면 입출력 대상에 따라 알맞은 크기의 배열을 사용하는 것이 좋다.<br>
	 * available()은 블락킹(blocking) 없이 읽어 올 수 있는 바이트의 수를 반환한다.<br>
	 * <small>※블락킹이란 데이터를 읽어 올 때 데이터를 기다리기 위해 멈춰있는 것을 뜻한다.</small>
	 */
	class Memo2 {

	}

	/**
	 * <h5>2.3 FileInputStream과 FileOutputStream</h5><br>
	 * FileInputStream/FileOutputStream은 파일에 입출력을 하기 위한 스트림이다. 실제 프로그래밍에서 많이 사용되는 스트림 중의 하나이다.<br>
	 * FileInputStream과 FileOutputStream의 생성자<br>
	 * <table>
	 *     <thead>
	 *         <th>생성자</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>FileInputStream(String name)</td>
	 *             <td>지정된 파일이름(name)을 가진 실제 파일과 연결된 FileInputStream을 생성한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>FileInputStream(File file)</td>
	 *             <td>파일의 이름이 String이 아닌 File인스턴스로 지정해주어야 하는 점을 제외하고 FileInputStream(String name)와 같다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>FileInputStream(FileDescriptor fdObj)</td>
	 *             <td>파일 디스크립터(fdObj)로 FileInputStream을 생성한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>FileOutputStream(String name)</td>
	 *             <td>지정된 파일이름(name)을 가진 실제 파일과의 연결된 FileOutputStream을 생성한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>FileOutputStream(String name, boolean append)</td>
	 *             <td>지정된 파일이름(name)을 가진 실제 파일과 연결된 FileOutputStream을 생성한다. 두번째 인자인 append를 true로 하면, 출력 시 기존의 파일내용의 마지막에 덧붙인다. false면, 기존의 파일내용을 덮어쓰게 된다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>FileOutputStream(File file)</td>
	 *             <td>파일의 이름을 String이 아닌 File인스턴스로 지정해주어야 하는 점을 제외하고 FileOutputStream(String name)과 같다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>FileOutputStream(File file, boolean append)</td>
	 *             <td>파일의 이름을 String이 아닌 File인스턴스로 지정해주어야 하는 점을 제외하고 FileOutputStream(String name, boolean append)과 같다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>FileOutputStream(FileDescriptor fdObj)</td>
	 *             <td>파일 디스크립터(fdObj)로 FileOutputStream을 생성한다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 */
	class Memo3 {

	}

}
