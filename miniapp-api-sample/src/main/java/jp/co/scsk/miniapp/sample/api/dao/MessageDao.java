package jp.co.scsk.miniapp.sample.api.dao;

import jp.co.scsk.miniapp.sample.api.entity.Message;
import jp.co.scsk.miniapp.sample.api.entity.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageDao {

    void insert(Message message);

    List<MessageVO> getMessageListByUserId(long userId);
}
