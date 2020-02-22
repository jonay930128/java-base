package wrx.xing.gaode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 请填写类的描述
 * (java8) 用 stream + lambda 将以下数组中的数字过滤出来，转换为 Integer 类型，并按照升序打印
 * [“abc”, ”3”, ”def”, “2”, “1”]
 * @author wangruxing
 * @date 2020-02-07 14:11
 */
public class Test4 {
	public static void main(String[] args) {
		String[] arr = {"abc","3","def","2","1"};
		List<Integer> collect = Stream.of(arr).filter(item -> {
			try {
				Integer.parseInt(item);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}).map(Integer :: parseInt).sorted().collect(Collectors.toList());

		for (Integer integer : collect) {
			System.out.println(integer);
		}
	}
}
