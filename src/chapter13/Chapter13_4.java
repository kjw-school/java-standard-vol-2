package chapter13;

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
	 *     싱글 코어에서 단순히 CPU만을 사용하는 계산 작업이라면 오히려 멀티쓰레드보다 싱글쓰레드로 프로그래밍하는 것이 더 효율적이다.
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

}
