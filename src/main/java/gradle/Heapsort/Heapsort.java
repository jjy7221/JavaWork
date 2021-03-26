package gradle.Heapsort;

import java.util.Scanner;

public class Heapsort {

    static int[] Init_Array(Scanner scanner) {
        int data;
        int size = 2;
        int[] ret = new int[1];
        System.out.println("배열에 넣을 값을 입력하세요! (0이하의 숫자 입력시 종료)");
        while ((data = scanner.nextInt()) > 0) {
            int[] dummy = new int[size++];
            for (int i = 1; i < ret.length; i++) {
                dummy[i] = ret[i];
            }
            dummy[dummy.length - 1] = data;
            ret = dummy;
        }
        ret[0] = -ret.length + 1;

        return ret;
    }

    static void Swap(int[] Array, int i, int j) {
        int temp = Array[i];
        Array[i] = Array[j];
        Array[j] = temp;
    }

    static void Min_Heapify(int[] Array) {
        int cnt = (Array.length - 1);
        for (int i = cnt; i >= ((cnt) / 2) + 1; i--) {
            for (int j = i; j > 1; j /= 2) {
                if (Array[j / 2] > Array[j]) {
                    Swap(Array, j, j / 2);
                    if (j < ((cnt) / 2) + 1) {
                        Min_HeapSort(Array, 0, j);
                    }
                }
            }
        }
    }

    static void Max_Heapify(int[] Array) {
        int cnt = (Array.length - 1);
        for (int i = cnt; i >= ((cnt) / 2) + 1; i--) {
            for (int j = i; j > 1; j /= 2) {
                if (Array[j / 2] < Array[j]) {
                    Swap(Array, j, j / 2);
                    if (j < ((cnt) / 2) + 1) {
                        Max_HeapSort(Array, 0, j);
                    }
                }
            }
        }
    }

    static void Max_HeapSort(int[] Array, int num, int sidx) {
        int cnt = (Array.length - 1) - num;
        for (int idx = sidx; idx < ((cnt) / 2) + 1; ) {

            if(idx == (Array.length-1)/2 && Array.length%2 != 0){
                if (Array[idx] < Array[idx * 2]) {
                    Swap(Array, idx, idx * 2);
                }
                break;
            }

            if (Array[idx * 2] > Array[(idx * 2) + 1]) {
                if (Array[idx] < Array[idx * 2] &&  idx* 2 <= cnt) {
                    Swap(Array, idx, idx * 2);
                }
                idx = idx * 2;

            } else {
                if (Array[idx] < Array[(idx * 2) + 1] && (idx* 2) +1 <= cnt) {
                    Swap(Array, idx, (idx * 2) + 1);
                }
                idx = (idx * 2) + 1;

            }
        }
    }

    static void Min_HeapSort(int[] Array, int num, int sidx) {
        int cnt = (Array.length - 1) - num;
        for (int idx = sidx; idx < ((cnt) / 2) + 1; ) {

            if(idx == (Array.length-1)/2 && Array.length%2 != 0){
                if (Array[idx] > Array[idx * 2]) {
                    Swap(Array, idx, idx * 2);
                }
                break;
            }

            if (Array[idx * 2] < Array[(idx * 2) + 1]) {
                if (Array[idx] > Array[idx * 2] &&  idx* 2 <= cnt) {
                    Swap(Array, idx, idx * 2);
                }
                idx = idx * 2;

            } else {
                if (Array[idx] > Array[(idx * 2) + 1] && (idx* 2) +1 <= cnt) {
                    Swap(Array, idx, (idx * 2) + 1);
                }
                idx = (idx * 2) + 1;

            }
        }
    }
    static void Heap_sort(int[] Array, int select) {

        if (select == 2) {
            System.out.println("Asc(오름차순)");
        } else {
            System.out.println("Desc(내림차순)");
        }

        for (int idx = 0; idx < Array.length - 3; idx++) {

            if (select == 2) {
                Max_HeapSort(Array, idx, 1);
            } else {
                Min_HeapSort(Array, idx, 1);
            }



            Swap(Array, 1, (Array.length - 1) - idx);

        }

    }

    static void Show_Array(int[] Array) {
        System.out.println();
        for (int idx = 0; idx < Array.length; idx++) {
            // if(idx == Array.length-1){
            //   System.out.printf("[%2d]%3d", idx, Array[idx]);
            // }
            // System.out.printf("[%2d]%3d, ", idx, Array[idx]);
            System.out.printf("%4d", Array[idx]);
        }
        System.out.println();
    }

    static void Show_inorder(int[] Array, int idx) {
        // Left - Root - Right >> 중위순회
        if (idx * 2 <= Array.length - 1) {
            Show_inorder(Array, idx * 2);
        }

        System.out.printf("%4d", Array[idx]);
        //System.out.printf("[%2d]%3d", idx, Array[idx]);

        if ((idx * 2) + 1 <= Array.length - 1) {
            Show_inorder(Array, (idx * 2) + 1);
        }
    }

    static void Show_preorder(int[] Array, int idx) {
        // Root - Left - Right >> 전위순회
        System.out.printf("%4d", Array[idx]);
        //System.out.printf("[%2d]%3d", idx, Array[idx]);

        if (idx * 2 <= Array.length - 1) {
            Show_preorder(Array, idx * 2);
        }

        if ((idx * 2) + 1 <= Array.length - 1) {
            Show_preorder(Array, (idx * 2) + 1);
        }
    }

    static void Show_postorder(int[] Array, int idx) {
        // Left - Right - Root >> 후위순회
        if (idx * 2 <= Array.length - 1) {
            Show_postorder(Array, idx * 2);
        }

        if ((idx * 2) + 1 <= Array.length - 1) {
            Show_postorder(Array, (idx * 2) + 1);
        }
        System.out.printf("%4d", Array[idx]);
        //System.out.printf("[%2d]%3d", idx, Array[idx]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] Array = Init_Array(scanner);

        Show_Array(Array);

        System.out.println("Min_Heap : 1 || Max_Heap : else");
        int select;
        if ((select = scanner.nextInt()) == 1) {
            Min_Heapify(Array);
        } else {
            Max_Heapify(Array);
        }

        Show_Array(Array);

        System.out.print("Sort : ");
        Heap_sort(Array, select);
        Show_Array(Array);

        System.out.print("Pre_order  : ");
        Show_preorder(Array, 1);
        System.out.println();

        System.out.print("In_order   : ");
        Show_inorder(Array, 1);
        System.out.println();

        System.out.print("Post_order : ");
        Show_postorder(Array, 1);
        System.out.println();

        scanner.close();
    }
}
