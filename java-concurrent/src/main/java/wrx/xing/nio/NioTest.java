package wrx.xing.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-06-17 22:43
 */
public class NioTest {

	@Test
	public void test1() throws Exception{
		FileOutputStream fileOutputStream = new FileOutputStream("nioTest.txt");
		FileChannel channel = fileOutputStream.getChannel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		byte[] bytes = "hello world i am wrx".getBytes();
		for (int i = 0; i < bytes.length; i++) {
			byteBuffer.put(bytes[i]);
		}
		byteBuffer.flip();
		channel.write(byteBuffer);
		fileOutputStream.close();
	}
	@Test
	public void test2() throws Exception{
		FileInputStream inputStream = new FileInputStream("input.txt");
		FileOutputStream outputStream = new FileOutputStream("output.txt");

		FileChannel inputChannel = inputStream.getChannel();
		FileChannel outputChannel = outputStream.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		while (true) {
			buffer.clear();
			int read = inputChannel.read(buffer);
			System.out.println("read:" + read);
			if (read == -1) {
				break;
			}
			buffer.flip();
			outputChannel.write(buffer);
		}
		inputStream.close();
		outputStream.close();
	}

}
