package chapter16;

/**
 * <h1>1. 네트워킹(Networking)</h1><br>
 * 네트워킹(networking)이란 두 대 이상의 컴퓨터를 케이블로 연결하여 네트워크(network)를 구성하는 것을 말한다. 네트워킹의 개념은 컴퓨터들을 서로 연결하여 데이터 손쉽게 주고받거나 또는 자원프린터와 같은 주변기기를 함께 공유하고자 하는 노력에서 시작되었다.<br>
 * 자바에서 제공하는 java.net패키지를 사용하면 이러한 네트워크 어플리케이션의 데이터 통신 부분을 쉽게 작성할 수 있으며, 간단한 네트워크 어플리케이션은 단 몇 줄의 자바코드 만으로도 작성이 가능하다.
 */
public class Chapter16 {

	/**
	 * <h5>1.1 클라이언트/서버(client/server)</h5><br>
	 * '클라이언트/서버'는 컴퓨터간의 관계를 역할로 구분하는 개념이다.<br>
	 * 서버(server)는 서비스를 제공하는 컴퓨터(service provider)이고, 클라이언트(client)는 서비스를 사용하는 컴퓨터(service user)가 된다.<br>
	 * 서비스는 서버가 클라이언트로부터 요청받은 작업을 처리하여 그 결과를 제공하는 것을 뜻하며 서버가 제공하는 서비스의 종류에 따라 파일서버(file server), 메일서버(mail server), 어플리케이션 서버(application server) 등이 있다.<br>
	 * 네트워크를 구성할 때 전용서버를 두는 것을 서버기반모델(server-based model)이라 하고 별도의 전용서버없이 각 클라이언트가 서버역할을 동시에 수행하는 것을 P2P모델(peer-to-peer)이라 한다.<br>
	 * 서버기반 모델과 P2P모델간의 비교<br>
	 * <table>
	 *     <thead>
	 *         <th>서버기반 모델(server-based model)</th>
	 *         <th>P2P 모델(peer-to-peer model)</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>
	 *                 - 안정적인 서비스의 제공이 가능하다.<br>
	 *                 - 공유 데이터의 관리와 보안이 용이하다.<br>
	 *                 - 서버구축비용과 관리비용이 든다.
	 *             </td>
	 *             <td>
	 *                 - 서버구축 및 운용비용을 절감할 수 있다.<br>
	 *                 - 자원의 활용을 극대화 할 수 있다.<br>
	 *                 - 자원의 관리가 어렵다.<br>
	 *                 - 보안이 취약하다.
	 *             </td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 */
	class Memo1 {

	}

	/**
	 * <h5>1.2 IP주소(IP address)</h5><br>
	 * IP주소는 컴퓨터(호스트 ,host)를 구별하는데 사용되는 고유한 값으로 인터넷에 연결된 모든 컴퓨터는 IP주소를 갖는다.<br>
	 * IP주소는 4 byte(32 bit)의 정수로 구성되어 있으며, 4개의 정수가 마침표를 구분자로'a.b.c.d'와 같은 형식으로 표현된다.<br>
	 * 여기서 a, b, c, d는 부호없는 1 byte값, 즉 0~255사이의 정수이다.<br>
	 * IP주소는 다시 네트워크주소와 호스트주소로 나눌 수 있는데, 32 bit(4 byte)의 IP주소중에서 네트워크주소와 호스트주소가 각각 몇 bit를 차지하는 지는 네트워크를 어떻게 구성하였는지에 따라 달라진다.<br>
	 * 그리고 서로 다른 두 호스트의 IP주소의 네트워크주소가 같다는 것은 두 호스트가 같은 네트워크에 포함되어 있다는 것을 의미한다.<br>
	 * IP주소와 서브넷 마스크를 비트연산자 '&'로 연산하면 IP주소에서 네트워크 주소만을 뽑아낼 수 있다.<br>
	 * '&'연산자는 bit의 값이 모두 1일 때만 1을 결과로 얻기 때문에 IP주소의 마지막 8 bit는 모두 0이 되었다.<br>
	 * 이 결과로 부터 IP주소 192.168.10.100의 네트워크 주소는 24bit(192.168.10)이라는 것과 호스트 주소는 마지막 8 bit(100)이라는 것을 알 수 있다.<br>
	 * IP주소에서 네트워크주소가 차지하는 자리수가 많을수록 호스트 주소의 범위가 줄어들기 때문에 네트워크의 규모가 작아진다. 이 경우 호스트 주소의 자리수가 8자리이기 때문에 256개(2<sup>8</sup>)의 호스트만 이 네트워크에 포함될 수 있다.<br>
	 * 호스트 주소가 0인 것은 네트워크 자신을 나타내고, 255는 브로드캐스트 주소로 사용되기 때문에 실제로는 네트워크에 포함 가능한 호스트 개수는 254개이다.<br>
	 * 이처럼 IP주소와 서브넷 마스크를 '&'연산하면 네트워크 주소를 얻어낼 수 있어서 서로 다른 두 호스트의 IP주소를 서브넷 마스크로 '&'연산을 수행해서 비교하면 이 두 호스트가 같은 네트워크 상에 존재하는지를 쉽게 확인할 수 있다.
	 */
	class Memo2 {

	}

	/**
	 * <h5>1.3 InetAddress</h5><br>
	 * 자바에서는 IP주소를 다루기 위한 클래스로 InetAddress를 제공하며 다음과 같은 메서드가 정의되어 있다.<br>
	 * InetAddress의 메서드<br>
	 * <table>
	 *     <thead>
	 *         <th>메서드</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>byte[] getAddress()</td>
	 *             <td>IP주소를 byte배열로 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 static InetAddress[]<br>
	 *                 getAllByName(String host)
	 *             </td>
	 *             <td>도메인명(host)에 지정된 모든 호스트의 IP주소를 배열에 담아 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>static InetAddress(byte[] addr)</td>
	 *             <td>byte배열을 통해 IP주소를 얻는다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 static InetAddress<br>
	 *                 getByName(String host)
	 *             </td>
	 *             <td>도메인명(host)을 통해 IP주소를 얻는다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>string getCanonicalHostName()</td>
	 *             <td>FQDN(fully qualified domain name)을 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String getHostAddress()</td>
	 *             <td>호스트의 IP주소를 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String getHostName()</td>
	 *             <td>호스트의 이름을 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>static InetAddress getLocalHost()</td>
	 *             <td>지역호스트의 IP주소를 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean isMulticastAddress()</td>
	 *             <td>IP주소가 멀티캐스트 주소인지 알려준다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean isLoopbackAddress()</td>
	 *             <td>IP주소가 loopback 주소(127.0.0.1)인지 알려준다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 */
	class Memo3 {

	}

	/**
	 * <h5>1.4 URL(Uniform Resource Locator)</h5><br>
	 * URL은 인터넷에 존재하는 여러 서버들이 제공하는 자원에 접근할 수 있는 주소를 표현하기 위한 것으로 '프로토콜://호스트명:포트번호/경로명/파일명?쿼리스트링#참조'의 형태로 이루어져 있다.<br>
	 * <small>※ URL에서 포트번호, 쿼리, 참조는 생략할 수 있다.</small><br>
	 * <pre><code>
	 *     http://www.codechobo.com:80/sample/hello.html?referer=codechobo#index1
	 *
	 *     프로토콜 - 자원에 접근하기 위해 서버와 통신하는데 사용되는 통신규약(http)
	 *     호스트명 - 자원을 제공하는 서버의 이름(www.codechobo.com)
	 *     포트번호 - 통신에 사용되는 서버의 포트번호(80)
	 *     경로명 - 접근하려는 자원이 저장된 서버상의 위치(/sample/)
	 *     파일명 - 접근하려는 자원의 이름(hello.html)
	 *     쿼리(query) - URL에서 '?'이후의 부분(referer = codechobo)
	 *     참조(anchor) - URL에서 '#'이후의 부분(index1)
	 * </code></pre>
	 * <br>
	 * <small>※ HTTP프로토콜에서는 80번 포트를 사용하기 때문에 URL에서 포트번호를 생략하는 경우 80으로 간주한다.</small><br>
	 * 자바에서는 URL을 다루기 위한 클래스로 URL클래스를 제공하며 다음과 같은 메서드가 정의되어 있다.<br>
	 * URL의 메서드<br>
	 * <table>
	 *     <thead>
	 *         <th>메서드</th>
	 *         <th>설명</th>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>URL(String spec)</td>
	 *             <td>지정된 문자열 정보의 URL객체를 생성한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>URL(String protocol, String host, String file)</td>
	 *             <td>지정된 값으로 구성된 URL 객체를 생성한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>URL(String protocol, String host, int port, String file)</td>
	 *             <td>지정된 값으로 구성된 URL 객체를 생성한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String getAuthority()</td>
	 *             <td>호스트명과 포트를 문자열로 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Object getContent()</td>
	 *             <td>URL의 Content객체를 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>Object getContent(Class[] classes)</td>
	 *             <td>URL의 Content객체를 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int getDefaultPort()</td>
	 *             <td>URL의 기본 포트를 반환한다. (http는 80)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String getFile()</td>
	 *             <td>파일명을 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String getHost()</td>
	 *             <td>호스트명을 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String getPath()</td>
	 *             <td>경로명을 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>int getPort()</td>
	 *             <td>포트를 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String getProtocol()</td>
	 *             <td>프토콜을 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String getQuery()</td>
	 *             <td>쿼리를 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String getRef()</td>
	 *             <td>참조(anchor)를 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String getUserInfo()</td>
	 *             <td>사용자정보를 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>URLConnection openConnection()</td>
	 *             <td>URL과 연결된 URLConnection을 얻는다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>URLConnection openConnection(Proxy proxy)</td>
	 *             <td>URL과 연결된 URLConnection을 얻는다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>InputStream openStream()</td>
	 *             <td>URL과 연결된 URlConnection의 InputStream을 얻는다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>boolean sameFile(URL other)</td>
	 *             <td>두 URL이 서로 같은 것인지 알려준다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>
	 *                 void set(String protocol, String host, int port, String file, String ref)
	 *             </td>
	 *             <td>URL 객체의 속성을 지정된 값으로 설정한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void set(String protocol, String host, int port, String authority, String userInfo, String path, String query, String ref)</td>
	 *             <td>URL 객체의 속성을 지정된 값으로 설정한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>String toExternalForm()</td>
	 *             <td>URL을 문자열로 변환하여 반환한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>URI toURI()</td>
	 *             <td>URL을 URI로 변환하여 반환한다.</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 */
	class Memo4 {

	}

	/**
	 * <h5>1.5 URLConnection</h5><br>
	 * URLConnection은 어플리케이션과 URL간의 통신연결을 나타내는 클래스의 최상위 클래스로 추상클래스이다.<br>
	 * URLConnection을 상속받아 구현한 클래스로는 HttpURLConnection과 JarURLConnection이 있으며 URL의 프로토콜이 http프로토콜이라면 openConnection()은 HttpURLConnection을 반환한다.<br>
	 * URLConnection을 사용해서 연결하고자하는 자원에 접근하고 읽고 쓰기를 할 수 있다.<br>
	 * <small>※ openConnection()은 URL클래스의 메서드이다.</small><br>
	 * <small>※ HttpURLConnection은 sun.net.www.protocol.http패키지에 속해있다.</small>
	 */
	class Memo5 {

	}

}
