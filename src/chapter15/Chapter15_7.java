package chapter15;

/**
 * <h1>7. 직렬화(Serialization)</h1>
 */
public class Chapter15_7 {

	/**
	 * <h5>7.1 직렬화란?</h5><br>
	 * 직렬화(serialization)란 객체를 데이터 스트림으로 만드는 것을 뜻한다.<br>
	 * 다시 얘기하면 객체에 저장된 데이터를 스트림에 쓰기(write)위해 연속적인(serial) 데이터로 변환하는 것을 말한다.<br>
	 * 반대로 스트림으로부터 데이터를 읽어서 객체를 만드는 것을 역직렬화(deserialization)라고 한다.<br>
	 * 객체는 클래스에 정의된 인스턴스변수와 집합이다. 객체에는 클래스변수나 메서드가 포함되지 않는다.<br>
	 * 객체는 오직 인스턴스변수들로만 구성되어 있다.<br>
	 * 객체를 생성하면 인스턴스변수와 메서드를 함께 그리곤 했지만 사실 객체에는 메서드가 포함되지 않는다.<br>
	 * 인스턴스변수는 인스턴스마다 다른 값을 가질 수 있어야하기 때문에 별도의 메모리공간이 필요하지만 메서드는 변하는 것이 아니라서 메모리를 낭비해 가면서 인스턴스마다 같은 내용의 코드(메서드)를 포함시킬 이유는 없다.<br>
	 * 객체를 저장한다는 것은 바로 객체의 모든 인스턴스변수의 값을 저장한다는 것과 같은 의미이다.<br>
	 * 어떤 객체를 저장하고자 한다면, 현재 객체의 모든 인스턴스변수의 값을 저장하기만 하면 된다.<br>
	 * 그리고 저장했던 객체를 다시 생성하려면, 객체를 생성한 후에 저장했던 값을 읽어서 생성한 객체의 인스턴스변수에 저장하면 되는 것이다.
	 */
	class Memo1 {

	}

	/**
	 * <h5>7.2 ObjectInputStream, ObjectOutputStream</h5><br>
	 * 직렬화(스트림에 객체를 출력)에는 ObjectOutputStream을 사용하고 역직렬화(스트림으로부터 객체를 입력)에는 ObjectInputStream을 사용한다.<br>
	 * ObjectInputStream과 ObjectOutputStream은 각각 InputStream과 OutputStream을 직접 상속받지만 기반스트림을 필요로 하는 보조스트림이다.<br>
	 * 그래서 객체를 생성할 때 입출력(직렬화/역직렬화)할 스트림을 지정해주어야 한다.<br>
	 * 만일 파일에 객체를 저장(직렬화)하고 싶다면 다음과 같이 하면 된다.<br>
	 * <pre><code>
	 *     FileOutputStream fos = new FileOutputStream("objectfile.ser");
	 *     ObjectOutputStream out = new ObjectOutputStream(fos);
	 *
	 *     out.writeObject(new UserInfo());
	 * </code></pre>
	 * <br>
	 * 역직렬화 방법 역시 간단하다. 직렬화할 때와는 달리 입력스트림을 사용하고 writeObject(Object obj)대신 readObject()를 사용하여 저장된 데이터를 읽기만 하면 객체로 역직렬화된다.<br>
	 * 다만 readObject()의 반환타입이 Object이기 때문에 객체 원래의 타입으로 형변환 해주어야 한다.<br>
	 * <pre><code>
	 *     FileInputStream fis = new FileInputStream("objectfile.ser");
	 *     ObjectInputStream in = new ObjectInputStream(fis);
	 *
	 *     UserInfo info = (UserInfo)in.readObject();
	 * </code></pre>
	 * <br>
	 * 객체를 직렬화/역직렬화하는 작업은 객체의 모든 인스턴스변수가 참조하고 있는 모든 객체에 대한 것이기 때문에 상당히 복잡하며 시간도 오래 걸린다.<br>
	 * readObject()와 writeObject()를 사용한 자동 직렬화가 편리하기는 하지만 직렬화작업시간을 단축시키려면 직렬화하고자 하는 객체의 클래스에 추가적으로 다음과 같은 2개의 메서드를 직접 구현해주어야 한다.<br>
	 * <pre><code>
	 *     private void writeObject(ObjectOutputStream out) throws IOException{
	 *         // write메서드를 사용해서 직렬화를 수행한다.
	 *     }
	 *
	 *     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
	 *         // read메서드를 사용해서 역직렬화를 수행한다.
	 *     }
	 * </code></pre>
	 */
	class Memo2 {

	}

	/**
	 * <h5>7.3 직렬화가 가능한 클래스 만들기 - Serializable, transient</h5><br>
	 * 직렬화가 가능한 클래스를 만드는 방법은 간단하다. 직렬화하고자 하는 클래스가 java.io.Serializable인터페이스를 구현하도록 하면 된다.<br>
	 * Serializable을 구현한 클래스를 상속받는다면, Serializable을 구현하지 않아도 된다.<br>
	 * 조상이 Serializable를 구현하면 자손역시 직렬화가 가능하다.<br>
	 * 조상클래스가 Serializable을 구현하지 않았다면 자손클래스를 직렬화할 때 조상클래스에 정의된 인스턴스변수는 직렬화 대상에서 제외된다.<br>
	 * 조상클래스에 정의된 인스턴스변수를 직렬화 대상에 포함시키기 위해서는 조상클래스가 Serializable을 구현하도록 하던가, 자손에서 조상의 인스턴스변수들이 직렬화되도록 처리하는 코드를 직접 추가해 주어야 한다.<br>
	 * 모든 클래스의 최고조상인 Object는 Serializable을 구현하지 않았기 때문에 직렬화할 수 없다.<br>
	 * 제어자 transient를 붙여서 직렬화 대상에서 제외되도록 할 수 있다.
	 */
	class Memo3 {

	}

	/**
	 * <h5>7.4 직렬화가능한 클래스의 버전관리</h5><br>
	 * 직렬화된 객체를 역직렬화할 때는 직렬화 했을 때와 같은 클래스를 사용해야한다. 그러나 클래스의 이름이 같더라도 클래스의 내용이 변경된 경우 역직렬화는 실패하며 다음과 같은 예외가 발생한다.<br>
	 * <pre><code>
	 *     java.io.InvalidClassException: UserInfo; local class incompatible;
	 *     stream classdesc serialVersionUID = 6956...
	 * </code></pre>
	 * <br>
	 * 위 예외의 내용은 직렬화 할 때와 역직렬화 할 때의 클래스의 버전이 같아야 하는데 다르다는 것이다.<br>
	 * 객체가 직렬화될 때 클래스에 정의된 멤버들의 정보를 이용해서 serialVersionUID라는 클래스의 버전을 자동생성해서 직렬화 내용에 포함된다.<br>
	 * 그래서 역직렬화 할 때 클래스의 버전을 비교함으로써 직렬화할 때의 클래스의 버전과 일치하는지 확인할 수 있는 것이다.<br>
	 * 그러나 static변수나 상수 또는 transient가 붙은 인스턴스변수가 추가되는 경우에는 직렬화에 영향을 미치지 않기 때문에 클래스의 버전을 다르게 인식하도록 할 필요는 없다.<br>
	 * 네트웍으로 객체를 직렬화하여 전송하는 경우, 보내는 쪽과 받는 쪽이 모두 같은 버전의 클래스를 가지고 있어야하는데 클래스가 조금만 변경되어도 해당 클래스를 재배포하는 것은 프로그램을 관리하기 어렵게 만든다.<br>
	 * 이럴 때는 클래스의 버전을 수동으로 관리해줄 필요가 있다.<br>
	 * 클래스의 버전을 수동으로 관리하려면 다음과 같이 serialVersionUID를 추가로 정의해야한다.<br>
	 * <pre><code>
	 *     class MyData implements java.io.Serializable {
	 *         static final long serialVersionUID = 3518731767529258119L;
	 *         int value;
	 *     }
	 * </code></pre>
	 * <br>
	 * 이렇게 클래스 내에 serialVersionUID를 정의해주면, 클래스의 내용이 바뀌어도 클래스의 버전이 자동생성된 값으로 변경되지 않는다.<br>
	 * serialVersionUID의 값은 정수값이면 어떠한 어떠한 값으로도 지정할 수 있지만 서로 다른 클래스간에 같은 값을 갖지 않도록 serialver.exe를 사용해서 생성된 값을 사용하는 것이 보통이다.<br>
	 * serialver.exe에 의해서 생성되는 serialVersionUID값은 클래스의 멤버들에 대한 정보를 바탕으로 하기 때문에 이 정보가 변경되지 않는 한 항상 같은 값을 생성한다.
	 */
	class Memo4 {

	}

}
