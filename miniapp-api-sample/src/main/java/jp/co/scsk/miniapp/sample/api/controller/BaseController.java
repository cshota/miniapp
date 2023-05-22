package jp.co.scsk.miniapp.sample.api.controller;

import jp.co.scsk.miniapp.sample.api.constants.AuthConst;
import jp.co.scsk.miniapp.sample.api.util.JWTUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class BaseController {

    /**
     * get the Id from current user
     * @return the username
     */
    public static Long getUserId() {
        return JWTUtil.getUserId(getToken());
    }

    /**
     * Get current user's request
     * @return the request object
     */
    private static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    /**
     * Get current user's JWT token
     * @return the token object
     */
    public static String getToken() {
        return Objects.requireNonNull(getRequest()).getHeader(AuthConst.HTTP_HEADER_TOKEN);
    }

}
