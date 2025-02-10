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
	class Chapter13_6_Memo01{}

	static class ThreadEx1 {

	}

}
