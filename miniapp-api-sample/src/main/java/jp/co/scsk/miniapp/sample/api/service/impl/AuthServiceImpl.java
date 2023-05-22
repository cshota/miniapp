package jp.co.scsk.miniapp.sample.api.service.impl;

import jp.co.scsk.miniapp.sample.api.constants.MessageConst;
import jp.co.scsk.miniapp.sample.api.dao.AuthDao;
import jp.co.scsk.miniapp.sample.api.entity.Customer;
import jp.co.scsk.miniapp.sample.api.entity.dto.CustomerInfoDTO;
import jp.co.scsk.miniapp.sample.api.entity.dto.SignDTO;
import jp.co.scsk.miniapp.sample.api.exception.AuthenticationException;
import jp.co.scsk.miniapp.sample.api.service.AuthService;
import jp.co.scsk.miniapp.sample.api.util.JWTUtil;
import jp.co.scsk.miniapp.sample.api.util.client.MiniAppApiClient;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private MiniAppApiClient miniAppApiClient;

    @Resource
    private AuthDao authDao;

    @Override
    @Transactional
    public SignDTO signIn(String accessCode) throws IOException, AuthenticationException {
        // get accessToken by accessCode
        String accessToken = miniAppApiClient.getAccessToken(accessCode);
        // get userInfo by accessToken
        CustomerInfoDTO customInfo = miniAppApiClient.getCustomInfo(accessToken);
        // save userInfo
        if (Strings.isBlank(customInfo.getOpenId())) {
            throw new AuthenticationException(
                    MessageConst.MessageCode.AUTHENTICATION_ERROR, "invalid accessCode");
        }
        Customer byUserId = authDao.getByUserId(Long.parseLong(customInfo.getOpenId()));
        Customer customer = new Customer();
        customer.setUserId(Long.parseLong(customInfo.getOpenId()));
        customer.setCustomerInfo(customInfo.getCustomerInfo());
        customer.setUpdateAt(new Date());
        customer.setAccessToken(accessToken);
        if (byUserId == null) {
            customer.setCreateAt(new Date());
            authDao.insert(customer);
        } else {
            authDao.update(customer);
        }
        // generate JWT
        SignDTO signDTO = new SignDTO();
        signDTO.setJwt(JWTUtil.generateJWTToken(customer.getUserId()));
        signDTO.setCustomId(String.valueOf(customer.getUserId()));
        return signDTO;
    }

    @Override
    public String getAccessToken(long userId) {
        return authDao.getByUserId(userId).getAccessToken();
    }

}
