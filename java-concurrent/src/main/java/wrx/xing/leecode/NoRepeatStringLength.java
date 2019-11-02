package wrx.xing.leecode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 请填写类的描述
 * 最大不重复子串
 * @author wangruxing
 * @date 2019-10-29 13:00
 */
public class NoRepeatStringLength {
	public static void main(String[] args) {
		String s = "abcabbcade";
		System.out.println(maxLength(s));
		System.out.println(maxLength2(s));
	}

	private static int maxLength(String s) {
		if (null == s || s.length() == 0) {
			return 0;
		}
		int res = 0;
		Set<Character> set = new HashSet<>();
		for (int i = 0,j = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (set.contains(c)) {
				set.remove(s.charAt(j++));
			}else {
				set.add(c);
				res = Math.max(res,set.size());
			}
		}
		return res;
	}

	private static int maxLength2(String s) {
		if (null == s || s.length() == 0) {
			return 0;
		}
		int res = 0;
		Map<Character,Integer> map = new HashMap<>();
		for (int i = 0,j = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (map.containsKey(c)) {
				j = Math.max(res,map.get(c) + 1);
			}
			map.put(c,i);
			res = Math.max(res,i -j + 1);
		}
		return res;
	}

	/*private static int maxLength(String s) {
		if (null == s || s.length() == 0) {
			return 0;
		}
		int res = 0;
		Map<Character,Integer> map = new HashMap<>();
		for (int i = 0,j = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (map.containsKey(c) && j <= map.get(c)) {
				j = Math.max(res,map.get(c) + 1);
			}
			map.put(c,i);
			res = Math.max(res,i -j + 1);
		}
		return res;
	}*/
}
