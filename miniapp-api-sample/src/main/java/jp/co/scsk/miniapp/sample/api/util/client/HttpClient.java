package jp.co.scsk.miniapp.sample.api.util.client;

import cn.hutool.json.JSONUtil;
import okhttp3.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

@Component
public class HttpClient {

    private final static MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    public final static String GET_METHOD = "GET";
    public final static String POST_METHOD = "POST";

    public String executeRequest(String host, int port, String path,
                                 String scheme, String method,
                                 Map<String, String> param, String requestBodyContent,
                                 Map<String, String> headMap)
            throws IOException {
        RequestBody requestBody = StringUtils.hasText(requestBodyContent) ?
                RequestBody.create(requestBodyContent, mediaType) : null;
        Headers headers = headMap != null && !headMap.isEmpty() ? Headers.of(headMap) : null;
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder urlBuilder =
                new HttpUrl.Builder()
                        .scheme(scheme)
                        .host(host);
        if(port > 0) {
            urlBuilder.port(port);
        }
        urlBuilder.addPathSegments(path);
        if (param != null && !param.isEmpty()) {
            param.forEach(urlBuilder::addQueryParameter);
        }

        Request.Builder requestBuilder = new Request.Builder()
                .url(urlBuilder.build())
                .method(method, requestBody);
        if (headers != null) {
            requestBuilder.headers(headers);
        }
        Request request = requestBuilder.build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }
}
