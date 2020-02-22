package wrx.xing.gaode;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 请填写类的描述
 * 写一个程序，由两个线程协作，按顺序打印出 1-10，其中一个线程打印奇数，另一个线程打印偶数，且两个线程要交替工作；
 * 例：
 * A 线程打印1，之后B 线程打印2，之后 A 线程打印 3，之后 B 线程打印 4……，直至 1-10 全部打印完毕；
 * @author wangruxing
 * @date 2020-02-07 14:01
 */
public class Test2 {
	public static void main(String[] args) {
		Resource resource = new Resource();
		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				resource.print1();
			}
		},"AA").start();
		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				resource.print2();
		}
		},"BB").start();
	}
}

class Resource {
	private AtomicInteger number = new AtomicInteger(1);
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();

	void print1() {
		lock.lock();
		try {
			while (number.get() % 2 == 0){
				c1.await();
			}
			System.out.println(Thread.currentThread().getName() + "打印值为：" + number.getAndIncrement());
			c2.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			;lock.unlock();
		}
	}
	void print2() {
		lock.lock();
		try {
			while (number.get() % 2 != 0){
				c2.await();
			}
			System.out.println(Thread.currentThread().getName() + "打印值为：" + number.getAndIncrement());
			c1.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			;lock.unlock();
		}
	}
}