package com.boubei.learn.jk.arithmetic;

/**
 * @author Jon.King  2007-8-1
 *
 * 骑士巡游问题
 */
public class Rider {
	int a[][]; // 走过的位置（坐标）

	int b[][] = { { 2, 3, 4, 3, 2 }, { 3, 4, 6, 4, 3 }, { 4, 6, 8, 6, 4 },
			{ 3, 4, 6, 4, 3 }, { 2, 3, 4, 3, 2 } };// 定义每一格有多少种走法

	int h[]; // 横向

	int v[]; // 纵向

	int currentRow; // 当前横向位置

	int currentColumn; // 当前纵向位置

	int total; // 走的步数

	public Rider() {
		total = 0;
		currentRow = 0;
		currentColumn = 0;
		a = new int[5][5];
		h = new int[8];
		v = new int[8];

		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				a[i][j] = 0;
		a[0][0] = 1;

		h[0] = 2;
		h[1] = 1;
		h[2] = -1;
		h[3] = -2;
		h[4] = -2;
		h[5] = -1;
		h[6] = 1;
		h[7] = 2;

		v[0] = -1;
		v[1] = -2;
		v[2] = -2;
		v[3] = -1;
		v[4] = 1;
		v[5] = 2;
		v[6] = 2;
		v[7] = 1;
	}

	public void move(int moveNumber) {
		currentRow += h[moveNumber];
		currentColumn += v[moveNumber];
	}

	public void moveback(int moveNumber) {
		currentRow -= h[moveNumber];
		currentColumn -= v[moveNumber];
	}

	public boolean isout() {
		if (currentRow < 0 || currentRow >= 5 || currentColumn >= 5
				|| currentColumn < 0)
			return true;
		else
			return false;
	}

	public void start_care() {
		int min_j = 8;  //一格能拥有的最多的走法种类为8
		int moveNumber = 0;
		
		//找出当前位置的下一步再走下一步时走法最少的那个位置，记录在上面两个变量里
		for (int i = 0; i < 8; i++) {
			move(i);

			if (isout() || (a[currentRow][currentColumn] == 1)) {
				//如果出界或者走到已经走过的，则返回
				moveback(i);
			} else {
				if (b[currentRow][currentColumn] <= min_j) {
					min_j = b[currentRow][currentColumn];  
					moveNumber = i;
				}
				moveback(i);
			}
		}
		move(moveNumber);
		b[currentRow][currentColumn] = b[currentRow][currentColumn] - 1;
		a[currentRow][currentColumn] = 1;
		total += 1;
		System.out.println("(" + currentRow + "," + currentColumn + ")");
	}
    
    public static void main(String[] args) {
        Rider test = new Rider();
        while (test.total < 24)
            test.start_care();
    }
}
