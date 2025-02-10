package chapter13;

/**
 * <h1>5. 쓰레드의 우선순위</h1>
 */
public class Chapter13_5 {

	/**
	 * <p>
	 *		쓰레드는 우선순위(priority)라는 속성(멤버변수)을 가지고 있는데, 이 우선순위의 값에 따라 쓰레드가 얻는 실행시간이 달라진다.<br>
	 *		쓰레드가 수행하는 작업의 중요도에 따라 쓰레드의 우선순위를 서로 다르게 지정하여 특정 쓰레드가 더 많은 작업시간을 갖도록 할 수 있다.
	 * </p>
	 */
	class Memo01{}

	/**
	 * <p>
	 *     <h5>쓰레드의 우선수위 지정하기</h5><br>
	 *     <b>void setPriority(int newPriority)</b> 쓰레드의 우선순위를 지정한 값으로 변경한다.<br>
	 *     <b>int getPriority()</b> 쓰레드의 우선순위를 반환한다.<br>
	 *     쓰레드가 가질 수 있는 우선순위의 범위는 1~10이며 숫자가 높을수록 우선순위가 높다.<br>
	 *     한 가지 더 알아두어야 할 것은 쓰레드의 우선순위는 쓰레드를 생성한 쓰레드로부터 상속받는다는 것이다.<br>
	 *     main메서드를 수행하는 쓰레드는 우선순위가 5이므로 main메서드내에서 생성하는 쓰레드의 우선순위는 자동적으로 5가 된다.<br>
	 *     쓰레드를 실행하기 전에만 우선순위를 변경할 수 있다는 것을 기억하자.<br>
	 *     우선순위가 같은 경우 각 쓰레드에게 거의 같은 양의 실행시간이 주어지지만, 우선순위가 다르다면 우선순위가 높은 t1에게 상대적으로 t2보다<br>
	 *     더 많은 양의 실행시간이 주어지고 결과적으로 작업 A가 B보다 더 빨리 완료될 수 있다.<br>
	 *     멀티코어에서는 쓰레드의 우선순위에 따른 차이가 거의 아니 전혀 없었다.<br>
	 *     멀티코어라 해도 OS마다 다른 방식으로 스케쥴링하기 때문에, 어떤 OS에서 실행하느냐에 따라 다른 결과를 얻을 수 있다.<br>
	 *     자바는 쓰레드가 우선순위에 따라 어떻게 다르게 처리되어야 하는지에 대해 강제하지 않으므로 쓰레드의 우선순위와 관련된 구현이 JVM마다 다를 수 있기 때문이다.<br>
	 *     만일 확인한다 하더라도 OS의 스케쥴러에 종속적이라서 어느 정도 예측만 가능한 정도일 뿐 정확히 알 수는 없다.<br>
	 *     차라리 쓰레드에 우선순위를 부여하는 대신 작업에 우선순위를 두어 PriorityQueue에 저장해 놓고, 우선순위가 높은 작업이 먼저 처리되도록 하는 것이 나을 수 있다.
	 * </p>
	 */
	class Memo02{}

	static class TheadEx1 {

		public static void main(String[] args) {
			ThreadEx1_1 t1 = new ThreadEx1_1();
			ThreadEx1_2 t2 = new ThreadEx1_2();

			t2.setPriority(7);

			System.out.println("priority of t1(-) : " + t1.getPriority());
			System.out.println("priority of t2(|) : " + t2.getPriority());
			t1.start();
			t2.start();
		}

	}

	static class ThreadEx1_1 extends Thread {

		@Override
		public void run() {
			for(int i = 0; i < 300; i++) {
				System.out.print("-");
				for(int x = 0; x < 10000000; x++) {

				}
			}
		}

	}

	static class ThreadEx1_2 extends Thread {
		public void run() {
			for(int i = 0; i < 300; i++) {
				System.out.print("|");
				for(int x = 0; x < 10000000; x++) {

				}
			}
		}
	}



}
