package wrx.xing.data_structures.sort;

import java.util.Arrays;

/**
 * 1
 * 请填写类的描述
 * 冒泡排序
 * 时间复杂度：O(n^2)
 * @author wangruxing
 * @date 2019-10-03 14:13
 */
public class BubbleSortDemo {

	public static void main(String[] args) {
//		int[] arr = {3,9,-1,10,-2};
		int[] arr = {1,2,3,4,5,6};
		bubbleSort(arr);

	}

	/**
	 * 冒泡排序规则
	 * 1：一共进行数组-1次的大的循环
	 * 2：每一趟的排序次数在逐渐减少
	 * 3：如果我们发现某趟排序中没有发生一次交换，可以提前结束排序，这个就是优化
	 * @param arr 数组
	 */
	private static void bubbleSort(int[] arr) {
		System.out.println("原数组：" + Arrays.toString(arr));
		int temp;
		// 这里是优化，如果在一趟排序中没有发生过交换则证明已经排好序了。
		boolean flag = false;
		for (int j = 0; j < arr.length - 1; j++) {
			for (int i = 0; i < arr.length - 1 - j; i++) {

				if (arr[i] > arr[i + 1]) {
					flag = true;
					temp = arr[i + 1];
					arr[i + 1] = arr[i];
					arr[i] = temp;
				}
			}
			System.out.print("第" + (j + 1) + "趟排序后的数组：");
			System.out.println(Arrays.toString(arr));

			if (flag) {
				flag = false;
			}else {
				break;
			}
		}
	}

}
