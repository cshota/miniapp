package jp.co.scsk.miniapp.sample.api.util;

import org.springframework.util.DigestUtils;

public class MD5Util {

    /**
     * MD5 Encryption
     * @param str Orig String
     * @return Encrypted String
     */
    public static String getMD5String(String str){
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    /**
     * miniApp calculate sign
     * @param requestURI uri
     * @param method GET/POST
     * @param queryString query param
     * @param bodyString request body
     * @param timeoutStampString timeoutStamp
     * @param secretKey miniApp sk
     * @return sign string
     */
    public static String calculateSign(String requestURI, String method,
                                       String queryString, String bodyString,
                                       String timeoutStampString, String secretKey) {
        return getMD5String("/" + requestURI + method + queryString + bodyString
                + timeoutStampString + MD5Util.getMD5String(secretKey));
    }
}
