package wrx.xing.data_structures.hashtab;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-10-06 10:24
 */
public class HashTabDemo {
}

class HashTab {
	private EmpLinkedList[] linkedLists;

	public HashTab(int size) {
		this.linkedLists = new EmpLinkedList[size];
		// 初始化
		for (EmpLinkedList linkedList : this.linkedLists) {
			linkedList = new EmpLinkedList();
		}
	}

	public void add(Emp emp) {
		int hashIndex = hashIndex(emp.id);
		linkedLists[hashIndex].add(emp);
	}

	private int hashIndex(int id) {
		return id % this.linkedLists.length;
	}

	public void list() {
		for (EmpLinkedList linkedList : this.linkedLists) {
			linkedList.list();
		}
	}
}

class Emp {
	public int id;
	public String name;
	public Emp next;

	public Emp(int id, String name) {
		this.id = id;
		this.name = name;
	}
}

class EmpLinkedList {
	// 头节点为空
	private Emp head;

	public void add(Emp emp) {
		// 添加第一个
		if (null == head) {
			head = emp;
			return;
		}
		Emp cur = head;
		// 找到最后一个节点
		while (cur.next != null) {
			cur = cur.next;
		}
		cur.next = emp;
	}

	public void list() {
		if (head == null) {
			System.out.println("无数据");
			return;
		}
		Emp cur = head;
		while (null != head.next) {
			System.out.printf("=>id=%d,name=%s \t",cur.id,cur.name);
			cur = cur.next;
		}
	}
}