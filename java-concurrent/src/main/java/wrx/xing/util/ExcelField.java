package wrx.xing.util;


import wrx.xing.util.excel.RuleHandler;
import wrx.xing.util.excel.impl.NoRuleHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请填写类的描述
 * excel导入导出的标识注解
 * @author wangruxing
 * @date 2018-12-07 09:52
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {
	/**
	 * 标题
	 * @return
	 */
	String header();
	/**
	 * 导出字段字段排序（升序）
	 */
	int sort() default 0;

	Class<? extends RuleHandler> rule() default NoRuleHandler.class;

	String ruleParam() default "";
}
