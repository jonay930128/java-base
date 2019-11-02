package wrx.xing.data_structures.sort;

import java.util.Arrays;

/**
 * 3
 * 请填写类的描述
 * 插入排序
 * 最差：最小的数字在最后一个，那么从小到大排序就要全部移动一遍
 * 所以优化后：希尔排序
 * @author wangruxing
 * @date 2019-10-03 16:12
 */
public class InsertSortDemo {
	public static void main(String[] args) {
		int[] arr = {3,9,-1,10,-2};
		insertSort(arr);
	}

	/**
	 * 大循环次数为数组长度-1次
	 * 把数组看成两个部分，第一部分是要插入的部分，从第二部分中取数往第一部分中插入
	 *
	 * @param arr 数组
	 */
	private static void insertSort(int[] arr) {
		int insertVal;
		int insertIndex;
		// 从第二部分的第一个位置开始遍历，因为第0个位置已经确定了。
		for (int i = 1; i < arr.length; i++) {
			// 这是要插入的元素
			insertVal = arr[i];
			// 这是第一部分的首次要插入的位置，也是第一部分数组有效长度。
			insertIndex = i - 1;
			// 如果发现要插入的比较小，则依次跟前面的比较，并把前面的向后移动
			while (insertIndex >= 0 && arr[insertIndex] > insertVal) {
				arr[insertIndex + 1] = arr[insertIndex];
				insertIndex--;
			}

			arr[insertIndex + 1] = insertVal;
		}
		System.out.println(Arrays.toString(arr));
	}



}
