package com.learning.eightqueue;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author fanyuwen
 * @date 2019/10/20 11:32
 * 著名的八皇后问题: 在一个 8 * 8的棋盘上摆出所有棋子满足都不在同一行/同一列/同一斜对角线的情况,打印出
 * 所有的情况
 */
public class EightQueen {
    private static final int LENGTH = 8, HEIGHT = 8;

    /**
     * 判断当前棋子是否满足前面已满足棋子的要求
     *
     * @param frontPieces     前面满足棋子的数组
     * @param currentAbscissa 当前棋子的横坐标
     * @param currentOrdinate 当前棋子的纵坐标
     * @return true:满足,false:不满足
     */
    private boolean computePieceIsSatisfy(int[] frontPieces, int currentAbscissa, int currentOrdinate) {
        if (currentAbscissa == 0) {
            return true;
        }
        for (int frontAbscissa = 0; frontAbscissa < currentAbscissa; frontAbscissa++) {
            //如果横坐标相同,纵坐标相同
            //用y=x+b/y=x-b/y=-x+b/y=-x-b
            if (frontPieces[frontAbscissa] == currentOrdinate ||
                    frontPieces[frontAbscissa] - frontAbscissa == currentOrdinate - currentAbscissa ||
                    frontPieces[frontAbscissa] + frontAbscissa == currentOrdinate + currentAbscissa) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        Queen queen = new Queen();
//        queen.placeQueen(8);
        //=============================================
        EightQueen queen = new EightQueen();
        queen.fun();

        /* 第1组解: 0 4 7 5 2 6 1 3
         * 第2组解: 0 5 7 2 6 3 1 4
         * 第3组解: 0 6 3 5 7 1 4 2
         * 第4组解: 0 6 4 7 1 3 5 2
         */
    }

    public final void computeEightQueue() {
        int[] ints = new int[LENGTH];
        Arrays.fill(ints, 0);
        fun(ints, 0);
        System.out.println("八皇后结果: " + count + " 个");

        /*
         * [0, 6, 3, 5, 7, 1, 4, 2]
         * [2, 4, 1, 7, 0, 6, 3, 5]
         * [2, 4, 1, 7, 5, 3, 6, 0]
         * [2, 4, 6, 0, 3, 1, 7, 5]
         * [2, 6, 1, 7, 4, 0, 3, 5]
         * [4, 0, 3, 5, 7, 1, 6, 2]
         * [4, 0, 7, 3, 1, 6, 2, 5]
         * [4, 2, 0, 5, 7, 1, 3, 6]
         * [4, 2, 7, 3, 6, 0, 5, 1]
         * [4, 6, 0, 2, 7, 5, 3, 1]
         * [4, 6, 3, 0, 2, 7, 5, 1]
         * [6, 0, 2, 7, 5, 3, 1, 4]
         * [6, 2, 0, 5, 7, 4, 1, 3]
         * [6, 4, 2, 0, 5, 7, 1, 3]
         */
    }

    private static int count = 0;

    private void fun(int[] array, int heng) {
        if (heng >= LENGTH) {
            System.out.println(Arrays.toString(array));
            count++;
            return;
        }
        for (int z = 0; z < HEIGHT; z++) {
            if (computePieceIsSatisfy(array, heng, z)) {
                array[heng] = z;
                fun(array, heng + 1);
            }
        }
    }

    //只使用一个移位变量就可以了,改天头脑清醒了可以试试
    public void fun() {
        //
        int[] array = new int[LENGTH];
        int current_heng = 0, current_zong = 0;
        int history_heng = 0;
        s:
        while (true) {
            //先判断是否已经
            if (current_heng == history_heng) {
                array[history_heng] = current_zong;
                current_heng++;
                current_zong = 0;
                continue;
            }
            if (history_heng >= LENGTH - 1) {
                System.out.println(Arrays.toString(array));
                current_heng = history_heng;
                current_zong = array[history_heng] + 1;
                history_heng--;
                continue;
            }
            for (int i = 0; i <= history_heng; i++) {
                //判断是否在皇后的势力范围
                if (current_zong == array[i] ||
                        i + array[i] == current_heng + current_zong ||
                        i - array[i] == current_heng - current_zong
                ) {
                    while (current_zong >= LENGTH - 1) {
                        current_heng = history_heng;
                        if (history_heng != 0) {
                            history_heng--;
                        } else {
                            if (array[current_heng] == LENGTH - 1) {
                                break s;
                            }
                        }
                        current_zong = array[current_heng] + 1;
                    }
                    current_zong++;
                    continue s;
                }
            }
            array[++history_heng] = current_zong;
            current_heng++;
            current_zong = 0;
        }
    }

    /**
     * 网上某个实现方式
     */
    static class Queen {
        private int x;
        private int y;

        public Queen(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Queen() {
        }

        public boolean isSafe(Queen q) {
            return !(this.x == q.x ||
                    this.y == q.y ||
                    this.x + this.y == q.x + q.y ||
                    this.x - this.y == q.x - q.y);
        }

        public int setY(int N, ArrayList<Integer> list, int y) {
            Queen q;
            Queen q_exist;
            boolean flag;
            for (int z = y; z < N; z++) {
                q = new Queen(list.size(), z);
                flag = true;
                for (int x = 0; x < list.size(); x++) {
                    q_exist = new Queen(x, list.get(x));
                    if (!(q_exist.isSafe(q))) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    list.add(q.y);
                    return q.y;
                }
            }
            return -1;
        }

        private void placeQueen(int N) {
            int all = 1;
            int y = 0;
            int next_y = 0;
            ArrayList<Integer> list = new ArrayList<>();
            Queen queen;
            while (true) {
                if (list.size() == 0) {
                    queen = new Queen(0, y);
                    list.add(queen.y);
                    y++;
                } else {
                    if (list.size() == N) {
                        System.out.print("第" + all + "组解: ");
                        all++;
                        for (int num : list) {
                            System.out.print(num + " ");
                        }
                        System.out.println();
                        next_y = list.get(list.size() - 1) + 1;
                        list.remove(list.size() - 1);
                    } else {
                        if (-1 == setY(N, list, next_y)) {
                            next_y = list.get(list.size() - 1) + 1;
                            list.remove(list.size() - 1);
                            if (list.size() == 0) {
                                if (next_y < N) {
                                    list.add(next_y);
                                    next_y = 0;
                                } else {
                                    break;
                                }
                            }
                        } else {
                            next_y = 0;
                        }
                    }
                }
            }
        }
    }
}