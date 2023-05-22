package jp.co.scsk.miniapp.sample.api.util.client;

import cn.hutool.json.JSONUtil;
import jp.co.scsk.miniapp.sample.api.constants.AuthConst;
import jp.co.scsk.miniapp.sample.api.entity.dto.CustomerMessageDTO;
import jp.co.scsk.miniapp.sample.api.util.DateTimeUtil;
import jp.co.scsk.miniapp.sample.api.util.MD5Util;
import jp.co.scsk.miniapp.sample.api.util.R;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import jp.co.scsk.miniapp.sample.api.entity.dto.CustomerInfoDTO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MiniAppApiClient {

    @Value("${miniapp-api.host}")
    private String host;

    @Value("${miniapp-api.port}")
    private int port;

    @Value("${miniapp-api.scheme}")
    private String scheme;

    @Value("${miniapp-api.access-key}")
    private String accessKey;

    @Value("${miniapp-api.secret-key}")
    private String secretKey;

    private long timeDiff = 0;

    @Value("${miniapp-api.timeout:600}")
    private long timeout;

    @Value("${miniapp-api.baseurl:api/}")
    private String apiBaseUrl;

    @Resource
    private HttpClient httpClient;

    @PostConstruct
    public void initTime() throws IOException {
        Long serverTime = getServerTime();
        this.timeDiff = (DateTimeUtil.getCurrentSecondStamp() - serverTime);
    }

    public Long getServerTime() throws IOException {
        String path = "integration/timing/getServerTime";
        String method = HttpClient.GET_METHOD;
        Map<String, String> headers = getAuthHeaders(path, method, "", "");
        String responseBody = executeHttpRequest(path, method, headers, null, null);
        return JSONUtil.parseObj(responseBody).get("serverTime", Long.class);
    }

    public String getAccessToken(String accessCode) throws IOException {
        String path = "integration/miniapi/getAccessToken";
        String method = HttpClient.GET_METHOD;
        Map<String, String> params = new HashMap<>();
        params.put("accessCode", accessCode);
        String paramsString = parseParams(params);
        Map<String, String> headers = getAuthHeaders(path, method, paramsString, "");
        String responseBody = executeHttpRequest(path, method, headers, params, null);
        return JSONUtil.parseObj(responseBody).get("accessToken", String.class);
    }

    public CustomerInfoDTO getCustomInfo(String accessToken) throws IOException {
        String path = "integration/miniapi/getCustomerInfo";
        String method = HttpClient.GET_METHOD;
        Map<String, String> params = new HashMap<>();
        params.put("accessToken", accessToken);
        String paramsString = parseParams(params);
        Map<String, String> headers = getAuthHeaders(path, method, paramsString, "");
        String responseBody = executeHttpRequest(path, method, headers, params, null);
        return JSONUtil.toBean(responseBody, CustomerInfoDTO.class);
    }

    public R pushMessage(CustomerMessageDTO message)
            throws IOException {
        String path = "integration/miniapi/sendMessage";
        String method = HttpClient.POST_METHOD;
        String requestBody = JSONUtil.parseObj(message).toString();
        Map<String, String> headers = getAuthHeaders(path, method, "", requestBody);
        String responseBody = executeHttpRequest(path, method, headers, null, requestBody);
        return JSONUtil.toBean(responseBody, R.class);
    }

    private Map<String, String> getAuthHeaders(String path, String method, String paramsString, String bodyString) {
        long timeoutSec = (DateTimeUtil.getCurrentSecondStamp() - this.timeDiff) + this.timeout;
        String timeoutTimeStampString = String.valueOf(timeoutSec);
        String sign = MD5Util.calculateSign(path, method, paramsString, bodyString, timeoutTimeStampString, secretKey);
        Map<String, String> headers = new HashMap<>();
        headers.put(AuthConst.HTTP_HEADER_MINI_MINIAPP_ACCESS_KEY, accessKey);
        headers.put(AuthConst.HTTP_HEADER_MINI_SIGN, sign);
        headers.put(AuthConst.HTTP_HEADER_MINI_TIMEOUT, timeoutTimeStampString);
        return headers;
    }

    private String parseParams(Map<String, String> params) {
        StringBuilder paramsString = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            params.forEach((k, v) -> {
                paramsString.append(k).append("=").append(v);
            });
        }
        return paramsString.toString();
    }

    private String executeHttpRequest(String path, String method, Map<String, String> headMap,
                                      Map<String, String> param, String requestBody)
            throws IOException {
        path = apiBaseUrl + path;
        String requestBodyContent = Strings.isBlank(requestBody) ? "" : requestBody;
        return httpClient.executeRequest(host, port, path, scheme, method, param, requestBodyContent, headMap);
    }

}
