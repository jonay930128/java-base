package wrx.xing.leecode;

import java.util.*;

/**
 *  * @author liuyumeng
 *  * @create 2019-12-18
 *  
 */
public class FindMatchArray {

	static Set<List<Integer>> set = new HashSet<>();

	public static void main(String[] args) {
		int[] arr = {1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9, 10};
		boolean[] booleans = new boolean[arr.length];
		long start = System.currentTimeMillis();
		find(arr, 0, booleans);
		long end = System.currentTimeMillis();
		System.out.println(set);
		System.out.println(end - start);
	}

	static void find(int[] arr, int position, boolean[] isIns) {
		if (position == arr.length) {
			List<Integer> list = new LinkedList<>();
			int sum = 0;
			int val = 0;
			for (int i = 0; i < arr.length; i++) {
				if (isIns[i]) {
					list.add(arr[i]) ;
					val++;
					if (val > 7) {
						return;
					}
					sum += arr[i];
					if (sum > 10) {
						return;
					}
				}
			}
			if (sum == 10) {
				set.add(list);
			}
		} else {
			isIns[position] = true;
			find(arr, position + 1, isIns);
			isIns[position] = false;
			find(arr, position + 1, isIns);
		}
	}

}