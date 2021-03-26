package gradle.Javapractice;

class Circle{
    int radius;
    String name;

    public Circle(){
        name = "";
        radius = 1;
    }

    public Circle(int radius, String name){
        this.radius = radius;
        this.name = name;
    }

    public double getArea(){
        return 3.14*radius*radius;
    }
}

public class clas {

    public static void main(String[] args){
        Circle Pizza = new Circle(10,"얼굴 좀 피짜");

        double area = Pizza.getArea();
        System.out.println(Pizza.name + "의 면적은" + area);

        Circle donut = new Circle();
        donut.name = "도우넛 어서오고";
        area = donut.getArea();
        System.out.println(donut.name + "의 면적은" + area);
    }
}
