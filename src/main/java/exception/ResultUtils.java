package exception;

import org.springframework.http.ResponseEntity;

/**
 * 返回包装类
 * @author diaolin
 * @date 2020/04/17
 */
public class ResultUtils<T> {

    /**
     * http请求包装
     * @param data
     * @param <T>
     * @return
     */
    public static <T>  ResponseEntity<Result<T>> wrapSuccess(T data) {
        return wrapResult(data, HttpCode.OK.value(), HttpCode.OK.msg());
    }


    @Deprecated
    public static <T> ResponseEntity<Result<T>> wrapSuccess() {
        return wrapSuccess( null);
    }

    /**
     * http请求包装
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResponseEntity<Result<T>> wrapFail(String msg) {
        return wrapResult(null, HttpCode.FAIL.value(), msg);
    }

    /**
     * http请求包装
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResponseEntity<Result<T>> wrapFail(String code, String msg) {
        return wrapResult(null, code, msg);
    }


    /**
     * http请求包装
     * @param data
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResponseEntity<Result<T>> wrapResult(T data, String code, String msg) {
        Result<T> result = null;
        result = result == null ? new Result() : result;
        result.setData(data).setCode(code).setMsg(msg).setTimestamp(System.currentTimeMillis());
        return ResponseEntity.ok(result);
    }



}
