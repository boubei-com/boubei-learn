package jinhe.lt.datastructure.array;

/**
 * @author 金普俊 各种排序算法
 */

public class Sort {
	private double[] a; // ref to array a

	private int nElems; // number of data items

	public Sort(int max) {
		a = new double[max];
		nElems = 0;
	}

	public void insert(double value) {
		a[nElems] = value;
		nElems++;
	}

	public void display() {
		for (int j = 0; j < nElems; j++)
			System.out.print(a[j] + " ");
		System.out.println("");
	}

	/**
	 * 冒泡排序（bubble sort）：  比较：O(N*N), 交换O(N*N)
     *                                  1. Compare two players.
	 *				 				    2. If the one on the left is taller, swap them.
	 *				 					3. Move one position right.
	 */
	public void bubbleSort() {
		int out, in;
		for (out = nElems - 1; out > 1; out--)
			for (in = 0; in < out; in++)
				if (a[in] > a[in + 1])
					swap(in, in + 1);
	}

	/**
	 * 选择排序（Selection sort） 比较：O(N*N), 交换O(N)
	 *		                            1.循环，记下当前循环再小的一个
	 *		                            2.将记录下的和当前循环最左边的数交换
	 */
	public void selectionSort() {
		int out, in, min;
		for (out = 0; out < nElems - 1; out++) {
			min = out;
			for (in = out + 1; in < nElems; in++)
				if (a[in] < a[min])
					min = in;
			swap(out, min);
		}
	}

	private void swap(int one, int two) {
		double temp = a[one];
		a[one] = a[two];
		a[two] = temp;
	}

	public static void main(String[] args) {
		int maxSize = 100;
		Sort arr;
		arr = new Sort(maxSize);
		arr.insert(77);
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);

		arr.display();
		//arr.bubbleSort();
		arr.selectionSort();
		arr.display();
	}

}