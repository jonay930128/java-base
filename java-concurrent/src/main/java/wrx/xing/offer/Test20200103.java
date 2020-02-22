package wrx.xing.offer;

import org.junit.Test;
import wrx.xing.leecode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2020-01-03 10:46
 */
public class Test20200103 {


	public static void main(String[] args) {
		String str = "abc";
		String str1 = findStr(str);
		if (null == str1) {
			return;
		}
	}

	public static List<Integer> subStr(String str1,String str2) {
		int length = str2.length();
		List<Integer> indexs = new ArrayList<>();
		// 找到str2的一个字符在str1中出现的位置，并保存集合中
		for(int i = 0; i < str1.length(); i++) {
			if(str1.charAt(i) == str2.charAt(0)) {
				indexs.add(i);
			}
		}
		if(indexs.isEmpty()) {
			return indexs;
		}
		for(int i = 0; i < indexs.size(); i++) {
			int index = indexs.get(i);
			int end = index + length;
			if(!str1.substring(index,end).equals(str2)) {
				indexs.remove(i);
				i--;
			}
		}
		return indexs;
	}


	public static String findStr(String s) {
		int max = 0;
		int first = 0;
		String res = null;
		for (int i = 1; i < s.length(); i++) {
			for (int k = 0, j = 0; j < s.length() - i; j++) {
				if (s.charAt(j) == s.charAt(j + i)) {
					k++;
				} else {
					k = 0;
				}
				if (k > max) {
					max = k;
					first = j - max + 1;
				}
			}
			if (max > 0) {
				res = s.substring(first, first + max);
			}
		}
		return res;
	}

}
