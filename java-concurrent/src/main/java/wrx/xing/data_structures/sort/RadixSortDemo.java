package wrx.xing.data_structures.sort;

import java.util.Arrays;

/**
 * 7
 * 请填写类的描述
 * 基数排序
 * 拿空间换时间，非常消耗内存，
 * 因为要额外开辟10个与原数组相同大小的桶
 * 此版本不支持负数排序
 *
 * @author wangruxing
 * @date 2019-10-04 14:50
 */
public class RadixSortDemo {
	public static void main(String[] args) {
		int[] arr = {53, 3, 542, 748, 14, 214};
		radixSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 基数排序
	 * 按照位数创建10个桶，因为0~9有十个数组
	 * 先找到数组里面最大的数，确定最大数是几位数，如234就是3位数，最多3轮
	 * 第一轮按取出个位的值，放入对应的桶中
	 * 然后再按照桶的编号依次将桶中的数据取出放入到原数组中
	 * 下一轮是十位，再下一轮是百位。直到达到最大的那轮。
	 *
	 * @param arr 原始数组
	 */
	private static void radixSort(int[] arr) {
		int[][] bucket = new int[10][arr.length];
		int[] bucketNum = new int[10];
		// 找到最大的数，获取最大的位数
		int max = arr[0];
		for (int item : arr) {
			if (item > max) {
				max = item;
			}
		}
		int times = String.valueOf(max).length();

		for (int i = 0, n = 1; i < times; i++, n *= 10) {
			// 放入桶中
			for (int j = 0; j < arr.length; j++) {
				int item = arr[j] / n % 10;
				bucket[item][bucketNum[item]] = arr[j];
				// 每次+1记录每个桶存入的有效个数，以及移动桶的下一个坐标。
				bucketNum[item]++;
			}
			// 按顺序从桶中取出
			int index = 0;
			// 一共10个桶
			for (int k = 0; k < 10; k++) {
				// 如果桶内有数据则取出
				if (bucketNum[k] != 0) {
					for (int s = 0; s < bucketNum[k]; s++) {
						arr[index++] = bucket[k][s];
					}
					bucketNum[k] = 0;
				}
			}
		}

	}
}
