package wrx.xing.gaode;

/**
 * 请填写类的描述
 * (java8) 定义一个 FunctionalInterface，使得可以将 System.out::println 赋值给该 FunctionInterface 类型的变量
 * @author wangruxing
 * @date 2020-02-07 14:11
 */
public class Test3 {
}

@FunctionalInterface
interface MyInterface {
	Object process(Object o);
}
