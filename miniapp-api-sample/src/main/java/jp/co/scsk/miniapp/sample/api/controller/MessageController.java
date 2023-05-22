package jp.co.scsk.miniapp.sample.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jp.co.scsk.miniapp.sample.api.constants.MessageConst;
import jp.co.scsk.miniapp.sample.api.entity.dto.PushMessageDTO;
import jp.co.scsk.miniapp.sample.api.entity.vo.MessageVO;
import jp.co.scsk.miniapp.sample.api.exception.InternalException;
import jp.co.scsk.miniapp.sample.api.exception.MessageException;
import jp.co.scsk.miniapp.sample.api.service.MessageService;
import jp.co.scsk.miniapp.sample.api.util.R;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Tag(name = "MessageController")
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Resource
    MessageService messageService;

    @Operation(summary = "push message")
    @PostMapping(value = "/push")
    public R pushMessage(@RequestBody PushMessageDTO message)
            throws MessageException, IOException, InternalException {
        if (Strings.isBlank(message.getMessageTitle())) {
            throw new MessageException(MessageConst.MessageCode.MESSAGE_IS_NONE, "message is blank");
        }
        messageService.pushMessage(message, getUserId());
        return R.ok();
    }

    @Operation(summary = "list message")
    @GetMapping(value = "/list")
    public R listMessage() {
        List<MessageVO> messageList = messageService.getMessageList(getUserId());
        return R.ok().put("messageList", messageList);
    }
}
