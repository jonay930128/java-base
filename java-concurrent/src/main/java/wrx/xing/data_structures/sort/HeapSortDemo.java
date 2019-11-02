package wrx.xing.data_structures.sort;

import java.util.Arrays;

/**
 * 8
 * 请填写类的描述
 * 堆排序
 *
 * @author wangruxing
 * @date 2019-10-06 18:38
 */
public class HeapSortDemo {
	public static void main(String[] args) {
		int[] arr = {4, 6, 8, 5, 9};
		heapSort(arr);
	}

	/**
	 * 对排序，先构建一个大顶堆/小顶堆
	 *
	 * @param arr 原数组
	 */
	private static void heapSort(int[] arr) {
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}

		int temp = 0;
		for (int j = arr.length - 1; j > 0; j--) {
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
		}

		System.out.println("排序后：" + Arrays.toString(arr));

	}

	/**
	 * 将数组调整为大顶堆
	 *
	 * @param arr    待调整的数组
	 * @param i      非叶子节点在数组中的索引
	 * @param length 待调整的长度，逐渐减少
	 */
	private static void adjustHeap(int[] arr, int i, int length) {
		// 先取出当前元素的值，保存到临时变量中
		int temp = arr[i];
		// 说明：k = i*2+1 k是i节点的左子节点
		for (int k = i * 2 + 1; k < length; k = i * 2 + 1) {
			// 说明左子节点的值小于右子节点的值
			if (k + 1 < length && arr[k] < arr[k + 1]) {
				// k指向右子节点
				k++;
			}
			// 如果子节点大于父节点
			if (arr[k] > temp) {
				// 把较大的值赋给当前节点
				arr[i] = arr[k];
				// !!! i指向k，继续循环比较
				i = k;
			} else {
				break;
			}
		}

		// 当结束for循环后，我们已经将i为父节点的数最大值，放在了最顶（局部）
		arr[i] = temp;
	}

}
