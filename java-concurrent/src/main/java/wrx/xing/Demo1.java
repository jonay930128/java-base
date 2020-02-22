package wrx.xing;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2020-02-06 14:24
 */
public class Demo1 {

	public static void main(String[] args) throws InterruptedException {

		CountDownLatch latch = new CountDownLatch(10);

		Ticket ticket = new Ticket();
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (ticket.bugTicket()) {}
				latch.countDown();
			},i + "").start();
		}

		latch.await();
		System.out.println("票卖光了");


	}
}

class Ticket {
	private AtomicInteger tickets = new AtomicInteger(1000);

	boolean bugTicket() {
		if (tickets.get() == 0) {
			return false;
		}
		System.out.println(Thread.currentThread().getName() + "抢到了票，剩余票是：" + tickets.decrementAndGet());
		return true;
	}
}