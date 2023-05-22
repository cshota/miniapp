package jp.co.scsk.miniapp.sample.api.exception;

import jp.co.scsk.miniapp.sample.api.constants.MessageConst.MessageCode;

public class AuthenticationException extends Exception{

    private final MessageCode code;
    private final String data;

    public AuthenticationException(MessageCode code) {
        super();
        this.code = code;
        this.data = null;
    }

    public AuthenticationException(MessageCode code, String data) {
        super();
        this.code = code;
        this.data = data;
    }

    public MessageCode getCode(){
        return code;
    }

    public String getData(){
        return data;
    }
}
