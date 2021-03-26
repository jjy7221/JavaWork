package gradle.Javapractice;

class Book{
    String title;
    String author;

    void show() {
        System.out.println(title + " " + author);
    }

    // this로 다른 생성자 호출
    //  ㆍthis()는 반드시 생성자 코드에서만 호출
    //  ㆍ반드시 같은 클래스 내 다른 생성자를 호출할 때 사용
    //  ㆍ반드시 실행 문장이 첫 번째 문장이여야 한다.
    public Book(){
        this("","");
       System.out.println("생성자 호출 됨");
    }

    public Book(String title){
        this(title, "작자미상");
    }

    public Book(String title, String author){
        this.title = title;
        this.author= author;
    }
}

public class clas2 {
    public static void main(String[] args){
        Book littlePrince = new Book("어린왕자", "생텍쥐페리");
        Book loveStory = new Book("춘향뎐");
        Book emptyBook = new Book();

        littlePrince.show();
        loveStory.show();

    }
}
