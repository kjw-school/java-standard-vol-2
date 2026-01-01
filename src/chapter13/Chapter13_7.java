package chapter13;

import static java.lang.Thread.*;

import java.util.Iterator;
import java.util.Map;

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
					sleep(1000);
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
					sleep(3 * 1000); // 3초마다
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

	/**
	 * getAllStackTraces()를 이용하면 실행 중 또는 대기상태, 즉 작업이 완료되지 않은 모든 쓰레드의 호출스택을 출력할 수 있다.<br>
	 * 결과를 보면 getAllStackTraces()가 호출되었을 때, 새로 생성한 Thread1, Thread2를 포함해서 모두 6개의 쓰레드가 실행 중 또는 대기상태에 있다는 것을 알 수 있다.<br>
	 * 프로그램을 실행하면, JVM은 가비지컬렉션, 이벤트처리, 그래픽처리와 같이 프로그램이 실행되는 데 필요한 보조작업을 수행하는 데몬 쓰레드들을 자동적으로 생성해서 실행시킨다.<br>
	 * 그리고 이 들은 'system쓰레드 그룹' 또는 'main쓰레드 그룹'에 속한다.<br>
	 * <small>※GUI는 Graphic User Interface의 약자로 Java에서는 AWT나 Swing을 이용해서 GUI를 가진 프로그램을 작성할 수 있다. JavaFx라는 보다 발전된 GUI기술도 있다.</small>
	 */
	static class ThreadEx02 {

		public static void main(String[] args) {

			ThreadEx02_1 t1 = new ThreadEx02_1("Thread1");
			ThreadEx02_2 t2 = new ThreadEx02_2("Thread2");
			t1.start();
			t2.start();

		}

	}

	static class ThreadEx02_1 extends Thread {

		ThreadEx02_1(String name) {
			super(name);
		}

		public void run() {
			try {
				sleep(5 * 1000); // 5초 동안 기다린다.
			} catch (InterruptedException e) {
			}
		}

	}

	static class ThreadEx02_2 extends Thread {

		ThreadEx02_2(String name) {
			super(name);
		}

		public void run() {
			Map map = getAllStackTraces();
			Iterator it = map.keySet().iterator();

			int x = 0;
			while (it.hasNext()) {
				Object obj = it.next();
				Thread t = (Thread)obj;
				StackTraceElement[] ste = (StackTraceElement[])(map.get(obj));

				System.out.println(
					"[" + ++x + "] name : " + t.getName() + ", group : " + t.getThreadGroup().getName() + ", daemon : "
						+ t.isDaemon());

				for (int i = 0; i < ste.length; i++) {
					System.out.println(ste[i]);
				}

				System.out.println();

			}
		}

	}

}
