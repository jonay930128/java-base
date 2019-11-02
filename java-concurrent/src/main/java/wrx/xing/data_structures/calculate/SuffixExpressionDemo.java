package wrx.xing.data_structures.calculate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 请填写类的描述
 * 中缀表达式转后缀表达式，并用后缀表达式计算。
 * @author wangruxing
 * @date 2019-10-02 12:33
 */
public class SuffixExpressionDemo {

	public static void main(String[] args) {
		String expression = "3 + 4 * 2 - (2 + 6) / 2 + 1";
		System.out.println(toPolandNotation(analysisExpression(expression)));
		System.out.println(calculateBySuffixExpression(toPolandNotation(analysisExpression(expression))));
	}

	private static List<String> analysisExpression(String expression) {
		List<String> list = new ArrayList<>(expression.length());
		String num = "";
		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			if (ch == ' ') {
				continue;
			}
			// ascii码[48,57]是数字
			if (ch >= 48 && ch <= 57) {
				if (i == expression.length() - 1) {
					list.add("" + ch);
				}else {
					num += ch;
				}
			}else {
				if (!"".equals(num)) {
					list.add(num);
					num = "";
				}
				list.add("" + ch);
			}
		}
		return list;
	}

	/**
	 * 中缀表达式转后缀表达式（逆波兰表达式）
	 * 1：如果是数字直接加到list中
	 * 2：如果是"("直接加到栈中
	 * 3：如果是")"把栈中的元素依次弹出加到list中，直到遇到"("，并把"("也弹出，目的是去掉括号
	 * 4：如果是其他符号，当前符号的优先级小于等于栈顶元素的优先级，则把栈中元素依次弹出加到list中直到不满足条件为止。
	 * 5：接着4，把当然符号加入到栈中
	 * 6：最后把栈中所有元素依次弹出加到list中，返回list；
	 *
	 * @param expressionList
	 * @return
	 */
	private static List<String> toPolandNotation(List<String> expressionList) {
		Stack<String> stack = new Stack<>();
		ArrayList<String> list = new ArrayList<>();
		for (String item : expressionList) {
			// 判断如果是数字直接加到list中
			if (item.matches("\\d+")) {
				list.add(item);
			}else if (item.equals("(")) {
				stack.push(item);
			}else if (item.equals(")")) {
				// 如果是右括号将栈中数据加到list中，直到遇到左括号为止
				while (!stack.peek().equals("(")) {
					list.add(stack.pop());
				}
				// 这时也要把左括号弹出，为了消掉括号
				stack.pop();
			}else {
				// 当item的优先级小于等于栈顶的优先级，弹出并加入到list个中
				while (!stack.empty() && comparePriority(item,stack.peek()) < 1) {
					list.add(stack.pop());
				}
				stack.push(item);
			}
		}

		// 将栈中剩下的元素加入到list中
		if (!stack.empty()) {
			list.add(stack.pop());
		}
		return list;
	}


	private static int comparePriority(String operation1,String operation2) {
		Integer one = null;
		Integer two = null;
		if (operation1.equals("+") || operation1.equals("-")) {
			one = 1;
		}
		if (operation1.equals("*") || operation1.equals("/")) {
			one = 2;
		}
		if (operation2.equals("+") || operation2.equals("-")) {
			two = 1;
		}
		if (operation2.equals("*") || operation2.equals("/")) {
			two = 2;
		}

		if (one == null || two == null) {
			return 1;
		}

		return one.compareTo(two);
	}

	private static int calculateBySuffixExpression(List<String> expressionList) {
		Stack<String> stack = new Stack<>();
		for (String item : expressionList) {
			// 是数字
			if (item.matches("\\d+")) {
				stack.push(item);
			}else {
				int num1 = Integer.parseInt(stack.pop());
				int num2 = Integer.parseInt(stack.pop());
				int res = calculate(num1,num2,item);
				stack.push(res + "");
			}
		}
		return Integer.parseInt(stack.pop());
	}

	private static int calculate(int num1, int num2,String operation) {
		switch (operation){
			case "+":
				return num1 + num2;
			case "-":
				return num2 - num1;
			case "*" :
				return num1 * num2;
			case "/" :
				return num2 / num1;
		}
		return 0;
	}
}
