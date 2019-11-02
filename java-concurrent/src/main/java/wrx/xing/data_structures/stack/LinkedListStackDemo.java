package wrx.xing.data_structures.stack;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-10-01 19:31
 */
public class LinkedListStackDemo {
}


class LinkedListStack<T> {

}

class LinkedNode<T> {
	public int size;
	public int top = -1;
	public T val;
	public LinkedNode<T> next;

	public LinkedNode(int size) {
		this.size = size;
	}

	private boolean isFull() {
		return this.top == size - 1;
	}

	private boolean isEmpty() {
		return this.top == -1;
	}

	public void push(T t) {
		if (isFull()) {
			System.out.println("满了");
		}

	}
}