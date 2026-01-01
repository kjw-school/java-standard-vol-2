package chapter13;

import javax.swing.*;

/**
 * <h1>4. 싱글쓰레드와 멀티쓰레드</h1>
 */
public class Chapter13_4 {

	/**
	 * <p>
	 *     하나의 쓰레드로 두개의 작업을 수행한 시간돠 두개의 쓰레드로 두 개의 작업을 수행한 시간은 거의 같다.<br>
	 *     오히려 두 개의 쓰레드로 작업한 시간이 싱글쓰레드로 작업한 시간보다 더 걸리게 되는데 그 이유는 쓰레드간의 작업전환(context switching)에 시간이 걸리기 때문이다.<br>
	 *     작업 전환을 할 때는 현재 진행 중인 작업의 상태, 예를 들면 다음에 실행해야할 위치(PC, 프로그램 카운터) 등의 정보를 저장하고 읽어 오는 시간이 소요된다.<br>
	 *     참고로 쓰레드의 스위칭에 비해 프로세스의 스위칭이 더 많은 정보를 저장해야하므로 더 많은 시간이 소요된다.<br>
	 *     <small>※프로세스 또는 쓰레드 간의 작업 전환을 '컨텍스트 스위칭(context switching)'이라고 한다.</small><br>
	 *     싱글 코어에서 단순히 CPU만을 사용하는 계산 작업이라면 오히려 멀티쓰레드보다 싱글쓰레드로 프로그래밍하는 것이 더 효율적이다.<br>
	 *     싱글 코어인 경우에는 멀티쓰레드라도 하나의 코어가 번갈아가면서 작업을 수행하는 것이므로 두 작업이 절대 겹치지 않는다.<br>
	 *     그러나, 멀티 코어에서는 멀티쓰레드로 두 작업을 수행하면, 동시에 두 쓰레드가 수행될 수 있으므로 두 작업이 겹치는 부분이 발생한다.<br>
	 *     <small>※여러 쓰레드가 여러 작업을 동시에 진행하는 것을 병행(concurrnet)라고 하고, 하나의 작업을 여러 쓰레드가 나눠서 처리하는 것을 병렬(parallel)이라고 한다.</small><br>
	 *     JVM의 쓰레드 스케줄러에 의해서 어떤 쓰레드가 얼마동안 실행될 것인지 결정되는 것과 같이 프로세스도 프로세스 스케줄러에 의해서<br>
	 *     실행순서와 실행시간이 결정되기 때문에 매 순간 상황에 따라 프로세스에게 할당되는 실행시간이 일정하지 않고 쓰레드에게 할당되는 시간 역시 일정하지 않게 된다.<br>
	 *     자바가 OS(플랫폼) 독립적이라고 하지만 실제로는 OS종속적인 부분이 몇 가지 있는데 쓰레드도 그 중의 하나이다.<br>
	 *     <small>※JVM의 종류에 따라 쓰레드 스케줄러의 구현방법이 다를 수 있기 때문에 멀티쓰레드로 작성된 프로그램을 다른 종류의 OS에서도 충분히 테스트해 볼 필요가 있다.</small><br>
	 *     두 쓰레드가 서로 다른 자원을 사용하는 작업의 경우에는 싱글쓰레드 프로세스보다 멀티 쓰레드 프로세스가 더 효율적이다.<br>
	 *     만일 사용자로 부터 입력받는 작업(A)과 화면에 출력하는 작업(B)을 하나의 쓰레드로 처리한다면 사용자가 입력을 마칠 때까지 아무 일도 하지 못하고 기다리기만 해야한다.<br>
	 *     그러나 두 개의 쓰레드로 처리한다면 사용자의 입력을 기다리는 동안 다른 쓰레드가 작업을 처리할 수 있기 때문에 보다 효율적인 CPU의 사용이 가능하다.<br>
	 * </p>
	 */
	class Memo01{}

	private static class ThreadEx1 {
		public static void main(String[] args) {
			long startTime = System.currentTimeMillis();

			for(int i = 0; i < 300; i++) System.out.printf("%s", new String("-"));
			System.out.print("소요시간1:" + (System.currentTimeMillis() - startTime));
			for(int i = 0; i < 300; i++) System.out.printf("%s", new String("|"));

			System.out.print("소요시간2:" + (System.currentTimeMillis() - startTime));


		}
	}

	static class ThreadEx2 {

		static long startTime = 0;

		public static void main(String[] args) {
			ThreadEx2_1 t1 = new ThreadEx2_1();
			t1.start();
			startTime = System.currentTimeMillis();
			for(int i = 0; i < 300; i++) System.out.printf("%s", new String("-"));
			System.out.print("소요시간1:" + (System.currentTimeMillis() - ThreadEx2.startTime));

		}

	}

	static class ThreadEx2_1 extends Thread {
		@Override
		public void run() {

			for(int i = 0; i< 300; i++) System.out.printf("%s", new String("|"));
			System.out.print("소요시간2:" + (System.currentTimeMillis() - ThreadEx2.startTime));

		}
	}

	/*
	 * 이 예제는 하나의 쓰레드로 사용자의 입력을 받는 작업과 화면에 숫자를 출력하는 작업을 처리하기 때문에 사용자가 입력을 마치기 전까지는
	 * 화면에 숫자가 출력되지 않다가 사용자가 입력을 마치고 나서야 화면에 숫자가 출력된다.
	 */
	static class ThreadEx3 {
		public static void main(String[] args) {
			String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
			System.out.println("입력하신 값은 " + input + "입니다.");

			for(int i = 10; i > 0; i--) {
				System.out.println(i);
				try {
					Thread.sleep(1000); // 1초간 시간을 지연한다.
				} catch(Exception e) {

				}
			}
		}
	}

	/*
	 * 이전 예제와는 달리 사용자로부터 입력받는 부분과 화면에 숫자를 출력하는 부분을 두 개의 쓰레드로 나누어서 처리했기 때문에 사용자가
	 * 입력을 마치지 않았어도 화면에 숫자가 출력되는 것을 알 수 있다.
	 */
	static class ThreadEx4 {

		public static void main(String[] args) {
			ThreadEx4_1 t1 = new ThreadEx4_1();
			t1.start();

			String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
			System.out.println("입력하신 값은 " + input + "입니다.");
		}

	}

	static class ThreadEx4_1 extends Thread{
		@Override
		public void run() {
			for(int i = 10; i > 0; i--) {
				System.out.println(i);
				try {
					sleep(1000);
				} catch (Exception e) {

				}
			}
		}
	}

}
