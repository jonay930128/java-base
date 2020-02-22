package wrx.xing.gaode;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 请填写类的描述
 *
 *  请用 3 种不同原理实现线程安全且延迟加载（懒汉模式）的单例（Singleton）类；
 *  1：双重判断
 *  2：静态内部类
 *  3：利用容器单例
 * @author wangruxing
 * @date 2020-02-07 13:51
 */
public class Test1 {
}

/**
 * 双重检测
 */
class Singleton1 {
	private volatile static Singleton1 instance;
	private Singleton1 (){}
	public static Singleton1 getInstance() {
		if (instance == null) {
			synchronized (Singleton1.class) {
				if (instance== null) {
					instance= new Singleton1();
				}
			}
		}
		return instance;
	}
}

/**
 * 静态内部类
 */
class Singleton2 {
	private Singleton2(){}
	public static Singleton2 getInstance(){
		return SingletonHolder.sInstance;
	}
	private static class SingletonHolder {
		private static final Singleton2 sInstance = new Singleton2();
	}
}

/**
 * 使用容器辅助
 */
class Singleton3 {
	private final static ConcurrentHashMap<Integer,Singleton3> map = new ConcurrentHashMap<>();
	private volatile static Singleton3 instance;
	private Singleton3(){}
	public synchronized static Singleton3 getInstance() {
		if (map.containsKey(1)) {
			return map.get(1);
		}
		map.putIfAbsent(1,instance = new Singleton3());
		return instance;
	}
}