package wrx.xing.data_structures.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-10-04 16:31
 */
public class BinarySearchDemo {
	public static void main(String[] args) {
		int[] arr = {1,4,7,9,12,15,18,19,23,25,28};
		System.out.println(binarySearchNoRecursion(arr,15));
	}
	private static int binarySearchNoRecursion(int[] arr,int target) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (target == arr[mid]) {
				return mid;
			}else if (target > arr[mid]) {
				left = mid + 1;
			}else {
				right = mid - 1;
			}
		}
		return -1;
	}
	private static int binarySearch(int[] arr, int findVal, int left, int right) {

		if (left > right) {
			return -1;
		}
		int midIndex = (left + right) / 2;
		int midVal = arr[midIndex];

		if (findVal > midVal) {
			return binarySearch(arr, findVal, midIndex + 1, right);
		} else if (findVal < midVal) {
			return binarySearch(arr, findVal, left, midIndex - 1);
		} else {
			return midIndex;
		}
	}


	private static int binarySearch2(int[] arr,int findVal) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			int index = (left + right) / 2;
			if (findVal > arr[index]) {
				left = index + 1;
			}else if (findVal < arr[index]) {
				right = index - 1;
			}else {
				return index;
			}
		}
		return -1;
	}


}
