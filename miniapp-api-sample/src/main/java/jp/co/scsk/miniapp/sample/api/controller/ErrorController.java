package jp.co.scsk.miniapp.sample.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import jp.co.scsk.miniapp.sample.api.constants.MessageConst;
import jp.co.scsk.miniapp.sample.api.exception.AuthenticationException;
import jp.co.scsk.miniapp.sample.api.util.R;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {
    /**
     * HTTP 401
     */
    @Operation(summary = "error", hidden = true)
    @RequestMapping("/401")
    public R http401 (@RequestAttribute("messageCode") MessageConst.MessageCode messageCode,
                      @RequestAttribute("errorField") String errorField) throws AuthenticationException {
        if (messageCode == null){
            throw new AuthenticationException(MessageConst.MessageCode.AUTH_TOKEN_EXPIRED);
        } else if(StringUtils.hasText(errorField)){
            throw new AuthenticationException(messageCode, "{\"fieldName\":\""+errorField+"\"}");
        } else {
            throw new AuthenticationException(messageCode);
        }

    }

}

