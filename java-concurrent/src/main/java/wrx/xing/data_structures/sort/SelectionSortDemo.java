package wrx.xing.data_structures.sort;

import java.util.Arrays;

/**
 * 2
 * 请填写类的描述
 * 选择排序
 * 时间复杂度O(n^2)
 * @author wangruxing
 * @date 2019-10-03 14:52
 */
public class SelectionSortDemo {
	public static void main(String[] args) {
		int[] arr = {3,9,-1,10,-2};
		selectionSort(arr);
	}

	/**
	 * 说明
	 * 1：一共有数组大小-1轮排序
	 * 2：假定第一个元素是最小的，依次与后面的比较，第一轮后找到最小的并交换。
	 * @param arr 数组
	 */
	private static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex  = i;
			int min = arr[i];
			for (int j = 1 + i; j < arr.length; j++) {
				if (min > arr[j]) {
					min = arr[j];
					minIndex = j;
				}
			}

			if (minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = min;
			}
		}

		System.out.println(Arrays.toString(arr));
	}
}
