package chapter12;

/**
 * <h1>애너테이션(annotation)</h1>
 */
public class Chapter12_03 {

	/**
	 * <p>
	 *     <h5>3.1 애너테이션이란?</h5><br>
	 *	   자바를 개발한 사람들은 소스코드에 대한 문서를 따로 만들기보다 소스코드와 문서를 하나의 파일로 관리하는 것이 낫다고 생각했다.<br>
	 *	   소스코드의 주석에 소스코드에 대한 정보를 저장하고, 소스코드의 주석으로부터 HTML문서를 생성해내는 프로그램(javadoc.exe)을 만들어서 사용했다.<br>
	 *	   미리 정의된 태그들을 이용해서 주석안에 정보를 저장하고, javadoc.exe라는 프로그램이 이 정보를 읽어서 문서를 작성하는데 사용한다.<br>
	 *	   이 기능을 응용하여, 소스코드 안에 다른 프로그램을 위한 정보를 미리 약속된 형식으로 포함시킨 것이 바로 애너테이션이다.<br>
	 *	   애너테이션은 주석(comment)처럼 프로그래밍 언어에 영향을 미치지 않으면서도 다른 프로그램에게 유용한 정보를 제공할 수 있다는 장점이 있다.<br>
	 *	   <small>※애너테이션(annotation)의 뜻은 주석, 주해, 메모이다.</small><br>
	 *	   자신이 작성한 소스코드 중에서 특정 메서드만 테스트하기를 원한다면, '@Test'라는 애너테이션을 메서드 앞에 붙인다.<br>
	 *	   '@Test'는 '이 메서드를 테스트해야 한다'는 것을 테스트 프로그램에게 알리는 역할을 할 뿐, 메서드가 포함된 프로그램 자체에는 아무런 영향을 미치지 않는다.<br>
	 *	   주석처럼 존재하지 않는 것이나 다름없다.<br>
	 *	   JDK에서 제공하는 표준 애너테이션은 주로 컴파일러를 위한 것으로 컴파일러에게 유용한 정보를 제공한다.<br>
	 *	   그리고 새로운 애너테이션을 정의할 때 사용되는 메타 애너테이션을 제공한다.<br>
	 *	   <small>※JDK에서 제공하는 애너테이션은 'java.lang.annotation'패키지에 포함되어 있다.</small>
	 * </p>
	 */
	private class Memo01{}

	/**
	 * <p>
	 *     <h5>표준 애너테이션</h5><br>
	 *     자바에서 기본적으로 제공하는 애너테이션들을 몇 개 없다. 그나마 이들의 일부는 '메타 애너테이션(meta annotation)'으로 애너테이션을 정의하는데
	 *     사용되는 애너테이션의 애너테이션이다.<br>
	 *     <table border="1">
	 *         <tr>
	 *             <th>애너테이션</th>
	 *             <th>설명</th>
	 *         </tr>
	 *         <tr>
	 *             <td>@Override</td>
	 *             <td>컴파일러에게 오버라이딩하는 메서드라는 것을 알린다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>@Deprecated</td>
	 *             <td>앞으로 사용하지 않을 것을 권장하는 대상에 붙인다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>@SuppressWarnings</td>
	 *             <td>컴파일러의 특정 경고메세지가 나타나지 않게 해준다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>@SafeVarargs</td>
	 *             <td>지네릭스 타입의 가변인자에 사용한다.(JDK1.7)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>@FunctionalInterface</td>
	 *             <td>함수형 인터페이스라는 것을 알린다.(JDK1.8)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>@Native</td>
	 *             <td>native메서드에서 참조되는 상수 앞에 붙인다.(JDK1.8)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>@Target</td>
	 *             <td>애너테이션이 적용가능한 대상을 지정하는데 사용한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>@Documented</td>
	 *             <td>애너테이션 정보가 javadoc으로 작성된 문서에 포함되게 한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>@inherited</td>
	 *             <td>애너테이션이 자손 클래스에 상속되도록 한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>@Retention</td>
	 *             <td>애너테이션이 유지되는 범위를 지정하는데 사용한다.</td>
	 *         </tr>
	 *         <tr>
	 *             <td>@Repeatable</td>
	 *             <td>애너테이션을 반복해서 적용할 수 있게 한다.(JDK1.8)</td?>
	 *         </tr>
 *         </table>
	 * </p>
	 */
	private class Memo02{}

	/**
	 * <p>
	 * 		<h5>@Override</h5><br>
	 * 		메서드 앞에만 붙일 수 있는 애너테이션으로, 조상의 메서드를 오버라이딩하는 것이라는걸 컴파일러에게 알려주는 역할을 한다.<br>
	 * </p>
	 */
	private class Parent {
		void parentMethod() {}
	}

	private class Child extends Parent {
		@Override
		void parentMethod() {}
	}

	/**
	 * <p>
	 * 		<h5>@Deprecated</h5><br>
	 * 		더 이상 사용되지 않는 필드나 메서드에 '@Deprecated'를 붙이는 것이다.<br>
	 * 		이 애너테이션이 붙은 대상은 다른 것으로 대체되었으니 더 이상 사용하지 않을 것을 권한다는 의미이다.<br>
	 * </p>
	 */
	private class Memo04{}

	/**
	 * <p>
	 *     <h5>@FunctionalInterface</h5><br>
	 *     '함수형 인터페이스(functional interface)'를 선언할 때, 이 애너테이션을 붙이면 컴파일 러가 '함수형 인터페이스'를 올바르게 선언했는지 확인하고<br>
	 *     잘못된 경우 에러를 발생시킨다.
	 * </p>
	 */
	private class Memo05{}

	/**
	 * <p>
	 * 		<h5>@SuppressWarnings</h5><br>
	 * 		컴파일러가 보여주는 경고메세지가 나타나지 않게 억제해준다.<br>
	 * 		'@SuppressWarnings'로 억제할 수 있는 경고 메세지의 종류는 여러 가지가 있는데,<br>
	 * 		주로 사용되는 것은 "deprecation", "unchecked", "rawtypes", "varargs" 정도이다.<br>
	 * 		"deprecation"은 앞서 살펴본것과 같이 "@Deprecated"가 붙은 대상을 사용해서 발생하는 경고를,<br>
	 * 		"unchecked"는 지네릭스로 타입을 지정하지 않았을 때 발생하는 경고를,<br>
	 * 		"rawtypes"는 지네릭스를 사용하지 않아서 발생하는 경고를,<br>
	 * 		그리고 "varargs"는 가변인자의 타입이 지네릭 타입일 때 발생하는 경고를 억제할 때 사용한다.<br>
	 * 		억제하려는 경고 메세지를 애너테이션의 뒤에 괄호()안에 문자열로 지정하면 된다.<br>
	 * 		만일 둘 이상의 경고를 동시에 억제하려면 다음과 같이 한다.<br>
	 * 		<code>@SuppressWarnings({ "deprecation", "unchecked", "varargs" })</code>
	 * </p>
	 */
	private class Memo06{}

	/**
	 * <p>
	 *     <h5>@SafeVarargs</h5><br>
	 *     이 애너테이션은 static이나 final이 붙은 메서드와 생성자에만 붙일 수 있다.즉, 오버라이드될 수 있는 메서드에는 사용할 수 없다는 뜻이다.<br>
	 *     컴파일 후에도 제거되지 않는 타입을 reifiable타입이라고 하고, 제거되는 타입을 non-reifiable타입이라고 한다.<br>
	 *     지네릭 타입들은 대부분 컴파일 시에 제거되므로 non-reifiable타입이다.<br>
	 *     <small>※reifiable 're(다시)' + '-ify(~화 하다)' + '-able(~할 수 있는)'의 합성어로 직역하면, '다시 ~화(化) 할 수 있는' 이라는 뜻이다.</small><br>
	 *     <small>컴파일 후에도 타입정보가 유지되면 reifiable타입이다.</small>
	 * </p>
	 */
	private class Memo07{}

	/**
	 * <p>
	 * 		<h5>3.3 메타 애너테이션</h5><br>
	 * 		메타 애너테이션은 '애너테이션을 위한 애너테이션', 즉<br>
	 * 		애너테이션에 붙이는 애너테이션으로 애너테이션을 정의할 때 애너테이션이 적용대상(target)이나 유지기간(retention)등을 지정하는데 사용된다.<br>
	 * 		<small>※메타 애너테이션은 'java.lang.annotation'패키지에 포함되어 있다.</small>
	 * </p>
	 */
	private class Memo08{}

	/**
	 * <p>
	 *     <h5>@Target</h5><br>
	 *     애너테이션이 적용가능한 대상을 지정하는데 사용된다.<br>
	 *     여러개의 값을 지정할 때는 배열에서 처럼 괄호{}를 사용해야한다.<br>
	 *     '@Target'으로 지정할 수 있는 애너테이션 적용대상의 종류<br>
	 *     <table>
	 *         <th>대상 타입</th>
	 *         <th>의미</th>
	 *         <tr>
	 *             <td>ANNOTATION_TYPE</td>
	 *             <td>애너테이션</td>
	 *         </tr>
	 *         <tr>
	 *             <td>CONSTRUCTOR</td>
	 *             <td>생성자</td>
	 *         </tr>
	 *         <tr>
	 *             <td>FIELD</td>
	 *             <td>필드(멤버변수, enum상수)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>LOCAL_VARIABLE</td>
	 *             <td>지역변수</td>
	 *         </tr>
	 *         <tr>
	 *             <td>METHOD</td>
	 *             <td>메서드</td>
	 *         </tr>
	 *         <tr>
	 *             <td>PACKAGE</td>
	 *             <td>패키지</td>
	 *         </tr>
	 *         <tr>
	 *             <td>PARAMETER</td>
	 *             <td>매개변수</td>
	 *         </tr>
	 *         <tr>
	 *             <td>TYPE</td>
	 *             <td>타입(클래스, 인터페이스, enum)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>TYPE_PARAMETER</td>
	 *             <td>타입 매개변수(JDK1.8)</td>
	 *         </tr>
	 *         <tr>
	 *             <td>TYPE_USE</td>
	 *             <td>타입이 사용되는 모든 곳(JDK1.8)</td>
	 *         </tr>
	 *     </table>
	 *     <br>
	 *     'TYPE'은 타입을 선언할 때, 애너테이션을 붙일 수 있다는 뜻이고, 'TYPE USE"는 해당 타입의 변수를 선언할 때 붙일 수 있다는 뜻이다.<br>
	 *     'FIELD'는 기본형에, 그리고 'TYPE USE'는 참조형에 사용된다는 점에 주의하자.
	 * </p>
	 */
	private class Memo09{}

	/**
	 * <p>
	 *     <h5>@Retention</h5><br>
	 *     애너테이션이 유지(retention)되는 기간을 지정하는데 사용된다.<br>
	 *     애너테이션의 유지정책(retention policy)의 종류
	 *     <table>
	 *         <th>유지정책</th>
	 *         <th>의미</th>
	 *         <tr>
	 *             <td>SOURCE</td>
	 *             <td>소스파일에만 존재, 클래스파일에는 존재하지 않음.</td>
	 *         </tr>
	 *		   <tr>
	 *		       <td>CLASS</td>
	 *		       <td>클래스 파일에 존재, 실행시에 사용불가. 기본값</td>
	 *		   </tr>
	 *		   <tr>
	 *		       <td>RUNTIME</td>
	 *		       <td>클래스 파일에 존재, 실행시에 사용가능.</td>
	 *		   </tr>
	 *     </table>
	 *     <br>
	 *     유지 정책을 'RUNTIME'으로 하면, 실행 시에 '리플렉션(reflection)'을 통해 클래스 파일에 저장된 애너테이션의 정보를 읽어서 처리할 수 있다.<br>
	 *     유지 정책 'CLASS'는 컴파일러가 애너테이션의 정보를 클래스 파일에 저장할 수 있게는 하지만,<br>
	 *     클래스 파일이 JVM에 로딩될 때는 애너테이션의 정보가 무시되어 실행 시에 애너테이션에 대한 정보를 얻을 수 없다.<br>
	 *     <small>※지역 변수에 붙은 애너테이션은 컴파일러만 인식할 수 있으므로, 유지정책이 'RUNTIME'인 애너테이션을 지역변수에 붙여도 실행 시에는 인식되지 않는다.</small>
	 * </p>
	 */
	private class Memo10{}

	/**
	 * <p>
	 *     <h5>@Documented</h5><br>
	 *     애너테이션에 대한 정보가 javadoc으로 작성한 문서에 포함되도록 한다.<br>
	 *     자바에서 제공하는 기본 애너테이션 중에 '@Override'와 '@SuppressWarnings'를 제외하고는 모두 이 메타 애너테이션이 붙어 있다.
	 * </p>
	 */
	private class Memo11{}

	/**
	 * <p>
	 *     <h5>@Inherited</h5><br>
	 *     애너테이션이 자손 클래스에 상속되도록 한다. '@Inherited'가 붙은 애너테이션을 조상클래스에 붙이면,<br>
	 *     자손 클래스도 이 애너테이션이 붙은 것과 같이 인식된다.
	 * </p>
	 */
	private class Memo12{}

	/**
	 * <p>
	 *     <h5>@Repeatable</h5><br>
	 *     보통은 하나의 대상에 한 종류의 애너테이션을 붙이는데, '@Repeatable'이 붙은 애너테이션은 여러 번 붙일 수 있다.<br>
	 *     일반적인 애너테이션과 달리 같은 이름의 애너테이션이 여러 개가 하나의 대상에 적용될 수 있기 때문에,<br>
	 *     이 애너테이션들을 하나로 묶어서 다룰 수 있는 애너테이션도 추가로 정의해야 한다.
	 * </p>
	 */
	private class Memo13{}

	/**
	 * <p>
	 *     <h5>@Natvie</h5><br>
	 *     네이티브 메서드(native method)에 의해 참조되는 '상수 필드(constant field)'에 붙이는 애너테이션이다.<br>
	 *     네이티브 메서드는 JVM이 설치된 OS의 메서드를 말한다. 네이티브 메서드는 보통 C언어로 작성되어 있는데,<br>
	 *     자바에서는 메서드의 선언부만 정의하고 구현은 하지 않는다. 그래서 추상 메서드처럼 선언부만 있고 몸통이 없다.<br>
	 *     네이티브 메서드는 자바로 정의되어 있기 때문에 호출하는 방법은 자바의 일반 메서드와 다르지 않지만 실제로 호출되는 것은 OS의 메서드이다.
	 * </p>
	 */
	private class Memo14{}

	/**
	 * <p>
	 *     <h5>3.4 애너테이션 타입 정의하기</h5><br>
	 *     새로운 애너테이션을 정의하는 방법<br>
	 *		<code>&commat;interface 애너테이션이름 {</code>
	 * 		<code>    타입 요소이름(); // 애너테이션의 요소를 선언한다.</code>
	 * 		<code>}</code>
	 * 		<br>
	 * 		엄밀히 말해서 '@Override'는 애너테이션이고, 'Override'는 '애너테이션의 타입'이다.
	 * </p>
	 */
	private class Memo15{}

	/**
	 * <p>
	 *     <h5>애너테이션의 요소</h5><br>
	 *     애너테이션 내에 선언된 메서드를 '애너테이션의 요소(element)'라고 한다.<br>
	 *     <small>※애너테이션에도 인터페이스처럼 상수를 정의할 수 있지만, 디폴트 메서드는 정의할 수 없다.</small>
	 * </p>
	 */
	private class Memo16{}
}
