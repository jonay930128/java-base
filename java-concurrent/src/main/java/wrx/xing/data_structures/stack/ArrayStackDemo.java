package wrx.xing.data_structures.stack;

import java.util.Scanner;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-10-01 19:10
 */
public class ArrayStackDemo {
	public static void main(String[] args) {
		ArrayStack stack = new ArrayStack(3);
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		String key ;
		while (loop) {
			key = scanner.next();
			switch (key) {
				case "push":
					System.out.println("请输入：");
					stack.push(scanner.nextInt());
					break;
				case "pop":
					stack.pop();
					break;
				case "exit":
					scanner.close();
					loop = false;
					break;
				case "show":
					stack.show();
					break;
				default:
					break;
			}
		}
		System.out.println("结束");
	}
}

class ArrayStack {
	private int top = -1;
	private int size;
	private int[] stack;

	public ArrayStack(int size) {
		this.size = size;
		this.stack = new int[size];
	}

	private boolean isFull() {
		return top == size - 1;
	}

	private boolean isEmpty() {
		return top == -1;
	}

	public void push(int val) {
		if (isFull()) {
			System.out.println("已经满了");
			return;
		}
		top++;
		stack[top] = val;
		System.out.println("添加成功");
	}

	public void pop() {
		if (isEmpty()) {
			System.out.println("空了");
		}
		int val = stack[top];
		top--;
		System.out.println("出栈：" + val);
	}

	public void show() {
		if (isEmpty()) {
			System.out.println("空的");
			return;
		}

		for (int i = top; i >= 0; i--) {
			System.out.printf("stack[%d]：%d /n",i,stack[i]);
		}
	}
}