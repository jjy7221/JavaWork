package gradle.BTS;

import java.util.Scanner;
import java.util.Stack;

class Node{
    private Node left;
    private Node right;
    private int data;

    Node(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}

class Tree{
    private Node Root;

    public Node getRoot() {
        return Root;
    }

    public void setRoot(Node root) {
        Root = root;
    }

    //#region Recursion
    // Node Insert_data(int data, Node Root){
    //   if(this.Root == null){
    //       this.Root = new Node(data);
    //       return this.Root;
    //   }

    //   if(Root == null)
    //       return new Node(data);

    //   if(Root.getData() > data)
    //       Root.setLeft(Insert_data(data, Root.getLeft()));
    //   else
    //       Root.setRight(Insert_data(data, Root.getRight()));

    //   return Root;
    // }
//#endregion

    //#region Loop
    void Insert_data(int data){
        if(this.Root == null){
            this.Root = new Node(data);
            return;
        }
        Node Root = this.Root;
        while(Root.getData() != data){
            if(Root.getData() > data){
                if(Root.getLeft() == null)
                    Root.setLeft(new Node(data));

                Root = Root.getLeft();
            }
            else{
                if(Root.getRight() == null)
                    Root.setRight(new Node(data));

                Root = Root.getRight();
            }
        }
    }
//#endregion

    Node Search_Node(int data, Node Root){

        if(Root == null) return null;

        if(Root.getData() > data)
            return Search_Node(data, Root.getLeft());
        else if(Root.getData() < data)
            return Search_Node(data, Root.getRight());
        else
            return Root;
    }

    void Delete_data(int data){
        Node Finder = getRoot();
        Node Tracer = Finder;

        while(Finder.getData() != data){
            Tracer = Finder;
            if(Finder.getData() > data)
                Finder = Finder.getLeft();
            else if(Finder.getData() < data)
                Finder = Finder.getRight();
            if(Finder == null){
                System.out.println("Data not found");
                return;
            }
        }

        Node Anchor = Finder;

        // Leap Node 일 경우
        if(Finder.getLeft() == null && Finder.getRight() == null)
            if(Tracer.getData() > Finder.getData())
                Tracer.setLeft(null);
            else if(Tracer.getData() < Finder.getData())
                Tracer.setRight(null);
            else
                Root = null;
        // Child Node가 1개 일 경우
        else if(Finder.getLeft() == null || Finder.getRight() == null){
            if(Finder.getLeft() != null)
                Finder = Finder.getLeft();
            else if(Finder.getRight() != null)
                Finder = Finder.getRight();

            if(Tracer == Anchor)
                setRoot(Finder);
            else if(Tracer.getData() > Finder.getData())
                Tracer.setLeft(Finder);
            else if(Tracer.getData() < Finder.getData())
                Tracer.setRight(Finder);
        }
        // Child Node가 모두 있을 경
        else{
            while(Finder.getRight() != null){
                Tracer = Finder;
                Finder = Finder.getRight();

                while(Finder.getLeft() != null){
                    Tracer = Finder;
                    Finder = Finder.getLeft();
                }
                Anchor.setData(Finder.getData());
                Anchor = Finder;
            }

            if( Tracer.getLeft() == Finder)
                Tracer.setLeft(null);
            else
                Tracer.setRight(null);
        }
    }

    public int depth(Node t) {
        if (t == null)
            return 0;

        return 1 + Math.max(depth(t.getLeft()), depth(t.getRight()));
    }

    public int calDepth(Node p) {
        int left_d, right_d = 0;
        left_d = depth(p.getLeft());
        right_d = depth(p.getRight());

        //diff>0 : 왼쪽이 긴 형태 | diff<0 : 오른쪽이 긴 형태
        int diff = left_d - right_d;
        return diff;
    }

    public Node split(int diff, Tree t) {

        Node temp = getRoot();
        Node f = null;
        Node ret = null;

        double cnt = Math.ceil(Math.abs(diff) / 2);

        if (diff < 0) {
            for (int i = 0; i < cnt; i++) {
                f = temp;
                temp = temp.getRight();
            }
            t.setRoot(temp.getRight());
            ret = temp;

            f.setRight(null);
            ret.setRight(null);
        } else {
            for (int i = 0; i < cnt; i++) {
                f = temp;
                temp = temp.getLeft();
            }
            t.setRoot(temp.getLeft());
            ret = temp;

            f.setLeft(null);
            ret.setLeft(null);
        }
        return ret;
    }

    public Tree join(Node x, Tree t) {
        Tree ret = new Tree();
        ret.setRoot(x);

        if (getRoot().getData() < t.getRoot().getData()) {
            ret.getRoot().setLeft(getRoot());
            ret.getRoot().setRight(t.getRoot());
        } else {
            ret.getRoot().setLeft(t.getRoot());
            ret.getRoot().setRight(getRoot());
        }
        return ret;
    }

    // 전위 순회(Root -> Left -> Right)
    void Preorder(Node Root){
        if(Root == null) return;

        System.out.print(Root.getData()+" ");

        Preorder(Root.getLeft());

        Preorder(Root.getRight());
    }

    // 중위 순회(Left -> Root -> Right)
    void Inorder(Node Root){
        if(Root == null) return;

        Inorder(Root.getLeft());

        System.out.print(Root.getData()+" ");

        Inorder(Root.getRight());
    }

    // 후위 순회(Right -> Left -> Root)
    void Postorder(Node Root){
        if(Root == null) return;

        Postorder(Root.getLeft());

        Postorder(Root.getRight());

        System.out.print(Root.getData()+" ");
    }
}

class InorderIterator{
    private Tree t;
    private Stack<Node> s;
    private Node Current;

    InorderIterator(Tree t){
        s = new Stack<>();
        this.t = t;
        Current = t.getRoot();
    }

    public int next(){
        while(Current != null){
            s.push(Current);
            Current = Current.getLeft();
        }
        if(!s.empty()){
            Current = s.pop();
            int temp = Current.getData();
            Current = Current.getRight();
            return temp;
        }
        return 0;
    }
}

public class BTS {

    static void Init(Tree song, Scanner scanner){
        int data;
        System.out.println("Insert Data(Enter 0 to escape)");
        while(0 != (data=scanner.nextInt())){
            //song.Insert_data(data, song.getRoot());
            song.Insert_data(data);
        }
    }

    static int findMin(InorderIterator I){
        int ret = I.next();
        int data = 0;

        while((data=I.next()) != 0){
            if(ret > data){
                ret = data;
            }
        }
        return ret;
    }

    static int findMax(InorderIterator I){
        int ret = I.next();
        int data = 0;

        while((data=I.next()) != 0){
            if(ret < data){
                ret = data;
            }
        }
        return ret;
    }

    static double avg(InorderIterator I){
        double ret = 0;

        int data = 0;
        int cnt = 0;
        while((data=I.next()) != 0) {
            cnt++;
            ret += data;
        }
        return ret/cnt;
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Tree song = new Tree();
        InorderIterator I = new InorderIterator(song);

        Init(song, scanner);
        song.Inorder(song.getRoot());
        System.out.println();

//
//        System.out.println(findMin(I));
//        I = new InorderIterator(song);
//        System.out.println(findMax(I));
//        I = new InorderIterator(song);
//        System.out.println( avg(I));

//        Tree s = new Tree();
//        Node r;
//        r = song.split(song.calDepth(song.getRoot()), s);
//        song.Inorder(song.getRoot());
//        System.out.println();
//        System.out.println(r.getData());
//        s.Inorder(s.getRoot());
//        System.out.println();
//
//        Tree ret;
//        ret=song.join(r,s);
//        ret.Inorder(ret.getRoot());
//        System.out.println();

        int data;

        while( 0 != (data=scanner.nextInt())){

            song.Delete_data(data);

            song.Inorder(song.getRoot());

            System.out.println();
        }

        scanner.close();
    }
}