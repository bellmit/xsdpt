package exception;


/**
 * @author diaolin
 * @date 2020/04/17
 */
@SuppressWarnings("serial")
public class BusinessException extends BaseException {
	private HttpCode httpCode = HttpCode.FAIL;

	public BusinessException() {
	}

	public BusinessException(Throwable ex) {
		super(ex);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(HttpCode httpCode) {
		super(httpCode.msg());
		this.httpCode = httpCode;
	}

	public BusinessException(String message, Throwable ex) {
		super(message, ex);
	}

	public BusinessException(String message, Object... params) {
		super(message, params);
	}

	public BusinessException(String message, Throwable ex, Object... params) {
		super(message, ex, params);
	}

	@Override
	protected HttpCode getCode() {
		return this.httpCode;
	}

	public HttpCode getHttpCode(){
		return this.httpCode;
	}
}
