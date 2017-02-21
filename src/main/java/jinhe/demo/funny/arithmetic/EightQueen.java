package jinhe.lt.funny.arithmetic;

import java.util.Date;

/**
 * <p>
 * EightQueen.java
 * </p>
 * n皇后问题算法。
 * 把棋盘看成一个坐标系，以左下角为原点（0,0)。坐标系的每个点为一个Point类。
 * 每个皇后为一个皇后对象Queen。
 * 判断一个点的坐标是否在，一个皇后控制的范围的函数为Queen.isUnderControl(point)。
 * 
 * @author 金普俊 2007-8-1
 * 
 */

public class EightQueen {

    Queen[] stack = new Queen[8];

    int sp = 0;

    int num = 0;

    public EightQueen() {
        num = 8;
        stack = new Queen[num];
        sp = 0;
    }

    public EightQueen(int num) {
        this.num = num;
        stack = new Queen[num];
        sp = 0;
    }

    /**
     * 打印皇后的坐标列表。
     */
    public void list() {
        System.out.println("Begin list the stack Point:");
        for (int i = 0; i < sp; i++) {
            stack[i].pos.println();
        }
        System.out.println("End list the stack Point:");
    }

    /**
     * 主算法流程。
     */
    public void calc() {
        sp = 0;
        stack[sp++] = new Queen();
        while (sp >= 0 && sp <= num - 1) {
            Queen queen = getQueen(sp);
            if (null == queen) {
                boolean flag = true;
                while (flag) {
                    --sp;
                    if (sp < 0)
                        break;
                    if (stack[sp].pos.y == num - 1) {

                    } else {
                        stack[sp++].pos.y++;
                        flag = false;
                        for (int k = 0; k < sp - 1; k++) {
                            if (stack[k].isUnderControl(stack[sp - 1].pos)) {
                                flag = true;
                                break;
                            }
                        }
                    }
                }

            } else {
                stack[sp++] = queen;
            }
        }
    }

    public Queen getQueen(int x) {
        boolean flag = true;
        int y = 0;
        while (flag) {
            flag = false;
            for (int i = 0; i < x; i++) {
                if (stack[i].isUnderControl(new Point(x, y))) {
                    flag = true;
                    break;
                }
            }
            if (flag && y <= num - 1) {
                y++;
            } else if (y >= num) {
                return null;
            }
        }
        return new Queen(new Point(x, y));
    }

    public static void main(String[] args) {
        EightQueen a = new EightQueen(20);
        long start = new Date().getTime();
        System.out.println("起始时间：[" + start + "]");
        a.calc();
        long end = new Date().getTime();
        System.out.println("截止时间:[" + end + "]");
        System.out.println("共耗时：[" + (end - start) + "]毫秒");
        if (a.sp > 0) {
            a.list();
        } else {
            System.out.println("这个题目无解！");
        }
    }
}

class Point {
    int x, y;

    public void println() {
        System.out.println("The Point is [x,y]=[" + x + "," + y + "]");
    }

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

class Queen {
    Point pos;

    public Queen() {
        pos = new Point();
    }

    public Queen(Point pos) {
        this.pos = pos;
    }

    public boolean isUnderControl(Point point) {
        boolean ret = true;
        if (point.x != pos.x && point.y != pos.y
                && Math.abs(point.x - pos.x) != Math.abs(point.y - pos.y)
                && Math.abs(point.x + point.y) != Math.abs(pos.x + pos.y)) {
            ret = false;
        }
        return ret;
    }
}
