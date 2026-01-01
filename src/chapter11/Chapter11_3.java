package chapter11;

import java.util.*;

/**
 * <h1>11.3 LinkedList</h1>
 */
public class Chapter11_3 {

    /**
     * 배열은 가장 기본적인 형태의 자료구조로 구조가 간단하며 사용하기 쉽고 데이터를 읽어오는데 걸리는 시간(접근시간, access time)이 가장 빠
     * 르다는 장점을 가지고 있지만 다음과 같은 단점을 가지고 있다.<br>
     * 1. 크기를 변경할 수 없다. <br>
     * - 크기를 변경할 수 없으므로 새로운 배열을 생성해서 데이터를 복사해야한다. <br>
     * - 실행속도를 향상시키기 위해서는 충분히 큰 크기의 배열을 생성해야 하므로 메모리가 낭비된다. <br>
     * 2. 비순차적인 데이터의 추가 또는 삭제에 시간이 많이 걸린다. <br>
     * - 차례대로 데이터를 추가하고 마지막에서부터 데이터를 삭제하는 것은 빠르지만, <br>
     * - 배열의 중간에 데이터를 추가하려면, 빈자리를 만들기 위해 다른 데이터들을 복사해서 이동해야한다. <br>
     * 이러한 배열의 단점을 보완하기 위해서 링크드 리스트(linked list)라는 자료구조가 고안되었다. 배열은 모든 데이터가 연속적으로 존재하지만 <br>
     * 링크드 리스트는 불연속적으로 존재하는 데이터를 서로 연결(link)한 형태로 구성되어있다. <br>
     * 링크드 리스트의 각 요소(node)들은 자신과 연결된 다음 요소에 대한 참조(주소값)와 데이터로 구성되어 있다. <br>
     * class node { <br>
     *     Node next; // 다음 요소의 주소를 저장 <br>
     *     Object obj; // 데이터를 저장 <br>
     * } <br>
     * 링크드 리스트에서의 데이터 삭제는 간단하다. 삭제하고자 하는 요소의 이전요소가 삭제 하고자 하는 요소의 다음 요소를 참조하도록 변경하기만 <br>
     * 하면 된다. 단 하나의 참조만 변경하면 삭제가 이루어지는 것이다. 배열처럼 데이터를 이동하기 위해 복사하는 과정이 없기 때문에 처리속도가 <br>
     * 매우 빠르다.<br>
     * 새로운 데이터를 추가할 때는 새로운 요소를 생성한 다음 추가하고자 하는 위치의 이전 요소의 참조를 새로운 요소에 대한 참조로 변경해주고,<br>
     * 새로운 요소가 그 다음 요소를 참조하도록 변경하기만 하면 되므로 처리속도가 매우 빠르다.<br>
     * 링크드 리스트는 이동방향이 단방향이기 때문에 다음 요소에 대한 접근은 쉽지만 이전요소에 대한 접근은 어렵다. 이 점을 보완한 것이 더블<br>
     * 링크드 리스트(이중 연결리스트, doubly linked list)이다.<br>
     * 더블 링크드 리스트는 단순히 링크드 리스트에 참조변수를 하나 더 추가하여 다음 요소에 대한 참조뿐 아니라 이전 요소에 대한 참조가 가능<br>
     * 하도록 했을 뿐, 그외에는 링크드 리스트와 같다.<br>
     * class Node {<br>
     *     Node next; // 다음 요소의 주소를 저장<br>
     *     Node previous; // 이전 요소의 주소를 저장<br>
     *     Object obj; // 데이터를 저장<br>
     * }<br>
     * 더블 링크드 리스트의 접근성을 보다 향상시킨 것이 '더블 써큘러 링크드 리스트(이중 원형 연결리스트, doubly circular linked list)'<br>
     * 인데, 단순히 더블 링크드 리스트의 첫 번째 요소와 마지막 요소를 서로 연결시킨 것이다. 이렇게 하면, 마지막 요소의 다음요소가 첫 번째<br>
     * 요소가 되고, 첫 번째 요소의 이전 요소가 마지막 요소가 된다.<br>
     * 실제로 LinkedList클래스는 이름과 달리 '링크드 리스트'가 아닌 '더블 링크드 리스트'로 구현되어 있는데, 이는 링크드 리스트의 단점인<br>
     * 낮은 접근성(accessbility)을 높이기 위한 것이다.<br>
     * LinkedList 역시 List인터페이스를 구현했기 때문에 ArrayList와 내부구현방법만 다를뿐 제공하는 메서드의 종류와 기능은 거의 같기 때문<br>
     * 에 이에대한 설명은 따로 필요없을 것 같다.<br>
     */
    static class ArrayListLinkedListTest {

        public static void main(String[] args) {

            //추가할 데이터의 개수를 고려하여 충분히 잡아야한다.
            ArrayList al = new ArrayList(2000000);
            LinkedList ll = new LinkedList();

            System.out.println("= 순차적으로 추가하기 =");
            System.out.println("ArrayList :"+add1(al));
            System.out.println("LinkedList :"+add1(ll));
            System.out.println();
            System.out.println("= 중간에 추가하기 =");
            System.out.println("ArrayList :"+add2(al));
            System.out.println("LinkedList :"+add2(ll));
            System.out.println();
            System.out.println("= 중간에서 삭제하기 =");
            System.out.println("ArrayList :"+remove2(al));
            System.out.println("LinkedList :"+remove2(ll));
            System.out.println();
            System.out.println("= 순차적으로 삭제하기 =");
            System.out.println("ArrayList :"+remove1(al));
            System.out.println("LinkedList :"+remove1(ll));

        }

        public static long add1(List list) {

            long start = System.currentTimeMillis();
            for(int i =0; i < 1000000; i++) list.add(i+"");
            long end = System.currentTimeMillis();
            return end - start;

        }

        public static long add2(List list) {

            long start = System.currentTimeMillis();
            for(int i =0; i < 10000; i++) list.add(500, "X");
            long end = System.currentTimeMillis();
            return end - start;

        }

        public static long remove1(List list) {
            long start = System.currentTimeMillis();
            for(int i=list.size()-1; i >= 0; i--) list.remove(i);
            long end = System.currentTimeMillis();
            return end - start;
        }

        public static long remove2(List list) {
            long start = System.currentTimeMillis();
            for(int i= 0 ; i < 10000; i++) list.remove(i);
            long end = System.currentTimeMillis();
            return end - start;
        }

    }

    /**
     * 결론1. <b>순차적으로 추가/삭제하는 경우에는 ArrayList가 LinkedList보다 빠르다.</b><br>
     * 만일 ArrayList의 크기가 충분하지 않으면, 새로운 크기의 ArrayList를 생성하고 데이터를 복사하는 일이 발생하게 되므로 순차적으로 데이터를 추가해도 ArrayList보다 LinkedList가 더 빠를 수 있다.<br>
     * 순차적으로 삭제한다는 것은 마지막 데이터로부터 역순으로 삭제해나간다는 것을 의미하며, ArrayList는 마지막 데이터부터 삭제할 경우 각요소들의 재배치가 필요허지 않기 때문에 상당히 빠르다. 단지 마지막 요소의 값을 null로만 바꾸면 되니까.<br>
     * 결론2. <b>중간 데이터를 추가/삭제하는 경우에는 LinkedList가 ArrayList보다 빠르다.</b><br>
     * 중간 요소를 추가 또는 삭제하는 경우, LinkedList는 각 요소간의 연결만 변경해주면 되기 때문에 처리속도가 상당히 빠르다. 반면에 ArrayList는 각 요소들을 재배치하여 추가할 공간을 확보하거나 빈 공간을 채워야하기 때문에 처리속도가 늦다.
     * <br>
     * 배열의 경우 만일 인덱스가 n인 요소의 값을 얻어 오고자 한다면 단순히 아래와 같은 수식을 계산함으로써 해결된다.<br>
     * <b>인덱스가 n인 데이터의 주소 = 배열의 주소 + n * 데이터 타입의 크기</b>
     */
    class Memo01{}

    /**
     * <table border="1">
     *     <tr>
     *         <th>컬렉션</th>
     *         <th>읽기(접근시간)</th>
     *         <th>추가/삭제</th>
     *         <th>비고</th>
     *     </tr>
     *     <tr>
     *         <td>ArrayList</td>
     *         <td>빠르다</td>
     *         <td>느리다</td>
     *         <td>순차적인 추가삭제는 더 빠름, 비효율적인 메모리 사용</td>
     *     </tr>
     *     <tr>
     *         <td>LinkedList</td>
     *         <td>느리다</td>
     *         <td>빠르다</td>
     *         <td>데이터가 많을수록 접근성이 떨어짐</td>
     *     </tr>
     * </table>
     */
    static class ArrayListLinkedListTest2 {

        public static void main(String[] args) {
            ArrayList a1 = new ArrayList(1000000);
            LinkedList l1 = new LinkedList();
            add(a1);
            add(l1);

            System.out.println("= 접근시간 테스트 =");
            System.out.println("ArrayList : " + access(a1));
            System.out.println("LinkedList : " + access(l1));

        }

        public static void add(List list) {
            for(int i = 0; i < 100000; i++) list.add(i+"");
        }

        public static long access(List list) {
            long start = System.currentTimeMillis();
            for(int i = 0; i < 1000; i++) list.get(i);
            long end = System.currentTimeMillis();
            return end - start;
        }

    }


}
