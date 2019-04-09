package wrx.xing.concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-03-24 15:17
 */
public class SpinLockDemo {
	// 原子引用
	private AtomicReference<Thread> atomicReference = new AtomicReference<>();

	private void myLock() {
		while (!atomicReference.compareAndSet(null,Thread.currentThread())) {

		}
		System.out.println(Thread.currentThread().getName() + "\t 获得锁");
	}

	private void myUnLock() {
		System.out.println(Thread.currentThread().getName() + "\t 释放锁");
		atomicReference.compareAndSet(Thread.currentThread(),null);
	}


	public static void main(String[] args) {
		SpinLockDemo spin = new SpinLockDemo();

		new Thread(() -> {
			spin.myLock();
			System.out.println(Thread.currentThread().getName() + "\t wolaila---------");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			spin.myUnLock();
		},"aa").start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(() -> {
			spin.myLock();
			System.out.println(Thread.currentThread().getName() + "\t wu lao er lai le ---------");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			spin.myUnLock();
		},"bb").start();

	}


}
