package wrx.xing.leecode;

import org.junit.Test;

import java.util.*;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-12-16 10:18
 */
public class Test1216 {


	@Test
	public void test1() {
//		[1,2,3,4,5],[4,5,3,2,1]
//		int[] pushA = {1,2,3,4,5};
		int[] pushA = {5, 4, 3, 2, 1};
		int[] popA = {4, 8, 6, 12, 16, 14, 10};

		/*TreeNode t1 = new TreeNode(10);
		TreeNode t2 = new TreeNode(5);
		TreeNode t3 = new TreeNode(12);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(7);
		TreeNode t6 = new TreeNode(3);
		t1.right = t2;
		t1.left = t3;
		t2.left = t4;
		t2.right = t5;
		t4.left = t6;
		System.out.println(FindPath(t1,22));*/

//		quickSort(popA,0,popA.length - 1);
//		System.out.println(Arrays.toString(popA));
//		int[] array = {6, -3, -2, 7, -15, 1, 2, 2};

//		System.out.println(FindGreatestSumOfSubArray(array));

//		System.out.println(7%1000000007);
//		System.out.println(halfFind(new int[]{1,2,3,4,5,6,7,8,9},8));

//		System.out.println(1 ^2);
//		System.out.println(isContinuous(new int[]{1,0,0,5,0}));
		char[] chars = "seacb".toCharArray();
		Arrays.sort(chars);
		System.out.println(Arrays.toString(chars));

	}


	ArrayList<ArrayList<Integer>> result = new ArrayList<>();
	ArrayList<Integer> list = new ArrayList<>();

//	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
//		if(root == null) {
//			return result;
//		}
//		list.add(root.val);
//		target -= root.val;
//		if(target == 0 && root.left == null && root.right == null) {
//			result.add(new ArrayList<Integer>(list));
//		}
//		// 因为在每一次的递归中，我们使用的是相同的result引用，所以其实左右子树递归得到的结果我们不需要关心，
//		// 可以简写为FindPath(root.left, target)；FindPath(root.right, target)；
//		// 但是为了大家能够看清楚递归的真相，此处我还是把递归的形式给大家展现了出来。
//		FindPath(root.left, target);
//		FindPath(root.right, target);
//		list.remove(list.size()-1);
//		return result;
//	}


	public RandomListNode Clone(RandomListNode pHead) {
		if (null == pHead) {
			return null;
		}
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		RandomListNode temp1 = pHead;
		RandomListNode temp2 = pHead;

		// 先循环将数据保存到map中
		while (temp1 != null) {
			map.put(temp1, new RandomListNode(temp1.label));
			temp1 = temp1.next;
		}

		// 再循环temp2将关系建立起来
		while (temp2 != null) {
			if (temp2.next != null) {
				map.get(temp2).next = map.get(temp2.next);
			}
			map.get(temp2).random = map.get(temp2.random);
			temp2 = temp2.next;
		}

		return map.get(pHead);
	}


	public TreeNode Convert(TreeNode pRootOfTree) {
		if (null == pRootOfTree) {
			return null;
		}
		List<TreeNode> list = new ArrayList<>();
		convertToList(pRootOfTree, list);

		for (int i = 1; i < list.size(); i++) {
			list.get(i - 1).right = list.get(i);
			list.get(i).left = list.get(i - 1);
		}

		return list.get(0);
	}

	public void convertToList(TreeNode pRootOfTree, List<TreeNode> list) {
		if (pRootOfTree.left != null) {
			convertToList(pRootOfTree.left, list);
		}
		list.add(pRootOfTree);
		if (pRootOfTree.right != null) {
			convertToList(pRootOfTree.right, list);
		}
	}

	public int MoreThanHalfNum_Solution(int[] array) {
		if (null == array || array.length == 0) {
			return 0;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (Integer i : array) {
			if (map.containsKey(i)) {
				map.put(i, map.get(i) + 1);
			} else {
				map.put(i, 1);
			}
		}
		for (Integer integer : map.keySet()) {
			if (map.get(integer) > (array.length / 2)) {
				return integer;
			}
		}
		return 0;
	}

	public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
		if (null == input || input.length == 0 || k == 0) {
			return new ArrayList<>();
		}
		ArrayList<Integer> list = new ArrayList<>();
		quickSort(input, 0, input.length - 1);
		for (int i = 0; i < k; i++) {
			list.add(input[i]);
		}
		return list;
	}

	public void quickSort(int[] input, int left, int right) {
		if (left < right) {
			int index = partition(input, left, right);
			quickSort(input, left, index - 1);
			quickSort(input, index + 1, right);
		}
	}

	private int partition(int[] input, int left, int right) {
		int index = left + 1;

		for (int i = index; i <= right; i++) {
			if (input[i] < input[left]) {
				if (i != index) {
					swap(input, i, index);
				}
				index++;
			}
		}
		swap(input, left, index - 1);
		return index - 1;
	}

	private void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}


	public int FindGreatestSumOfSubArray(int[] array) {
		if (null == array || array.length == 0) {
			return 0;
		}
		int max = array[0];
		int sum = array[0];

		for (int i = 1; i < array.length; i++) {
			if (sum <= 0) {
				sum = array[i];
			} else {
				sum += array[i];
			}
			if (sum >= max) {
				max = sum;
			}
		}
		return max;
	}

	public String PrintMinNumber(int[] numbers) {
		if (null == numbers || numbers.length == 0) {
			return null;
		}

		List<String> list = new ArrayList<>(numbers.length);
		for (int number : numbers) {
			list.add(number + "");
		}

		list.sort((s1, s2) -> (s1 + s2).compareTo((s2 + s1)));

		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			sb.append(s);
		}
		return sb.toString();
	}

	public int FirstNotRepeatingChar(String str) {
		char[] chars = str.toCharArray();
		int[] count = new int[256];
		for (char aChar : chars) {
			count[aChar]+= 1;
		}
		for (int i = 0; i < chars.length; i++) {
			if (count[chars[i]] == 1) {
				return i;
			}
		}
		return -1;
	}

	public int InversePairs(int [] array) {

		return 0;
	}

	public List<Integer> getList() {
		List<Integer> list = new ArrayList<>(100000);
		for (int i = 0; i < 9999999; i++) {

		}
		return list;
	}


	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		if (null == pHead1 || null == pHead2) {
			return null;
		}
		ListNode p1 = pHead1;
		ListNode p2 = pHead2;
		while (p1 != p2) {
			p1 = p1.next;
			p2 = p2.next;
			if (p1 != p2) {
				if (p1 == null) {
					p1 = pHead1;
				}
				if (p2 == null) {
					p2 = pHead2;
				}
			}
		}
		return p1;
	}

	public int GetNumberOfK(int [] array , int k) {
		if (null == array || array.length == 0) {
			return 0;
		}
		int count = 0;
		for (int i : array) {
			if (i == k) {
				count++;
			}
		}
		return count;
	}

	public int halfFind(int[] array,int k) {
		int left = 0;
		int right = array.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (array[mid] == k) {
				return mid;
			}else if(array[mid] > k) {
				right = mid - 1;
			}else if (array[mid] < k) {
				left = mid + 1;
			}
		}
		return -1;
	}

	public boolean IsBalanced_Solution(TreeNode root) {
		if (null == root) {
			return false;
		}

		int left = treeDept(root.left);
		int right = treeDept(root.right);

		return Math.abs(left - right) <= 1;
	}

	private int treeDept(TreeNode root) {
		if (null == root) {
			return 0;
		}

		return Math.max(treeDept(root.left) + 1,treeDept(root.right) + 1);
	}

	public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
		if (null == array || array.length == 0) {
			return;
		}

	}

	public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
		ArrayList<ArrayList<Integer> > result = new ArrayList<>();
		if (0 == sum) {
			return result;
		}
		for (int i = 1; i < sum; i++) {
			int j = i;
			int temp = 0;
			while (temp < sum) {
				temp += j;
				j++;
			}
			if (temp == sum) {
				ArrayList<Integer> integers = new ArrayList<>();
				for (int k = i; k < j; k++) {
					integers.add(k);
				}
				result.add(integers);
			}
		}

		return result;
	}

	public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
		return method1(array, sum);
	}

	private ArrayList<Integer> method1(int[] array, int sum) {
		if (null == array || array.length == 0 || sum == 0) {
			return new ArrayList<>();
		}
		ArrayList<Integer> list = new ArrayList<>();
		HashMap<Integer,Integer> map = new HashMap<>();
		for (int i : array) {
			map.put(i,i);
		}
		for (int i : array) {
			Integer integer = map.get(sum - i);
			if (null != integer) {
				list.add(i);
				list.add(integer);
				break;
			}
		}
		return list;
	}

	private ArrayList<Integer> method2(int[] array, int sum) {
		if (null == array || array.length == 0 || sum == 0) {
			return new ArrayList<>();
		}
		ArrayList<Integer> list = new ArrayList<>();
		int low = 0;
		int high = array.length - 1;
		while (low <  high) {
			int temp = array[low] + array[high];
			if (sum == temp) {
				list.add(array[low]);
				list.add(array[high]);
				break;
			}else if (temp > sum) {
				high--;
			}else if (temp < sum) {
				low++;
			}
		}
		return list;
	}

	public String LeftRotateString(String str,int n) {
		if (null == str) {
			return null;
		}
		String[] arr = str.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int length = arr.length - 1; length >= 0; length--) {
			sb.append(arr[length]).append(" ");
		}
		return sb.deleteCharAt(sb.length() - 1).toString();
	}

	public boolean isContinuous(int [] numbers) {
		if (null == numbers || numbers.length < 5) {
			return false;
		}
		Arrays.sort(numbers);
		int zero = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (0 == numbers[i]) {
				zero++;
				continue;
			}
			if (i >= zero + 1 && numbers[i-1] != 0 && numbers[i] - numbers[i - 1] > 1) {
				if (numbers[i] - numbers[i - 1] == 2) {
					zero--;
				}else {
					zero -= numbers[i] - numbers[i - 1] - 1;
				}
			}
		}
		return 0 == zero || (zero == 4 && numbers[4] == 5);
	}

	public int LastRemaining_Solution(int n, int m) {
		if (n < 2 || m < 2) {
			return -1;
		}
		int count = 0;
		while (n < m) {
			m = m - n;
			count++;
		}


		return m - 1 - count;
	}

	public int Add(int num1,int num2) {
		Integer[] ints = {num1, num2};
		return Arrays.stream(ints).mapToInt(item -> item).sum();
	}

	public ListNode deleteDuplication(ListNode pHead){
		if (pHead == null || pHead.next == null) { // 只有0个或1个节点
			return pHead;
		}
		if (pHead.value == pHead.next.value) {
			ListNode next = pHead.next;
			while (next != null && next.value == next.next.value) {
				next = next.next;
			}
			return deleteDuplication(next);
		}else {
			pHead.next = deleteDuplication(pHead.next);
			return pHead;
		}
	}

	public TreeLinkNode GetNext(TreeLinkNode pNode){
		// 先找到根节点
		TreeLinkNode temp = pNode;
		while (temp.next != null) {
			temp = temp.next;
		}
		// 从跟节点开始做中序遍历
		ArrayList<TreeLinkNode> list = new ArrayList<>();
		// 中序遍历
		inOrder(temp,list);
		// 遍历list
		for (int i = 0; i < list.size(); i++) {
			if (pNode == list.get(i)) {
				// 如果i是最后一个说明没有下一个，如果不是最后一个就返回下一个
				return i == list.size() - 1 ? null : list.get(i + 1);
			}
		}
		return null;
	}

	private void inOrder(TreeLinkNode pNode,ArrayList<TreeLinkNode> list) {
		if (pNode.left != null) {
			inOrder(pNode.left,list);
		}
		list.add(pNode);
		if (pNode.right != null) {
			inOrder(pNode.right,list);
		}
	}
	private void inOrder(TreeNode pNode,ArrayList<TreeNode> list) {
		if (pNode.left != null) {
			inOrder(pNode.left,list);
		}
		list.add(pNode);
		if (pNode.right != null) {
			inOrder(pNode.right,list);
		}
	}

	boolean isSymmetrical(TreeNode pRoot){
		return pRoot == null || jude(pRoot.left,pRoot.right);
	}

	private boolean jude(TreeNode node1, TreeNode node2) {
		if (node1 == null && node2 == null) {
			return true;
		} else if (node1 == null || node2 == null) {
			return false;
		}
		if (node1.val != node2.val) {
			return false;
		} else {
			return jude(node1.left, node2.right) && jude(node1.right, node2.left);
		}
	}

	private void Mirror(TreeNode root) {
		if (null == root) {
			return;
		}
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		if (root.left != null) {
			Mirror(root.left);
		}
		if (root.right != null) {
			Mirror(root.right);
		}
	}

	public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
		if (null == pRoot) {
			return new ArrayList<>();
		}
		ArrayList<ArrayList<Integer> > result = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(pRoot);
		while (!queue.isEmpty()) {
			int size = queue.size();
			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode poll = queue.poll();
				list.add(poll.val);
				if (poll.left != null) {
					queue.offer(poll.left);
				}
				if (poll.right != null) {
					queue.offer(poll.right);
				}
			}
			result.add(list);
		}
		return result;
	}

	TreeNode KthNode(TreeNode pRoot, int k){
		if (null == pRoot || k < 1) {
			return null;
		}
		ArrayList<TreeNode> nodes = new ArrayList<>();
		inOrder(pRoot,nodes);

		return nodes.get(k - 1);
	}

	public ArrayList<Integer> maxInWindows(int [] num, int size){
		if (null == num || size < 1) {
			return new ArrayList<>();
		}
		ArrayList<Integer> result = new ArrayList<>();
		int start = 0;
		while (true) {
			ArrayList<Integer> list = getList(num, size, start);
			if (list.isEmpty()) {
				break;
			}
			list.sort(Integer::compareTo);
			result.add(list.get(list.size() - 1));
			start++;
		}
		return result;
	}

	private ArrayList<Integer> getList(int[] num,int size,int start) {
		ArrayList<Integer> integers = new ArrayList<>();
		if (start + size <= num.length) {
			int end = start + size;
			for (int i = start; i < end; i++) {
				integers.add(num[i]);
			}
		}
		return integers;
	}

	public ArrayList<String> Permutation(String str) {
		ArrayList<String> list = new ArrayList<>();
		if (null != str) {
			char[] chars = str.toCharArray();
			Arrays.sort(chars);
		}
		return list;
	}



	public static void permutation(char[] s,int from,int to) {
		if(to <= 1)
			return;
		if(from == to) {
			System.out.println(s);
		} else {
			for(int i=from; i<=to; i++) {
				swap(s,i,from); //交换前缀，使其产生下一个前缀
				permutation(s, from+1, to);
				swap(s,from,i); //将前缀换回，继续做上一个前缀的排列
			}
		}
	}

	public static void swap(char[] s,int i,int j) {
		char tmp = s[i];
		s[i] = s[j];
		s[j] = tmp;
	}


	public static ArrayList<String> Permutation2(String str) {
		if (str == null || str.length() == 0) {
			return new ArrayList<>();
		}
		Set<String> set = new TreeSet<>();
		helper(str.toCharArray(), 0, set);
		return new ArrayList<>(set);
	}
	public static void helper(char[] s, int i, Set<String> set) {
		if (i == s.length) {
			set.add(String.valueOf(s));
		}
		for (int j = i; j < s.length; j++) {
			swap2(s, i, j);
			helper(s, i + 1, set);
			swap2(s, i, j);
		}
	}
	public static void swap2(char[] s, int i, int j) {
		if (i == j){
			return;
		}
		s[i] ^= s[j];
		s[j] ^= s[i];
		s[i] ^= s[j];
	}



}
