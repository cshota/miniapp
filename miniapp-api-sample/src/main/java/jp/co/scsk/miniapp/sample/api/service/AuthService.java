package jp.co.scsk.miniapp.sample.api.service;

import jp.co.scsk.miniapp.sample.api.entity.dto.SignDTO;
import jp.co.scsk.miniapp.sample.api.exception.AuthenticationException;

import java.io.IOException;

public interface AuthService {
    SignDTO signIn(String accessCode) throws IOException, AuthenticationException;

    String getAccessToken(long userId);
}
