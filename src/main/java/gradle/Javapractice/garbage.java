package gradle.Javapractice;

// Garbage >> new로 할당 받은 객체나 배열의 메모리가 레퍼런스가 하나도 없는 상태이면
//            가비지로 판단 가용가능한 메모리상태로 바꿔준다
//             장점 >> 소멸자 함수 호출이나 따로 메모리 반환을 하는 일이 없어짐

public class garbage {
    public static void main(String[] args){
        String a = new String("Good");
        String b = new String("Bad");
        String c = new String("Normal");
        String d,e;
        a = null;
        d=c;
        c = null;
        // a가 가르키고 있던 "Good"은 가비지가 된다
        System.gc(); // 가비지 컬렉션 강제 요청 함수
        // 가비지 컬렉션
        // >자바 플랫 폼은 가용 메모리가 일정 크기 이하로 줄어들면
        //  자동으로 가비지를 회수하여 가용 메모리를 늘린다
    }
}
