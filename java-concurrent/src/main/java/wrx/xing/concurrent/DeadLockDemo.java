package wrx.xing.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-04-07 16:12
 */
public class DeadLockDemo {

	public static void main(String[] args) {
		Integer lock1 = 1;
		Integer lock2 = 2;
		new Thread(new HoldLockThread(lock1,lock2),"aaaa").start();
		new Thread(new HoldLockThread(lock2,lock1),"bbbb").start();
	}

}

class HoldLockThread implements Runnable{
	private final Integer lock1;
	private final Integer lock2;

	public HoldLockThread(int lock1, int lock2) {
		this.lock1 = lock1;
		this.lock2 = lock2;
	}

	@Override
	public void run() {
		synchronized (lock1) {
			System.out.println(Thread.currentThread().getName() + "\t 持有" + lock1 + ",尝试获取" + lock2);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (lock2) {
				System.out.println(Thread.currentThread().getName() + "\t hahahha");
			}
		}
	}
}
