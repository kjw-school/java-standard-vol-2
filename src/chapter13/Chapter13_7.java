package chapter13;

/**
 * <h1>7. 데몬 쓰레드(daemon thread)</h1>
 */
public class Chapter13_7 {

	/**
	 * 데몬 쓰레드는 다른 일반 쓰레드(데몬 쓰레드가 아닌 쓰레드)의 작업을 돕는 보조적인 역할을 수행하는 쓰레드이다. <br>
	 * 일반 쓰레드가 모두 종료되면 데몬 쓰레드는 강제적으로 자동 종료되는데, 그 이유는 데몬 쓰레드는 일반 쓰레드의 보족역할을 수행하므로<br>
	 * 일반 쓰레드가 모두 종료되고 나면 데몬 쓰레드의 존재의 의미가 없기 때문이다.<br>
	 * 이 점을 제외하고는 데몬 쓰레드와 일반 쓰레드는 다르지 않다. 데몬 쓰레드의 예로는 가비지 컬렉터, 워드프로세서의 자동저장, 화면자동갱신 등이 있다.<br>
	 * 데몬 쓰레드는 무한루프의 조건문을 이용해서 실행 후 대기하고 있다가 특정 조건이 만족되면 작업을 수행하고 다시 대기하도록 작성한다.<br>
	 * 데몬 쓰레드는 이반 쓰레드의 작성방법과 실행방법이 같으며 다만 쓰레드를 생성한 다음 실행하기 전에 setDaemon(true)를 호출하기만 하면 된다.<br>
	 * 데몬 쓰레드가 생성한 쓰레드는 자동적으로 데몬 쓰레드가 된다.<br>
	 * <b>boolean isDaemon()</b> - 쓰레드가 데몬 쓰레드인지 확인한다. 데몬 쓰레드이면 true를 반환한다.<br>
	 * <b>void setDaemon(boolean on)</b> - 쓰레드를 데몬 쓰레드로 또는 사용자 쓰레드로 변경한다. 매개변수 on의 값을 true로 저장하면 데몬 쓰레드가 된다.<br>
	 */
	class Memo01 {
	}

	static class ThreadEx01 implements Runnable {
		static boolean autoSave = false;

		public static void main(String[] args) {
			Thread t = new Thread(new ThreadEx01());
			t.setDaemon(true); // 이 부분이 없으면 종료되지 않는다.
			t.start();

			for (int i = 1; i <= 10; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

				}
				System.out.println(i);

				if (i == 5)
					autoSave = true;
			}

			System.out.println("프로그램을 종료합니다.");

		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(3 * 1000); // 3초마다
				} catch (InterruptedException e) {
				}

				// autoSave의 값이 true이면 autoSave()를 호출한다.
				if (autoSave) {
					autoSave();
				}
			}
		}

		public void autoSave() {
			System.out.println("작업파일이 자동저장되었습니다.");
		}

	}

}
