package wrx.xing.concurrent;


/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-03-22 20:35
 */
public class VolatileDemo {
	public static void main(String[] args) {
		VolatileTest demo = new VolatileTest();
		new Thread( () -> {
			System.out.println(Thread.currentThread().getName() + "\t start...");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			demo.setNumber(20);
			System.out.println(Thread.currentThread().getName() + "\t number:"+demo.number);
		},"aa").start();

		while (demo.number == 0) {

		}

		System.out.println("main finish,number="+demo.number);
	}

}

class VolatileTest {
	volatile int number = 0;

	public void setNumber(int number) {
		this.number = number;
	}

}
