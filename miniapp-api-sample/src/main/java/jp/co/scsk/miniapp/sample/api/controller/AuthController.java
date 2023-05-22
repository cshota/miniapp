package jp.co.scsk.miniapp.sample.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jp.co.scsk.miniapp.sample.api.entity.dto.SignDTO;
import jp.co.scsk.miniapp.sample.api.exception.AuthenticationException;
import jp.co.scsk.miniapp.sample.api.service.AuthService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.io.IOException;

import static jp.co.scsk.miniapp.sample.api.constants.MessageConst.MessageCode;

@Tag(name = "AuthenticationController")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    AuthService authService;

    @Operation(summary = "SignIn")
    @GetMapping(value = "/sign")
    public SignDTO sign(String accessCode) throws AuthenticationException, IOException {
        if (Strings.isBlank(accessCode)) {
            throw new AuthenticationException(MessageCode.AUTHENTICATION_ERROR, "access code is blank");
        }
        return authService.signIn(accessCode);
    }
}
