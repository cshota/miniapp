package jp.co.scsk.miniapp.sample.api.exception;

import jp.co.scsk.miniapp.sample.api.constants.MessageConst.MessageCode;
import jp.co.scsk.miniapp.sample.api.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class AppExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @ExceptionHandler(AuthenticationException.class)
    public Object handleAuthenticationException(AuthenticationException e,
                                                HttpServletRequest request, HttpServletResponse response) {
        logger.error("AUTH ERROR AT "+request.getRequestURL().toString()+":"+request.getMethod());
        logger.error(e.getMessage(), e);
        response.setStatus(401);
        return R.error(e.getCode(), e.getMessage(), e.getData());
    }

    @ExceptionHandler(MessageException.class)
    public Object handleMessageException(MessageException e,
                                                HttpServletRequest request, HttpServletResponse response) {
        logger.error("message ERROR AT "+request.getRequestURL().toString()+":"+request.getMethod());
        logger.error(e.getMessage(), e);
        response.setStatus(400);
        return R.error(e.getCode(), e.getMessage(), e.getData());
    }

    @ExceptionHandler({ Exception.class })
    public Object handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        logger.error("ERROR IN HTTP "+request.getRequestURL().toString()+":"+request.getMethod());
        logger.error(e.getMessage(), e);
        response.setStatus(500);
        return R.error(MessageCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
