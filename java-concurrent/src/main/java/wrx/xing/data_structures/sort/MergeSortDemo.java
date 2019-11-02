package wrx.xing.data_structures.sort;

import java.util.Arrays;

/**
 * 6
 * 请填写类的描述
 * 归并算法
 *
 * @author wangruxing
 * @date 2019-10-04 13:19
 */
public class MergeSortDemo {
	public static void main(String[] args) {
		int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
		int[] temp = new int[arr.length];
		mergeSort(arr, 0, arr.length - 1, temp);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 归并排序，类似fork/join思想
	 * 先拆分，拆到最小力度再合并
	 * @param arr   原始数组
	 * @param left  左边起索引
	 * @param right 右边起始索引
	 * @param temp  临时数组
	 */
	//分+合方法
	private static void mergeSort(int[] arr, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2; //中间索引
			//向左递归进行分解
			mergeSort(arr, left, mid, temp);
			//向右递归进行分解
			mergeSort(arr, mid + 1, right, temp);
			//合并
			merge(arr, left, mid, right, temp);
		}
	}

	/**
	 * 合并算法
	 *
	 * @param arr   原始数组
	 * @param left  左边有序序列的初始索引
	 * @param mid   中间索引
	 * @param right 右边有序的初始索引
	 * @param temp  中转数组
	 */
	private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		// 初始化左边的索引
		int l = left;
		// 初始化右边的索引
		int r = mid + 1;
		// 指向temp的当前索引
		int tempIndex = 0;

		// 1:
		// 先把左右两边(有序)的数据按照规则填充到temp数组
		// 直到左右两边的有序序列有一边处理完毕为止
		while (l <= mid && r <= right) {
			if (arr[l] <= arr[r]) {
				temp[tempIndex] = arr[l];
				l++;
				tempIndex++;
			} else {
				temp[tempIndex] = arr[r];
				r++;
				tempIndex++;
			}
		}

		// 2：
		// 把所有剩下的数据的一边的数据依次全部填充到temp
		while (l <= mid) {
			temp[tempIndex] = arr[l];
			l++;
			tempIndex++;
		}
		while (r <= right) {
			temp[tempIndex] = arr[r];
			r++;
			tempIndex++;
		}

		// 3：
		// 将temp数组的元素拷贝到arr，注意并不是每次都全部拷贝
		// 初始化temp数组下标，从0开始
		tempIndex = 0;
		int tempLeft = left;
		// 第一次合并 tempLeft = 0 , right = 1 //  tempLeft = 2  right = 3 // tL=0 ri=3
		// 最后一次 tempLeft = 0  right = 7
		while (tempLeft <= right) {
			arr[tempLeft] = temp[tempIndex];
			tempLeft++;
			tempIndex++;
		}
	}
}
