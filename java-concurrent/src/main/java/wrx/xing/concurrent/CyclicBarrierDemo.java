package wrx.xing.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 请填写类的描述
 * 集齐7颗龙珠召唤神龙
 * @author wangruxing
 * @date 2019-03-10 11:11
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> System.out.println("龙哥出来吧"));
		for (int i = 0; i < 7; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "\t找到一颗龙珠");
				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			},String.valueOf(i)).start();
		}
	}
}
