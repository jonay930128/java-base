package wrx.xing.data_structures.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-10-09 21:14
 */
public class HuffmanTreeDemo {
	public static void main(String[] args) {
		int arr[] = {1,3,6,7,8,13,29};
		HuffmanTree tree = new HuffmanTree();
		Node root = tree.buildHuffmanTree(arr);
		root.preOrder(root);
	}

}

class HuffmanTree {

	Node buildHuffmanTree(int[] arr) {
		List<Node> nodes = new ArrayList<>(arr.length);
		for (int value : arr) {
			nodes.add(new Node(value));
		}
		while (nodes.size() > 1) {
			// 排序
			Collections.sort(nodes);

			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);

			Node parent = new Node(leftNode.value + rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;

			nodes.remove(leftNode);
			nodes.remove(rightNode);
			nodes.add(parent);
		}


		return nodes.get(0);
	}
}

class Node implements Comparable<Node> {
	int value;
	Node left;
	Node right;

	Node(int value) {
		this.value = value;
	}

	// 前序遍历
	void preOrder(Node node) {
		System.out.println(node);
		if (node.left != null) {
			preOrder(node.left);
		}
		if (node.right != null) {
			preOrder(node.right);
		}
	}

	@Override
	public String toString() {
		return "Node{" +
				"value=" + value +
				'}';
	}

	@Override
	public int compareTo(Node o) {
		return this.value - o.value;
	}
}
