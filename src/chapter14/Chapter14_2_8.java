package chapter14;

/**
 * <h1>2.8 스트림의 변환</h1><br>
 * <table>
 *     <thead>
 *         <td>from</td>
 *         <td>to</td>
 *         <td>변환 메서드</td>
 *     </thead>
 *     <tbody>
 *         <tr>
 *             <td colspan="3">1. 스트림 -> 기본형 스트림</td>
 *         </tr>
 *         <tr>
 *             <td rowspan="3">Stream&lt;T&gt;</td>
 *             <td>IntStream</td>
 *             <td>mapToInt(ToIntFunction&lt;T&gt; mapper)</td>
 *         </tr>
 *         <tr>
 *             <td>LongStream</td>
 *             <td>mapToLong(ToLongFunction&lt;T&gt; mapper)</td>
 *         </tr>
 *         <tr>
 *             <td>DoubleStream</td>
 *             <td>mapToDouble(ToDoubleFunction&lt;T&gt; mapper)</td>
 *         </tr>
 *         <tr>
 *             <td colspan="3">2. 기본형 스트림 -> 스트림</td>
 *         </tr>
 *         <tr>
 *             <td>IntStream</td>
 *             <td>Stream&lt;Integer&gt;, Stream&lt;Long&gt; Stream&lt;Double&gt;</td>
 *             <td>boxed()</td>
 *         </tr>
 *         <tr>
 *             <td>LongStream</td>
 *             <td>Stream&lt;Integer&gt;, Stream&lt;Long&gt; Stream&lt;Double&gt;</td>
 *             <td>boxed()</td>
 *         </tr>
 *         <tr>
 *             <td>DoubleStream</td>
 *             <td>Stream&lt;Integer&gt;, Stream&lt;Long&gt; Stream&lt;Double&gt;, Stream&lt;U&gt;</td>
 *             <td>boxed(), mapToObj(DoubleFunction&lt;U&gt; mapper)</td>
 *         </tr>
 *         <tr>
 *             <td colspan="3">3. 기본형 스트림 -> 기본형 스트림</td>
 *         </tr>
 *         <tr>
 *			   <td>IntStream</td>
 *			   <td>LongStream, DoubleStream</td>
 *			   <td>asLongStream(), asDoubleStream</td>
 *         </tr>
 *         <tr>
 *			   <td>LongStream</td>
 *			   <td>LongStream, DoubleStream</td>
 *			   <td>asLongStream(), asDoubleStream</td>
 *         </tr>
 *         <tr>
 *			   <td>DoubleStream</td>
 *			   <td>LongStream, DoubleStream</td>
 *			   <td>asLongStream(), asDoubleStream</td>
 *         </tr>
 *         <tr>
 *             <td colspan="3">4. 스트림 -> 부분 스트림</td>
 *         </tr>
 *         <tr>
 *             <td>Stream&lt;T&gt;</td>
 *             <td>Stream&lt;T&gt;, IntStream</td>
 *             <td>skip(long n), limit(long maxSize)</td>
 *         </tr>
 *         <tr>
 *             <td>IntStream</td>
 *             <td>Stream&lt;T&gt;, IntStream</td>
 *             <td>skip(long n), limit(long maxSize)</td>
 *         </tr>
 *         <tr>
 *             <td colspan="3">5. 두 개의 스트림 -> 스트림</td>
 *         </tr>
 *         <tr>
 *             <td>Stream&lt;T&gt;, Stream&lt;T&gt;</td>
 *             <td>Stream&lt;T&gt;</td>
 *             <td>concat(Stream&lt;T&gt; a, Stream&lt;T&gt; b)</td>
 *         </tr>
 *         <tr>
 *             <td>IntStream, IntStream</td>
 *             <td>IntStream</td>
 *             <td>concat(IntStream a, IntStream b)</td>
 *         </tr>
 *         <tr>
 *             <td>LongStream, LongStream</td>
 *             <td>LongStream</td>
 *             <td>concat(LongStream a, LongStream b)</td>
 *         </tr>
 *         <tr>
 *             <td>DoubleStream, DoubleStream</td>
 *             <td>DoubleStream</td>
 *             <td>concat(DoubleStream a, DoubleStream b)</td>
 *         </tr>
 *         <tr>
 *             <td colspan="3">6. 스트림의 스트림 -> 스트림</td>
 *         </tr>
 *         <tr>
 *             <td>Stream&lt;Stream&lt;T&gt;&gt;</td>
 *             <td>Stream&lt;T&gt;</td>
 *             <td>flatMap(Function mapper)</td>
 *         </tr>
 *         <tr>
 *             <td>Stream&lt;IntStream&gt;</td>
 *             <td>IntStream</td>
 *             <td>flatMapToInt(Function mapper)</td>
 *         </tr>
 *         <tr>
 *             <td>Stream&lt;LongStream&gt;</td>
 *             <td>LongStream</td>
 *             <td>flatMapToLong(Function mapper)</td>
 *         </tr>
 *         <tr>
 *             <td>Stream&lt;DoubleStream&gt;</td>
 *             <td>DoubleStream</td>
 *             <td>flatMapToDouble(Function mapper)</td>
 *         </tr>
 *         <tr>
 *             <td colspan="3">7. 스트림 <-> 병렬 스트림</td>
 *         </tr>
 *         <tr>
 *             <td>Stream&lt;T&gt;</td>
 *             <td>Stream&lt;T&gt;, IntStream, LongStream, DoubleStream</td>
 *             <td>parallel() 스트림 -> 병렬 스트림, sequential() 병렬 스트림 -> 스트림</td>
 *         </tr>
 *         <tr>
 *             <td>IntStream</td>
 *             <td>Stream&lt;T&gt;, IntStream, LongStream, DoubleStream</td>
 *             <td>parallel() 스트림 -> 병렬 스트림, sequential() 병렬 스트림 -> 스트림</td>
 *         </tr>
 *         <tr>
 *             <td>LongStream</td>
 *             <td>Stream&lt;T&gt;, IntStream, LongStream, DoubleStream</td>
 *             <td>parallel() 스트림 -> 병렬 스트림, sequential() 병렬 스트림 -> 스트림</td>
 *         </tr>
 *         <tr>
 *             <td>DoubleStream</td>
 *             <td>Stream&lt;T&gt;, IntStream, LongStream, DoubleStream</td>
 *             <td>parallel() 스트림 -> 병렬 스트림, sequential() 병렬 스트림 -> 스트림</td>
 *         </tr>
 *         <tr>
 *             <td colspan="3">8. 스트림 -> 컬렉션</td>
 *         </tr>
 *         <tr>
 *             <td>Stream&lt;T&gt;</td>
 *             <td>Collection&lt;T&gt;, List&lt;T&gt;, Set&lt;T&gt;</td>
 *             <td>Collection&lt;T&gt; - collect(Collectors.toCollection(Supplier factory)), List&lt;T&gt; - collect(Collectors.toList()), Set&lt;T&gt; - collect(Collectors.toSet())</td>
 *         </tr>
 *         <tr>
 *             <td>IntStream</td>
 *             <td>Collection&lt;T&gt;, List&lt;T&gt;, Set&lt;T&gt;</td>
 *             <td>Collection&lt;T&gt; - collect(Collectors.toCollection(Supplier factory)), List&lt;T&gt; - collect(Collectors.toList()), Set&lt;T&gt; - collect(Collectors.toSet())</td>
 *         </tr>
 *         <tr>
 *             <td>LongStream</td>
 *             <td>Collection&lt;T&gt;, List&lt;T&gt;, Set&lt;T&gt;</td>
 *             <td>Collection&lt;T&gt; - collect(Collectors.toCollection(Supplier factory)), List&lt;T&gt; - collect(Collectors.toList()), Set&lt;T&gt; - collect(Collectors.toSet())</td>
 *         </tr>
 *         <tr>
 *             <td>DoubleStream</td>
 *             <td>Collection&lt;T&gt;, List&lt;T&gt;, Set&lt;T&gt;</td>
 *             <td>Collection&lt;T&gt; - collect(Collectors.toCollection(Supplier factory)), List&lt;T&gt; - collect(Collectors.toList()), Set&lt;T&gt; - collect(Collectors.toSet())</td>
 *         </tr>
 *         <tr>
 *             <td colspan="3">9. 컬렉션 -> 스트림</td>
 *         </tr>
 *         <tr>
 *             <td>Collection&lt;T&gt;, List&lt;T&gt;, Set&lt;T&gt;</td>
 *             <td>Stream&lt;T&gt;</td>
 *             <td>stream()</td>
 *         </tr>
 *         <tr>
 *             <td colspan="3">10. 스트림 -> Map</td>
 *         </tr>
 *         <tr>
 *             <td>Stream&lt;T&gt;</td>
 *             <td>Map&lt;K, V&gt;</td>
 *             <td>collect(Collectors.toMap(Function key, Function value)), collect(Collectors.toMap(Function k, Function v, BinaryOperator)), collect(Collectors.toMap(Function k, Function v, BinaryOperator merge, Supplier mapSupplier))</td>
 *         </tr>
 *         <tr>
 *             <td>IntStream</td>
 *             <td>Map&lt;K, V&gt;</td>
 *             <td>collect(Collectors.toMap(Function key, Function value)), collect(Collectors.toMap(Function k, Function v, BinaryOperator)), collect(Collectors.toMap(Function k, Function v, BinaryOperator merge, Supplier mapSupplier))</td>
 *         </tr>
 *         <tr>
 *             <td>LongStream</td>
 *             <td>Map&lt;K, V&gt;</td>
 *             <td>collect(Collectors.toMap(Function key, Function value)), collect(Collectors.toMap(Function k, Function v, BinaryOperator)), collect(Collectors.toMap(Function k, Function v, BinaryOperator merge, Supplier mapSupplier))</td>
 *         </tr>
 *         <tr>
 *             <td>DoubleStream</td>
 *             <td>Map&lt;K, V&gt;</td>
 *             <td>collect(Collectors.toMap(Function key, Function value)), collect(Collectors.toMap(Function k, Function v, BinaryOperator)), collect(Collectors.toMap(Function k, Function v, BinaryOperator merge, Supplier mapSupplier))</td>
 *         </tr>
 *         <tr>
 *             <td colspan="3">11. 스트림 -> 배열</td>
 *         </tr>
 *         <tr>
 *             <td rowspan="2"></td>
 *             <td>Object[]</td>
 *             <td>toArray()</td>
 *         </tr>
 *         <tr>
 *             <td>T[]</td>
 *             <td>toArray(IntFunction&lt;A[]&gt; generator)</td>
 *         </tr>
 *         <tr>
 *             <td>IntStream, LongStream, DoubleStream</td>
 *             <td>int[], long[], double[]</td>
 *             <td>toArray()</td>
 *         </tr>
 *     </tbody>
 * </table>
 */
public class Chapter14_2_8 {
}


