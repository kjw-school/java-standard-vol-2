package chapter13;

import static java.lang.Thread.*;

import javax.swing.*;

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

	/**
	 * th1이 2초 동안 작업을 멈추고 일시정지 상태에 있도록 하였으니까 쓰레드 th1이 가장 늦게 종료되어야 하는데 결과에서는 제일 먼저 종료되었다.<br>
	 * 그 이유는 sleep()이 항상 현재 실행 중인 쓰레드에 대해 작동하기 때문에 'th1.sleep(2000)'과 같이 호출하였어도 실제로 영향을 받는 것은 main메서드를 실행하는 main쓰레드이다.
	 */
	static class ThreadEx01 {
		public static void main(String[] args) {
			ThreadEx01_1 th1 = new ThreadEx01_1();
			ThreadEx01_2 th2 = new ThreadEx01_2();
			th1.start();
			th2.start();

			try {
				sleep(2000);
			} catch (InterruptedException e) {
			}

			System.out.println("<<main 종료>>");
		} // main
	}

	static class ThreadEx01_1 extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 300; i++)
				System.out.print("-");
			System.out.print("<<th1 종료>>");
		} // run()
	}

	static class ThreadEx01_2 extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 300; i++)
				System.out.print("|");
			System.out.println("<<th2 종료>>");
		} // run()
	}

	/**
	 * <h5>interrupt()와 interrupted() - 쓰레드의 작업을 취소한다.</h5><br>
	 * 진행 중인 쓰레드의 작업이 끝나기 전에 취소시켜야할 때가 있다. 예를 들어 큰 파일을 다운로드 받을 때 시간이 너무 오래 걸리면 중간에 다운로드를 포기하고 취소할 수 있어야 한다.<br>
	 * interrupt()는 쓰레드에게 작업을 멈추라고 요청한다. 단지 멈추라고 요청만 하는 것일 뿐 쓰레드를 강제로 종료시키지는 못한다.<br>
	 * interrupt()는 그저 쓰레드의 interrupted상태(인스턴스 변수)를 바꾸는 것일 뿐이다.<br>
	 * 그리고 interrupted()는 쓰레드에 대해 interrupt()가 호출되었다면 true를 반환한다.<br>
	 * isInterrupted()도 쓰레드의 interrupted()가 호출되었는지 확인하는데 사용할 수 있지만, interrupted()와 달리 isInterrupted()는 쓰레드의 interrupted상태를 false로 초기화하지 않는다.<br>
	 * <code>
	 *     void interrupt() 쓰레드의 interrupted상태를 false에서 true로 변경<br>
	 *     boolean isInterrupted() 쓰레드의 interrupted상태를 반환.<br>
	 *     static boolean interrupted() 현재 쓰레드의 interrupted상태를 반환 후, false로 변경
	 * </code><br>
	 * 쓰레드가 sleep(), wait(), join()에 의해 '일시정지 상태(WAITING)'에 있을 때, 해당 쓰레드에 대해 interrupt()를 호출하면, sleep(), wait(), join()에서 InterruptedException이 발생하고 쓰레드는 '실행대기 상태(RUNNABLE)'로 바뀐다.<br>
	 * 즉, 멈춰있던 쓰레드를 깨워서 실행가능한 상태로 만드는 것이다.
	 */
	class Memo03 {
	}

	static class ThreadEx02 {
		public static void main(String[] args) {

			ThreadEx02_1 th1 = new ThreadEx02_1();
			th1.start();
			String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
			th1.interrupt(); // interrupt()를 호출하면, interrupted상태가 true가 된다.
			System.out.println("isInterrupted(): " + th1.isInterrupted()); // true
		}
	}

	static class ThreadEx02_1 extends Thread {
		@Override
		public void run() {
			int i = 10;

			while (i != 0 && !isInterrupted()) {
				System.out.println(i--);
				for (long x = 0; x < 2500000000L; x++)
					; // 시간 지연
			}
			System.out.println("카운트가 종료되었습니다.");
		}

	}

	static class ThreadEx03 {
		public static void main(String[] args) {
			ThreadEx03_1 th1 = new ThreadEx03_1();
			th1.start();

			String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
			System.out.println("입력하신 값은 " + input + "입니다.");
			th1.interrupt(); // interrupt()를 호출하면, interrupted상태가 true가 된다.
			System.out.println("isInterrupted(): " + th1.isInterrupted()); // true

		}
	}

	static class ThreadEx03_1 extends Thread {
		@Override
		public void run() {
			int i = 10;
			while (i != 0 && !isInterrupted()) {
				System.out.println(i--);
				try {
					sleep(1000); // 1초 지연
				} catch (InterruptedException e) {
				}
			}
			System.out.println("카운트가 종료되었습니다.");
		}
	}

	/**
	 * <h5>suspend(), resume(), stop()</h5><br>
	 * suspend()는 sleep()처럼 쓰레드를 멈추게 한다. suspend()에 의해 정지된 쓰레드는 resume()을 호출해야 다시 실행대기 상태가 된다.<br>
	 * stop()은 호출되는 즉시 쓰레드가 종료된다.<br>
	 * suspend(), resume(), stop()은 쓰레드의 실행을 제어하는 가장 손쉬운 방법이지만, suspend()와 stop()이 교착상태(deadlock)를 일으키기 쉽게 작성되어있으므로 사용이 권장되지 않는다.<br>
	 * 그래서 이 메서드들은 모두 'deprecated'되었다.
	 */
	class Memo04 {
	}

	static class TheadEx04 {
		public static void main(String[] args) {
			ThreadEx04_1 th = new ThreadEx04_1();
			Thread th1 = new Thread(th, "*");
			Thread th2 = new Thread(th, "**");
			Thread th3 = new Thread(th, "***");
			th1.start();
			th2.start();
			th3.start();

			try {
				sleep(2000);
				th1.suspend();
				sleep(2000);
				th2.suspend();
				sleep(3000);
				th1.resume();
				sleep(3000);
				th1.stop();
				th2.stop();
				sleep(2000);
				th3.stop();
			} catch (InterruptedException e) {
			}

		} // main
	}

	static class ThreadEx04_1 implements Runnable {

		@Override
		public void run() {
			while (true) {
				System.out.println(Thread.currentThread().getName());
				try {
					sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		} // run()
	}

	static class ThreadEx05 {
		public static void main(String[] args) {
			ThreadEx05_1 r1 = new ThreadEx05_1();
			ThreadEx05_1 r2 = new ThreadEx05_1();
			ThreadEx05_1 r3 = new ThreadEx05_1();
			Thread th1 = new Thread(r1, "*");
			Thread th2 = new Thread(r2, "**");
			Thread th3 = new Thread(r3, "***");
			th1.start();
			th2.start();
			th3.start();

			try {
				Thread.sleep(2000);
				r1.suspend();
				Thread.sleep(2000);
				r2.suspend();
				Thread.sleep(3000);
				r1.resume();
				Thread.sleep(3000);
				r1.stop();
				r2.stop();
				Thread.sleep(2000);
				r3.stop();
			} catch (InterruptedException e) {
			}
		}
	}

	static class ThreadEx05_1 implements Runnable {

		boolean suspended = false;
		boolean stopped = false;

		@Override
		public void run() {
			while (!stopped) {
				if (!suspended) {
					System.out.println(Thread.currentThread().getName());
					try {
						sleep(1000);
					} catch (InterruptedException e) {
					}
				}
			}

			System.out.println(Thread.currentThread().getName() + " - stopped");
		}

		public void suspend() {
			suspended = true;
		}

		public void resume() {
			suspended = false;
		}

		public void stop() {
			stopped = true;
		}

	}

	static class ThreadEx06 {

		public static void main(String[] args) {
			ThreadEx06_1 th1 = new ThreadEx06_1("*");
			ThreadEx06_1 th2 = new ThreadEx06_1("**");
			ThreadEx06_1 th3 = new ThreadEx06_1("***");
			th1.start();
			th2.start();
			th3.start();

			try {

				Thread.sleep(2000);
				th1.suspend();
				Thread.sleep(2000);
				th2.suspend();
				Thread.sleep(3000);
				th1.resume();
				Thread.sleep(3000);
				th1.stop();
				th2.stop();
				Thread.sleep(2000);
				th3.stop();

			} catch (InterruptedException e) {

			}
		}

	}

	static class ThreadEx06_1 implements Runnable {

		boolean suspended = false;
		boolean stopped = false;

		Thread th;

		ThreadEx06_1(String name) {
			th = new Thread(this, name); // Thread(Runnable r, String name)
		}

		@Override
		public void run() {
			while (!stopped) {
				if (!suspended) {
					System.out.println(Thread.currentThread().getName());
					try {
						sleep(1000);
					} catch (InterruptedException e) {
					}
				}
			}
			System.out.println(Thread.currentThread().getName() + " - stopped");
		}

		public void suspend() {
			suspended = true;
		}

		public void resume() {
			suspended = false;
		}

		public void stop() {
			stopped = true;
		}

		public void start() {
			th.start();
		}
	}

	/**
	 * <h5>yield() - 다른 쓰레드에게 양보한다.</h5><br>
	 * yield()는 쓰레드 자신에게 주어진 실행시간을 다른 차례의 쓰레드에게 양보(yield)한다.<br>
	 * 예를 들어 스케쥴러에 의해 1초의 실행시간을 할당받은 쓰레드가 0.5초의 시간동안 작업한 상태에서 yield()가 호출되면, 나머지 0.5초는 포기하고 다시 실행대기상태가 된다.
	 */
	class Memo05 {

	}

}
