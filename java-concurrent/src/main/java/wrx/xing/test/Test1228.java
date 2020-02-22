package wrx.xing.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-12-28 15:41
 */
public class Test1228 {

	@Test
	public void test1() {
//		System.out.println(violenceMatch("abcdyabcwuodlsdfs","abcwuodl"));
//		System.out.println(isValid("(]"));
//		int[] arr1 = {4, 1, 2};
//		int[] arr2 = {4, 3, 1, 2};
//		int[] ints = nextGreaterElement(arr1, arr2);
		ListNode n1 = new ListNode(0);
		ListNode n2 = new ListNode(7);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(2);
		ListNode n5 = new ListNode(9);
		ListNode n6 = new ListNode(6);
		ListNode listNode = mergeTwoLists(n2, n3);
		System.out.println(1);
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (null == l1 && null == l2) {
			return null;
		} else if (null == l1) {
			return l2;
		} else if (null == l2) {
			return l1;
		}
		ListNode node = new ListNode(0);
		ListNode p = l1;
		ListNode q = l2;
		ListNode temp = node;
		int count = 0;
		while (q != null || p != null) {
			int x = p == null ? 0 : p.val;
			int y = q == null ? 0 : q.val;
			int sum = count + x + y;
			count = sum / 10;
			temp.next = new ListNode(sum % 10);
			temp = temp.next;
			if (p != null) {
				p = p.next;
			}
			if (q != null) {
				q = q.next;
			}
		}
		if (count > 0) {
			temp.next = new ListNode(count);
		}
		return node.next;
	}

	public int lengthOfLongestSubstring(String s) {
		if (null == s || "".equals(s)) {
			return 0;
		}
		char[] chars = s.toCharArray();
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < chars.length - 1; i++) {
			int count = 1;
			ArrayList<Character> characters = new ArrayList<>();
			characters.add(chars[i]);
			for (int j = i + 1; j < chars.length; j++) {
				if (characters.contains(chars[j])) {
					break;
				}
				characters.add(chars[j]);
				count++;
			}
			list.add(count);
		}
		if (list.isEmpty()) {
			return 1;
		}
		list.sort(Integer::compareTo);
		return list.get(list.size() - 1);
	}

	public String longestPalindrome(String s) {
		if (null == s || "".equals(s)) {
			return s;
		}
		char[] array = s.toCharArray();
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < array.length; i++) {
			map.put(array[i], i);
		}
		ArrayList<Integer[]> list = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			Integer j = map.get(array[i]);
			if (j != i) {
				list.add(new Integer[]{i, j});
			}
		}
		if (list.isEmpty()) {
			return array[0] + "";
		}
		ArrayList<String> strs = new ArrayList<>();
		for (Integer[] indexs : list) {
			String str = "";
			for (int i = indexs[0]; i <= indexs[1]; i++) {
				str += array[i];
			}
			strs.add(str);
		}
		for (int i = 0; i < strs.size(); i++) {
			String str = strs.get(i);
			String reverse = new StringBuffer(str).reverse().toString();
			if (!str.equals(reverse)) {
				strs.remove(i);
				i--;
			}
		}
		if (strs.isEmpty()) {
			return array[0] + "";
		}
		strs.sort((a, b) -> b.length() - a.length());
		return strs.get(0);
	}

	public int violenceMatch(String str1, String str2) {
		boolean dd = str1.contains("dd");
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();

		int s1Len = s1.length;
		int s2Len = s2.length;
		// i索引指向s1
		int i = 0;
		// j索引指向s2
		int j = 0;
		// 保证不越界
		while (i < s1Len && j < s2Len) {
			if (s1[i] == s2[j]) {
				i++;
				j++;
			} else {
				i = i - (j - 1);
				j = 0;
			}
		}
		if (j == s2Len) {
			return i - j;
		} else {
			return -1;
		}
	}

	public boolean isValid(String s) {

		HashMap<Character, Character> mappings = new HashMap<>();
		mappings.put(')', '(');
		mappings.put('}', '{');
		mappings.put(']', '[');

		// Initialize a stack to be used in the algorithm.
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// If the current character is a closing bracket.
			if (mappings.containsKey(c)) {

				// Get the top element of the stack. If the stack is empty, set a dummy value of '#'
				char topElement = stack.empty() ? '#' : stack.pop();

				// If the mapping for this bracket doesn't match the stack's top element, return false.
				if (topElement != mappings.get(c)) {
					return false;
				}
			} else {
				// If it was an opening bracket, push to the stack.
				stack.push(c);
			}
		}

		// If the stack still contains elements, then it is an invalid expression.
		return stack.isEmpty();

	}

	public int[] nextGreaterElement(int[] findNums, int[] nums) {
		Stack<Integer> stack = new Stack<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		int[] res = new int[findNums.length];
		for (int i = 0; i < nums.length; i++) {
			while (!stack.empty() && nums[i] > stack.peek())
				map.put(stack.pop(), nums[i]);
			stack.push(nums[i]);
		}
		while (!stack.empty())
			map.put(stack.pop(), -1);
		for (int i = 0; i < findNums.length; i++) {
			res[i] = map.get(findNums[i]);
		}
		return res;
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (null == l1 && null != l2) {
			return l2;
		}else if (null == l2 && null != l1) {
			return l1;
		}else if (null == l1 && null == l2) {
			return null;
		}
		while (true) {
			if (l2 == null) {
				break;
			}
			ListNode node = new ListNode(l2.val);
			l1 = add(l1,node);
			l2 = l2.next;
		}
		return l1;
	}

	private ListNode add(ListNode node, ListNode add) {
		ListNode temp = node;
		while (true) {
			if (temp.next == null) {
				break;
			}
			if (temp.next.val > add.val) {
				break;
			}
			temp = temp.next;
		}
		if (temp.val <= add.val) {
			add.next = temp.next;
			temp.next = add;
			return node;
		}else {
			add.next = temp;
			return add;
		}
	}

}

class ListNode {
	int val;
	ListNode next;

	public ListNode(int val) {
		this.val = val;
	}
}
