package chapter13;

/**
 * 2. 쓰레드의 구현과 실행
 */
public class Chapter13_2 {

    /**
     * <p>
     *     쓰레드를 구현하는 방법은 {@code Thread}클래스를 상속받는 방법과 {@code Runnable}인터페이스를 구현하는 방법, 모두 두 가지가 있다.<br>
     *     Thread클래스를 상속받으면 다른 클래스를 상속받을 수 없기 떄문에 , {@code Runnable}인터페이스를 구현하는 방법이 일반적이다.<br>
     *     <b>1. Thread</b>클래스를 상속
     *     <blockquote>
     *         class MyThread extends Thread {
     *             public void run() { 작업내용 } // {@code Thread}클래스의 run()을 오버라이딩
     *         }
     *     </blockquote><br>
     *     <b>2. Runnable</b>인터페이스를 구현
     *     <blockquote>
     *         class MyThread implements Runnable {
     *             public void run() { 작업내용 } // {@code Runnable}인터페이스의 run()을 구현
     *         }
     *     </blockquote><br>
     *     {@code Runnable}인터페이스는 오로지 run()만 정의되어 있는 간단한 인터페이스이다.<br>
     *     {@code Runnable}인터페이스를 구현하기 위해서 해야 할 일은 추상메서드인 run()의 몸통{}을 만들어 주는 것뿐이다.<br>
     *     쓰레드를 구현한다는 것은, 위의 두 방법 중 어떤 것을 선택하든지, 그저 쓰레드를 통해 작업하고자 하는 내용으로 run()의 몸통{}을 채
     *     우는 것일 뿐이다.
     * </p>
     */
    static class Memo01 {}

    /**
     * <p>
     *     {@code Thread}클래스를 상속받은 경우와 {@code Runnable}인터페이스를 구현한 경우의 인스턴스 생성방법이 다르다.<br>
     *     <blockquote>
     *         ThreadEx01_1 = t1 = new ThreadEx01_1(); // {@code Thread}의 자손클래스의 인스턴스를 생성<br>
     *         Runnable r = new ThreadEx01_2(); // {@code Runnable}을 구현한 클래스의 인스턴스를 생성<br>
     *         Thread t2 = new Thread(r); // 생성자 Thread(Runnable target)<br>
     *         Thread t2 = new Thread(new ThreadEx01_2()); // 위의 두 줄을 한 줄로 간단히
     *     </blockquote><br>
     *      {@code Runnable}인터페이스를 구현한 경우, {@code Runnable}인터페이스를 구현한 클래스의 인스턴스를 생성한 다음, 이 인스턴스를
     *      {@code Thread}클래스의 생성자의 매개변수로 제공해야 한다.<br>
     *      {@code Thread}클래스를 상속받으면, 자손 클래스에서 조상인 {@code Thread}클래스의 메서드를 직접 호출할 수 있지만,<br>
     *      {@code Runnable}을 구현하면 {@code Thread}클래스의 {@code static}메서드인 {@code currentThread()}를 호출하여 쓰레드에 대한 참조를 얻어 와야만 호출이 가능하다.<br>
     *      {@code static Thread currentThread() - 현재 실행중인 쓰레드의 참조를 반환한다.}<br>
     *      {@code String getName() - 쓰레드의 이름을 반환한다.}
     * </p>
     */
    static class TheadEx01 {
        public static void main(String[] args) {
            ThreadEx01_1 t1 = new ThreadEx01_1();
            Runnable r = new ThreadEx01_2();
            Thread t2 = new Thread(r); // 생성자 Thread(Runnable target)

            t1.start();
            t2.start();
        }
    }
    
    static class ThreadEx01_1 extends Thread {
        public void run() {
            for(int i = 0; i < 5; i++) {
                System.out.println(getName()); // 조상인 Thread의 getName()을 호출
            }
        
        }
    }

    static class ThreadEx01_2 implements Runnable {

        @Override
        public void run() {
            for(int i = 0; i < 5; i++) {
                // Thead.currentThread() - 현재 실행중인 Thread를 반환한다.
                System.out.println(Thread.currentThread().getName());
            }
        }

    }

    /**
     * <p>
     *     <h5>쓰레드의 실행 - start()</h5><br>
     *     쓰레드를 생성했다고 해서 자동으로 실행되는 것은 아니다. {@code start()}를 호출해야함 쓰레드가 실행된다.<br>
     *     사실은 {@code start()}가 호출되었다고 해서 바로 실행되는 것이 아니라, 일단 실행대기 상태에 있다가 자신의 차례가 되어야 실행된다.<br>
     *     물론 실행대기중인 쓰레드가 하나도 없으면 곧바로 실행상태가 된다.<br>
     *     <small>※쓰레드의 실행순서는 OS의 스케쥴러가 작성한 스케쥴에 의해 결정된다.</small><br>
     *     한가지 더 알아 두어야 하는 것은 한 번 실행이 종료된 쓰레드는 다시 실행할 수 없다는 것이다.즉,<br>
     *     하나의 쓰레드에 대해 start()가 한 번만 호출될 수 있다는 뜻이다.<br>
     *     만일 쓰레드의 작업을 한 번 더 수행해야 한다면 새로운 쓰레드를 생성한 다음에 {@code start()}를 호출해야 한다.<br>
     *     하나의 쓰레드에 대해 {@code start()}를 두 번 이상 호출하면 실행시에 {@code IllegalThreadStateException}이 발생한다.
     * </p>
     */
    static class Memo02 {}

}
