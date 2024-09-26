package chapter11;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 1.2 ArrayList
 */
public class Chapter11_2 {

    /**
     * ArrayList는 List인터페이스를 구현하기 때문에 데이터의 저장순서가 유지되고 중복을 허용한다는 특징을 갖는다.
     * ArrayList는 기존의 Vector를 개선한 것으로 Vector와 구현원리와 기능적인 측면에서 동일하다고 할 수 있다.
     * ArrayList는 Object배열을 이용해서 데이터를 순차적으로 저장한다.
     * 배열에 더 이상 저장할
     * 공간이 없으면 보다 큰 새로운 배열을 생성해서 기존의 배열에 저장된 내용을 새로 배열로 복사한 다음에 저장된다.
     */

    static class ArrayListEx1 {
        public static void main(String[] args) {
            ArrayList list1 = new ArrayList(10);
            list1.add(new Integer(5));
            list1.add(new Integer(4));
            list1.add(new Integer(2));
            list1.add(new Integer(0));
            list1.add(new Integer(1));
            list1.add(new Integer(3));

            ArrayList list2 = new ArrayList(list1.subList(1,4));
            print(list1, list2);

            Collections.sort(list1); // list1과 list2를 정렬한다.
            Collections.sort(list2); // Collections.sort(List 1)
            print(list1, list2);

            System.out.println("list1.containsAll(list2):" + list1.containsAll(list2));

            list2.add("B");
            list2.add("C");
            list2.add(3, "A");
            print(list1, list2);

            list2.set(3,"AA");
            print(list1, list2);

            // list1에서 list2와 겹치는 부분만 남기고 나머지는 삭제한다.
            System.out.println("list1.retainAll(list2):"+list1.retainAll(list2));

            print(list1, list2);

            // list2에서 list1에 포함된 객체들을 삭제한다.
            for(int i = list2.size()-1; i >= 0; i--) {
                if(list1.contains(list2.get(i))) {
                    list2.remove(i);
                }
            }

            print(list1, list2);

        }

        static void print(ArrayList list1, ArrayList list2) {
            System.out.println("list1:"+list1);
            System.out.println("list2:"+list2);
            System.out.println("");
        }

    }

    /**
     * ArrayList는 List인터페이스를 구현했기 때문에 저장된 순서를 유지한다는 것을 알 수 있다.
     */

    static class ArrayListEx2 {
        public static void main(String[] args) {

        }


    }

}
