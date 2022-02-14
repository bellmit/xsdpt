package exception;


import lombok.Data;

/**
 * Ajax 请求时的自定义查询状态码，主要参考Http状态码，但并不完全对应
 * @author diaolin
 * @date 2020/04/17
 */
public enum HttpCode {

	FAIL("-1","未知异常"),
	CSERROR("1","请求参数异常，不符合规范"),
	DATA_ARR_ERROR("2","号码集合或地址集合必须至少有一个存在数据"),
	USER_ERROR("3","用户代码重复，请重新填写！"),
	INTERNAL_SERVER_ERROR("500","系统走神了,请稍候再试."),
	OK("200","接口请求成功");

	private final String value;

	private final String msg;

	private HttpCode(String value,String msg) {
		this.value = value;
		this.msg = msg;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public String value() {
		return this.value;
	}

	public String msg() {
		return this.msg;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}
}
