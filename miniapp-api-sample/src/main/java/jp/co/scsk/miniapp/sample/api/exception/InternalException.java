package jp.co.scsk.miniapp.sample.api.exception;

import jp.co.scsk.miniapp.sample.api.constants.MessageConst;

public class InternalException extends Exception{

    private final MessageConst.MessageCode code;
    private final String data;

    public InternalException(MessageConst.MessageCode code) {
        super();
        this.code = code;
        this.data = null;
    }

    public InternalException(MessageConst.MessageCode code, String data) {
        super();
        this.code = code;
        this.data = data;
    }

    public MessageConst.MessageCode getCode(){
        return code;
    }

    public String getData(){
        return data;
    }
}
