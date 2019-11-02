package wrx.xing.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-04-04 21:08
 */
public class BlockQueueConsumerAndProducer {

	public static void main(String[] args) throws InterruptedException {
		ShareResource shareResource = new ShareResource(new SynchronousQueue<>());
		new Thread(() -> {
			try {
				shareResource.increment();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		new Thread(() -> {
			try {
				shareResource.decrement();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		TimeUnit.SECONDS.sleep(5);
		shareResource.stop();
	}

}

class ShareResource {
	private volatile boolean flag = true;
	private AtomicInteger atomicInteger = new AtomicInteger();
	private BlockingQueue<Integer> blockingQueue = null;

	public ShareResource(BlockingQueue<Integer> blockingQueue) {
		this.blockingQueue = blockingQueue;
		System.out.println("queueName = " + blockingQueue.getClass().getName());

	}

	public void increment() throws Exception {
		while (flag) {
			boolean offer = blockingQueue.offer(atomicInteger.incrementAndGet(), 1, TimeUnit.SECONDS);
			if (offer) {
				System.out.println("生产蛋糕成功：" + atomicInteger.get());
			}else {
				System.out.println("生产蛋糕失败");
			}
			TimeUnit.SECONDS.sleep(1);
		}
	}


	public void decrement() throws Exception {
		while (flag) {
			Integer poll = blockingQueue.poll(1, TimeUnit.SECONDS);
			if (null == poll) {
				this.flag = false;
				return;
			}else {
				System.out.println("消费蛋糕成功：" + atomicInteger.get());
			}
			TimeUnit.SECONDS.sleep(1);
		}
	}


	public void stop () {
		this.flag = false;
		System.out.println("工厂倒闭了");
	}
}