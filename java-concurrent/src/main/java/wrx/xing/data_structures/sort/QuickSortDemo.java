package wrx.xing.data_structures.sort;

import java.util.Arrays;

/**
 * 5
 * 请填写类的描述
 * 快速排序
 * @author wangruxing
 * @date 2019-10-04 10:04
 */
public class QuickSortDemo {
	// 设定基准值（pivot）
//	int index = left + 1;
//		for (int i = index; i <= right; i++) {
//		if (arr[i] < arr[left]) {
//			swap(arr, i, index);
//			index++;
//		}
//	}
//	swap(arr, left, index - 1);
//		return index - 1;
	public static void main(String[] args) {
//		int[] arr = {3,9,-1,10,-2};
		int[] arr = {42, 40, 67, 71, 49, 8, 2, 75};
//		int[] arr = new int[8];
//		for (int i = 0; i < 8; i++) {
//			arr[i] = (int)(Math.random() * 80);
//		}
		System.out.println(Arrays.toString(arr));
		long start = System.currentTimeMillis();
		quickSort(arr,0,arr.length - 1);
		System.out.println(Arrays.toString(arr));
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	/**
	 * 先找一个基准，比如中间那个元素
	 * 基准左边的向右边移动，右边的向左边移动
	 * 左边发现比基准大停下，右边发现比基准小停下，然后他俩交换
	 * 左边重复上面步骤递归
	 * 右边重复上面步骤递归
	 * @param arr 数组
	 */
	private static void quickSort2(int[] arr,int left,int right) {
		// 记录中轴位置元素，以他为基准
		int pivot = arr[(left + right) / 2];
		// 左边
		int l = left;
		// 右边
		int r = right;
		// 临时变量交换时用
		int temp;
		while (l < r) {
			// 左边的小于基准就找下一个
			while (arr[l] < pivot) {
				l++;
			}
			// 右边的大于基准就继续往前找
			while (arr[r] > pivot) {
				r--;
			}

			// 如果l >= r说明pivot 的左右两的值，已经按照左边全部是
			// 小于等于pivot值，右边全部是大于等于pivot值
			// 因为下面的操作可能导致下标越界(67/70行)，所以在这加个判断。
			if( l >= r) {
				break;
			}

			// 交换
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;

			// 如果发现交换后的数据与基准相等，那么让下标后移
			// 如：[45,55,45,35,45] ---> 经过上述循环和交换后变为：[45,55,45,35,45],因为l,r都没有改变，
			// 所以要执行下面两个判断，好继续比较，不然就是死循环了。
			// 因为是交换后的位置，所以要反过来加/减
			if (arr[l] == pivot) {
				r--;
			}
			if (arr[r] == pivot) {
				l++;
			}
		}
		// 经过上面的分区后，如果l等于r那么就让他们各自往下移动一位
		// 如：[45,55,45,35,45] ---> 经过上面循环后：[45,35,45,55,45]，l=2,r=2,
		// 如果不移位，下面的递归将错误
		if (l == r) {
			l++;
			r--;
		}

		// 左边递归
		if (left < r) {
			quickSort(arr,left,r);
		}
		// 右边递归
		if (l < right) {
			quickSort(arr,l,right);
		}
	}


	private static int[] quickSort(int[] arr, int left, int right) {
		if (left < right) {
			int partitionIndex = partition(arr, left, right);
			quickSort(arr, left, partitionIndex - 1);
			quickSort(arr, partitionIndex + 1, right);
		}
		return arr;
	}
	private static int partition(int[] arr, int left, int right) {
		int index = left + 1;
		for (int i = index; i <= right; i++) {
			if (arr[left] > arr[i]) {
				swap(arr,index,i);
				index++;
			}
		}
		swap(arr,left,index - 1);
		return index - 1;
	}
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private int[] sort(int[] arr,int left,int right) {
		if (left < right) {
			int partitionIndex = findPartition(arr,left,right);
			sort(arr,left,partitionIndex - 1);
			sort(arr,partitionIndex + 1,right);
		}
		return arr;
	}

	private int findPartition(int[] arr, int left, int right) {
		int index = left + 1;
		for (int i = index; i <= right; i++) {
			if (arr[left] > arr[i]) {
				swap(arr,index,i);
				index++;
			}
		}
		swap(arr,left,index - 1);
		return index - 1;
	}
}
