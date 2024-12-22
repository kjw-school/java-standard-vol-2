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
     *      Thread클래스의 생성자의 매개변수로 제공해야 한다.
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

}
