package wrx.xing.util.excel;

/**
 * 请填写类的描述
 *
 * @author wangruxing
 * @date 2019-09-20 14:07
 */
public class CheckResult {
	/**
	 * 是否成功
	 */
	private boolean success;
	/**
	 * 若失败，失败信息
	 */
	private String errorMsg;

	public CheckResult(boolean success, String errorMsg) {
		this.success = success;
		this.errorMsg = errorMsg;
	}

	public static CheckResult buildSuccess(){
		return new CheckResult(true,null);
	}

	public static CheckResult buildFail(String msg){
		return new CheckResult(false,msg);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
