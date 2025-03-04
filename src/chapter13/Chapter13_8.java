package chapter13;

/**
 * <h1>8. 쓰레드의 실행제어</h1>
 */
public class Chapter13_8 {

	/**
	 * 쓰레드 프로그래밍이 어려운 이유는 동기화(synchronization)와 스케줄링(scheduling)때문이다.<br>
	 * <b>쓰레드의 스케줄링과 관련된 메서드</b><br>
	 * 메서드 | 설명<br>
	 * static void sleep(long millis), static void sleep(long millis, int nanos) | 지정된 시간(천분의 일초 단위)동안 쓰레드를 일시정지시킨다. 지정한 시간이 지나고 나면, 자동적으로 다시 실행대기 상태가 된다.<br>
	 * void join(), void join(long millis), void join(long millis, int nanos) | 지정된 시간동안 쓰레드가 실행되도록 한다. 지정된 시간이 지나거나 작업이 종료되면 join()을 호출한 쓰레드로 다시 돌아와 실행을 계속한다.<br>
	 * void interrupt() | sleep()이나 join()에 의해 일시정지 상태인 쓰레드를 깨워서 실행대기상태로 만든다. 해당 쓰레드에서는 InterruptedException이 발생함으로써 일시정지상태를 벗어나게 된다.<br>
	 * void stop() | 쓰레드를 즉시 종료시킨다.<br>
	 * void suspend() | 쓰레드를 일시정지시킨다. resume()을 호출하면 다시 실행대기상태가 된다.<br>
	 * void resume() | suspend()에 의해 일시정지상태에 있는 쓰레드를 실행대기상태로 만든다.<br>
	 * static void yield() | 실행 중에 자신에게 주어진 실행시간을 다른 쓰레드에게 양보(yield)하고 자신은 실행대기상태가 된다.<br>
	 * <small>※resume(), stop(), suspend()는 쓰레드를 교착상태(dead-lock)로 만들기 쉽기 때문에 deprecated되었다.</small><br>
	 * <b>쓰레드의 상태</b><br>
	 * 상태 | 설명<br>
	 * NEW | 쓰레드가 생성되고 아직 start()가 호출되지 않은 상태<br>
	 * RUNNABLE | 실행 중 또는 실행 가능한 상태<br>
	 * BLOCKED | 동기화블럭에 의해서 일시정지된 상태(lock이 풀릴 때까지 기다리는 상태)<br>
	 * WAITING, TIMED_WAITING | 쓰레드의 작업이 종료되지는 않았지만 실행가능하지 않은(unrunnable) 일시정지 상태, TIMED_WAITING은 일시정지시간이 지정된 경우를 의미한다.<br>
	 * TERMINATED | 쓰레드의 작업이 종료된 상태<br>
	 * <small>※쓰레드의 상태는 Thread의 getState()메서드를 호출해서 확인할 수 있다.JDK1.5부터 추가되었다.</small><br>
	 * <b>쓰레드의 생성부터 소멸까지의 과정</b><br>
	 * 1. 쓰레드를 생성하고 start()를 호출하면 바로 실행되는 것이 아니라 실행대기열에 저장되어 자신의 차례가 될 때까지 기다려야 한다. 실행대기열은 큐(queue)와 같은 구조로 먼저 실행대기열에 들어온 쓰레드가 먼저 실행된다.<br>
	 * 2. 실행대기상태에 있다가 자신의 차례가 되면 실행상태가 된다.<br>
	 * 3. 주어진 실행시간이 다되거나 yield()를 만나면 다시 실행대기상태가 되고 다음 차례의 쓰레드가 실행상태가 된다.<br>
	 * 4. 실행 중에 suspend(), sleep(), wait(), join(), I/O block에 의해 일시정지상태가 될 수 있다. I/O block은 입출력작업에서 발생하는 지연상태를 말한다. 사용자의 입력을 기다리는 경우를 예로 들 수 있는데, 이런 경우 일시정지 상태에 있다가 사용자가 입력을 마치면 다시 실행대기 상태가 된다.<br>
	 * 5. 지정된 일시정지시간이 다되거나(time-out), notify(), resume(), interrupt()가 호출되면 일시정지상태를 벗어나 다시 실행대기열에 저장되어 자신의 차례를 기다리게 된다.<br>
	 * 6. 실행을 모두 마치거나 stop()이 호출되면 쓰레드는 소멸된다.
	 */
	class Memo01 {
	}

	/**
	 * <h5>sleep(long millis) - 일정시간동안 쓰레드를 멈추게 한다.</h5><br>
	 * sleep()은 지정된 시간동안 쓰레드를 멈추게 한다.<br>
	 * static void sleep(long millis)<br>
	 * static void sleep(long millis, int nanos)<br>
	 * 밀리세컨드(millis, 1000분의 일초)와 나노세컨드(nanos, 10억분의 일초)의 시간단위로 세밀하게 값을 지정할 수 있지만 어느 정도의 오차가 발생할 수 있다는 것은 염두에 둬야한다.<br>
	 * <small>※나노세컨드(nanos)로 지정할 수 있는 값의 범위는 0~999999이며, 999999나노세컨드는 약 1밀리세컨드이다.</small><br>
	 * sleep()에 의해 일시정지 상태가 된 쓰레드는 지정된 시간이 다 되거나 interrupt()가 호출되면(interruptedException발생), 잠에서 깨어나 실행대기 상태가 된다.<br>
	 * 그래서 sleep()을 호출할 때는 항상 try-catch문으로 예외를 처리해줘야 한다.
	 */
	class Memo02 {
	}

	static class ThreadEx01 {
		
	}

}
