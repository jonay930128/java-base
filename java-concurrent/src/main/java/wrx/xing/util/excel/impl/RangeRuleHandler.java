package wrx.xing.util.excel.impl;


import wrx.xing.util.excel.CheckResult;
import wrx.xing.util.excel.RuleHandler;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-09-20 14:20
 */
public class RangeRuleHandler implements RuleHandler {
	@Override
	public CheckResult check(String value, String rule) {

		String[] rules = rule.split(",");
		double min = Double.parseDouble(rules[0]);
		double max = Double.parseDouble(rules[1]);

		String errorMsg = String.format("请在[%s,%s]直接填写",rules[0],rules[1]);
		double v;
		try {
			v = Double.parseDouble(value);
		}catch (NumberFormatException e) {
			return CheckResult.buildFail(errorMsg);
		}

		if (v >= min && v <= max) {
			return CheckResult.buildSuccess();
		}else {
			return CheckResult.buildFail(errorMsg);
		}

	}
}
