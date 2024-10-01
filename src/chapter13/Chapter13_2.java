package chapter13;

/**
 * 2. 쓰레드의 구현과 실행
 */
public class Chapter13_2 {

    /**
     * 쓰레드를 구현하는 방법은 Thread클래스를 상속받는 방법과 Runnable인터페이스를 구
     * 현하는 방법, 모두 두 가지가 있다. Thread클래스를 상속받으면 다른 클래스를 상속받을
     * 수 없기 때문에, Runnable인터페이스를 구현하는
     * 방법이 일반적이다.
     * 1. Thread클래스를 상속
     * class Mythread extends Thread {
     *     public void run() { 작업내용 } Thread클래스의 run()을 오버라이딩
     * }
     * 2. Runnable인터페이스를 구현
     * class MyThread implements Runnable {
     *     public void run() { 작업내용 } Runnable인터페이스의 run()을 구현
     * }
     */

    static class Ex1 {
        public static void main(String[] args) {

            Ex1_1 t1 = new Ex1_1();

            Runnable r = new Ex1_2();
            Thread t2 = new Thread(r); // 생성자 Thread(Runnable target)

            t1.start();
            t2.start();

        }
    }

    static class Ex1_1 extends Thread{
       public void run() {
           for(int i = 0; i < 5; i++) {
               System.out.println("Thread Ex1_1 = " + getName()); // 조상인 Thread의 getName()을 호출
           }
       }
    }

    static class Ex1_2 implements Runnable {
        public void run() {
            for(int i = 0; i < 5; i++) {
                // Thread.currentThread() - 현재 실행중인 Thread를 반환한다.
                System.out.println("Thread Ex1_2 = " + Thread.currentThread().getName());
            }
        }
    }

    /**
     * Thread클래스를 상속받은 경우와 Runnable인터페이스를 구현한 경우의 인스턴스 생성
     * 방법이 다르다.
     * Ex1_1 t1 = new Ex1_1(); Thread의 자손 클래스의 인스턴스를 생성
     *
     * Runnable r = new Ex1_2(); Runnable을 구현한 클래스의 인스턴스를 생성
     * Thread t2 = new Thread(r); 생성자 Thread(Runnable target)
     *
     * Thread t2 = new Thread(new Ex1_2());
     * Runnable인터페이스를 구현한 경우, Runnable인터페이스를 구현한 클래스의 인스턴스
     * 를 생성한 다음, 이 인스턴스를 Thread클래스의 생성자의 매개변수로 제공해야 한다.
     * Thread클래스를 상속받으면, 자손 클래스에서 조상인 Thread클래스의 메서드를 직접 호출할 수 있지만, Runnable을 구현하면 Thread클래
     * 스의 static메서드인 currentThread()를 호출하여 쓰레드에 대한 참조를 얻어 와야만 호출이 가능하다.
     * static Thread currentThread() - 현재 실행중인 쓰레드의 참조를 반환한다.
     * String getName() - 쓰레드의 이름을 반환한다.
     * 쓰레드의 실행 - start()
     * 쓰레드를 생성했다고 해서 자동으로 실행되는 것은 아니다. start()를 호출해야만 쓰레드가 실행된다.
     * t1.start(); // 쓰레드 t1을 실행시킨다.
     * t2.start(); // 쓰레드 t2를 실행시킨다.
     * 사실은 start()가 호출되었다고 해서 바로 실행되는 것이 아니라. 일단 실행대기 상태에 있다가 자신의 차례가 되어야 실행된다. 물론 실행
     * 대기중인 쓰레드가 하나도 없으면 곧바로 실행상태가 된다.
     * ※ 쓰레드의 실행순서는 OS의 스케쥴러가 작성한 스케쥴에 의해 결정된다.
     * 한 가지 더 알아 두어야 하는 것은 한 번 실행이 종료된 쓰레드는 다시 실행할 수 없다는 것이다. 즉, 하나의 쓰레드에 대해 start()가
     * 한 번만 호출될 수 있다는 뜻이다. 만일 쓰레드의 작업을 한 번 더 수행해야 한다면 아래의 오른쪽 코드와 같이 새로운 쓰레드를 생성한
     * 다음에 start()를 호출해야 한다. 하나의 쓰레드에 대해 start()를 두 번 이상 호출하면 실행시에 IllegalThreadStateException이 발
     * 생한다.
     * ThreadEx1_1 = new ThreadEx1_1();    ThreadEx1_1 t1 = new ThreadEx1_1();
     * t1.start();                      →  t1.start();
     * t1.start(); // 예외 발생             t1 = new ThreadEx1_1(); // 다시 생성
     *                                     t1.start(); // OK
     */

}
