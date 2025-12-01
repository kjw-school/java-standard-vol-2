package chapter13;

/**
 * <h1>9. 쓰레드의 동기화</h1><br>
 * 멀티쓰레드 프로세스의 경우 여러 쓰레드가 같은 프로세스 내의 자원을 공유해서 작업하기 때문에 서로의 작업에 영향을 주게된다.<br>
 * 만일 쓰레드A가 작업하던 도중에 다른 쓰레드B에게 제어권이 넘어갔을 때, 쓰레드 A가 작업하던 공유데이터를 쓰레드B가 임의로 변경하였다면,<br>
 * 다시 쓰레드A가 제어권을 받아서 나머지 작업을 마쳤을 때 원래 의도했던 것과 다른 결과를 얻을 수 있다.<br>
 * 이러한 일이 발생하는 것을 방지하기 위해서 한 쓰레드가 특정 작업을 끝마치기 전까지 다른 쓰레드에 의해 방해받지 않도록 하는 것이 필요하다.<br>
 * 그래서 도입된 개념이 바로 '임계 영역(critical section)'과 '잠금(락, lock)'이다.<br>
 * 공유 데이터를 사용하는 코드 영역을 임계 영역으로 지정해놓고, 공유 데이터(객체)가 가지고 있는 lock을 획득한 단 하나의 쓰레드만 이 영역내의 코드를 수행핧 수 있게 한다.<br>
 * 해당 쓰레드가 임계 영역 내의 모든 코드를 수행하고 벗어나서 lock을 반납해야만 다른 쓰레드가 반납된 lock을 획득하여 임계 영역의 코드를 수행할 수 있게 된다.<br>
 * 이처럼 <b>한 쓰레드가 진행 중인 작업을 다른 쓰레드가 간섭하지 못하도록 막는 것을 '쓰레드의 동기화(synchronization)'</b>라고 한다.
 */
public class Chapter13_9 {

	/**
	 * <h5>9.1 synchronized를 이용한 동기화</h5><br>
	 * <pre><code>
	 *     1. 메서드 전체를 임계 영역으로 지정
	 *     public synchronized void calcSum() {
	 *         // ...
	 *     }
	 *     2. 특정한 영역을 임계 영역으로 지정
	 *     synchronized(객체의 참조변수) {
	 *         // ...<
	 *     }
	 * </code></pre>
	 * <br>
	 * 첫 번째 방법은 메서드 앞에 synchronized를 붙이는 것인데, synchronized를 붙이면 메서드 전체가 임계 영역으로 설정된다.<br>
	 * 쓰레드는 synchronized메서드가 호출된 시점부터 해당 메서드가 포함된 객체의 lock을 얻어 작업을 수행하다가 메서드가 종료되면 lock을 반환한다.<br>
	 * 두 번째 방법은 메서드 내의 코드 일부를 블럭{}으로 감싸고 블럭 앞에 'synchronized(참조변수)'를 붙이는 것인데, 이때 참조변수는 락을 걸고자하는 객체를 참조하는 것이야한다.<br>
	 * 이 블럭을 synchronized블럭이라고 부르며, 이 블럭의 영역 안으로 들어가면서부터 쓰레드는 지정된 객체의 lock을 얻게 되고, 이 블럭을 벗어나면 lock을 반납한다.<br>
	 * 두 방법 모두 lock의 획득과 반납이 모두 자동적으로 이루어지므로 우리가 해야 할 일은 그저 임계 영역만 설정해주는 것뿐이다.<br>
	 * 모든 객체는 lock을 하나씩 가지고 있으며, 해당 객체의 lock을 가지고 있는 쓰레드만 임계 영역의 코드를 수행할 수 있다. 다른 쓰레드들은 lock을 얻을 때까지 기다리게 된다.<br>
	 * 임계 영역은 멀티쓰레드 프로그램의 성능을 좌우하기 때문에 가능하면 메서드 전체에 락을 거는 것보다 synchronized블럭으로 임계 영역을 최소화해서 보다 효율적인 프로그램이 되도록 노력해야 한다.
	 */
	class Memo1 {

	}

	/**
	 * <h5>9.2 wait()과 notify()</h5><br>
	 * 특정 쓰레드가 객체의 락을 가진 상태로 오랜 시간을 보내지 않도록 하는 것도 중요하다. 만일 계좌에 출금할 돈이 부족해서 한 쓰레드가 락을 보유한 채로 돈이 입금될 때까지 오랜 시간을 보낸다면, 다른 쓰레드들은 모두 해당 객체의 락을 기다리느라 다른 작업들도 원활히 진행되지 않을 것이다.<br>
	 * 이러한 상황을 개선하기 위해 고안된 것이 바로 wait()과 notify()이다. 동기화된 임계 영역의 코드를 수행하다가 작업을 더 이상 진행할 상황이 아니면, 일단 wait()을 호출하여 쓰레드가 락을 반납하고 기다리게 한다.<br>
	 * 다른 쓰레드가 락을 얻어 해당 객체에 대한 작업을 수행할 수 있게 된다. 나중에 작업을 진행할 수 있는 상황이 되면 notify()를 호출해서, 작업을 중단했던 쓰레드가 다시 락을 얻어 작업을 진행할 수 있게 한다.<br>
	 * 차이가 있다면, 오래 기다린 쓰레드가 락을 얻는다는 보장이 없다는 것이다. wait()이 호출되면, 실행 중이던 쓰레드는 해당 객체의 대기실(waiting pool)에서 통지를 기다린다. notify()가 호출되면, 해당 객체의 대기실에 있던 모든 쓰레드 중에서 임의의 쓰레드만 통지를 받는다.<br>
	 * notifyAll()은 기다리고 있는 모든 쓰레드에게 통보를 하지만, 그래도 lock을 얻을 수 있는 것은 하나의 쓰레드일 뿐이고 나머지 쓰레드는 통보를 받긴 했지만, lock을 얻지 못하면 다시 lock을 기다리는 신세가 된다.<br>
	 * wait()과 notify()는 특정 객체에 대한 것이므로 Object클래스에 정의되어있다.<br>
	 * wait()은 notify() 또는 notifyAll()이 호출될 때까지 기다리지만, 매개변수가 있는 wait()은 지정된 시간동안만 기다린다. 지정된 시간이 지난 후에 자동적으로 notify()가 호출되는 것과 같다.<br>
	 * waiting pool은 객체마다 존재하는 것이므로 notifyAll()이 호출된다고 해서 모든 객체의 waiting pool에 있는 쓰레드가 깨워지는 것은 아니다. notifyAll()이 호출된 객체의 waiting pool에 대기 중인 쓰레드만 해당된다는 것을 기억하자.<br>
	 * <b>wait(), notify(), notifyAll()</b><br>
	 * - Object에 정의되어 있다.<br>
	 * - 동기화 블록(synchronized블록)내에서만 사용할 수 있다.<br>
	 * - 보다 효율적인 동기화를 가능하게 한다.
	 */
	class Memo2 {

	}

	/**
	 * <h5>기아 현상과 경쟁 상태</h5><br>
	 * 지독히 운이 나쁘면 요리사 쓰레드는 계속 통지를 받지 못하고 오랫동안 기다리게 되는데, 이것을 '기아(starvation) 현상'이라고 한다. 이 현상을 막으려면, notify()대신 notifyAll()을 사용해야 한다.<br>
	 * 일단 모든 쓰레드에게 통지를 하면, 손님 쓰레드는 다시 waiting pool에 들어가더라도 요리사 쓰레드는 결국 lock을 얻어서 작업을 진행할 수 있기 때문이다.<br>
	 * notifyAll()로 요리사 쓰레드의 기아현상은 막았지만, 손님 쓰레드까지 통지를 받아서 불필요하게 요리사 쓰레드와 lock을 얻기 위해 경쟁하게 된다. 이처럼 여러 쓰레드가 lock을 얻기 위해 서로 경쟁하는 것을 '경쟁 상태(race condition)'라고 하는데, 이 경쟁 상태를 개선하기 위해서는 요리사 쓰레드와 손님 쓰레드를 구별해서 통지하는 것이 필요하다.
	 */
	class Memo3 {
	}

	/**
	 * <h5>9.3 Lock과 Condition을 이용한 동기화</h5><br>
	 * 동기화할 수 있는 방법은 synchronized블럭 외에도 'java.util.concurrent.locks'패키지가 제공하는 lock클래스들을 이용하는 방법이 있다.<br>
	 * 이 패키지는 JDK1.5에 와서야 추가된 것으로 그 전에는 동기화 방법이 synchronized블럭뿐이었다.<br>
	 * synchronized블럭으로 동기화를 하면 자동적으로 lock이 잠기고 풀리기 때문에 편리하다.<br>
	 * 심지어 synchronized블럭 내에서 예외가 발생해도 lock은 자동적으로 풀린다. 그러나 때로는 같은 메서드 내에서만 lock을 걸 수 있다는 제약이 불편하기도 하다. 그럴 때 이 lock클래스를 사용한다.<br>
	 * <b>lock클래스의 종류</b><br>
	 * <b>ReentrantLock</b> 재집이 가능한 lock, 가장 일반적인 배타 lock<br>
	 * <b>ReentrantReadWriteLock</b> 읽기에는 공유적이고, 쓰기에는 배타적인 lock<br>
	 * <b>StampedLock</b> ReentrantReadWriteLock에 낙관적인 lock의 기능을 추가<br>
	 * <small>※ StampedLock은 JDK1.8부터 추가되었으며, 다른 lock과 달리 Lock인터페이스를 구현하지 않았다.</small><br>
	 * ReentrantLock은 가장 일반적인 lock이다. 'reentrant(재진입할 수 있는)'이라는 단어가 앞에 붙은 이유는 우리가 앞서 wait() & notify()에서 배운 것처럼,<br>
	 * 특정 조건에서 lock을 풀고 나중에 다시 lock을 얻고 임계영역으로 들어와서 이후의 작업을 수행할 수 있기 때문이다.<br>
	 * ReentrantReadWriteLock은 이름에서 알 수 있듯이, 읽기를 위한 lock과 쓰기를 위한 lock을 제공한다.<br>
	 * ReentrantLock은 배타적인 lock이라서 무조건 lock이 있어야만 임계 영역의 코드를 수행할 수 있지만,<br>
	 * ReentrantReadWriteLock은 읽이 lock이 걸려있으면, 다른 쓰레드가 읽이 lock을 중복해서 걸고 읽기를 수행할 수 있다.<br>
	 * 읽기는 애용을 변경하지 않으므로 동시에 여러 쓰레드가 읽어도 문제가 되지 않는다. 그러나 읽기 lock이 걸린 상태에서 쓰기 lock을 거는 것은 허용되지 않는다.<br>
	 * 반대의 경우도 마찬가지다, 읽기를 할때는 읽기 lock을 걸고, 쓰기 할 때는 쓰기 lock을 거는 것일 뿐 lock을 거는 방법은 같다.<br>
	 * StampedLock은 lock을 걸거나 해지할 때 '스탬프(long타입의 정수값)'를 사용하며, 읽기와 쓰기를 위한 lock외에 '낙관적 읽기 lock(optimstic reading lock)'이 추가된 것이다.<br>
	 * 읽기 lock이 걸려있으면, 쓰기 lock을 얻기 위해서는 읽기 lock이 풀릴 때까지 기다려야하는데 비해, '낙관적 읽기 lock'은 쓰기 lock에 의해 바로 풀린다.<br>
	 * 그래서 낙관적 읽기에 실패하면, 읽기 lock을 얻어서 다시 읽어 와야 한다, <b>무조건 읽기 lock을 걸지 않고, 쓰기와 읽기가 충동할 때만 쓰기가 끝난 후에 읽기 lock을 거는 것이다.</b>
	 */
	class Memo4 {
	}

	/**
	 * <h5>ReentrantLock의 생성자</h5><br>
	 * <b>ReentrantLock()</b><br>
	 * <b>ReentrantLock(boolean fair)</b><br>
	 * 생성자의 매개변수를 true로 주면, lock이 풀렸을 때 가장 오래 기다린 쓰레드가 lock을 획득할 수 있게, 즉 공정(fair)하게 처리한다.<br>
	 * 그러나 공정하게 처리하려면 어떤 쓰레드가 가장 오래 기다렸는지 확인하는 과정을 거칠 수밖에 없으므로 성능은 떨어진다.<br>
	 * <b>void lock()</b> lock을 잠근다.<br>
	 * <b>void unlock()</b> lock을 해지한다.<br>
	 * <b>boolean isLocked()</b> lock이 잠겼는지 확인한다.<br>
	 * 자동적으로 lock의 잠금과 해제가 관리되는 synchronized블럭과는 달리, ReetrantLock과 같은 lock클래스들은 수동으로 lock을 잠그고 해제해야 한다.<br>
	 * 이 외에도 tryLock()이라는 메서드가 있는데, 이 메서드는 lock()과 달리, 다른 쓰레드에 의해 lock이 걸려 있으면 lock을 얻으려고 기다리지 않는다. 또는 지정된 시간만큼만 기다린다.<br>
	 * <pre><code>
	 *     boolean tryLock()
	 *     boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException
	 * </code></pre>
	 * <br>
	 * lock()은 lock을 얻을 때까지 쓰레드를 블락(block)시키므로 쓰레드의 응답성이 나빠질수 있다. 응답성이 중요한 경우, tryLock()을 이용해서 지정된 시간동안 lock을 얻지 못하면 다시 작업을 시도할 것인지 포기한 것인지를 사용자가 결정할 수 있게 하는 것이 좋다.<br>
	 * 그리고 이 메서드는 InterruptedException을 발생시킬 수 있는데, 이것은 지정된 시간동안 lock을 얻으려고 기다리는 중에 interrupt()에 의해 작업을 취소될 수 있도록 코드를 작성할 수 있다는 뜻이다.
	 */
	class Memo5 {

	}

	/**
	 * <h5>ReentrantLock과 Condition</h5><br>
	 * wait() & notify() 예제에 요리사 쓰레드와 손님 쓰레드를 구분해서 통지하지 못한 다는 단점이 있었다.<br>
	 * wait() & notify()로 쓰레드의 종류를 구분하지 않고, 공유 객체의 waiting pool에 같이 몰아넣는 대신, 손님 쓰레드를 위한 Condition과 요리사 쓰레드를 위한 Condition을 만들어서 각각의 waiting pool에서 따로 기다리도록 하면 문제는 해결된다.<br>
	 * Condition은 이미 생성된 lock으로부터 newCondition()을 호출해서 생성한다.<br>
	 * <pre><code>
	 *     private ReentrantLock lock = new ReentrantLock(); // lock을 생성
	 *     // lock으로 condition을 생성
	 *     private Condition forCook = lock.newCondition();
	 *     private Condition forCust = lock.newCondition();
	 * </code></pre>
	 * <br>
	 * <table border="1">
	 *     <thead>
	 *         <td>Object</td>
	 *         <td>Condition</td>
	 *     </thead>
	 *     <tbody>
	 *         <tr>
	 *             <td>void wait()</td>
	 *             <td>void await()<br>void awaitUninterruptibly()</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void wait(long timeout)</td>
	 *             <td>boolean await(long time, TimeUnit unit)<br> long awaitNanos(long nanosTimeout)<br> boolean awaitUntil(Date deadline)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void notify()</td>
	 *             <td>void signal()</td>
	 *         </tr>
	 *         <tr>
	 *             <td>void notifyAll()</td>
	 *             <td>void signalAll()</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 */
	class Memo6 {

	}

	/**
	 * <h5>9.4 volatile</h5><br>
	 * 멀티 코어 프로세서에서는 코어마다 별도의 캐시를 가지고 있다.<br>
	 * 코어는 메모리에서 읽어온 값을 캐시에 저장하고 캐시에서 값을 읽어서 작업한다. 다시 같은 값을 읽어올 때는 먼저 캐시에 있는지 확인하고 없을 때만 메모리에서 읽어온다.<br>
	 * 그러다보니 도중에 메모리에 저장된 변수의 값이 변경되었는데도 캐시에 저장된 값이 갱신되지 않아서 메모리에 저장된 값이 다른 경우가 발생한다.<br.
	 * 변수 앞에 volatile을 붙이면, 코어가 변수의 값을 읽어올 때 캐시가 아닌 메모리에서 읽어오기 때문에 캐시와 메모리간의 값의 불일치가 해결된다.<br>
	 * 변수에 volatile을 붙이는 대신에 synchronized블럭을 사용해도 같은 효과를 얻을 수 있다. 쓰레드가 synchronized블럭으로 들어갈 때와 나올 때, 캐시와 메모리간의 동기화가 이루어지기 떄문에 값의 불일치가 해소되기 때문이다.
	 */
	class Memo7 {

	}

	/**
	 * <h5>volatile로 long과 double을 원자화</h5><br>
	 * JVM은 데이터를 4 byte(=32bit)단위로 처리하기 때문에, int와 int보다 작은 타입들은 한 번에 릭거나 쓰는 것이 가능하다.<br>
	 * 즉, 단 하나의 명령어로 읽거나 쓰기가 가능하다는 뜻이다. 하나의 명령어는 더 이상 나눌 수 없는 최소의 작업단위이므로 작업의 중간에 다른 쓰레드가 끼어들 틈이 없다.<br>
	 * 그러나, 크기가 8 byte인 long과 double타입의 변수는 하나의 명령어로 값을 읽거나 쓸 수 없기 때문에, 변수의 값을 읽는 과정에 다른 쓰레드가 끼어들 여지가 있다.<br>
	 * 다른 쓰레드가 끼어들지 못하게 하려고 변수를 읽고 쓰는 모든 문장을 synchronized블럭으로 감쌀수도 있지만, 더 간단한 방법이 있다. 변수를 선언할 때 volatile을 붙이는 것이다.<br>
	 * <small>※ 상수에는 volatile을 붙일 수 없다. 즉, 변수에 final과 volatile을 같이 불일 수 없다. 사실 상수는 변하지 않는 값이므로 멀티쓰레드에 안전(thread-safe)하다. 그래서 volatile를 붙일 필요가 없다.</small><br>
	 * <pre><code>
	 *     volatile long sharedVal; // long타입의 변수(8 byte)를 원자화
	 *     volatile double sharedVal; // double타입의 변수(8 byte)를 원자화
	 * </code></pre>
	 * <br>
	 * volatile은 해당 변수에 대한 읽거나 쓰기가 원자화 된다. 원자화라는 것은 작업을 더 이상 나눌 수 없게 한다는 의미인데, synchronized블럭도 일종의 원자화라고 할 수 있다.<br>
	 * 즉, synchronized블럭은 여러 문장을 원자화함으로써 쓰레드의 동기화를 구현한 것이라고 보면 된다.<br>
	 * volatile은 변수의 읽거나 쓰기를 원자화 할 뿐, 동기화하는 것은 아니라는 점에 주의하자. 동기화가 필요할 때 synchronized블럭 대신 volatile을 쓸 수 없다.
	 */
	class Memo8 {

	}

}
