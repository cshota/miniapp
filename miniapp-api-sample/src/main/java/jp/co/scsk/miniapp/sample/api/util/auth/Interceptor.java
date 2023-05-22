package jp.co.scsk.miniapp.sample.api.util.auth;

import jp.co.scsk.miniapp.sample.api.constants.AuthConst;
import jp.co.scsk.miniapp.sample.api.dao.AuthDao;
import jp.co.scsk.miniapp.sample.api.entity.Customer;
import jp.co.scsk.miniapp.sample.api.exception.AuthenticationException;
import jp.co.scsk.miniapp.sample.api.util.JWTUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.scsk.miniapp.sample.api.constants.MessageConst.MessageCode;

public class Interceptor implements HandlerInterceptor {

    @Resource
    private AuthDao authDao;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws AuthenticationException {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader(AuthConst.HTTP_HEADER_TOKEN);
        if (Strings.isBlank(token)) {
            throw new AuthenticationException(MessageCode.AUTHENTICATION_ERROR, "token is null.");
        }
        long userId = JWTUtil.getUserId(token);
        Customer customer = authDao.getByUserId(userId);
        if (customer == null) {
            throw new AuthenticationException(MessageCode.AUTHENTICATION_ERROR, "can not find user.");
        }
        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
    }


}