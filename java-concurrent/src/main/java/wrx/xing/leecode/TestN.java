package wrx.xing.leecode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-12-11 16:31
 */
public class TestN {
	@Test
	public void test() {
		int[] pre = {1,2,3,4,5,6,7};
		int[] in = {3,4,5,1,2};
//		System.out.println(JumpFloor(3));
		ListNode node = new ListNode(0,new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,null)))));
		ListNode node2 = new ListNode(5,new ListNode(6,new ListNode(7,new ListNode(8,new ListNode(9,null)))));
//		ListNode reverse = reverse(node);
//		System.out.println();
//		Power(2,2);
//		int[] array = {1,2,5,4,7,8,6,9};
//		int[] array = {1,1,2,4,6,3,1};
//		reOrderArray(array);
//		System.out.println(Arrays.toString(array));
		ListNode merge = Merge(node2, node);
		System.out.println(merge);
	}

	public int JumpFloor(int target) {
		if (0 == target) {
			return 0;
		}
		if (1 == target) {
			return 1;
		}
		int zero = 0;
		int one = 1;
		int result = 0;
		for (int i = 2; i <= target; i++) {
			result = zero + one;
			zero = one;
			one = result;
		}
		return result;
	}

	public double Power(double base, int exponent) {
		if (base == 0) {
			return 0;
		}
		double result = 1;
		int count = exponent > 0 ? exponent : -exponent;
		for (int i = 0; i < count; i++) {
			result *= base;
		}

		return exponent > 0 ? result : 1 / result;
	}

	public ListNode reverse(ListNode head) {
		if (head.next == null) {
			return head;
		}
		ListNode reverse = reverse(head.next);
		head.next.next = head;
		head.next = null;
		return reverse;
	}

	public void reOrderArray(int [] array) {
		int start = 1;

		for (int i = 1; i < array.length; i++) {
			if (array[i] % 2 != array[i - 1] % 2) {
				if (array[i] % 2 == 0) {
					start = i + 1;
				}
				break;
			}
		}
		for (int i = start; i < array.length; i++) {
			int n = i;
			while (n > 0 && array[n - 1] % 2 != array[n] % 2) {
				swap(array,n - 1, n);
				n--;
			}
		}
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public ListNode FindKthToTail(ListNode head, int k) {
		if (head == null) {
			return head;
		}
		int length = 0;
		ListNode newHead = head;
		while (newHead != null) {
			length++;
			newHead = newHead.next;
		}
		if (k > length) {
			return null;
		}

		int index = length - 1 - k;
		while (index > 0) {
			head = head.next;
			index--;
		}
		return head;
	}


	public ListNode Merge(ListNode list1, ListNode list2) {
		if (null == list1 || null == list2) {
			return null;
		}
		ListNode listNode = new ListNode(list1.value - 1, list2);
		while (list1 != null) {
			add(listNode,new ListNode(list1.value,null));
			list1 = list1.next;
		}
		return listNode.next;
	}

	public void add(ListNode head, ListNode node) {
		ListNode temp = head;
		while (true) {
			if (temp.next == null) {
				break;
			}
			if (temp.next.value >= node.value) {
				break;
			}
			temp = temp.next;
		}

		node.next = temp.next;
		temp.next = node;
	}

	public void add2(ListNode head, ListNode node) {
		ListNode temp = head;
		while (true) {
			if (temp.next == null) {
				break;
			}
			if (temp.next.value >= node.value) {
				break;
			}
			temp = temp.next;
		}

		node.next = temp.next;
		temp.next = node;
	}
}

class ListNode {
	int value;
	ListNode next;

	public ListNode(int value, ListNode next) {
		this.value = value;
		this.next = next;
	}

	@Override
	public String toString() {
		return "ListNode{" +
				"value=" + value +
//				"next=" + next.value +
				'}';
	}
}


