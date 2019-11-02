package wrx.xing.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-04-12 19:03
 */
public class ReadWriteLockDemo {
	public static void main(String[] args) {
		Resource resource = new Resource();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "\t开始写");
			resource.set("kljljlj");
			System.out.println(Thread.currentThread().getName() + "\t写完毕");
		},"write").start();

		try {
			TimeUnit.SECONDS.sleep(1);
			System.out.println("============");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "\t开始读");
			String s = resource.get();
			System.out.println(Thread.currentThread().getName() + "\t读完毕：" + s);
		},"read").start();
	}
}

class Resource {
	private String resource = "aaa";
	private ReadWriteLock rwLock = new ReentrantReadWriteLock();

	public String get() {
		try {
			rwLock.readLock().lock();
			return resource;
		}finally {
			rwLock.readLock().unlock();
		}
	}

	public void set(String str) {
		try {
			rwLock.writeLock().lock();
			this.resource = str;
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}finally {
			rwLock.writeLock().unlock();
		}
	}
}