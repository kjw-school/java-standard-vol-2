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
               System.out.println(getName()); // 조상인 Thread의 getName()을 호출
           }
       }
    }

    static class Ex1_2 implements Runnable {
        public void run() {
            for(int i = 0; i < 5; i++) {
                // Thread.currentThread() - 현재 실행중인 Thread를 반환한다.
                System.out.println(Thread.currentThread().getName());
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
     */

}
