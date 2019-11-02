package wrx.xing.data_structures.queue;

import java.util.Random;
import java.util.Scanner;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-09-28 18:44
 */
public class CircleArrayQueueDemo {
	public static void main(String[] args) {
		CircleArrayQueue queue = new CircleArrayQueue(4);
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		Random random = new Random();
		char key = ' ';
		while (loop) {
			key = scanner.next().charAt(0);
			switch (key) {
				case 'a':
					queue.push(random.nextInt());
					break;
				case 'r':
					try {
						int poll = queue.poll();
						System.out.println("数据：" + poll);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 'e':
					scanner.close();
					loop = false;
					break;
				case 's':
					try {
						queue.show();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				default:
					break;
			}
		}
		System.out.println("结束");
	}
}

class CircleArrayQueue {
	/**
	 * 最大容量
	 */
	private int maxSize;
	/**
	 * 队列有效长度
	 */
	private int size;
	/**
	 * 添加索引
	 */
	private int pushIndex;
	/**
	 * 取出索引位置
	 */
	private int pollIndex;

	private int[] arr;

	public CircleArrayQueue(int maxSize) {
		this.maxSize = tableSizeFor(maxSize);
		this.arr = new int[maxSize];
		this.pushIndex = -1;
		this.pollIndex = -1;
	}

	/**
	 * 返回给定目标容量的两倍幂。
	 *
	 * @param cap 目标容量
	 * @return 返回
	 */
	private int tableSizeFor(int cap) {
		int n = cap - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		int MAXIMUM_CAPACITY = 1 << 30;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}

	private boolean isEmpty() {
		return size == 0;
	}

	private boolean isFull() {
		return size == maxSize;
	}

	public void push(int val) {
		if (isFull()) {
			System.out.println("已经满了，不能加入了。");
			return;
		}
		pushIndex = getIndex(++pushIndex);
		arr[pushIndex] = val;
		size++;

	}

	public int poll() {
		if (isEmpty()) {
			throw new RuntimeException("暂无数据");
		}
		pollIndex = getIndex(++pollIndex);
		size--;
		return arr[pollIndex];
	}

	private int getIndex(int index) {
		return index & maxSize - 1;
	}

	public void show() {
		if (isEmpty()) {
			throw new RuntimeException("暂无数据");
		}
		for (int i : arr) {
			System.out.printf("%d\t",i);
		}
	}

}
