package wrx.xing.io;

import java.io.*;
import java.util.*;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-04-09 11:31
 */
public class IoDemo {
	public static void main(String[] args) throws Exception{
		/* 读入TXT文件 */
		String pathname = "E:\\otherGit\\bannedwords\\pub_sms_banned_words.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
		File filename = new File(pathname); // 要读取以上路径的input。txt文件
		InputStreamReader reader = new InputStreamReader(
				new FileInputStream(filename)); // 建立一个输入流对象reader
		BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
		String line = "";
		line = br.readLine();
		Set<String> set = new LinkedHashSet<>();
		while (line != null) {
			line = br.readLine(); // 一次读入一行数据
			if (line != null && !"".equalsIgnoreCase(line) && !"null".equalsIgnoreCase(line)) {
				line = new String(Base64.getDecoder().decode(line)).trim();
				set.add(line);
			}
		}
		reader.close();

		/* 写入Txt文件 */
		File writename = new File("E:\\otherGit\\bannedwords\\pub_sms_banned_words2.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
		writename.createNewFile(); // 创建新文件
		BufferedWriter out = new BufferedWriter(new FileWriter(writename));
		for (String str : set) {


			out.write(str + "\r\n"); // \r\n即为换行

		}

		out.flush(); // 把缓存区内容压入文件
		out.close(); // 最后记得关闭文件
	}
}
