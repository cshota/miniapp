package jp.co.scsk.miniapp.sample.api.service.impl;

import jp.co.scsk.miniapp.sample.api.constants.MessageConst;
import jp.co.scsk.miniapp.sample.api.dao.MessageDao;
import jp.co.scsk.miniapp.sample.api.entity.Message;
import jp.co.scsk.miniapp.sample.api.entity.dto.CustomerMessageDTO;
import jp.co.scsk.miniapp.sample.api.entity.dto.PushMessageDTO;
import jp.co.scsk.miniapp.sample.api.entity.vo.MessageVO;
import jp.co.scsk.miniapp.sample.api.exception.InternalException;
import jp.co.scsk.miniapp.sample.api.service.AuthService;
import jp.co.scsk.miniapp.sample.api.service.MessageService;
import jp.co.scsk.miniapp.sample.api.util.R;
import jp.co.scsk.miniapp.sample.api.util.SnowflakeUtil;
import jp.co.scsk.miniapp.sample.api.util.client.MiniAppApiClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    MiniAppApiClient miniAppApiClient;

    @Resource
    MessageDao messageDao;

    @Resource
    AuthService authService;

    @Override
    @Transactional
    public void pushMessage(PushMessageDTO messageDTO, Long userId)
            throws IOException, InternalException {
        CustomerMessageDTO customerMessageDTO = new CustomerMessageDTO();
        customerMessageDTO.setMessageTitle(messageDTO.getMessageTitle());
        customerMessageDTO.setMessageBody(messageDTO.getMessageBody());
        customerMessageDTO.setClickPagePath(messageDTO.getClickPagePath());
        customerMessageDTO.setAccessToken(authService.getAccessToken(userId));
        R r = miniAppApiClient.pushMessage(customerMessageDTO);
        if ("OK".equals(r.get("result"))) {
            Message message = new Message();
            message.setId(SnowflakeUtil.getSnowflakeId());
            message.setCreateAt(new Date());
            message.setUserId(userId);
            message.setTitle(messageDTO.getMessageTitle());
            message.setMessage(messageDTO.getMessageBody());
            message.setClickPagePath(messageDTO.getClickPagePath());
            messageDao.insert(message);
        } else {
            throw new InternalException(MessageConst.MessageCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<MessageVO> getMessageList(Long userId) {
        return messageDao.getMessageListByUserId(userId);
    }
}
