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
//		int[] arr = {1,2,33,4,5,33,6,7,8,8,8,8,9,34};
//		System.out.println(binarySearch2(arr,8,0,arr.length));

		int[] arr = {1,4,7,9,12,15,18,19,23,25,28};
		System.out.println(binarySearchNoRecursion(arr,280));
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

	/**
	 * 错的
	 * @param arr
	 * @param findVal
	 * @param left
	 * @param right
	 * @return
	 */
	private static List<Integer> binarySearch2(int[] arr,int findVal, int left, int right) {
		// 当 left > right 时，说明递归整个数组，但是没有找到
		if (left > right) {
			return new ArrayList<Integer>();
		}
		int mid = (left + right) / 2;
		int midVal = arr[mid];

		if (findVal > midVal) { // 向 右递归
			return binarySearch2(arr, findVal,mid + 1, right);
		} else if (findVal < midVal) { // 向左递归
			return binarySearch2(arr,findVal, left, mid - 1);
		} else {
//			 * 思路分析
//			 * 1. 在找到mid 索引值，不要马上返回
//			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 4. 将Arraylist返回

			List<Integer> resIndexlist = new ArrayList<Integer>();
			//向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
			int temp = mid - 1;
			while(true) {
				if (temp < 0 || arr[temp] != findVal) {//退出
					break;
				}
				//否则，就temp 放入到 resIndexlist
				resIndexlist.add(temp);
				temp -= 1; //temp左移
			}
			resIndexlist.add(mid);  //

			//向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
			temp = mid + 1;
			while(true) {
				if (temp > arr.length - 1 || arr[temp] != findVal) {//退出
					break;
				}
				//否则，就temp 放入到 resIndexlist
				resIndexlist.add(temp);
				temp += 1; //temp右移
			}

			return resIndexlist;
		}
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
}
