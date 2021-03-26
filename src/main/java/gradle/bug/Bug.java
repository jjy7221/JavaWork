package gradle.bug;

import java.util.Random;

class Pos{
    int y;
    int x;

    Pos(int[][] Array, Random random){
        this.y = random.nextInt(Array.length);
        this.x = random.nextInt(Array[0].length);

    }
}

public class Bug {
    static void NextPos(int[][] Array) {

        Random random = new Random(); //랜덤 객체 생성(디폴트 시드값 : 현재시간)
        random.setSeed(System.currentTimeMillis());

        Pos spot = new Pos(Array, random);

        int next;
        int y=0 ,x=0, move = 0;
        Array[spot.y][spot.x] += 1;

        while (Check_exit(Array, spot) || spot.x != Array[0].length -1 || spot.y != Array.length-1) {

            next = random.nextInt(8);

            y= spot.y;
            x= spot.x;

            switch (next) {
                case 1:
                    if (spot.x + 1 < Array[0].length) {
                        spot.x += 1;
                        Array[spot.y][spot.x] += 1;
                    }
                    break;
                case 2:
                    if (spot.x + 1 < Array[0].length && spot.y - 1 >= 0) {
                        spot.x += 1;
                        spot.y -= 1;
                        Array[spot.y][spot.x] += 1;
                    }
                    break;
                case 3:
                    if (spot.y - 1 >= 0) {
                        spot.y -= 1;
                        Array[spot.y][spot.x] += 1;
                    }
                    break;
                case 4:

                    if (spot.x - 1 >= 0 && spot.y - 1 >= 0) {
                        spot.x -= 1;
                        spot.y -= 1;
                        Array[spot.y][spot.x] += 1;
                    }
                    break;
                case 5:
                    if (spot.x - 1 >= 0) {
                        spot.x -= 1;
                        Array[spot.y][spot.x] += 1;
                    }
                    break;
                case 6:
                    if (spot.x - 1 >= 0 && spot.y + 1 < Array.length) {
                        spot.x -= 1;
                        spot.y += 1;
                        Array[spot.y][spot.x] += 1;
                    }
                    break;
                case 7:
                    if (spot.y + 1 < Array.length) {
                        spot.y += 1;
                        Array[spot.y][spot.x] += 1;
                    }
                    break;
                default:
                    if (spot.x + 1 < Array[0].length && spot.y + 1 < Array.length) {
                        spot.x += 1;
                        spot.y += 1;
                        Array[spot.y][spot.x] += 1;
                    }
                    break;
            }

            if(x != spot.x || y != spot.y) {
                Show_Array(Array, spot);
                move ++;
            }
        }
        System.out.println("총 움직인 횟수는 "+move+" 움직였습니당.");
    }

    static void Show_Array(int[][] Array, Pos bug) {
        System.out.println();
        for (int i = 0; i < Array.length; i++) {
            for (int j = 0; j < Array[0].length; j++) {
                if (i == bug.y && j == bug.x) {
                    System.out.print("   *");
                } else
                    System.out.printf("%4d", Array[i][j]);
            }
            System.out.println();
        }
    }

    static boolean Check_exit(int[][] Array, Pos bug) {
        for (int i = 0; i < Array.length; i++) {
            for (int j = 0; j < Array[0].length; j++) {
                if (Array[i][j] == 0) {
                    return true;
                }

            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] Array = new int[8][8];

        NextPos(Array);

    }
}
