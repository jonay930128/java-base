package wrx.xing.test;

//学生类实现Cloneable接口
class Student extends Person{
    private String name;  //姓名
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name= name;  
    } 
//   //重写clone方法
//   public Student clone() {
//        Student student = null;
//        try {
//            student = (Student) super.clone();
//            } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//            }
//            return student;
//   }


	public Student() {
		System.out.println("儿子构造");
	}

	{
		System.out.println("儿子普通代码块111");
	}
	{
		System.out.println("儿子普通代码块2222");
	}

	static {
		System.out.println("静态------");
	}
      
}

//学生类实现Cloneable接口
class Person implements Cloneable{

	private String name = "ddd";  //姓名

	private static Book book = new Book("llll");

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name= name;
	}
	//重写clone方法
	public Student clone() {
		Student student = null;
		try {
			student = (Student) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return student;
	}

	public Person() {
		System.out.println("老子构造");
	}

	{
		System.out.println("老子普通代码块111");
	}
	{
		System.out.println("老子普通代码块2222");
	}

	static {
		System.out.println("老子静态------");
	}

}

class Book {
	public Book(String name) {
		System.out.println(name);
	}
}

public class Test {  
      
    public static void main(String args[]) {  
        Student stu1 = new Student();  //创建学生1
        stu1.setName("test1");  

//        Student stu2 = stu1.clone();  //通过克隆创建学生2
//        stu2.setName("test2");


        System.out.println("学生1:" + stu1.getName());  
//        System.out.println("学生2:" + stu2.getName());
    }  
}