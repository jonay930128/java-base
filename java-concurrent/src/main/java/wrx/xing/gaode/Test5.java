package wrx.xing.gaode;

import java.util.Arrays;
import java.util.Random;

/**
 * 请填写类的描述
 * 随机填充一个 length 为 100 的 int 数组，使用 冒泡排序法 对该数组进行升序排序，并打印排序后的结果
 * @author wangruxing
 * @date 2020-02-07 14:15
 */
public class Test5 {
	public static void main(String[] args) {
		int[] arr = new int[100];
		for (int i = 0; i < 100; i++) {
			arr[i] = new Random().nextInt(1000);
		}
		System.out.println("原始数组：" + Arrays.toString(arr));
		// 冒泡排序
		bubbleSort(arr);
		System.out.println("排序后数组：" + Arrays.toString(arr));
	}

	private static void bubbleSort(int[] arr) {
		int temp;
		for (int j = 0; j < arr.length - 1; j++) {
			for (int i = 0; i < arr.length - 1 - j; i++) {
				if (arr[i] > arr[i + 1]) {
					temp = arr[i + 1];
					arr[i + 1] = arr[i];
					arr[i] = temp;
				}
			}
		}
	}
}
