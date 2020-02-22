package wrx.xing.data_structures.sort;

/**
 * 请填写类的描述
 * 二叉排序树
 *
 * @author wangruxing
 * @date 2019-10-19 14:28
 */
public class BinarySortTreeDemo {

	public static void main(String[] args) {
		int[] arr = {5,23,798,23,27,6,7,8,34,21};
		BinarySortTree tree = new BinarySortTree();
		for (int i : arr) {
			tree.add(new Node(i));
		}
		tree.infixOrder();
		tree.height();
	}

}

class BinarySortTree {
	private Node root;

	void add(Node node) {
		if (root == null) {
			root = node;
		}else {
			root.add(node);
		}
	}

	void infixOrder() {
		if (root == null) {
			System.out.println("没有数据");
			return;
		}
		root.infixOrder();
	}

	void height() {
		int height = root.height();
		System.out.println(height);
	}

}


/**
 * 节点
 */
class Node {
	int value;
	Node left;
	Node right;

	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ListNode{" +
				"value=" + value +
				'}';
	}

	int height() {
		return Math.max(left == null ? 0 : left.height(),right == null ? 0 : right.height()) + 1;
	}

	void add(Node node) {
		if (null == node) {
			return;
		}
		if (this.value > node.value) {
			if (null == this.left) {
				this.left = node;
			}else {
				this.left.add(node);
			}
		}else {
			if (null == this.right) {
				this.right = node;
			}else {
				this.right.add(node);
			}
		}
	}

	void infixOrder() {
		if (this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.infixOrder();
		}
	}
}
