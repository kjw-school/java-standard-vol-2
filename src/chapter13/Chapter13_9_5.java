package chapter13;

/**
 * <h1>9.5 fork & join 프레임웍</h1><br>
 * JDK1.7부터 'fork & join 프레임웍'이 추가되었고, 이 프레임웍은 하나의 작업을 작은 단위로 나눠서 여러 쓰레드가 동시에 처리하는 것을 쉽게 만들어준다.<br>
 * 먼저 수행할 작업에 따라 RecursiveAction과 RecursiveTask, 두 클래스 중에서 하나를 상속받아 구현해야한다.
 * <b>RecursiveAction</b> 반환값이 없는 작업을 구현할 때 사용<br>
 * <b>RecursiveTask</b> 반환값이 있는 작업을 구현할 때 사용<br>
 * 두 클래스 모두 compute()라는 추상 메서드를 가지고 있는데, 우리는 상속을 통해 이 추상 메서드를 구현하기만 하면 된다.
 * <pre><code>
 *     public abstract class RecursiveAction extends ForkJoinTask<Void> {
 *      	...
 *      	protected abstract void compute(); // 상속을 통해 이 메서드를 구현해야 한다.
 *      	...
 *     }
 *
 *     public abstract class RecursiveTask<V> extends ForkJoinTask<V> {
 *         ...
 *         V result;
 *         protected abstract void compute(); // 상속을 통해 이 메서드를 구현해야 한다.
 *     }
 * </code></pre>
 * 그 다음에는 쓰레드풀과 수행할 작업을 생성하고, invoke()로 작업을 시작한다. 쓰레드를 시작할 때 run()이 아니라 start()를 호출하는 것처럼, fork&join프레임웍으로 수행할 작업도 compute()가 아닌 invoke()로 시작한다.<br>
 * ForkJoinPool은 fork&join프레임웍에서 제공하는 쓰레드 풀(thread pool)로, 지정된 수의 쓰레드를 생성해서 미리 만들어 놓고 반복해서 재사용할 수 있게 한다.<br>
 * 그리고 쓰레드를 반복해서 생성하지 않아도 된다는 장점과 너무 많은 쓰레드가 생성되어 성능이 저하되는 것을 막아준다는 장점이 있다.<br>
 * 쓰레드 풀은 쓰레드가 수행해야하는 작업이 담긴 큐를 제공하며, 각 쓰레드는 자신의 작업 큐에 담긴 작업을 순서대로 처리한다.<br>
 * <small>※쓰레드 풀은 기본적으로 코어의 개수와 동일한 개수의 쓰레드를 생성한다.</small>
 */
public class Chapter13_9_5 {

	/**
	 * <h5>compute()의 구현</h5><br>
	 * compute()를 구현할 때는 수행할 작업 외에도 작업을 어떻게 나눌 것인가에 대해서도 알려줘야 한다.
	 * <pre><code>
	 *	public long compute() {
	 *
	 * 		long size = to - from + 1; // from <= i <= to
	 *
	 * 		if (size <= 5) // 더할 숫자가 5개 이하면
	 * 			return sum; // 숫자의 합을 반환. sum()은 from부터 to까지의 수를 더해서 반환
	 *
	 * 		// 범위를 반으로 나눠서 두 개의 작업을 생성
	 * 		long half = (from + to) / 2;
	 *
	 * 		SumTask leftSum = new SumTask(from, half);
	 * 		SumTask rightSum = new SumTask(half + 1, to);
	 *
	 * 		leftSum.fork(); // 작업(leftSum)을 작업 큐에 넣는다.
	 *
	 * 		return rightSum.compute() + leftSum.join();
	 *
	 * }
	 * </code></pre>
	 * 여기서는 지정된 범위를 절반으로 나누어서 나눠진 범위의 합을 계산하기 위한 새로운 SumTask를 생성하는데, 이 과정은 작업이 더 이상 나눠질 수 없을 때까지, size의 값이 5보다 작거나 같을 때까지, 반복된다<br>
	 * <small>※ compute()의 구조는 일반적인 재귀호출 메서드와 동일하다.</small>
	 */
	class Memo01 {
	}

	/**
	 * <h5>다른 쓰레드의 작업 훔쳐오기</h5><br>
	 * fork()가 호출되어 작업 큐에 추가된 작업 역시, compute()에 의해 더 이상 나눌 수 없을때까지 반복해서 나뉘고, 자신의 작업 큐가 비어있는 쓰레드는 다른 쓰레드의 작업 큐에서 작업을 가져와서 수행한다.<br>
	 * 이것을 작업 훔쳐오기(work stealing)라고 하며, 이 과정은 모두 쓰레드풀에 의해 자동적으로 이루어진다.<br>
	 * <small>※작업의 크기를 충분히 작게 해야 각 쓰레드가 골고루 작업을 나눠가질 수 있다.</small>
	 */
	class Memo02 {
	}

	/**
	 * <h5>fork()와 join()</h5><br>
	 * fork()는 작업을 쓰레드의 작업 큐에 넣는 것이고, 작업 큐에 들어간 작업은 더 이상 나눌수 없을 때까지 나뉜다.<br>
	 * 즉 compute()로 나누고 fork()로 작업 큐에 넣는 작업이 계속해서 반복된다. 그리고 나눠진 작업은 각 쓰레드가 골고루 나눠서 처리하고, 작업의 결과는 join()을 호출해서 얻을 수 있다.<br>
	 * fork()와 join()의 중요한 차이점이 하나 있는데, 그것은 바로 fork()는 비동기 메서드(asynchronous method)이고, join()은 동기 메서드(synchronous method)라는 것이다.<br>
	 * <b>fork()</b> 해당 작업을 쓰레드 풀의 작업 큐에 넣는다. 비동기 메서드<br>
	 * <b>join()</b> 해당 작업의 수행이 끝날 때가지 기다렸다가, 수행이 끝나면 그 결과를 반환한다. 동기 메서드<br>
	 * 비동기 메서드는 일반적인 메서드와 달리 메서드를 호출만 할 뿐, 그 결과를 기다리지 않는다.<br>
	 * (내부적으로는 다른 쓰레드에게 작업을 수행하도록 지시만 하고 결과를 기다리지 않고 돌아오는 것이다.)
	 */
	class Memo03 {
	}

}
