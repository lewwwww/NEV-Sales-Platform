package com.qf.config;

import com.qf.entity.EIException;
import com.qf.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 统一捕获 Controller / Service 抛出的异常，返回统一格式 R（code/msg），
 * 避免异常堆栈直接暴露给前端，同时让 EIException 自定义业务异常真正生效。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常：由代码主动抛出 EIException，携带自定义 code 和 msg
     */
    @ExceptionHandler(EIException.class)
    public R handleEIException(EIException e) {
        log.warn("业务异常 code={}, msg={}", e.getCode(), e.getMsg());
        return R.error(e.getCode(), e.getMsg());
    }

    /**
     * 参数校验异常（JSR303 @Valid 校验不通过）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldError() == null
                ? "参数校验失败"
                : e.getBindingResult().getFieldError().getDefaultMessage();
        log.warn("参数校验失败: {}", msg);
        return R.error(400, msg);
    }

    /**
     * 兜底异常：未知异常统一返回 500，避免堆栈裸奔，同时打印完整堆栈方便排查
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.error("系统异常", e);
        return R.error(500, "系统繁忙，请稍后重试");
    }
}
