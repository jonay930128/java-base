package wrx.xing.offer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 请填写类的描述
 * 剑指offer刷题
 * @author wangruxing
 * @date 2020-02-16 12:34
 */
public class OfferTest0216 {

	@Test
	public void offerTest() {
		int[] arr = {1,2,4,1,3,6,7,8,9};
//		int[] arr = {2,4,1,3,6,7,8,9};
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 第一题
	 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
	 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
	 *
	 * 思路：因为该二维数组是有规律的，从左到右递增，从上到下递增，所以我们可以先从一个角出发，比如左下角开始，
	 * 		 如果左下角那个点比目标小，那么继续往右边找，如果比目标大往上找，直到找到相等为止，注意边界。
	 * 测试：
	 * 		int[] arr1 = {1,2,3,4,5};
	 * 		int[] arr2 = {6,7,8,9,10};
	 * 		int[] arr3 = {11,12,13,14,15};
	 * 		int[] arr4 = {16,17,18,19,20};
	 * 		int[] arr5 = {21,22,23,24,25};
	 * 		int[][] array = new int[5][5];
	 * 		array[0] = arr1;
	 * 		array[1] = arr2;
	 * 		array[2] = arr3;
	 * 		array[3] = arr4;
	 * 		array[4] = arr5;
	 * 		System.out.println(Find(-4,array));
	 */
	boolean Find(int target, int [][] array) {
		if (null == array || array.length < 1) {
			return Boolean.FALSE;
		}
		// 总行数
		int rows = array.length;
		// 总列数
		int cols = array[0].length;
		// 最后一行
		int row = rows - 1;
		int col = 0;
		while (row >= 0 && col < cols) {
			// 比目标小，向右移动
			if (array[col][row] < target) {
				col++;
			}else if (array[col][row] > target) {
				row--;
			}else {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	/**
	 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
	 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
	 *
	 * 思路：
	 * 		前序遍历的一个元素是树的根节点，通过根节点在中序遍历的位置确定，根节点左边的是左子树，跟节点右边的是右子树
	 * 		重复上述步骤直至无法继续分割为止。
	 */
	TreeNode reConstructBinaryTree(int [] pre,int [] in) {
		if (null == pre || null == in || pre.length == 0 || in.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(pre[0]);

		List<Integer> in0 = new ArrayList<>();
		List<Integer> in1 = new ArrayList<>();
		List<Integer> pre0 = new ArrayList<>();
		List<Integer> pre1 = new ArrayList<>();

		boolean left = true;
		for (int i : in) {
			if (i == pre[0]) {
				left = false;
				continue;
			}
			if (left) {
				in0.add(i);
			}else {
				in1.add(i);
			}
		}
		for (int i = 1; i < pre.length; i++) {
			if (pre0.size() != in0.size()) {
				pre0.add(pre[i]);
			}else {
				pre1.add(pre[i]);
			}
		}

		root.left = reConstructBinaryTree(listToArray(pre0),listToArray(in0));
		root.right = reConstructBinaryTree(listToArray(pre1),listToArray(in1));
		return root;
	}

	private int[] listToArray(List<Integer> list) {
		int[] arr = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}

	boolean HasSubtree(TreeNode root1,TreeNode root2) {
		if (root1 == null || root2 == null) {
			return false;
		}

		return isSubTree(root1,root2) || HasSubtree(root1.left,root2) || HasSubtree(root2.right,root2);
	}

	boolean isSubTree(TreeNode root,TreeNode subTree) {
		if (null == subTree) {
			return true;
		}
		if (null == root) {
			return false;
		}
		if (root.val != subTree.val) {
			return false;
		}

		return isSubTree(root.left,subTree.left) && isSubTree(root.right,subTree.right);
	}

	public int minNumberInRotateArray(int [] array) {
		if (null == array || array.length == 0) {
			return 0;
		}
		int left = 0;
		int right = array.length - 1;
		while (left <= right && left > -1 && right < array.length) {
			int mid = (left + right) >> 1;
			if (array[mid] < array[mid - 1]) {
				return array[mid];
			}else if (array[mid] < array[right]) {
				right = mid - 1;
			}else {
				left = mid + 1;
			}
		}
		return 0;
	}

	public ListNode FindKthToTail(ListNode head,int k) {
		if (null == head || k < 1) {
			return null;
		}
		ListNode node1 = head;
		ListNode node2 = head;
		// 先让第一个走(k-1)步，因为倒数第一个就是最后一个，倒数第二个才走了一步
		for (int i = 0; i < k - 1; i++) {
			node1 = node1.next;
			// 说明k超过了链表长度
			if (node1 == null) {
				return null;
			}
		}

		// 两个人同时走，第一个走到头了，那么第二个就是倒数第k个
		while (node1 != null) {
			node1 = node1.next;
			node2 = node2.next;
		}

		return node2;
	}

}
