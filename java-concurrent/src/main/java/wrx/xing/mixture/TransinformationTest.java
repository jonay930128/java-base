package wrx.xing.mixture;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-08-19 11:36
 */
public class TransinformationTest {

	public static void main(String[] args) {
		Employee employee = new Employee("wrx", 18);
		pass(employee);
		System.out.println("main---" + employee);
	}

	private static void pass(Employee employee) {
		employee.setAge(22);
		employee = new Employee("wzy", 20);
		System.out.println("pass---" + employee);
	}
}
