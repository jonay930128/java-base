package wrx.xing.concurrent.demo131;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-05-23 20:21
 */
public class Service {

	private boolean flag = true;

	public void runMethod() {
		String str = "sss";
		while (flag) {
//			synchronized (str){
//
//			}
		}
		System.out.println("停下来");
	}

	public void stopMethod() {
		this.flag = false;
	}

}
