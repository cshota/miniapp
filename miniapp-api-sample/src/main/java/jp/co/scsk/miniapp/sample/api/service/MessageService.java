package jp.co.scsk.miniapp.sample.api.service;

import jp.co.scsk.miniapp.sample.api.entity.dto.PushMessageDTO;
import jp.co.scsk.miniapp.sample.api.entity.vo.MessageVO;
import jp.co.scsk.miniapp.sample.api.exception.InternalException;

import java.io.IOException;
import java.util.List;

public interface MessageService {
    void pushMessage(PushMessageDTO message, Long userId) throws IOException, InternalException;

    List<MessageVO> getMessageList(Long userId);
}
