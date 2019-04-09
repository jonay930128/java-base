package wrx.xing.concurrent;

import java.util.concurrent.*;

/**
 * 请填写类的描述
 * 晚自习7个人，班长最后走锁门
 * @author wangruxing
 * @date 2019-03-10 10:53
 */
public class CountDownLatchDemo {
	private static CountDownLatch countDownLatch = new CountDownLatch(7);
	private static ExecutorService executor = Executors.newFixedThreadPool(7);
//	private static ExecutorService executor2 = new ThreadPoolExecutor(
//			0,
//			7,
//			1L,
//			TimeUnit.SECONDS,
//			new LinkedBlockingDeque<>(10),
//			Executors.defaultThreadFactory(),
//			new ThreadPoolExecutor.AbortPolicy()
//			);
	public static void main(String[] args) {
		for (int i = 1; i <= 7; i++) {
			executor.execute(() -> {
				System.out.println(Thread.currentThread().getName() + "\t自习完毕");
				countDownLatch.countDown();
			});
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("班长锁门");

	}
}
