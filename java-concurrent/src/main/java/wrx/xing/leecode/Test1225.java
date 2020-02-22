package wrx.xing.leecode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-12-25 15:09
 */
public class Test1225 {
	@Test
	public void Test1() {
//		int[] ints = {2, 4, 3, 6, 3, 2, 5, 5};
//		int[] num1 = new int[1];
//		int[] num2 = new int[1];
//		FindNumsAppearOnce(ints,num1,num2);
//		System.out.println(Arrays.toString(num1));
//		System.out.println(Arrays.toString(num2));

		System.out.println(5 / 2);
	}

	public int InversePairs(int [] array) {
		if (null == array || array.length == 0) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) {
					count++;
				}
			}
		}
		return count%1000000007;
	}

	public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
		Arrays.sort(array);
		for (int i = 0; i < array.length ; i++) {
			if (i == array.length - 1) {
				num2[0] = array[i];
				break;
			}
			if (array[i] == array[i + 1]) {
				i++;
			}else {
				if (num1[0] == 0) {
					num1[0] = array[i];
				}else if (num2[0] == 0) {
					num2[0] = array[i];
				}else {
					break;
				}
			}
		}
	}

	public boolean duplicate(int numbers[],int length,int [] duplication) {
		if (null == numbers || length == 0) {
			return false;
		}
		Arrays.sort(numbers);
		for (int i = 0; i < numbers.length - 1; i++) {
			if (numbers[i] == numbers[i + 1]) {
				duplication[0] = numbers[i];
				return true;
			}
		}
		return false;
	}

	public int[] multiply(int[] A) {
		if (null == A || A.length == 0) {
			return A;
		}
		int[] b = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			b[i] = 1;
			for (int j = 0; j < A.length; j++) {
				if (i == j) {
					continue;
				}
				b[i] *= A[j];
			}
		}
		return b;
	}

	ArrayList<Integer> list = new ArrayList<>();
	public void Insert(Integer num) {
		list.add(num);
	}

	public Double GetMedian() {
		list.sort(Integer::compareTo);
		if (list.size() % 2 == 0) {
			return (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2d;
		}else {
			return list.get(list.size() / 2) * 1d;
		}
	}

	public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
	{
		int[] counts = new int[256];
		for (char c : matrix) {
			counts[c]++;
		}
		for (char c : str) {
			counts[c]--;
		}
		for (char c : str) {
			if (counts[c] < 0) {
				return false;
			}
		}
		return true;
	}
}
