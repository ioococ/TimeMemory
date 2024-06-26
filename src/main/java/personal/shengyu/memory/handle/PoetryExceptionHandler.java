package personal.shengyu.memory.handle;

import com.alibaba.fastjson.JSON;
import personal.shengyu.memory.config.PoetryResult;
import personal.shengyu.memory.utils.CodeMsg;
import personal.shengyu.memory.utils.PoetryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class PoetryExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public PoetryResult handlerException(Exception ex) {
        log.error("请求URL-----------------" + PoetryUtil.getRequest().getRequestURL());
        log.error("出错啦------------------", ex);
        if (ex instanceof PoetryRuntimeException) {
            PoetryRuntimeException e = (PoetryRuntimeException) ex;
            return PoetryResult.fail(e.getMessage());
        }

        if (ex instanceof PoetryLoginException) {
            PoetryLoginException e = (PoetryLoginException) ex;
            return PoetryResult.fail(300, e.getMessage());
        }

        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
            Map<String, String> collect = e.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return PoetryResult.fail(JSON.toJSONString(collect));
        }

        if (ex instanceof MissingServletRequestParameterException) {
            return PoetryResult.fail(CodeMsg.PARAMETER_ERROR);
        }

        return PoetryResult.fail(CodeMsg.FAIL);
    }
}
