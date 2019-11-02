package wrx.xing.data_structures.search;

/**
 * 请填写类的描述
 * 插值查找
 *
 * @author wangruxing
 * @date 2019-10-04 17:26
 */
public class InsertValSearchDemo {
	public static void main(String[] args) {
		int[] arr = new int[100];
		for (int i = 0; i < 100; i++) {
			arr[i] = i + 1;
		}
		System.out.println(insertValSearch(arr, 11, 0, arr.length - 1));
	}

	/**
	 * @param arr     原数组
	 * @param findVal 要查找的元素
	 * @param left    左边索引
	 * @param right   右边索引
	 * @return 找到返回对应的索引，没找到返回-1
	 */
	private static int insertValSearch(int[] arr, int findVal, int left, int right) {
		System.out.println("hello~");
		if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
			return -1;
		}

		int midIndex = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
		int midVal = arr[midIndex];

		if (findVal > midVal) {
			return insertValSearch(arr,findVal,midIndex + 1,right);
		}else if(findVal < midVal) {
			return insertValSearch(arr,findVal,left,midIndex - 1);
		}else {
			return midIndex;
		}
	}
}
