package org.demo.security.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.demo.security.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class WebGlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(HttpServletResponse response, Exception e) {
        log.info("服务器异常");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new Result("system.err", e.getMessage(), null);
    }


}
