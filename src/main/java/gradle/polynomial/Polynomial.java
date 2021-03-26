package gradle.polynomial;

import java.util.Scanner;

class Node {
    private double coef;
    private int exp;
    private Node next;

    Node(double coef, int exp) {
        this.coef = coef;
        this.exp = exp;
        this.next = null;
    }

    void Setnext(Node nextnode) {
        this.next = nextnode;
    }

    Node Getnext() {
        return this.next;
    }

    void Setcoef(double coef) {
        this.coef = coef;
    }

    double Getcoef() {
        return this.coef;
    }

    void Setexp(int exp) {
        this.exp = exp;
    }

    int Getexp() {
        return this.exp;
    }
}

class L_List {
    private Node head;

    L_List() {
        this.head = null;
    }

    void Insert_data(double coef, int exp) {
        if (head == null) {
            this.head = new Node(coef, exp);
        } else {
            Node temp = this.head;
            while (true) {
                if (temp.Getexp() < exp) {
                    Node P_next = temp.Getnext();
                    Node dummy = new Node(temp.Getcoef(), temp.Getexp());

                    temp.Setcoef(coef);
                    temp.Setexp(exp);

                    dummy.Setnext(P_next);
                    temp.Setnext(dummy);

                    return;
                } else if (temp.Getexp() == exp) {
                    temp.Setcoef(temp.Getcoef() + coef);
                    if (temp.Getcoef() == 0) {
                        if (temp == this.head) {
                            Node dummy = head.Getnext();
                            this.head = null;
                            this.head = dummy;
                            return;
                        }
                        Node P_next = temp.Getnext();
                        Node trace = this.head;
                        while (trace.Getnext() != temp) {
                            trace = trace.Getnext();
                        }
                        trace.Setnext(P_next);
                        temp = null;
                    }
                    return;
                } else if (temp.Getexp() > exp && temp.Getnext() == null) {
                    temp.Setnext(new Node(coef, exp));
                    return;
                }
                temp = temp.Getnext();
            }
        }
    }

    public void reverse() {
        Node temp = this.Gethead();

        while (temp != null) {
            temp.Setcoef(temp.Getcoef() * -1);
            temp = temp.Getnext();
        }

    }

    public L_List Add(L_List y) {
        L_List ret = new L_List();

        Node f = this.Gethead();
        Node g = y.Gethead();

        while (g != null) {
            ret.Insert_data(g.Getcoef(), g.Getexp());
            g = g.Getnext();
        }

        while (f != null) {
            ret.Insert_data(f.Getcoef(), f.Getexp());
            f = f.Getnext();
        }

        return ret;
    }

    public L_List Sub(L_List y) {

        y.reverse();
        L_List ret = this.Add(y);
        y.reverse();

        return ret;
    }

    public L_List Multiply(L_List y) {
        L_List ret = new L_List();

        Node f = this.Gethead();
        while (f != null) {
            Node g = y.Gethead();
            while (g != null) {
                ret.Insert_data(f.Getcoef() * g.Getcoef(), f.Getexp() + g.Getexp());

                g = g.Getnext();
            }
            f = f.Getnext();
        }
        return ret;
    }

    public void substitution(int num) {
        Node temp = this.Gethead();
        int result = 0;
        while (temp != null) {
            result += temp.Getcoef() * (Math.pow(num, temp.Getexp()));
            temp = temp.Getnext();
        }
        System.out.println(">>>" + result);

    }

    void Show_List() {
        Node temp = this.head;

        if (temp == null) {
            System.out.println("Empty List");
            return;
        }

        while (temp != null) {

            if (temp != this.head && temp.Getcoef() > 0)
                System.out.print("＋");
            else if (temp.Getcoef() < 0)
                System.out.print("－");


            if (temp.Getcoef() != 1 && temp.Getcoef() != -1 || temp.Getexp() == 0)
                if (temp.Getcoef() % 1 == 0)
                    System.out.print((int) (Math.abs(temp.Getcoef())));
                else System.out.print(Math.abs(temp.Getcoef()));

            int num = temp.Getexp();
            int quotient = 0;
            int cnt = 0;
            while (num != 0) {
                quotient *= 10;
                quotient = quotient + num % 10;
                num /= 10;
                cnt++;
            }

            if (quotient != 0)
                System.out.print("x");

            for (int i = 0; i < cnt; i++) {
                int rest = quotient % 10;
                quotient /= 10;

                switch (rest) {
                    case 0:
                        if (cnt != 1)
                            System.out.print("⁰");
                        break;
                    case 1:
                        if (cnt != 1)
                            System.out.print("¹");
                        break;
                    case 2:
                        System.out.print("²");
                        break;
                    case 3:
                        System.out.print("³");
                        break;
                    case 4:
                        System.out.print("⁴");
                        break;
                    case 5:
                        System.out.print("⁵");
                        break;
                    case 6:
                        System.out.print("⁶");
                        break;
                    case 7:
                        System.out.print("⁷");
                        break;
                    case 8:
                        System.out.print("⁸");
                        break;
                    case 9:
                        System.out.print("⁹");
                        break;
                    default:
                        System.out.print("Error");
                        break;
                }
            }

            temp = temp.Getnext();

        }
        System.out.println();
    }

    void Sethead(Node L_node) {
        this.head = L_node;
    }

    Node Gethead() {
        return this.head;
    }
}

public class Polynomial {

    static L_List Initdata(Scanner scanner) {
        L_List ret = new L_List();
        double coef;

        System.out.println("Insert data into List(Enter 0 to escape)");
        System.out.print("계수를 입력하세요>>>");
        while (0 != (coef = scanner.nextDouble())) {
            System.out.print("지수를 입력하세요>>>");
            int exp = scanner.nextInt();

            ret.Insert_data(coef, exp);

            ret.Show_List();

            System.out.print("계수를 입력하세요>>>");
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        L_List f1 = Initdata(scanner);
        L_List g1 = Initdata(scanner);

        f1.Show_List();
        f1.substitution(2);
        g1.Show_List();
        g1.substitution(3);

        L_List k1 = f1.Add(g1);
        k1.Show_List();
        k1.substitution(4);

        L_List h1 = f1.Multiply(g1);
        h1.Show_List();
        h1.substitution(5);

        scanner.close();
    }
}
