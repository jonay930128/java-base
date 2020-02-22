package wrx.xing.arithmetic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 请填写类的描述
 * 参考DFA算法demo:http://blog.csdn.net/chenssy/article/details/26961957
 *
 * @author wangruxing
 * @date 2019-04-09 15:26
 */
public class BadWordUtil {
	/**
	 * 词典map
	 */
	private Map<String, Object> wordMap;
	/**
	 * 读写锁
	 */
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

	/**
	 * 读取文件
	 * @param filePath 文件路径
	 * @return 返回集合
	 */
	public Set<String> readTxtByLine(String filePath) {
		Set<String> words = new HashSet<>();
		BufferedReader reader = null;
		String temp;
		try {
			reader = new BufferedReader(new InputStreamReader(BadWordUtil.class.getClassLoader().getResourceAsStream(filePath), "UTF-8"));
			while ((temp = reader.readLine()) != null) {
				words.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return words;
	}


	/**
	 * TODO 将我们的敏感词库构建成了一个类似与一颗一颗的树，这样我们判断一个词是否为敏感词时就大大减少了检索的匹配范围。
	 *
	 * @param keyWordSet 敏感词库
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void addBadWordToHashMap(Set<String> keyWordSet) {
		if (keyWordSet.isEmpty()) {
			return;
		}
		// 初始化敏感词容器，减少扩容操作
		wordMap = new HashMap(keyWordSet.size());
		Map nowMap;
		Map<String, String> newWorMap;
		//迭代keyWordSet
		for (String key : keyWordSet) {
			// 关键字
			nowMap = wordMap;
			for (int i = 0; i < key.length(); i++) {
				// 转换成char型
				char keyChar = key.charAt(i);
				// 获取
				Object wordMap = nowMap.get(keyChar);
				// 如果存在该key，直接赋值
				if (wordMap != null) {
					if (wordMap instanceof Map) {
						nowMap = (Map) wordMap;
					}
				}
				// 不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
				else {
					newWorMap = new HashMap<>(key.length());
					// 不是最后一个
					newWorMap.put("isEnd", "0");
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}

				if (i == key.length() - 1) {
					// 最后一个
					nowMap.put("isEnd", "1");
				}
			}
		}
	}

	/**
	 * 检查文字中是否包含敏感字符，检查规则如下：<br>
	 *
	 * @param txt 文本
	 * @param beginIndex 开始下标
	 * @return 如果存在，则返回敏感词字符的长度，不存在返回0
	 */
	@SuppressWarnings({"rawtypes"})
	private int checkBadWord(String txt, int beginIndex) {
		// 敏感词结束标识位：用于敏感词只有1位的情况
		boolean flag = false;
		// 匹配标识数默认为0
		int matchFlag = 0;
		char word;
		Map nowMap = wordMap;
		for (int i = beginIndex; i < txt.length(); i++) {
			word = txt.charAt(i);
			// 获取指定key
			if (nowMap.get(word) instanceof Map) {
				nowMap = (Map) nowMap.get(word);
			}else {
				break;
			}
			// 存在，则判断是否为最后一个
			if (nowMap != null) {
				// 找到相应key，匹配标识+1
				matchFlag++;
				// 如果为最后一个匹配规则,结束循环，返回匹配标识数
				if ("1".equals(nowMap.get("isEnd"))) {
					// 结束标志位为true
					flag = true;
				}
			}
			// 不存在，直接返回
			else {
				break;
			}
		}
        /*“粉饰”匹配词库：“粉饰太平”竟然说是敏感词
         * “个人”匹配词库：“个人崇拜”竟然说是敏感词
         * if(matchFlag < 2 && !flag){     
            matchFlag = 0;
        }*/
		if (!flag) {
			matchFlag = 0;
		}
		return matchFlag;
	}

	/**
	 * 判断文字是否包含敏感字符
	 *
	 * @param txt       文字
	 * @return 若包含返回true，否则返回false
	 */
	public boolean isContainBadWord(String txt) {
		rwLock.readLock().lock();
		try {
			boolean flag = false;
			for (int i = 0; i < txt.length(); i++) {
				// 判断是否包含敏感字符
				int matchFlag = checkBadWord(txt, i);
				// 大于0存在，返回true
				if (matchFlag > 0) {
					flag = true;
					break;
				}
			}
			return flag;
		}finally {
			rwLock.readLock().unlock();
		}
	}

	/**
	 * 替换敏感字字符
	 *
	 * @param txt 文本
	 * @param replaceChar 替换字符，默认*
	 */
	public String replaceBadWord(String txt, String replaceChar) {
		rwLock.readLock().lock();
		try {
			String resultTxt = txt;
			// 获取所有的敏感词
			Set<String> set = getBadWord(txt);
			Iterator<String> iterator = set.iterator();
			String word;
			String replaceString;
			while (iterator.hasNext()) {
				word = iterator.next();
				replaceString = getReplaceChars(replaceChar, word.length());
				resultTxt = resultTxt.replaceAll(word, replaceString);
			}

			return resultTxt;
		}finally {
			rwLock.readLock().unlock();
		}

	}

	/**
	 * 获取文字中的敏感词
	 *
	 * @param txt       文字
	 * @return 敏感词集合
	 */
	private Set<String> getBadWord(String txt) {
		Set<String> sensitiveWordList = new HashSet<>();

		for (int i = 0; i < txt.length(); i++) {
			// 判断是否包含敏感字符
			int length = checkBadWord(txt, i);
			// 存在,加入list中
			if (length > 0) {
				sensitiveWordList.add(txt.substring(i, i + length));
				// 减1的原因，是因为for会自增
				i = i + length - 1;
			}

		}

		return sensitiveWordList;
	}

	/**
	 * 获取文字中的敏感词
	 *
	 * @param txt       文字
	 * @return 敏感词集合
	 */
	public Set<String> getMax10BadWord(String txt) {
		rwLock.readLock().lock();
		try {
			Set<String> sensitiveWordSet = new HashSet<>();

			for (int i = 0; i < txt.length(); i++) {
				// 判断是否包含敏感字符
				int length = checkBadWord(txt, i);
				// 存在,加入list中
				if (length > 0) {
					sensitiveWordSet.add(txt.substring(i, i + length));
					// 减1的原因，是因为for会自增
					i = i + length - 1;
				}

				// 最多返回10条
				if (sensitiveWordSet.size() >= 10) {
					break;
				}
			}

			return sensitiveWordSet;
		}finally {
			rwLock.readLock().unlock();
		}

	}

	/**
	 * 获取替换字符串
	 *
	 * @param replaceChar 替换字符串
	 * @param length 长度
	 * @return 返回结果
	 */
	private String getReplaceChars(String replaceChar, int length) {
		StringBuilder resultReplace = new StringBuilder(replaceChar);

		for (int i = 1; i < length; i++) {
			resultReplace.append(replaceChar);
		}

		return resultReplace.toString();
	}

	/**
	 * 清空指定词
	 * @param badWords 敏感词
	 */
	public void cleanBadWord(Set<String> badWords) {
		if (!badWords.isEmpty()) {
			rwLock.writeLock().lock();
			try {
				for (String badWord : badWords) {
					clean(badWord);
				}
			}finally {
				rwLock.writeLock().unlock();
			}

		}
	}

	/**
	 * 递归清除
	 * @param badWord 数据
	 */
	private void clean(String badWord) {
		Map nowMap = wordMap;
		Map lastMap = wordMap;
		for (int i = 0; i < badWord.length(); i++) {
			char word = badWord.charAt(i);
			// 获取指定key
			Object o = nowMap.get(word);
			if (null != o) {

				if (i != 0) {
					if (lastMap.get(badWord.charAt(i - 1)) instanceof Map) {
						lastMap = (Map)lastMap.get(badWord.charAt(i - 1));
					}
				}
				if (o instanceof Map) {
					nowMap = (Map) o;
				}
			}else {
				break;
			}
			// 最后一个字
			if (i == badWord.length() - 1) {
				if (nowMap.size() > 1) {
					nowMap.put("isEnd","0");
				}else {
					lastMap.remove(word);
					if (badWord.length() - 1 > 0) {
						clean(badWord.substring(0,badWord.length() - 1));
					}
				}
			}

		}
	}
}