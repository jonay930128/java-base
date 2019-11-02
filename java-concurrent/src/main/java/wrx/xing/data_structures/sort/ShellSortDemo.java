package wrx.xing.data_structures.sort;

import java.util.Arrays;

/**
 * 4
 * 请填写类的描述
 * 希尔排序
 * @author wangruxing
 * @date 2019-10-03 16:45
 */
public class ShellSortDemo {
	public static void main(String[] args) {
		int[] arr = {3,9,-1,10,-2};
		shellSort(arr);
	}

	/**
	 * 将数组分组/2，分组后再分组，直到组数小于等于1为止
	 * @param arr 数组
	 */
	private static void shellSort(int[] arr) {
		int temp;
		for (int group = arr.length / 2; group > 0; group /= 2) {
			// 遍历各组所有元素，步长为group
			for (int i = group; i < arr.length; i++) {
				for (int j = i - group;j >= 0;j -= group) {
					// 如果当前元素大于加上步长后的那个元素，说明交换
					if (arr[j] > arr[j + group]) {
						temp = arr[j];
						arr[j] = arr[j + group];
						arr[j + group] = temp;
					}
				}
			}
		}
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 移位法
	 * 效率高
	 * @param arr 数组
	 */
	private static void shellSort2(int[] arr) {
		int temp;
		// 增量group,并逐步缩小增量
		for (int group = arr.length / 2; group > 0; group /= 2) {
			for (int i = group; i < arr.length; i++) {
				int insertIndex = i;
				temp = arr[i];
				if (arr[insertIndex] < arr[insertIndex - group]) {
					while (insertIndex - group > 0 && temp < arr[insertIndex - group]) {
						// 移动
						arr[insertIndex] = arr[insertIndex - group];
						insertIndex -= group;
					}
					// 当退出循环后说明已经找到插入的位置
					arr[insertIndex] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
	}
}
