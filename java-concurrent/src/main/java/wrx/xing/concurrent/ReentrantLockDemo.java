package wrx.xing.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 请填写类的描述
 * 三个线程A,B,C交替执行，进行5轮
 *
 * Synchronized和Lock有什么区别？
 * 1：原始构成：
 * Synchronized是关键字属于JVM层面，monitorenter与monitorexit指令来控制。底层是通过monitor对象来完成的，
 * 其实wait/notify等方法也是依赖monitor对象。
 * Lock是具体的类（java.util.concurrent.locks.Lock）是api层面的锁。
 * 2：使用方法：
 * Synchronized不需要手动释放锁，当Synchronized代码块执行完成后系统会自动释放锁的占用
 * ReentrantLock需要手动释放锁，如果不是放可能造成死锁。
 * 3：等待是否可中断：
 * Synchronized不可中断，除非抛出异常或者正常执行完成。
 * ReentrantLock可中断，一个是设置超时方法tryLock(Long timeout,TimeUnit unit),一个是lockInterruptibly()
 * 放代码块中，调用interrupt()方法中断。
 * 4：加锁是否公平：
 * Synchronized非公平锁
 * ReentrantLock两者都可以，构造方法可以传一个boolean值。
 * 5：绑定多个条件Condition
 * Synchronized没有
 * ReentrantLock用来实现分组唤醒需要唤醒的线程，可以精确唤醒，而不是像Synchronized唤醒一个或者全部唤醒。
 *
 * @author wangruxing
 * @date 2019-11-28 21:18
 */
public class ReentrantLockDemo {

	public static void main(String[] args) {
		ShareResource2 share = new ShareResource2();
		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				share.print5();
			}
		},"AA").start();
		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				share.print10();
			}
		},"BB").start();
		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				share.print15();
			}
		},"CC").start();
	}

}

class ShareResource2 {
	private int number = 1;
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();

	void print5() {
		lock.lock();
		try {
			while (number != 1){
				c1.await();
			}
			System.out.println(Thread.currentThread().getName());
			number = 2;
			c2.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			;lock.unlock();
		}
	}
	void print10() {
		lock.lock();
		try {
			while (number != 2){
				c2.await();
			}
			System.out.println(Thread.currentThread().getName());
			number = 3;
			c3.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			;lock.unlock();
		}
	}
	void print15() {
		lock.lock();
		try {
			while (number != 3){
				c3.await();
			}
			System.out.println(Thread.currentThread().getName());
			number = 1;
			c1.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			;lock.unlock();
		}
	}
}