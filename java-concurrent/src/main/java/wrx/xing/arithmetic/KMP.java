package wrx.xing.arithmetic;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-12-30 13:30
 */
public class KMP {
	public static void main(String[] args) {

	}

	public static int[] kmpNext(String dest) {
		// 创建一个next数组保存部分匹配值
		int[] next = new int[dest.length()];
		// 如果字符串长度为1部分匹配值就是0
		next[0] = 0;
		for (int i = 1,j = 0; i < dest.length(); i++) {
			// 当dest.charAt(i) != dest.charAt(j)时，我们要从next[j - 1]开始获取新的j
			// 直到dest.charAt(i) == dest.charAt(j)退出
			// 这是KMP算法的核心
			while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
				j = next[j - 1];
			}
			// 当相等时，部分匹配值就是+1
			if (dest.charAt(i) == dest.charAt(j)) {
				j++;
			}
			next[i] = j;
		}
		return next;
	}

	public static int kmpSearch(String str1,String str2,int[] next) {
		for (int i = 0,j = 0; i < str1.length(); i++) {
			// 当str1.charAt(i) != str2.charAt(j)时需要调整j的大小
			// KMP算法的核心
			while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
				j = next[j - 1];
			}
			if (str1.charAt(i) == str2.charAt(j)) {
				j++;
			}
			if (j == str2.length()) {
				// +1是因为j++的时候i并没有++所以要+1；
				return i - j + 1;
			}
		}
		return -1;
	}
}
