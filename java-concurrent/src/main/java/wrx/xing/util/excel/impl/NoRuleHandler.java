package wrx.xing.util.excel.impl;


import wrx.xing.util.excel.CheckResult;
import wrx.xing.util.excel.RuleHandler;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-09-20 14:33
 */
public class NoRuleHandler implements RuleHandler {
	@Override
	public CheckResult check(String value, String rule) {
		return new CheckResult(true,null);
	}
}
