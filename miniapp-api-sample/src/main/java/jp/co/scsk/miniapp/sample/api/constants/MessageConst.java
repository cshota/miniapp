package jp.co.scsk.miniapp.sample.api.constants;

public class MessageConst {

    public enum MessageCode{
        //HTTP 400
        MESSAGE_IS_NONE,

        //HTTP 401
        AUTHENTICATION_ERROR,
        AUTH_TOKEN_EXPIRED,

        //HTTP 500
        INTERNAL_SERVER_ERROR,
    }
}
