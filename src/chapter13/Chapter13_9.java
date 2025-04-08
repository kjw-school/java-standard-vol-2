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
	 * <code>
	 *     <b>1. 메서드 전체를 임계 영역으로 지정</b><br>
	 *     public <b>synchronized</b> void calcSum() {<br>
	 *         // ...
	 *     }<br>
	 *     <b>2. 특정한 영역을 임계 영역으로 지정</b><br>
	 *     <b>synchronized(객체의 참조변수)</b> {<br>
	 *         // ...<br>
	 *     }
	 * </code>
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
	 */
	class Memo4 {
	}

}
