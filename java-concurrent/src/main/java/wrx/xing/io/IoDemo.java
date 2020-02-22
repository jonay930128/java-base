package wrx.xing.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-04-09 11:31
 */
public class IoDemo {
	public static void main(String[] args) throws Exception {
		// 这个集合里有1000个
		List<String> paths = Arrays.asList("1.txt","2.txt");
		List<List<String>> collect = paths.parallelStream().map(item -> {
			try {
				return top10(item);
			} catch (Exception e) {
				return new ArrayList<String>();
			}
		}).collect(Collectors.toList());



	}

	public static List<String> top10(String pathName)throws Exception  {
		InputStreamReader reader = new InputStreamReader(new FileInputStream(pathName));
		BufferedReader br = new BufferedReader(reader);
		String line = "";
		line = br.readLine();
		Map<String, Integer> map = new HashMap<>();
		while (line != null) {
			line = br.readLine();
			if (line != null && !"".equalsIgnoreCase(line) && !"null".equalsIgnoreCase(line)) {
				line = line.trim();
				if(null == line || "".equalsIgnoreCase(line) || "null".equalsIgnoreCase(line)) {
					continue;
				}
				String[] split = line.split(",");
				if (split.length != 2) {
					continue;
				}
				int num;
				try {
					num = Integer.parseInt(split[1]);
				} catch (NumberFormatException e) {
					continue;
				}
				Integer count = map.get(split[0]);
				if (null == count) {
					map.put(split[0], num);
				} else {
					map.put(split[0], count + num);
				}
			}
		}
		reader.close();

		List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, (o1, o2) -> o2.getValue() - o1.getValue());

		List<String> result = new ArrayList();
		if(list.isEmpty()) {
			return result;
		}else {
			for (int i = 0; i < 10 && i < list.size(); i++) {
				result.add(list.get(i).getKey());
			}
			return result;
		}

	}
}
