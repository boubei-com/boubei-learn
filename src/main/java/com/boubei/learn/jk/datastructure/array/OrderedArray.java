package com.boubei.learn.jk.datastructure.array;

/**
* @author Jon.King  2005-8-24
*
* 排序数组
*/
public class OrderedArray {
	private double[] a; // ref to array a

	private int nElems; // 数组长度

	public OrderedArray(int max) {
		a = new double[max];
		nElems = 0;
	}

	public int size() {
		return nElems;
	}

	/**
	 * 排序数组的二分查找，返回匹配数据在数组中的下标，没找到则返回数组length
	 * @param searchKey
	 * @return
	 */
	public int find(double searchKey) {
		int lowerBound = 0;
		int upperBound = nElems - 1;
		int curIn;
		while (true) {
			curIn = (lowerBound + upperBound) / 2;
			if (a[curIn] == searchKey)
				return curIn;
			if (lowerBound > upperBound)
				return nElems;
			else {
				if (a[curIn] < searchKey)
					lowerBound = curIn + 1;
				else
					upperBound = curIn - 1;
			}
		}

	}

	/**
	 * 排序数组插入
	 * @param value
	 */
	public void insert(double value) {
		int j;
		//找到要插入数据的位置j
		for (j = 0; j < nElems; j++)
			if (a[j] > value)
				break;
		//将位置j之后的数后移一个位置
		for (int k = nElems; k > j; k--)
			a[k] = a[k - 1];
		a[j] = value;
		nElems++;
	}

	/**
	 * 排序数组删除
	 * @param value
	 * @return
	 */
	public boolean delete(double value) {
		int j = find(value);                
		if (j == nElems)
			return false;
		else {
			//将位置j之后的数前移一个位置
			for (int k = j; k < nElems; k++)
				a[k] = a[k + 1];
			nElems--;
			return true;
		}
	}

	public void display() {
		for (int j = 0; j < nElems; j++)
			System.out.print(a[j] + " ");
		System.out.println("");
	}
	
	public static void main(String[] args) {
		int maxSize = 100;
		OrderedArray arr = new OrderedArray(maxSize);
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
		int searchKey = 55;
		if (arr.find(searchKey) != arr.size())
			System.out.println("Found " + searchKey);
		else
			System.out.println("Can't find " + searchKey);
		arr.display();
		arr.delete(00);
		arr.delete(55);
		arr.delete(99);
		arr.display();
	}

}
