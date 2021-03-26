package gradle.pracitce;

import java.util.*;

class Point{
    int x, y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString(){
        return "Point(" + x + "," + y + ")";
    }
    @Override
    public boolean equals(Object obj){
        Point p = (Point)obj;
        if(x == p.x && y == p.y) return true;
        else return false;
    }
}

interface MyInterface{
    public void print();
    default  public void print2(){
        System.out.println("디폴트 메소드는 인터페이스 설계단계에서 중요하다(여유공간 확보)");
    }
}

class MyClass1 implements MyInterface{
    @Override
    public void print(){
        System.out.println("MyClass1");
    }
}

class MyClass2 implements MyInterface{
    @Override
    public void print(){
        System.out.println("MyClass2");
    }
    @Override
    public void print2(){
        System.out.println("디폴트 메소드 재정의");
    }
}

@FunctionalInterface    // 람다식은 추상 메서드가 1개여야 한다 2개 이상이면 오류 따라서 어노테이션을 써줘 오류를 예방
interface Multiply{
    double getValue();
}

@FunctionalInterface
interface Verify{
    boolean check(int n);
//    default void k(int a){
//        System.out.println(a);
//    }
}

@FunctionalInterface
interface  Verify2{
    boolean check(int n, int d);
}

@FunctionalInterface
interface NumberFunc{
    int func(int n);
}

interface IntegerFunc{
    Integer modify(Integer n);
}

interface  Myfunc<T>{
    T modify(T t);
}

interface StringFunc {
    String modify(String s);
}

interface StringFunc2 {
    String[] modify(String s) throws EmptyStringException;
}

class EmptyStringException extends Exception{
    EmptyStringException(){
        super("빈 문자열");
    }
}

interface Calculator{
    int func();
}

/////////////////////////////////////////////////////////////////////////////////////

public class pracitce {

    public static void test(MyInterface mi){
        mi.print();
    }

    public static MyInterface test2(){
        MyInterface mi = new MyInterface() {
            @Override
            public void print() {
                System.out.println("test2() 메서드에서 변환된 MyInterface");
            }
        };
        return mi;
    }

    public static MyInterface test3(){
        return new MyInterface() {
            @Override
            public void print() {
                System.out.println("Hello");
            }
        };
    }

    static String test99(StringFunc sf, String s) {
        return sf.modify(s);
    }

    static int num1 = 10;
    int num2 = 20;

    public static void main(String[] args) throws EmptyStringException {

//// region Obj오버라이딩
//        Point p = new Point(2,3);
//        Point q = new Point(2,3);
//        Point a = p;
//        System.out.println(p.toString());
//        System.out.println(p);
//        System.out.println(p + "입니다");
//
//        if(p == q)
//            System.out.println("a==b");
//        if(p.equals(q))
//            System.out.println("a is equal to b");
//        if(q.equals(a))
//            System.out.println("q is equal to a");
//        if(a == p)
//            System.out.println("q == p");
//        if(q.equals(a))
//            System.out.println("q is equal to a");
//        // endregion

//// region 람다 명령형
//        List<String> list = Arrays.asList("서울", "북경", "상해", "뉴욕");
//        boolean result = false;
//        for (String city : list) {
//            if (city.equals("서울")) {
//                result = true;
//                break;
//            }
//        }
//        System.out.println(result);
//        // 서술형
//        System.out.println(list.contains("서울"));
//
//        // endregion

//// region 인터페이스 구현 방법1) - implements 키워드
//        MyClass1 mc1 = new MyClass1();
//        MyClass2 mc2 = new MyClass2();
//        mc1.print();
//        mc1.print2();
//        mc2.print();
//        mc2.print2();
////endregion

//// region 인터페이스 구현 방법2) - 익명 클래스 사용
//        MyInterface mi = new MyInterface() {
//            @Override
//            public void print() {
//                System.out.println("익명 클래스로 구현");
//            }
//        };
//
//        mi.print();
//        mi.print2();
//        // endregion

////region 인터페이스 구현 방법3) - 선언, 생성, 호출을 한번에 처리
//        (new MyInterface(){
//            @Override
//            public void  print(){
//                System.out.println("선언, 생성, 호출을 한번에 처리");
//            }
//        }).print();
//        //endregion

//// region 매개변수
//        MyClass1 mv1 = new MyClass1();
//        MyInterface ml = new MyInterface() {
//            @Override
//            public void print() {
//                System.out.println("익명 클래스로 구현");
//            }
//        };
//
//        test(mv1);
//        test(ml);
//
//        MyInterface ml2 = test2();
//        ml2.print();
//// endregion

////region 람다식 구현
//        MyInterface m = () -> System.out.println("Hello");  // test3()
//        m.print();
//        //endregion

//// region 람다식 기본문법
//        Multiply m;
//        m = () -> 3.14 * 2;
//        System.out.println(m.getValue());
//
//        m = () -> 10 * 3;
//        System.out.println(m.getValue());
//        // endregion

////region 매개변수를 이용한 람다
//        Verify m = (n) -> (n % 2) == 0;
//        System.out.println(m.check(10));
//
//        m = (n) -> n + 1 == 2;
//        System.out.println(m.check(3));
//
//        Verify m2 = (n) -> n >= 0;
//        System.out.println(m2.check(-5));
//
//        Verify2 vf = (n, d) -> (n % d) == 0;
//        System.out.println(vf.check(24,3));
//                //endregion

////region 람다 블록
//        NumberFunc sum = (n) -> {
//            int result = 0;
//            for(int i = 0 ; i <= n; i++){
//                result += i;
//            }
//            return result;
//        };
//
//        System.out.println(sum.func(10));
//        System.out.println(sum.func(100));
//        //endregion

////region 제네릭 인터페이스
//        Myfunc<String> mf1 = (str) -> str.toUpperCase() + " : " + str.length();
//        System.out.println(mf1.modify("java"));
//        System.out.println(mf1.modify("java programming"));
//
//        Myfunc<Integer> mf2 = (n) -> n * 2;
//        System.out.println(mf2.modify(23));
//        System.out.println(mf2.modify(42));
//        //endregion

////region 매개변수가 인터페이스형
//        String str = "Korea, Australia, China, Germany, Turkey";
//        StringFunc sf1 = (s) -> {
//            String result = "";
//            char c;
//            for(int i = 0; i < s.length(); i++){
//                c = s.charAt(i);
//                if(c == ',')
//                    result += " ";
//                else
//                    result += c;
//            }
//            return result;
//        };
//
//        String s1 = test99(sf1, str);
//        System.out.println(s1);
//
//
//        String s2 = test99((s) -> {
//            int max = 0;
//            int index = 0;
//            String[] word = s.split(",");
//            for(int i =0; i < word.length; i++){
//                if(max < word[i].length()) {
//                    max = word[i].length();
//                    index = i;
//                }
//            }
//            return word[index];
//        }, str);
//        System.out.println(s2);
////endregion

////region 예외처리
//        String str = "Korea,Australia,China,Germany,Spain,Turkey";
//        StringFunc2 sf = (s) -> {
//            if(s.length() == 0)
//                throw new EmptyStringException();
//            return s.split(",");
//        };
//
//        String result[] = sf.modify(str);
//        System.out.println(Arrays.toString(result));
//
//
//        String str2 = "";
//        String result2[] = sf.modify(str2);
//        System.out.println(Arrays.toString(result2));
//        //endregion

        pracitce test = new pracitce();
        int num3 = 30;

        Calculator calc = () -> {
            int num4 = 40;
            pracitce.num1++;
            test.num2++;
            num4++;
            return num4;
        };

    }
}
