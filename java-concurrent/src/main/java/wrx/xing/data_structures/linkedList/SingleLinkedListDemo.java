package wrx.xing.data_structures.linkedList;

import java.util.Stack;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-10-01 10:30
 */
public class SingleLinkedListDemo {

	public static void main(String[] args) {
		SingleLinkedList list = new SingleLinkedList();
		SingleNode node1 = new SingleNode(1, "宋江", "及时雨");
		SingleNode node2 = new SingleNode(2, "卢俊义", "智多星");
		SingleNode node3 = new SingleNode(3, "吴用", "及时雨");
		SingleNode node4 = new SingleNode(4, "公孙胜", "入云龙");

		list.addOrder(node1);
		list.addOrder(node2);
		list.addOrder(node3);
		list.addOrder(node4);
		list.show();

		System.out.println("反转后：");
		list.reverse3();
		list.show();

//		list.del(node1);
//		System.out.println("删除节点：");
//		list.show();



	}
}

class SingleLinkedList {
	// 头结点什么也不放
	private SingleNode head = new SingleNode(0,"","");

	public void add(SingleNode node) {
		SingleNode temp = head;
		while (true) {
			if (temp.next == null) {
				temp.next = node;
				break;
			}
			temp = temp.next;
		}
	}

	public void addOrder(SingleNode node) {
		SingleNode temp = head;
		while (true) {
			if (temp.next == null) {
				break;
			}
			if (temp.next.no >= node.no) {
				break;
			}
			temp = temp.next;
		}

		node.next = temp.next;
		temp.next = node;
	}

	public void show() {
		SingleNode temp = head;
		while (true) {
			if (temp.next == null) {
				break;
			}
			System.out.println(temp.next);
			temp = temp.next;
		}
	}


	public void reverse() {
		// 一个都没有或者只有一个就直接返回，无需反转
		if (head.next == null || head.next.next == null) {
			return;
		}
		SingleNode newHead = new SingleNode(0, "", "");
		SingleNode cur = head.next;
		SingleNode next = null;

		while (cur != null) {
			// 1: 记录下一个节点
			next = cur.next;
			// 2：当前节点的下一个节点就是新头节点的下一个节点
			cur.next = newHead.next;
			// 3：把当前节点放到新头节点的下一个节点上，也就是一个位置上
			newHead.next = cur;
			// 4：移动当前节点到原来链表的下一个节点
			cur = next;

		}
		head = newHead;
	}

	void reverse2 () {
		// 没有或者有一个无需反转
		if (head.next == null || head.next.next == null) {
			return;
		}
		// 思路：先创建一个新链表，遍历旧链表的每一个元素，每次都放到新链表的头部
		// 1：创建新链表
		SingleNode newHead = new SingleNode(0,"","");

		// 2：遍历旧的链表
		SingleNode current = head.next;
		SingleNode currentNext = null;
		SingleNode newHeadNext = null;
		while (current != null) {
			// 记录下一个节点
			currentNext = current.next;
			// 记录新头节点的第一个节点
			newHeadNext = newHead.next;
			// 每次遍历的节点都放到新头结点的第一个节点
			newHead.next = current;
			// 新头节点的第二个节点就是上次头节点的第一个节点
			newHead.next.next = newHeadNext;
			// 遍历下一个
			current = currentNext;
		}

		// 最后换头结点
		head = newHead;
	}

	void reverse3() {
		Stack<SingleNode> stack = new Stack<>();
		SingleNode current = head.next;
		while (null != current) {
			stack.push(current);
			current = current.next;
		}

		while (stack.size() > 0) {
			System.out.println(stack.pop());
		}
	}

	public void del(SingleNode node) {
		SingleNode cur = head;
		while (cur.next != null) {
			if (cur.next.no.equals(node.no)) {
				cur.next = cur.next.next;
				break;
			}
			cur = cur.next;
		}
	}
}

class SingleNode {
	public Integer no;
	public String name;
	public String nickName;
	public SingleNode next;

	public SingleNode(Integer no, String name, String nickName) {
		this.no = no;
		this.name = name;
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "SingleNode{" +
				"no=" + no +
				", name='" + name + '\'' +
				", nickName='" + nickName + '\'' +
				'}';
	}
}
