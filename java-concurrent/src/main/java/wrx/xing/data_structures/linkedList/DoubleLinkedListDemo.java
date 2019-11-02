package wrx.xing.data_structures.linkedList;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-10-01 15:54
 */
public class DoubleLinkedListDemo {
	public static void main(String[] args) {
		DoubleLinkedList list = new DoubleLinkedList();
		DoubleNode node1 = new DoubleNode(1, "wrx");
		DoubleNode node2 = new DoubleNode(2, "wzy");
		DoubleNode node3 = new DoubleNode(3, "sq");
		DoubleNode node4 = new DoubleNode(4, "whs");
		list.addOrder(node1);
		list.addOrder(node4);
		list.addOrder(node3);
		list.addOrder(node2);
		list.show();

		list.del(node2);
		System.out.println("删除一个：");
		list.show();
	}
}

class DoubleLinkedList {

	private DoubleNode head = new DoubleNode(0,"");

	public void add(DoubleNode node) {
		DoubleNode cur = head;
		while (true) {
			if (cur.next == null) {
				cur.next = node;
				node.pre = cur;
				break;
			}
			cur = cur.next;
		}
	}

	public void addOrder(DoubleNode node) {
		DoubleNode cur = head;
		while (true) {
			if (cur.next == null) {
				cur.next = node;
				node.pre = cur;
				break;
			}
			if (cur.next.no > node.no) {
				cur.next.pre = node;
				node.next = cur.next;
				cur.next = node;
				node.pre = cur;

				break;
			}
			cur = cur.next;
		}
	}

	public void show() {
		DoubleNode cur = this.head.next;
		while (cur != null) {
			System.out.println(cur);
			cur = cur.next;
		}
	}

	public void del(DoubleNode node) {
		DoubleNode cur = this.head.next;
		if (cur == null) {
			System.out.println("无数据");
			return;
		}
		while (true) {
			if (cur.no == node.no) {
				cur.pre.next = cur.next;
				if (cur.next != null) {
					cur.next.pre = cur.pre;
				}
				break;
			}
			cur = cur.next;
		}
	}
}

class DoubleNode {
	public int no;
	public String name;
	public DoubleNode next;
	public DoubleNode pre;

	public DoubleNode(int no, String name) {
		this.no = no;
		this.name = name;
	}

	@Override
	public String toString() {
		return "DoubleNode{" +
				"no=" + no +
				", name='" + name + '\'' +
				", pre=" + pre.name +
				'}';
	}
}
