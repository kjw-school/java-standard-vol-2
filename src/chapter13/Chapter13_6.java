package chapter13;

/**
 * <h1>6. 쓰레드 그룹(thread group)</h1>
 */
public class Chapter13_6 {

	/**
	 * 쓰레드 그룹은 서로 관련된 쓰레드를 그룹으로 다루기 위한 것으로, 쓰레드 그룹을 생성해서 쓰레드를 그룹으로 묶어서 관리할 수 있다.<br>
	 * 쓰레드 그룹에 다른 쓰레드 그룹을 포함 시킬 수 있다. 사실 쓰레드 그룹은 보안상의 이유로 도입된 개념으로, 자신이 속한 쓰레드 그룹이나 하위 쓰레드 그룹을 변경할 수 있지만<br>
	 * 다른 쓰레드 그룹의 쓰레드를 변경할 수는 없다.<br>
	 * 쓰레드를 쓰레드 그룹에 포함시키려면 Thread의 생성자를 이용해야 한다.<br>
	 * <code>
	 *     Thread(ThreadGroup group, String name)<br>
	 *     Thread(ThreadGroup group, Runnable target)<br>
	 *     Thread(ThreadGroup group, Runnable target, String name)<br>
	 *     Thread(ThreadGroup group, Runnable target, String name, long stackSize)
	 * </code>
	 * <br>
	 * 모든 쓰레드는 반드시 쓰레드 그룹이 포함되어 있어야 하기 때문에, 위와 같이 쓰레드 그룹을 지정하는 생성자를 사용하지 않은 쓰레드는 기본적으로 자신을 생성한 쓰레드와 같은 쓰레드 그룹에 속하게 된다.
	 * <br>
	 * 자바 어플리케이션이 실행되면, JVM은 main과 system이라는 쓰레드 그룹을 만들고 JVM운영에 필요한 쓰레드들을 생성해서 이 쓰레드 그룹에 포함시킨다.<br>
	 * 우리가 생성하는 모든 쓰레드 그룹은 main쓰레드 그룹의 하위 쓰레드 그룹이 되며, 쓰레드 그룹을 지정하지 않고 생성한 쓰레드는 자동적으로 main쓰레드 그룹에 속하게 된다.<br>
	 * <code>
	 *     <b>ThreadGroup getThreadGroup()</b> 쓰레드 자신이 속한 쓰레드 그룹을 반환한다.<br>
	 *     <b>void uncaughtException(Thread t, Throwable e)</b> 쓰레드 그룹의 쓰레드가 처리되지 않은 예외에 의해 실행이 종료되었을 때, JVM에 의해 이 메서드가 자동적으로 호출된다.
	 * </code>
	 */
	class Chapter13_6_Memo01 {
	}

	/**
	 * 새로 생성한 모든 쓰레드 그룹은 main쓰레드 그룹의 하위 쓰레드 그룹으로 포함되어 있다.<br>
	 * setMaxPriority()는 쓰레드가 쓰레드 그룹에 추가되기 이전에 호출되어야 한다.<br>
	 * 쓰레드 그룹 grp1의 최대우선순위를 3으로 했기 때문에, 후에 여기에 속하게 된 쓰레드 그룹과 쓰레드가 영향을 받았다.<br>
	 * 참조변수 없이 쓰레드를 생성해서 바로 실행시켰는데, 그렇다고 해서 이 쓰레드가 가비지 컬렉터의 제거 대상이 되지는 않는다. 이 쓰레드의 참조가 ThreadGroup에 저장되어 있기 때문이다.<br>
	 * <small>※쓰레드 그룹을 지정하지 않은 쓰레드는 자동적으로 main쓰레드 그룹에 속하게 된다.</small>
	 */
	static class ThreadEx1 {
		public static void main(String[] args) {

			ThreadGroup main = Thread.currentThread().getThreadGroup();
			ThreadGroup grp1 = new ThreadGroup("Group1");
			ThreadGroup grp2 = new ThreadGroup("Group2");

			// ThreadGroup(ThreadGroup parent, String name)
			ThreadGroup subGrp1 = new ThreadGroup(grp1, "SubGroup1");

			grp1.setMaxPriority(3); // 쓰레드 그룹 grp1의 최대우선순위를 3으로 변경.

			Runnable r = new Runnable() {
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
			};

			// Thread(ThreadGroup tg, Runnable r, String name)
			new Thread(grp1, r, "th1").start();
			new Thread(subGrp1, r, "th2").start();
			new Thread(grp2, r, "th3").start();

			System.out.println(
				">>List Of ThreadGroup : " + main.getName() + ", Active ThreadGroup: " + main.activeGroupCount()
					+ ", Active Thread: " + main.activeCount());
			main.list();
		}
	}

}
