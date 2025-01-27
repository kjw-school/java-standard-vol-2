package chapter13;

/**
 * <p>
 *     <h1>3. start()와 run()</h1>
 * </p>
 */
public class Chapter13_3 {

	/**
	 * <p>
	 *     main메서드에서 {@code run()}을 호출하는 것은 생성된 쓰레드를 실행시키는 것이 아니라 단순히 클래스에 선언된 메서드를 호출하는 것일 뿐이다.<br>
	 *     반면에 {@code start()}는 새로운 쓰레드가 작업을 실행하는데 필요한 호출스택(call stack)을 생성한 다음에 {@code run()}을 호출해서, 생성된 호출스택에 {@code run()}이 첫 번째로 올라가게 한다.<br>
	 *     모든 쓰레드는 독립적인 작업을 수행하기 위해 자신만의 호출스택을 필요로 하기 때문에, 새로운 쓰레드를 생성하고 실행시킬 때마다 새로운 호출스택이 생성되고 쓰레드가 종료되면 작업에 사용된 호출스택은 소멸된다.<br>
	 *     쓰레드가 둘 이상일 때는 호출스택의 최상위에 있는 메서드일지라도 대기상태에 있을 수 있다.<br>
	 *     스케줄러는 실행대기중인 쓰레드들의 우선순위를 고려하여 실행순서와 실행시간을 결정하고, 각 쓰레드들은 작성된 스케줄에 따라 자신의 순서가 되면 지정된 사건동안 작업을 수행한다.<br>
	 *     이 때 주어진 시간동안 작업을 마치지 못한 쓰레드는 다시 자신의 차례가 돌아올 때까지 대기상태로 있게 되며, 작업을 마친 쓰레드, 즉 run()의 수행이 종료된 쓰레드는 호출스택이 모두 비워지면서 이 쓰레드가 사용하던 호출스택은 사라진다.<br>
	 *     이는 마치 자바프로그램을 실행하면 호출스택이 생성되고 main메서드가 처음으로 호출되고, main메서드가 종료되면 호출스택이 비워지면서 프로그램도 종료되는 것과 같다.
	 * </p>
	 */
	class Memo01{}

	/**
	 * <p>
	 *     <h5>main쓰레드</h5><br>
	 *     main메서드의 작업을 수행하는 것도 쓰레드이며, 이를 main쓰레드 라고 한다.<br>
	 *     프로그램을 실행하면 기본적으로 하나의 쓰레드(일꾼)를 생성하고, 그 쓰레드가 main메서드를 호출해서 작업이 수행되도록 하는 것이다.<br>
	 *     main메서드가 수행을 마쳤다하더라도 다른 쓰레드가 아직 작업을 마치지 않은 상태라면 프로그램이 종료되지 않는다.<br>
	 *     <b>실행 중인 사용자 쓰레드가 하나도 없을 때 프로그램은 종료된다.</b><br>
	 *     쓰레드는 '사용자 쓰레드(user thread)'와 '데몬 쓰레드(daemon thread)', 두 종류가 있다.
	 * </p>
	 */
	class Memo02{}

	private static class ThreadEx1 {

		public static void main(String[] args) {
			ThreadEx1_1 t1 = new ThreadEx1_1();
			t1.start();
		}

	}

	private static class ThreadEx1_1 extends Thread {

		@Override
		public void run() {
			throwException();
		}

		private void throwException() {

			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private static class ThreadEx2 {
		public static void main(String[] args) {
			ThreadEx2_1 t1 = new ThreadEx2_1();
			t1.run();
		}
	}

	private static class ThreadEx2_1 extends Thread {
		@Override
		public void run() {
			throwException();
		}

		private void throwException() {

			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
