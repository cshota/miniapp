package jp.co.scsk.miniapp.sample.api.exception;

import jp.co.scsk.miniapp.sample.api.constants.MessageConst;

public class MessageException extends Exception{

    private final MessageConst.MessageCode code;
    private final String data;

    public MessageException(MessageConst.MessageCode code) {
        super();
        this.code = code;
        this.data = null;
    }

    public MessageException(MessageConst.MessageCode code, String data) {
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
