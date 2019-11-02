package wrx.xing.concurrent.demo131;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-05-23 20:25
 */
public class DemoTest {

	public static void main(String[] args) throws InterruptedException {
		Service service = new Service();
		new Thread(() -> {
			service.runMethod();
		},"AAA").start();
		Thread.sleep(1000);
		new Thread(() -> {
			service.stopMethod();
		},"BBB").start();
		System.out.println("main方法结束");
	}

}
