package wrx.xing.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-04-03 21:41
 */
public class TraditionalConsumerAndProducer {
	public static void main(String[] args) {
		ShareData shareData = new ShareData();

		for (int i = 0; i < 5; i++) {

			new Thread(() -> {shareData.increment();}).start();
			new Thread(() -> {shareData.decrement();}).start();
		}
	}
}

class ShareData {
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void increment(){
		lock.lock();
		try {
			while (number != 0) {
				condition.await();
			}
			number++;
			System.out.println("number=======" + number);
			condition.signalAll();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}

	}

	public void decrement(){
		lock.lock();
		try {
			while (number == 0) {
				condition.await();
			}
			number--;
			System.out.println("number=======" + number);
			condition.signalAll();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}

	}
}
