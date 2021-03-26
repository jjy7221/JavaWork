package gradle.Matrix;

import java.util.Random;

public class Matrix {
    static void initMatrix(int Matrix[][]) {

        Random random = new Random(); //랜덤 객체 생성(디폴트 시드값 : 현재시간)
        random.setSeed(System.currentTimeMillis());

        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix[0].length; j++) {
                Matrix[i][j] = random.nextInt(3);
            }
        }
    }

    static void ShowMat(int Matrix[][]) {
        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix[0].length; j++) {
                System.out.print(Matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void Add(int Matrix[][], int Matrix2[][]) {
        if (CheckRC(Matrix, Matrix2)) {
            System.out.println("Fail to Add");
        } else {
            for (int i = 0; i < Matrix.length; i++) {
                for (int j = 0; j < Matrix[0].length; j++) {
                    Matrix[i][j] += Matrix2[i][j];
                }
            }
        }
    }

    static void Sub(int Matrix[][], int Matrix2[][]) {
        if (CheckRC(Matrix, Matrix2)) {
            System.out.println("Fail to Sub");
        } else {
            for (int i = 0; i < Matrix.length; i++) {
                for (int j = 0; j < Matrix[0].length; j++) {
                    Matrix[i][j] -= Matrix2[i][j];
                }
            }
        }
    }

    static int[][] Multiply(int Matrix[][], int Matrix2[][]) {
        int[][] ret;

        if (CheckMul(Matrix, Matrix2)) {
            if (CheckMul(Matrix2, Matrix)) {
                System.out.println("Fail to Multiply");
                return ret = new int[Matrix.length][Matrix2[0].length];
            } else {
                ret = new int[Matrix2.length][Matrix[0].length];

                for (int i = 0; i < Matrix2.length; i++) {
                    for (int j = 0; j < Matrix[0].length; j++) {
                        for (int k = 0; k < Matrix2[0].length; k++) {
                            ret[i][j] += Matrix2[i][k] * Matrix[k][j];
                        }
                    }
                }
                return ret;
            }
        } else {
            ret = new int[Matrix.length][Matrix2[0].length];

            for (int i = 0; i < Matrix.length; i++) {
                for (int j = 0; j < Matrix2[0].length; j++) {
                    for (int k = 0; k < Matrix[0].length; k++) {
                        ret[i][j] += Matrix[i][k] * Matrix2[k][j];
                    }
                }
            }
        }
        return ret;
    }

    static int[][] Transpose(int[][] Matrix) {
        int[][] ret = new int[Matrix[0].length][Matrix.length];

        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix[0].length; j++) {
                ret[j][i] = Matrix[i][j];
            }
        }
        return ret;
    }

    static boolean CheckRC(int Matrix[][], int Matrix2[][]) {
        if (Matrix.length != Matrix2.length || Matrix[0].length != Matrix2[0].length) {
            return true;
        }
        return false;
    }

    static boolean CheckMul(int Matrix[][], int Matrix2[][]) {
        if (Matrix[0].length != Matrix2.length) {
            return true;
        }
        return false;
    }

    // main fucn
    public static void main(String[] args) {

        int A[][] = new int[2][6];
        initMatrix(A);
        System.out.println("A-Array");
        ShowMat(A);
        int B[][] = new int[2][3];
        initMatrix(B);
        System.out.println("B-Array");
        ShowMat(B);
        int C[][] = Multiply(A, B);
        System.out.println("C-Array");
        ShowMat(C);
        int D[][] = new int[2][3];
        initMatrix(D);
        System.out.println("D-Array");
        ShowMat(D);

        Add(A, D);
        System.out.println("Add(A-Array)");
        ShowMat(A);

        Sub(D, A);
        System.out.println("Sub(D-Array)");
        ShowMat(D);

        int E[][];
        E = Transpose(D);
        System.out.println("Transpose(D-Array)");
        ShowMat(E);
    }
}


