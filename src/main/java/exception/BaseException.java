/**
 *
 */
package exception;

import com.nju.sdpt.util.StringUtil;

/**
 *
 * @author diaolin
 * @date 2020/04/17
 */
@SuppressWarnings("serial")
public abstract class BaseException extends RuntimeException {

	private Object[] params;

	public BaseException() {
	}

	public BaseException(Throwable ex) {
		super(ex);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, Object... params) {
		super(message);
		this.params = params;
	}

	public BaseException(String message, Throwable ex) {
		super(message, ex);
	}

	public BaseException(String message, Throwable ex, Object... params) {
		super(message, ex);
		this.params = params;
	}

	public void handler(Result result) {

		result.setCode(getCode().value())
				.setMsg(getCode().msg())
				.setTimestamp(System.currentTimeMillis());
	}

	@Override
	public String getMessage() {
		if (StringUtil.isNotBlank(super.getMessage())) {
			return super.getMessage();
		} else {
			return getCode().msg();
		}
	}

	protected abstract HttpCode getCode();
}
