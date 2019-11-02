package wrx.xing.data_structures.linkedList;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-10-01 16:40
 */
public class CircleLinkedListDemo {
	public static void main(String[] args) {
		CircleLinkedList list = new CircleLinkedList();
		list.add(5);
		list.show();
		System.out.println("约瑟夫");
		list.josepfu(2,2);
	}
}

class CircleLinkedList {
	private CircleNode first;

	public void add(int size) {
		CircleNode cur = null;
		for (int i = 1; i <= size; i++) {
			if (i == 1) {
				first = new CircleNode(1);
				first.next = first;
				cur = first;
				continue;
			}
			CircleNode node = new CircleNode(i);

			cur.next = node;
			node.next = first;
			cur = node;
		}
	}

	public void show() {
		if (first == null) {
			System.out.println("无数据");
			return;
		}
		CircleNode cur = first;
//		while (true) {
//			System.out.println(cur);
//			if (cur.next.no == first.no) {
//				break;
//			}
//			cur = cur.next;
//		}
		System.out.println(cur);
		while (cur.next.no != first.no) {
			cur = cur.next;
			System.out.println(cur);
		}
	}

	public void josepfu(int num,int start) {
		CircleNode cur = first;
		CircleNode helper ;
		while (true) {
			if (cur.next.no == first.no) {
				helper = cur;
				if (start > cur.no){
					System.out.println("错误参数");
				}
				break;
			}
			cur = cur.next;
		}

		// 移动到开始的位置
		for (int i = 0; i < start - 1; i++) {
			first = first.next;
			helper = helper.next;
		}

		while (true) {
			// 剩一个了
			if (helper == first) {
				break;
			}
			// 移动
			for (int i = 0; i < num - 1; i++) {
				first = first.next;
				helper = helper.next;
			}
			// 这时first就是要出去的那个
			System.out.println("出去：" + first);

			first = first.next;
			helper.next = first;
		}

		System.out.println("最后一个：" + first);

	}

}

class CircleNode {
	public int no;
	public CircleNode next;

	public CircleNode(int no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "CircleNode{" +
				"no=" + no +
				", next=" + next.no +
				'}';
	}
}