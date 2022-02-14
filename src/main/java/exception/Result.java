package exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author diaolin
 * @date 2020/04/17
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求结果编码
     */
    @ApiModelProperty(value = "请求结果编码")
    private String code = HttpCode.OK.value();

    /**
     * 请求结果信息（data为空时才存在）
     */
    @ApiModelProperty(value = "请求结果信息（data为空时才存在）")
    private String msg;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private long timestamp = System.currentTimeMillis();

    /**
     * 返回值数据
     */
    @ApiModelProperty(value = "返回值数据")
    private T data;

}
